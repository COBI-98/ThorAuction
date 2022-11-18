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

					<form>
						<div class="sign-up">
							<h2 class="sign-up__title">기본정보입력</h2>
							<div class="sign-up__sub">
								회원가입을 위해서 핸드폰 인증이 진행되며,
								<br>
								인증이 완료되기 전까지 회원가입이 완료가 되지 않습니다.
							</div>

							<!-- 아이디 -->
							<div class="member-input">
								<div class="member-input__state">
									<input id="memberInput4806" class="member-input__box" placeholder="아이디" type="text"
										autocomplete="off" name="userId" value=""> <span class="member-input__valid-wrapper"></span>
								</div>
								<div class="sign-up__input-error-message idErrorMessage">필수 항목입니다</div>
								<div class="sign-up__input-error-message idErrorMessage">3~15자의 영문 소문자, 숫자만 사용 가능합니다</div>
							</div>

							<!-- 비밀번호 -->
							<div class="member-input">
								<div class="member-input__state">
									<input id="memberInput9300" class="member-input__box" placeholder="비밀번호" type="password"
										autocomplete="off" name="userPw" value=""> <span class="member-input__valid-wrapper"></span>
								</div>
								<div class="sign-up__input-error-message pwErrorMessage">필수 항목입니다</div>
								<div class="sign-up__input-error-message pwErrorMessage">8~16자 영문 대ㆍ소문자, 숫자를 사용하세요</div>
							</div>

							<!-- 비밀번호 재확인 -->
							<div class="member-input">
								<div class="member-input__state">
									<input id="memberInput9300" class="member-input__box" placeholder="비밀번호 재확인" type="password"
										autocomplete="off" name="pwEquals" value=""> <span class="member-input__valid-wrapper"></span>
								</div>
								<div class="sign-up__input-error-message pwEqualsErrorMessage">필수 항목입니다</div>
								<div class="sign-up__input-error-message pwEqualsErrorMessage">비밀번호가 일치하지 않습니다</div>
							</div>

							<div class="sign-up__l-btn">
								<button type="submit" class="member-button sign-up__btn">다음</button>
								<button type="button" class="member-button cancel-button sign-up__btn-cancel">취소</button>
							</div>
						</div>
					</form>
					<br>
					<br>
					<br>
					<div class="login__l-or" style="text-align: center; padding-top: 50px;">OR</div>

					<div class="simple-login" style="text-align: center;">
						<h2 class="login__fb-title">SNS계정으로 간편 로그인/회원가입</h2>
						<!-- javascript:popup() -->
						<a href="/oauth2/authorization/kakao" style="text-decoration: none; padding-right: 15px;">
							<img src="/images/member/icons8-free-instant-messaging-app-for-cross-platform-devices-48.png"
								class="kakao-button__img" alt="카카오로고">
						</a>

						<a href="#">
							<img src="/images/member/icons8-구글-로고.svg" class="google-button__img" alt="구글로고">
						</a>
					</div>

					<div class="sign-up__go-to-login">
						이미 회원이신가요?
						<a href="./login" type="button" class="sign-up__go-to-login-btn">로그인하기</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script>
	var width = '500';
	var height = '400';
	var left = Math.ceil((window.screen.width) / 2);
	var top = Math.ceil((window.screen.height) / 2);
// /oauth2/authorization/kakao
	function popup() {
		var url = "/oauth2/authorization/kakao";
		var name = "popup test";
		var option = "width=" + width + ", height=" + height + ", top=" + top
				+ ", left=" + left + ", location=kakaoLogin"
		window.open(url, name, option);
	}
</script>
</html>