<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>낙찰내역 :: 비드코인</title>
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
<link rel="stylesheet" href="/css/application.css">
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
														<a href="../mypage/likeProduct?id=${memberDB.id}">관심내역</a>
													</li>
													<li class="woocommerce-MyAccount-navigation-link woocommerce-MyAccount-navigation-link--auctions">
														<a href="../mypage/bidhistory?id=${memberDB.id}">입찰내역</a>
													</li>
													<li class="woocommerce-MyAccount-navigation-link woocommerce-MyAccount-navigation-link--auctions is-active"">
														<a href="/mypage/successfulBid?id=${memberDB.id}">낙찰내역</a>
													</li>
													<li class="woocommerce-MyAccount-navigation-link woocommerce-MyAccount-navigation-link--msm-profile">
														<a href="../mypage/payhistory?id=${memberDB.id}">결제내역</a>
													</li>
													<li class="woocommerce-MyAccount-navigation-link woocommerce-MyAccount-navigation-link--msm-unsubscribe">
														<a href="../mypage/appProduct?id=${memberDB.id}">신청한 상품</a>
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
														<form action="/mypage/successfulBid?id=123"  class="row formStyle">
															<div>
															<label class="test_obj">
																<input type="hidden" name="id" value="${memberDB.id}">
																<input type="submit" name="auctionSort" <c:if test="${param.auctionSort == null || param.auctionSort == 1}">class="checked"</c:if> value="1">
																<span>온라인경매 낙찰내역</span>
																
															</label>
															
															<label class="test_obj">
																<input type="submit" name="auctionSort" <c:if test="${param.auctionSort == 0}">class="checked"</c:if> value="0" >
																<span>실시간경매 낙찰내역</span>
															</label>
															</div>
														</form>

													<c:choose>
														<c:when test="${not empty saleProducts && (param.auctionSort == null || param.auctionSort == 1)}">
														<div class="mypage_content">
															<div class="pt5 pb5">총 <strong class="import">${count}</strong> 건의 낙찰내역이 있습니다.</div>
															<input type="hidden" name="id" value="${memberDB.id}">
															<div class="myorder">
																<c:forEach items="${saleProducts}" var="product" varStatus="status">
																		<c:choose>
																			
																			<c:when test="${product.deadlineInfo == true && product.productVOs[0].status == true && product.maxBidAmount != product.bidAmountVOs[0].bidAmount}">
																			<li class="list">
																			<div class="shopinfo">경매 시작일: <fmt:formatDate value="${product.productDate}" pattern="yyyy-MM-dd HH:mm:ss"/> / 낙찰금액: <fmt:formatNumber value="${product.maxBidAmount}" pattern="###,###,###,###"/>원</div>
																				<div class="orderlist">
																					<ul class="prolist">
																						<li>
																							<a href="/product/detail?productId=${product.productId}">
																							<c:forEach items="${product.productVOs}" var="products">
																								<div class="imgbox"><img src="/file/product/${products.productFileVOs[0].fileName}" width="100px"></div>
																								<div class="name">${products.productName}</div>
																							</c:forEach>
																									<input type="hidden" name="productId" value="${product.bidAmountVOs[0].productId}">
																									<div class="price" style="margin-left: 100px;">
																										입찰금액: <fmt:formatNumber value="${product.bidAmountVOs[0].bidAmount}" pattern="###,###,###,###"/>원<br> 입찰일: <fmt:formatDate value="${product.bidAmountVOs[0].bidDate}" pattern="yyyy-MM-dd HH:mm:ss"/><br>
																									</div>
																									<input type="hidden" class="productDate" name="tdate" value="${product.productDate}">
																									<input type="hidden" class="timeLength" value="${product.productVOs[0].auctionPeriod}">
																									<div class="timedate" id="timertest" style="margin-left: 100px;">
								
																									</div>
																							</a>
																						</li>
																					</ul>
																					<div class="statebox">
																							<div class="state">낙찰 실패</div>
																					</div>
																				</div>
																			</li>
																			</c:when>
																			<c:when test="${product.deadlineInfo == true && product.productVOs[0].status == true && product.maxBidAmount == product.bidAmountVOs[0].bidAmount}">
																				<!-- 마감된 상품이 내가 최고가로 입찰을 했다면 낙찰내역으로 -->
																				<li class="list">
																				<div class="shopinfo">경매 시작일: <fmt:formatDate value="${product.productDate}" pattern="yyyy-MM-dd HH:mm:ss"/> / 낙찰금액: <fmt:formatNumber value="${product.maxBidAmount}" pattern="###,###,###,###"/>원</div>
																					<div class="orderlist">
																						<ul class="prolist">
																							<li>
																								<a href="/product/detail?productId=${product.productId}">
																								<c:forEach items="${product.productVOs}" var="products">
																									<div class="imgbox"><img src="/file/product/${products.productFileVOs[0].fileName}" style="width="100px; height=100px;"></div>
																									<div class="name">${products.productName}</div>
																								</c:forEach>
																										<input type="hidden" name="productId" value="${product.bidAmountVOs[0].productId}">
																										<div class="price" style="margin-left: 100px;">
																											입찰금액: <fmt:formatNumber value="${product.bidAmountVOs[0].bidAmount}" pattern="###,###,###,###"/>원<br> 입찰일: <fmt:formatDate value="${product.bidAmountVOs[0].bidDate}" pattern="yyyy-MM-dd HH:mm:ss"/><br>
																										</div>
																										<input type="hidden" class="productDate" name="tdate" value="${product.productDate}">
																										<input type="hidden" class="timeLength" value="${product.productVOs[0].auctionPeriod}">
																										<div class="timedate" id="timertest" style="margin-left: 100px;">
									
																										</div>
																								</a>
																							</li>
																						</ul>
																						<div class="statebox">
																								<div class="state">낙찰 성공</div>
																						</div>
																					</div>
																				</li>
																			</c:when>
																			
																			<c:otherwise>
																				
																			
																			</c:otherwise>
																		</c:choose>
																		
																			
																	</c:forEach>
																
															</div>
															<div class="he20"></div>
														</div>
													</div>
													</c:when>
													<c:when test="${not empty userPayHistory && param.auctionSort == 0}">
														<div class="mypage_content">
															<div class="pt5 pb5">총 <strong class="import">${userPayHistory.size()}</strong> 건의 낙찰내역이 있습니다.</div>
															<input type="hidden" name="id" value="${memberDB.id}">
															<div class="myorder">
																<c:forEach items="${userPayHistory}" var="product" varStatus="status">
																		<li class="list">
																				<div class="orderlist">
																					<ul class="prolist">
																						<li>
																							<c:forEach items="${product.productVOs}" var="products">
																								<div class="imgbox"><img src="/file/product/${products.productFileVOs[0].fileName}" width="100px"></div>
																								<div class="name">${products.productName}</div>
																							</c:forEach>
																									<div class="price">
																										입찰금액: <fmt:formatNumber value="${product.cashe}" pattern="###,###,###,###"/>원<br> 입찰일: <fmt:formatDate value="${product.regDate}" pattern="yyyy-MM-dd HH:mm:ss"/><br>
																									</div>
																							</a>
																						</li>
																					</ul>
																					<div class="statebox">
																							<div class="state">낙찰</div>
																					</div>
																				</div>
																			</li>
																			
																			
																		
																			
																	</c:forEach>
																
															</div>
															<div class="he20"></div>
														</div>
													</div>
													</c:when>
														<c:otherwise>
															<table class="table" style="font-size: 15px;">
															<div class="pt5 pb5">총 <strong class="import">0</strong> 건의 낙찰내역이 있습니다.</div>
																
															<div class="null">낙찰한 내역이 없습니다</div>
															</table>
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
	<script src="/js/timerTest.js"></script> 
</body>
</html>