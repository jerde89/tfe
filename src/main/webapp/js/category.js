var categoryDatatable;
let nameAlreadyExist = false;


$(document).ready(function () {

    //Fonction permettant d'afficher le tableau des catégories

    $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
        $.fn.dataTable.tables({visible: true, api: true}).columns.adjust();
    });
    console.log($('#categories_ajax'));
    console.log('#categories_ajax');
    categoryDatatable = $('#categories_ajax').DataTable({
        ajax: {url: '/category/getAll', dataSrc: ""},
        columns: [
            {data: 'name'},
            {data: 'description'},
            {data: 'createdAt', render: DataTable.render.datetime('DD/MM/YYYY HH:mm')},
            {data: 'updateAt', render: DataTable.render.datetime('DD/MM/YYYY HH:mm')},
            {
                data: 'enable', render: function (data, type, row) {
                    if (data == true) {
                        return '<input type="checkbox" disabled checked>';
                    } else {
                        return '<input type="checkbox" disabled>';
                    }
                    return data;
                }
            },
            {data: 'ActionsCategories', render: makeActionCategories},

        ],
        order: [[2, 'desc']],

        "language": languageConfig,

    });

    //Fonction permettant de reformater la date au format "DD/MM/YYYY HH:mm"
    function renderDateTz(data, type, row) {
        if (!data) {
            return '';
        }

        return moment(data).format("DD/MM/YYYY HH:mm");
    }

    //Fonction permettant d'afficher et faire appel à une fonction d'action
    function makeActionCategories(data, type, full, meta) {
        return '<div class="row"><div class="col-4 text-center">\n' +
            '                                         <button onclick="toggleCategoryPopup(' + full.id + ')" data-toggle="tooltip" data-placement="top" title="Modifier" >\n' +
            '                                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" className="bi bi-pencil" viewBox="0 0 16 16">\n' +
            '                                                    <path d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"/>\n' +
            '                                                </svg>' +
            '                                                   \n' +
            '                                         </button>\n' +
            '                                    </div>\n' +
            '    </div> ';
    }

    //permet d'afficher max 10 caractère et puis ....
    function minText10(data, type, full, meta) {
        var strReturn = data.substring(0, 10);
        strReturn += data.length > 9 ? '...' : '';
        return strReturn;
    }

    //permet de rendre un mail en href
    function transformToMail(data) {
        return '<a href="mailto:' + data + '"/>' + data + '</a>';
    }


})
;

//Fonction d'affichage du popup d'ajout ou de modification d'une catégorie
function toggleCategoryPopup(id) {
    // Permet d'effacer les champs erreur à l'ouverture de la popup
    document.getElementById("errorName").innerHTML = "";
    $("#name").removeClass("fieldMistake ");
    document.getElementById("errorDescription").innerHTML = "";
    $("#description").removeClass("fieldMistake ");


    if (!id) {
        $("#formCategory").toggle();
        return;
    }
    var found = false;
    $('#idCategory').val("");
    $('#name').val("");
    $('#description').val("");
    $('#created').val("");
    $('#update').val("");
    $('#enable').prop("checked", true)

    const isNew = id === -1;
    if (isNew) {
        $("#blockCreated").hide();
        $("#blockUpdated").hide();
        showPopup();
        $('#formCategory').dialog('option', 'title', 'Nouvelle catégorie');
        return;
    }
    $("#blockCreated").show();
    $("#blockUpdated").show();

    categoryDatatable.rows().every(function (rowIdx, tableLoop, rowLoop) {
        var c = this.data();
        if (c.id === id) {
            $('#idCategory').val(c.id);
            document.getElementById('name').value = c.name;
            document.getElementById('description').value = c.description;
            document.getElementById('created').innerHTML = moment(c.createdAt).format("DD/MM/YYYY HH:mm");
            if (c.updateAt) {
                document.getElementById('update').innerHTML = moment(c.updateAt).format("DD/MM/YYYY HH:mm");

            }
            $( "#enable" ).prop( "checked", false );
            // si enable est à true, la checkbox est cochée
            if (c.enable) {
                $( "#enable" ).prop( "checked", c.enable );
                // document.getElementById('enable').checked = c.enable;
            }
            // $("#formCategory").toggle();
            showPopup();
            $('#formCategory').dialog('option', 'title', 'Modification catégorie');
            found = true;
            // return;
        }
    });
    if (!found) {
        $("#formCategory").toggle();
    }
}

//Fonction permettant d'enregistrer l'ajout ou la modification d'une catégorie par appel AJAX
function callAjaxModifyCategory() {
    const id = $('#idCategory').val();
    console.log($('#idCategory').val());

    var data = {
        'name': $('#name').val(),
        'description': $('#description').val(),
        'enable': $('#enable').is(':checked'),
    };

    const isNew = id === "";
    var ajax;
    if (isNew) {
        ajax = {
            type: "POST",
            url: pageContextPath + "/category"
        }
    } else {
        ajax = {
            type: "PUT",
            url: pageContextPath + "/category/" + id,
        }
    }

    $.ajax({
        contentType: 'application/json',
        type: ajax.type,
        url: ajax.url,
        data: JSON.stringify(data),
        success: successSaveCategory,
        fail: fail,
        dataType: "json",
        headers: {'X-CSRF-Token': $('#_csrf').val()}

    });
}

//Fonction d'affichage d'un popup de succès lorsqu'une catégorie a été ajoutée ou modifiée
function successSaveCategory() {
    categoryDatatable.ajax.reload();
    $.toast(
        {
            heading: 'Félicitations',
            text: 'Votre catégorie a été enregistrée.',
            showHideTransition: 'slide',
            icon: 'success',
            position: 'top-right',
            stack: false
        }
    );
    $("#formCategory").toggle();
}

//Fonction d'alerte fail
function fail(e) {
    console.log(e);
    alert("fail: " + e);
}

//Fonction de vérification de l'imput "name" de la jsp "category"
function checkNameCategory(isNew) {
    var nameCategory = $("#name").val();

    if (nameCategory === "" || nameCategory == null) {
        document.getElementById("errorName").innerHTML = "Veuillez indiquer au moins un caractère";
        $("#name").addClass("fieldMistake ");
    } else if (nameCategory.length > 50) {
        document.getElementById("errorName").innerHTML = "Maximun 50 Caractères";
        $("#name").addClass("fieldMistake ");
    } else {
        document.getElementById("errorName").innerHTML = "";
        $("#name").removeClass("fieldMistake ");
    }
    if (!isNew) {
        return;
    }
    $.ajax({
        type: "GET",
        url: pageContextPath + "/category/nameIsUnique/" + nameCategory,
        success: function (response) {
            if (response === false) {
                document.getElementById("errorName").innerHTML = "La catégorie existe déja dans notre système";
                $("#name").addClass("fieldMistake ");
                nameAlreadyExist = true;
            } else {
                nameAlreadyExist = false;
            }
        },
        fail: function () {
            $.toast(
                {
                    heading: 'Erreur',
                    text: 'Erreur lors de la vérification de la catégorie ',
                    showHideTransition: 'slide',
                    icon: 'error',
                    position: 'top-right',
                    stack: false
                }
            )
        }
    });
}

//Fonction de vérification de l'imput "description" de la jsp "category"
function checkDescriptionCategory() {
    var descriptionCategory = $("#description").val();

    if (descriptionCategory == "" || descriptionCategory == null) {
        document.getElementById("errorDescription").innerHTML = "Veuillez indiquer au moins un caractère";
        $("#description").addClass("fieldMistake ");
        return false;
    } else if (descriptionCategory.length > 200) {
        document.getElementById("errorDescription").innerHTML = "Maximun 200 Caractères";
        $("#description").addClass("fieldMistake ");
        return false;
    }
    document.getElementById("errorDescription").innerHTML = "";
    $("#description").removeClass("fieldMistake ");
    return true;
}

//Fonction de vérification des imputs "name" & "description" de la jsp "category"
function validateCategoryForm(isNew) {
    let formIsValid = true;
    checkNameCategory(isNew) === false ? formIsValid = false : formIsValid = formIsValid;
    checkDescriptionCategory() === false ? formIsValid = false : formIsValid = formIsValid;
    return formIsValid;
}

//Fonction permettant de faire appel à la fonction callAjaxModifyCategory() si la focntion validateCategoryForm est vérifiée
function saveCategoryForm() {
    const isNew = $("#idCategory").val() == "";
    var isValid = validateCategoryForm(isNew);
    if (isValid) {
        callAjaxModifyCategory();
    }
}

function closePopupCategory() {
    $("#formCategory").toggle();
}

//Fonction permettant d'afficher la popup d'ajout ou de modification d'une catégorie
function showPopup(){
    $("#formCategory").dialog({
        modal: true,
        minWidth: 600,
        minHeight: 300,
        open: function() {
            $(this).closest(".ui-dialog")
                .find(".ui-dialog-titlebar-close")
                .removeClass("ui-dialog-titlebar-close")
                .html("<span class='ui-button-icon-primary ui-icon ui-icon-closethick'></span>");
        },
        buttons: [
            {
                text: "Fermer",
                icon: "ui-icon-heart",
                click: function () {
                    $(this).dialog("close");
                }
            },
            {
                text: "Enregistrer",
                icon: "ui-icon-heart",
                click: function () {
                    saveCategoryForm();
                    $(this).dialog("close");
                }
            },
        ]
    });
}
