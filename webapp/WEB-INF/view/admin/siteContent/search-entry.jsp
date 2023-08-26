<%@page import="org.apache.jasper.tagplugins.jstl.core.Import"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
<div class="list-header search-form" id="search-form"
	style="position:relative !important;" 
	>
	
	<form action="search">
		<input name="key" type="text" placeholder="Tìm theo tên hoạt động" class="form-control" size="20"/> 
		<button type="submit" class="btn"
			style="position:absolute !important; right: 0px !important; top: 0px !important;" 
			><i class="icon-search-8"> </i></button>
	</form>
	
</div>