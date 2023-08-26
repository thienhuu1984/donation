<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    
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
      	
      		<h2 class="mb-4">THÔNG TIN QUYÊN GÓP</h2>
      		
      		<div class="add-new p-2">                                                    
                <a  class="btn btn-secondary" href="donation">Trở về danh sách</a> 
            </div>
      		
      		
      		<!-- Donation details -->
        	<div id="details" class="grid">

				<div class="label">Mã hoạt động </div>
				<div class="content">${dona.code}</div>

				<div class="label">Tên hoạt động </div>
				<div class="content">${dona.name}</div>
				
				<div class="label">Tổng tiền quyên góp được </div>
				<div class="content">${dona.money}</div>
				
				<div class="label">Ngày bắt đầu </div>
				<div class="content">${dona.startDate}</div>

				<div class="label">Ngày kết thúc</div>
				<div class="content">${dona.endDate}</div>

				<div class="label">Tổ chức/Cá nhân</div>
				<div class="content">${dona.organizationName}</div>

				<div class="label">Số điện thoại</div>
				<div class="content">${dona.phoneNumber}</div>

				<div class="label">Nội dung </div>
				<div class="content">${dona.description}</div>
				
				<div class="label">Trạng thái</div>
				<div class="content">
				
					<c:if test="${dona.status==0}">
						Mới tạo
					</c:if>
					<c:if test="${dona.status==1}">
						Đang quyên góp
					</c:if>
					<c:if test="${dona.status==-1}">
						Đã kết thúc
					</c:if>
				
				</div>
        		
        	
        	</div>
        	
        	<h3 class="mb-4">DANH SÁCH ĐÃ QUYÊN GÓP</h3>
        	
        	<div class="d-flex justify-content-between p-2">
	            <form:form action="entriesPerPage">
	                Hiển thị 
	                <select name="entriesPerPage" id="entriesPerPage" class="inputbox">
	                    <option value="10">10</option>
	                    <option value="20">20</option>
	                    <option value="50">50</option>
	                    <option value="100">100</option>
	                </select>
	                dòng/trang
	            </form:form>
	            
				<div class="list-header search-form" id="search-form">
		            <form:form action="searchUsers">
		                <input type="text" placeholder="Search" class="inputbox"/> 
		            </form:form>
		        </div>
            </div>
            
            
            <nav aria-label="Page navigation example" id="navigation">
		        <ul class="pagination justify-content-center">
		            <c:if test="${site.totalPages>1}">
			            <li class="page-item">
			            	<button class="page-link" href="#" id="btn-prev">‹</button>
			            </li>
		            </c:if>
		            
		            <c:forEach var="p" items="${site.pageList}">
		            	<li class="page-item <c:if test="${p == site.page}">disabled</c:if> ">
			            	<a class="page-link" id="page-num">${p}</a>
			            </li>
		            </c:forEach>
			            
		            <c:if test="${site.totalPages>1}">
			            <li class="page-item">
			            	<button class="page-link" id="btn-next">›</button>
			            </li>
		            </c:if>
		            
		        </ul>
        	</nav>
        	
        	<!-- Donated user list -->
        	<div id="list-container">
        	
        		<div class="list-header col1">Họ Tên</div>
				<div class="list-header col2">Tiền quyên góp</div>
				<div class="list-header col3">Ngày quyên góp</div>
				<div class="list-header col4">Nội dung</div>
				<div class="list-header col5">Trạng thái</div>
				<div class="list-header col6">Hành động</div>
				
				<c:forEach var="donauser" items="${donausers}">
				                       	
					<div class="list-detail col1 <c:if test="${loop.index % 2 ==0}">even</c:if>">${donauser.userName}</div>
					<div class="list-detail col2 <c:if test="${loop.index % 2 ==0}">even</c:if>">${donauser.money}</div>
					<div class="list-detail col3 <c:if test="${loop.index % 2 ==0}">even</c:if>">${donauser.created}</div>
					<div class="list-detail col4 <c:if test="${loop.index % 2 ==0}">even</c:if>">${donauser.message}</div>
					<div class="list-detail col5 <c:if test="${loop.index % 2 ==0}">even</c:if>">
						<c:if test="${donauser.status==0}">Chờ xác nhận</c:if>
						<c:if test="${donauser.status==1}">Đã xác nhận</c:if>  
					</div>
					
					<div class="list-detail col6 <c:if test="${loop.index % 2 ==0}">even</c:if>">
						<c:if test="${donauser.status==0}">
							<a href="confirmDonate?userDonationId=${donauser.id}">Xác nhận</a> 
						</c:if>
						<c:if test="${donauser.status==1}">
							<a href="cancelDonate?userDonationId=${donauser.id}">Hủy xác nhận</a>
						</c:if>  
					</div>
				
				</c:forEach>
                        

        	</div>
      		
        	
        	
      		
      	</div>
      	
        
    </div>

    <div class="footer"></div>
    
</body>
</html>