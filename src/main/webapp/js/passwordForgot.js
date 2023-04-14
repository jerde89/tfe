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
    $.ajax({
        type: "GET",
        url: pageContextPath + "/passwordForgot/checkIfEmailExist/" + emailUser,
        success: function (response) {
            if (response === false) {
                document.getElementById("errorEmailUser").innerHTML = "L'email n'existe pas dans notre système";
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
