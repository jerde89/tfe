//Fonction de vérification de l'imput "inputOldPassword" de la jsp "changePassword"
function checkPasswordOld() {

    var passwordOld = $("#inputOldPassword").val();
    var passwordEncoded

    if (passwordOld == "" || passwordOld == null) {
        document.getElementById("errorPasswordOld").innerHTML = "Veuillez indiquer au moins un caractère";
        $("#inputOldPassword").addClass("fieldMistake ");
    } else if (passwordOld.length > 50) {
        document.getElementById("errorPasswordOld").innerHTML = "Maximun 50 Caractères";
        $("#inputOldPassword").addClass("fieldMistake ");
    } else {
        document.getElementById("errorPasswordOld").innerHTML = "";
        $("#inputOldPassword").removeClass("fieldMistake ");
    }
    $.ajax({
        type: "GET",
        url: pageContextPath + "/changePassword/passwordOldCorrect/" + passwordOld,
        success: function (response) {
            if (response === false) {
                document.getElementById("errorPasswordOld").innerHTML = "Le mot de passe encodé n'est pas correct";
                $("#inputOldPassword").addClass("fieldMistake ");
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
                    text: 'Erreur lors de la vérification de votre ancien mot de passe ',
                    showHideTransition: 'slide',
                    icon: 'error',
                    position: 'top-right',
                    stack: false
                }
            )
        }
    });
}