<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Login</title>
</head>
<body>
<jsp:useBean id="user" class="bean.User" scope="session"></jsp:useBean>
<form action="/Shop/Controller" method="post">
<div>
<input type="hidden" name="action" value="dologin">
Username:<input type="text" name="username"><br>
Password:<input type="text" name="password">
</div>
<div>
<input type="submit" value="Login">
</div>
</form>
</body>
</html>