<%@page import="org.apache.jasper.tagplugins.jstl.core.Import"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    


<nav aria-label="Page navigation example" id="navigation">
	<ul class="pagination justify-content-center">
		
		<c:if test="${curPage > 1}">
			<li class="page-item">
				<a class="page-link" href="page?page=${curPage-1}" id="btn-prev">‹</a>
			</li>
		</c:if>
		   
		   
		<c:forEach var = "i" begin = "1" end = "${totalPages}">
			<li class="page-item ">
				<a class="page-link <c:if test="${i == curPage}">disabled</c:if>" 
			<c:if test="${i != curPage}">href="page?page=${i}"</c:if> >${i}</a>
		</li>
		
		</c:forEach>
		
		          
		<c:if test="${curPage  < totalPages}">
			<li class="page-item">
				<a class="page-link" id="btn-next"  href="page?page=${curPage-1}">›</a>
			</li>
		</c:if>
	     
</ul>
</nav>