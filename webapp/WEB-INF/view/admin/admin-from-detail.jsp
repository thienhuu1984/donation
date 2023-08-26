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
            
            	<form:form modelAttribute="account" action="processUpdateUser" method="GET">
            		
            		<form:input path="id" class="hidden" />
            		
            		<div class="form-group row mb-3">
						<label for="input-id" class="col-sm-4 col-form-label"
							>Tên tài khoản</label
						>
						<div class="col-sm-9">
							<form:input path="username"  class="form-control" disabled="true" />
						</div>
					</div>
					
					<div class="form-group row mb-3">
					  <label for="input-name" class="col-sm-3 col-form-label"
					    >Vai trò</label
					  >
					  <div class="col-sm-9">
							<form:select path="roleId"  class="form-control" >
								<c:forEach var="role" items="${roles}">
									<option value="${role.id}" <c:if test="${role.id == account.roleId}">selected="true"</c:if>>${role.roleName}</option>
								</c:forEach>
							</form:select>
					  </div>
					</div>
					
					<div class="form-group row mb-3">
					  <label for="input-name" class="col-sm-3 col-form-label"
					    >Họ và tên</label
					  >
					  <div class="col-sm-9">
							<form:input path="fullName"  class="form-control" />
					  </div>
					</div>
					
					<div class="form-group row mb-3">
					  <label for="input-name" class="col-sm-3 col-form-label"
					    >Địa chỉ</label
					  >
					  <div class="col-sm-9">
							<form:input path="address"  class="form-control" />
					  </div>
					</div>
					
					<div class="form-group row mb-3">
					  <label for="input-name" class="col-sm-3 col-form-label"
					    >Điện thoại</label
					  >
					  <div class="col-sm-9">
							<form:input path="phoneNumber"  class="form-control" />
					  </div>
					</div>
					
					<div class="form-group row mb-3">
					  <label for="input-name" class="col-sm-3 col-form-label"
					    >Email</label
					  >
					  <div class="col-sm-9">
							<form:input path="email"  class="form-control" />
					  </div>
					</div>
					
					
					<input type="submit" value="Cập nhật" class="btn btn-primary"  />
				</form:form>

            		
            		
            </div>
          </div>
        </div>
      </div>

	</div>

</body>
</html>