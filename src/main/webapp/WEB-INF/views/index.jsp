<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>토르의 실시간 대장간</title>
<c:import url="./template/boot.jsp"></c:import>
<link href="/css/reset.css" rel="stylesheet">
<link href="/images/Thor.jpg" rel="shortcut icon" type="image/x-icon">
</head>
<body>
	<c:import url="./template/header.jsp"></c:import>
	<section class="container d-flex flex-wrap justify-content-center">

		<h1>토르의 실시간 대장간 메인</h1>

		<div class="mt-5">
			<a href="../mypage/update">회원정보 수정</a>
			<a href="../mypage/delete">회원탈퇴</a>
		</div>
		<a href="/oauth2/authorization/kakao">
			<img src="/images/member/kakao_login_logo.png" class="kakao-button__img" alt="">
		</a>
		<a href="../admin/adminpage" class="nav-link link-dark px-2">|카카오 정보|</a>
	</section>
	<c:import url="./template/footer.jsp"></c:import>
</body>
</html>
