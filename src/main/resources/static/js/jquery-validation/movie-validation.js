
    $.validator.addMethod("regex",function(value, element, regexp) {
             var check = false;
             var re = new RegExp(regexp);
             return this.optional(element) || re.test(value);
         },);

      $.validator.addMethod("maxAge", function(value, element, max) {
            var today = new Date();
            var birthDate = new Date(value);
            var age = today.getFullYear() - birthDate.getFullYear();

            if (age < max+1) {
                return true;
            }

      },);

     $.validator.addMethod("future", function(value, element) {
           var futureDate = new Date('2026-12-31');
           var releaseDate = new Date(value);

           if(releaseDate < futureDate)
           {
                return true;
           }
     },"ReleaseDate should be within 2026!");

    $.validator.addMethod("maxAge", function(value, element, max) {
          var today = new Date();
          var birthDate = new Date(value);
          var age = today.getFullYear() - birthDate.getFullYear();

          if (age < max+1) {
              return true;
          }

    },);

$(document).ready(function ()
{
     $("#movieForm").validate({
       rules: {
         title: {
           required: true,
           regex: "^[a-zA-Z0-9\s ]+$"
         },
         releaseDate: {
           required: true,
           future:true,
           date:true,
           maxAge:60
         },
         genre: {
           required: true,
         },
         actors: {
           required: true,
         },
       },
       messages: {
         title: {
            required: "Please enter movie title",
            regex: "No special Characters allowed here"
         },
         releaseDate:{
            required: "Please select release date",
            date: "Please enter a valid date format.",
            maxAge: "your movie should be within 60 year range."
         },
         genre:{
            required: "Please select genre"
         },
         actors:{
            required: "Please select at least one actor"
         },
         gender:{
            required: "Please select gender"
         },
       },
       /*errorPlacement: function (error, element) {
         if (element.attr("name") == "actors") {
           error.addClass("invalid-feedback");
           error.insertAfter("#actors-error");
         } else {
           error.addClass("invalid-feedback");
           error.insertAfter(element);
         }
       },*/
       /*highlight: function (element, errorClass, validClass) {
         $(element).addClass("is-invalid").removeClass("is-valid");
       },
       unhighlight: function (element, errorClass, validClass) {
         $(element).removeClass("is-invalid").addClass("is-valid");
       },*/
       submitHandler: function(form) {               // if you don't want to highlight border then remove comment.
                form.submit();
            }
     });
});