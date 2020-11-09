<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Guests</title>
</head>

<style>
.error {
	color: red
}
</style>

<body>
	<div align="left">
		<a href="/Proiect-SpringMVC/backAtEvents/${idEv}">Back At Events</a>
	</div>
	<div align="center">
		<a href="/Proiect-SpringMVC/addGuest/${idEv}">Add New Guest</a>
	</div>
	<hr />
	<div id="left" style="float: left; width: 50%;">
		EVENT GUESTS:
		<ul>
			<c:forEach var="listValue" items="${list}">
				<li>${listValue}</li>
			</c:forEach>
		</ul>
	</div>

</body>
</html>