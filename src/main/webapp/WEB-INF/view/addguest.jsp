<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Guest</title>
</head>

<style>
.error {
	color: red
}
</style>

<body>
	<h1 align="left">Add New Guest</h1>
	<form action="/Proiect-SpringMVC/process-addguest/${idEv}"
		method="post">
		<table>
			<tr>
				<td>Name :</td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td>Email :</td>
				<td><input type="text" name="email"></td>
			</tr>
			<tr>
				<td>Phone1 :</td>
				<td><input type="text" name="phoneNo"></td>
			</tr>
			<tr>
				<td>Phone2 :</td>
				<td><input type="text" name="phoneNo"></td>
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