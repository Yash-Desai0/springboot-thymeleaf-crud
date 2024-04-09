    var actors;                              // for render table view

    var token = getCookie("Authorization"); // get the token from the cookie

    fetchActors();                          // when page load show all actors.

    function fetchActors(){
        $.ajax({
            method: 'GET',
            headers: {
                    'Authorization': 'Bearer ' + token          // Include the token in the Authorization header
            },
            url: '/api/v1/actors',
            success: function (response) {
                     actors = response.data;
                     populateActorsAtDom(response)      // populate the Actor to dom.
            },
            error: (errorResponse) =>{
                document.getElementById("logoutForm").style.display = "none";
                toastr.error(errorResponse.responseText);
            },
        })
    }

    // Actor table view
    function populateActorsAtDom(actors = []) {
       $("#actor-data").empty();
       const actorElementCountTag = $("#actor-count");
       actorElementCountTag.text(actors.length ? 'Total Actors: ' + actors.length : "No Movies Yet!");

       actors.forEach(({id, userName, gender, dateOfBirth, phoneNumber, biography, image}) => {
       const imageData = `data:image/png;base64,${image}`;
       $("#actor-data").append(`
       <tr>
           <td >${id}</td>
           <td >${userName}</td>
           <td >${gender}</td>
           <td >${dateOfBirth}</td>
           <td >${phoneNumber}</td>
           <td >${biography}</td>
           <td><img src="${imageData}" width="50" height="50"></td>    // src="http://localhost:8086/api/v1/actors/${id}/getImage" asking the 401.
           <td>
               <button data-toggle="modal" data-target="#myModal" data-id="${id}">Edit</button>
               <button onclick="deleteActorById(${id})">Delete</button>
           </td>
       </tr>`);
       });
    }

    $('#myModal').on('hidden.bs.modal', function () {           // when model hidden reset form
            $(this).find('form')[0].reset();
            document.getElementById("phoneNumber-error").style.display = "none";
            document.getElementById("dateOfBirth-error").style.display = "none";
            document.getElementById("gender-error").style.display = "none";
            document.getElementById("userName-error").style.display = "none";
    });

    $('#myModal').on('show.bs.modal', function (event) {       // when modal open check for id
        var button = $(event.relatedTarget);
        var id = button.data('id');

        if (id) {
            populateDataToActorForm(id);
        }
    });

    var actor = null;
    var selected = null;
    $('#actorForm').submit(function (event) {
        event.preventDefault();
        var isValid = $("#actorForm").valid();       // For validate actor form by jQuery validations
        selected = $("input[type='radio'][name='gender']:checked");
        actor = {
                    id: $('#id').val(),
                    userName: $('#userName').val(),
                    dateOfBirth: $('#dateOfBirth').val(),
                    gender: selected.length > 0 ? selected.val() : 0,
                    phoneNumber: $('#phoneNumber').val(),
                    biography: $('#biography').val(),
        }
        if(isValid) {
             if(actor.id) {
                updateActorImage(actor.id);
             }
             else {
                saveActor();
             }
        }
    });

    function saveActor()
    {
        console.log(actor);
        $.ajax({
              async: false,
              type: 'POST',
              url: '/api/v1/actors',
              headers: {
                   'Authorization': 'Bearer ' + token // Include the token in the Authorization header
              },
              contentType: "application/json",
              dataType: "json",
              data: JSON.stringify(actor),
              success: ({resultStatus}) => {
                  if (resultStatus.status === "SUCCESS") {
                      toastr.success("Actor saved successfully.");
                      fetchActors();
                  }
              },
              error: (errorResponse) => {
                console.log(errorResponse);
                    if(errorResponse.status == 401)
                        toastr.error(errorResponse.responseText);
                    else
                        toastr.error(errorResponse.responseText);
              },
        });
    }

    function updateActorImage(id)
    {
        var formData = new FormData();
        formData.append('image',$('#image')[0].files[0])

        $.ajax({
              async: false,
              type: 'PUT',
              headers: {
                    'Authorization': 'Bearer ' + token // Include the token in the Authorization header
              },
              url: "/api/v1/actors/" + id +'/updateImage',
              data: formData,
              mimeType: 'multipart/form-data',
              processData: false,
              contentType:false,
              success: function (resultStatus) {
                      if(resultStatus != null){
                            console.log("hello ");
                            toastr.success("Actor image updated successfully.");
                            updateActor(id);  // call the further update.
                        }
              },
              error: (errorResponse) => {
                   console.log(errorResponse);
                   if(errorResponse.status == 401)
                       toastr.error(errorResponse.responseText);
                   else
                       toastr.error(errorResponse.responseText);
              },
        });
    }

    function updateActor(id)
            {
                $.ajax({
                      async: false,
                      type: 'PUT',
                      headers: {
                            'Authorization': 'Bearer ' + token // Include the token in the Authorization header
                      },
                      url: '/api/v1/actors/' + id,
                      contentType: "application/json",
                      dataType: "json",
                      data: JSON.stringify(actor),
                      success: ({resultStatus}) => {
                        console.log(resultStatus);
                          if (resultStatus.status === "SUCCESS") {
                              toastr.success("Actor updated successfully.");
                              fetchActors();
                          }
                      },
                      error: (errorResponse) => {
                      if(errorResponse.status == 401)
                          toastr.error(errorResponse.responseText);
                      else
                          toastr.error(errorResponse.responseText);
                      },
                });
            }

        function populateDataToActorForm(id) {          // populate the row data into model
            $.ajax
            ({
                async: false,
                type: "GET",
                headers: {
                    'Authorization': 'Bearer ' + token // Include the token in the Authorization header
                },
                contentType: "application/json",
                dataType: "json",
                url: "/api/v1/actors/" + id,
                success: (successResponse) =>
                {
                        document.getElementById("form-title").innerHTML = "Edit Actor";
                        $('#id').val(successResponse.id);
                        $('#userName').val(successResponse.userName);
                        $("#gender" + successResponse.gender).prop('checked', true);
                        $('#dateOfBirth').val(successResponse.dateOfBirth);
                        $('#phoneNumber').val(successResponse.phoneNumber);
                        $('#biography').val(successResponse.biography);
                },
                error: (errorResponse) => {
                    toastr.error("something bad happended!");
                },
            });
        }

        // Actor delete
        function deleteActorById(id) {
            if (confirm('Are you sure you want to delete this movie?')) {
                $.ajax({
                        async: false,
                        type: "DELETE",
                        headers: {
                              'Authorization': 'Bearer ' + token // Include the token in the Authorization header
                        },
                        contentType: "application/json",
                        dataType: "json",
                        url: "/api/v1/actors/" + id,
                        success: ({resultStatus}) => {
                        console.log(resultStatus);
                            console.log(resultStatus.status);
                            if (resultStatus.status === "SUCCESS") {
                                toastr.success("Actor deleted successfully.");
                                fetchActors();
                            }
                        },
                        error: (errorResponse) => {
                            if(errorResponse.status == 401)
                                toastr.error(errorResponse.responseText);
                            else
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