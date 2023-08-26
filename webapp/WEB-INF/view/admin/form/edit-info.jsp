<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    
<form:input path="id" class="hidden"/>
				
<div>
	<span>Họ tên đầy đủ<span class="text-danger">*</span></span> 
	<form:input path="username"  class="form-control"/> 
	<c:if test="${not empty errors}">
		<div class="errors text-danger font-italic mt-2">${errors.errorNotNull}</div>
	</c:if>
</div>

<div>
	<span>Điện thoại<span class="text-danger">*</span></span> 
	<form:input path="phoneNumber" class="form-control"/> 
	<c:if test="${not empty errors}">
		<div class="errors text-danger font-italic mt-2">${errors.errorNotNull}</div>
		<div class="errors text-danger font-italic mt-2">${errors.errorPhoneNumber}</div>
	</c:if>
</div>

<div>
	<span>Email<span class="text-danger">*</span></span> 
	<form:input path="email"  class="form-control"/> 
	<c:if test="${not empty errors}">
		<div class="errors text-danger font-italic mt-2">${errors.errorNotNull}</div>
		<div class="errors text-danger font-italic mt-2">${errors.errorEmail}</div>
	</c:if>
</div>


<div>
	<span>Địa chỉ</span> 
	<form:textarea path="address" class="form-control" rows="8" cols="20"/> 
</div>

<div>
	<span>Thông tin khác</span> 
	<form:textarea path="note" class="form-control" rows="8" cols="20"/> 
</div>