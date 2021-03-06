<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Issue Management</title>
</head>
<body>
	<h4>Issue details:<br>
		<c:forEach items="${details}" var="individual_detail">
		${individual_detail}
			<br>
		</c:forEach>
	</h4>
	<h4>What do you want to do...</h4>
	
	<a href="http://localhost:8088/IssueTrackingSystem/issue/reject">reject this issue</a>
	<a href="http://localhost:8088/IssueTrackingSystem/issue/request">request a reassignment</a>
	<a href="http://localhost:8088/IssueTrackingSystem/issue/resolve">mark it resolved</a>
	<a href="http://localhost:8088/IssueTrackingSystem/issue/addDetailDepartAdmin">add a new detail</a>
	<br>
	<a href="http://localhost:8088/IssueTrackingSystem/dashboard/depadmin">back to dashboard</a>
	
</body>
</html>