<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    
    
<!DOCTYPE html>
<html>
<head>
    <title>Quyên góp từ thiện</title>
	
	<%@ include file="siteContent/head-tag-content.jsp" %>
	
</head>
<body>
    	
    	<%@ include file="siteContent/n-topPane.jsp" %>
    	
    	<div class="container mt-5" >
    	 
    		<h2 class="header text-center text-uppercase">Các đợt quyên góp</h2>
    		
    		<div class="contents mt-33 d-flex flex-wrap">
    			
    			<c:forEach var="dona" items="${donations}" >
    				
    				<div class="item d-flex flex-column justify-content-between m-3 p-3  border border-secondary rounded pe-auto"
    					style="width:30% !important;
    							" 	>
    					
    					<div onclick="location.href='donationDetail?id=${dona.id}';">
    					
		    				<div class="text-body text-uppercase" style="font-weight: bold !important;">${dona.name}</div>
		    				<div class="status">
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
		    			
		    			
		    				<div class="label ">
		    					Ngày bắt đầu
		    					<span class="detail text-body">${dona.startDate}</span>
		    				</div>
		    				
		    				<div class="label ">
		    					Ngày kết thúc
		    					<span class="detail text-body">${dona.endDate}</span>
		    				</div>
		    				
		    				<div class="label">
		    					Số tiền đã quyên góp được: 
		    					<span class="detail text-body">${dona.money}</span>
		    				</div>

		    				<div class="label text-body">${dona.organizationName}</div>
		    				<div class="detail">${dona.phoneNumber}</div>
		    				
		    				
		    			</div>
	    			
	    				<br/>

                       	<c:if test="${dona.status==1}">
                       		<a class="btn btn-primary" href="donate?id=${dona.id}"
                       			>Quyên góp</a>
                       	</c:if>
                       	<c:if test="${dona.status==-1}">
                       		<a class="btn btn-secondary text-white" aria-disabled="true">Đã kết thúc</a>
                       	</c:if>
                       	<c:if test="${dona.status==0}">
                       		<a class="btn btn-secondary text-white" aria-disabled="true">Chờ</a>
                       	</c:if>
	                       	
						

	    			</div>
    			</c:forEach>
    		</div>
    		
    	</div>
    	

    	<c:if test="${not empty donate }">
				<%@ include file="siteContent/n-donate.jsp" %>
		</c:if>
    		
    		
</body>
</html>