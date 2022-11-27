<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>



<html>
<head>
    <%@include file="head.jsp" %>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <%--    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@fortawesome/fontawesome-free@6.2.0/css/fontawesome.min.css" integrity="sha384-z4tVnCr80ZcL0iufVdGQSUzNvJsKjEtqYZjiQrrYKlpGow+btDHDfQWkFjoaz/Zr" crossorigin="anonymous">--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/register.css">
    <link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Muli'>

</head>
<body>

<%@include file="navbar.jsp" %>

<div>
    <h1>test inscription</h1>
</div>

<section class="ftco-section">
    <div class="container">

        <div class="row justify-content-center">
            <div class="col-md-12 col-lg-10">
                <div class="wrap d-md-flex">
<%--                    <div class="img" style="background-image: url(image/painLogin.jpg);">--%>
<%--                    </div>--%>
                    <div class="login-wrap p-4 p-md-5">
                        <div class="d-flex">
                            <div class="w-100">
                                <h3 class="mb-4">Inscription</h3>
                            </div>

                        </div>
                        <form action="#" class="signin-form">
                            <div class="form-group d-md-flex">
                                <div class="w-50 text-left">
                                    <div class="form-group mb-3">
                                        <label class="label" for="name">Prénom</label>
                                        <input type="text" id="name" class="form-control" placeholder="Prénom" required>
                                    </div>
                                </div>
                                <div class="w-50 text-left">
                                    <div class="form-group mb-3">
                                        <label class="label" for="password">Nom</label>
                                        <input type="password" id="password" class="form-control" placeholder="Nom"  required>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <button type="submit" class="form-control btn btn-primary rounded submit px-3">S'inscrire                  </button>
                            </div>

                        </form>
                        <p class="text-center">Pas encore membre? <a href="${pageContext.request.contextPath}/register" >Inscrivez-vous</a>

                    </div>
                </div>
            </div>
        </div>
    </div>
</section>




<%@include file="footer.jsp" %>
</body>


</html>