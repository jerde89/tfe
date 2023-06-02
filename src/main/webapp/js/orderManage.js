var tableWaiting;
$(document).ready(function () {
    tableWaiting = $('#orderGrid').DataTable({
        ajax: {url: '/order/afterStatusInProgress', dataSrc: ""},
        columns: [
            {
                className: 'dt-control',
                orderable: false,
                data: null,
                defaultContent: '',
            },
            {data: 'deliveryDate', render: DataTable.render.datetime('DD/MM/YYYY', 'DD/MM/YYYY', 'fr')},
            {data: 'user.lastname', render: makeUser},
            {data: 'deliveryMode', render: renderDeliveryMode, className: 'dt-body-center'},
            {data: 'total', render: renderTotalPrice, className: "text-right"},
            {data: 'status', render: renderDeliveryStatus, className: 'dt-body-center'},

        ],
        order: [[1, 'desc']],
        "language": languageConfig
    });
    $('#orderGrid tbody').on('click', 'td.dt-control', function () {
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

    function makeUser(data, type, full) {
        return full.user.lastname + ' ' + full.user.firstname;
    }

    function renderDeliveryMode(data, type, full) {
        if (data === "SHOP") {
            return '<span title="En magasin"><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" className="bi bi-shop"\n' +
                '             viewBox="0 0 16 16">\n' +
                '            <path\n' +
                '                d="M2.97 1.35A1 1 0 0 1 3.73 1h8.54a1 1 0 0 1 .76.35l2.609 3.044A1.5 1.5 0 0 1 16 5.37v.255a2.375 2.375 0 0 1-4.25 1.458A2.371 2.371 0 0 1 9.875 8 2.37 2.37 0 0 1 8 7.083 2.37 2.37 0 0 1 6.125 8a2.37 2.37 0 0 1-1.875-.917A2.375 2.375 0 0 1 0 5.625V5.37a1.5 1.5 0 0 1 .361-.976l2.61-3.045zm1.78 4.275a1.375 1.375 0 0 0 2.75 0 .5.5 0 0 1 1 0 1.375 1.375 0 0 0 2.75 0 .5.5 0 0 1 1 0 1.375 1.375 0 1 0 2.75 0V5.37a.5.5 0 0 0-.12-.325L12.27 2H3.73L1.12 5.045A.5.5 0 0 0 1 5.37v.255a1.375 1.375 0 0 0 2.75 0 .5.5 0 0 1 1 0zM1.5 8.5A.5.5 0 0 1 2 9v6h1v-5a1 1 0 0 1 1-1h3a1 1 0 0 1 1 1v5h6V9a.5.5 0 0 1 1 0v6h.5a.5.5 0 0 1 0 1H.5a.5.5 0 0 1 0-1H1V9a.5.5 0 0 1 .5-.5zM4 15h3v-5H4v5zm5-5a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1v3a1 1 0 0 1-1 1h-2a1 1 0 0 1-1-1v-3zm3 0h-2v3h2v-3z"/>\n' +
                '        </svg></span>';
        } else if (data === "HOME") {
            return '<span title="A domicile"><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-truck" viewBox="0 0 16 16">\n' +
                '  <path d="M0 3.5A1.5 1.5 0 0 1 1.5 2h9A1.5 1.5 0 0 1 12 3.5V5h1.02a1.5 1.5 0 0 1 1.17.563l1.481 1.85a1.5 1.5 0 0 1 .329.938V10.5a1.5 1.5 0 0 1-1.5 1.5H14a2 2 0 1 1-4 0H5a2 2 0 1 1-3.998-.085A1.5 1.5 0 0 1 0 10.5v-7zm1.294 7.456A1.999 1.999 0 0 1 4.732 11h5.536a2.01 2.01 0 0 1 .732-.732V3.5a.5.5 0 0 0-.5-.5h-9a.5.5 0 0 0-.5.5v7a.5.5 0 0 0 .294.456zM12 10a2 2 0 0 1 1.732 1h.768a.5.5 0 0 0 .5-.5V8.35a.5.5 0 0 0-.11-.312l-1.48-1.85A.5.5 0 0 0 13.02 6H12v4zm-9 1a1 1 0 1 0 0 2 1 1 0 0 0 0-2zm9 0a1 1 0 1 0 0 2 1 1 0 0 0 0-2z"/>\n' +
                '</svg></span>';
        }
    }

    function renderTotalPrice(data) {
        return addZeroes(Math.round(data * 100) / 100) + " €";
    }

    function renderDeliveryStatus(data, type, full) {
        if (data === "PACKAGED") {
            return '<span title="Emballée" class="c-pointer" onclick="changeOrderStatus(' + full.idOrder + ', \'DONE\', \'' + full.user.lastname + ' ' + full.user.firstname + '\')" class="cursor-pointer">' +
                '<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-box-seam" viewBox="0 0 16 16">\n' +
                '  <path d="M8.186 1.113a.5.5 0 0 0-.372 0L1.846 3.5l2.404.961L10.404 2l-2.218-.887zm3.564 1.426L5.596 5 8 5.961 14.154 3.5l-2.404-.961zm3.25 1.7-6.5 2.6v7.922l6.5-2.6V4.24zM7.5 14.762V6.838L1 4.239v7.923l6.5 2.6zM7.443.184a1.5 1.5 0 0 1 1.114 0l7.129 2.852A.5.5 0 0 1 16 3.5v8.662a1 1 0 0 1-.629.928l-7.185 2.874a.5.5 0 0 1-.372 0L.63 13.09a1 1 0 0 1-.63-.928V3.5a.5.5 0 0 1 .314-.464L7.443.184z"/>\n' +
                '</svg></span>';
        } else if (data === "DONE") {
            return '<span title="Recptionnée">' +
                '<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-check2-square" viewBox="0 0 16 16">\n' +
                '  <path d="M3 14.5A1.5 1.5 0 0 1 1.5 13V3A1.5 1.5 0 0 1 3 1.5h8a.5.5 0 0 1 0 1H3a.5.5 0 0 0-.5.5v10a.5.5 0 0 0 .5.5h10a.5.5 0 0 0 .5-.5V8a.5.5 0 0 1 1 0v5a1.5 1.5 0 0 1-1.5 1.5H3z"/>\n' +
                '  <path d="m8.354 10.354 7-7a.5.5 0 0 0-.708-.708L8 9.293 5.354 6.646a.5.5 0 1 0-.708.708l3 3a.5.5 0 0 0 .708 0z"/>\n' +
                '</svg>'+
                '</span>';
        } else if (data === "IN_PROGRESS") {
            return '<span title="En cours" class="c-pointer" onclick="changeOrderStatus(' + full.idOrder + ', \'PACKAGED\', \'' + full.user.lastname + ' ' + full.user.firstname + '\')">' +
                '<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-journal-arrow-up" viewBox="0 0 16 16">\n' +
                '  <path fill-rule="evenodd" d="M8 11a.5.5 0 0 0 .5-.5V6.707l1.146 1.147a.5.5 0 0 0 .708-.708l-2-2a.5.5 0 0 0-.708 0l-2 2a.5.5 0 1 0 .708.708L7.5 6.707V10.5a.5.5 0 0 0 .5.5z"/>\n' +
                '  <path d="M3 0h10a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H3a2 2 0 0 1-2-2v-1h1v1a1 1 0 0 0 1 1h10a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H3a1 1 0 0 0-1 1v1H1V2a2 2 0 0 1 2-2z"/>\n' +
                '  <path d="M1 5v-.5a.5.5 0 0 1 1 0V5h.5a.5.5 0 0 1 0 1h-2a.5.5 0 0 1 0-1H1zm0 3v-.5a.5.5 0 0 1 1 0V8h.5a.5.5 0 0 1 0 1h-2a.5.5 0 0 1 0-1H1zm0 3v-.5a.5.5 0 0 1 1 0v.5h.5a.5.5 0 0 1 0 1h-2a.5.5 0 0 1 0-1H1z"/>\n' +
                '</svg>'+
                '</span>';
        }
    }
});

function format(d) {
    console.log(d);
    // `d` is the original data object for the row
    let subTableHtml = '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;" class="table table-striped table-bordered">' +
        '<thead>' +
        '<tr>' +
        ' <th>Produit</th>' +
        '<th>Quantité</th>' +
        '</tr>' +
        '</thead>' +
        '';

    d.orderDetails.forEach(el => {
        console.log(el);
        subTableHtml += '<tr>' +
            //"<td>" + el.productVersion.product.name + "</td>" +
            "<td>" + el.productVersion.id + "</td>" +
            "<td>" + el.quantity + "</td>" +
            "</tr>";
    });
    subTableHtml += '</table>';
    return subTableHtml;
}

function changeOrderStatus(orderId, status, userFullName) {
    let statusLabel = "Emballée";
    if (status === "DONE") {
        statusLabel = "Receptionnée";
    }
    $.confirm({
        theme: 'bootstrap', // 'material', 'bootstrap',
        title: 'Changment de status',
        alignMiddle: true,
        content: 'Etes-vous sur de vouloir passer la commande de ' + userFullName + ' au statut:' + statusLabel,
        buttons: {
            confirm: function () {
                const data = {
                    orderId: orderId,
                    status: status,
                };
                $.ajax({
                    url: pageContextPath + '/order/changeStatus',
                    type: 'PUT',
                    data: data,
                    success: function (data) {
                        tableWaiting.ajax.reload();
                    },
                    headers: {'X-CSRF-Token': $('input[name=csrf]').val()}
                });
            }, cancel: function () {
                return;
            },
        }
    });

}

function addZeroes(num) {
    return num.toLocaleString("fr", {useGrouping: false, minimumFractionDigits: 2})
}