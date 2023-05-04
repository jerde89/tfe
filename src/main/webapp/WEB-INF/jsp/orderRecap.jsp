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
</head>
<body onload="showMyBag()">
<%@include file="navbar.jsp" %>
<h1>Récapitulatif du panier</h1>
<div id="orderList">

    </div>
<div>

    <div>
        <div>
            <input type="radio" id="huey" name="drone" value="huey"
                   checked>
            <label for="huey">Huey</label>
        </div>

        <div>
            <input type="radio" id="dewey" name="drone" value="dewey">
            <label for="dewey">Dewey</label>
        </div>


    </div>
<h1>Date de la commande/livraison</h1>
<input type="date">
</div>
<div>
<input type="submit" value="Valider ma commande">
</div>
</body>
</html>
