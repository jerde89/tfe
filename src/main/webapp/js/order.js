$(document).ready(function () {
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
    $('.toHide').hide();
    findProductByCategory();
});

function addBagProduct(idProduct, maneProduct, price, rateTVA) {
    var quantity = parseInt($('#quantity_' + idProduct).val(), 10);
    var myBag = JSON.parse(localStorage.getItem("myBag"));
    if (!myBag) {
        myBag = {
            total: quantity,
            record: [{
                id: idProduct,
                name: maneProduct,
                price: price,
                quantity: quantity,
                rateTVA: rateTVA
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
            price: price,
            quantity: quantity,
            rateTVA: rateTVA
        })
        myBag.total += quantity;
    }
    localStorage.setItem("myBag", JSON.stringify(myBag));
    $('#mybBagCount').html(myBag.total);
}


function findProductByCategory() {
    var checkedVals = $('input[name="categoryCheckbox"]:checked').map(function () {
        console.log(this);
        return parseInt(this.value, 10);
    }).get();
    console.log(checkedVals);
    $.ajax({
        type: "GET",
        url: pageContextPath + "/product/findByCategories?categories=" + checkedVals,
        success: function (response) {
            $('#productList').html('');
            response.forEach(product => {
                let productImg = product.img;
                if (!product.img) {
                    productImg = "istockphoto-1341411204-612x612.jpg";
                }
                const imgSrc = "http://localhost:8080/imageProduct/" + productImg;
                const priceWithTVA = parseFloat((product.price) * (1 + (product.taxRate / 100))).toFixed(2);
                var productDiv = ' <div class="profile">\n' +
                    '                <div class="profile__image">' +
                    '                   <img src="' + imgSrc + '" alt="' + product.name + '"/>' +
                    '                  </div>\n' +
                    '                <div class="profile__info">\n' +
                    '                    <h3>' + product.name + '</h3>\n' +
                    '                    <p class="profile__info__extra">' + product.description + '</p>\n' +
                    '                </div>\n' +
                    '                <div class="profile__stats">\n' +
                    '                    <p class="profile__stats__title">Catégorie</p>\n' +
                    '                    <h5 class="profile__stats__info">' + product.category.name + '</h5>\n' +
                    '                </div>\n' +
                    '                <div class="profile__stats">\n' +
                    '                    <p class="profile__stats__title">Prix</p>\n' +
                    '                    <h5>' + priceWithTVA + '</h5>\n' +
                    '                </div>\n' +
                    '                <div class="profile__stats">\n' +
                    '                    <p class="profile__stats__title">Quantité</p>\n' +
                    '                    <input id="quantity_' + product.idProduct + '" type="number" value="1" class="quantity">\n' +
                    '                </div>\n' +
                    '                <div class="profile__cta">\n' +
                    '                    <a class="button" onclick="addBagProduct(' + product.idProduct + ',\'' + product.name + '\', ' + product.price + ', ' + product.taxRate + ')">Ajouter au panier</a>\n' +
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