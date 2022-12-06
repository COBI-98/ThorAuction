<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../template/boot.jsp"></c:import>
<link href="/css/reset.css" rel="stylesheet">
<link href="/images/Thor.jpg" rel="shortcut icon" type="image/x-icon">
<link rel="stylesheet" href="/css/member/join.css">
</head>
<script defer src="/js/admin/kakaoLoginCheck.js"></script>
<body>
	<c:import url="../template/header.jsp"></c:import>
	<div id="root">
		<div class="app">
			<div class="member-scroll-layout">
				<div class="member-scroll-layout__bg"></div>
				<div class="member-scroll-layout__inner">
					<div class="register-header">
						<h1 class="register-header__logo">
							<img class="register-header__logo-image" src="/images/member/Odin.png" alt="토실대">
						</h1>
						<div class="register-header__step">
							<i class="register-header__step-dot">step 1</i> <i
								class="register-header__step-dot register-header__step-dot--on">step 2</i> <i
								class="register-header__step-dot">step 3</i> <i class="register-header__step-dot">step 4</i>
						</div>
					</div>
					<c:choose>
						<c:when test="${kakaoInfo != null }">
							<input type="hidden" id="kemail" name="kaEmail" value="${kakaoInfo.kaEmail }">
						</c:when>
						<c:when test="${naverInfo != null }">
							<input type="hidden" id="kemail" name="kaEmail" value="${naInfo.NaEmail }">
						</c:when>
						<c:otherwise>error</c:otherwise>
					</c:choose>

					<form action="./kakaoLogin" method="post">

						<c:choose>
							<c:when test="${kakaoInfo != null }">
								<input type="hidden" id="kaName" name="kaNickName" value="${kakaoInfo.kaNickName }">
							</c:when>
							<c:when test="${naverInfo != null }">
								<input type="hidden" id="kaName" name="NaNickName" value="${naverInfo.username }">
							</c:when>
							<c:otherwise>error</c:otherwise>
						</c:choose>
						<table>
							<tr>
								<th>이름</th>
								<c:choose>
									<c:when test="${kakaoInfo != null }">
										<td>${kakaoInfo.kaName }</td>
									</c:when>
									<c:when test="${naverInfo != null }">
										<td>${naverInfo.name }</td>
									</c:when>
									<c:otherwise>error</c:otherwise>
								</c:choose>
							</tr>
							<tr>
								<th>생년월일</th>
								<td>
									<c:choose>
										<c:when test="${kakaoInfo != null }">
											<input type="text" name="kaBirth">
										</c:when>
										<c:when test="${naverInfo != null }">
											<input type="text" name="NaBirth">
										</c:when>
										<c:otherwise>error</c:otherwise>
									</c:choose>
								</td>
							</tr>
							<tr>
								<th>우편번호</th>
								<td>
									<c:choose>
										<c:when test="${kakaoInfo != null }">
											<input type="text" name="kaPost">
										</c:when>
										<c:when test="${naverInfo != null }">
											<input type="text" name="NaPost">
										</c:when>
										<c:otherwise>error</c:otherwise>
									</c:choose>
								</td>
							</tr>
							<tr>
								<th>주소</th>
								<td>
									<c:choose>
										<c:when test="${kakaoInfo != null }">
											<input type="text" name="kaAddr">
										</c:when>
										<c:when test="${naverInfo != null }">
											<input type="text" name="NaAddr">
										</c:when>
										<c:otherwise>error</c:otherwise>
									</c:choose>
								</td>
							</tr>
							<tr>
								<th>상세주소</th>
								<td>
									<c:choose>
										<c:when test="${kakaoInfo != null }">
											<input type="text" name="kaAddrDetail">
										</c:when>
										<c:when test="${naverInfo != null }">
											<input type="text" name="NaAddrDetail">
										</c:when>
										<c:otherwise>error</c:otherwise>
									</c:choose>
								</td>
							</tr>
							<tr>
								<th>전화번호</th>
								<td>
									<c:choose>
										<c:when test="${kakaoInfo != null }">
											<input type="text" name="kaPhone">
										</c:when>
										<c:when test="${naverInfo != null }">
											<input type="text" name="NaPhone">
										</c:when>
										<c:otherwise>error</c:otherwise>
									</c:choose>
								</td>
							</tr>
						</table>
						<button type="submit" id="join_btn">가입하기</button>
					</form>
					<br>
					<br>

					<div class="sign-up__go-to-login">
						이미 회원이신가요?
						<a href="./login" type="button" class="sign-up__go-to-login-btn">로그인하기</a>
						<a href="#" type="button" class="sign-up__go-to-login-btn">취소</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<c:import url="../template/footer.jsp"></c:import>
</body>
</html>