<%@page import="org.apache.jasper.tagplugins.jstl.core.Import"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    

<div class="d-flex flex-row justify-content-start align-items-center mr-auto ">
	<span class="pr-3">Hiển thị</span>
	
	<span> 
	
		<form action="entries" method="GET">
		
			<select name="entries" id="entriesPerPage" class="form-control" 
			   	onchange="this.form.submit();">
			   	
				<option value="10"  <c:if test="${entries == 10}">selected</c:if>>10</option>
				<option value="20"  <c:if test="${entries == 20}">selected</c:if>>20</option>
				<option value="50"  <c:if test="${entries == 50}">selected</c:if>>50</option>
				<option value="100" <c:if test="${entries == 100}">selected</c:if>>100</option>
			    
			</select>
		
		</form>
	        
	</span>
	
	<span class="px-3">dòng/trang</span>
    
</div>