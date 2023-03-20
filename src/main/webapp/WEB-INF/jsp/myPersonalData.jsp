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
<%--    <script src="${pageContext.request.contextPath}/js/register.js"></script>--%>
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
                <input type="email" class="form-control" id="inputEmail" name="email" value="${user.getEmail()}"   readonly>
            </div>
        </div>

<%--        <input type="hidden"--%>
<%--               name="password"--%>
<%--               value="${user.getPassword()}"/>--%>



        <hr>
        <div class="row mx-2">
            <div class="col-6">
                <label for="inputName" class="form-label text-black">Nom*</label>
                <input type="text" class="form-control" id="inputName" name="lastname" value="${user.getLastname()}" onblur="checkNameUser()">
                <div class="col-6">
                    <div class="span-error-div"><span class="span-error4" id="errorNameUser"></span></div>
                </div>
            </div>

            <div class="col-6">
                <label for="inputFirstname" class="form-label text-black">Prénom*</label>
                <input type="text" class="form-control" id="inputFirstname" name="firstname" value="${user.getFirstname()}" onblur="checkFirstnameUser()">
                <div class="col-6">
                    <div class="span-error-div"><span class="span-error4" id="errorFirstnameUser"></span></div>
                </div>
            </div>
        </div>

        <div class="row mx-2">
            <div class="col-6">
                <label for="inputPhone" class="form-label text-black">Téléphone*</label>
                <input type="text" class="form-control" id="inputPhone" name="phone" value="${user.getPhone()}" onblur="checkPhoneUser()">
                <div class="col-6">
                    <div class="span-error-div"><span class="span-error4" id="errorPhoneUser"></span></div>
                </div>
            </div>

            <div class="col-6">
                <label for="datepicker" class="form-label text-black">Date de naissance</label>
                <input type="text" class="form-control" id="datepicker" name="dateOfBirth" value="${user.getDateOfBirthFormated()}">
                <%--                name="InputDateOfBirthUser"--%>
                <div class="col-6">
                    <div class="span-error-div"><span class="span-error4" id="errorDateOfBirthUser"></span></div>
                </div>
            </div>
        </div>

        <div class="row mx-2">
            <div class="col-12">
                <label for="inputStreet" class="form-label text-black">Rue*</label>
                <input type="text" class="form-control" id="inputStreet" name="Address.street" value="${user.getAddress().getStreet()}" onblur="checkStreet()">
                <div class="col-12">
                    <div class="span-error-div"><span class="span-error4" id="errorStreet"></span></div>
                </div>
            </div>
        </div>


        <div class="row mx-2">
            <div class="col-6">
                <label for="inputNumber" class="form-label text-black">Numéro*</label>
                <input type="text" class="form-control" id="inputNumber" name="Address.number" value="${user.getAddress().getNumber()}" onblur="checkNumber()">
                <div class="span-error-div"><span class="span-error4" id="errorNumber"></span></div>
            </div>

            <div class="col-6">
                <label for="InputBox" class="form-label text-black">Boîte</label>
                <input type="text" class="form-control" id="InputBox" name="Address.box" value="${user.getAddress().getBox()}" >
                <div class="span-error-div"><span class="span-error4" id="errorBox"></span></div>
            </div>
        </div>


        <div class="row mx-2">
            <div class="col-6">
                <label for="inputPostalCode" class="form-label text-black">Code postal*</label>
                <input type="text" class="form-control" id="inputPostalCode" name="Address.City.postalCode" value="${user.getAddress().getCity().getPostalCode()}" onblur="checkPostalCode()">
                <div class="col-6">
                    <div class="span-error-div"><span class="span-error4" id="errorPostalCode"></span></div>
                </div>
            </div>

            <div class="col-6">
                <label for="inputCityName" class="form-label text-black">Ville*</label>
                <input type="text" class="form-control" id="inputCityName" name="Address.City.cityName" value="${user.getAddress().getCity().getCityName()}" onblur="checkCity()">
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
                <input id="country_selector" type="text" class="form-control" name="Address.City.Country.countryName" value="${user.getAddress().getCity().getCountry().getCountryName()}">
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