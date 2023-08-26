<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
<nav id="sidebar">
	<h1><a href="./" class="logo">ADMIN PANE</a></h1>
	
	<c:if test="${!pagename.equals('login')}">
		<ul class="list-unstyled components mb-5">
		
			<li><a>
				<span class="fa fa-user mr-3"></span> Xin chào, <br/><span style="font-weight:bold;align:center;">${adminname}</span><br/>
				<span class="btn btn-warning" onclick="location.href='logout'">Đăng xuất</span>
				</a>
			</li>
		
	    	<li <c:if test="${pagename.equals('donation')}">class="active"</c:if>>
	    		<a href="donation"
	    			><span class="fa fa-newspaper-o mr-3"></span> QUẢN LÝ QUYÊN GÓP</a>
	    	</li>
	    	
	        <li <c:if test="${pagename.equals('users')}">class="active"</c:if>>
	        	<a href="users"
	        		><span class="fa fa-list mr-3"></span> QUẢN LÝ TÀI KHOẢN</a>
	        </li>
	        
	        <li <c:if test="${pagename.equals('roles')}">class="active"</c:if>>
	        	<a href="roles"
	        		><span class="fa fa-cogs mr-3"></span> QUẢN LÝ VAI TRÒ</a>
	        </li>
	        
	    </ul>
    </c:if>
</nav>

