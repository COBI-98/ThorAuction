<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- security tag -->
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>토르의 실시간 대장간</title>
<c:import url="./template/boot.jsp"></c:import>
<link href="/css/reset.css" rel="stylesheet">
<link href="/images/Thor.jpg" rel="shortcut icon" type="image/x-icon">
</head>
<script defer src="/js/admin/kakaoLoginCheck.js"></script>
<body>
	<c:import url="./template/header.jsp"></c:import>
	<section class="container d-flex flex-wrap justify-content-center">

		<h1>토르의 실시간 대장간 메인</h1>

		<div class="mt-5">
			<a href="../mypage/update">회원정보 수정</a>
			<a href="../mypage/delete">회원탈퇴</a>
		</div>
		<sec:authorize access="!isAuthenticated()">
			<button type="button" class="btn btn-outline-none" onclick="idcheck()">
				<img src="/images/member/kakao_login_logo.png" class="kakao-button__img" alt="">
			</button>
			<a href="../admin/adminpage" class="nav-link link-dark px-2">|카카오 정보|</a>
		</sec:authorize>
		<sec:authorize access="isAuthenticated()">
		<b>카카오 로그인완료!</b>
		<br>
		<a href="../admin/adminpage" class="nav-link link-dark px-2">|카카오 정보|</a>
		</sec:authorize>
	</section>
	<c:import url="./template/footer.jsp"></c:import>
</body>
</html>
