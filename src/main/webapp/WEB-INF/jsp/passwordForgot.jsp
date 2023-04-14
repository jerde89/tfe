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
    <script src="${pageContext.request.contextPath}/js/passwordForgot.js"></script>

</head>
<body>

<div class="containerHeader">
    <%@include file="navbar.jsp" %>
</div>


<div class="containerBody">
    <section class="ftco-section">
        <div class="container">

            <div class="row justify-content-center">
                <div class="form-group">
                    <div class="col-xs-15">
                            <div class="container">
                                <c:if test="${not empty sessionScope.errors}">
                                    <div class="alert alert-danger" role="alert">
                                        <c:forEach items="${sessionScope.errors}" var="error">
                                            <li>${error}</li>
                                        </c:forEach>
                                    </div>
                                    <c:remove var="errors" scope="session"></c:remove>
                                </c:if>

                                <c:if test="${not empty sessionScope.success}">
                                    <script>
                                        $.toast(
                                            {
                                                heading: 'Félicitations',
                                                text: 'votre mot de passe a été envoyé',
                                                showHideTransition: 'slide',
                                                icon: 'success',
                                                position: 'top-right',
                                                stack: false,
                                            }
                                        );
                                    </script>

                                    <c:remove var="success" scope="session"></c:remove>
                                </c:if>
                            </div>

                    </div>
                </div>
                <div class="col-md-12 col-lg-10">
                    <div class="wrap d-md-flex">
                        <div class="img" style="background-image: url(image/password.jpg);">
                        </div>
                        <div class="login-wrap p-4 p-md-5 ">
                            <div class="d-flex ">
                                <div class="w-100">
                                    <h3 class="mb-4">Mot de passe oublié</h3>
                                </div>

                            </div>
                            <p>Entrez l’email associé à votre compte et nous vous enverrons un email avec les instructions pour changer votre mot de passe.</p>

                            <form action="/passwordForgot/checkIfEmailExist" method="post"
                                  >
<%--                                Doit être mis dans tous les formulaires => empééche les attauques cross sites--%>
                                <input type="hidden"
                                       name="${_csrf.parameterName}"
                                       value="${_csrf.token}"/>
                                <div class="form-group mb-3">
                                    <label for="inputEmail" class="form-label text-black">Email (identifiant)*</label>
                                    <input type="email" class="form-control" id="inputEmail" name="email" placeholder="Email" onblur="checkEmailUser()">
                                    <div class="col-12">
                                        <div class="span-error-div"><span class="span-error4" id="errorEmailUser"></span></div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <button type="submit" class="form-control btn btn-primary rounded submit px-3">Envoyer
                                    </button>
                                </div>


                            </form>
                            <p class="text-center">Retour sur la page de <a href="${pageContext.request.contextPath}/login" >Connexion ?</a>

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

