<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
</head>

<style>
.error {
	color: red
}
</style>

<body>
	<h1 align="center">Welcome!</h1>
	<hr />
	<form action="process-loginpage" method="post">
		<div align="center">
			<table>
				<tr>
					<td>Username :</td>
					<td><input type="text" name="username"></td>
				</tr>
				<tr>
					<td>Password :</td>
					<td><input type="password" name="password"></td>
				</tr>
				<tr></tr>
				<tr></tr>
				<tr>
					<td align="right"><input type="submit" name="login" value="Login"></td>
					<td align="center"><input type="submit" name="register" value="Register"></td>
				</tr>
			</table>
		</div>
	</form>
	<br>
	<div align="center" class="error">${message}</div>
</body>

</html>