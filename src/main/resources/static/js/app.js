 $.validator.addMethod("minAge", function(value, element, min) {
     var today = new Date();
     var birthDate = new Date(value);
     var age = today.getFullYear() - birthDate.getFullYear();

     if (age > min+1) {
         return true;
     }

     var m = today.getMonth() - birthDate.getMonth();

     if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) {
         age--;
     }

     return age >= min;
     },
 );

  $.validator.addMethod("maxAge", function(value, element, max) {
      var today = new Date();
      var birthDate = new Date(value);
      var age = today.getFullYear() - birthDate.getFullYear();

      if (age < max+1) {
          return true;
      }

      },);

 $.validator.addMethod(
     "regex",
     function(value, element, regexp) {
         var check = false;
         var re = new RegExp(regexp);
         return this.optional(element) || re.test(value);
     },
     "Special char not allowed!"
 );

 $.validator.addMethod(
      "phoneIn",
      function(phoneNumber, element) {
          phoneNumber = phoneNumber.replace(/\s+/g,"");
          return this.optional(element) || phoneNumber.match(/^[6-9]\d{9}$/);
      },
      "Please specify a valid phone number");

$(document).ready(function() {
 $("#registrationForm").validate({
     rules: {
         'userName': {
         required: true,
         minlength: 6,
         maxlength: 20
         },

         gender:{
            required: true
         },

         'dateOfBirth': {
           date:true,
           minAge: 18,
           maxAge: 60,
         },

         'phoneNumber': {
          required: true,
          minlength: 10,
          maxlength: 10,
          phoneIn: true
         },

         'biography': {
         maxlength: 50
         }
     },

     messages: {
         userName: {
         required: "Fill Username!",
         maxlength: "Username contains 20 characters!",
         minlength: "Minimum 6 characters required!"
         },

         gender: {
         required: "Select your gender!"
         },

         dateOfBirth: {
         minAge: "age should be 18!",
         maxAge: "You are not eligible.",
         date: "Please enter a valid date format."
         },

         biography: {
         maxlength: "Only 50 chars allowed!"
         },

         phoneNumber: {
         required: "Fill Phone Field",
         maxlength: "Only 10 allowed!"
         }
     },

     errorPlacement: function (error, element) {

         if (element.attr("name") == "gender") {
           error.addClass("invalid-feedback");
           error.insertAfter("#gender-error");
         } else {
           error.addClass("invalid-feedback");
           error.insertAfter(element);
         }
     },

     highlight: function (element) {
         $(element).closest('.control-group').removeClass('success').addClass('error');
     },

     submitHandler: function(form) {
         form.submit();
     }
   });
 });