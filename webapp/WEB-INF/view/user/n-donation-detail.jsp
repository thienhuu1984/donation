<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    
<!DOCTYPE html>
<html>
<head>
    <title>Quyên góp từ thiện</title>
	
	<%@ include file="siteContent/head-tag-content.jsp" %>
	
</head>
<body style="position: relative !important">
    	
    <%@ include file="siteContent/n-topPane.jsp" %>
    
	<div class="container mt-5 position-relative" style="position: relative !important">
		<div class="content-title w-50">
			<h2 class="header text-success">Thông tin quyên góp</h2>
			<div class="content text-body">
				<p>${dona.name}</p>
				<p>${dona.description}</p>
			</div>
		</div>
		
		<div class="content-info "
			style="position:absolute !important; top:0px !important; right: 0px !important;">
		
			<div class="bg-light w-38 p-4"
					style="border: 1px solid #198754 !important; border-radius: 10px !important;">
				<h4 class="header text-success">Thông tin</h4>
				<div class= "content">
					<div>
						<span class="label">Mã hoạt động: </span>
						<span class="detail text-body"  style="font-weight: bold !important;">${dona.code}</span>
					</div>
					
					<div>
						<span class="label">Tên hoạt động: </span>
						<span class="detail text-body"  style="font-weight: bold !important;" >${dona.name}</span>
					</div>
					
					<div>
						<span class="label">Ngày bắt đầu: </span>
						<span class="detail text-body" style="font-weight: bold !important;">${dona.startDate}</span>
					</div>
					
					<div>
						<span class="label">Ngày kết thúc:</span>
						<span class="detail text-body" style="font-weight: bold !important;">${dona.endDate}</span>
					</div>
					
					<div>
						<span class="label">Cá nhân/Tổ chức:</span>
						<span class="detail text-body" style="font-weight: bold !important;">${dona.organizationName}</span>
					</div>
					
					<div>
						<span class="label">Số điện thoại:</span>
						<span class="detail text-body" style="font-weight: bold !important;">${dona.phoneNumber}</span>
					</div>
					
					<div>
						<span class="label">Tổng tiền quyên góp đã nhận được:</span>
						<span class="detail text-body" style="font-weight: bold !important;">${dona.money}</span>
					</div>
					
					<div>
						<span class="label">Trạng thái: </span>
						<span class="detail text-body" style="font-weight: bold !important;">
	                       	<c:if test="${dona.status==0}">
	                       		Mới tạo
	                       	</c:if>
	                       	<c:if test="${dona.status==1}">
	                       		Đang quyên góp
	                       	</c:if>
	                       	<c:if test="${dona.status==-1}">
	                       		Đã kết thúc
	                       	</c:if>
						</span>
					</div>
				</div>
			</div>
				
			<div class="mt-2 w-100">
               	<c:if test="${dona.status==1}">
               		<a class="btn btn-primary w-100 bd-highlight" href="donate?id=${dona.id}">Quyên góp</a>
               	</c:if>
               	<c:if test="${dona.status==-1}">
               		<a class="btn btn-secondary text-white w-100 bd-highlight" aria-disabled="true">Đã kết thúc</a>
               	</c:if>
               	<c:if test="${dona.status==0}">
               		<a class="btn btn-secondary text-white w-100 bd-highlight" aria-disabled="true">Chưa tới thời gian quyên góp</a>
               	</c:if>
			</div>
			
		</div>
		
		
		
		<div class="donate-detail">
		
			<h4 class="header">Danh sách quyên góp</h4>
			
			<div class="d-flex flex-column-reverse my-4" style="gap:15px !important">
				<c:forEach var="donauser" items="${donausers}">
		            <div class="d-flex align-items-start" style="gap:15px !important">    
		            	<div class="align-self-start border border-success border-2 rounded-circle
		            		text-success d-flex align-items-center" 
		            		style="width:20px !important; height: 20px !important">
		            		<i class="icon-user-1"> </i>
		            	</div>
		            	<div class="align-self-stretch px-3 
		            			border border-success border-2 bg-light" 
		            		style="border-radius: 6px !important;
		            				width:200px !important;
		            				line-height: 1.5em !important;">                                	
					        <div class="label text-body" style="font-weight:bold !important;">
					        	<c:if test="${donauser.hiddenName}">Người quyên góp</c:if>
					        	<c:if test="${!donauser.hiddenName}">${donauser.userName}</c:if>
					        	
					        </div>
					        <div class="detail date">${donauser.created}</div>
					        <div class="detail money">${donauser.money}</div>
					        <div class="detail" style="font-style: italic !important;">
					        	
					        	<c:if test="${donauser.message.length() gt 0}">${donauser.message}</c:if>
					        	<c:if test="${empty donauser.message}">Quyên góp</c:if>
					        	
					        </div>
					    </div>
					    
		       		</div>
		      	</c:forEach>
	      	</div>
	      	
		</div>
	
	</div>

	<c:if test="${not empty donate }">
			<%@ include file="siteContent/n-donate.jsp" %> 
	</c:if>
	
    
</body>
</html>