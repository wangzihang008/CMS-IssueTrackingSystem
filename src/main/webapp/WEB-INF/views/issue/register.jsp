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
	<c:if test="${message != null}">
		<h4>${message}</h4>
	</c:if>
	<h2>Register</h2>
	<%-- 	<sf:form action="register" method="POST" modelAttribute="registerIssue"> --%>
	<%-- 		<sf:label path="title">TITLE:</sf:label> --%>
	<%-- 		<sf:input type="text" placeholder="Input Issue Title" path="title" /> --%>
	<!-- 		<p>DESCRIPTION:</p> -->
	<!-- 		<input type="text" placeholder="Input Issue Detail" name="content" /> -->

	<%-- 		<sf:label path="department">DEPARTMENT:</sf:label> --%>
	<%-- 		<sf:select path="department"> --%>
	<%-- 			<c:forEach items="${allDepartment}" var="item"> --%>
	<%-- 				<option value="${item.id}"><c:out value="${item.name}" /></option> --%>
	<%-- 			</c:forEach> --%>
	<%-- 		</sf:select> --%>

	<%-- 		<sf:label path="priority">PRIORITY:</sf:label> --%>
	<%-- 		<sf:select name="priority" path="priority"> --%>
	<!-- 			<option value="1">1</option> -->
	<!-- 			<option value="2">2</option> -->
	<!-- 			<option value="3">3</option> -->
	<!-- 			<option value="4">4</option> -->
	<!-- 			<option value="5">5</option> -->
	<%-- 		</sf:select> --%>
	<!-- 		<input type="submit" value="SUBMIT"> -->
	<%-- 	</sf:form> --%>

	<form action="/IssueTrackingSystem/issue/register" method="POST">
		<input type="text" placeholder="Input Issue Title" name="title" /> 
		<input type="text" placeholder="Input Issue Content" name="content" /> 
		<select name="department">
			<c:forEach items="${allDepartment}" var="item">
				<option value="${item['id']}"><c:out value="${item.name}" /></option>
			</c:forEach>
		</select>
		<!-- 			type="text" placeholder="Input Issue Department" name="department" /> -->
		<!-- 		<input type="text" placeholder="Input Issue Priority" name="priority" /> -->
		<select name="priority">
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
		</select> <input type="submit" value="submit" />
	</form>
</body>
</html>