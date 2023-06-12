<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<html>
<head>
    <%@include file="head.jsp" %>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <%--    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@fortawesome/fontawesome-free@6.2.0/css/fontawesome.min.css" integrity="sha384-z4tVnCr80ZcL0iufVdGQSUzNvJsKjEtqYZjiQrrYKlpGow+btDHDfQWkFjoaz/Zr" crossorigin="anonymous">--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
    <link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Muli'>
    <script src="${pageContext.request.contextPath}/js/stripeSuccess.js"></script>
    <script type="text/javascript">
        var pageContextPath = "${pageContext.request.contextPath}";

    </script>
    <title>Success</title>
</head>
<body>
<%@include file="navbar.jsp" %>

<div id="content" class="containerBody d-none">
    <section class="ftco-section">
        <div class="container">

            <div class="row justify-content-center">

                <div class="col-md-12 col-lg-10">
                    <div class="wrap d-md-flex">
                        <div class="img" style="background-image: url('/image/merci.jpg'); ">
                        </div>
                        <div class="login-wrap p-4 p-md-5 ">
                            <div class="d-flex ">
                                <div class="w-100">
                                    <h3 class="mb-1">Commande validée & payée</h3>
                                </div>

                            </div>
                            <hr>
                            <p>Votre commande est validée et payée.</p>

                            <p>Nous vous remercions pour votre confiance.</p>

                            <p>A bientôt !</p>




                            <p class="text-center">Retour sur la page de <a href="${pageContext.request.contextPath}/order" >Commande ?</a>

                        </div>
                    </div>
                </div>
            </div>

        </div>

    </section>

</div>
<input type="hidden" id='_csrf' name="${_csrf.parameterName}" value="${_csrf.token}">
</body>
<%@include file="footer.jsp" %>
</html>

