<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<html>
<head>
<meta charset="ISO-8859-1">
<title>Events</title>
</head>

<style>
.error {
	color: red
}
</style>


<body>
	<h1 align="center">Events list</h1>
	<div align="right">
		<a href="/Proiect-SpringMVC/logout">LOG OUT</a>
	</div>
	<hr />
	<div id="container" style="width: 100%;">
		<div id="left" style="float: left; width: 50%;">
			MY EVENTS
			<table border="2" width="70%" cellpadding="2">
				<tr>
					<th>Id</th>
					<th>Name</th>
					<th>Date</th>
					<th>Street</th>
					<th>Number</th>
					<th>Edit</th>
					<th>Delete</th>
					<th>Guests</th>
				</tr>
				<c:forEach var="ev" items="${list1}">
					<tr>
						<td>${ev.id}</td>
						<td>${ev.name}</td>
						<td>${ev.date}</td>
						<td>${ev.adress.street}</td>
						<td>${ev.adress.number}</td>
						<td><a href="/Proiect-SpringMVC/editevent/${ev.id}">Edit</a></td>
						<td><a href="/Proiect-SpringMVC/deleteevent/${ev.id}">Delete</a></td>
						<td><a href="/Proiect-SpringMVC/viewGuest/${ev.id}">View
								Guests</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>

		<div id="right" style="float: right; width: 50%;">
			ALL EVENTS
			<ul>
				<c:forEach var="listValue" items="${list2}">
					<li>${listValue}</li>
				</c:forEach>
			</ul>
		</div>
	</div>
	<br />
	<a href="/Proiect-SpringMVC/addEv/${idUser}">Add New Event</a>
</body>
</html>