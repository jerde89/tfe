var grid;
$(document).ready(function () {
    console.log("ready")
    grid = $('#orderGrid').DataTable({
        ajax: {url: '/order/forCurrentUser', dataSrc: ""},
        columns: [
            {
                className: 'dt-control',
                orderable: false,
                data: null,
                defaultContent: '',
            },
            {data: 'id'},
            {data: 'deliveryDate', render: DataTable.render.datetime('DD/MM/YYYY', 'DD/MM/YYYY', 'fr')},
            {data: 'total', render: renderTotalPrice, className: "text-right"},
            {data: 'status', render: renderDeliveryStatus, className: 'dt-body-center'},


        ],
        order: [[2, 'desc']],
        "language": languageConfig
    });
    $('#orderGrid tbody').on('click', 'td.dt-control', function () {
        var tr = $(this).closest('tr');
        var row = grid.row(tr);

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

    function format(d) {
        // `d` is the original data object for the row
        let subTableHtml = '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;" class="table table-striped table-bordered">' +
            '<thead>' +
            '<tr>' +
            '   <th>Catégories</th>\n' +
            '   <th>Produit</th>\n' +
            '   <th>Quantité</th>'+
            '</tr>' +
            '</thead>' +
            '';

        d.orderDetailDTOs.forEach(el => {
            console.log(el);
            subTableHtml += '<tr>' +
                "<td>" + el.productVersion.product.category.name + "</td>" +
                 "<td>" + el.productVersion.product.name + "</td>" +
                "<td>" + el.quantity + "</td>" +
                "</tr>";
        });
        subTableHtml += '</table>';
        return subTableHtml;
    }

});

function renderDeliveryStatus(data, type, full) {
    console.log(data);

    if (data === "PACKAGED") {
        return '<span title="Emballée" ">' +
            '<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-box-seam" viewBox="0 0 16 16">\n' +
            '  <path d="M8.186 1.113a.5.5 0 0 0-.372 0L1.846 3.5l2.404.961L10.404 2l-2.218-.887zm3.564 1.426L5.596 5 8 5.961 14.154 3.5l-2.404-.961zm3.25 1.7-6.5 2.6v7.922l6.5-2.6V4.24zM7.5 14.762V6.838L1 4.239v7.923l6.5 2.6zM7.443.184a1.5 1.5 0 0 1 1.114 0l7.129 2.852A.5.5 0 0 1 16 3.5v8.662a1 1 0 0 1-.629.928l-7.185 2.874a.5.5 0 0 1-.372 0L.63 13.09a1 1 0 0 1-.63-.928V3.5a.5.5 0 0 1 .314-.464L7.443.184z"/>\n' +
            '</svg></span>';
    } else if (data === "DONE") {
        return '<span title="Délivrée">' +
            '<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-check2-square" viewBox="0 0 16 16">\n' +
            '  <path d="M3 14.5A1.5 1.5 0 0 1 1.5 13V3A1.5 1.5 0 0 1 3 1.5h8a.5.5 0 0 1 0 1H3a.5.5 0 0 0-.5.5v10a.5.5 0 0 0 .5.5h10a.5.5 0 0 0 .5-.5V8a.5.5 0 0 1 1 0v5a1.5 1.5 0 0 1-1.5 1.5H3z"/>\n' +
            '  <path d="m8.354 10.354 7-7a.5.5 0 0 0-.708-.708L8 9.293 5.354 6.646a.5.5 0 1 0-.708.708l3 3a.5.5 0 0 0 .708 0z"/>\n' +
            '</svg>'+
            '</span>';
    } else if (data === "IN_PROGRESS") {
        return '<span title="En cours" >' +
            '<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-journal-arrow-up" viewBox="0 0 16 16">\n' +
            '  <path fill-rule="evenodd" d="M8 11a.5.5 0 0 0 .5-.5V6.707l1.146 1.147a.5.5 0 0 0 .708-.708l-2-2a.5.5 0 0 0-.708 0l-2 2a.5.5 0 1 0 .708.708L7.5 6.707V10.5a.5.5 0 0 0 .5.5z"/>\n' +
            '  <path d="M3 0h10a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H3a2 2 0 0 1-2-2v-1h1v1a1 1 0 0 0 1 1h10a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H3a1 1 0 0 0-1 1v1H1V2a2 2 0 0 1 2-2z"/>\n' +
            '  <path d="M1 5v-.5a.5.5 0 0 1 1 0V5h.5a.5.5 0 0 1 0 1h-2a.5.5 0 0 1 0-1H1zm0 3v-.5a.5.5 0 0 1 1 0V8h.5a.5.5 0 0 1 0 1h-2a.5.5 0 0 1 0-1H1zm0 3v-.5a.5.5 0 0 1 1 0v.5h.5a.5.5 0 0 1 0 1h-2a.5.5 0 0 1 0-1H1z"/>\n' +
            '</svg>'+
            '</span>';
    } else if (data === "WAITING") {
        return '<span title="En attente" >' +
            '<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-hourglass-split" viewBox="0 0 16 16">\n' +
            '  <path d="M2.5 15a.5.5 0 1 1 0-1h1v-1a4.5 4.5 0 0 1 2.557-4.06c.29-.139.443-.377.443-.59v-.7c0-.213-.154-.451-.443-.59A4.5 4.5 0 0 1 3.5 3V2h-1a.5.5 0 0 1 0-1h11a.5.5 0 0 1 0 1h-1v1a4.5 4.5 0 0 1-2.557 4.06c-.29.139-.443.377-.443.59v.7c0 .213.154.451.443.59A4.5 4.5 0 0 1 12.5 13v1h1a.5.5 0 0 1 0 1h-11zm2-13v1c0 .537.12 1.045.337 1.5h6.326c.216-.455.337-.963.337-1.5V2h-7zm3 6.35c0 .701-.478 1.236-1.011 1.492A3.5 3.5 0 0 0 4.5 13s.866-1.299 3-1.48V8.35zm1 0v3.17c2.134.181 3 1.48 3 1.48a3.5 3.5 0 0 0-1.989-3.158C8.978 9.586 8.5 9.052 8.5 8.351z"/>\n' +
            '</svg></span>';
    }
    return data;
}
function renderTotalPrice(data) {
    return addZeroes(Math.round(data * 100) / 100) + " €";
}
function addZeroes(num) {
    return num.toLocaleString("fr", {useGrouping: false, minimumFractionDigits: 2})
}
