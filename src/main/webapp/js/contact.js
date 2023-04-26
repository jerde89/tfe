function checkName() {
    var name = $("#InputName").val();
    if (name == "" || name == null) {
        document.getElementById("errorNameFeedback").innerHTML = "Veuillez indiquer au moins un caractère";
        $("#InputName").addClass("fieldMistake ");
        return false;
    } else if (name.length > 50) {
        document.getElementById("errorNameFeedback").innerHTML = "Maximun 50 Caractères";
        $("#inputName").addClass("fieldMistake ");
        return false;
    }
    document.getElementById("errorNameFeedback").innerHTML = "";
    $("#inputName").removeClass("fieldMistake ");
    return true;
}

function checkFirstname() {
    var firstname = $("#InputFirstname").val();
    if (firstname == "" || firstname == null) {
        document.getElementById("errorFirstnameFeedback").innerHTML = "Veuillez indiquer au moins un caractère";
        $("#InputFirstname").addClass("fieldMistake ");
        return false;
    } else if (firstname.length > 50) {
        document.getElementById("errorFirstnameFeedback").innerHTML = "Maximun 50 Caractères";
        $("#InputFirstname").addClass("fieldMistake ");
        return false;
    }
    document.getElementById("errorFirstnameFeedback").innerHTML = "";
    $("#inputFirstname").removeClass("fieldMistake ");
    return true;
}

function checkEmail() {

    var email = $("#InputEmail").val();
    var validRegex = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;

    if (email.value == "" || email.value == null) {
        document.getElementById("errorEmailFeedback").innerHTML = "Veuillez indiquer un caractère";
        $("#InputEmail").addClass("fieldMistake ");
        result = false;

    } else if(!email.value.match(validRegex) ) {
        document.getElementById("errorEmailFeedback").innerHTML = "Veuillez indiquer une adresse valide";
        $("#InputEmail").addClass("fieldMistake ");
        result = false;

    } else {
        document.getElementById("errorEmailFeedback").innerHTML = "";
        $("#InputEmail").removeClass("fieldMistake ");
    }


}


function validateContactFeedback() {

    let result = true;

    let nameFeedback = document.forms["contactFeedback"]["nameFeedback"];
    let firstnameFeedback = document.forms["contactFeedback"]["firstnameFeedback"];
    let emailFeedback = document.forms["contactFeedback"]["emailFeedback"];
    var validRegex = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
    let phoneFeedback = document.forms["contactFeedback"]["phoneFeedback"];
    let CommentFeedback = document.forms["contactFeedback"]["commentFeedback"];




    if (nameFeedback.value == "" || nameFeedback.value == null) {
        document.getElementById("errorNameFeedback").innerHTML = "Veuillez indiquer un caractère";
        // Permet d'afficher la bordure de l'input en rouge (voir css)
        // #InputName => nom de l'input dans la jsp
        // fieldMistake => nom du css
        $("#InputName").addClass("fieldMistake ");

        result = false;
    } else if(nameFeedback.value.length >50) {
        document.getElementById("errorNameFeedback").innerHTML = "Maximun 50 Caractères";
        $("#InputName").addClass("fieldMistake ");

        result = false;

    } else {
        document.getElementById("errorNameFeedback").innerHTML = "";
        nameFeedback.removeAttribute('style');
        $("#InputName").removeClass("fieldMistake ");
    }

    if (firstnameFeedback.value == "" || firstnameFeedback.value == null) {
        document.getElementById("errorFirstnameFeedback").innerHTML = "Veuillez indiquer un caractère";
        $("#InputFirstname").addClass("fieldMistake ");

        result = false;
    } else if(firstnameFeedback.value.length >50) {
        document.getElementById("errorFirstnameFeedback").innerHTML = "Maximun 50 Caractères";
        $("#InputFirstname").addClass("fieldMistake ");

        result = false;

    } else {
        document.getElementById("errorFirstnameFeedback").innerHTML = "";
        firstnameFeedback.removeAttribute('style');
        $("#InputFirstname").removeClass("fieldMistake ");
    }


    if (emailFeedback.value == "" || emailFeedback.value == null) {
        document.getElementById("errorEmailFeedback").innerHTML = "Veuillez indiquer un caractère";
        $("#InputEmail").addClass("fieldMistake ");
        result = false;

    } else if(!emailFeedback.value.match(validRegex) ) {
        document.getElementById("errorEmailFeedback").innerHTML = "Veuillez indiquer une adresse valide";
        $("#InputEmail").addClass("fieldMistake ");
        result = false;

    } else {
        document.getElementById("errorEmailFeedback").innerHTML = "";
        emailFeedback.removeAttribute('style');
        $("#InputEmail").removeClass("fieldMistake ");
    }

    if (phoneFeedback.value == "" || phoneFeedback.value == null) {
        document.getElementById("errorPhoneFeedback").innerHTML = "Veuillez indiquer un caractère";
        $("#InputPhone").addClass("fieldMistake ");
        result = false;
    } else if(phoneFeedback.value.length >15) {
        document.getElementById("errorPhoneFeedback").innerHTML = "Maximun 15 Caractères";
        $("#InputPhone").addClass("fieldMistake ");
        result = false;

    } else {
        document.getElementById("errorPhoneFeedback").innerHTML = "";
        phoneFeedback.removeAttribute('style');
        $("#InputPhone").removeClass("fieldMistake ");
    }

    if (CommentFeedback.value == "" || CommentFeedback.value == null) {
        document.getElementById("errorCommentFeedback").innerHTML = "Veuillez indiquer un caractère";
        $("#InputCommentFeedback").addClass("fieldMistake ");
        result = false;
    } else if(CommentFeedback.value.length >500) {
        document.getElementById("errorCommentFeedback").innerHTML = "Maximun 500 Caractères";
        $("#InputCommentFeedback").addClass("fieldMistake ");
        result = false;

    } else {
        document.getElementById("errorCommentFeedback").innerHTML = "";
        CommentFeedback.removeAttribute('style');
        $("#InputCommentFeedback").removeClass("fieldMistake ");
    }


    return result;
}
