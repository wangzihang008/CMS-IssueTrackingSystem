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
	<a href="http://localhost:8088/IssueTrackingSystem/issue/update/addNewIssueDetailDepartAdmin">add a new detail</a>
	<%-- <sf:form action = "http://localhost:8088/IssueTrackingSystem/issue/update/depadmin" method = "Post" modelAttribute = "new_issue_detail">
		<sf:input path = "content" type = "text" />
		<input type = "submit" value = "Add a new detail"/>
	</sf:form> --%>
</body>
</html>