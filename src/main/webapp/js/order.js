$(document).ready(function () {
    // Handler for .ready() called.
    // alert("clic");
    $("#jerome").click(function () {
        alert("clic");
    });
    // toggle list vs card view
    $(".option__button").on("click", function () {
        $(".option__button").removeClass("selected");
        $(this).addClass("selected");
        if ($(this).hasClass("option--grid")) {
            $(".results-section").attr("class", "results-section results--grid");
        } else if ($(this).hasClass("option--list")) {
            $(".results-section").attr("class", "results-section results--list");
        }
    });
    //apprearance
    $("input.variation").on("click", function () {
        // if ($(this).val() > 3) {
        //     $("body").css("background", "#111");
        //     $("footer").attr("class", "dark");
        // } else {
        //     $("body").css("background", "#f9f9f9");
        //     $("footer").attr("class", "");
        // }
    });
});

function addBagProduct(idProduct, maneProduct, price) {
    var quantity = parseInt($('#quantity_' + idProduct).val(), 10);
    var myBag = JSON.parse(localStorage.getItem("myBag"));
    if (!myBag) {
        myBag = {
            total: quantity,
            record: [{
                id: idProduct,
                name: maneProduct,
                price: price,
                quantity: quantity
            }
            ]
        };
        $('#mybBagCount').html(myBag.total);
        localStorage.setItem("myBag", JSON.stringify(myBag));
        return;
    }

    var found = false;
    myBag.record.forEach(record => {
        if (record.id === idProduct) {
            myBag.total += quantity;
            record.quantity += quantity;
            found = true;
        }
    });
    if (!found) {
        myBag.record.push({
            id: idProduct,
            name: maneProduct,
            price : price,
            quantity: quantity
        })
        myBag.total += quantity;
    }
    localStorage.setItem("myBag", JSON.stringify(myBag));
    $('#mybBagCount').html(myBag.total);

}
