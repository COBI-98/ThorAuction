<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- security tag -->
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	/* 	function Frameset(page) {
	 framecode = "<frameset rows='1*'>"
	 + "<frame name=main src='" + page + "'>" + "</frameset>";

	 page = window.open("");
	 page.document.open();
	 page.document.write(framecode);
	 page.document.close();
	 } */
</script>
</head>
<style>
* {
	text-align: center;
}

.aa {
	padding-right: 15px;
}
</style>
<body>
	<h1>Index Page</h1>
	<a href="./kjk/admin/admin">연습</a>
	<!-- <a href="javascript:Frameset('loginForm.jsp')">카카오 로그인 버튼</a>
	<a href="javascript:Frameset('admin.jsp')">리스트 예시</a> -->
	<br>
	<br>
	<sec:authorize access="!isAuthenticated()">
		<a class="aa" href="./member/join">회원가입</a>
		<a href="./member/login">로그인</a>
	</sec:authorize>
	<sec:authentication property="Principal" var="member" />
	<sec:authorize access="isAuthenticated()">
		<h3>${member.name}님안녕하세요</h3>
		<br>
		<br>
		<br>
		<a href="/member/delete">로그아웃</a>
	</sec:authorize>

</body>
</html>
