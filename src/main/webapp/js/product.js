var productDatatable;


$(document).ready(function () {
    $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
        $.fn.dataTable.tables({visible: true, api: true}).columns.adjust();
    });
    console.log($('#product_ajax'));
    console.log('#product_ajax');
    productDatatable = $('#product_ajax').DataTable({
        ajax: {url: '/product/getAll', dataSrc: ""},
        columns: [
            {data: 'name'},
            {data: 'description'},
            {data: 'category.name'},
            {data: 'img', render: function (data, type, row) {
                    if (data !== "" && data !== null) {
                        return '<img class="img24" src="'+pageContextPath+'/imageProduct/'+data+'">';
                    } else {
                        return '<img class="img24" src="'+pageContextPath+'/imageProduct/istockphoto-1341411204-612x612.jpg">';
                    }
                    return data;
                }
                },
            {data: 'price'},
            {data: 'taxRate'},
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
            {data: 'createdAt', render: DataTable.render.datetime('DD/MM/YYYY')},
            {data: 'updateAt', render: DataTable.render.datetime('DD/MM/YYYY')},
            {data: 'ActionsProduct', render: makeActionProduct},
        ],
        order: [[2, 'desc']],

        "language": languageConfig,

    });


    function renderDateTz(data, type, row) {
        if (!data) {
            return '';
        }

        return moment(data).format("DD/MM/YYYY HH:mm");
    }

    // fonction permetant de gérer les actions sur un produit
    function makeActionProduct(data, type, full, meta) {
        console.log(data);
        console.log(type);
        console.log(full);
        return '<div class="row"><div class="col-4 text-center">\n' +
            '                                         <button onclick="toggleProductPopup(' + full.idProduct + ')" data-toggle="tooltip" data-placement="top" title="Modifier" >\n' +
            '                                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" className="bi bi-pencil" viewBox="0 0 16 16">\n' +
            '                                                    <path d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"/>\n' +
            '                                                </svg>' +
            '                                                   \n' +
            '                                         </button>\n' +
            '                                    </div>\n' +
            '    </div> ';
    }


    function minText10(data, type, full, meta) {
        var strReturn = data.substring(0, 10);
        strReturn += data.length > 9 ? '...' : '';
        return strReturn;
    }
    $('form').on('submit', function(e) {
        // alert('submit');
        e.preventDefault();
    });

    $('button').on('click', function(e) {
        e.preventDefault();
        // alert('clicked');
    });


})
;

function toggleProductPopup(id) {
    if (!id) {
        $("#formProduct").toggle();
        return;
    }
    var found = false;
    $('#idProduct').val("");
    $('#name').val("");
    $('#description').val("");
    $('#category').val("");
    // $('#img').val("")
    $('#price').val("");
    $('#tax_rate').val("");
    $('#created').val("");
    $('#update').val("");
    $('#enable').prop( "checked", true )
    $("#file").val(null);
    const isNew = id === -1;
    if (isNew) {
        $("#blockCreated").hide();
        $("#blockUpdated").hide();
        $("#formProductTitleAdd").show();
        $("#formProductTitleModify").hide();
        $("#imgUrlDiv").hide();
        $("#tax_rate").val(6);
        $("#formProduct").prop('title', "Ajout d'un produit");
        showPopup();
        return;
    }
    $("#blockCreated").show();
    $("#blockUpdated").show();
    $("#formProductTitleAdd").hide();
    $("#formProductTitleModify").show();


    productDatatable.rows().every(function (rowIdx, tableLoop, rowLoop) {
        var c = this.data();
        if (c.idProduct === id) {
            $('#idProduct').val(c.idProduct);
            //Dans la popup de modification des produits => va recupérer les info sur le produit pour les afficher
            //elemendId => id de l'input de jsp
            //C.name => entity nom d'un élément
            document.getElementById('name').value = c.name;
            document.getElementById('description').value = c.description;
            document.getElementById('category').value = c.category.id;
            $('#imgUrl').html(c.img);
            document.getElementById('price').value = c.price;
            document.getElementById('tax_rate').value = c.taxRate;
            document.getElementById('created').innerHTML = moment(c.createdAt).format("DD/MM/YYYY HH:mm");
            if (c.updateAt) {
                document.getElementById('update').innerHTML = moment(c.updateAt).format("DD/MM/YYYY HH:mm");
            }
            // si enable est à true, la checkbox est cochée
            if (c.enable) {
                document.getElementById('enable').checked = c.enable;
            }
            $("#formProduct").prop('title', "Mdofication d'un produit");
            showPopup();
            found = true;
            // return;
        }
    });
    if (!found) {
        $("#formProduct").prop('title', "Ajout d'un produit");
        showPopup();
    }
}

// function makeActionProduct(data, type, full, meta) {
//     console.log(data);
//     console.log(type);
//     console.log(full);
//     return '<div class="row"><div class="col-4 text-center">\n' +
//         '                                         <button onclick="toggleProductPopup(' + full.idProduct + ')" data-toggle="tooltip" data-placement="top" title="Modifier" >\n' +
//         '                                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" className="bi bi-pencil" viewBox="0 0 16 16">\n' +
//         '                                                    <path d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"/>\n' +
//         '                                                </svg>' +
//         '                                                   \n' +
//         '                                         </button>\n' +
//         '                                    </div>\n' +
//         '    </div> ';
// }

function callAjaxModifyProduct() {

    const id = $('#idProduct').val();
    console.log($('#idProduct').val());

    var form = $("#formData");
    // var formData = new FormData(form[0]);
    const formData = new FormData($('#form-data')[0]);
    console.log(formData);


    var data = {
        'name': $('#name').val(),
        'description': $('#description').val(),
        'category': {'id':  $('#category').val()},
        // 'category':  $('#category').val(),
        'img': $('#img').val(),
        'price': $('#price').val(),
        'taxRate': $('#tax_rate').val(),

        'enable': $('#enable').is(':checked'),
        // 'formData': $('#file').file[0]
    };

    const isNew = id === "";
    var ajax;
    if (isNew) {
        ajax = {
            type: "POST",
            url: pageContextPath + "/product"
        }
    } else {
        ajax = {
            type: "PUT",
            url: pageContextPath + "/product/" + id,

        }
    }

    $.ajax({

        // contentType: 'application/json',
        type: ajax.type,
        url: ajax.url,
        // data: JSON.stringify(data),
        data: formData,
        success: successSaveProduct,
        fail: fail,
        // dataType: "json",
        headers: {'X-CSRF-Token': $('#_csrf').val()},

        cache: false,
        contentType: false,
        processData: false

    });
}

function successSaveProduct() {

    productDatatable.ajax.reload();
    $.toast(
        {
            heading: 'Félicitations',
            text: 'Votre produit a été enregistré.',
            showHideTransition: 'slide',
            icon: 'success',
            position: 'top-right',
            stack: false
        }
    );
    $("#formProduct").toggle();

}

function fail(e) {
    console.log(e);
    alert("fail: " + e);

}

function checkNameProduct(isNew) {
    var nameProduct = $("#name").val();

    if (nameProduct === "" || nameProduct == null) {
        document.getElementById("errorName").innerHTML = "Veuillez indiquer au moins un caractère";
        $("#name").addClass("fieldMistake ");
    } else if (nameProduct.length > 50) {
        document.getElementById("errorName").innerHTML = "Maximun 50 Caractères";
        $("#name").addClass("fieldMistake ");
    } else {
        document.getElementById("errorName").innerHTML = "";
        $("#name").removeClass("fieldMistake ");
    }
    if (!isNew) {
        return;
    }
}

function checkDescriptionProduct() {
    var descriptionProduct = $("#description").val();

    if (descriptionProduct == "" || descriptionProduct == null) {
        document.getElementById("errorDescription").innerHTML = "Veuillez indiquer au moins un caractère";
        $("#description").addClass("fieldMistake ");
        return false;
    } else if (descriptionProduct.length > 200) {
        document.getElementById("errorDescription").innerHTML = "Maximun 200 Caractères";
        $("#description").addClass("fieldMistake ");
        return false;
    }
    document.getElementById("errorDescription").innerHTML = "";
    $("#description").removeClass("fieldMistake ");
    return true;
}

function checkPriceProduct() {
    var priceProduct = $("#price").val();

    if (priceProduct == "" || priceProduct == null) {
        document.getElementById("errorPrice").innerHTML = "Veuillez indiquer un montant";
        $("#price").addClass("fieldMistake ");
        return false;
    } else if (priceProduct.length > 10) {
        document.getElementById("errorPrice").innerHTML = "Maximun 10 Caractères";
        $("#price").addClass("fieldMistake ");
        return false;
    }
    document.getElementById("errorPrice").innerHTML = "";
    $("#price").removeClass("fieldMistake ");
    return true;
}

function checkTaxRateProduct() {
    var tax_rate = $("#tax_rate").val();

    if (tax_rate == "" || tax_rate == null) {
        document.getElementById("errorTva").innerHTML = "Veuillez indiquer un taux de TVA";
        $("#tax_rate").addClass("fieldMistake ");
        return false;
    } else if (tax_rate.length > 2) {
        document.getElementById("errorTva").innerHTML = "Maximun 2 Caractères";
        $("#tax_rate").addClass("fieldMistake ");
        return false;
    }
    document.getElementById("errorTva").innerHTML = "";
    $("#tax_rate").removeClass("fieldMistake ");
    return true;
}

function validateProductForm(isNew) {
    let formIsValid = true;
    // checkNameProduct(isNew) === false ? formIsValid = false : formIsValid = formIsValid;
    // checkDescriptionProduct() === false ? formIsValid = false : formIsValid = formIsValid;
    return formIsValid;
}

function saveProductForm() {

    const isNew = $("#idProduct").val() == "";
    console.log('saveproduct idProduct' + isNew);
    console.log('#idProduct' + isNew);
    var isValid = validateProductForm(isNew);
    if (isValid) {
        callAjaxModifyProduct();
    }
}

function closePopupProduct() {
    $("#formProduct").toggle();
}

function showPopup(){
    $("#formProduct").dialog({
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
                    saveProductForm();
                    $(this).dialog("close");
                }
            },
        ]
    });
}