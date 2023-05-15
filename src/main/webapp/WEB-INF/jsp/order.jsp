<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: jedescha
  Date: 27-04-23
  Time: 09:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="head.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/order.css">
    <script src="${pageContext.request.contextPath}/js/order.js"></script>
        <script type="text/javascript">
    var pageContextPath = "${pageContext.request.contextPath}";
        </script>
    <title>Commande</title>
</head>
<body>
<%@include file="navbar.jsp" %> <!-- Start DEMO HTML (Use the following code into your project)-->
<input class="variation" id="bluepurple" type="radio" value="1" name="color" checked="checked"/>
<label for="bluepurple"></label>
<input class="variation" id="sunset" type="radio" value="2" name="color"/>
<label for="sunset"></label>
<input class="variation" id="godiva" type="radio" value="3" name="color"/>
<label for="godiva"></label>
<input class="variation" id="dark" type="radio" value="4" name="color"/>
<label for="dark"></label>
<input class="variation" id="pinkaru" type="radio" value="5" name="color"/>
<label for="pinkaru"></label>
<main>
    <section class="results-header">
        <h1>Nos produits</h1>
        <div class="results-header__option">
            <div class="option__button option--grid selected"><span></span><span></span><span></span><span></span><span>Grid</span></div>
            <div class="option__button option--list"><span></span><span></span><span></span><span>List</span></div>
        </div>
    </section>
    <div class="filter-section__wrapper">
        <section class="filter-section">
            <h6>Filters</h6>
            <div class="filters">
                <h5 class="filters__title">Catégories</h5>
                <c:forEach var="category" items="${categoryList}">
                    <div class="filters__item">
                        <div class="checkbox">
                            <input id="checkbox-${category.id}" type="checkbox" name="categoryCheckbox" onclick="findProductByCategory()" value="${category.id}" checked/>
                            <label for="checkbox-${category.id}">${category.name}<span
                                    class="box"></span>
                                <div class="tooltip top" data-tooltip="Younger than 18 months."><span><i
                                        class="icon-info"></i></span></div>
                            </label>
                        </div>
                        <span class="badge status-primary">${category.countProduct}</span>
                    </div>
                </c:forEach>

            </div>
        </section>
    </div>

    <section class="results-section results--grid" id="productList">

        <c:forEach var="product" items="${productList}">
            <div class="profile">
<%--                https://s3-us-west-2.amazonaws.com/s.cdpn.io/567707/dog.png--%>
                <div class="profile__image"><img src="http://localhost:8080/imageProduct/${product.img}"

                                                 alt="Doggo"/></div>
                <div class="profile__info">
                    <h3>${product.name}</h3>
                    <p class="profile__info__extra">${product.description}</p>
                </div>
                <div class="profile__stats">
                    <p class="profile__stats__title">Catégorie</p>
                    <h5 class="profile__stats__info">${product.category.name}</h5>
                </div>
                <div class="profile__stats">
                    <p class="profile__stats__title">Prix</p>
                    <h5>${product.price}</h5>
                </div>
                <div class="profile__stats">
                    <p class="profile__stats__title">Quantité</p>
<%--                    <h5 class="profile__stats__info">45 lbs</h5>--%>
                    <input id="quantity_${product.idProduct}" type="number" value="1" class="quantity">
                </div>
                <div class="profile__cta">
                    <a class="button" onclick="addBagProduct(${product.idProduct},'${product.name}', '${product.price}')">Ajouter au panier</a>
                </div>
            </div>
        </c:forEach>
    </section>

</main>
</body>
</html>
