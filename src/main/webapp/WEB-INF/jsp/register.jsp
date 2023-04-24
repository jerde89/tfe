<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>


<html>
<head>
    <%@include file="head.jsp" %>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <%--    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@fortawesome/fontawesome-free@6.2.0/css/fontawesome.min.css" integrity="sha384-z4tVnCr80ZcL0iufVdGQSUzNvJsKjEtqYZjiQrrYKlpGow+btDHDfQWkFjoaz/Zr" crossorigin="anonymous">--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/register.css">
<%--    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/countrySelect.css">--%>
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
    <form class="shadow p-4 bg-white rounded" name="registerForm" id="registerForm"
          method="post" action="${pageContext.request.contextPath}/register/addUser"
          onsubmit="return validateRegisterForm('register')">

        <input type="hidden"
               name="${_csrf.parameterName}"
               value="${_csrf.token}"/>

        <div class="mt-2 mb-5 text-center">
            <h3 class="text-black">Inscription</h3>
            <h6 class="text-black-50">Veuillez entrer les champs obligatoires (*)</h6>
        </div>

        <div class="row mx-2">
            <div class="col-12">
                <label for="inputEmail" class="form-label text-black">Email (identifiant)*</label>
                <input type="email" class="form-control" id="inputEmail" name="email" placeholder="Email" onblur="checkEmailUser()">
                <div class="col-12">
                    <div class="span-error-div"><span class="span-error4" id="errorEmailUser"></span></div>
                </div>
            </div>
        </div>

        <div class="row mx-2">
            <div class="col-12">
                <label for="inputPassword" class="form-label text-black">Mot de passe*</label>
                <div class="input-group" id="show_hide_password">
                    <input type="password" class="form-control" id="inputPassword" name="passwordNoConfirm" placeholder="Mot de passe" onblur="checkPassword()">
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
                <label for="inputConfirmPassword" class="form-label text-black">Confirmer le mot de passe*</label>
                <div class="input-group" id="show_hide_password_confirm">
                    <input type="password" class="form-control" id="inputConfirmPassword" name="password" placeholder="Entrez le Mot de passe de nouveau" onblur="checkConfirmPassword()">
                    <div class="input-group-addon">
                        <a href=""><i class="fa fa-eye-slash" aria-hidden="true"></i></a>
                    </div>
                </div>
                <div class="col-12">
                    <div class="span-error-div"> <span class="span-error4" id="errorConfirmPassword"></span></div>
                </div>
            </div>
        </div>

        <hr>
        <div class="row mx-2">
            <div class="col-6">
                <label for="inputName" class="form-label text-black">Nom*</label>
                <input type="text" class="form-control" id="inputName" name="lastname" placeholder="Nom" onblur="checkNameUser()">
                <div class="col-6">
                    <div class="span-error-div"><span class="span-error4" id="errorNameUser"></span></div>
                </div>
            </div>

            <div class="col-6">
                <label for="inputFirstname" class="form-label text-black">Prénom*</label>
                <input type="text" class="form-control" id="inputFirstname" name="firstname" placeholder="Prénom" onblur="checkFirstnameUser()">
                <div class="col-6">
                    <div class="span-error-div"><span class="span-error4" id="errorFirstnameUser"></span></div>
                </div>
            </div>
        </div>

        <div class="row mx-2">
            <div class="col-6">
                <label for="inputPhone" class="form-label text-black">Téléphone*</label>
                <input type="text" class="form-control" id="inputPhone" name="phone" placeholder="Téléphone" onblur="checkPhoneUser()">
                <div class="col-6">
                    <div class="span-error-div"><span class="span-error4" id="errorPhoneUser"></span></div>
                </div>
            </div>

            <div class="col-6">
                <label for="datepicker" class="form-label text-black">Date de naissance</label>
                <input type="text" class="form-control" id="datepicker" name="dateOfBirth" placeholder="01/01/2000">
                <%--                name="InputDateOfBirthUser"--%>
                <div class="col-6">
                    <div class="span-error-div"><span class="span-error4" id="errorDateOfBirthUser"></span></div>
                </div>
            </div>
        </div>

        <div class="row mx-2">
            <div class="col-12">
                <label for="inputStreet" class="form-label text-black">Rue*</label>
                <input type="text" class="form-control" id="inputStreet" name="Address.street" placeholder="Rue" onblur="checkStreet()">
                <div class="col-12">
                    <div class="span-error-div"><span class="span-error4" id="errorStreet"></span></div>
                </div>
            </div>
        </div>


        <div class="row mx-2">
            <div class="col-6">
                <label for="inputNumber" class="form-label text-black">Numéro*</label>
                <input type="text" class="form-control" id="inputNumber" name="Address.number" placeholder="Numéro" onblur="checkNumber()">
                <div class="span-error-div"><span class="span-error4" id="errorNumber"></span></div>
            </div>

            <div class="col-6">
                <label for="InputBox" class="form-label text-black">Boîte</label>
                <input type="text" class="form-control" id="InputBox" name="Address.box" placeholder="Boîte" >
                <div class="span-error-div"><span class="span-error4" id="errorBox"></span></div>
            </div>
        </div>


<%--        <div class="row mx-2">--%>
<%--            <div class="col-6">--%>
<%--                <label for="inputPostalCode" class="form-label text-black">Code postal*</label>--%>
<%--                <input type="text" class="form-control" id="inputPostalCode" name="Address.City.postalCode" placeholder="Code postal" onblur="checkPostalCode()">--%>
<%--                <div class="col-6">--%>
<%--                    <div class="span-error-div"><span class="span-error4" id="errorPostalCode"></span></div>--%>
<%--                </div>--%>
<%--            </div>--%>

         <div class="row mx-2">
                <div class="col-6">
                    <label class="form-label text-black">Ville*</label>
                    <select  class="form-control" name="Address.City.idCity" id="idCity">

                        <option selected value=""></option>
                        <%--                        var="category => une nouvelle variable appellée category--%>
                        <%--                        Items => reçu du controller ProdcutController de la focntion showCategoryList--%>
                        <c:forEach var="city" items="${cityList}">
                            <option value="${city.idCity}"><c:out value="${city.postalCode} - ${city.cityName}"/></option>
                        </c:forEach>
                    </select>
                    <div class="span-error-div"><span class="span-error4" id="errorTva"></span></div>

                </div>

                <div class="col-6">
                        <label class="form-label text-black">Pays (Uniquement Belgique pour le moment)</label>
                        <input type="email" class="form-control"  value="Belgique"   readonly>
                </div>
           </div>


<%--            <div class="col-6">--%>
<%--                <label for="inputCityName" class="form-label text-black">Ville*</label>--%>
<%--                <input type="text" class="form-control" id="inputCityName" name="Address.City.cityName" placeholder="Ville*" onblur="checkCity()">--%>
<%--                <div class="col-6">--%>
<%--                    <div class="span-error-div">--%>
<%--                        <span class="span-error4" id="errorCityName"></span>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
        </div>

        <%-- si plusieurs pays utiliserProcédure Jquery pour afficher les pays en liste déroulante   https://www.jqueryscript.net/form/country-picker-flags.html--%>
<%--        <div class="row mx-2">--%>
<%--            <div class="col-12">--%>
<%--                <label class="form-label text-black">Pays*</label>--%>
<%--                <input id="country_selector" type="text" class="form-control" name="Address.City.Country.countryName">--%>
<%--            </div>--%>
<%--        </div>--%>



        <div class="form-group" style="text-align:center">
            <button id="submitBtn" type="submit" class="form-control btn btn-primary rounded submit px-3">S'inscrire
            </button>
        </div>

    </form>
</div>
<%--</div>--%>

<%@include file="footer.jsp" %>

</body>



</html>
