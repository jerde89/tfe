<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
                        <th>Date de la commande</th>
                        <th>Prix total </th>
                        <th>Etat</th>

                    </tr>
                    </thead>

                    <tbody>
                    <tr data-toggle="collapse" data-target="#demo1" class="accordion-toggle">
                        <td><button class="btn btn-default btn-xs"><span class="glyphicon glyphicon-eye-open"></span></button></td>
                        <td>1</td>
                        <td>31/12/2023</td>
                        <td>25€</td>
                        <td>Terminé</td>

                    </tr>

                    <tr>
                        <td colspan="12" class="hiddenRow">
                            <div class="accordian-body collapse  px-5" id="demo1">
                                <table class="table table-striped">
                                    <thead>
                                    <tr class="info">
                                        <th>Catégories</th>
                                        <th>Produits</th>
                                        <th>Quantité</th>
                                    </tr>
                                    </thead>

                                    <tbody>

                                    <tr data-toggle="collapse"  class="accordion-toggle" data-target="#demo10">
                                        <td>Pains</td>
                                        <td>Pain de campagne</td>
                                        <td>10</td>

                                    </tr>


                                    <tr>
                                        <td>Patisserie</td>
                                        <td>Tarte aux fraises</td>
                                        <td>1</td>

                                    </tr>


                                    </tbody>
                                </table>

                            </div>
                        </td>
                    </tr>



                    <tr data-toggle="collapse" data-target="#demo2" class="accordion-toggle">
                        <td><button class="btn btn-default btn-xs"><span class="glyphicon glyphicon-eye-open"></span></button></td>
                        <td>32</td>
                        <td>11/11/2022</td>
                        <td>14,5</td>
                        <td>Terminé</td>

                    </tr>
                    <tr>
                        <td colspan="6" class="hiddenRow"><div id="demo2" class="accordian-body collapse">Demo2</div></td>
                    </tr>

                    </tbody>
                </table>
            </div>

<%--        </div>--%>

    </div>
</div>

</body>
<%@include file="footer.jsp" %>
</html>
