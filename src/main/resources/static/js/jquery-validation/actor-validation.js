$.validator.addMethod('imageFormat', function(value, element) {
    // If no file is selected, consider it valid
    if (element.files.length === 0) {
        return true;
    }

    // Check if the file type is an image
    var fileType = element.files[0].type;
    var validTypes = /^image\/(jpeg|jpg|png|gif)$/.test(fileType);

    return validTypes;
},);

$.validator.addMethod('fileSize', function(value, element) {
    // If no file is selected, consider it valid
    if (element.files.length === 0) {
        return true;
    }

    // Check if the file size is less than or equal to 1MB
    var validSize = element.files[0].size <= 1048576 && element.files[0].size > 0; // 1MB in bytes

    return validSize;
},);


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

 $.validator.addMethod("regex",function(value, element, regexp) {
         var check = false;
         var re = new RegExp(regexp);
         return this.optional(element) || re.test(value);
     },);

 $.validator.addMethod("phoneIn",function(phoneNumber, element) {
          phoneNumber = phoneNumber.replace(/\s+/g,"");
          return this.optional(element) || phoneNumber.match(/^[6-9]\d{9}$/);
      },
      "Please specify a valid phone number");

$(document).ready(function() {
 $("#actorForm").validate({
     rules: {
         'userName': {
         required: true,
         minlength: 6,
         maxlength: 20,
         regex: "^[a-zA-Z\s ]+$"
         },

         gender:{
            required: true
         },

         'dateOfBirth': {
           required: true,
           date:true,
           minAge: 18,
           maxAge: 60
         },

         'phoneNumber': {
          required: true,
          minlength: 10,
          maxlength: 10,
          phoneIn: true
         },

         'biography': {
         maxlength: 50
         },

         'image':{
         required:false,
         imageFormat: true,
         fileSize: true
         }
     },

     messages: {
         userName: {
         required: "Fill Username!",
         maxlength: "Username contains 20 characters!",
         minlength: "Minimum 6 characters required!",
         regex: "Digits and special chars not allowed here"
         },

         gender: {
         required: "Select your gender!"
         },

         dateOfBirth: {
         required: "Enter the Date of Birth!",
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
         },

         image:{
         imageFormat: "Please select a valid image file (JPEG, JPG, PNG, GIF)",
         fileSize: "Image size should be less than or equal to 1MB"
         },
     },

     errorPlacement: function (error, element) {

         if (element.attr("name") == "gender")
         {
           error.addClass("invalid-feedback");
           error.insertAfter("#gender-error");
         }else {
           error.addClass("invalid-feedback");
           error.insertAfter(element);
         }
     },

    /*highlight: function (element, errorClass, validClass) {                                   // if you want to highlight border then remove comment.
             $(element).addClass("is-invalid").removeClass("is-valid");
           },
       unhighlight: function (element, errorClass, validClass) {
         $(element).removeClass("is-invalid").addClass("is-valid");
       },*/

     submitHandler: function(form) {
         form.submit();
     }
   });
 });