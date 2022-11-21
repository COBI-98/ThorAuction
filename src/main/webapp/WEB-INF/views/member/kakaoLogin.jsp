<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" href="/css/member/join.css">
<body>
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
					<form action="./kakaoLogin" method="post">
						<input type="text" name="kaNickName" value="${kakaoInfo.kaNickName }">
						<table>
							<tr>
								<th>이름</th>
								<td>${kakaoInfo.kaName }</td>
							</tr>
							<!-- <tr>
								<th>아이디</th>
								<td>
									<input type="text" id="id" name="id">

									<button type="button" class="idCheck">중복확인</button>

									<p class="result">
										<span class="msg"></span>
									</p>
								</td>
							</tr>
							<tr>
								<th>비밀번호</th>
								<td>
									<input type="password" name="pw">
								</td>
							</tr>
							<tr>
								<th>이름</th>
								<td>
									<input type="text" name="name">
								</td>
							</tr> -->
							<tr>
								<th>생년월일</th>
								<td>
									<input type="text" name="kaBirth">
								</td>
							</tr>
							<!-- <tr>
								<th>이메일</th>
								<td>
									<input type="email" name="kaEmail">
								</td>
							</tr> -->
							<tr>
								<th>우편번호</th>
								<td>
									<input type="text" name="kaPost">
								</td>
							</tr>
							<tr>
								<th>주소</th>
								<td>
									<input type="text" name="kaAddr">
								</td>
							</tr>
							<tr>
								<th>상세주소</th>
								<td>
									<input type="text" name="kaAddrDetail">
								</td>
							</tr>
							<tr>
								<th>전화번호</th>
								<td>
									<input type="text" name="kaPhone">
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
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>