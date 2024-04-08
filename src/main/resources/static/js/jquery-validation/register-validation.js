$.validator.addMethod("checklower", function(value) {
  return /[a-z]/.test(value);
});

$.validator.addMethod("checkupper", function(value) {
  return /[A-Z]/.test(value);
});

$.validator.addMethod("checkdigit", function(value) {
  return /[0-9]/.test(value);
});

$(document).ready(function() {
 $("#registerForm").validate({
     rules: {
         'userName': {
         required: true,
         minlength: 6,
         maxlength: 20
         },

         'password':{
            required: true,
            minlength:6,
            maxlength:20,
            checkupper:true,
            checklower:true,
            checkdigit:true
         },

          'email': {
           required:true,
           email:true
         },
     },

     messages: {
         userName: {
         required: "Fill Username!",
         maxlength: "Username contains 20 characters!",
         minlength: "Minimum 6 characters required!"
         },

         password: {
         required: "Enter your password!",
         checklower: "Need atleast 1 lowercase alphabet",
         checkupper: "Need atleast 1 uppercase alphabet",
         checkdigit: "Need atleast 1 digit"
         },

         email: {
         required: "Enter your email!",
         email: "Please Enter a valid email address"
         },
     },

     errorPlacement: function (error, element) {
           error.addClass("invalid-feedback");
           error.insertAfter(element);
     },

     highlight: function (element) {
         $(element).closest('.control-group').removeClass('success').addClass('error');
     },

     submitHandler: function(form) {
         form.submit();
     }
   });
 })
