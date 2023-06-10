<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>



<html>
<head>
    <%@include file="head.jsp" %>
    <%--    <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700&display=swap" rel="stylesheet">--%>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <%--    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@fortawesome/fontawesome-free@6.2.0/css/fontawesome.min.css" integrity="sha384-z4tVnCr80ZcL0iufVdGQSUzNvJsKjEtqYZjiQrrYKlpGow+btDHDfQWkFjoaz/Zr" crossorigin="anonymous">--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
    <link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Muli'>

</head>
<body>

<div class="containerHeader">
    <%@include file="navbar.jsp" %>
</div>


<div class="containerBody">
    <section class="ftco-section">
        <div class="container">


            <!-- Place for messages: error, alert etc ... -->

            <div class="row justify-content-center">
                <div class="form-group">
                    <div class="col-xs-15">
                        <div>
                            <c:if test="${param.error != null }">
                                <div class="alert alert-danger col-xs-offset-1 col-xs-10">
                                    Email et/ou mot de passe invalide.
                                </div>
                            </c:if>
<%--                            <c:if test="${param.error != null }">--%>
<%--                                <div class="alert alert-danger col-xs-offset-1 col-xs-10">--%>
<%--                                    <c:out value="${param.error}"/>--%>
<%--                                </div>--%>
<%--                            </c:if>--%>
                            <c:if test="${param.logout != null }">
                                <div class="alert alert-success col-xs-offset-1 col-xs-10">
                                    Vous êtes déconnecté.
                                </div>
                            </c:if>
                        </div>
                    </div>
                </div>
                <div class="col-md-12 col-lg-10">
                    <div class="wrap d-md-flex">
                        <div class="img" style="background-image: url(image/painLogin.jpg);">
                        </div>
                        <div class="login-wrap p-4 p-md-5">
                            <div class="d-flex">
                                <div class="w-100">
                                    <h3 class="mb-4">Connexion</h3>
                                </div>


                            </div>
                            <form action="/login" method="post">
                                <div class="form-group mb-3">
                                    <label class="label" for="name">Email</label>
                                    <input type="text" name="username" class="form-control" placeholder="Email" required>
                                </div>
                                <div class="form-group mb-3">
                                    <label class="label" for="password">Mot de passe</label>
                                    <input type="password" name="password" class="form-control" placeholder="Mot de passe"
                                           required>
                                </div>
                                <div class="form-group">
                                    <button type="submit" class="form-control btn btn-primary rounded submit px-3">Se
                                        connecter
                                    </button>
                                </div>
                                <div class="form-group d-md-flex">
                                    <div class="w-50 text-left">
                                        <label class="checkbox-wrap checkbox-primary mb-0">Enregistrer
                                            <input type="checkbox" checked>
                                            <span class="checkmark"></span>
                                        </label>
                                    </div>
                                    <div class="w-50 text-md-right">
                                        <a href="${pageContext.request.contextPath}/passwordForgot">Mot de passe oublié</a>
                                    </div>
                                </div>
                                <input type="hidden"
                                       name="${_csrf.parameterName}"
                                       value="${_csrf.token}"/>

                            </form>
                            <p class="text-center">Pas encore membre? <a href="${pageContext.request.contextPath}/register" >Inscrivez-vous</a>
                                <%--
                                                        </p>
                                <%--                        enlever data-toggle="tab"--%>
                                <%--                        <p class="text-center">Pas encore membre? <a data-toggle="tab" href="${pageContext.request.contextPath}/register" >Inscrivez-vous</a>--%>
                                <%--&lt;%&ndash;                            href="${pageContext.request.contextPath}/register"&ndash;%&gt;--%>
                                <%--                        </p>--%>
                        </div>
                    </div>
                </div>
            </div>

        </div>

    </section>
</div>

<div class="containerFooter">
    <%@include file="footer.jsp" %>
</div>

</body>

</html>