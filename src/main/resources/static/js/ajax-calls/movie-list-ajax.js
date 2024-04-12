    $('#actors').chosen({ width: '461px' });

    var token = getCookie("Authorization"); // get the token from the cookie

    var actorOptions;           // For store actor Options and make it global for access letter.

    fetchActorOptions();        // when page load fetch actor options from the database

    fetchMovies();              // When page load show all movies.

    function fetchActorOptions() {
        $.ajax({
                       method: 'GET',
                       headers: {
                           'Authorization': 'Bearer ' + token                   // Include the token in the Authorization header
                       },
                       url: '/api/v1/actors',
                       success: function (data) {
                                $("#actors").empty();
                                actorOptions = data;                            // for not hit database.
                                data.forEach(function (actor) {
                                    $("#actors").append(`<option value="${actor.id}">${actor.userName}</option>`);
                                });
                                $("#actors").trigger('chosen:updated');
                       }
        })
    }

    function fetchMovies(){
        $.ajax({
           method: 'GET',
           headers: {
               'Authorization': 'Bearer ' + token                           // Include the token in the Authorization header
           },
           url: '/api/v1/movies',
           success: function (data) {
                $("#userTable").dataTable({
                    "retrieve":true,
                    "destroy":true,
                    "data": data,
                    "columns":[
                        {data:'title'},
                        {data:'releaseDate'},
                        {data:'genre'},
                        {data:'actors',
                         render: function (data) {
                               if(data){
                                    return data.map(actor => actor.userName).join(", ");
                               }else{
                                    return "No Actors";
                               }}
                        },
                        {data:null,
                             render: function (data) {
                                   return '<button data-toggle="modal" data-target="#myModal" data-id="'+ data.id +'">Edit</button>' + ' ' +
                                   '<button onclick="deleteMovieById('+ data.id+')">Delete</button>';
                             }
                        }
                    ]
                });
           },
           error: (errorResponse) =>{
               document.getElementById("logoutForm").style.display = "none";
               toastr.error(errorResponse.responseText);
           },
        })
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
                            setTimeout(function(){
                               window.location.reload();
                            }, 1000);
                        }
                    },
                    error: (errorResponse) => {
                            toastr.error(errorResponse.responseText);
                    },
                })
        }
    }

    // Clear the token from localStorage on logout
    if(token != null)
    {
        $('#logoutForm').click(function() {
                document.cookie = 'Authorization' + '=; expires=Thu, 01-Jan-70 00:00:01 GMT;';
        });
    }
    else
    {
        document.getElementById("logoutForm").style.display = "none";
    }

    function getCookie(cname) {
          let name = cname + "=";
          let decodedCookie = decodeURIComponent(document.cookie);
          let ca = decodedCookie.split(';');
          for(let i = 0; i < ca.length; i++) {
            let c = ca[i];
            while (c.charAt(0) == ' ') {
              c = c.substring(1);
            }
            if (c.indexOf(name) == 0) {
              return c.substring(name.length, c.length);
            }
          }
          return "";
    }
