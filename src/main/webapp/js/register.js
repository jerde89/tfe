let emailAlreadyExist = false;

// Fonction permettant d'afficher le calendrier dans un input date (exemple date de naissance)
$(function () {
    $("#datepicker").datepicker({
        changeYear: true,
        changeMonth: true,
        yearRange: "1930:2023",
        dateFormat: "dd/mm/yy",
    });
});


$(document).ready(function () {
    //Permet d'afficher ou non le mot de passe  => oeil
    $("#show_hide_password a").on('click', function (event) {
        event.preventDefault();
        if ($('#show_hide_password input').attr("type") == "text") {
            $('#show_hide_password input').attr('type', 'password');
            $('#show_hide_password i').addClass("fa-eye-slash");
            $('#show_hide_password i').removeClass("fa-eye");
        } else if ($('#show_hide_password input').attr("type") == "password") {
            $('#show_hide_password input').attr('type', 'text');
            $('#show_hide_password i').removeClass("fa-eye-slash");
            $('#show_hide_password i').addClass("fa-eye");
        }
    });
    $("#show_hide_password_confirm a").on('click', function (event) {
        event.preventDefault();
        if ($('#show_hide_password_confirm input').attr("type") == "text") {
            $('#show_hide_password_confirm input').attr('type', 'password');
            $('#show_hide_password_confirm i').addClass("fa-eye-slash");
            $('#show_hide_password_confirm i').removeClass("fa-eye");
        } else if ($('#show_hide_password_confirm input').attr("type") == "password") {
            $('#show_hide_password_confirm input').attr('type', 'text');
            $('#show_hide_password_confirm i').removeClass("fa-eye-slash");
            $('#show_hide_password_confirm i').addClass("fa-eye");
        }
    });
    $("#country_selector").countrySelect({
        defaultCountry: "be",
        preferredCountries: ['be', 'fr', 'lu', 'nl', 'de'],
    });

});

function checkEmailUser() {

    var emailUser = $("#inputEmail").val();
    if (emailUser == "" || emailUser == null) {
        document.getElementById("errorEmailUser").innerHTML = "Veuillez indiquer au moins un caractère";
        $("#inputEmail").addClass("fieldMistake ");
        return false;
    } else if (emailUser.length > 100) {
        document.getElementById("errorEmailUser").innerHTML = "Maximun 100 Caractères";
        $("#inputEmail").addClass("fieldMistake ");
        return false;
    } else {
        document.getElementById("errorEmailUser").innerHTML = "";
        $("#inputEmail").removeClass("fieldMistake ");
    }
    $.ajax({
        type: "GET",
        url: pageContextPath + "/register/emailIsUnique/" + emailUser,
        success: function (response) {
            if (response === false) {
                document.getElementById("errorEmailUser").innerHTML = "L'email existe déja dans notre système";
                $("#inputEmail").addClass("fieldMistake ");
                emailAlreadyExist = true;
            } else {
                emailAlreadyExist = false;
            }
        },
        fail: function () {
            console.log("fail");
            $.toast(
                {
                    heading: 'Erreur',
                    text: 'Erreur lors de la vérification de l\'email ',
                    showHideTransition: 'slide',
                    icon: 'error',
                    position: 'top-right',
                    stack: false
                }
            )
        }
    });
}


function checkPassword() {

    var password = $("#inputPassword").val();

    if (password.length < 6) {
        document.getElementById("errorPassword").innerHTML = "Veuillez indiquer au moins 6 caractères";
        $("#inputPassword").addClass("fieldMistake ");
        return false;

    } else if (password.length > 50) {
        document.getElementById("errorPassword").innerHTML = "Maximun 50 Caractères";
        $("#inputPassword").addClass("fieldMistake ");
        return false;

    } else {
        document.getElementById("errorPassword").innerHTML = "";
        $("#inputPassword").removeClass("fieldMistake ");
    }
}

function checkConfirmPassword() {

    var confirmPassword = $("#inputConfirmPassword").val();
    var password = $("#inputPassword").val();

    if (confirmPassword !== password) {
        document.getElementById("errorConfirmPassword").innerHTML = "Les mots de passe ne sont pas similaires";
        $("#inputConfirmPassword").addClass("fieldMistake ");
        return false;

    } else {
        document.getElementById("errorConfirmPassword").innerHTML = "";
        $("#inputConfirmPassword").removeClass("fieldMistake ");
    }
}

function checkNameUser() {
    var nameUser = $("#inputName").val();
    if (nameUser === "" || nameUser == null) {
        document.getElementById("errorNameUser").innerHTML = "Veuillez indiquer au moins un caractère";
        $("#inputName").addClass("fieldMistake ");
        return false;
    } else if (nameUser.length > 50) {
        document.getElementById("errorNameUser").innerHTML = "Maximun 50 Caractères";
        $("#inputName").addClass("fieldMistake ");
        return false;
    }
    document.getElementById("errorNameUser").innerHTML = "";
    $("#inputName").removeClass("fieldMistake ");
    return true;
}

function checkFirstnameUser() {
    var firstnameUser = $("#inputFirstname").val();
    if (firstnameUser == "" || firstnameUser == null) {
        document.getElementById("errorFirstnameUser").innerHTML = "Veuillez indiquer au moins un caractère";
        $("#inputFirstname").addClass("fieldMistake ");
        return false;
    } else if (firstnameUser.length > 50) {
        document.getElementById("errorFirstnameUser").innerHTML = "Maximun 50 Caractères";
        $("#inputFirstname").addClass("fieldMistake ");
        return false;
    }
    document.getElementById("errorFirstnameUser").innerHTML = "";
    $("#inputFirstname").removeClass("fieldMistake ");
    return true;
}

function checkPhoneUser() {

    var phoneUser = $("#inputPhone").val();

    if (phoneUser == "" || phoneUser == null) {
        document.getElementById("errorPhoneUser").innerHTML = "Veuillez indiquer au moins un caractère";
        $("#inputPhone").addClass("fieldMistake ");
        return false;

    } else if (phoneUser.length > 50) {
        document.getElementById("errorPhoneUser").innerHTML = "Maximun 50 Caractères";
        $("#inputPhone").addClass("fieldMistake ");
        return false;

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
        return false;

    } else if (street.length > 100) {
        document.getElementById("errorStreet").innerHTML = "Maximun 100 Caractères";
        $("#inputStreet").addClass("fieldMistake ");
        return false;

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
        return false;
    } else if (number.length > 10) {
        document.getElementById("errorNumber").innerHTML = "Maximun 10 Caractères";
        $("#inputNumber").addClass("fieldMistake ");
        return false;

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
        return false;
    } else if (postalCode.length > 10) {
        document.getElementById("errorPostalCode").innerHTML = "Maximun 10 Caractères";
        $("#inputPostalCode").addClass("fieldMistake ");
        return false;
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
        return false;

    } else if (city.length > 100) {
        document.getElementById("errorCityName").innerHTML = "Maximun 100 Caractères";
        $("#inputCityName").addClass("fieldMistake ");
        return false;

    } else {
        document.getElementById("errorCityName").innerHTML = "";
        $("#inputCityName").removeClass("fieldMistake ");
    }
}

// Fonction de vérification des différents champs sur le Onsubmit du formulaire registerForm (register.jsp)
function validateRegisterForm(origin) {
    try {
        //Par défaut, on met que le formulaire est valide (true)
        let formIsValid = true;

        //checkEmailUser() => on appelle la fonction checkEmailUser
        //checkEmailUser() === false => SI cette fonction retourne false (conditions ne sont pas réspectées (exemple : champ vide))
        //? formIsValid = false => ALORS passe formIsValid à false
        //: formIsValid =formIsValid => SINON (pas utilisé dans ce cas)

        //Revient au même que de faire
        // if(!checkFirstnameUser()){
        //     formIsValid = false;
        // }
        checkEmailUser() === false ? formIsValid = false : formIsValid = formIsValid;
        if (origin == "register") {
            checkPassword() === false ? formIsValid = false : formIsValid = formIsValid;
        }

        checkNameUser() === false ? formIsValid = false : formIsValid = formIsValid;
        checkFirstnameUser() === false ? formIsValid = false : formIsValid = formIsValid;
        checkPhoneUser() === false ? formIsValid = false : formIsValid = formIsValid;
        checkStreet() === false ? formIsValid = false : formIsValid = formIsValid;
        checkNumber() === false ? formIsValid = false : formIsValid = formIsValid;
        // checkPostalCode() === false ? formIsValid = false : formIsValid=formIsValid;
        //checkCity() ===false ? formIsValid = false : formIsValid=formIsValid;

        //retourne la valeur de formIsValid (true ou false)
        return formIsValid;
    } catch (e) {
        console.error(e);
        return false;
    }
}

