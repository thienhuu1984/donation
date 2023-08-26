<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    
    
<!DOCTYPE html>
<html>
<head>

	<title>Quyên góp từ thiện - Danh sách tài khoản</title>

	
	<%@ include file="../siteContent/head-tag-content.jsp" %>
		
</head>
<body>

	<h1 class="hidden">Assignment 1 - PRJ321x - huuttfx17728@funix.edu.vn</h1>

	<div class="admin wrapper d-flex align-items-stretch">
    	<%@ include file="../siteContent/n-topPane.jsp" %>

		<!-- Page Content  -->
      	<div id="content" class="p-4 p-md-5 pt-5">
      	
      		
      		<h2 class="mb-4">Chào mừng đến với ứng dụng Quyên góp.</h2>
      		
      		
      		
      		<div class="text-center">
      			Trước tiên, bạn vui lòng tạo tài khoản quản trị.
            </div>
            
            <div class="contents mt-4 d-flex justify-content-center "
            	>
      		
	      		<div class="contents d-flex flex-column border border border-success rounded p-5"
	      			style="width:50% !important;">
	    		
	    			<form:form modelAttribute="account" action="step-2" acceptCharset="UTF-8" method="POST">
	    			
						<div >
							<span>Tạo tên đăng nhập<span class="text-danger">*</span></span> 
							<form:input path="username" type="text"  class="form-control"/> 
							<c:if test="${not empty errors}">
								<div class="errors text-danger font-italic mt-2"
									style="line-height:1.5em !important;"
									>${errors.errorUsername}</div>
							</c:if>
						</div>
						
						<div class="mt-3">
							<span>Tạo mật khẩu<span class="text-danger">*</span></span>
							<form:input path="password" type="password" class="form-control"/>
							<c:if test="${not empty errors}">
								<div class="errors text-danger font-italic mt-2"
									style="line-height:1.5em !important;"
									>${errors.errorPassword}</div>
							</c:if>
						</div>
						
						<div class="mt-3">
							<span>Nhắc lại mật khẩu<span class="text-danger">*</span></span> 
							<form:input path="confirmPassword" type="password" class="form-control"/>
							<c:if test="${not empty errors}">
								<div class="errors text-danger font-italic mt-2"
									style="line-height:1.5em !important;"
									>${errors.errorConfirmPassword}</div>
							</c:if>
						</div>
						
						<div class="mt-3 d-flex flex-column align-items-center">
							<input value="Tạo tài khoản quản trị" type="submit" class="btn btn-primary" /> 
							<input value="Nhập lại" type="reset" class="btn btn-link" />
						</div>
						
						
					</form:form>
		
	    		</div>
    		</div>
      		
      	</div>
      	
    </div>
    

    <div class="footer"></div>
    
    

</body>
</html>