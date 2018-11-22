<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>General Admin Dashboard</title>
</head>
<body>
	<div>
		<a href="/issue/register/">Register New Issue</a>
	</div>
	<div>
		<h4>All Issues</h4>
		<c:if test="${issues == null}">
			<p>There is no issue</p>
		</c:if>
		<table>
			<c:forEach items="${issues}">
				<tr>
					<td>${issues.id}</td>
					<td>${issues.title}</td>
					<td>${issues.createDate}</td>
					<td>${issues.status}</td>
					<td><a href="issue/detail/${issues.id}">Details</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>