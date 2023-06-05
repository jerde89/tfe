
$( document ).ready(function() {
    var myBag = JSON.parse(sessionStorage.getItem("myBag"));
    // si myBag existe (pas nul ou undefined)
    if(myBag){
        $('#mybBagCount').html(myBag.total);
    }
});

function goToPageOrderRecap(){
    location.href="/orderRecap";
}

function deleteSession(){
    sessionStorage.removeItem("myBag");
}