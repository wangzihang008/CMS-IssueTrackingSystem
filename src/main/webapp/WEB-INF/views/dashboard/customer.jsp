<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<table>
		<tr>
			<td>issue ID</td>
			<td>issue title</td>
			<td>issue status</td>
			<td>priority</td>
			<td>see detail</td>
			<td>approve this issue</td>
		</tr>


		<c:forEach var="issue" items="${issueList}">
			<tr>
				<td>${issue.id}</td>
				<td>${issue.title}</td>
				<td>${issue.status}</td>
				<td>${issue.priority}</td>
				<td>
					<form action="issue/update/approve/${issue.id}">
						<input type="submit" value="issueDetail">
					</form>
				</td>

				<td>
					<form action="issue/update/approved/${issue.id}">
						<input type="submit" value="approve">
					</form>
				</td> ${error}
			</tr>
		</c:forEach>
	</table>
</body>
</html>