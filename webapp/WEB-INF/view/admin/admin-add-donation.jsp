<%@page import="org.apache.jasper.tagplugins.jstl.core.Import"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
<!DOCTYPE html>
<html>
<head>
	<title>Quyên góp từ thiện</title>
	
	<%@ include file="siteContent/head-tag-content.jsp" %>
		
</head>
<body>

	

	<h1 class="hidden">Assignment 1 - PRJ321x - huuttfx17728@funix.edu.vn</h1>

   <div class="admin wrapper d-flex align-items-stretch">
    	<%@ include file="siteContent/admin-leftPane.jsp" %>
		
		<!-- Page Content  -->
      	<div id="content" class="p-4 p-md-5 pt-5">
      	
      		<h2 class="mb-4">${dona.title}</h2>
      		
      		<div class="container" id="main" style="max-width: 90%">
				<div class="row justify-content-center align-items-center mt-4">
					<div class="col-lg-6 col-lg-offset-2">
					
						<form:form modelAttribute="dona" action="${dona.process}" method="GET">
						
							<form:input path="id" class="hidden"/>
							
							<label  class="col-form-label"
								>Mã hoạt động: </label>
							<form:input path="code" placeholder="Mã hoạt động" class="form-control" />

							<label  class="col-form-label"
								>Tên hoạt động: </label>
							<form:input path="name" placeholder="Tên hoạt động" class="form-control" />

							<label  class="col-form-label"
								>Ngày bắt đầu: </label>
							<form:input type="date" path="startDate" class="form-control"/>

							<label  class="col-form-label"
								>Ngày kết thúc:</label>
							<form:input type="date"  path="endDate" class="form-control"  />

							<label  class="col-form-label"
								>Tên cá nhân / Tổ chức:</label>
							<form:input path="organizationName"  class="form-control"/>

							<label  class="col-form-label"
								>Số điện thoại:</label>
							<form:input path="phoneNumber"  class="form-control" />

							<label  class="col-form-label"
								>Nội dung: </label>
							<form:textarea path="description" rows="5"  class="form-control" style="height: 200px !important;" />
							

							<br/>
							<input type="submit" value="${dona.button}" class="btn btn-primary"/>
						
						</form:form>
					
							
					</div>
				</div>
			</div>
      		
      		
      	</div>
      	

    </div>

    <div class="footer"></div>
    
</body>
</html>