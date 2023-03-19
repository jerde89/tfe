<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>


<html>
<head>
    <%@include file="head.jsp" %>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <%--    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@fortawesome/fontawesome-free@6.2.0/css/fontawesome.min.css" integrity="sha384-z4tVnCr80ZcL0iufVdGQSUzNvJsKjEtqYZjiQrrYKlpGow+btDHDfQWkFjoaz/Zr" crossorigin="anonymous">--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/register.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/countrySelect.css">
    <link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Muli'>
    <script src="${pageContext.request.contextPath}/js/register.js"></script>
    <script src="${pageContext.request.contextPath}/js/countrySelect.js"></script>
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
                text: 'Votre formulaire a été envoyé avec succès',
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
          method="post" action="${pageContext.request.contextPath}/myPersonalData/modifiedUser"  onsubmit="return validateRegisterForm('myPersonalData')">

        <input type="hidden"
               name="${_csrf.parameterName}"
               value="${_csrf.token}"/>

        <div class="mt-2 mb-5 text-center">
            <h3 class="text-black">Mes données personnelles</h3>
            <h6 class="text-black-50">Veuillez entrer les champs obligatoires (*)</h6>
        </div>

        <div class="row mx-2">
            <div class="col-12">
                <label for="inputEmail" class="form-label text-black">Email (identifiant => non modifiable)</label>
                <input type="email" class="form-control" id="inputEmail" name="email" value="${email}"   readonly>
            </div>
        </div>

        <input type="hidden"
               name="password"
               value="${password}"/>

<%--        <div class="row mx-2">--%>
<%--            <div class="col-12">--%>
<%--                <label for="inputOldPassword" class="form-label text-black">Ancien Mot de passe*</label>--%>
<%--                <div class="input-group" id="show_hide_password">--%>
<%--                    <input type="password" class="form-control" id="inputOldPassword" name="passwordNoConfirm" placeholder="Mot de passe" onblur="checkPassword()">--%>
<%--                    <div class="input-group-addon">--%>
<%--                        <a href=""><i class="fa fa-eye-slash" aria-hidden="true"></i></a>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <div class="col-12">--%>
<%--                    <div class="span-error-div"><span class="span-error4" id="errorPassword"></span></div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>

<%--        <div class="row mx-2">--%>
<%--            <div class="col-12">--%>
<%--                <label for="inputPassword" class="form-label text-black">Nouveau Mot de passe*</label>--%>
<%--                <div class="input-group" id="show_hide_password">--%>
<%--                    <input type="password" class="form-control" id="inputPassword" name="passwordNoConfirm" placeholder="Mot de passe" onblur="checkPassword()">--%>
<%--                    <div class="input-group-addon">--%>
<%--                        <a href=""><i class="fa fa-eye-slash" aria-hidden="true"></i></a>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <div class="col-12">--%>
<%--                    <div class="span-error-div"><span class="span-error4" id="errorPassword"></span></div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>

<%--        <div class="row mx-2">--%>
<%--            <div class="col-12">--%>
<%--                <label for="inputConfirmPassword" class="form-label text-black">Confirmer le nouveau mot de passe*</label>--%>
<%--                <div class="input-group" id="show_hide_password_confirm">--%>
<%--                    <input type="password" class="form-control" id="inputConfirmPassword" name="password" placeholder="Entrez le Mot de passe de nouveau" onblur="checkConfirmPassword()">--%>
<%--                    <div class="input-group-addon">--%>
<%--                        <a href=""><i class="fa fa-eye-slash" aria-hidden="true"></i></a>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <div class="col-12">--%>
<%--                    <div class="span-error-div"> <span class="span-error4" id="errorConfirmPassword"></span></div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>

        <hr>
        <div class="row mx-2">
            <div class="col-6">
                <label for="inputName" class="form-label text-black">Nom*</label>
                <input type="text" class="form-control" id="inputName" name="lastname" value="${lastname}" onblur="checkNameUser()">
                <div class="col-6">
                    <div class="span-error-div"><span class="span-error4" id="errorNameUser"></span></div>
                </div>
            </div>

            <div class="col-6">
                <label for="inputFirstname" class="form-label text-black">Prénom*</label>
                <input type="text" class="form-control" id="inputFirstname" name="firstname" value="${firstname}" onblur="checkFirstnameUser()">
                <div class="col-6">
                    <div class="span-error-div"><span class="span-error4" id="errorFirstnameUser"></span></div>
                </div>
            </div>
        </div>

        <div class="row mx-2">
            <div class="col-6">
                <label for="inputPhone" class="form-label text-black">Téléphone*</label>
                <input type="text" class="form-control" id="inputPhone" name="phone" value="${phone}" onblur="checkPhoneUser()">
                <div class="col-6">
                    <div class="span-error-div"><span class="span-error4" id="errorPhoneUser"></span></div>
                </div>
            </div>

            <div class="col-6">
                <label for="datepicker" class="form-label text-black">Date de naissance</label>
                <input type="text" class="form-control" id="datepicker" name="dateOfBirth" value="${dateOfBirth}">
                <%--                name="InputDateOfBirthUser"--%>
                <div class="col-6">
                    <div class="span-error-div"><span class="span-error4" id="errorDateOfBirthUser"></span></div>
                </div>
            </div>
        </div>

        <div class="row mx-2">
            <div class="col-12">
                <label for="inputStreet" class="form-label text-black">Rue*</label>
                <input type="text" class="form-control" id="inputStreet" name="Address.street" value="${street}" onblur="checkStreet()">
                <div class="col-12">
                    <div class="span-error-div"><span class="span-error4" id="errorStreet"></span></div>
                </div>
            </div>
        </div>


        <div class="row mx-2">
            <div class="col-6">
                <label for="inputNumber" class="form-label text-black">Numéro*</label>
                <input type="text" class="form-control" id="inputNumber" name="Address.number" value="${number}" onblur="checkNumber()">
                <div class="span-error-div"><span class="span-error4" id="errorNumber"></span></div>
            </div>

            <div class="col-6">
                <label for="InputBox" class="form-label text-black">Boîte</label>
                <input type="text" class="form-control" id="InputBox" name="Address.box" value="${box}" >
                <div class="span-error-div"><span class="span-error4" id="errorBox"></span></div>
            </div>
        </div>


        <div class="row mx-2">
            <div class="col-6">
                <label for="inputPostalCode" class="form-label text-black">Code postal*</label>
                <input type="text" class="form-control" id="inputPostalCode" name="Address.City.postalCode" value="${postalCode}" onblur="checkPostalCode()">
                <div class="col-6">
                    <div class="span-error-div"><span class="span-error4" id="errorPostalCode"></span></div>
                </div>
            </div>

            <div class="col-6">
                <label for="inputCityName" class="form-label text-black">Ville*</label>
                <input type="text" class="form-control" id="inputCityName" name="Address.City.cityName" value="${cityName}" onblur="checkCity()">
                <div class="col-6">
                    <div class="span-error-div">
                        <span class="span-error4" id="errorCityName"></span>
                    </div>
                </div>
            </div>
        </div>

        <%--Procédure Jquery pour afficher les pays en liste déroulante   https://www.jqueryscript.net/form/country-picker-flags.html--%>
        <div class="row mx-2">
            <div class="col-12">
                <label class="form-label text-black">Pays*</label>
                <input id="country_selector" type="text" class="form-control" name="Address.City.Country.countryName" value="${country}">
            </div>
        </div>

        <div class="row">
            <div class="col-6">
                <div class="form-group" style="text-align:center">
                    <button id="submitBtn" type="submit" class="form-control btn btn-primary rounded submit px-3" onclick="javascript:history.go(-1)">Retour
                    </button>
                </div>
            </div>

            <div class="col-6">
                <div class="form-group" style="text-align:center">
                    <button id="submitBtn" type="submit" class="form-control btn btn-primary rounded submit px-3">Enregistrer mes modifications
                    </button>
                </div>
            </div>
        </div>
    </form>

</div>



</body>
<%@include file="footer.jsp" %>


</html>