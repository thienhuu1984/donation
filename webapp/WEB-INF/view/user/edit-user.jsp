<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    
    
<!DOCTYPE html>
<html>
<head>
	<title>Quyên góp từ thiện - Đăng ký thành viên mới</title>
	
	<%@ include file="siteContent/head-tag-content.jsp" %>
	
</head>
<body>
    	
    	<%@ include file="siteContent/n-topPane.jsp" %>
    	
    	<div class="container mt-5  d-flex flex-column align-items-center" >
    		
    		<h2 class="header text-center text-uppercase">Hoàn thiện thông tin</h2>
    		
    		<div class="contents mt-33 d-flex flex-column">
    		
    			<form:form modelAttribute="newUser" action="newUser" acceptCharset="UTF-8" method="POST">
    				
    				
					<div >
						<span>Chọn tên đăng nhập<span class="text-danger">*</span></span> 
						<form:input path="username" type="text"  class="form-control"/> 
					</div>
					<div>
						<span>Mật khẩu<span class="text-danger">*</span></span>
						<form:input path="password" type="password" class="form-control"/>
					</div>
					<div>
						<span>Nhắc lại mật khẩu<span class="text-danger">*</span></span> 
						<form:input path="confirmPassword" type="password" class="form-control"/>
					</div>
					<div class="mt-3">
						<input value="Đăng ký" type="submit" class="btn btn-primary" /> 
						<input value="Nhập lại" type="reset" class="btn btn-warning" />
					</div>
					
					
				</form:form>
	
    		</div>
    	
    	</div>

	<h2></h2>
	
	

</body>
</html>