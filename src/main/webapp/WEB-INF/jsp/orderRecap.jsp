<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <%@include file="head.jsp" %>
    <title>Récapitulatif</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/orderRecap.css">
    <script src="${pageContext.request.contextPath}/js/orderRecap.js"></script>
    <script type="text/javascript">
        var pageContextPath = "${pageContext.request.contextPath}";
    </script>

</head>
<body onload="documentLoaded()">
<%@include file="navbar.jsp" %>
<h3>Récapitulatif du panier</h3>
<div class="data">
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Produit</th>
            <th scope="col">Quantité</th>
            <th scope="col">Prix unitaire</th>
            <th scope="col">Total</th>
        </tr>
        </thead>
        <tbody id="orderList">
        </tbody>
    </table>
</div>
<div>


</div>
<div>
    <h3>Mode de réception</h3>
    <div class="m-3">
        <div>
            <input type="radio" id="shop" name="delivery_mode" value="SHOP" checked>
            <label for="shop">En magasin </label>
        </div>

        <div>
            <input type="radio" id="delivery" name="delivery_mode" value="HOME">
            <label for="delivery">Livraison à domicile</label>
        </div>
    </div>

</div>
<div>
    <h3>Date de la commande/livraison</h3>
    <input type="text" id="datepicker">
</div>
<div class="m-5">
    <input type="submit" value="Valider ma commande" class="form-control btn btn-primary rounded submit px-3"
           onclick="sendOrder()">
</div>
<input type="hidden" id='_csrf' name="${_csrf.parameterName}" value="${_csrf.token}">
</body>
</html>