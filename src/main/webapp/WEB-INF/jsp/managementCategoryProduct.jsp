<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <%@include file="head.jsp" %>
    <script type="text/javascript">
        var pageContextPath = "${pageContext.request.contextPath}";
    </script>
    <script src="${pageContext.request.contextPath}/js/managementCategoryProduct.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/managementCategoryProduct.css">
</head>

<div>
<%@include file="navbar.jsp" %>

<div class="rowMargin">
    <div class="row justify-content-between">
        <div class="col-4">
            <button onclick="togglePopupAddCategory()" class="btn btn-light" data-toggle="tooltip" data-placement="top" title="ajouter une catégorie" >
                ajouter une catégorie
            </button>
        </div>

        <div class="col-4">
            <button onclick="togglePopupAddCategory()" class="btn btn-light" data-toggle="tooltip" data-placement="top" title="ajouter un produit" >
                ajouter un produit
            </button>
        </div>
    </div>
</div>
<div class="container">
    <div>
        <ul class="nav nav-tabs" role="tablist">
            <li class="active">
                <a href="#tab-table1" data-toggle="tab">Catégories</a>
            </li>
            <li>
                <a href="#tab-table2" data-toggle="tab">Produits</a>
            </li>
            <li>
                <a href="#tab-table3" data-toggle="tab">Récapitulatif</a>
            </li>
        </ul>

        <div class="tab-content">
            <div class="tab-pane active m-4" id="tab-table1">
                <table class="table table-striped table-bordered" id="categories_ajax" cellspacing="0" width="100%">
                    <thead>
                    <tr>
                        <th>Nom</th>
                        <th>Description</th>
                        <th>Créée le</th>
                        <th>Modifiée le</th>
                        <th>Actif</th>
                        <th>Modifier</th>
                    </tr>
                    </thead>
                </table>

            </div>

            <div class="tab-pane m-4" id="tab-table2">
                <table class="table table-striped table-bordered" id="Product_ajax" cellspacing="0" width="100%">
                    <thead>
                    <tr>
                        <th>Nom</th>
                        <th>Description</th>
                        <th>Prix</th>
                        <th>Image</th>
                        <th>Actif</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                </table>
            </div>
            <div class="tab-pane m-4" id="tab-table3">
                <table class="table table-striped table-bordered" id="Recapitulatif_ajax" cellspacing="0" width="100%">
                    <thead>
                    <tr>
                        <th>Nom</th>
                        <th>Description</th>
                        <th>Prix</th>
                        <th>Image</th>
                        <th>Actif</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>
    </div>

</div>

<!--Popup pour ajouter une catégorie-->
<div class="contentAddCategory">
    <form class="shadow p-4 bg-white rounded" novalidate method="post"
          action="${pageContext.request.contextPath}/managementCategoryProduct/addCategory">
        <div class="pb-1">
            <h3>Nouvelle catégorie</h3>
        </div>
        <hr>
        <div class="pb-3" >
            <label><b>Nom : </b></label>
            <input type="text" id="nameAdd" name="name"/>
        </div>

        <div class="pb-3">
            <label><b>Description : </b></label>
            <input type="text" id="descriptionAdd" name="description"/>
        </div>

        <div class="pb-3">
            <label>Actif : </label>
            <input type="checkbox" id="actifAdd" name="enable"/>
        </div>
        <input type="hidden" id="addCategoryIdHidden"/>
        <button type="submit" onclick="" class="btn btn-primary" style="background-color:#F0BF72">
            Ajouter
        </button>
        <button onclick="togglePopupAddCategory()" class="btn btn-primary" style="background-color:#F0BF72">
            Annuler
        </button>
    </form>
</div>

<!--Popup pour modifier une catégorie-->
<div class="contentModifyCategory">
    <div class="pb-1">
        <h3>La catégorie <span id="nom"></span> <span id="nameLabel"></span></h3>
    </div>
    <hr>
    <div class="pb-3">
        <label><b>Nom : </b></label>
        <input type="text" id="name"/>
    </div>

    <div class="pb-3">
        <label><b>Description : </b></label>
        <input type="text" id="description"/>
    </div>
    <div class="pb-3">
        <label><b>Créé le : </b>
            <span id="created"></span>
        </label>
    </div>

    <div class="pb-3">
        <label><b>Modifié le : </b>
            <span id="update"></span>
        </label>
    </div>

    <div class="pb-3">
        <label><b>actif : </b>
            <input type="checkbox" id="enable"/>
        </label>
    </div>
    <div class="row">

        <div class="col-md-6 ms-auto text-center">
            <input type="hidden" id="categoryId"/>
            <button onclick="callAjaxModifyCategory()" class="btn btn-primary" style="background-color:#F0BF72">
                Enregistrer
            </button>
        </div>
        <div class="col-md-6 ms-auto text-center">
            <button onclick="toggleModifyCategoryPopup()" class="btn btn-primary" style="background-color:#F0BF72">
                Fermer
            </button>
        </div>
    </div>
</div>
</body>
</html>



