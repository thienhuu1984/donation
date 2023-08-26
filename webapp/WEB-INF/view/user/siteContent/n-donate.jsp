<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    

<div class=" d-flex align-items-center  justify-content-center"
	style="
			width: 100% !important;
			height: 100% !important;
			background-color: color: rgba(0,0,0, 0.5) !important;
			position:fixed !important; 
			top:0 !important;
			left:0 !important;
			z-index: 999 !important;"
	>

	<div class="" 
			style="
				width: 90% !important;
				height: 70% !important;
				background-color: #fff !important;
				border: 1px solid #198754 !important;
				border-radius: 10px;"
			
			>
	
		<div class="bd-highlight w-100">
		
		 		
			<h3 class="donateHeader p-3 text-white bg-success">
				Quyên góp cho: <span style="font-weight:bold !important;">${donate.donationName}</span>
			</h3>
		
			<form:form action="donateAction" modelAttribute="donate" method="GET">
			
				<form:input path="userId" class="hidden" />
				<form:input path="donationId" class="hidden" />
				
				<div class="p-3">
					<div class="row" style="gap:8px !important;">
						<div class="col col-lg-2">Họ tên</div>
						<div class="col-md-auto">
							<span><form:input path="userName" disabled="true"/></span>
							<span class="m-3 p-3"><form:checkbox path="hiddenName" /> Quyên góp ẩn danh?</span>
						</div>
						<div class="w-100"></div>
						<div class="col col-lg-2">Số tiền quyên góp</div>
						<div class="col-md-auto">
							<span><form:input path="money" size="50"/></span>
							<c:if test="${not empty errors}">
								<span class="errors text-danger font-italic m-2 p-3">${errors.errorMoney}</span>
							</c:if>
						</div>
						
						<div class="w-100"></div>
						<div class="col col-lg-2">Lời nhắn</div>
						<div class="col-md-auto">
							<span><form:textarea path="message" rows="6" cols="100"/></span>
						</div>
						
					</div>
				</div>
				
				<div class="d-flex justify-content-center "  style="gap:20px !important;">
					<input type="submit" value="Quyên góp" class="btn btn-primary" />
					<a href="close?id=${donate.donationId}" class="btn btn-warning">Đóng</a>
				</div>
			
			</form:form>
			
		</div>
		
	</div>
</div>