<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>토르의 실시간 대장간</title>
	<c:import url="../template/boot.jsp"></c:import>
	
	<link href="/images/Thor.jpg" rel="shortcut icon" type="image/x-icon">
	
	<link rel="stylesheet" href="/css/chunk48-455aca93.css"> <!-- carousel -->
	<link rel="stylesheet" href="/css/chunk166-a1b0af68.css"> <!-- production-selling -->
	<link rel="stylesheet" href="/css/chunk244-0436abc3.css"> <!-- html-->
	<link rel="stylesheet" href="/css/chunk55-0c2ab26.css">
	<link rel="stylesheet" media="only screen and (min-width: 1023px)" href="/css/testThor.css">
	<link rel="stylesheet" href="/css/testThorText.css">
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

</head>
<body>
	<c:import url="../template/header.jsp"></c:import>
	<section class="container d-flex flex-wrap justify-content-center">
		<div class="production-selling-overview container content">
			<div class="production-selling-overview__container row">
				<div class="production-selling-overview__cover-image-wrap col-12 col-md-6 col-lg-7">
					<div class="production-selling-cover-image-container"> <!-- check -->
						<div class="carousel production-selling-cover-image production-selling-overview__cover-image" role="region" aria-roledescription="carousel"> <!-- check -->
							<div class="carousel__list-wrap production-selling-cover-image__carousel-wrap">
								<div class="carousel__list" id="carousel__list" aria-live="polite" style="transform: translateX(-0%); transition: transform 1s ease 0s;">
						<input type="hidden" id="productDetailDate" value="${saleProductVO.productDate}">
						<c:forEach items="${saleProductVO.productVOs}" var="VO">
							<input type="hidden" id="timeLength" value="${VO.auctionPeriod}">
							<c:forEach items="${VO.productFileVOs}" var="file" varStatus="status">
								<c:if test="${not file.sort}">
									
									<div class="carousel__list__entry production-selling-cover-image__entry" role="group" aria-roledescription="slide" aria-label="${status.count}" style="width: 100%;">
										<img class="production-selling-cover-image__entry__image" tabindex="0" alt="상품의 서브 이미지" src="/file/product/${file.fileName}?gif=1&amp;w=480&amp;h=480&amp;c=c&amp;webp=1" 
										srcset="/file/product/${file.fileName}?gif=1&amp;w=720&amp;h=720&amp;c=c&amp;webp=1 1.5x,/file/product/${file.fileName}?gif=1&amp;w=960&amp;h=960&amp;c=c&amp;webp=1 2x,/file/product/${file.fileName}?gif=1&amp;w=1440&amp;h=1440&amp;c=c&amp;webp=1 3x">
													<svg class="production-selling__timer__arrow-icon" width="14" height="14" preserveAspectRatio="xMidYMid meet">
														<path fill="#FFF" d="M3.5 11.487l1.313 1.346L10.5 7 4.812 1.167 3.5 2.513 7.875 7z"></path></svg></a>
									</div>
										
								</c:if>
							</c:forEach>
						
						
						</div>
							<ul class="production-selling-cover-image__list">
								<c:forEach items="${VO.productFileVOs}" var="file" varStatus="status">
									<c:if test="${not file.sort}">
										<li class="production-selling-cover-image__list__item">
											<button class="production-selling-cover-image__list__btn" type="button" aria-label="${status.count}">
												<img class="image" alt="상품의 대표 이미지" src="/file/product/${file.fileName}?gif=1&amp;w=72&amp;h=72&amp;c=c&amp;webp=1" 
												srcset="/file/product/${file.fileName}?gif=1&amp;w=144&amp;h=144&amp;c=c&amp;webp=1 1.5x,/file/product/${file.fileName}?gif=1&amp;w=144&amp;h=144&amp;c=c&amp;webp=1 2x,
												/file/product/${file.fileName}?gif=1&amp;w=240&amp;h=240&amp;c=c&amp;webp=1 3x">
											</button>
										</li>
									</c:if>
								</c:forEach>
							</ul>
								
								<div class="production-selling-cover-image__paginator">
								<c:forEach items="${VO.productFileVOs}" var="file" varStatus="status">
									<c:if test="${not file.sort}">
										<ul class="carousel-paginator">
											<li><span class="carousel-paginator__page tiny" data-key="${status.count}">
											</span>
										</li>
										</ul>
										</c:if>
								</c:forEach>
							</div>
								</div>
									</div>
								</div>
						</div>
						<div class="production-selling-overview__content col-12 col-md-6 col-lg-5">
							<div class="production-selling-header">
								<h1 class="production-selling-header__title">
									<p class="production-selling-header__title__brand-wrap">
										<span class="production-selling-header__title__name">${VO.productName} : (상품이름)</span>
											
											</h1>
										</div>
											<div class="production-selling-header__content">
												<div class="production-selling-header__price"><span class="production-selling-header__price__price-wrap"><span class="production-selling-header__price__discount"><span class="number">시작가</span><span class="percent"></span></span>
												<del class="production-selling-header__price__original">
													<span class="number"><fmt:formatNumber value="${VO.productPrice}" pattern="###,###"/></span><span class="won">원</span></del><span class="production-selling-header__price__separator production-selling-header__price__original"></span>
													<span class="production-selling-header__price__coupon"><span class="number">현재가 : <fmt:formatNumber value="${maxAmount}" pattern="###,###"/></span><span class="won">원</span>
												</span>
											</div>
										</div>
										<div class="production-selling-header__info-wrap">
											<!-- 회원 이름 -->
											<input type="hidden" id="id" value="${memberVO.id}">
											<input type="hidden" id="productId" value="${saleProductVO.productId}">
											<c:choose>
												<c:when test = "${not saleProductVO.deadlineInfo}">
											<div class="production-selling-header__promotion">
												<div class="production-selling-header__promotion__title-wrap">
													<span>혜택</span>
												</div>
												<div class="production-selling-header__promotion__content-wrap">
													<p class="production-selling-header__promotion__entry">
														<!-- 가격 계산 소수점버리기-->
														<c:set var="point" value="${VO.productPrice*0.03}"></c:set>
														<b><fmt:formatNumber type="number" maxFractionDigits="0" value="${point}" /><!-- -->P</b> 적립 (<!-- -->(Gold (회원등급)) <!-- -->3<!-- -->% 적립)</p>
													</div>
												</div>
												<div class="production-selling-header__delivery">
													<div class="production-selling-header__delivery__title-wrap">
														<span>배송</span>
													</div>
													<div class="production-selling-header__delivery__content-wrap">
														<span class="production-selling-header__delivery__fee"><b>무료배송</b></span>
														<div class="_2SUYq production-selling-header__delivery__today-departure__dropdown">
															<div class="production-selling-header__delivery__today-departure__header">
																<span class="text">배송정보 : <span class="active">잘 보내드림</span></span>
																
														</div>
													</div>
													
																</div>
													
																	</div>
												
																	
																			<div class="info">
																				<ul>
																						<li class="timer" id="timer">
																						<div id="productTimeDate">

																						</div> 
																						
																						</li>
																				</ul>
																			</div>
																		</c:when>
																		<c:otherwise>
																			<div class="info">
																				<ul>
																					<li id="productTimeDate">
																					</li>
																				</ul>
																			</div>
																		</c:otherwise>
																	</c:choose>
										</div>
							
							
							<c:choose>
								<c:when test = "${not saleProductVO.deadlineInfo}">
									<div class="btn_box right">	
										<!-- return false; -->
										<a href="#" onclick="do_fav(94);" class="mbtn red">관심경매등록</a>
									</div>
									<div class="bidbtn" style="display:block">
										<c:choose>
											<c:when test="${VO.productPrice != maxAmount || saleProductVO.bidAmountVOs[0].amountId != null}">
												<div class="bid1">
													<c:set var="bidAmount" value="${maxAmount + saleProductVO.amountUnit}"></c:set>
													<input type="hidden" id="amountUnit" value="${saleProductVO.amountUnit}">
													<input type="hidden" id="startAmount" value="${bidAmount}">
													<span><a href="#" onclick="dobid('A');">입찰하기 <strong><em><fmt:formatNumber value="${bidAmount}" pattern="###,###"/></em> 원</strong></a></span>
													<fmt:formatNumber value="${saleProductVO.amountUnit}" pattern="###,###"/>원 단위로 입찰
												</div>
												<div class="bid2">
													<span><strong><input type="text" id="bid_money" onkeyup="numberFormat(this);"  onblur="priceCutting('C', '${saleProductVO.amountUnit}');" value="<fmt:formatNumber value="${bidAmount}" pattern="###,###"/>"> 원</strong> <a href="#" onclick="dobid('B');">자율입찰 +</a></span>
													원하는 입찰 금액을 적어주세요. <br>(입찰시 입찰단위로 변환됩니다.)<br><fmt:formatNumber value="${saleProductVO.amountUnit}" pattern="###,###"/>원 단위로 자율입찰됩니다.
												</div>
											</c:when>
											<c:otherwise>
												<div class="bid1">
													<input type="hidden" id="amountUnit" value="${saleProductVO.amountUnit}">
													<input type="hidden" id="startAmount" value="${VO.productPrice}">
													<span><a href="#" onclick="dobid('A');">입찰하기 <strong><em><fmt:formatNumber value="${VO.productPrice}" pattern="###,###"/></em> 원</strong></a></span>
													<fmt:formatNumber value="${saleProductVO.amountUnit}" pattern="###,###"/>원 단위로 입찰
												</div>
												<div class="bid2">
													<span><strong><input type="text" id="bid_money" onkeyup="numberFormat(this);" onblur="priceCutting('C', '${saleProductVO.amountUnit}');" value="<fmt:formatNumber value="${VO.productPrice}" pattern="###,###"/>"> 원</strong> <a href="#" onclick="dobid('B');">자율입찰 +</a></span>
													원하는 입찰 금액을 적어주세요. <br>(입찰시 입찰단위로 변환됩니다.)<br><fmt:formatNumber value="${saleProductVO.amountUnit}" pattern="###,###"/>원 단위로 자율입찰됩니다.
												</div>
											</c:otherwise>
										</c:choose>
									</div>
								</c:when>
								<c:otherwise>

								</c:otherwise>							
							</c:choose>
						<div class="readinfo">
							<h4>구매 전 꼭 확인해주세요!</h4>
							<ul>
								<li class="on"><a href="#">배송정보 안내</a>
								<div class="txt">
								
								유료 - 4,000원
								
								<div>
								발송예정시기: 
								2일 이내.
								
								</div>
								</div>
								</li>
							</ul>
						</div>
					</div>
				</div>
				<div class="tap_product">
					<ul>
						<li class="on" data-move="detail_con1"><a href="#">입찰현황</a></li>
						<li data-move="detail_con2"><a href="#">상세정보</a></li>
						<li data-move="detail_con3"><a href="#">배송/반품</a></li>
						<li data-move="detail_con4"><a href="#">문의</a></li>
					</ul>
				</div>
				<div class="product_cont">
					<div class="auction_run" id="div_auction_run">
						<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
						<meta http-equiv="X-UA-Compatible" content="IE=edge">
						<link rel="stylesheet" media="only screen and (max-width: 1023px)" href="/css/ranking.css">
						
						<c:forEach items="${saleProductVO.bidAmountVOs}" var="amountVO" varStatus="status">
							<c:if test="${amountVO.bidAmount != null}">
								<div class="runner">
									<div class="title">
										<strong><fmt:formatNumber value="${amountVO.bidAmount}" pattern="###,###"/></strong>원
										<div class="name">${amountVO.id}<br></div>
									</div>
									<div class="run">
										<div class="bg${status.count}">
											<img src="/images/dollar.png" alt="">
											<span>${status.count}위</span>
										</div>
									</div>
								</div>
							</c:if>
						</c:forEach>
						
					</div>
					<div class="action_help">
						<span class="point">입찰시, 꼭! 확인해주세요</span>
						<dl>
							<dt>낙찰자<br>결제/배송안내</dt>
							<dd>
								<ul>
									<li><span>낙찰 후 48시간내에 결제</span> 해야하며, 48시간이 지나면 자동으로 입찰취소 상태로 변경됩니다.</li>
									<li>입찰차가 입찰 후 <span>즉시 입찰 취소가 가능하나, 20만원 이상 고가 물건 취소시 30일동안 입찰신청이 중지</span>됩니다.</li>
									<li>카드결제, 가상계좌, 실시간계좌이체, 방문결제중 선택해야하며, 방문일정 날짜 및 시간을 일주일내로 예약해 놓아야 합니다.</li>
									<li>방문결제시, 예약해 놓은 일정이 지날경우 자동으로 취소됩니다.</li>
								</ul>
							</dd>
						</dl>
					</div>
				</div>
				
				<table class="table table-bordered border-warning">
					<tbody>
						<tr class="table-warning">
							
							<th>상품상세정보</th>
							<tr>
							</tr>
							
							  <tr>
							  <td>${VO.productInformation}</td>
							  </tr>
						  
						</tr>
						</tbody>
					</table>
			</div>
			
				
		
		</c:forEach>
		
	</section>
	<script src="/js/detailTimer.js"></script>
	<script src="/js/bidAmount.js"></script>
	<script src="/js/test466.js"></script>
	<script src="/js/test36.js"></script>
	<script src="/js/test48.js"></script>
	<!-- <script src="/js/test466.js"></script> -->
	<c:import url="../template/footer.jsp"></c:import>
</body>
</html>