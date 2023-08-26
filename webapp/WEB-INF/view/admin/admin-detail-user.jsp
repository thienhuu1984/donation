<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
<!DOCTYPE html>
<html>
<head>

	<title>Quyên góp từ thiện - Thêm thành viên mới</title>

	
	<%@ include file="siteContent/head-tag-content.jsp" %>
		
</head>
<body>

	<h1 class="hidden">Assignment 1 - PRJ321x - huuttfx17728@funix.edu.vn</h1>

    <div class="admin wrapper d-flex align-items-stretch">
    	<%@ include file="siteContent/admin-leftPane.jsp" %>
        
        <!-- Page Content  -->
      	<div id="content" class="p-4 p-md-5 pt-5">
      	
      		<h2 class="mb-4">CHI TIẾT TÀI KHOẢN</h2>
      		
      		<div class="add-new p-2">
                <a class="btn btn-primary" href="updateUser?id=${user.id}">Sửa tài khoản</a>
                <a class="btn btn-warning" href="changeRole?id=${user.id}">Thay vai trò</a>
                <a class="btn btn-warning" href="resetPassword?id=${user.id}">Khôi phục mật khẩu</a>                                                        
                <a  class="btn btn-secondary" href="users">Trở về danh sách</a> 
            </div>
      		
      		<!-- User details -->
        	<div id="details" class="grid">
				<div class="label">Họ và tên</div>
				<div class="content">${user.fullName}</div>
				
				<div class="label">Email</div>
				<div class="content">${user.email}</div>
				
				<div class="label">Số điện thoại</div>
				<div class="content">${user.phoneNumber}</div>
				
				<div class="label">Địa chỉ</div>
				<div class="content">${user.address}</div>
				
				<div class="label">Tên tài khoản</div>
				<div class="content">${user.username}</div>
				
				<div class="label">Vai trò</div>
				<div class="content">
				
				<c:if test="${changeRole != null}">
					<form:form modelAttribute="user" action="updateRole" method="GET">
						<form:input path="id" class="hidden" />
						
						<form:select path="roleId"  class="form-control" >
							<c:forEach var="role" items="${changeRole}">
								<option value="${role.id}" <c:if test="${role.id == user.roleId}">selected="true"</c:if>>${role.roleName}</option>
							</c:forEach>
						</form:select>
						
						<input type="submit" value="Cập nhật" class="btn btn-warning"  />
					</form:form>
				</c:if>
				<c:if test="${changeRole == null}">
					${user.roleName}
				</c:if>
				
				</div>
        		
        		
        	
        	</div>
      		
        	
        	
      		
      	</div>
        
    </div>

    <div class="footer"></div>

</body>
</html>