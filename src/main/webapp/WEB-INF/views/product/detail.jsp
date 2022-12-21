<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>세상의 모든 경매: BIDCOIN</title>
  <link href="/images/miniLogo_BidCoin.png" rel="shortcut icon" type="image/x-icon">
	<c:import url="../template/boot.jsp"></c:import>
	
	
	<link rel="stylesheet" href="/css/chunk48-455aca93.css"> <!-- carousel -->
	<link rel="stylesheet" href="/css/chunk166-a1b0af68.css"> <!-- production-selling -->
	<link rel="stylesheet" href="/css/chunk244-0436abc3.css"> <!-- html-->
	<link rel="stylesheet" href="/css/chunk55-0c2ab26.css">
	<link rel="stylesheet" media="only screen and (min-width: 1023px)" href="/css/testThor.css">
	<link rel="stylesheet" href="/css/testThorText.css">
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.css">
	<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>

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
											<input type="hidden" id="startAmount" value="${VO.productPrice}">
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
										<a href="#" onclick="do_fav();" class="mbtn red">관심경매등록</a>
									</div>
									<div class="bidbtn" style="display:block">
										<c:choose>
											<c:when test="${VO.productPrice != maxAmount || saleProductVO.bidAmountVOs[0].amountId != null}">
												<div class="bid1">
													<c:set var="bidAmount" value="${maxAmount + saleProductVO.amountUnit}"></c:set>
													<input type="hidden" id="amountUnit" value="${saleProductVO.amountUnit}">
													<input type="hidden" id="bidAmountCheck" value="${bidAmount}">
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
													<input type="hidden" id="bidAmountCheck" value="${VO.productPrice}">
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
							<c:if test="${amountVO.bidAmount != null && status.count < 5 }">
								<p></p>
								<div class="runner" id="detail_con1">
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
						<tr class="table-warning" id="detail_con2">
							
							<th>상품상세정보</th>
							<tr>
							</tr>
							
							  <tr>
							  <td>${VO.productInformation}</td>
							  </tr>
						  
						</tr>
						</tbody>
				</table>
				<h3 id="detail_con5" style="margin: 50px 0 25px 0">상품 정보 제공</h3>
				<table class="tit_detailinfo">
		
					<tbody><tr>
						<th>품명 및 모델명</th>
						<td>상세페이지</td>
					</tr>
					
					<tr>
						<th>KC 인증 필 유무</th>
						<td>상세페이지</td>
					</tr>
					
					<tr>
						<th>정격전압, 소비전력</th>
						<td>상세페이지</td>
					</tr>
					
					<tr>
						<th>에너지소비효율등급</th>
						<td>상세페이지</td>
					</tr>
					
					<tr>
						<th>동일모델의 출시년월</th>
						<td>상세페이지</td>
					</tr>
					
					<tr>
						<th>제조자(수입자)</th>
						<td>상세페이지</td>
					</tr>
					
					<tr>
						<th>제조국</th>
						<td>상세페이지</td>
					</tr>
					
					<tr>
						<th>크기(용량, 형태 포함)</th>
						<td>상세페이지</td>
					</tr>
					
					<tr>
						<th>품질보증기준</th>
						<td>상세페이지</td>
					</tr>
					
					<tr>
						<th>A/S책임자와의 전화번호</th>
						<td>상세페이지</td>
					</tr>
					
				</tbody>
			</table>
			<div class="product_info" id="detail_con3">
				<dl>
					<dt>환불/교환 불가사유</dt>
					<dd>
						<ul>
							<li>ㆍ환불/교환 요청 가능 기간이 초과한 경우(배송 완료 시점으로부터 7일 초과 시)</li>
							<li>ㆍ재고가 부족한 경우(단순 변심으로 교환을 요청했으나 재고가 부족한 경우 환불처리될 수 있음)</li>
							<li>ㆍ주문/제작 상품의 제작이 이미 진행된 경우</li>
							<li>ㆍ분실/파손/고장/오염이 발생한 경우</li>
							<li>ㆍ모니터 해상도 차이로 인하여 색상 등 이미지가 실제와 상이한 경우</li>
						</ul>
					</dd>	
				</dl>
				</div>
				<br>
				<br>
				<br>
				<hr>
			<div class="stitle" id="detail_con4" style="font-size: 28px; font-weight: 600; padding: 0">상품 문의하기</div>
			<div class="stext">
				상품에 대한 문의를 남기는 공간입니다. 
			</div>
			<ul class="product_qna">
				<c:forEach items="${saleProductQna.productQuestionVOs}" var="questionVO">
					<c:if test="${questionVO.questionId != null }">
							<li id="do_menu2${questionVO.questionId}" class="">
									<div class="qust">
										<span class="result <c:if test="${questionVO.adminQuestionVO.aqNum != null}"> end</c:if>"><c:if test="${questionVO.adminQuestionVO.aqNum != null}"> 완료</c:if><c:if test="${questionVO.adminQuestionVO.aqNum == null}">대기</c:if></span>
										<a href="#" onclick="do_ment_view2('${questionVO.questionId}');return false" class="tit">${questionVO.questionTitle}</a>
										<span class="name">${questionVO.id}</span>
										<span class="date">${questionVO.questionDate}</span>
										<!-- 작성자만 삭제가능 -->
										<c:if test="${memberVO.id == questionVO.id}">
											<sapn class="delete" onclick="do_delete_question_user('${questionVO.questionId}');">삭제</sapn>
										</c:if>
									</div>
									
									<div class="box">
										<div class="txt">
											<div></div>${questionVO.questionContents}
										</div>
										<c:if test="${questionVO.adminQuestionVO.aqNum != null}">
											
											<div class="reply">
												<div style=" margin: 8px;">
													<img src="/images/Bitcoin.png" alt="" style="width: 30px; display: inline; margin: 8px;">${questionVO.adminQuestionVO.name}
													<div style="float: right; width: 10%;">
														${questionVO.adminQuestionVO.aqDate}
													</div>
												</div>
												<div style="margin: 20px 15px; font-size: 15px;">
													${questionVO.adminQuestionVO.aqContents}
												</div>
												
												
												<!-- 관리자만-->
												<c:if test="${memberVO.roleVOs[0].roleNum == 1}">
													<button type="button" class="btn btn-warning" onclick="do_delete_question_admin('${questionVO.adminQuestionVO.aqNum}');">삭제</button>
												</c:if>
											</div>
										</c:if>
										<!-- 관리자만 보이게 -->
										<c:if test="${memberVO.roleVOs[0].roleNum == 1}">
											<c:if test="${questionVO.adminQuestionVO.aqNum == null}">
												<div class="reply">
													<form action="./adminQuestionAdd" method="post">
														<input type="text" name="aqContents" id="aqContents">
														<input type="hidden" name="questionId" value="${questionVO.questionId}">
														<input type="hidden" name="prNum" value="${questionVO.productId}">
														<input type="hidden" name="name" value="${memberVO.id}">
														
														<button type="submit" class="btn btn-warning">관리자답변</button>
													</form>
												</div>
											
											</c:if>
										</c:if>
									</div>
									
								</li> 
							
					</c:if>
				</c:forEach>			
			</ul>
			<c:if test="${memberVO.id == null}">
			<p class="size12 pt15 center gray999">로그인을 하신 후에 문의 작성이 가능합니다</p>
			</c:if>
				<div class="pt5 center" style="border-radius: 20px; font-size: 18px; margin: 10px;"><a href="#" onclick="open_layer_qna();" class="btn_after" style="border-radius: 20px;">상품문의 등록하기</a></div>	

		</div>
			
		<div id="fixed_pop" style="display: none; padding: 0px; margin: 0px;" onclick="close_layer();"></div>

		<div id="layer_pop_qna" style="display: none;" class="layer_pop_design">
			<form name="write_form2" id="write_form2" method="post" >
			<h3 class="tit">상품문의</h3>
			<input type="hidden" id="id" name="id" value="${memberVO.id}">
			<input type="hidden" id="productId" name="productId" value="${saleProductVO.productId}">
				<div class="conbox">
				<table width="100%" cellpadding="0" cellspacing="0" border="0" class="qna_board">
					<colgroup>
						<col width="20%">
						<col width="80%">
					</colgroup><colgroup>
					</colgroup><tbody><tr>
						<th>제목</th>
						<td><input type="text" name="questionTitle" id="commentTitle" placeholder="제목" class="wid100" valid="trim,required" element-name="제목"></td>
					</tr>
					<tr>
						<th>문의내용</th>
						<td><textarea class="wid100" name="questionContents" id="commentContents" valid="trim,required" element-name="문의내용"></textarea></td>
					</tr>
				</tbody></table>	
				<div class="center pt10">
					<a href="#" onclick="do_save_qna();" class="mbtn import1_bg">등록</a>
					<a href="#" onclick="close_layer();" class="mbtn gray">취소</a>
				</div>
				
				</div>
			</form>
			</div>		
		
		</c:forEach>
		
	</section>
	<script type="text/javascript">
		
			
		  $(".tap_product li").click(function() {
			 var scrollPosition = $(this).data("move")
		
			 $(this).addClass('on');
			 $(this).siblings().removeClass('on');
			 var movenum = $(this).data("move")
			  var offset = $("#" + movenum).offset();
				$('html, body').animate({scrollTop : offset.top-100}, 400);
		  });
		
		  $( document ).ready( function() {
			var jbOffset = $( '.tap_product' ).offset();
		
			$( window ).scroll( function() {
			  if ( $( document ).scrollTop() > jbOffset.top ) {
				$( '.tap_product' ).addClass( 'jbFixed' );
			  }
			  else {
				$( '.tap_product' ).removeClass( 'jbFixed' );
			  }
			});
		
		  } );
		
		 
		
		</script>
	<script src="/js/detailTimer.js"></script>
	<script src="/js/bidAmount.js"></script>
	<script src="/js/test466.js"></script>
	<script src="/js/test36.js"></script>
	<script src="/js/test48.js"></script>
	<!-- <script src="/js/test466.js"></script> -->
	<c:import url="../template/footer.jsp"></c:import>
</body>
</html>