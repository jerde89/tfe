<c:set var="pagina" value="${requestScope['javax.servlet.forward.request_uri']}"/>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page pageEncoding="UTF-8" %>


<link rel="stylesheet" href="${pageContext.request.contextPath}/css/navbar.css">
<script src="${pageContext.request.contextPath}/js/navbar.js"></script>


<nav class="navbar navbar-expand-lg navbar-dark sticky-top" style="background-color: #522110">

    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <li class="nav-item">
                <a class="navbar-brand" style="padding-top: 5px" href="#"> <img
                        src="${pageContext.request.contextPath}/image/logo.jpg" alt="logo"
                        width="40" height="40" class="d-inline-block align-text-top">
                </a>
            </li>

            <li class="nav-item"><a class="nav-link"
                                    aria-current="page" href="${pageContext.request.contextPath}/">Accueil</a></li>


            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/order">Commmander</a>
            </li>
            <li class="nav-item">
                <a class="nav-link ${pagina.endsWith('/contact') ? 'active' : ''}"
                   href="${pageContext.request.contextPath}/contact">Contact</a>
            </li>
            <li class="nav-item">
                <sec:authorize access="hasAnyAuthority('ADMIN', 'EMPLOYEE')">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/category">Gestion des Catégories</a>
            <li class="nav-item">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/product">Gestion des Produits</a>
            <li class="nav-item">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/userManage">Gestion des Utilisateurs</a>
            <li class="nav-item">
                </sec:authorize>

                <sec:authorize access="hasAnyAuthority('ADMIN')">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/feedbackList">Liste des feedbacks</a>
            <li class="nav-item">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/shop">Gestion des magasins</a>
            <li class="nav-item">
                </sec:authorize>
            </li>

        </ul>


        <div class="d-flex" style="float: right;    margin: 6px;">
            <div id="myBagNav">
                <div class="btn-group">
                    <div onclick="goToPageOrderRecap()" style="cursor: pointer">
                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor"
                             style="color :white" class="bi bi-cart3" viewBox="0 0 16 16">
                            <path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .49.598l-1 5a.5.5 0 0 1-.465.401l-9.397.472L4.415 11H13a.5.5 0 0 1 0 1H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM3.102 4l.84 4.479 9.144-.459L13.89 4H3.102zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
                        </svg>
                    </div>
                    <span id="mybBagCount">0</span>
                </div>

                <div class="btn-group">
                    <button type="button" class="btn" data-bs-toggle="dropdown" aria-expanded="false">
                        <div onclick="resetItem()">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512" width="20" height="20"
                                 fill="currentColor" style="color :white">
                                <!--! Font Awesome Pro 6.4.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. -->
                                <path d="M135.2 17.7L128 32H32C14.3 32 0 46.3 0 64S14.3 96 32 96H416c17.7 0 32-14.3 32-32s-14.3-32-32-32H320l-7.2-14.3C307.4 6.8 296.3 0 284.2 0H163.8c-12.1 0-23.2 6.8-28.6 17.7zM416 128H32L53.2 467c1.6 25.3 22.6 45 47.9 45H346.9c25.3 0 46.3-19.7 47.9-45L416 128z"/>
                            </svg>
                        </div>
                    </button>
                </div>
            </div>
            <div class="btn-group dropstart ">
                <button type="button" class="btn dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                    <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor"
                         class="bi bi-person" viewBox="0 0 16 16" style="color: #f2f3f4">--%>
                        <path d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6Zm2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0Zm4 8c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4Zm-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10Z"/>
                    </svg>
                </button>
                <div class="dropdown-menu">
                    <sec:authorize access="isAnonymous()">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/login">Se connecter</a>
                    </sec:authorize>
                    <sec:authorize access="isAuthenticated()">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/myPersonalData">Mes données
                            personnelles</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/changePassword">Modifier mot
                            de passe</a>
                        <hr class="dropdown-divider">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/logout">Se déconnecter</a>
                    </sec:authorize>
                </div>
            </div>
        </div>

        <%--        <div class="d-flex" style="float: right;    margin: 6px;">--%>
        <%--            <li class="nav-item dropdown">--%>
        <%--                <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true"--%>
        <%--                   aria-expanded="false">--%>

        <%--&lt;%&ndash;                <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-expanded="false">&ndash;%&gt;--%>
        <%--&lt;%&ndash;                <a class="btn btn-secondary btn-lg active" role="button" aria-pressed="false" style="background: #522110; border: 0px;">&ndash;%&gt;--%>
        <%--                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person" viewBox="0 0 16 16" style="color: #f2f3f4">--%>
        <%--                        <path d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6Zm2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0Zm4 8c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4Zm-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10Z"/>--%>
        <%--                    </svg>--%>
        <%--                </a>--%>
        <%--                <div class="dropdown-menu">--%>
        <%--                <sec:authorize access="isAuthenticated()">--%>
        <%--                    <a class="dropdown-item" href="${pageContext.request.contextPath}/myPersonalData">Mes données personnelles</a>--%>
        <%--                </sec:authorize>--%>
        <%--                    <a class="dropdown-item" href="${pageContext.request.contextPath}/login">Se connecter</a>--%>
        <%--                    <a class="dropdown-item" href="#">Something else here</a>--%>
        <%--                    <hr class="dropdown-divider">--%>
        <%--                    <a class="dropdown-item" href="#">Se déconnecter</a>--%>
        <%--                 </div>--%>
        <%--            </li>--%>
        <%--        </div>--%>

    </div>
</nav>


<%--<a href="${pageContext.request.contextPath}/login" class="btn btn-secondary btn-lg active" role="button" aria-pressed="true" style="background: #522110; border: 0px;">--%>
<%--    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person" viewBox="0 0 16 16" style="color: #f2f3f4">--%>
<%--        <path d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6Zm2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0Zm4 8c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4Zm-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10Z"/>--%>
<%--    </svg>--%>
<%--</a>--%>
