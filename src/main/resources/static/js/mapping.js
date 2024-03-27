$(document).ready(function ()
{
     $("#movieForm").validate({
       rules: {
         title: {
           required: true,
         },
         releaseDate: {
           required: true,
         },
         genre: {
           required: true,
         },
         actors: {
           required: true,
         },
       },
       messages: {
         title: "Please enter movie title",
         releaseDate: "Please select release date",
         genre: "Please select genre",
         actors: "Please select at least one actor",
         gender: "Please select gender",
       },
       errorPlacement: function (error, element) {
         if (element.attr("name") == "actors") {
           error.addClass("invalid-feedback");
           error.insertAfter("#actors-error");
         } else {
           error.addClass("invalid-feedback");
           error.insertAfter(element);
         }
       },
       highlight: function (element, errorClass, validClass) {
         $(element).addClass("is-invalid").removeClass("is-valid");
       },
       unhighlight: function (element, errorClass, validClass) {
         $(element).removeClass("is-invalid").addClass("is-valid");
       },
     });
         console.log("jquery validations");
});