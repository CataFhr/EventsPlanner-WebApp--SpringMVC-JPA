<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Event</title>
</head>

<style>
.error {
	color: red
}
</style>

<body>
	<h1 align="left">Add New Event</h1>
	<form action="/Proiect-SpringMVC/process-addevent/${idUser}"
		method="post">
		<table>
			<tr>
				<td>Name :</td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td>Date :</td>
				<td><input type="date" name="date"></td>
			</tr>
			<tr>
				<td>Street :</td>
				<td><input type="text" name="street"></td>
			</tr>
			<tr>
				<td>Number :</td>
				<td><input type="text" name="number"></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" name="add" value="Add"></td>
			</tr>
		</table>
	</form>
	<br>
	<div align="left" class="error">${message}</div>
</body>
</html>