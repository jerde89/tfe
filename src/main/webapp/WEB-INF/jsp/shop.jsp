<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <%@include file="head.jsp" %>

    <script src="${pageContext.request.contextPath}/js/category.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/category.css">
    <meta th:name="_csrf" th:content="${_csrf.token}"/>
    <meta th:name="_csrf_header" th:content="${_csrf.headerName}"/>
    <script type="text/javascript">
        var pageContextPath = "${pageContext.request.contextPath}";
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
    </script>
</head>
<body>
<div>
    <%@include file="navbar.jsp" %>
    <h1 style="text-align: center">Gestion des magasins
        <button onclick="toggleShopPopup(-1)" data-toggle="tooltip" data-placement="top"
                title="ajouter une catégorie">
            <svg xmlns="http://www.w3.org/2000/svg" width="40" height="40" fill="currentColor" class="bi bi-node-plus"
                 viewBox="0 0 16 16">
                <path fill-rule="evenodd"
                      d="M11 4a4 4 0 1 0 0 8 4 4 0 0 0 0-8zM6.025 7.5a5 5 0 1 1 0 1H4A1.5 1.5 0 0 1 2.5 10h-1A1.5 1.5 0 0 1 0 8.5v-1A1.5 1.5 0 0 1 1.5 6h1A1.5 1.5 0 0 1 4 7.5h2.025zM11 5a.5.5 0 0 1 .5.5v2h2a.5.5 0 0 1 0 1h-2v2a.5.5 0 0 1-1 0v-2h-2a.5.5 0 0 1 0-1h2v-2A.5.5 0 0 1 11 5zM1.5 7a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1z"/>
            </svg>
        </button>
    </h1>

    <div class="container">
        <div>
            <div class="tab-pane active m-4" id="tab-table1">
                <table class="table table-striped table-bordered" id="shop_ajax" cellspacing="0" width="100%">
                    <thead>
                    <tr>
                        <th>Nom</th>
                        <th>Rue</th>
                        <th>Numéro</th>
                        <th>Code postal</th>
                        <th>Ville</th>
                        <th>Pays</th>
                        <th>Créer le</th>
                        <th>Actif</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                </table>

            </div>

        </div>

    </div>


    <div id="formshop" class="popupDialog">
        <%--        <form method="post"--%>
        <%--              action="${pageContext.request.contextPath}/category"--%>
        <%--              onsubmit="return validateCategoryForm()">--%>

        <input type="hidden" id='_csrf' name="${_csrf.parameterName}" value="${_csrf.token}">

        <input type="hidden" id="idShop">
        <div class="my-3">
            <div class="col-12">
                <label class="form-label text-black">Nom :</label>
                <input type="text" class="form-control" id="name" name="name"/>
            </div>
        </div>

            <div class="row mx-2">
                <div class="col-12">
                    <label for="inputStreet" class="form-label text-black">Rue*</label>
                    <input type="text" class="form-control" id="inputStreet" name="Address.street" placeholder="Rue" onblur="checkStreet()">
                    <div class="col-12">
                        <div class="span-error-div"><span class="span-error4" id="errorStreet"></span></div>
                    </div>
                </div>
            </div>


            <div class="row mx-2">
                <div class="col-6">
                    <label for="inputNumber" class="form-label text-black">Numéro*</label>
                    <input type="text" class="form-control" id="inputNumber" name="Address.number" placeholder="Numéro" onblur="checkNumber()">
                    <div class="span-error-div"><span class="span-error4" id="errorNumber"></span></div>
                </div>

                <div class="col-6">
                    <label for="InputBox" class="form-label text-black">Boîte</label>
                    <input type="text" class="form-control" id="InputBox" name="Address.box" placeholder="Boîte" >
                    <div class="span-error-div"><span class="span-error4" id="errorBox"></span></div>
                </div>
            </div>

            <div class="row mx-2">
                <div class="col-6">
                    <label class="form-label text-black">Ville*</label>
                    <select  class="form-control" name="Address.City.idCity" id="idCity">

                        <option selected value=""></option>
                        <%--                        var="category => une nouvelle variable appellée category--%>
                        <%--                        Items => reçu du controller ProdcutController de la focntion showCategoryList--%>
                        <c:forEach var="city" items="${cityList}">
                            <option value="${city.idCity}"><c:out value="${city.postalCode} - ${city.cityName}"/></option>
                        </c:forEach>
                    </select>
                    <div class="span-error-div"><span class="span-error4" id="errorTva"></span></div>

                </div>

                <div class="col-6">
                    <label class="form-label text-black">Pays (Uniquement Belgique pour le moment)</label>
                    <input type="email" class="form-control"  value="Belgique"   readonly>
                </div>

        <div class="my-3">
            <div class="">
                <label class="form-label text-black">Actif : </label>
                <input type="checkbox" id="enable" name="enable"/>
            </div>
        </div>

        <div class="my-3" id="blockCreated">
            <div class="col-12">
                <label class="form-label text-black">Créé le :
                </label>
                <span id="created"></span>
            </div>
        </div>

        <div class="my-3" id="blockUpdated">
            <div class="col-12">
                <label class="form-label text-black">Modifié le :
                </label>
                <span id="update"></span>
            </div>
        </div>
        <%--        <div class="rowr">--%>
        <%--            <div class="col-md-6 ms-auto text-center">--%>
        <%--                <button onclick="closePopupCategory()" class="btn btn-primary" style="background-color:#e3b04b">--%>
        <%--                    Annuler--%>
        <%--                </button>--%>
        <%--            </div>--%>
        <%--            <div class="col-md-6 ms-auto text-center">--%>
        <%--                <button type="submit" onclick="saveCategoryForm()" class="btn btn-primary"--%>
        <%--                        style="background-color:#e3b04b">--%>
        <%--                    Enregistrer--%>
        <%--                </button>--%>
        <%--            </div>--%>

        <%--        </div>--%>
        <%--        </form>--%>
    </div>

</body>

<c:import url="footer.jsp"/>
</html>

<%--Pour le popup https://codepen.io/imprakash/pen/GgNMXO --%>