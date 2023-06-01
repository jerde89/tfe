let deliveryPicker = null;
$(document).ready(function () {
    documentLoaded();
    $('input[type=radio][name=delivery_mode]').change(function () {
        if (this.value == 'HOME') {
            $("#datepicker").datepicker("option", "beforeShowDay", renderCalendarCallback);
            const val = $("#totalOrders").html().replace("€", "").trim();
            const totalOrders = Number(val) + 2;
            $("#totalOrders").html(totalOrders.toFixed(2) + "€");
        } else {
            const val = $("#totalOrders").html().replace("€", "").trim();
            let totalOrders = Number(val) - 2;
            if (totalOrders < 0) {
                totalOrders = 0;
            }
            $("#totalOrders").html(totalOrders.toFixed(2) + "€");
        }
    });
    // deliveryDate
    // btnSendOrder
});

function renderCalendarCallback(date) {
    var day = date.getDay();
    if ($('input[name="delivery_mode"]:checked').val() === 'HOME') {
        return [(day === 0)];
    }
    ;
    return [(day !== 2 && day !== 3 && day !== 4)];
}

function documentLoaded() {
    $("#btnSendOrder").prop("disabled", true);
    // console.log($('#myBagNav'));
    // $('#myBagNav').hide();
    showMyBag();
    deliveryPicker = $("#datepicker").datepicker({
        beforeShowDay: renderCalendarCallback,
        closeText: 'Fermer',
        prevText: '&#x3c;Préc',
        nextText: 'Suiv&#x3e;',
        currentText: 'Aujourd\'hui',
        monthNames: ['Janvier', 'Fevrier', 'Mars', 'Avril', 'Mai', 'Juin',
            'Juillet', 'Aout', 'Septembre', 'Octobre', 'Novembre', 'Decembre'],
        monthNamesShort: ['Jan', 'Fev', 'Mar', 'Avr', 'Mai', 'Jun',
            'Jul', 'Aou', 'Sep', 'Oct', 'Nov', 'Dec'],
        dayNames: ['Dimanche', 'Lundi', 'Mardi', 'Mercredi', 'Jeudi', 'Vendredi', 'Samedi'],
        dayNamesShort: ['Dim', 'Lun', 'Mar', 'Mer', 'Jeu', 'Ven', 'Sam'],
        dayNamesMin: ['Di', 'Lu', 'Ma', 'Me', 'Je', 'Ve', 'Sa'],
        weekHeader: 'Sm',
        dateFormat: 'dd/mm/yy',
        firstDay: 1,
        isRTL: false,
        showMonthAfterYear: false,
        yearSuffix: '',
        minDate: '+3D',
        maxDate: '+2M +0D',
        numberOfMonths: 1,
        showButtonPanel: true,
        onSelect: function (dateText) {
            $("#btnSendOrder").prop("disabled", false);
            console.log("Selected date: " + dateText + "; input's current value: " + this.value);
        }
    });
    $("#datepicker").change(function (a, b, c) {
        if ($("#datepicker").val()) {
            $("#btnSendOrder").prop("disabled", false);
        } else {
            $("#btnSendOrder").prop("disabled", true);
        }
    })
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
        if (record.quantity === 0) {
            return;
        }
        const productPriceTva = parseFloat(record.price) * (1 + (record.rateTVA / 100));
        const totalPrice = Number((record.quantity * productPriceTva).toFixed(2));

        var htmlDiv = "<tr id='product_row_" + record.id + "'>\n" +
            "            <th scope=\"row\">" + record.name + "</th>\n" +
            "            <td>" +
            "               <input id='quantity_" + record.id + "' class='w-100' type='number' min='0' value='" + record.quantity + "' " +
            "                   onchange='changeQuantity(" + record.id + ", this, " + productPriceTva + ")'></td>\n" +
            "            <td>" + Number(productPriceTva).toFixed(2) + "€</td>\n" +
            "            <td>" +
            "                   <span class='totalPriceByProduct' id='totalPrice_" + record.id + "'>" + totalPrice + "€</span>" +
            "            </td>\n" +
            "            <td> " +
            "           <span  data-toggle='tooltip' data-placement='top' title='supprimer' onclick='deleteProduct(" + record.id + ")'>\n" +
            "               <svg xmlns='http://www.w3.org/2000/svg' width='16' height='16' fill='currentColor' className='bi bi-trash' viewBox='0 0 16 16'>\n" +
            "                     <path d='M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5Zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5Zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6Z'/>\n" +
            "                    <path d='M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1ZM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118ZM2.5 3h11V2h-11v1Z'/>\n" +
            "               </svg>\n" +
            "           </span>\n" +
            "            </td>\n" +
            "        </tr>"

        $("#orderList").append(htmlDiv);
    });
    $("#orderList").append("<tr class='border-none'><td></td></tr>" +
        "           <tr class='border-none'>\n" +
        "            <td></td>\n" +
        "            <td></td>\n" +
        "            <td>Total :</td>\n" +
        "            <td><span id=\"totalOrders\"></span></td>\n" +
        "            <td></td>\n" +
        "        </tr>");

    calculationTotalOrders();
}

function changeQuantity(id, element, priceUnit) {
    var newQuantity = parseInt(element.value, 10);
    var newTotalPrice = Number((newQuantity * priceUnit).toFixed(2));
    $("#totalPrice_" + id).html(newTotalPrice + "€");
    updateMyBag(id, newQuantity);
    calculationTotalOrders();
}

function updateMyBag(productId, newQuantity) {
    var myBag = JSON.parse(localStorage.getItem("myBag"));
    if (!myBag) {
        return;
    }
    if (!myBag.record) {
        return;
    }
    let diff = 0;
    myBag.record.forEach(product => {
        if (product.id === productId) {
            diff = newQuantity - product.quantity;
            product.quantity = newQuantity;
        }
    });
    myBag.total += diff;
    $('#mybBagCount').html(myBag.total);

    localStorage.setItem("myBag", JSON.stringify(myBag));
}

function resetBag() {
    localStorage.removeItem("myBag");
    $('#mybBagCount').html(0);
}

function deleteProduct(productId) {
    $("#product_row_" + productId).remove();
    updateMyBag(productId, 0);
    calculationTotalOrders();

}

function calculationTotalOrders() {
    var totalOrders = 0;
    //$('.totalPriceByProduct') => permet de recupérer tous les éléments dans la class est CSS est totalPriceByProduct
    //.each => peremet de parcourir tous les résultats
    $('.totalPriceByProduct').each(function () {
        totalOrders += parseFloat($(this).html());
    });
    $("#totalOrders").html(Number(totalOrders).toFixed(2) + "€");
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
        deliveryDate: $("#datepicker").val(),
        // total: $("#totalOrders").html().replace('€', '').trim(),
        deliveryMode: $('input[name="delivery_mode"]:checked').val(),
        orderDetails: []
    }
    myBag.record.forEach(product => {
        var oderDetail = {
            id: product.id,
            price: product.price,
            quantity: parseInt($("#quantity_" + product.id).val(), 10),
            // total: $("#totalPrice_" + product.id).html().replace('€', '').trim()
            product: {
                idProduct: product.id
            }
        }
        order.orderDetails.push(oderDetail);
    });
    const request = $.ajax({
        contentType: 'application/json',
        type: "POST",
        url: pageContextPath + "/order",
        data: JSON.stringify(order),
        success: successSaveOrder,
        fail: fail,
        dataType: "json",
        headers: {'X-CSRF-Token': $('#_csrf').val()}

    });
    request.fail(function (jqXHR, textStatus) {
        alert("Request failed: " + textStatus);
    });

}

function successSaveOrder() {
    resetBag();
    location.href = "/order";
}

function fail() {
    alert('ben jouki biloute');
}
