    var actors;     // for render table view

    // Get the token from localStorage
    var token = localStorage.getItem('jwtToken');

        fetchActors();  // when page load show all actors.

        function fetchActors(){
            $.ajax({
                method: 'GET',
                headers: {
                        'Authorization': 'Bearer ' + token // Include the token in the Authorization header
                    },
                url: '/api/v1/actors',
                success: function (response) {
                         actors = response.data;
                         populateActorsAtDom(response)      // populate the Actor to dom.
                }
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
               <td><img src="http://localhost:8086/api/v1/actors/${id}/getImage" width="50" height="50"></td>
               <td>
                   <button data-toggle="modal" data-target="#myModal" data-id="${id}">Edit</button>
                   <button onclick="deleteActorById(${id})">Delete</button>
               </td>
           </tr>`);
           });
        }

        $('#myModal').on('hidden.bs.modal', function () {           // when model hidden reset form
                $(this).find('form')[0].reset();
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
                    if(isValid)
                    {
                         if(actor.id)
                         {
                            updateActorImage(actor.id);
                         }
                         else
                         {
                         saveActor();
                         }
                        /*addOrUpdateActorHandler();*/
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
                      toastr.error(errorResponse.responseJSON.message);
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
                                updateActor(id);
                          /*fetchActors();*/
                            }
                  },
                  error: (errorResponse) => {
                      toastr.error(errorResponse.responseJSON.message);
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
//                                  fetchActors();
                              }
                          },
                          error: (errorResponse) => {
                              toastr.error(errorResponse.responseJSON.message);
                          },
                    });
                }

        /*// Actor create or update
        function addOrUpdateActorHandler() {            // both method post and put
           var selected = $("input[type='radio'][name='gender']:checked");


           var id = $('#id').val();
           var url = id ? `/api/v1/actors/${id}` : '/api/v1/actors';

           var formData = new FormData();
           formData.append('id',$('#id').val());
           formData.append('userName',$('#userName').val());
           formData.append('dateOfBirth',$('#dateOfBirth').val());
           formData.append('phoneNumber',$('#phoneNumber').val());
           formData.append('biography',$('#biography').val());
           formData.append('gender',selected.length > 0 ? selected.val() : 0);
           formData.append('image',$('#image')[0].files[0])
           $.ajax({
              async: false,
              type: id ? 'PUT' : 'POST',
              url: url,
//              contentType: "application/json",
//              dataType: "json",
//              data: JSON.stringify(actor),
              data: formData,
              mimeType: 'multipart/form-data',
              processData: false,
              contentType:false,
              success: function ({resultStatus}) {
                   if(resultStatus.status === "SUCCESS")
                   {
                        toastr.success("Actor " + (actor.id ? "updated" : "added") + " successfully.");
                        fetchActors();
                        $('#myModal').modal('hide');
                        $('.modal-backdrop').remove();
                        $('.close').click();
                   }
              },
              error: function (errorResponse) {
                    toastr.error(errorResponse.responseText);
                    console.log("actor ....");
              }
           });
        }*/


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
                        toastr.error(errorResponse.responseJSON.message);
                    },
            })
        }

        // Clear the token from localStorage on logout
        $('#logoutButton').click(function() {
            localStorage.removeItem('jwtToken');
        });


