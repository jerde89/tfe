

function validateContactFeedback() {

    let result = true;

    let nameFeedback = document.forms["contactFeedback"]["nameFeedback"];



    if (nameFeedback.value == "" || nameFeedback.value == null) {
        document.getElementById("errorNameFeedback").innerHTML = "Veuillez indiquer un caractère";
        // Permet d'afficher la bordure de l'input en rouge (voir css)
        // #exampleInputNom => nom de l'input dans la jsp
        // fieldMistake => nom du css
        $("#exampleInputNom").addClass("fieldMistake ");

        result = false;
    } else if(nameFeedback.value.length <3) {
        document.getElementById("errorNameFeedback").innerHTML = "trop court fieu";
        $("#exampleInputNom").addClass("fieldMistake ");

        result = false;

    } else {
        document.getElementById("errorNameFeedback").innerHTML = "";
        nameFeedback.removeAttribute('style');
        $("#exampleInputNom").removeClass("fieldMistake ");
    }

    return result;
}
