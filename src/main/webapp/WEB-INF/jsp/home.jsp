<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<html>
<head>
	<title>Fournil homa page</title></head>
<body>
	<h2>Home page</h2>
	some content 
	
	Hello <security:authentication property="principal.username"/>
	R�les: <security:authentication property="principal.authorities"/>	
	<hr>
	<security:authorize access="hasRole('MANAGER')"> 
		<p> 
			<a href="${pageContext.request.contextPath}/manager">Manager</a>
		</p>
	</security:authorize>
	<p> 
		<a href="">Admin</a>
	</p>
	
	<a href="${pageContext.request.contextPath}/customer">Customer</a>
	<hr>
	<form:form action="${pageContext.request.contextPath}/logout" method="POST" class="form-horizontal">
		<!-- Login/Submit Button -->
		<div style="margin-top: 10px" class="form-group">
			<div class="col-sm-6 controls">
				<button type="submit" class="btn btn-success">Logout</button>
			</div>
		</div>
	</form:form>
</body>

</html>
