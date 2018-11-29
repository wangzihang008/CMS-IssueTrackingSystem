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
		<h4>All Issues</h4>
		<c:if test="${issues == null}">
			<p>There is no issue</p>
		</c:if>
		<table>
			<thead>
				<tr>
					<td>Issue ID</td>
					<td>Issue Title</td>
					<td>Issue Create Date</td>
					<td>Issue Status</td>
				</tr>
			</thead>
			<c:forEach items="${issues}" var="item">
				<tr>
					<td>${item.id}</td>
					<td>${item.title}</td>
					<td>${item.createDate.time}</td>
					<td>${item.status}</td>
					<td><a href="/IssueTrackingSystem/issue/detail/${item.id}">Details</a></td>
					<td><c:if test="${item.status eq 'REQUESTED'}">
							<a href="/IssueTrackingSystem/issue/reassign/${item.id}">Reassign</a>
						</c:if></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<span style="color: red;">${reassignMsg}</span>
</body>
</html>