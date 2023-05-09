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


function findProductByCategory() {
    var checkedVals = $('input[name="categoryCheckbox"]:checked').map(function() {
        console.log(this);
        return parseInt(this.value,10);
    }).get();
    console.log(checkedVals);
    $.ajax({
        type: "GET",
        url: pageContextPath + "/product/findByCategories?categories=" + checkedVals,
        success: function (response) {
            console.log(response);
            $('#productList').html('');
            response.forEach(product => {

                console.log(product);
                var productDiv= ' <div class="profile">\n' +
                    '                <div class="profile__image"><img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/567707/dog.png"\n' +
                    '                                                 alt="Doggo"/></div>\n' +
                    '                <div class="profile__info">\n' +
                    '                    <h3>'+product.name +'</h3>\n' +
                    '                    <p class="profile__info__extra">'+product.description +'</p>\n' +
                    '                </div>\n' +
                    '                <div class="profile__stats">\n' +
                    '                    <p class="profile__stats__title">Catégorie</p>\n' +
                    '                    <h5 class="profile__stats__info">'+product.category.name +'</h5>\n' +
                    '                </div>\n' +
                    '                <div class="profile__stats">\n' +
                    '                    <p class="profile__stats__title">Prix</p>\n' +
                    '                    <h5>'+product.price +'</h5>\n' +
                    '                </div>\n' +
                    '                <div class="profile__stats">\n' +
                    '                    <p class="profile__stats__title">Quantité</p>\n' +
                    '<%--                    <h5 class="profile__stats__info">45 lbs</h5>--%>\n' +
                    '                    <input id="quantity_${product.idProduct}" type="number" value="1" class="quantity">\n' +
                    '                </div>\n' +
                    '                <div class="profile__cta">\n' +
                    '                    <a class="button" onclick="addBagProduct('+product.idProduct +',\'${product.name}\', \'${product.price}\')">Ajouter au panier</a>\n' +
                    '                </div>\n' +
                    '            </div>';
                $('#productList').append(productDiv);
            });

        },
        fail: function () {
            console.log("fail");
            $.toast(
                {
                    heading: 'Erreur',
                    text: 'Erreur lors de la vérification de l\'email ',
                    showHideTransition: 'slide',
                    icon: 'error',
                    position: 'top-right',
                    stack: false
                }
            )
        }
    });
}