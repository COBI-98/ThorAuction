<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지 :: 비드코인</title>
<c:import url="../template/boot.jsp"></c:import>
<link href="/images/bidcoin_coin.png" rel="shortcut icon" type="image/x-icon">

<!-- 마이페이지 CSS -->
<link rel="stylesheet" href="/css/mypage/info.css">
</head>
<body>
	<c:import url="../template/header.jsp"></c:import>
	<section class="container d-flex flex-wrap justify-content-center">
		<div class="main-page-wrapper" style="margin: 0px;">
        <!-- MAIN CONTENT AREA -->
        <div class="container">
            <div class="row content-layout-wrapper align-items-start">
                <div class="site-content col-lg-12 col-12 col-md-12" role="main">
                    <article id="post-118" class="post-118 page type-page status-publish hentry">
                        <div class="entry-content">
                            <div class="woocommerce">
                                <div class="woocommerce-my-account-wrapper">
                                    <div class="woodmart-my-account-sidebar">
                                        <h3 class="woocommerce-MyAccount-title entry-title">마이페이지</h3>
                                        <nav class="woocommerce-MyAccount-navigation">
                                            <ul>
                                                <li class="woocommerce-MyAccount-navigation-link woocommerce-MyAccount-navigation-link--dashboard is-active">
                                                    <a href="#">대시보드</a>
                                                </li>
                                                <li class="woocommerce-MyAccount-navigation-link woocommerce-MyAccount-navigation-link--orders">
                                                    <a href="#">관심내역</a>
                                                </li>
                                                <li class="woocommerce-MyAccount-navigation-link woocommerce-MyAccount-navigation-link--auctions">
                                                    <a href="#">낙찰내역</a>
                                                </li>
                                                <li class="woocommerce-MyAccount-navigation-link woocommerce-MyAccount-navigation-link--msm-profile">
                                                    <a href="../mypage/payhistory?id=${memberDB.id}">결제내역</a>
                                                </li>
                                                <li class="woocommerce-MyAccount-navigation-link woocommerce-MyAccount-navigation-link--msm-unsubscribe">
                                                    <a href="#">신청한 상품</a>
                                                </li>
                                                <li class="woocommerce-MyAccount-navigation-link woocommerce-MyAccount-navigation-link--wishlist">
                                                    <a href="../mypage/checkpw?id=${memberDB.id}">정보 수정</a>
                                                </li>
                                                <li class="woocommerce-MyAccount-navigation-link woocommerce-MyAccount-navigation-link--wishlist">
                                                    <a href="../mypage/updatepw?id=${memberDB.id}">비밀번호 변경</a>
                                                </li>                                                
                                                <li class="woocommerce-MyAccount-navigation-link woocommerce-MyAccount-navigation-link--customer-logout">
                                                    <a href="../mypage/delete?id=${memberDB.id}">회원탈퇴</a>
                                                </li>
                                            </ul>
                                        </nav>
                                    </div><!-- .woodmart-my-account-sidebar -->
                                    <div class="woocommerce-MyAccount-content">
                                        <div class="woocommerce-notices-wrapper"></div>
                                        <p>안녕하세요, <strong>${memberDB.id}</strong> 님</p>
                                        <p>
                                            현재 <strong>${memberDB.name}</strong> 님의 포인트는 <strong><fmt:formatNumber value="${memberDB.point}" pattern="###,###,###,###"/></strong>	 입니다
                                        </p>

                                        <div class="woodmart-my-account-links">
                                            <div class="dashboard-link">
                                                <a href="#">내 정보</a>
                                            </div>
                                            <div class="orders-link">
                                                <a href="#">관심내역</a>
                                            </div>
                                            <div class="auctions-link">
                                                <a href="#">낙찰내역</a>
                                            </div>
                                            <div class="msm-profile-link">
                                                <a href="./payhistory?id=${memberDB.id}">결제내역</a>
                                            </div>
                                            <div class="msm-unsubscribe-link">
                                                <a href="#">신청한 상품</a>
                                            </div>
                                            <div class="auctions-link update">
                                                <a href="./checkpw?id=${memberDB.id}">정보 수정</a>
                                            </div>                                                                                  
                                            <div class="customer-logout-link">
                                                <a href="./updatepw?id=${memberDB.id}">비밀번호 변경</a>
                                            </div>
                                        </div>
                                        <table class="shop_table shop_table_responsive my_account_orders mshop-members-info">
                                            <tbody>
                                                <tr>
                                                    <td>포인트 충전</td>
                                                    <td>포인트가 부족하신가요? 포인트를 충전해보세요!</td>
                                                    <td>
                                                        <a href="./charge" class="button">충전하기</a>
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div><!-- .woocommerce-my-account-wrapper -->
                                </div>
                            </div>
                        </div>
                    </article><!-- #post -->
                </div><!-- .site-content -->
            </div><!-- .main-page-wrapper -->
        </div> <!-- end row -->
    </div>


	</section>
	<c:import url="../template/footer.jsp"></c:import>
</body>
</html>