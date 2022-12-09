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
<style>
input {
	border-top-width: 0px;
	border-right-width: 0px;
	border-left-width: 0px;
	border-bottom-width: 1px;
	text-decoration: none;
	border-style: solid;
	outline: none;
	padding-bottom: 8px;
}
</style>
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
							<img class="register-header__logo-image" src="/images/Bitcoin.png" alt="토실대">
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

					<form action="./kakaoLogin" method="post" id="frm">

						<c:choose>
							<c:when test="${kakaoInfo != null }">
								<input type="hidden" id="kaName" name="kaNickName" value="${kakaoInfo.kaNickName }">
							</c:when>
							<c:when test="${naverInfo != null }">
								<input type="hidden" id="kaName" name="NaNickName" value="${naverInfo.username }">
							</c:when>
							<c:otherwise>error</c:otherwise>
						</c:choose>

						<section class="container-fluid justify-content-center col-lg-11">
							<table class="table table-borderless">
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
												<input type="text" name="kaBirth" class="birth">
											</c:when>
											<c:when test="${naverInfo != null }">
												<input type="text" name="NaBirth" class="birth">
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
												<input type="text" name="kaPost" class="post">
											</c:when>
											<c:when test="${naverInfo != null }">
												<input type="text" name="NaPost" class="post">
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
												<input type="text" name="kaAddr" class="addr">
											</c:when>
											<c:when test="${naverInfo != null }">
												<input type="text" name="NaAddr" class="addr">
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
												<input type="text" name="kaAddrDetail" class="addrDetail">
											</c:when>
											<c:when test="${naverInfo != null }">
												<input type="text" name="NaAddrDetail" class="addrDetail">
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
												<input type="text" name="kaPhone" class="phone">
											</c:when>
											<c:when test="${naverInfo != null }">
												<input type="text" name="NaPhone" class="phone">
											</c:when>
											<c:otherwise>error</c:otherwise>
										</c:choose>
									</td>
								</tr>
							</table>
							<br>
							<div style="text-align: center;">
								<button class="btn btn-outline-dark" type="submit" id="join_btn">가입하기</button>
							</div>
						</section>
					</form>
				</div>
			</div>
		</div>
	</div>
	<c:import url="../template/footer.jsp"></c:import>
</body>
</html>