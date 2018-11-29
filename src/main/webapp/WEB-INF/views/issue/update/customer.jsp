<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New Update</title>
</head>
<body>
	<h2>New Update</h2>
	<sf:form action="${issueId}" method="POST"
		modelAttribute="newIssueDetail">
		<sf:textarea path="content" placeholder="Input New Update Content"/>
		<input type="submit" value="Submit">
	</sf:form>
</body>
</html>