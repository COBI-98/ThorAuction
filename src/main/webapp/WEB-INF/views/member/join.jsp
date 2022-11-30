<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>필수정보입력 :: 토실대</title>

	<!-- JQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    
    <!-- SweetAlert2 -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.css">
	<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>

    <!-- 회원가입 CSS -->
    <link rel="stylesheet" href="/css/member/join.css">

    <!-- 회원가입 JS -->
    <script defer src="/js/member/join.js"></script>
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
                            <i class="register-header__step-dot">step 2</i>
                            <i class="register-header__step-dot register-header__step-dot--on">step 3</i>
                            <i class="register-header__step-dot">step 4</i>
                        </div>
                    </div>

                    <form action="join" method="POST" id="joinform">
                        
                        <input type="hidden" name="id" value="${param.id}">
                        <input type="hidden" name="pw" value="${param.pw}">
                        <div class="sign-up">
                            <h2 class="sign-up__title">개인정보입력</h2>
                            <div class="sign-up__sub">회원가입을 위해서 핸드폰 인증이 진행되며, <br>인증이 완료되기 전까지 회원가입이 완료가 되지 않습니다.</div>
                            
                            <!-- 이름 -->
                            <div class="member-input">
                                <div class="member-input__state">
                                    <input id="name" name="name" class="member-input__box" placeholder="이름" type="text" autocomplete="off">                                    
                                    <span class="member-input__valid-wrapper"></span>
                                </div>
                                <div class="sign-up__input-error-message nameErrorMessage"></div>
                            </div>
                            
                            <!-- 생년월일 -->
                            <div class="member-input">
                                <div class="member-input__state">
                                    <input id="birth" class="member-input__box" name="birth" maxlength='6' placeholder="생년월일" type="text" autocomplete="off">
                                    <span class="member-input__valid-wrapper"></span>
                                </div>
                                <div class="sign-up__input-error-message birthErrorMessage"></div>
                            </div>

                            <!-- 전화번호 -->
                            <div class="member-input">
                                <div class="member-input__state" style="padding-bottom: 0px;">
                                    <div class="container">
                                        <input id="phone" oninput="autoHyphen(this)" class="member-input__box" name="phone" maxlength='13' placeholder="전화번호" type="text" autocomplete="off">
                                        <button id="confirm__btn" type="button" class="member-button confirm__btn">인증번호 받기</button>
                                    </div>
                                    <span class="member-input__valid-wrapper"></span>
                                </div>
                                <div class="sign-up__input-error-message phoneErrorMessage"></div>
                                <div class="member-input__state">
                                    <input id="memberInput9300" class="member-input__box" readonly="readonly" placeholder="인증번호를 입력하세요" type="text" autocomplete="off" name="cnNum">                                    
                                    <span class="member-input__valid-wrapper"></span>
                                </div>
                                <div class="sign-up__input-error-message cnErrorMessage">인증이 필요합니다</div>
                                <div class="sign-up__input-error-message cnErrorMessage">인증번호를 다시 확인해주세요</div>
                                <div class="sign-up__input-error-message cnErrorMessage">인증이 성공했습니다</div>
                            </div>

                            <!-- 이메일 -->
                            <div class="member-input">
                                <div class="member-input__state">
                                    <input id="email" class="member-input__box" placeholder="이메일" type="email" autocomplete="off" name="email">
                                    <span class="member-input__valid-wrapper"></span>
                                </div>
                                <div class="sign-up__input-error-message emailErrorMessage"></div>
                            </div>

                            <!-- 우편번호 -->
                            <div class="member-input">
                                <div class="member-input__state" style="padding-bottom: 0px;">
                                    <div class="container">
                                    <input id="post" name="post" class="member-input__box" readonly="readonly" placeholder="우편번호" type="text" autocomplete="off">
                                    <button type="button" onclick="execution_daum_address()" class="member-button confirm__btn">검색</button>
                                    <span class="member-input__valid-wrapper"></span>
                                </div>
                                </div>
                                <div class="sign-up__input-error-message postErrorMessage"></div>
                            </div>

                            <!-- 주소 -->
                            <div class="member-input">
                                <div class="member-input__state">
                                    <input id="addr" name="addr" class="member-input__box" readonly="readonly" placeholder="주소" type="text" autocomplete="off">
                                    <span class="member-input__valid-wrapper"></span>
                                </div>
                                <div class="sign-up__input-error-message addrErrorMessage"></div>
                            </div>

                            <!-- 상세주소 -->
                            <div class="member-input">
                                <div class="member-input__state">
                                    <input id="addr2" name="addr2" class="member-input__box" readonly="readonly" placeholder="상세주소" type="text" autocomplete="off">
                                    <span class="member-input__valid-wrapper"></span>
                                </div>
                            </div>
                            

                            <div class="sign-up__l-btn">
                                <button type="button" id="join_submit_btn" class="member-button sign-up__btn">가입하기</button>
                                <button type="button" id="join_cancel_btn" class="member-button cancel-button sign-up__btn-cancel">취소</button>
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
    
    <!-- 다음 주소록 API -->
    <script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</body>
</html>