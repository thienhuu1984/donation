<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    
    
<!DOCTYPE html>
<html>
<head>

	<title>Quyên góp từ thiện - DANH SÁCH ĐỢT QUYÊN GÓP</title>
	
	<%@ include file="siteContent/head-tag-content.jsp" %>
	
</head>
<body>

	<h1 class="hidden">Assignment 1 - PRJ321x - huuttfx17728@funix.edu.vn</h1>

    <div class="admin wrapper d-flex align-items-stretch">
    	<%@ include file="siteContent/admin-leftPane.jsp" %>

		<!-- Page Content  -->
      	<div id="content" class="p-4 p-md-5 pt-5">
      	
      		<h2 class="mb-4">DANH SÁCH ĐỢT QUYÊN GÓP</h2>
      		
      		<div class="add-new p-2">
                <a href="addDonation" class="btn btn-primary">Thêm hoạt động quyên góp mới</a>
            </div>
      		
      		<div class="d-flex ">
	      		<%@ include file="siteContent/entry.jsp" %>
	            
				<%@ include file="siteContent/search-entry.jsp" %>
            </div>
            
            <%@ include file="siteContent/page-view.jsp" %>
            
      		
      		<!-- Donation list -->
        	<div id="list-container">
        		<div class="list-header code">Mã số</div>
	            <div class="list-header name">Tên hoạt động</div>
	            <div class="list-header start-date">Ngày bắt đầu</div>
	            <div class="list-header end-date">Ngày kết thúc</div>
	            <div class="list-header phone">Số điện thoại</div>
	            <div class="list-header money">Số tiền đã quyên góp</div>
	            <div class="list-header status">Trạng thái</div>
	            <div class="list-header action">Hành động</div>
				
                     
                     
				<c:forEach var="dona" items="${donations}">
                        	                        	
					<div class="list-detail col1 <c:if test="${loop.index % 2 ==0}">even</c:if>">${dona.code}</div>
					<div class="list-detail col2 <c:if test="${loop.index % 2 ==0}">even</c:if>">${dona.name}</div>
					<div class="list-detail col3 <c:if test="${loop.index % 2 ==0}">even</c:if>">${dona.startDate}</div>
					<div class="list-detail col4 <c:if test="${loop.index % 2 ==0}">even</c:if>">${dona.endDate}</div>
					<div class="list-detail col5 <c:if test="${loop.index % 2 ==0}">even</c:if>">${dona.phoneNumber}</div>
					<div class="list-detail col6 <c:if test="${loop.index % 2 ==0}">even</c:if>">${dona.money}</div>
					
					<div class="list-detail col7 <c:if test="${loop.index % 2 ==0}">even</c:if>">
						<c:if test="${dona.status==0}">
							<div>Mới tạo</div>
						</c:if>
						<c:if test="${dona.status==1}">
							<div>Đang quyên góp</div>
						</c:if>
						<c:if test="${dona.status==-1}">
							<div>Đã kết thúc</div>
						</c:if>
					
					</div>
					<div class="list-detail col8 <c:if test="${loop.index % 2 ==0}">even</c:if>">
						<a class="" href="editDonation?id=${dona.id}" title="Cập nhật"><i class="icon-pencil"> </i></a>
						<a class="" href="donationDetail?id=${dona.id}" title="Chi tiết"><i class="icon-list"> </i></a> 
						
						<c:if test="${dona.status==1}">
							 <a class="" href="donate?id=${dona.id}" title="Quyên góp"><i class="icon-suitcase"> </i></a> 
							 <a class="" href="finish?id=${dona.id}" title="Kết thúc" ><i class="icon-pencil"> </i></a>
						</c:if>
						<c:if test="${dona.status==0}">
							<a class="" href="deleteDonation?id=${dona.id}" 
								onclick="if(!confirm('Bạn có chắc xóa đợt quyên góp ${dona.code} này không?')) return false"
								title="Xóa"><i class="icon-trash-1"> </i></a> 
							<a class="" href="start?id=${dona.id}" title="Bắt đầu quyên góp"><i class="icon-suitcase"> </i></a> 
						</c:if>
						<c:if test="${dona.status==-1}">
							<a class="" href="start?id=${dona.id}" title="Bắt đầu quyên góp"><i class="icon-suitcase"> </i></a> 
							<a class="" href="deleteDonation?id=${dona.id}" 
								onclick="if(!confirm('Bạn có chắc xóa đợt quyên góp ${dona.code} này không?')) return false"
								title="Xóa"><i class="icon-trash-1"> </i></a> 
						</c:if>
					</div>
				</c:forEach>
        	
        	</div>
      		
        	
        	
      		
      	</div>

        
    </div>
    

    <div class="footer"></div>
    

</body>
</html>