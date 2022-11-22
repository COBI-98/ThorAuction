<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>기본정보입력 :: 토실대</title>
    
	<!-- JQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

    <!-- 회원가입 CSS -->
    <link rel="stylesheet" href="/css/member/join.css">

    <!-- 회원가입 JS -->
    <script defer src="/js/member/signup.js"></script>
</head>
<body>
<div id="root">
        <div class="app">
            <div class="member-scroll-layout">
                <div class="member-scroll-layout__bg"></div>
                <div class="member-scroll-layout__inner">
                    <div class="register-header">
                        <h1 class="register-header__logo">
                            <img class="register-header__logo-image" src="/images/member/Testimg.png" alt="토실대 임시로고">
                        </h1>
                        <div class="register-header__step">
                            <i class="register-header__step-dot">step 1</i>
                            <i class="register-header__step-dot register-header__step-dot--on">step 2</i>
                            <i class="register-header__step-dot">step 3</i>
                            <i class="register-header__step-dot">step 4</i>
                        </div>
                    </div>

                    <form action="signup" method="POST" id="signupForm">
                        <div class="sign-up">
                            <h2 class="sign-up__title">기본정보입력</h2>
                            <div class="sign-up__sub">회원가입을 위해서 핸드폰 인증이 진행되며, <br>인증이 완료되기 전까지 회원가입이 완료가 되지 않습니다.</div>
                            
                            <!-- 아이디 -->
                            <div class="member-input">
                                <div class="member-input__state" style="padding-bottom: 0px;">
                                    <div class="container">
                                        <input id="id" class="member-input__box" placeholder="아이디" type="text" autocomplete="off" name="id">                                    
                                        <button type="button" class="member-button confirm__btn idCheck">중복확인</button>
                                    </div>
                                    <span class="member-input__valid-wrapper"></span>
                                </div>
                                <div class="sign-up__input-error-message idErrorMessage"></div>
                            </div>
                            
                            <!-- 비밀번호 -->
                            <div class="member-input">
                                <div class="member-input__state">
                                    <input id="pw" class="member-input__box" placeholder="비밀번호" type="password" autocomplete="off" name="pw">
                                    <span class="member-input__valid-wrapper"></span>
                                </div>
                                <div class="sign-up__input-error-message pwErrorMessage"></div>
                                <!-- <div class="sign-up__input-error-message pwErrorMessage">8~16자 영문 대ㆍ소문자, 숫자를 사용하세요</div> -->
                            </div>

                            <!-- 비밀번호 재확인 -->
                            <div class="member-input">
                                <div class="member-input__state">
                                    <input id="pwEquals" class="member-input__box" placeholder="비밀번호 재확인" type="password" autocomplete="off" name="pwEquals">
                                    <span class="member-input__valid-wrapper"></span>
                                </div>
                                <div class="sign-up__input-error-message pwEqualsErrorMessage"></div>
                            </div>

                            <div class="sign-up__l-btn">
                                <button type="button" id="signup_next_btn" disabled="disabled" class="member-button sign-up__btn">다음</button>
                                <button type="button" id="signup_cancel_btn" class="member-button cancel-button sign-up__btn-cancel">취소</button>
                            </div>
                        </div>
                    </form>

                    <div class="sign-up__go-to-login">이미 회원이신가요?
                        <a href="/member/login" type="button" class="sign-up__go-to-login-btn">로그인하기</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>