<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<style type="text/css">
.header{
	position: relative;
	height: 100px;
	
}
.logo{
	height: 40px;
	margin-top: 30px;
}
.right {
    position: absolute;
    top: 50%; 
	right: 0;
	transform: translateY(-50%);
}
.right a{
	color: #000000;
	padding-left: 15px;
}
</style>
<c:set var="path" value="${pageContext.request.contextPath }"></c:set>
</head>
<body>
<div class="header">
	<span class="left">
		<img alt="" src="${path }/resources/main/logo.png" class="logo">
	</span>
	<span class="right">
		<c:if test="${sessionScope.MB_id == null }">
			<a>회원가입</a>
			<a href="loginForm.do">로그인</a>
		</c:if>
		<c:if test="${sessionScope.MB_id != null }">
			<c:if test="${sessionScope.MB_id == 'admin'}">
				관리자 페이지
			</c:if>
			<c:if test="${sessionScope.MB_id != 'admin'}">
				${sessionScope.MB_id }님 환영합니다
			</c:if>
			<a href="logout.do">로그아웃</a>
		</c:if>
	</span>
</div>
</body>
</html>