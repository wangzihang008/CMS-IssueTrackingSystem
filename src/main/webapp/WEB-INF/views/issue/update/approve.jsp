<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
			<td>content</td>
			<td>create Date</td>
			<td>status</td>
		</tr>
		<c:forEach var="issue" items="${List}">
			<tr>
				<td>${issue.content}</td>
				<td>${issue.createDate}</td>
				<td>${issue.status}</td>
			</tr>
		</c:forEach>
	</table>
	<hr>
	<form action="/dashboard/customer">
		<input type="sumbit" value="issueList">
	</form>

</body>
</html>