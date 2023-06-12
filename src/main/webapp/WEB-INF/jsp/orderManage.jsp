<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.time.format.DateTimeFormatter" %>

<html>
<head>
    <title>Gestion des commandes</title>
    <meta charset="UTF-8">
    <%@include file="head.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/orderManage.css">
    <script src="${pageContext.request.contextPath}/js/orderManage.js"></script>
    <script type="text/javascript">
        var pageContextPath = "${pageContext.request.contextPath}";
    </script>
</head>

<body>
<div class="containerHeader">
    <%@include file="navbar.jsp" %>
</div>

<%--https://mdbootstrap.com/docs/standard/extended/responsive-table/--%>
<h1 style="text-align: center">Gestions des commandes</h1>



<div class="container">

    <ul class="nav nav-tabs" role="tablist">
        <li class="active">
            <a href="#tab-table1" data-toggle="tab">En cours ou réalisées</a>
        </li>
        <li>
            <a href="#tab-table2" data-toggle="tab">En attentes</a>
        </li>
    </ul>

    <div class="tab-content">
        <div class="tab-pane active m-4" id="tab-table1">
            <table class="table table-striped table-bordered hover table-hover" id="orderGridWaiting" cellspacing="0"
                   width="100%">
                <thead>
                <tr>
                    <th></th>
                    <th>Date de réception</th>
                    <th>Nom</th>
                    <th>Mode de livraison</th>
                    <th>Total</th>
                    <th>Payé?</th>
                    <th>Statut</th>
                </tr>
                </thead>
            </table>
        </div>
        <div class="tab-pane m-4" id="tab-table2">
            <table class="table table-striped table-bordered hover table-hover" id="orderGridPending" cellspacing="0"
                   width="100%">
                <thead>
                <tr>
                    <th></th>
                    <th>Date de réception</th>
                    <th>Nom</th>
                    <th>Mode de livraison</th>
                    <th>Total</th>
                    <th>Payé?</th>
                    <th>Statut</th>

                </tr>
                </thead>
            </table>
        </div>
    </div>
</div>

<input type="hidden" id='_csrf' name="csrf" value="${_csrf.token}">
</body>
</html>
