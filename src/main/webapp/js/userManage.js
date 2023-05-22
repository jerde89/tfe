var userManageDatatable;
var userManageAjax;

$(document).ready(function () {
    $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
        $.fn.dataTable.tables({visible: true, api: true}).columns.adjust();
    });
    userManageDatatable = $('#user_Manage_ajax').DataTable({
        ajax: {url: '/userManage/userList', dataSrc: ""},
        columns: [
            {data: 'lastname'},
            {data: 'firstname'},
            {data: 'email', render: transformToMail10},
            {data: 'phone'},
            {data: 'address.street', render: minText15},
            {data: 'address.number'},
            {data: 'address.box'},
            {data: 'address.city.cityName'},
            {data: 'makeActionUser', render: makeActionUser},

        ],
        order: [[1, 'asc']],

        "language": languageConfig
    });

    function minText10(data, type, full, meta) {
        var strReturn = data.substring(0, 10);
        strReturn += data.length > 9 ? '...' : '';
        return strReturn;
    }

    function minText15(data, type, full, meta) {
        var strReturn = data.substring(0, 15);
        strReturn += data.length > 14 ? '...' : '';
        return strReturn;
    }

    function transformToMail(data) {
        return '<a href="mailto:' + data + '"/>' + data + '</a>';
    }

    function transformToMail10(data) {
        var strReturn = data.substring(0, 10);
        strReturn += data.length > 9 ? '...' : '';
        return '<a href="mailto:' + strReturn + '"/>' + strReturn + '</a>';
    }

    function makeActionUser(data, type, full, meta) {
        console.log(data);
        console.log(type);
        console.log(full);
        return '<div class="row"><div class="col-4 text-center">\n' +
            '<button onclick="toggleUserPopup(' + full.idUser + ')" data-toggle="tooltip" data-placement="top" title="Plus d informations" >\n' +
            '      <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-eye" viewBox="0 0 16 16">\n' +
            '           <path d="M16 8s-3-5.5-8-5.5S0 8 0 8s3 5.5 8 5.5S16 8 16 8zM1.173 8a13.133 13.133 0 0 1 1.66-2.043C4.12 4.668 5.88 3.5 8 3.5c2.12 0 3.879 1.168 5.168 2.457A13.133 13.133 0 0 1 14.828 8c-.058.087-.122.183-.195.288-.335.48-.83 1.12-1.465 1.755C11.879 11.332 10.119 12.5 8 12.5c-2.12 0-3.879-1.168-5.168-2.457A13.134 13.134 0 0 1 1.172 8z"/>\n' +
            '           <path d="M8 5.5a2.5 2.5 0 1 0 0 5 2.5 2.5 0 0 0 0-5zM4.5 8a3.5 3.5 0 1 1 7 0 3.5 3.5 0 0 1-7 0z"/>\n' +
            '      </svg>' +
            '   </button>\n' +
            '</div>\n'
    }


});


function toggleUserPopup(id) {
    if (!id) {
        $("#formUserInfo").toggle();
        return;
    }
    var found = false;
    $('#lastname').val("");
    $('#firstname').val("");

    userManageDatatable.rows().every(function (rowIdx, tableLoop, rowLoop) {
        var c = this.data();
        if (c.idUser === id) {
            $('#idUser').val(c.idUser);
            document.getElementById('lastname').value = c.lastname;
            document.getElementById('firstname').value = c.firstname;
            document.getElementById('email').value = c.email;
            document.getElementById('phone').value = c.phone;
            document.getElementById('street').value = c.address.street;
            document.getElementById('number').value = c.address.number;
            document.getElementById('box').value = c.address.box;
            document.getElementById('idCity').value = c.address.city.idCity;
            document.getElementById('countryName').value = c.address.city.country.countryName;
            document.getElementById('role').value = c.role.idRole;
            $('#enabled').prop('checked', c.enabled);

            //permet de mettre les champs en lecture seul pour tous les acteurs qui ne sont pas admin
            if (!isAdmin) {
                $('#lastname').prop('disabled', true);
                $('#firstname').prop('disabled', true);
                $('#email').prop('disabled', true);
                $('#phone').prop('disabled', true);
                $('#street').prop('disabled', true);
                $('#number').prop('disabled', true);
                $('#box').prop('disabled', true);
                $('#idCity').prop('disabled', true);
                $('#countryName').prop('disabled', true);
                $('#role').prop('disabled', true);
                $('#enabled').prop('disabled', true);

            }
            var btn = [
                {
                    text: "Fermer",
                    icon: "ui-icon-heart",
                    click: function () {
                        $(this).dialog("close");
                    }
                }];
            if (isAdmin) {
                btn.push({
                    text: "Enregistrer",
                    icon: "ui-icon-heart",
                    click: function validateUserManageForm() {
                        modifiedUser();
                        $(this).dialog("close");
                    }
                });
            }

            // $("#formCategory").toggle();
            $("#formUserInfo").dialog({
                modal: true,
                minWidth: 1200,
                minHeight: 100,
                open: function () {
                    $(this).closest(".ui-dialog")
                        .find(".ui-dialog-titlebar-close")
                        .removeClass("ui-dialog-titlebar-close")
                        .html("<span class='ui-button-icon-primary ui-icon ui-icon-closethick'></span>");
                },
                buttons: btn
            });
            found = true;
            // return;
        }
    });
    if (!found) {
        $("#formUserInfo").toggle();
    }
}

function modifiedUser() {
    var data = {
        lastname: $('#lastname').val(),
        firstname: $('#firstname').val(),
        email: $('#email').val(),
        phone: $('#phone').val(),
        address: {
            street: $('#street').val(),
            number: $('#number').val(),
            box: $('#box').val(),
            city: {
                idCity: $('#idCity').val(),
            },
        },
        role: {
            idRole: $('#role').val(),
        },
        enabled: $('#enabled').is(':checked')
    }
    $.ajax({
        contentType: 'application/json',
        type: 'PUT',
        url: pageContextPath + "/userManage/modifiedUser",
        data: JSON.stringify(data),
        success: successSaveUser,
        fail: fail,
        dataType: "json",
        headers: {'X-CSRF-Token': $('#_csrf').val()}

    });
}

function successSaveUser() {
    userManageDatatable.ajax.reload();
    $.toast(
        {
            heading: 'Félicitations',
            text: 'Votre utilisateur a été modifié.',
            showHideTransition: 'slide',
            icon: 'success',
            position: 'top-right',
            stack: false
        }
    );
    // $("#formCategory").toggle();
}

function fail(e) {
    console.log(e);
    alert("fail: " + e);

}

function checkLastname() {
    var lastname = $("#lastname").val();
    if (lastname === "" || lastname == null) {
        document.getElementById("errorLastname").innerHTML = "Veuillez indiquer au moins un caractère";
        $("#lastname").addClass("fieldMistake ");
        return false;
    } else if (lastname.length > 50) {
        document.getElementById("errorLastname").innerHTML = "Maximun 50 Caractères";
        $("#lastname").addClass("fieldMistake ");
        return false;
    }
    document.getElementById("errorLastname").innerHTML = "";
    $("#lastname").removeClass("fieldMistake ");
    return true;
}

function checkFirstnameUser() {
    var firstnameUser = $("#firstname").val();
    if (firstnameUser == "" || firstnameUser == null) {
        document.getElementById("errorFirstnameUser").innerHTML = "Veuillez indiquer au moins un caractère";
        $("#firstname").addClass("fieldMistake ");
        return false;
    } else if (firstnameUser.length > 50) {
        document.getElementById("errorFirstnameUser").innerHTML = "Maximun 50 Caractères";
        $("#firstname").addClass("fieldMistake ");
        return false;
    }
    document.getElementById("errorFirstnameUser").innerHTML = "";
    $("#firstname").removeClass("fieldMistake ");
    return true;
}

function checkPhoneUser() {

    var phoneUser = $("#phone").val();

    if (phoneUser == "" || phoneUser == null) {
        document.getElementById("errorPhoneUser").innerHTML = "Veuillez indiquer au moins un caractère";
        $("#phone").addClass("fieldMistake ");
        return false;

    } else if (phoneUser.length > 50) {
        document.getElementById("errorPhoneUser").innerHTML = "Maximun 50 Caractères";
        $("#phone").addClass("fieldMistake ");
        return false;

    } else {
        document.getElementById("errorPhoneUser").innerHTML = "";
        $("#phone").removeClass("fieldMistake ");
    }
}

function checkStreet() {

    var street = $("#street").val();

    if (street == "" || street == null) {
        document.getElementById("errorStreet").innerHTML = "Veuillez indiquer au moins un caractère";
        $("#street").addClass("fieldMistake ");
        return false;

    } else if (street.length > 100) {
        document.getElementById("errorStreet").innerHTML = "Maximun 100 Caractères";
        $("#street").addClass("fieldMistake ");
        return false;

    } else {
        document.getElementById("errorStreet").innerHTML = "";
        $("#street").removeClass("fieldMistake ");
    }
}

function checkNumber() {

    var number = $("#number").val();

    if (number == "" || number == null) {
        document.getElementById("errorNumber").innerHTML = "Veuillez indiquer au moins un caractère";
        $("#number").addClass("fieldMistake ");
        return false;
    } else if (number.length > 10) {
        document.getElementById("errorNumber").innerHTML = "Maximun 10 Caractères";
        $("#number").addClass("fieldMistake ");
        return false;

    } else {
        document.getElementById("errorNumber").innerHTML = "";
        $("#number").removeClass("fieldMistake ");
    }
}

function validateUserManageForm() {
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
        checkLastname() === false ? formIsValid = false : formIsValid = formIsValid;
        checkFirstnameUser() === false ? formIsValid = false : formIsValid = formIsValid;
        checkPhoneUser() === false ? formIsValid = false : formIsValid = formIsValid;
        checkStreet() === false ? formIsValid = false : formIsValid = formIsValid;
        checkNumber() === false ? formIsValid = false : formIsValid = formIsValid;


        //retourne la valeur de formIsValid (true ou false)
        return formIsValid;
    } catch (e) {
        console.error(e);
        return false;
    }
}