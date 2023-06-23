<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <%@include file="head.jsp" %>

    <script src="${pageContext.request.contextPath}/js/product.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/product.css">
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

    <h4 style="text-align: center">Gestion des produits
        <button onclick="toggleProductPopup(-1)" data-toggle="tooltip" data-placement="top" title="ajouter un produit">
            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-node-plus"
                 viewBox="0 0 16 16">
                <path fill-rule="evenodd"
                      d="M11 4a4 4 0 1 0 0 8 4 4 0 0 0 0-8zM6.025 7.5a5 5 0 1 1 0 1H4A1.5 1.5 0 0 1 2.5 10h-1A1.5 1.5 0 0 1 0 8.5v-1A1.5 1.5 0 0 1 1.5 6h1A1.5 1.5 0 0 1 4 7.5h2.025zM11 5a.5.5 0 0 1 .5.5v2h2a.5.5 0 0 1 0 1h-2v2a.5.5 0 0 1-1 0v-2h-2a.5.5 0 0 1 0-1h2v-2A.5.5 0 0 1 11 5zM1.5 7a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1z"/>
            </svg>
        </button>
    </h4>
    <div class="container">
        <div>
            <div class="tab-pane active m-4" id="tab-table1">
                <table class="table table-striped table-bordered" id="product_ajax" cellspacing="0" width="100%">
                    <thead>
                    <tr>
                        <th>Nom</th>
<%--                        <th>Description</th>--%>
                        <th>Catégorie</th>
                        <th>Image</th>
                        <th>HTVA</th>
                        <th>Taux</th>
                        <th>TVAC</th>
                        <th>Actif</th>
                        <th>Créée le</th>
                        <th>Modifiée le</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                </table>

            </div>

        </div>

    </div>



    <%--        <div id="formProductTEST" class="popup">--%>
    <%--            <form method="post" action="${pageContext.request.contextPath}/product/test" enctype="multipart/form-data">--%>
    <div id="formProduct" class="popupDialog">
        <form method="post" id="form-data" enctype="multipart/form-data">

            <input type="hidden" id='_csrf' name="${_csrf.parameterName}" value="${_csrf.token}">

<%--            <div class="pb-1" style="text-align: center">--%>
<%--                <h3 id="formProductTitleAdd">Nouveau produit</h3>--%>
<%--                <h3 id="formProductTitleModify">Modification produit</h3>--%>
<%--            </div>--%>
<%--            <hr>--%>

            <input type="hidden" id="idProduct">
            <div class="row mx-1">
                <div class="col-12">
                    <label class="form-label text-black">Nom :</label>
                    <input type="text" class="form-control" id="name" name="name" onblur="checkNameProduct()"/>
                    <div class="col-12">
                        <div class="span-error-div"><span class="span-error4" id="errorName"></span></div>
                    </div>
                </div>
            </div>

            <div class="row mx-1">
                <div class="col-12">
                    <label class="form-label text-black">Description :</label>
                    <input type="text" class="form-control" id="description" name="description" onblur="checkDescriptionProduct()"/>
                    <div class="col-12">
                        <div class="span-error-div"><span class="span-error4" id="errorDescription"></span></div>
                    </div>
                </div>
            </div>

            <div class="row mx-1">
                <div class="col-12">
                    <label class="form-label text-black">Catégorie :</label>
                    <select class="form-control" name="category" id="category">

                        <%--                        var="category => une nouvelle variable appellée category--%>
                        <%--                        Items => reçu du controller ProdcutController de la focntion showCategoryList--%>
                        <c:forEach var="category" items="${categoryList}">
                            <option value="${category.id}"><c:out value="${category.name}"/></option>
                        </c:forEach>
                    </select>
                    <div class="col-12">
                        <div class="span-error-div"><span class="span-error4" id="errorCategory"></span></div>
                    </div>
                </div>
            </div>

            <div class="row mx-1">
                <div class="col-12">
                    <label class="form-label text-black">Image :</label>
                    <input type="file" class="form-control" id="file" name="file" accept="image/*" onchange="return fileValidation()"/>
                    <div class="col-12">
                        <div class="span-error-div"><span class="span-error4" id="errorFile"></span></div>
                    </div>
                    <div id="imagePreview"></div>
                    <div  id="imgUrlDiv">
                        <span>Chemin actuel:</span>
                        <span id="imgUrl"></span>

                        <!-- Image preview -->

                    </div>
                </div>
            </div>

            <div class="row mx-1">
                <div class="col-12">
                    <label class="form-label text-black">HTVA :</label>
                    <input type="text" class="form-control" id="price" name="price"
                           onblur="checkPriceProduct()"/>
                    <div class="col-12">
                        <div class="span-error-div"><span class="span-error4" id="errorPrice"></span></div>
                    </div>
                </div>
            </div>

            <div class="row mx-1">
                <div class="col-12">
                    <label class="form-label text-black">Taux de TVA :</label>
                    <%--                    <input type="text" class="form-control" id="tva" name="tva"--%>
                    <%--                           onblur="checkTvaProduct()"/>--%>
                    <select class="form-control" name="tax_rate" id="tax_rate" onblur="checkTaxRateProduct()">
                        <option value=""></option>
                        <option value="0">0</option>
                        <option value="6">6</option>
                        <option value="12">12</option>
                        <option value="21">21</option>
                    </select>
                    <div class="col-12">
                        <div class="span-error-div"><span class="span-error4" id="errorTva"></span></div>
                    </div>
                </div>
            </div>


            <div class="row mx-1">
                <div class="pb-3">
                    <label class="form-label text-black">Actif : </label>
                    <input type="checkbox" id="enable" name="enable"/>
                </div>
            </div>

            <div class="row mx-1" id="blockCreated">
                <div class="col-12">
                    <label class="form-label text-black">Créé le :</label>
                    <span id="created"></span>

                </div>
            </div>

            <div class="row mx-1" id="blockUpdated">
                <div class="col-12">
                    <label class="form-label text-black">Modifié le :</label>
                    <span id="update"></span>

                </div>
            </div>
        </form>
    </div>
    <%--        </div>--%>

</body>
<c:import url="footer.jsp"/>
</html>


