<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login page</title>
</head>
<body>
	<h2>Login here</h2>
	<sf:form action = "login" method = "Post" modelAttribute = "blank_user_login">
		Username: <sf:input path = "username" type = "text" />
		Password: <sf:input path = "password" type = "password" />
		<input type = "submit" value = "login!"/>
	</sf:form>
	
	<span style="color: red;">${fail_msg}</span>

</body>
</html>