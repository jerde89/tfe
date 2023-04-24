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
    <div class="my-3">
        <div class="col-12">
            <label class="form-label text-black">Nom :</label>
            <input type="text" class="form-control" id="lastname" name="lastname"/>
        </div>
    </div>

    <div class="my-3">
        <div class="col-12">
            <label class="form-label text-black">Prénom :</label>
            <input type="text" class="form-control" id="firstname" name="firstname"/>
        </div>
    </div>

    <div class="my-3">
        <div class="col-12">
            <label class="form-label text-black">Email :</label>
            <input type="email" class="form-control" id="email" name="email"/>
        </div>
    </div>

    <div class="my-3">
        <div class="col-12">
            <label class="form-label text-black">Téléphonne :</label>
            <input type="text" class="form-control" id="phone" name="phone"/>
        </div>
    </div>

    <div class="my-3">
        <div class="col-12">
            <label class="form-label text-black">Rue :</label>
            <input type="text" class="form-control" id="street" name="street"/>
        </div>
    </div>

    <div class="row">


            <div class="col-6">
                <label class="form-label text-black">Numéro :</label>
                <input type="text" class="form-control" id="number" name="number"/>
            </div>
            <div class="col-6">
                <label class="form-label text-black">Boîte :</label>
                <input type="text" class="form-control" id="box" name="box"/>
            </div>


    </div>

    <div class="my-3">
        <div class="col-12">
            <label class="form-label text-black">Code postal :</label>
            <input type="text" class="form-control" id="postalCode" name="postalCode"/>
        </div>
    </div>

    <div class="my-3">
        <div class="col-12">
            <label class="form-label text-black">Ville :</label>
            <input type="text" class="form-control" id="cityName" name="cityName"/>
        </div>
    </div>

    <div class="my-3">
        <div class="col-12">
            <label class="form-label text-black">Pays :</label>
            <input type="text" class="form-control" id="countryName" name="countryName"/>
        </div>
    </div>

    <div class="my-3">
        <div class="col-12">
            <label class="form-label text-black">Rôle :</label>
            <select  class="form-control" name="role" id="role">
<%--                <option value=""></option>--%>
                <%--                        var="role => une nouvelle variable appellée role--%>
                <%--                        Items => reçu du controller UserManageController de la focntion showUserManage--%>
                <c:forEach var="roletest" items="${roleList}">
                    <option value="${roletest.idRole}"><c:out value="${roletest.role}"/></option>
                </c:forEach>
            </select>
        </div>
    </div>

    <div class="my-3">
        <div class="">
            <label class="form-label text-black">Actif : </label>
            <input type="checkbox" id="enabled" name="enabled"/>
        </div>
    </div>


</div>
<input type="hidden" id='_csrf' name="csrf" value="${_csrf.token}">

</body>


<c:import url="footer.jsp"/>

</html>
