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


    <div class="col-md-12">
        <h4>Date de réception des commandes <input type="date" id="datepicker"> </h4>

        <%--        <div class="panel panel-default">--%>
        <%--            <div class="panel-heading">--%>
        <%--                Employee--%>
        <%--            </div>--%>
        <div class="panel-body">
            <table class="table table-condensed table-striped">
                <thead>
                <tr>
                    <th></th>
                    <th>Utilisateur</th>
                    <th>Quantite</th>
                    <th>Statut</th>
                </tr>
                </thead>

                <tbody>

                <tr data-toggle="collapse" data-target="#demo${order.idOrder}" class="accordion-toggle">
                    <td>
                        <button class="btn btn-default btn-xs"><span class="glyphicon glyphicon-eye-open"></span>
                        </button>
                    </td>
                    <td>Julien</td>
                    <td>15</td>
                    <td>
                        <select name="statut" id="statut-select">
                            <option value="dog">En cours de préparation</option>
                            <option value="cat">Préparé</option>
                            <option value="cat">Délivré</option>
                        </select>
                    </td>
                </tr>

                <tr>
                    <td colspan="12" class="hiddenRow">
                        <div class="accordian-body collapse  px-5" id="demo${order.idOrder}">
                            <table class="table table-striped">
                                <thead>
                                <tr class="info">
                                    <th>Produits</th>
                                    <th>Quantité</th>
                                </tr>
                                </thead>

                                <tbody>

                                <tr data-toggle="collapse" class="accordion-toggle">

                                    <td>Croissant</td>
                                    <td>10</td>

                                </tr>

                                <tr data-toggle="collapse" class="accordion-toggle">

                                    <td>Pain en chocolat</td>
                                    <td>5</td>

                                </tr>

                                </tbody>
                            </table>
                        </div>
                    </td>
                </tr>

                <tr data-toggle="collapse" data-target="#demo${order.idOrder}" class="accordion-toggle">
                    <td>
                        <button class="btn btn-default btn-xs"><span class="glyphicon glyphicon-eye-open"></span>
                        </button>
                    </td>
                    <td>Jean paul</td>
                    <td>6</td>
                    <td>
                        <select name="statut" id="statut-select">
                            <option value="dog">En cours de préparation</option>
                            <option value="cat">Préparé</option>
                            <option value="cat">Délivré</option>
                        </select>
                    </td>
                </tr>

                <tr>
                    <td colspan="12" class="hiddenRow">
                        <div class="accordian-body collapse  px-5" id="demo${order.idOrder}">
                            <table class="table table-striped">
                                <thead>
                                <tr class="info">
                                    <th>Produits</th>
                                    <th>Quantité</th>
                                </tr>
                                </thead>

                                <tbody>

                                <tr data-toggle="collapse" class="accordion-toggle">

                                    <td>Baguette blanche</td>
                                    <td>3</td>

                                </tr>

                                <tr data-toggle="collapse" class="accordion-toggle">

                                    <td>Pain banc</td>
                                    <td>3</td>

                                </tr>

                                </tbody>
                            </table>
                        </div>
                    </td>
                </tr>

                </tbody>

            </table>
        </div>
    </div>


    <div class="tab-content">
        <div class="tab-pane active m-4" id="tab-table1">
            <table class="table table-striped table-bordered" id="feedback_non_lus_ajax" cellspacing="0"
                   width="100%">
                <thead>
                <tr>
                    <th>Date de réception</th>
                    <th>Nom</th>
                    <th>Prénom</th>
                    <th>Quantité</th>
                    <th>Statut</th>
                    <th>Actions</th>
                </tr>
                </thead>
            </table>
        </div>

    </div>
</div>


</body>
</html>
