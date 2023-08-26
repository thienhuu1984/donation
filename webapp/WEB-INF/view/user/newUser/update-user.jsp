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
      			Tiếp tục nhé. Bạn vui lòng điền thông tin của mình.
            </div>
      		
      		<div class="contents mt-4 d-flex justify-content-center "
            	>
      		
	      		<div class="contents d-flex flex-column border border border-success rounded p-5"
	      			style="width:50% !important;">

					<form:form modelAttribute="account" action="ready" acceptCharset="UTF-8" method="GET">
					
						<form:input path="id" class="hidden"/>
						<form:input path="roleId" class="hidden" />
								
						<div>
							<span>Họ tên đầy đủ<span class="text-danger">*</span></span> 
							<form:input path="fullName"  class="form-control"/> 
							<c:if test="${not empty errors}">
								<div class="errors text-danger font-italic mt-2">${errors.errorFullName}</div>
							</c:if>
						</div>
						
						<div class="mt-3">
							<span>Điện thoại<span class="text-danger">*</span></span> 
							<form:input path="phoneNumber" class="form-control"/> 
							<c:if test="${not empty errors}">
								<div class="errors text-danger font-italic mt-2">${errors.errorPhoneNumber}</div>
							</c:if>
						</div>
						
						<div class="mt-3">
							<span>Email<span class="text-danger">*</span></span> 
							<form:input path="email"  class="form-control"/> 
							<c:if test="${not empty errors}">
								<div class="errors text-danger font-italic mt-2">${errors.errorEmail}</div>
							</c:if>
						</div>
						
						
						<div class="mt-3">
							<span>Địa chỉ</span> 
							<form:textarea path="address" class="form-control" rows="8" cols="20"/> 
						</div>
						
						<div class="mt-3">
							<span>Thông tin khác</span> 
							<form:textarea path="note" class="form-control"  style="height: 200px !important;" /> 
						</div>
						
						<div class="mt-3 d-flex flex-column align-items-center">
							<input value="Bắt đầu ứng dụng" type="submit" class="btn btn-primary" /> 
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

