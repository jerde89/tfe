var categoryDatatable;


$(document).ready(function () {
    $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
        $.fn.dataTable.tables({visible: true, api: true}).columns.adjust();
    });
    console.log($('#categories_ajax'));
    console.log('#categories_ajax');
    categoryDatatable = $('#categories_ajax').DataTable({
        ajax: {url: '/managementCategoryProduct/displayCategories', dataSrc: ""},
        columns: [
            {data: 'name'},
            {data: 'description'},
            {data: 'createdAt', render: renderDateTz},
            {data: 'updateAt', render: renderDateTz},
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



    function renderDateTz(data, type, row) {
        if (!data){
            return '';
        }

        return moment(data).format("DD/MM/YYYY HH:mm");
    }

    function makeActionCategories(data, type, full, meta) {
        console.log(data);
        console.log(type);
        console.log(full);
        return '<div class="row"><div class="col-4 text-center">\n' +
            '                                         <button onclick="toggleModifyCategoryPopup(' + full.idProductCategory + ')" data-toggle="tooltip" data-placement="top" title="Modifier" >\n' +
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

    function transformToMail(data) {
        return '<a href="mailto:' + data + '"/>' + data + '</a>';
    }


})
    ;

//fonction d'affichage de la popup de modification de catégorie
    function toggleModifyCategoryPopup(id) {
        if (!id) {
            $(".contentModifyCategory").toggle();
            return;
        }
        var found = false;

        categoryDatatable.rows().every(function (rowIdx, tableLoop, rowLoop) {
            var c = this.data();
            if (c.idProductCategory == id) {
                document.getElementById('categoryId').value = c.idProductCategory;
                document.getElementById('name').value = c.name;
                document.getElementById('nameLabel').innerHTML = c.name;
                document.getElementById('description').value = c.description;
                document.getElementById('created').innerHTML = moment(c.createdAt).format("DD/MM/YYYY HH:mm");
                if (c.updateAt) {
                    document.getElementById('update').innerHTML = moment(c.updateAt).format("DD/MM/YYYY HH:mm");

                }
                // si enable est à true, la checkbox est cochée
                if (c.enable) {
                    document.getElementById('enable').checked = c.enable;
                }
                $(".contentModifyCategory").toggle();
                found = true;
                // return;
            }
        });
        if (!found) {
            $(".contentModifyCategory").toggle();
        }
    }

    function togglePopupAddCategory() {
        $(".contentAddCategory").toggle();
    }

    function callAjaxModifyCategory() {
        const id = $('#categoryId').val();
        console.log($('#categoryId').val());
        var data = {
            'name': $('#name').val(),
            'description': $('#description').val(),
            'enable': $('#enable').is(':checked'),
            'IdProductCategory': null,
            'createdAt': null,
            'updateAt': null,

        };

        $.ajax({
            contentType: 'application/json',
            type: "PUT",
            url: pageContextPath + "/managementCategoryProduct/modifyCategory/" + id,
            data: JSON.stringify(data),
            success: successModifyCategory,
            fail: fail,
            dataType: "json",

        });
    }

    function successModifyCategory() {
        console.log("successModifyCategory");
        categoryDatatable.ajax.reload();
        $.toast(
            {
                heading: 'Félicitations',
                text: 'Votre a été modifié.',
                showHideTransition: 'slide',
                icon: 'success',
                position: 'top-right',
                stack: false
            }
        );
        $(".contentModifyCategory").toggle();

    }

    function fail(e) {
        console.log(e);
        alert("fail: " + e);
    }


