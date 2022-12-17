<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>입찰내역 :: 비드코인</title>
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
<link rel="stylesheet" id="msm-form-style-css" href="https://cdn.dosomp.com/wp-content/plugins/mshop-mcommerce-premium-s2/lib/mshop-members-s2/assets/css/mshop-members-form.css?ver=2.0.13"type="text/css" media="all">
<link rel="stylesheet" id="msm-semantic-calendar-css-css" href="https://cdn.dosomp.com/wp-content/plugins/mshop-mcommerce-premium-s2/lib/mshop-members-s2/assets/vendor/semantic-ui-calendar/calendar.min.css?ver=2.0.13" type="text/css" media="all">

<!-- JQuery -->
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<!-- SweetAlert2 -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.css">
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>

<!-- 마이페이지 CSS -->
<link rel="stylesheet" href="/css/mypage/info.css">
<link rel="stylesheet" href="/css/mypage/bidhistory.css">
</head>
<body>
	<c:import url="../template/header.jsp"></c:import>
	<section class="container d-flex flex-wrap justify-content-center">

		<div class="main-page-wrapper" style="margin: 0px; width: 100%;">
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
														<a href="#">관심내역</a>
													</li>
													<li class="woocommerce-MyAccount-navigation-link woocommerce-MyAccount-navigation-link--auctions is-active">
														<a href="../mypage/bidhistory?id=${memberDB.id}">입찰내역</a>
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
													<div class="field" style="">
														<div class="mypage_content">
															<div class="pt5 pb5">총 <strong class="import">${count}</strong> 건의 입찰내역이 있습니다.</div>
															<input type="hidden" name="id" value="${memberDB.id}">
															<div class="myorder">
																<c:forEach items="${saleProducts}" var="product">
																<input type="hidden" name="productId" value="${product.productId}">
																			<li class="list">
																				<div class="shopinfo">경매시작: <fmt:formatDate value="${product.productDate}" pattern="yyyy-MM-dd HH:mm:ss"/> / 현재가: 공사 중...</div>
																				<div class="orderlist">
																					<ul class="prolist">
																						<li>
																							<a href="#">
																							<c:forEach items="${product.productVOs}" var="products">
																								<div class="imgbox"><img src="/file/product/${products.productFileVOs[0].fileName}"></div>
																								<div class="name">${products.productName}
																							</c:forEach>
																									<div class="price">
																										입찰금액: <fmt:formatNumber value="${product.bidAmountVOs[0].bidAmount}" pattern="###,###,###,###"/>원<br> 입찰일: <fmt:formatDate value="${product.bidAmountVOs[0].bidDate}" pattern="yyyy-MM-dd HH:mm:ss"/><br>
																									</div>
																								</div>
																							</a>
																						</li>
																					</ul>
																					<div class="statebox">
																						<div class="state ">입찰</div>
																					</div>
																				</div>
																			</li>
																		
																	</c:forEach>
																
															</div>
															<div class="he20"></div>
														</div>
													</div>
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