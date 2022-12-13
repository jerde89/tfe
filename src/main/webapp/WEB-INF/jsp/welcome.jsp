<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <%@include file="head.jsp" %>

</head>
<body>

<%@include file="navbar.jsp" %>


<h1 style="text-align: center">Bienvenue au Fournil des gonceries</h1>

<div class="col ms-auto text-center pt-5">
    <div class="zoom">
        <div class="zoom :hover">
            <img src="${pageContext.request.contextPath}/image/MaitreBoulanger.png" alt="Image des gérants"  width="20%" alt="Profil">
        </div>
    </div>
</div>


</body>

<c:import url="footer.jsp"/>


</html>

