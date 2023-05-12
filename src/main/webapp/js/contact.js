// function checkName() {
//     var nameFeedback = document.forms["contactFeedback"]["nameFeedback"];
//
//     if (nameFeedback.value == "" || nameFeedback.value == null) {
//         document.getElementById("errorNameFeedback").innerHTML = "Veuillez indiquer un caractère";
//         $("#InputName").addClass("fieldMistake ");
//         return false;
//     } else if(nameFeedback.value.length >50) {
//         document.getElementById("errorNameFeedback").innerHTML = "Maximun 50 Caractères";
//         $("#InputName").addClass("fieldMistake ");
//         return false;
//     } else {
//         document.getElementById("errorNameFeedback").innerHTML = "";
//         nameFeedback.removeAttribute('style');
//         $("#InputName").removeClass("fieldMistake ");
//     }
//
// }
//
// function checkFirstname() {
//
//     var firstnameFeedback = document.forms["contactFeedback"]["firstnameFeedback"];
//
//     if (firstnameFeedback.value == "" || firstnameFeedback.value == null) {
//         document.getElementById("errorFirstnameFeedback").innerHTML = "Veuillez indiquer un caractère";
//         $("#InputFirstname").addClass("fieldMistake ");
//         return false;
//     } else if(firstnameFeedback.value.length >50) {
//         document.getElementById("errorFirstnameFeedback").innerHTML = "Maximun 50 Caractères";
//         $("#InputFirstname").addClass("fieldMistake ");
//         return false;
//
//     } else {
//         document.getElementById("errorFirstnameFeedback").innerHTML = "";
//         firstnameFeedback.removeAttribute('style');
//         $("#InputFirstname").removeClass("fieldMistake ");
//     }
// }
//
// function checkEmail() {
//
//     var email = document.forms["contactFeedback"]["emailFeedback"];
//     var validRegex = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
//
//     if (email.value == "" || email.value == null) {
//         document.getElementById("errorEmailFeedback").innerHTML = "Veuillez indiquer un caractère";
//         $("#InputEmail").addClass("fieldMistake ");
//         return false;
//
//     } else if(!email.value.match(validRegex) ) {
//         document.getElementById("errorEmailFeedback").innerHTML = "Veuillez indiquer une adresse valide";
//         $("#InputEmail").addClass("fieldMistake ");
//         return false;
//
//     } else {
//         document.getElementById("errorEmailFeedback").innerHTML = "";
//         email.removeAttribute('style');
//         $("#InputEmail").removeClass("fieldMistake ");
//     }
//
// }
//
//
// function checkPhone() {
//
//     var phone = document.forms["contactFeedback"]["phoneFeedback"];
//
//     if (phone.value == "" || phone.value == null) {
//         document.getElementById("errorPhoneFeedback").innerHTML = "Veuillez indiquer au moins un caractère";
//         $("#InputPhone").addClass("fieldMistake ");
//         return false;
//
//     } else if (phone.value.length > 50) {
//         document.getElementById("errorPhoneFeedback").innerHTML = "Maximun 15 Caractères";
//         $("#InputPhone").addClass("fieldMistake ");
//         return false;
//
//     } else {
//         document.getElementById("errorPhoneFeedback").innerHTML = "";
//         phone.removeAttribute('style');
//         $("#InputPhone").removeClass("fieldMistake ");
//     }
// }
//
// function checkComment() {
//
//     var comment = document.forms["contactFeedback"]["commentFeedback"];
//
//     if (comment.value == "" || comment.value == null) {
//         document.getElementById("errorCommentFeedback").innerHTML = "Veuillez indiquer un caractère";
//         $("#InputCommentFeedback").addClass("fieldMistake ");
//         return false;
//     } else if(comment.value.length >500) {
//         document.getElementById("errorCommentFeedback").innerHTML = "Maximun 500 Caractères";
//         $("#InputCommentFeedback").addClass("fieldMistake ");
//         return false;
//
//     } else {
//         document.getElementById("errorCommentFeedback").innerHTML = "";
//         comment.removeAttribute('style');
//         $("#InputCommentFeedback").removeClass("fieldMistake ");
//     }
// }
//
//
// function validateContactFeedback() {
//     try {
//         let formIsValid = true;
//
//         checkName() === false ? formIsValid = false : formIsValid = formIsValid;
//         checkFirstname() === false ? formIsValid = false : formIsValid = formIsValid;
//         checkEmail() === false ? formIsValid = false : formIsValid = formIsValid;
//         checkPhone() === false ? formIsValid = false : formIsValid = formIsValid;
//         checkComment() === false ? formIsValid = false : formIsValid = formIsValid;
//
//         return formIsValid;
//     }catch (e) {
//         console.error(e);
//         return false;
//     }
// }
