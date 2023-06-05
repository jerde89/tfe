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
<%--<div id="color-theme">--%>
    <input class="variation" id="bluepurple" type="radio" value="1" name="color"/>
    <label for="bluepurple" class="toHide"></label>
    <input class="variation" id="sunset" type="radio" value="2" name="color" checked="checked"/>
    <label for="sunset" class="toHide"></label>
    <input class="variation" id="godiva" type="radio" value="3" name="color"/>
    <label for="godiva" class="toHide"></label>
    <input class="variation" id="dark" type="radio" value="4" name="color"/>
    <label for="dark" class="toHide"></label>
    <input class="variation" id="pinkaru" type="radio" value="5" name="color"/>
    <label for="pinkaru" class="toHide" ></label>
<%--</div>--%>
<main>
    <section class="results-header">
        <h1>Nos produits</h1>
        <div class="results-header__option">
            <div class="option__button option--grid selected"><span></span><span></span><span></span><span></span><span>Grid</span>
            </div>
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
                            <input id="checkbox-${category.id}" type="checkbox" name="categoryCheckbox"
                                   onclick="findProductByCategory()" value="${category.id}" checked/>
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


    </section>

</main>
</body>
<c:import url="footer.jsp"/>
</html>
