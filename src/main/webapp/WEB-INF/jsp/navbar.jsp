<c:set var="pagina" value="${requestScope['javax.servlet.forward.request_uri']}" />


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
                                    aria-current="page" href="#">Accueil</a></li>

            <li class="nav-item">
                <a class="nav-link" href="#">Nos produits</a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="#">Promotions</a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="#">Commmander</a>
            </li>
            <li class="nav-item">
                <a class="nav-link ${pagina.endsWith('/contact') ? 'active' : ''}"
                   href="${pageContext.request.contextPath}/contact">Contact</a>
            </li>

            <li class="nav-item dropdown ">
                <a class="nav-link dropdown-toggle ${pagina.endsWith('/feedbackList') ? 'active' : ''}" data-toggle="dropdown" href="#" role="button" aria-haspopup="true"
                   aria-expanded="false">Administration</a>
                <div class="dropdown-menu">
                    <a class="dropdown-item ${pagina.endsWith('/feedbackList') ? 'active' : ''}" href="${pageContext.request.contextPath}/feedbackList">Liste des                        feedbacks</a>
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/managementCategoryProduct">Gestion
                        des Produits</a>
                </div>
            </li>

        </ul>
        <div class="d-flex" style="float: right;    margin: 6px;">

            <a class="btn. btn-light btn-sm"  href="${pageContext.request.contextPath}/login">Se connecter</a>

        </div>
    </div>
</nav>
