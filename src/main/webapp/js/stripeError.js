$(document).ready(function () {
    var myBag = JSON.parse(sessionStorage.getItem("myBag"));
    if (!myBag) {
        return;
    }
    if (!myBag.orderId) {
        return;
    }
    var orderId =myBag.orderId;
    const data = {
        orderId: orderId,
    };
    $.ajax({
        url: pageContextPath + '/order/deleteOrder',
        type: 'DELETE',
        data: data,
        success: function () {
             resetBag();
            $('#content').removeClass( "d-none");
        },
        headers: {'X-CSRF-Token': $('input[name=_csrf]').val()}
    });
});


function resetBag() {
    sessionStorage.removeItem("myBag");
    $('#mybBagCount').html(0);
}