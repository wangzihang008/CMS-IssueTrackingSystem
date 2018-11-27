<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to Issue Tracking System</title>
</head>
<body>

	<h2>Welcome to Issue Tracking System</h2>
	<c:if test="${errorMessage != null}">
		<h4>${errorMessage}</h4>
	</c:if>
	<a href="login">go to login</a>
	<h1></h1>
	<a href="register">go to register</a>
</body>
</html>