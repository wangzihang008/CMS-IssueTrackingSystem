<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dashboard</title>
</head>
<body>
	
	<h1>Welcome ${active_user}</h1>
	
	<h3>
		Your issues to deal with: <br>
		<c:forEach items="${issues}" var="individual_issue">
		${individual_issue}
			<br>
		</c:forEach>
	</h3>

	<sf:form action = "/IssueTrackingSystem/dashboard/depadmin" method = "POST" modelAttribute="selected_issue">
		<select name="title">
			<option value="">---select---</option>
			<c:forEach items="${issues}" var="individual_issue">
				<option value="${individual_issue}">${individual_issue}</option>
			</c:forEach>
		</select>
		<input type="submit" value="manage this issue">
	</sf:form>
	
</body>
</html>