<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지 :: 토실대</title>
<link href="/images/bidcoin_coin.png" rel="shortcut icon" type="image/x-icon">

	<!-- JQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    
    <!-- SweetAlert2 -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.css">
	<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>

    <!-- 비밀번호 체크 CSS -->
    <link rel="stylesheet" href="/css/mypage/checkpw.css">

	<!-- 비밀번호 체크 JS -->    
    <script defer src="/js/mypage/checkpw.js"></script>
</head>
<body>
 <div id="root">
        <div class="app">
            <div class="member-card-layout">
                <div class="member-card-layout__container">
                    <div class="member-card-layout__inner">
                        <h1 class="member-card-layout__logo">
                            <a href="../"><img class="member-card-layout__logo-image" src="/images/bidcoin_logo.png" alt="비드코인"></a>
                        </h1>
                        <div class="check-password">
                            <h2 class="check-password__title">정보 수정</h2>
                            <div class="check-password__sub">회원정보 확인을 위해 비밀번호를 한번 더 입력해 주세요.</div>
                            
                            <form action="checkpw" method="POST" id="checkpw_form">
                                <div class="member-input">
                                    <div class="member-input__state member-input__state--value member-input__state--disabled">
                                        <input id="id" class="member-input__box" type="text" readonly="readonly" autocomplete="off" name="id" value="${memberDB.id}">
                                        <span class="member-input__valid-wrapper"></span>
                                    </div>
                                </div>
                                
                                <div class="member-input">
                                    <div class="member-input__state">
                                        <input id="pw" class="member-input__box" type="password" placeholder="비밀번호" autocomplete="off" name="pw">
                                        <span class="member-input__valid-wrapper"></span>
                                    </div>
                                </div>

                                <div class="check-password__l-modal-btn">
                                    <button type="button" id="checkpw_btn" class="member-button check-password__btn">로그인</button>
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