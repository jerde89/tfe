function resetItem(){
    localStorage.removeItem("myBag");
    $('#mybBagCount').html(0);
}

$( document ).ready(function() {
    var myBag = JSON.parse(localStorage.getItem("myBag"));
    // si myBag existe (pas nul ou undefined)
    if(myBag){
        $('#mybBagCount').html(myBag.total);
    }
});

function goToPageOrderRecap(){
    location.href="/orderRecap";
}