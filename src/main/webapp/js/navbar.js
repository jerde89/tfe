function resetItem(){
    localStorage.removeItem("myBag");
    $('#mybBagCount').html(0);
}

$( document ).ready(function() {
    var myBag = JSON.parse(localStorage.getItem("myBag"));
    $('#mybBagCount').html(myBag.total);
});