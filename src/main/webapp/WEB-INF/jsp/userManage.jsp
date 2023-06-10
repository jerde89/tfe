<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <%@include file="head.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/userManage.css">
    <script src="${pageContext.request.contextPath}/js/userManage.js"></script>
    <script type="text/javascript">
        var pageContextPath = "${pageContext.request.contextPath}";
        var isAdmin = false;
        <sec:authorize  access="hasAnyAuthority('ADMIN')">
        isAdmin = true;
        </sec:authorize>
    </script>
</head>
<body>

<%@include file="navbar.jsp" %>

<div class="container">
    <h1 style="text-align: center">Gestion des utilisateurs</h1>
    <div class="tab-content">
            <div class="tab-pane active m-4" id="tab-table">
                <table class="table table-striped table-bordered" id="user_Manage_ajax" cellspacing="0"
                       width="100%">
                    <thead>
                    <tr>
                        <th>Nom</th>
                        <th>Prénom</th>
                        <th>Email</th>
                        <th>Téléphone</th>
                        <th>Rue</th>
                        <th>Numéro</th>
                        <th>Box</th>
                        <th>Ville</th>
                        <th>Action</th>

                    </tr>
                    </thead>
                </table>
            </div>
        </div>
</div>

<div id="formUserInfo" class="popupDialog container">

    <input type="hidden" id='_csrf' name="${_csrf.parameterName}" value="${_csrf.token}">

    <input type="hidden" id="idUser">

    <div class="row ">
            <div class="col-6  px-5 py-2">
                <label class="form-label text-black">Nom :</label>
                <input type="text" class="form-control" id="lastname" name="lastname" onblur="checkLastname()"/>
                <div class="col-6">
                    <div class="span-error-div"><span class="span-error4" id="errorLastname"></span></div>
                </div>
            </div>
            <div class="col-6 px-5 py-2">
                <label class="form-label text-black">Prénom :</label>
                <input type="text" class="form-control" id="firstname" name="firstname" onblur="checkFirstnameUser()"/>
                <div class="col-6">
                    <div class="span-error-div"><span class="span-error4" id="errorFirstnameUser"></span></div>
                </div>
            </div>
    </div>

    <div class="row">

        <div class="col-6 px-5 py-2">
            <label class="form-label text-black">Email :</label>
            <input type="email" class="form-control" id="email" name="email" readonly="readonly"/>
        </div>
        <div class="col-6 px-5 py-2">
            <label class="form-label text-black">Téléphonne :</label>
            <input type="text" class="form-control" id="phone" name="phone" onblur="checkPhoneUser()"/>
            <div class="col-6">
                <div class="span-error-div"><span class="span-error4" id="errorPhoneUser"></span></div>
            </div>
        </div>

    </div>

    <div class="row">
        <div class= "col-12 px-5 py-2">
            <label class="form-label text-black">Rue :</label>
            <input type="text" class="form-control" id="street" name="street" onblur="checkStreet()"/>
            <div class="col-6">
                <div class="span-error-div"><span class="span-error4" id="errorStreet"></span></div>
            </div>
        </div>
    </div>

    <div class="row">
             <div class= "col-6 px-5 py-2">
                <label class="form-label text-black">Numéro :</label>
                <input type="text" class="form-control" id="number" name="number" onblur="checkNumber()"/>
                 <div class="col-6">
                     <div class="span-error-div"><span class="span-error4" id="errorNumber"></span></div>
                 </div>
            </div>
            <div class= "col-6 px-5 py-2">
                <label class="form-label text-black">Boîte :</label>
                <input type="text" class="form-control" id="box" name="box"/>
            </div>
    </div>

   <div class="row">
        <div class= "col-12 px-5 py-2">
            <div>
                <label class="form-label text-black">Villetest*</label>
                <select  class="form-control" name="Address.City.idCity" id="idCity">
                    <c:forEach var="city" items="${cityList}">
                        <c:choose>
                            <c:when test="${user.address.city.idCity eq city.idCity}">
                                <option  value="${city.idCity}" selected>${city.postalCode} - ${city.cityName}</option>

                            </c:when>
                            <c:otherwise>
                                <option value=${city.idCity}>${city.postalCode} - ${city.cityName}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
                <div class="span-error-div"><span class="span-error4" id="errorTva"></span></div>
            </div>
    </div>
    </div>

    <div class="row">
          <div class= "col-6 px-5 py-2">
                <label class="form-label text-black">Pays :</label>
                <input type="text" class="form-control" id="countryName" name="countryName" readonly="readonly"/>
          </div>

          <div class= "col-6 px-5 py-2">
                <label class="form-label text-black">Rôle :</label>
                <select  class="form-control" name="role" id="role">
                    <c:forEach var="roletest" items="${roleList}">
                        <option value="${roletest.idRole}"><c:out value="${roletest.role}"/></option>
                    </c:forEach>
                </select>
          </div>
    </div>

        <div class="row">
            <div class="col-12 px-5 py-2">
                <label class="form-label text-black">Actif : </label>
                <input type="checkbox" id="enabled" name="enabled"/>
            </div>
        </div>


</div>


<input type="hidden" id='_csrf' name="csrf" value="${_csrf.token}">

</body>


<c:import url="footer.jsp"/>

</html>
