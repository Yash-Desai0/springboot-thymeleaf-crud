$(document).ready(function() {
    $.ajax({
        url: "http://localhost:8086/api/v1/actors"
    }).then(function(data) {
       $('.greeting-id').append(data.id);
       $('.greeting-content').append(data.content);
    });
});