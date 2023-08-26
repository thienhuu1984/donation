<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
    
<!DOCTYPE html>
<html>
<head>
	
	<%@ include file="siteContent/head-tag-content.jsp" %>
	
	<title>Quyên góp từ thiện - Đăng nhập</title>
	
</head>
<body>
	
	<%@ include file="siteContent/n-topPane.jsp" %>
	
	<c:if test="${not empty request}">
		<div class="message text-warning bg-dark font-italic p-3 " >${request.requestLogin}</div>
	</c:if>
	
	<div class="login d-flex  justify-content-center mt-4">
		
			
		<div class="form align-self-start  w-40 d-flex flex-column">
			
			<h2 class="header">Đăng nhập</h2>
			
			<c:if test="${not empty errors}">
				<div class="message text-danger font-italic p-3" >${errors.errorLogin}</div>
			</c:if>
			
			<form:form method="POST" modelAttribute="login" action="loginPOST"  >
				
				<div class="input">
					<span>Tên đăng nhập: </span>
					<form:input path="username" class="form-control"/>
				</div>
				
				<div class="input">
					<span>Mật khẩu: </span>
					<form:input path="password" type="password" class="form-control"/>
				</div>
				
				<input type="submit" value="Đăng nhập"  class="btn btn-primary mt-3" />
			</form:form>
			
			<div class="register mt-2">
				Bạn chưa có tài khoản? <a href="registration">Đăng ký</a>
			</div>
			
		</div>
	</div>
</body>
</html>