$(document).ready(function () {
    var myBag = JSON.parse(sessionStorage.getItem("myBag"));
    if (!myBag) {
        return;
    }
    if (!myBag.record) {
        return;
    }
    var orderId =myBag.orderId;
    const data = {
        orderId: orderId,
    };
    $.ajax({
        url: pageContextPath + '/order/changeStatusPaid',
        type: 'PUT',
        data: data,
        success: function () {
            resetBag();
            $('#content').show();
        },
        headers: {'X-CSRF-Token': $('input[name=csrf]').val()}
    });
});
