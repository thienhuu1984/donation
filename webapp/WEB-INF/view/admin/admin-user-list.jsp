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
      	
      		
      			<h2 class="mb-4">QUẢN LÝ THÀNH VIÊN</h2>
      		
      		
      		
      		<div class="add-new p-2">
      			<a href="newAccount" class="btn btn-primary">Thêm mới</a>
            </div>
      		
      		<div class="d-flex ">
	      		<%@ include file="siteContent/entry.jsp" %>
	            
				<%@ include file="siteContent/search-entry.jsp" %>
            </div>
            
            
            <%@ include file="siteContent/page-view.jsp" %>
      		
      		<!-- Donation list -->
        	<div id="list-container">
        		<div class="list-header col1">Họ tên</div>
                <div class="list-header col2">Email</div>
                <div class="list-header col3">Điện thoại</div>
                <div class="list-header col4">Tài khoản</div>
                <div class="list-header col5">Vai trò</div>
                <div class="list-header col6">Trạng thái</div>
                <div class="list-header col7">Hành động</div>

				<c:forEach var="userDetail" items="${usersList}" varStatus="loop">                        
                        	
					<div class="list-detail col1 <c:if test="${loop.index % 2 ==0}">even</c:if>">${userDetail.fullName}</div>
					<div class="list-detail col2 <c:if test="${loop.index % 2 ==0}">even</c:if>">${userDetail.email}</div>
					<div class="list-detail col3 <c:if test="${loop.index % 2 ==0}">even</c:if>">${userDetail.phoneNumber}</div>
					<div class="list-detail col4 <c:if test="${loop.index % 2 ==0}">even</c:if>">${userDetail.username}</div>
					<div class="list-detail col5 <c:if test="${loop.index % 2 ==0}">even</c:if>">${userDetail.roleName}</div>
					
					<div class="list-detail col6 <c:if test="${loop.index % 2 ==0}">even</c:if>">
						<c:if test="${userDetail.status==0}">
							<div>Hoạt động</div>
						</c:if>
						<c:if test="${userDetail.status==-1}">
							<div>Đã khóa</div>
						</c:if>
					
					</div>
					
					<div class="list-detail col7 <c:if test="${loop.index % 2 ==0}">even</c:if>">
						<a class="btn btn-secondary" href="send?id=${userDetail.id}">Gửi</a> 
						<a class="btn btn-primary" href="updateUser?id=${userDetail.id}">Sửa</a> 
						<a class="btn btn-info" href="detailUser?id=${userDetail.id}">Chi tiết</a> 
						<a class="btn btn-danger" href="deleteUser?id=${userDetail.id}"
							onclick="if(!confirm('Bạn có chắc xóa tài khoản ${userDetail.fullName} không?')) return false"
						>Xóa</a>
						<c:if test="${userDetail.status==0}">
							<a class="btn btn-warning" href="blockUser?id=${userDetail.id}">Khóa</a>
						</c:if>
						<c:if test="${userDetail.status==-1}">
							<a class="btn btn-warning" href="unblockUser?id=${userDetail.id}">Mở</a>
						</c:if>
					</div>
					
				</c:forEach>
        	
        	</div>
      		
      	</div>
      	
    </div>
    

    <div class="footer"></div>
    
    

</body>
</html>