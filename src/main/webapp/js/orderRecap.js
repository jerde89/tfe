function showMyBag() {
    var myBag = JSON.parse(localStorage.getItem("myBag"));
    if (!myBag.record) {
        return;
    }
    myBag.record.forEach(record => {
        var htmlDiv = "<div class=''>" +
            record.name +
            "<input type='number' value='"+record.total+"'>"+
            "<input type='hidden' value='"+record.id+"'>"+


            "</div>";

        $("#orderList").append(htmlDiv);
    });
}