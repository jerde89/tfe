<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
	crossorigin="anonymous"></script>
</head>
<body>
	<div class="containerbh">
		<div class="row">
			<div class="col bg-success">
				<div class="bg-dark">HELLOOO</div>
				<div class="row">
					<div class="col bg-success text-center">dsfdsf</div>
					<div class="col bg-info text-center">sdfdsf</div>
				</div>
                <div class="bg-primary">Sporting</div>
			</div>
			<div class="col bg-warning">right</div>
		</div>
	</div>
	<table>
		<thead>
		<tr>
			<th>ISBN</th>
			<th>Name</th>
			<th>Author</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${customers}" var="customer">
			<tr>
				<td>${customer.lastName}</td>
				<td>${customer.lastName}</td>
				<td>${customer.email}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</body>
</html>
