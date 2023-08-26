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
	
	<div class="wrapper d-flex align-items-stretch">
    	<%@ include file="siteContent/admin-leftPane.jsp" %>

      <!-- Page Content  -->
      <div id="content" class="p-4 p-md-5 pt-5">
      
        <h2 class="mb-4">THÊM TÀI KHOẢN MỚI</h2>
        
        <div class="container" id="main" style="max-width: 90%">
          <div class="row justify-content-center align-items-center mt-4">
            <div class="col-lg-6 col-lg-offset-4">
            
            	<form:form modelAttribute="account" action="createNewAccount" method="POST">
            		
            		<div class="form-group row mb-3">
						<label for="input-id" class="col-sm-3 col-form-label"
							>Tên tài khoản</label
						>
						<div class="col-sm-9">
							<form:input path="username" placeholder="Họ và tên" class="form-control" />
						</div>
					</div>
					
					<div class="form-group row mb-3">
						<label for="input-name" class="col-sm-3 col-form-label"
							>Mật khẩu</label
						>
						<div class="col-sm-9">
							<form:password path="password" placeholder="Mật khẩu" class="form-control" />
						</div>
					</div>
					
					<div class="form-group row mb-3">
					  <label for="input-name" class="col-sm-3 col-form-label"
					    >Nhắc lại mật khẩu</label
					  >
					  <div class="col-sm-9">
							<form:password path="confirmPassword" placeholder="Nhắc lại mật khẩu" class="form-control" />
					  </div>
					</div>
					
					<div class="form-group row mb-3">
					  <label for="input-name" class="col-sm-3 col-form-label"
					    >Vai trò</label
					  >
					  <div class="col-sm-9">
							<form:select path="roleId" placeholder="Chọn vai trò" class="form-control" >
								<c:forEach var="role" items="${roles}">
									<option value="${role.id}">${role.roleName}</option>
								</c:forEach>
							</form:select>
					  </div>
					</div>
					
					
					
					<input type="submit" value="Tạo tài khoản" class="btn btn-primary"  />
				</form:form>

            		
            		
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="footer"></div>
    
</body>
</html>