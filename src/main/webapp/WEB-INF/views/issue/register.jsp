<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h2>Register</h2>
	<sf:form action="issue/register" method="POST"
		modelAttribute="newIssue">
		<div>
			<p>TITLE:</p>
			<sf:input type="text" placeholder="Input Your Issue Title"
				path="title" />
		</div>
		<div>
			<p>EMAIL:</p>
			<sf:input type="text" placeholder="Input Your Email" path="email" />
		</div>
		<div>
			<p>PASSWORD:</p>
			<sf:input type="password" placeholder="Input Your Password"
				path="password" />
		</div>
		<input type="submit" value="SUBMIT">
	</sf:form>
</body>
</html>