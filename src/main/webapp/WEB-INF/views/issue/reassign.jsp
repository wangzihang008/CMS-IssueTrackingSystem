<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reassign</title>
</head>
<body>
	<form action="/IssueTrackingSystem/issue/reassign" method="POST">
		<select name="department">
			<c:forEach items="${allDepartment}" var="item">
				<option value="${item['id']}"><c:out value="${item.name}" /></option>
			</c:forEach>
		</select> <input type="submit" value="submit" />
	</form>
</body>
</html>