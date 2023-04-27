<c:set var="pagina" value="${requestScope['javax.servlet.forward.request_uri']}" />
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page pageEncoding="UTF-8" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/navbar.css">


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
            </sec:authorize>
            </li>

        </ul>
        <div class="d-flex" style="float: right;    margin: 6px;">
                <div class="btn-group dropstart ">
                    <button type="button" class="btn dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person" viewBox="0 0 16 16" style="color: #f2f3f4">--%>
                                <path d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6Zm2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0Zm4 8c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4Zm-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10Z"/>
                            </svg>
                    </button>
                    <div class="dropdown-menu">
                        <sec:authorize access="isAnonymous()">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/login">Se connecter</a>
                        </sec:authorize>
                        <sec:authorize access="isAuthenticated()">
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/myPersonalData">Mes données personnelles</a>
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/changePassword">Modifier mot de passe</a>
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
