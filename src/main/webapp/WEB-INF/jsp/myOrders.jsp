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
            <table class="table table-condensed table-striped">
                <thead>
                <tr>
                    <th></th>
                    <th>Numéro de commande</th>
                    <th>Date de reception commande</th>
                    <th>Prix total</th>
                    <th>Etat</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach var="order" items="${myOrders}">
                    <tr data-toggle="collapse" data-target="#demo${order.idOrder}" class="accordion-toggle">
                        <td>
                            <button class="btn btn-default btn-xs"><span class="glyphicon glyphicon-eye-open"></span>
                            </button>
                        </td>
                        <td>${order.idOrder}</td>
                        <td>${order.dateOfReceipt.format( DateTimeFormatter.ofPattern("dd/MM/yyyy"))}</td>
                        <td>${order.total}</td>
                        <td>${order.status}</td>
                    </tr>

                    <tr>
                        <td colspan="12" class="hiddenRow">
                            <div class="accordian-body collapse  px-5" id="demo${order.idOrder}">
                                <table class="table table-striped">
                                    <thead>
                                    <tr class="info">
                                        <th>Catégories</th>
                                        <th>Produits</th>
                                        <th>Quantité</th>
                                    </tr>
                                    </thead>

                                    <tbody>
                                    <c:forEach var="orderDetail" items="${order.orderDetails}">
                                        <tr data-toggle="collapse" class="accordion-toggle"
                                            data-target="#demo${order.idOrder}0">
                                            <td>${orderDetail.product.category.name}</td>
                                            <td>${orderDetail.product.name}</td>
                                            <td>${orderDetail.quantity}</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

</body>
<%@include file="footer.jsp" %>
</html>
