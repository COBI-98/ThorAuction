<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>결제내역 :: 비드코인</title>
	<link href="/images/bidcoin_coin.png" rel="shortcut icon" type="image/x-icon">
	
	<c:import url="../template/boot.jsp"></c:import>
	<link href="/css/reset.css" rel="stylesheet">
	
	<style type="text/css">
		.msm-profile .mfs_form {
			max-width: 100%;
			font-size: 14px;
		}
		.submit_btn {
		    background-color: rgb(254, 165, 50) !important;
		    color: white !important;
		}
	</style>
	
	<link rel="stylesheet" id="msm-semantic-css-css" href="https://cdn.dosomp.com/wp-content/plugins/mshop-mcommerce-premium-s2/lib/mshop-members-s2/assets/vendor/semantic/semantic.min.css?ver=2.0.13" type="text/css" media="all">
	<link rel="stylesheet" id="msm-font-awesome-css" href="https://cdn.dosomp.com/wp-content/plugins/mshop-mcommerce-premium-s2/lib/mshop-members-s2/assets/font-awesome/css/font-awesome.min.css?ver=2.0.13" type="text/css" media="all">
	<link rel="stylesheet" id="msm-form-style-css" href="https://cdn.dosomp.com/wp-content/plugins/mshop-mcommerce-premium-s2/lib/mshop-members-s2/assets/css/mshop-members-form.css?ver=2.0.13" type="text/css" media="all">
	<link rel="stylesheet" id="msm-semantic-calendar-css-css" href="https://cdn.dosomp.com/wp-content/plugins/mshop-mcommerce-premium-s2/lib/mshop-members-s2/assets/vendor/semantic-ui-calendar/calendar.min.css?ver=2.0.13" type="text/css" media="all">
	
	<!-- JQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    
    <!-- SweetAlert2 -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.css">
	<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	
	<!-- 마이페이지 CSS -->
	<link rel="stylesheet" href="/css/mypage/info.css">

</head>
<body>
	<c:import url="../template/header.jsp"></c:import>
	<section class="container d-flex flex-wrap justify-content-center">
	
	<div class="main-page-wrapper" style="margin : 0px; width: 100%;">
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
												<li class="woocommerce-MyAccount-navigation-link woocommerce-MyAccount-navigation-link--dashboard">
													<a href="../mypage/info?id=${memberDB.id}">대시보드</a>
												</li>
												<li class="woocommerce-MyAccount-navigation-link woocommerce-MyAccount-navigation-link--orders ">
													<a href="../mypage/likeProduct?id=${memberDB.id}">관심내역</a>
												</li>
												<li class="woocommerce-MyAccount-navigation-link woocommerce-MyAccount-navigation-link--auctions">
													<a href="../mypage/bidhistory?id=${memberDB.id}">입찰내역</a>
												</li>													
												<li class="woocommerce-MyAccount-navigation-link woocommerce-MyAccount-navigation-link--auctions">
													<a href="/mypage/successfulBid?id=${memberDB.id}">낙찰내역</a>
												</li>
												<li class="woocommerce-MyAccount-navigation-link woocommerce-MyAccount-navigation-link--msm-profile is-active">
													<a href="../mypage/payhistory?id=${memberDB.id}">결제내역</a>
												</li>
												<li	class="woocommerce-MyAccount-navigation-link woocommerce-MyAccount-navigation-link--msm-unsubscribe">
													<a href="../mypage/appProduct?id=${memberDB.id}">신청한 상품</a>
												</li>
												<li	class="woocommerce-MyAccount-navigation-link woocommerce-MyAccount-navigation-link--wishlist">
													<a href="../mypage/checkpw?id=${memberDB.id}">정보 수정</a>
												</li>
												<li class="woocommerce-MyAccount-navigation-link woocommerce-MyAccount-navigation-link--wishlist ">
													<a href="../mypage/updatepw?id=${memberDB.id}">비밀번호 변경</a>
												</li>
												<li class="woocommerce-MyAccount-navigation-link woocommerce-MyAccount-navigation-link--customer-logout">
													<a href="../mypage/delete?id=${memberDB.id}">회원탈퇴</a>
												</li>
											</ul>
										</nav>
									</div>
									<!-- .woodmart-my-account-sidebar -->
									<div class="woocommerce-MyAccount-content">
										<div class="woocommerce-notices-wrapper"></div>
										<div style="max-width: 800px; margin: 0 auto;" class="msm-profile">
											<div id="mshop_form_2702" class="ui mfs_form " style="">
												<style></style>
													<div class="field" style="">
													<c:choose>
														<c:when test="${not empty pays}">
														<table class="table" style="font-size: 15px;">
														  <thead>
														    <tr>
														      <th scope="col">주문번호</th>
														      <th scope="col">금액</th>
														      <th scope="col">상태</th>
														      <th scope="col">날짜</th>
														    </tr>
														  </thead>
														  <tbody>
														    <c:forEach items="${pays}" var="pay">
														    	<tr>
														    		<td scope="row">
														    		<c:set var="merchant" value="${pay.payMerchant}" />
																    ${fn:substring(merchant,9,25) }
														      		</td>
														      <td><fmt:formatNumber value="${pay.payTotal}" pattern="###,###,###,###"/>원</td>
														      <c:choose>
														      	<c:when test="${pay.payResult == 'paid'}"><td style="color: blue; font-weight: bold;">결제성공</td></c:when>
														      	<c:when test="${pay.payResult == 'ready'}"><td>결제대기</td style="color: red; font-weight: bold;"></c:when>
														      	<c:when test="${pay.payResult == 'failed'}"><td style="color: red; font-weight: bold; ">결제실패</td></c:when>
														      </c:choose>
														      <td>
														      <c:set var="date" value="${pay.payDate}" />
														      ${fn:substring(date, 0, 16) }
														      </td>
														    </tr>
														    </c:forEach>
														  </tbody>
														</table>
													</div>
													</c:when>
														<c:otherwise>
															<table class="table" style="font-size: 15px;">
																<thead>
																	<tr>
																		<th scope="col">상품번호</th>
																		<th scope="col">이미지</th>
																		<th scope="col">상품명</th>
																		<th scope="col">등록일</th>
																		<th scope="col">삭제</th>
																	</tr>
																</thead>
															</table>
															<div class="null">결제한 내역이 없습니다</div>
														</c:otherwise>
													</c:choose>
												<div class="mshop-members-message"></div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</article>
					<!-- #post -->
				</div>
				<!-- .site-content -->
			</div>
			<!-- .main-page-wrapper -->
		</div>
		<!-- end row -->
	</div>
	<script>
		var mfs_form_2702 = {
			ajaxurl : '/wp-admin/admin-ajax.php',
			fieldRules : '{"first_name":{"rules":[{"type":"empty","prompt":"\uc774 \ub984\uc744(\ub97c) \uc785\ub825\ud558\uc138\uc694."}]},"billing_email":{"rules":[{"type":"empty","prompt":"\uc774\uba54\uc77c\uc744(\ub97c) \uc785\ub825\ud558\uc138\uc694."}]},"billing_phone":{"rules":[{"type":"empty","prompt":"\uc804\ud654\ubc88\ud638\uc744(\ub97c) \uc785\ub825\ud558\uc138\uc694."}]},"confirm_password":{"rules":[{"type":"match[password]","prompt":"\ube44\ubc00\ubc88\ud638\uac00 \uc77c\uce58\ud558\uc9c0 \uc54a\uc2b5\ub2c8\ub2e4."}]}}',
			redirectURL : '',
			msm_nonce : 'e8d033345a',
		};

		setTimeout(function() {
			jQuery('#mshop_members_popup').css('display', 'block');
		});
		jQuery(document).ready(function($) {
			$('.ui.checkbox.email_agreement').checkbox('check');
		});
	</script>
		
			
		
	</section>
	<c:import url="../template/footer.jsp"></c:import>
</body>
</html>