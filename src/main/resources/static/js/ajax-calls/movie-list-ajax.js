
    $('#actors').chosen({ width: '461px' });

    var actorOptions;           // For store actor Options and make it global for access letter.

    var token = localStorage.getItem('jwtToken');

    fetchActorOptions();        // when page load fetch actor options from the database

    fetchMovies();              // When page load show all movies.

    function fetchActorOptions() {
        $.get("/api/v1/actors", function (data) {
            $("#actors").empty();
            actorOptions = data;                            // for not hit database.
            data.forEach(function (actor) {
                $("#actors").append(`<option value="${actor.id}">${actor.userName}</option>`);
            });
            $("#actors").trigger('chosen:updated');
        });
    }

    function fetchMovies(){
        $.ajax({
               method: 'GET',
               headers: {
                                                         'Authorization': 'Bearer ' + token // Include the token in the Authorization header
                                                     },
               url: '/api/v1/movies',
               success: function (response) {
                        movies = response.data;
                        populateMoviesAtDom(response)
               }
        })
    }

    var movies;                                             // For render table view

    // Movie table view
    function populateMoviesAtDom(movies = []) {
       $("#movie-data").empty();
       const actorElementCountTag = $("#movie-count");
       actorElementCountTag.text(movies.length ? 'Total Movies: ' + movies.length : "No Movies Yet!");

       movies.forEach(({ id, title, releaseDate, genre, actors }) => {
       $("#movie-data").append(`
       <tr>
           <td>${id}</td>
           <td>${title}</td>
           <td>${releaseDate}</td>
           <td>${genre}</td>
           <td>${actors.length > 0 ? actors.map(actor => actor.userName).join(", ") : "No Actors"}</td>
           <td>
               <button data-toggle="modal" data-target="#myModal" data-id="${id}">Edit</button>
               <button onclick="deleteMovieById(${id})">Delete</button>
           </td>
       </tr>`);
       });
    }

    $('#myModal').on('hidden.bs.modal', function () {
        $(this).find('form')[0].reset();
    });

    $('#myModal').on('show.bs.modal', function (event) {
        $(this).appendTo('body');
        var button = $(event.relatedTarget);
        var id = button.data('id');

        if (id) {
            populateDataToMovieForm(id);
        }
    });

    $('#movieForm').submit(function (event) {
        event.preventDefault();
        var isValid = $("#movieForm").valid();       // For validate movie form by jQuery validations
        if(isValid)
        {
            addOrUpdateMovieHandler();
        }
    });

    // Movie create or update
    function addOrUpdateMovieHandler() {

           var movie = {
               id: $('#id').val(),
               title: $('#title').val(),
               genre: $('#genre').val(),
               releaseDate: $('#releaseDate').val(),
               actors: $('#actors').val()
           };

           var url = movie.id ? `/api/v1/movies/${movie.id}` : '/api/v1/movies';

           $.ajax({
               async: false,
               type: movie.id ? 'PUT' : 'POST',
               url: url,
               headers: {
                                                         'Authorization': 'Bearer ' + token // Include the token in the Authorization header
                                                     },
               contentType: "application/json",
               dataType: "json",
               data: JSON.stringify(movie),
               success: function ({resultStatus}) {
                    if(resultStatus.status === "SUCCESS")
                    {
                        toastr.success("Movie " + (movie.id ? "updated" : "added") + " successfully.");
                        fetchMovies();
                        $('#myModal').modal('hide');
                        $('.modal-backdrop').remove();
                        $('.close').click();
                    }
               },
               error: function (errorResponse) {
                      toastr.error(errorResponse.responseText);
               }
           });
    }

    // populate the row data into model
    function populateDataToMovieForm(id) {
        $.ajax
            ({
                async: false,
                type: "GET",
                headers: {
                                                          'Authorization': 'Bearer ' + token // Include the token in the Authorization header
                                                      },
                contentType: "application/json",
                dataType: "json",
                url: "/api/v1/movies/" + id,
                success: (successResponse) =>
                {
                    document.getElementById("form-title").innerHTML = "Edit Movie";
                    $('#id').val(successResponse.id);
                    $('#title').val(successResponse.title);
                    $('#genre').val(successResponse.genre);
                    $('#releaseDate').val(successResponse.releaseDate);

                    $("#actors").empty();
                    actorOptions.forEach(function (actor) {
                    $("#actors").append(`<option value="${actor.id}" ${successResponse.actors.some(a => a.id === actor.id) ? 'selected' : ''}>${actor.userName}</option>`);
                    });
                    $("#actors").trigger('chosen:updated');

                },
                error: (errorResponse) => {
                    toastr.error("something went wrong!");
                },
            });
    }

    // Movie delete
    function deleteMovieById(id) {
        if (confirm('Are you sure you want to delete this movie?')) {
            $.ajax({
                    async: false,
                    type: "DELETE",
                    headers: {
                                                              'Authorization': 'Bearer ' + token // Include the token in the Authorization header
                                                          },
                    contentType: "application/json",
                    dataType: "json",
                    url: "/api/v1/movies/" + id,
                    success: ({resultStatus}) => {
                        if (resultStatus.status === "SUCCESS") {
                            toastr.success("Actor deleted successfully.");
                            fetchMovies();
                        }
                    },
                    error: (errorResponse) => {
                        alert(errorResponse.responseText);
                    },
                })
        }
    }


    // Clear the token from localStorage on logout
            $('#logoutButton').click(function() {
                localStorage.removeItem('jwtToken');
            });
