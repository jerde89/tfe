function documentLoaded() {
    console.log($('#myBagNav'));
    $('#myBagNav').hide();
    showMyBag();
    $("#datepicker").datepicker({
        beforeShowDay: function(d) {
            var day = d.getDay();
            return [(day !== 2 && day !== 3 && day !== 4)];
        },
        closeText: 'Fermer',
        prevText: '&#x3c;Préc',
        nextText: 'Suiv&#x3e;',
        currentText: 'Aujourd\'hui',
        monthNames: ['Janvier','Fevrier','Mars','Avril','Mai','Juin',
            'Juillet','Aout','Septembre','Octobre','Novembre','Decembre'],
        monthNamesShort: ['Jan','Fev','Mar','Avr','Mai','Jun',
            'Jul','Aou','Sep','Oct','Nov','Dec'],
        dayNames: ['Dimanche','Lundi','Mardi','Mercredi','Jeudi','Vendredi','Samedi'],
        dayNamesShort: ['Dim','Lun','Mar','Mer','Jeu','Ven','Sam'],
        dayNamesMin: ['Di','Lu','Ma','Me','Je','Ve','Sa'],
        weekHeader: 'Sm',
        dateFormat: 'dd-mm-yy',
        firstDay: 1,
        isRTL: false,
        showMonthAfterYear: false,
        yearSuffix: '',
        minDate: '+2D',
        maxDate: '+2M +0D',
        numberOfMonths: 1,
        showButtonPanel: true
    });
}


function showMyBag() {

    var myBag = JSON.parse(localStorage.getItem("myBag"));
    if (!myBag) {
        return;
    }
    if (!myBag.record) {
        return;
    }
    myBag.record.forEach(record => {
        var totalPrice = parseFloat(record.price) * parseFloat(record.quantity);
        var htmlDiv = "<tr>\n" +
            "            <th scope=\"row\">" + record.name + "</th>\n" +
            "            <td>" +
            "               <input id='quantity_"+record.id+"' type='number' value='" + record.quantity + "' " +
            "                   onchange='changeQuantity(" + record.id + ", this, " + record.price + ")'></td>\n" +
            "            <td>" + record.price + "€</td>\n" +
            "            <td><span class='totalPriceByProduct' id='totalPrice_" + record.id + "'>" + totalPrice + "€</span></td>\n" +
            "        </tr>"

        $("#orderList").append(htmlDiv);
    });
    $("#orderList").append("<tr>\n" +
        "            <td></td>\n" +
        "            <td></td>\n" +
        "            <td>Total :</td>\n" +
        "            <td><span id=\"totalOrders\"></span></td>\n" +
        "        </tr>");

    calculTotalOrders();
}

function changeQuantity(id, element, priceUnit) {
    var newQuantity = parseInt(element.value, 10);
    var newTotalPrice = Number((newQuantity * priceUnit).toFixed(2));
    $("#totalPrice_" + id).html(newTotalPrice + "€");
    calculTotalOrders();
}

function calculTotalOrders() {
    var totalOrders = 0;
    //$('.totalPriceByProduct') => permet de recupérer tous les éléments dans la class est CSS est totalPriceByProduct
    //.each => peremet de parcourir tous les résultats
    $('.totalPriceByProduct').each(function () {
        totalOrders += parseFloat($(this).html());
    });
    $("#totalOrders").html(totalOrders + "€");
}

function sendOrder() {
    var myBag = JSON.parse(localStorage.getItem("myBag"));
    if (!myBag) {
        return;
    }
    if (!myBag.record) {
        return;
    }
    let order = {
        dateOfReceipt: $("#datepicker").val(),
        total: $("#totalOrders").html().replace('€', '').trim(),
        deliveryMode:$('input[name="delivery_mode"]:checked').val(),
        oderDetails: [
        ]
    }
    myBag.record.forEach(product => {
        var oderDetail = {
            id: product.id,
            unitPrice: product.price,
            quantity: $("#quantity_"+product.id).val,
            total: $("#totalPrice_"+product.id).html().replace('€', '').trim()
        }
        order.oderDetails.push(oderDetail);
    });
    $.ajax({
        contentType: 'application/json',
        type: "POST",
        url:  pageContextPath + "/order",
        data: JSON.stringify(order),
        success: successSaveCategory,
        fail: fail,
        dataType: "json",
        headers: {'X-CSRF-Token': $('#_csrf').val()}

    });

}

function successSaveCategory() {
    alert('ben jouki biloute');
}function fail() {
    alert('ben jouki biloute');
}