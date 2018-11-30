<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Issue Detail</title>
</head>
<body>
	<h1>Detail page</h1>
	<a href="/IssueTrackingSystem/logout">Logout</a>
	<table>
		<thead>
			<tr>
				<td>Issue Detail ID</td>
				<td>Issue Detail Content</td>
				<td>Issue Detail Create Date</td>
				<td>Issue Detail Status</td>
				<td>Issue Detail Creator</td>
			</tr>
		</thead>
		<c:forEach items="${details}" var="item">
			<tr>
				<td>${item.id}</td>
				<td>${item.content}</td>
				<td>${item.createDate.time}</td>
				<td>${item.status}</td>
				<td>${item.user.username}</td>
			</tr>
		</c:forEach>
	</table>
	<a href="/IssueTrackingSystem/dashboard/${fn:toLowerCase(userType)}">
		Back to dashboard</a>
</body>
</html>