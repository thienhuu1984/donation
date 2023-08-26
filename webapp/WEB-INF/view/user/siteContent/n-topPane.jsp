<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    
    
<div class="header bg-info  bd-highlight p-3">
	<h1 class="hidden" hidden="hidden">Assignment 1 - PRJ321x - huuttfx17728@funix.edu.vn</h1>
	<div class="container-fluid d-flex justify-content-between align-items-center">
		<div class="page-title text-white">
			<h2 class=" text-white fw-bold" onclick="location.href='./';">Quyên góp từ thiện</h2>
			<div class="sub-title fst-italic">Nơi hội tụ của những nét đẹp tâm hồn</div>
		</div>
		
		<div class="nav-bar d-flex align-items-center flex-column" >
			<c:if test="${username == null}">
				<span><a href="login" class="btn btn-light" >Đăng nhập</a></span>
				<span class="text-warning">Chưa có tài khoản?
					<a class="text-white font-italic btn btn-link" href="register">«Đăng ký»</a>
				</span>
			</c:if>
			<c:if test="${username != null}">
				<div class="text-white h6">Xin chào: <span class="text-warning fw-bold">${username}</span></div> <a href="logout" class="btn btn-warning" >Đăng xuất</a>
			</c:if>
		</div>
	</div>
	
	
</div>