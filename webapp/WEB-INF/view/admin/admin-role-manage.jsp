<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    
    
<!DOCTYPE html>
<html>
<head>

	<title>Quyên góp từ thiện - Danh sách tài khoản</title>

	
	<%@ include file="siteContent/head-tag-content.jsp" %>
		
</head>
<body>

	<h1 class="hidden">Assignment 1 - PRJ321x - huuttfx17728@funix.edu.vn</h1>
	
	<div class="admin wrapper d-flex align-items-stretch">
    	<%@ include file="siteContent/admin-leftPane.jsp" %>

		<!-- Page Content  -->
      	<div id="content" class="p-4 p-md-5 pt-5">
      	
      		<h2 class="mb-4">QUẢN LÝ VAI TRÒ THÀNH VIÊN</h2>
      		
      		<div class="add-new p-2">
                    
				<div class="form-pane">
					<form:form action="addNewRole" method="get" modelAttribute="newRole"  acceptCharset="UTF-8">
						<form:input path="roleName" placeholder="Nhập vai trò mới"  />
						<form:input path="description" placeholder="Mô tả"  />
						<input type="submit" value = "Thêm mới" class="btn btn-primary" />
					</form:form>
				</div>
            </div>
      		
      		<div style="height:15px" > </div>
      		
      		<!-- Donation list -->
        	<div id="list-container">
        	
				<div class="list-header col1">STT</div>
				<div class="list-header col2">Tên vai trò</div>
				<div class="list-header col3">Mô tả</div>
				
				<c:forEach var="role" items="${rolesList}" varStatus="loop">
					<div class="list-detail col1 <c:if test="${loop.index % 2 ==0}">even</c:if>">${loop.index +1}</div>
					<div class="list-detail col2 <c:if test="${loop.index % 2 ==0}">even</c:if>">${role.roleName}</div>
					<div class="list-detail col3 <c:if test="${loop.index % 2 ==0}">even</c:if>">${role.description}</div>
				</c:forEach>
        	
        	</div>
      		
      	</div>
      	
    </div>
    <div class="footer"></div>

</body>
</html>