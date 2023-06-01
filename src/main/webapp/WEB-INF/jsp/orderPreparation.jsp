<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<html>
<head>
    <title>Préparations des commandes</title>
    <meta charset="UTF-8">
    <%@include file="head.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/orderPreparation.css">
    <script src="${pageContext.request.contextPath}/js/orderPreparation.js"></script>
    <meta th:name="_csrf" th:content="${_csrf.token}"/>
    <meta th:name="_csrf_header" th:content="${_csrf.headerName}"/>
    <script type="text/javascript">
        var pageContextPath = "${pageContext.request.contextPath}";
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
    </script>
</head>
<body>

<%@include file="navbar.jsp" %>


<div class="container" style="margin-bottom: 100px">

<%--https://mdbootstrap.com/docs/standard/extended/responsive-table/--%>
        <h1 style="text-align: center">Préparations des commandes</h1>
        <ul class="nav nav-tabs" role="tablist">
            <li class="active">
                <a href="#tab-table1" data-toggle="tab">A préparer</a>
            </li>
            <li>
                <a href="#tab-table2" data-toggle="tab">Préparées</a>
            </li>
        </ul>

    <div class="tab-content">

        <div class="tab-pane active m-4" id="tab-table1" >
            <table id="orderWaitingTable" class="table table-striped table-bordered display" >
                <thead>
                    <tr>
                        <th ></th>
                        <th>Date de réception</th>
                        <th>Nombre de commande</th>
                        <th>Nombre de Produit</th>
                        <th>Réalisée</th>
                    </tr>
                </thead>
            </table>
        </div>

        <div class="tab-pane m-4" id="tab-table2">
            <table id="orderInProgressTable" class="table table-striped table-bordered display" style="width: 100%">
                <thead>
                    <tr>
                        <th></th>
                        <th>Date de réception</th>
                        <th>Nombre de commande</th>
                        <th>Nombre de Produit</th>
                    </tr>
                </thead>
            </table>
        </div>

    </div>
</div>
<input type="hidden" id='_csrf' name="csrf" value="${_csrf.token}">
</body>
<%@include file="footer.jsp" %>
</html>
