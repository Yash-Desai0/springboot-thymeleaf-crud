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

 $.validator.addMethod(
     "regex",
     function(value, element, regexp) {
         var check = false;
         var re = new RegExp(regexp);
         return this.optional(element) || re.test(value);
     },
     "Special char not allowed!"
 );

$(document).ready(function() {
 $("#registrationForm").validate({
     rules: {
         'userName': {
         required: true,
         minlength: 6,
         maxlength: 20,
         },

         gender:{
            required: true
         },

         'dateOfBirth': {
         minAge: 18
         },

         'phoneNumber': {
          required: true,
          minlength: 10,
          maxlength: 10
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
         minAge: "age should be 18!"
         },

         biography: {
         maxlength: "Only 50 chars allowed!"
         },

         phoneNumber: {
         required: "Fill Phone Field",
         maxlength: "Only 10 allowed!"
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