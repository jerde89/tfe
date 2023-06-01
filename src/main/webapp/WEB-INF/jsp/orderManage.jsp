<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.time.format.DateTimeFormatter" %>

<html>
<head>
    <title>Gestion des commandes</title>
    <meta charset="UTF-8">
    <%@include file="head.jsp" %>
    <script src="${pageContext.request.contextPath}/js/orderManage.js"></script>

</head>

<body>
<div class="containerHeader">
    <%@include file="navbar.jsp" %>
</div>

<%--https://mdbootstrap.com/docs/standard/extended/responsive-table/--%>
<h1 style="text-align: center">Gestions des commandes</h1>



<div class="container">

    <div class="tab-content">
        <div class="tab-pane active m-4" id="tab-table1">
            <table class="table table-striped table-bordered" id="orderGrid" cellspacing="0"
                   width="100%">
                <thead>
                <tr>
                    <th></th>
                    <th>Date de réception</th>
                    <th>Nom</th>
                    <th>Mode de livraison</th>
                    <th>Total</th>
                    <th>Statut</th>
                </tr>
                </thead>
            </table>
        </div>

    </div>
</div>


</body>
</html>
