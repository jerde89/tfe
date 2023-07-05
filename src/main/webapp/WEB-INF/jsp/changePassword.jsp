<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>


<html>
<head>
    <%@include file="head.jsp" %>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/changePassword.css">
    <link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Muli'>
<%--    <script src="${pageContext.request.contextPath}/js/changePassword.js"></script>--%>
    <script src="${pageContext.request.contextPath}/js/register.js"></script>
    <script type="text/javascript">
        var pageContextPath = "${pageContext.request.contextPath}";
    </script>
</head>
<body>

<%@include file="navbar.jsp" %>


<c:if test="${not empty sessionScope.success}">
    <script>
        $.toast(
            {
                heading: 'Félicitations',
                text: 'Vos modifications ont été enregistrées avec succès',
                showHideTransition: 'slide',
                icon: 'success',
                position: 'top-right',
                stack: false,
            }
        );
    </script>

    <c:remove var="success" scope="session"></c:remove>
</c:if>


<div class="container">
    <c:if test="${not empty sessionScope.errors}">
        <div class="alert alert-danger" role="alert">
            <c:forEach items="${sessionScope.errors}" var="error">
                <li>${error}</li>
            </c:forEach>
        </div>
        <c:remove var="errors" scope="session"></c:remove>
    </c:if>
</div>
<%--<div class="container align-items-center col-8 ">--%>
<div class="container align-items-center col-8 ">
    <form class="shadow p-4 bg-white rounded" name="myPersonalDataForm" id="myPersonalDataForm"
          method="post" action="${pageContext.request.contextPath}/changePassword/test"  >
<%--        onsubmit="return validateRegisterForm('myPersonalData')"--%>

        <input type="hidden"
               name="${_csrf.parameterName}"
               value="${_csrf.token}"/>

        <div class="mt-2 mb-5 text-center">
            <h3 class="text-black">Modifier mon mot de passe</h3>
            <h6 class="text-black-50">Veuillez entrer les champs obligatoires (*)</h6>
        </div>

                <div class="row mx-2">
                    <div class="col-12">
                        <label for="inputOldPassword" class="form-label text-black">Ancien Mot de passe*</label>
                        <div class="input-group" id="show_hide_password_old">
                            <input type="password" class="form-control" id="inputOldPassword" name="oldPassword" placeholder="Ancien mot de passe">
                            <div class="input-group-addon">
                                <a href=""><i class="fa fa-eye-slash" aria-hidden="true"></i></a>
                            </div>
                        </div>
                        <div class="col-12">
                            <div class="span-error-div"><span class="span-error4" id="errorPasswordOld"></span></div>
                        </div>
                    </div>
                </div>

                <div class="row mx-2">
                    <div class="col-12">
                        <label for="inputPassword" class="form-label text-black">Nouveau Mot de passe*</label>
                        <div class="input-group" id="show_hide_password">
                            <input type="password" class="form-control" id="inputPassword" name="newPassword" placeholder="Mot de passe" onblur="checkPassword()">
                            <div class="input-group-addon">
                                <a href=""><i class="fa fa-eye-slash" aria-hidden="true"></i></a>
                            </div>
                        </div>
                        <div class="col-12">
                            <div class="span-error-div"><span class="span-error4" id="errorPassword"></span></div>
                        </div>
                    </div>
                </div>

                <div class="row mx-2">
                    <div class="col-12">
                        <label for="inputConfirmPassword" class="form-label text-black">Confirmer le nouveau mot de passe*</label>
                        <div class="input-group" id="show_hide_password_confirm">
                            <input type="password" class="form-control" id="inputConfirmPassword" name="newConfirmPassword" placeholder="Entrez le Mot de passe de nouveau" onblur="checkConfirmPassword()">
                            <div class="input-group-addon">
                                <a href=""><i class="fa fa-eye-slash" aria-hidden="true"></i></a>
                            </div>
                        </div>
                        <div class="col-12">
                            <div class="span-error-div"> <span class="span-error4" id="errorConfirmPassword"></span></div>
                        </div>
                    </div>
                </div>




        <div class="form-group" style="text-align:center">
            <button id="submitBtn" type="submit" class="form-control btn btn-primary rounded submit px-3">Enregistrer mes modifications
            </button>
        </div>


    </form>

</div>

</body>

<%@include file="footer.jsp" %>


</html>
