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
<%--    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/checkout.css" />--%>
    <script src="${pageContext.request.contextPath}/js/orderRecap.js"></script>
<%--    <script src="https://js.stripe.com/v3/"></script>--%>
<%--    <script src="${pageContext.request.contextPath}/js/checkout.js" defer></script>--%>
    <script type="text/javascript">
        var pageContextPath = "${pageContext.request.contextPath}";
    </script>

</head>
<body>
<%@include file="navbar.jsp" %>
<div class="col-md-12">
    <h3>Récapitulatif du panier</h3>
    <div onclick="resetBag()">Sup all</div>
    <div class="data">
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Produit</th>
                <th scope="col">Quantité</th>
                <th scope="col">Prix unitaire</th>
                <th scope="col">Total</th>
                <th scope="col">Action</th>
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
                <label for="delivery">Livraison à
                    domicile </label><span>(2€ supplémentaire, uniquement le dimanche)</span>
            </div>
        </div>
    </div>
    <div>
        <h3>Date de la commande/livraison</h3>
        <div class="m-3">
            <input type="text" id="datepicker">
        </div>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-sm">
            </div>
            <div class="col-sm">
                <input id="btnSendOrder" type="submit" value="Valider ma commande"
                       class="form-control btn btn-primary rounded submit px-3"
                       onclick="sendOrder()" > *
            </div>
            <div class="col-sm">
            </div>
        </div>
    </div>
    <input type="hidden" id='_csrf' name="${_csrf.parameterName}" value="${_csrf.token}">
<%--    <!-- Display a payment form -->--%>
<%--    <form id="payment-form">--%>
<%--        <div id="link-authentication-element">--%>
<%--            <!--Stripe.js injects the Link Authentication Element-->--%>
<%--        </div>--%>
<%--        <div id="payment-element">--%>
<%--            <!--Stripe.js injects the Payment Element-->--%>
<%--        </div>--%>
<%--        <button id="submit">--%>
<%--            <div class="spinner hidden" id="spinner"></div>--%>
<%--            <span id="button-text">Pay now</span>--%>
<%--        </button>--%>
<%--        <div id="payment-message" class="hidden"></div>--%>
<%--    </form>--%>
</div>
</body>
</html>
