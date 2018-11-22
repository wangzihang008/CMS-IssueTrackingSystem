<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
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
</body>
</html>