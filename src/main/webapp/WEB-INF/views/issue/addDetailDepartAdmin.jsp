<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Detail</title>
</head>
<body>
	<h1>Are you sure to add a new detail to this issue?</h1>
	<sf:form action = "/IssueTrackingSystem/issue/addDetailDepartAdmin" method = "POST" modelAttribute="blank_detail">
		Type detail here: <sf:input path = "content" type = "text" />
		<input type="submit" value="add a new detail!">
	</sf:form>
	<a href="http://localhost:8088/IssueTrackingSystem/dashboard/depadmin">back to dashboard</a>
	
</body>
</html>