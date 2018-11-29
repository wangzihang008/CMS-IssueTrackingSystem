<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Issue Detail</title>
</head>
<body>
	<h1>Detail page</h1>
	<h2>${details}</h2>
	<a href="/IssueTrackingSystem/dashboard/${fn:toLowerCase(userType)}">
		Back to dashboard</a>
</body>
</html>