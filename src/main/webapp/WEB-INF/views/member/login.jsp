<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/member/login.css">
</head>
<body>
	<div id="root">
		<div class="app">
			<div class="member-card-layout">
				<div class="member-card-layout__container">
					<div class="member-card-layout__inner">
						<h1 class="member-card-layout__logo">
							<img class="member-card-layout__logo-image" src="/images/member/Odin.png" alt="토실대">
						</h1>

						<div class="login">
							<form>
								<div class="member-input">
									<div class="member-input__state">
										<input id="memberInput8029" class="member-input__box" type="text" autocomplete="off" name="userId"
											value="">
										<label for="memberInput8029" class="member-input__label">아이디</label>
										<span class="member-input__valid-wrapper"></span>
									</div>
								</div>

								<div class="member-input">
									<div class="member-input__state">
										<input id="memberInput6205" class="member-input__box" type="password" autocomplete="off"
											name="userPw" value="">
										<label for="memberInput6205" class="member-input__label">비밀번호</label>
										<span class="member-input__valid-wrapper"></span>
									</div>
								</div>

								<div class="login__l">
									<div class="login__checkbox">
										<div class="member-checkbox">
											<span class="member-checkbox__state"> <input id="memberCheckbox1444" type="checkbox"
												class="member-checkbox__input">
											</span>
											<label for="memberCheckbox1444" class="member-checkbox__label">로그인 상태 유지하기</label>
										</div>
									</div>
								</div>

								<button type="submit" class="member-button login__btn" disabled="">로그인</button>

								<div class="login__l-sign-up">
									<span class="login__sign-up-link"> <a class="member-link" href="#">아이디 찾기</a> <span
										class="member-link__span">|</span> <a class="member-link" href="#">비밀번호 찾기</a> <span
										class="member-link__span">|</span> <a class="member-link" href="#">회원가입</a>
									</span>
								</div>

								<div class="login__l-or">OR</div>

								<div class="simple-login">
									<h2 class="login__fb-title">SNS계정으로 간편 로그인/회원가입</h2>
									<a href="/oauth2/authorization/kakao">
										<img src="/images/member/icons8-free-instant-messaging-app-for-cross-platform-devices-48.png"
											class="kakao-button__img" alt="카카오로고">
									</a>

									<a href="#">
										<img src="/images/member/icons8-구글-로고.svg" class="google-button__img" alt="구글로고">
									</a>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>