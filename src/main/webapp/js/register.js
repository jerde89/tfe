// Fonction permettant d'afficher le calendrier dans un input date (exemple date de naissance)
$(function () {
    $("#datepicker").datepicker({
        changeYear: true,
        changeMonth: true,
        yearRange: "1930:2023",
        dateFormat: "dd/mm/yy",
    });
});



// Fonction permettant d'afficher ou non le mot de passe => oeil
$(document).ready(function() {
    $("#show_hide_password a").on('click', function(event) {
        event.preventDefault();
        if($('#show_hide_password input').attr("type") == "text"){
            $('#show_hide_password input').attr('type', 'password');
            $('#show_hide_password i').addClass( "fa-eye-slash" );
            $('#show_hide_password i').removeClass( "fa-eye" );
        }else if($('#show_hide_password input').attr("type") == "password"){
            $('#show_hide_password input').attr('type', 'text');
            $('#show_hide_password i').removeClass( "fa-eye-slash" );
            $('#show_hide_password i').addClass( "fa-eye" );
        }
    });
    $("#country_selector").countrySelect({
        defaultCountry:"be",
        preferredCountries: ['be','fr','lu','nl','de'],
    });

});

// Fonction de vérification des différents champs sur le Onsubmit du formulaire registerForm (register.jsp)
function validateRegisterForm() {
    return true;

    let emailUser = document.forms["registerForm"]["email"];
    var validRegex = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
    let password = document.forms["registerForm"]["passwordNoConfirm"];
    let nameUser = document.forms["registerForm"]["lastname"];
    let firstnameUser = document.forms["registerForm"]["firstname"];
    let phoneUser = document.forms["registerForm"]["phone"];
    let street = document.forms["registerForm"]["Address.street"];
    let number = document.forms["registerForm"]["Address.number"];
    let PostalCode = document.forms["registerForm"]["Address.City.postalCode"];
    let cityName = document.forms["registerForm"]["Address.City.cityName"];
    let flag = true;

    if (emailUser.value == "" || emailUser.value == null) {
        document.getElementById("errorEmailUser").innerHTML = "Veuillez indiquer au moins un caractère";
        $("#inputEmail").addClass("fieldMistake ");
        flag = false;

    } else {
        document.getElementById("errorEmailUser").innerHTML = "";
        emailUser.removeAttribute('style');
        $("#inputEmail").removeClass("fieldMistake ");
    }

    if (password.value == "" || password.value == null) {
        document.getElementById("errorPassword").innerHTML = "Veuillez indiquer au moins un caractère";
        $("#inputPassword").addClass("fieldMistake ");
        flag = false;

    } else {
        document.getElementById("errorPassword").innerHTML = "";
        password.removeAttribute('style');
        $("#inputPassword").removeClass("fieldMistake ");

    }

    if (nameUser.value == "" || nameUser.value == null) {
        document.getElementById("errorNameUser").innerHTML = "Veuillez indiquer au moins un caractère";
        $("#inputName").addClass("fieldMistake ");
        flag = false;

    }  else {
        document.getElementById("errorNameUser").innerHTML = "";
        nameUser.removeAttribute('style');
        $("#inputName").removeClass("fieldMistake ");
    }

    if (firstnameUser.value == "" || firstnameUser.value == null) {
        document.getElementById("errorFirstnameUser").innerHTML = "Veuillez indiquer au moins un caractère";
        $("#inputFirstname").addClass("fieldMistake ");
        flag = false;
    } else {
        document.getElementById("errorFirstnameUser").innerHTML = "";
        firstnameUser.removeAttribute('style');
        $("#inputFirstname").removeClass("fieldMistake ");
    }

    if (phoneUser.value == "" || phoneUser.value == null) {
        document.getElementById("errorPhoneUser").innerHTML = "Veuillez indiquer au moins un numéro";
        $("#inputPhone").addClass("fieldMistake ");
        flag = false;

    }  else {
        document.getElementById("errorPhoneUser").innerHTML = "";
        phoneUser.removeAttribute('style');
        $("#inputPhone").removeClass("fieldMistake ");
    }

    if (street.value == "" || street.value == null) {
        document.getElementById("errorStreet").innerHTML = "Veuillez indiquer au moins un caractère";
        $("#inputStreet").addClass("fieldMistake ");
        flag = false;

    } else {
        document.getElementById("errorStreet").innerHTML = "";
        street.removeAttribute('style');
        $("#inputStreet").removeClass("fieldMistake ");
    }

    if (number.value == "" || number.value == null) {
        document.getElementById("errorNumber").innerHTML = "Veuillez indiquer au moins un caractère";
        $("#inputNumber").addClass("fieldMistake ");
        flag = false;
    }  else {
        document.getElementById("errorNumber").innerHTML = "";
        number.removeAttribute('style');
        $("#inputNumber").removeClass("fieldMistake ");
    }

    if (PostalCode.value == "" || PostalCode.value == null) {
        document.getElementById("errorPostalCode").innerHTML = "Veuillez indiquer au moins un numéro";
        $("#inputPostalCode").addClass("fieldMistake ");
        flag = false;
    }  else {
        document.getElementById("errorPostalCode").innerHTML = "";
        PostalCode.removeAttribute('style');
        $("#inputPostalCode").removeClass("fieldMistake ");
    }

    if (cityName.value == "" || cityName.value == null) {
        document.getElementById("errorCityName").innerHTML = "Veuillez indiquer au moins un caractère";
        $("#inputCityName").addClass("fieldMistake ");
        flag = false;
    } else {
        document.getElementById("errorCityName").innerHTML = "";
        cityName.removeAttribute('style');
        $("#inputCityName").removeClass("fieldMistake ");
    }
    return flag;
}

function checkEmailUser() {

    var emailUser = $("#inputEmail").val();

    if (emailUser == "" || emailUser == null) {
        document.getElementById("errorEmailUser").innerHTML = "Veuillez indiquer au moins un caractère";
        $("#inputEmail").addClass("fieldMistake ");

    } else if (emailUser.length > 100) {
        document.getElementById("errorEmailUser").innerHTML = "Maximun 100 Caractères";
        $("#inputEmail").addClass("fieldMistake ");

    } else {
        document.getElementById("errorEmailUser").innerHTML = "";
        $("#inputEmail").removeClass("fieldMistake ");
    }

}


function checkPassword() {

    var password = $("#inputPassword").val();

    if (password.length < 6) {
        document.getElementById("errorPassword").innerHTML = "Veuillez indiquer au moins 6 caractères";
        $("#inputPassword").addClass("fieldMistake ");

    } else if (password.length > 50) {
        document.getElementById("errorPassword").innerHTML = "Maximun 50 Caractères";
        $("#inputPassword").addClass("fieldMistake ");

    } else {
        document.getElementById("errorPassword").innerHTML = "";
        $("#inputPassword").removeClass("fieldMistake ");
    }

}

function checkConfirmPassword() {

    var confirmPassword = $("#inputConfirmPassword").val();
    var password = $("#inputPassword").val();

    if (confirmPassword != password) {
        document.getElementById("errorConfirmPassword").innerHTML = "Les mots de passe ne sont pas similaires";
        $("#inputConfirmPassword").addClass("fieldMistake ");

    } else {
        document.getElementById("errorConfirmPassword").innerHTML = "";
        $("#inputConfirmPassword").removeClass("fieldMistake ");
    }
}

function checkNameUser() {

    var nameUser = $("#inputName").val();

    if (nameUser == "" || nameUser == null) {
        document.getElementById("errorNameUser").innerHTML = "Veuillez indiquer au moins un caractère";
        $("#inputName").addClass("fieldMistake ");

    } else if (nameUser.length > 50) {
        document.getElementById("errorNameUser").innerHTML = "Maximun 50 Caractères";
        $("#inputName").addClass("fieldMistake ");

    } else {
        document.getElementById("errorNameUser").innerHTML = "";
        $("#inputName").removeClass("fieldMistake ");
    }
}

function checkFirstnameUser() {

    var firstnameUser = $("#inputFirstname").val();

    if (firstnameUser == "" || firstnameUser == null) {
        document.getElementById("errorFirstnameUser").innerHTML = "Veuillez indiquer au moins un caractère";
        $("#inputFirstname").addClass("fieldMistake ");

    } else if (firstnameUser.length > 50) {
        document.getElementById("errorFirstnameUser").innerHTML = "Maximun 50 Caractères";
        $("#inputFirstname").addClass("fieldMistake ");

    } else {
        document.getElementById("errorFirstnameUser").innerHTML = "";
        $("#inputFirstname").removeClass("fieldMistake ");
    }
}

function checkPhoneUser() {

    var phoneUser = $("#inputPhone").val();

    if (phoneUser == "" || phoneUser == null) {
        document.getElementById("errorPhoneUser").innerHTML = "Veuillez indiquer au moins un caractère";
        $("#inputPhone").addClass("fieldMistake ");

    } else if (phoneUser.length > 50) {
        document.getElementById("errorPhoneUser").innerHTML = "Maximun 50 Caractères";
        $("#inputPhone").addClass("fieldMistake ");

    } else {
        document.getElementById("errorPhoneUser").innerHTML = "";
        $("#inputPhone").removeClass("fieldMistake ");
    }
}

function checkStreet() {

    var street = $("#inputStreet").val();

    if (street == "" || street == null) {
        document.getElementById("errorStreet").innerHTML = "Veuillez indiquer au moins un caractère";
        $("#inputStreet").addClass("fieldMistake ");

    } else if (street.length > 100) {
        document.getElementById("errorStreet").innerHTML = "Maximun 100 Caractères";
        $("#inputStreet").addClass("fieldMistake ");

    } else {
        document.getElementById("errorStreet").innerHTML = "";
        $("#inputStreet").removeClass("fieldMistake ");
    }
}

function checkNumber() {

    var number = $("#inputNumber").val();

    if (number == "" || number == null) {
        document.getElementById("errorNumber").innerHTML = "Veuillez indiquer au moins un caractère";
        $("#inputNumber").addClass("fieldMistake ");

    } else if (number.length > 10) {
        document.getElementById("errorNumber").innerHTML = "Maximun 10 Caractères";
        $("#inputNumber").addClass("fieldMistake ");

    } else {
        document.getElementById("errorNumber").innerHTML = "";
        $("#inputNumber").removeClass("fieldMistake ");
    }
}

function checkPostalCode() {

    var postalCode = $("#inputPostalCode").val();

    if (postalCode == "" || postalCode == null) {
        document.getElementById("errorPostalCode").innerHTML = "Veuillez indiquer au moins un caractère";
        $("#inputPostalCode").addClass("fieldMistake ");

    } else if (postalCode.length > 10) {
        document.getElementById("errorPostalCode").innerHTML = "Maximun 10 Caractères";
        $("#inputPostalCode").addClass("fieldMistake ");

    } else {
        document.getElementById("errorPostalCode").innerHTML = "";
        $("#inputPostalCode").removeClass("fieldMistake ");
    }
}

function checkCity() {

    var city = $("#inputCityName").val();

    if (city == "" || city == null) {
        document.getElementById("errorCityName").innerHTML = "Veuillez indiquer au moins un caractère";
        $("#inputCityName").addClass("fieldMistake ");

    } else if (city.length > 100) {
        document.getElementById("errorCityName").innerHTML = "Maximun 100 Caractères";
        $("#inputCityName").addClass("fieldMistake ");

    } else {
        document.getElementById("errorCityName").innerHTML = "";
        $("#inputCityName").removeClass("fieldMistake ");
    }
}

