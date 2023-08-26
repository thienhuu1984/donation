<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    
    
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Assignment 1 - Từ Thiên Hữu</title>
</head>
<body>

	<h1>Từ Thiên Hữu - Assignment1 - Admin page</h1>

	<h2>CHỜ TÍ ĐANG CODE</h2>
	
	<div>
		<form:form action="inputTest" method="GET" acceptCharset="UTF-8">
			Viết tiếng Việt: 
			<input type="text" name="testValue" />
			<input type="submit" value="Send" /> 
		</form:form>
	</div>
	
	<ul>
		<c:forEach var="text" items="${tests}">
		
			<li>${text.text}</li>				
			
		</c:forEach>
	</ul>
</body>
</html>