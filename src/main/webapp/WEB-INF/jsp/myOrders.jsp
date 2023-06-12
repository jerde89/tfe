<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<html>
<head>
    <title>Mes commandes</title>
    <meta charset="UTF-8">
    <%@include file="head.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/myOrders.css">
    <script type="text/javascript">
        var pageContextPath = "${pageContext.request.contextPath}";
    </script>
    <script src="${pageContext.request.contextPath}/js/myOrders.js"></script>
</head>
<body>
<div class="containerHeader">
    <%@include file="navbar.jsp" %>
</div>

<%--https://mdbootstrap.com/docs/standard/extended/responsive-table/--%>
<h1 style="text-align: center">Mes commandes</h1>

<div class="container">
    <div class="col-md-12">
        <%--        <div class="panel panel-default">--%>
        <%--            <div class="panel-heading">--%>
        <%--                Employee--%>
        <%--            </div>--%>
        <div class="panel-body">
            <table class="table table-condensed table-striped" id="orderGrid">
                <thead>
                <tr>
                    <th></th>
                    <th>Numéro de commande</th>
                    <th>Date de reception</th>
                    <th>Prix total</th>
                    <th>Payé?</th>
                    <th>Etat</th>
                </tr>
                </thead>
            </table>
        </div>
    </div>
</div>

</body>
<%@include file="footer.jsp" %>
</html>
