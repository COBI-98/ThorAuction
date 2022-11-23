<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- security tag -->
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>토르의 실시간 대장간</title>
<c:import url="../template/boot.jsp"></c:import>
<link href="/css/reset.css" rel="stylesheet">
<link href="/images/Thor.jpg" rel="shortcut icon" type="image/x-icon">
</head>
<script defer src="/js/admin/kakaoJoin.js"></script>
<body>
	<c:import url="../template/header.jsp"></c:import>
	<section class="container justify-content-center" style="text-align: center;">

		<div class="login__l-sign-up">
			<span class="login__sign-up-link"> <a class="member-link" class="btn btn-outline-none"
					href="./join2">회원가입</a>
			</span>
		</div>
		<br>
		<br>
		<div class="login__l-or">OR</div>
		<br>
		<br>
		<div class="simple-login">
			<h2 class="login__fb-title">SNS계정으로 간편 로그인/회원가입</h2>
			<!-- /oauth2/authorization/kakao -->

			<input type="hidden" id="nickName" name="kaNickName" value="${kakaoInfo.kaNickName }">
			<sec:authorize access="!isAuthenticated()">
				<c:choose>
					<c:when test="${detailData == null }">
						<!-- onclick="kakao()" -->
						<button type="button" id="kakaoJoinBtn" onclick="kakao()" class="btn btn-outline-none">
							<img src="/images/member/icons8-free-instant-messaging-app-for-cross-platform-devices-48.png"
								class="kakao-button__img" alt="카카오로고">
						</button>
						<!-- <a href="/oauth2/authorization/kakao" id="kakaoJoinBtn">
							<img src="/images/member/icons8-free-instant-messaging-app-for-cross-platform-devices-48.png"
								class="kakao-button__img" alt="카카오로고">
						</a> -->
					</c:when>
					<c:otherwise>
						<a href="/" id="kakaoJoinBtn">
							<img src="/images/member/icons8-free-instant-messaging-app-for-cross-platform-devices-48.png"
								class="kakao-button__img" alt="카카오로고">
						</a>
						<!--<a href="/oauth2/authorization/kakao" id="kakaoJoinBtn" onclick="kakao()">
							<img src="/images/member/icons8-free-instant-messaging-app-for-cross-platform-devices-48.png"
								class="kakao-button__img" alt="카카오로고">
						</a> -->
					</c:otherwise>
				</c:choose>
			</sec:authorize>
			<!-- 로그인 후 -->
			<sec:authentication property="Principal" var="member" />
			<sec:authorize access="isAuthenticated()">
				<a href="/" id="kakaoJoinBtn2">
					<img src="/images/member/icons8-free-instant-messaging-app-for-cross-platform-devices-48.png"
						class="kakao-button__img" alt="카카오로고">
				</a>
			</sec:authorize>

			<a href="#">
				<img src="/images/member/icons8-구글-로고.svg" class="google-button__img" alt="구글로고">
			</a>
		</div>

	</section>
	<c:import url="../template/footer.jsp"></c:import>
</body>
</html>
