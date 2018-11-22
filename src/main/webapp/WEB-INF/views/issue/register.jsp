<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Issue Register</title>
</head>
<body>
	<c:if test="${errorMessage != null}">
		<h4>${errorMessage}</h4>
	</c:if>
	<h2>Register</h2>
	<sf:form action="issue/register" method="POST"
		modelAttribute="newIssueDetail">
		<div>
			<p>TITLE:</p>
			<input type="text" placeholder="Input Issue Title"
				name="title" />
		</div>
		<div>
			<p>DESCRIPTION:</p>
			<sf:input type="text" placeholder="Input Issue Detail" path="content" />
		</div>
		<div>
			<p>DEPARTMENT:</p>
			<select name="priority">
				<c:forEach items="${allDepartment}" var="item">
			         <option value="${item.id}"><c:out value="${item}" /></option>
				</c:forEach>
			</select>
			<input type="text" placeholder="Input Issue Department"
				name="department" />
		</div>
		<div>
			<p>PRIORITY:</p>
			<select name="priority">
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
			</select>
		</div>
		<input type="submit" value="SUBMIT">
	</sf:form>
</body>
</html>