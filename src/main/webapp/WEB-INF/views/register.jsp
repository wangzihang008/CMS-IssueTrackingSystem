<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 
 <%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User register page</title>
</head>
<body>
	<h2>Register here</h2>
	<sf:form action = "register" method = "Post" modelAttribute = "user_register">
		Username: <sf:input path = "username" type = "text" />
		Password: <sf:input path = "password" type = "password" />
		<input type = "submit" value = "register!"/>
	</sf:form>
	
	<span style="color: red;">${fail_msg}</span>
</body>
</html>