var tableWaiting;
var tableInProgress;

/* Formatting function for row details - modify as you need */
function format(d) {
    // `d` is the original data object for the row
    console.log(d);
    let subTableHtml = '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;" class="table table-striped table-bordered">' +
        '<thead>' +
        '<tr>' +
        ' <th>Produit</th>' +
        '<th>Quantité</th>' +
        '</tr>' +
        '</thead>' +
        '';
    for (const productObj in d.productDetailDTOMap) {
    // d.productDetailDTOMap.forEach(el => {

        var el = d.productDetailDTOMap[productObj];
        subTableHtml += '<tr>' +
            "<td>" + el.product.name + "</td>" +
            "<td>" + el.productQuantity + "</td>" +
            "</tr>"
    };
    subTableHtml +='</table>';
    return subTableHtml;
}

$(document).ready(function () {
    tableWaiting = $('#orderWaitingTable').DataTable({
        ajax: {url: '/order/waitingByDeliveryDate', dataSrc: ""},
        columns: [
            {
                className: 'dt-control',
                orderable: false,
                data: null,
                defaultContent: '',
            },
            {data: 'deliveryDate', render: DataTable.render.datetime('YYYY-MM-DD', 'DD/MM/YYYY', 'fr')},
            {data: 'order', render: makeOrderCount},
            {data: 'productQuantity'},
            {data: 'status' , render: makeActionDone},
        ],
        order: [[1, 'desc']],

        "language": languageConfig
    });

    tableInProgress = $('#orderInProgressTable').DataTable({
        ajax: {url: '/order/InProgressByDeliveryDate', dataSrc: ""},
        columns: [
            {
                className: 'dt-control',
                orderable: false,
                data: null,
                defaultContent: '',
            },
            {data: 'deliveryDate', render: DataTable.render.datetime('YYYY-MM-DD', 'DD/MM/YYYY', 'fr')},
            {data: 'order', render: makeOrderCount},
            {data: 'productQuantity'},
        ],
        order: [[1, 'desc']],

        "language": languageConfig
    });

    // Add event listener for opening and closing details
    $('#orderWaitingTable tbody').on('click', 'td.dt-control',function(){
        var tr = $(this).closest('tr');
        var row = tableWaiting.row(tr);

        if (row.child.isShown()) {
            // This row is already open - close it
            row.child.hide();
            tr.removeClass('shown');
        } else {
            // Open this row
            row.child(format(row.data())).show();
            tr.addClass('shown');
        }
    });

    $('#orderInProgressTable tbody').on('click', 'td.dt-control',function(){
        var tr = $(this).closest('tr');
        var row = tableInProgress.row(tr);

        if (row.child.isShown()) {
            // This row is already open - close it
            row.child.hide();
            tr.removeClass('shown');
        } else {
            // Open this row
            row.child(format(row.data())).show();
            tr.addClass('shown');
        }
    });
});

function manageExpand () {

}
function makeActionDone(data, type, full, meta) {
    return '<input type="checkbox" onclick="orderReady(\''+full.orderIds.join(",")+'\')">';
}

function makeOrderCount(data, type, full, meta) {
    return full.orderIds.length;
}

function orderReady(orderIds) {
    // alert(orderIds);
    var data = {};
    data.orderIds = orderIds;
    $.ajax({
        type: "PUT",
        url: pageContextPath + "/order/statusToInProgress",
        data: data, success: successToInProgress, fail: fail,
        headers: {'X-CSRF-Token': $('input[name=csrf]').val()}
    });
}

function successToInProgress(){
    reloadAllDatatable();
    $.toast(
        {
            heading: 'Commande',
            text: 'Les commandes sont marquées comme préparées.',
            showHideTransition: 'slide',
            icon: 'success',
            position: 'top-right',
            stack: false
        }
    )
}

function reloadAllDatatable() {
    tableWaiting.ajax.reload();
    tableInProgress.ajax.reload();
}
function fail(e) {
    console.log(e);
    alert("fail: " + e);
}