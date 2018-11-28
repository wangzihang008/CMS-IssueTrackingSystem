<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reject</title>
</head>
<body>
	<h1>Are you sure to reject this issue?</h1>
	<sf:form action = "/IssueTrackingSystem/issue/reject" method = "POST">
		<select name="selection">
			<option value=-1>---select---</option>
			<option value=1>yes</option>
			<option value=0>no</option>
		</select>
		<input type="submit" value="select">
	</sf:form>
	
	<span style="color: red;">${msg}</span>
	<br>
	<a href="http://localhost:8088/IssueTrackingSystem/dashboard/depadmin">back to dashboard</a>
	
</body>
</html>