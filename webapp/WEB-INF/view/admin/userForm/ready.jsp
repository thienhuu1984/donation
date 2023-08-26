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
    	<%@ include file="../siteContent/admin-leftPane.jsp" %>

		<!-- Page Content  -->
      	<div id="content" class="p-4 p-md-5 pt-5">
      	
      		
      		<h2 class="mb-4">Chào bạn ${adminname}</h2>
      		
      		
      		
      		<div class="text-center">
      			Chúng ta bắt đầu sử dụng ứng dụng nào.
            </div>
      		
      		<div class="contents mt-33 d-flex flex-column border border border-success rounded">

				<a href="./" class="btn btn-success">Vào trang quản trị</a>
				
			</div>
      		
      	</div>
      	
    </div>
    

    <div class="footer"></div>
    
    

</body>
</html>

