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
	<!-- <link href="/css/reset.css" rel="stylesheet"> -->
	<link href="/images/Thor.jpg" rel="shortcut icon" type="image/x-icon">
	
	<link rel="stylesheet" href="/css/chunk48-455aca93.css"> <!-- carousel -->
	<link rel="stylesheet" href="/css/chunk166-a1b0af68.css"> <!-- production-selling -->
	<link rel="stylesheet" href="/css/chunk244-0436abc3.css"> <!-- html-->
	<link rel="stylesheet" href="/css/chunk55-0c2ab26.css">
	<link rel="stylesheet" href="/css/chunk39.css">
	<link rel="stylesheet" href="/css/chunk202.css"> 
	<link rel="stylesheet" href="/css/testThor.css">
	<link rel="stylesheet" href="/css/testThorText.css">
</head>
<body>
	<c:import url="../template/header.jsp"></c:import>
		<div class="category-feed-container">
			<div class="category-feed-wrap container">
				<div class="category-feed row">
					<div class="category-feed__side-bar col-12 col-md-3">
						<section class="commerce-category-list">
							<h2 class="commerce-category-list__title">
								<a href="./list?<c:if test="${param.deadlineInfo != null}">deadlineInfo=${param.deadlineInfo}&</c:if>">전체보기</a>
							</h2>
							
							<ul class="commerce-category-list__others">
							<c:forEach items="${categoryVO}" var="category" varStatus="status">
								<li class="commerce-category-list__others__item">
									<a href="./list?<c:if test="${param.deadlineInfo != null}">deadlineInfo=${param.deadlineInfo}&</c:if>categoryId=${category.categoryId}">${category.categoryName}</a>
								</li>
							</c:forEach>
							</ul>
						</section>
					</div>
					<div class="category-feed__content col-12 col-md-9">
						<div class="commerce-category-header category-feed__content__header">
							<div class="commerce-category-header__breadcrumb-wrap">
								<nav class="commerce-category-breadcrumb-wrap commerce-category-header__breadcrumb">
									<ol class="commerce-category-breadcrumb">
										<li class="commerce-category-breadcrumb__entry">
											<a class="link" href="#">
												<c:choose>
													<c:when test="${param.categoryId == 1}">의류</c:when>
													<c:when test="${param.categoryId == 2}">뷰티, 잡화</c:when>
													<c:when test="${param.categoryId == 3}">가구, 생활, 건강</c:when>
													<c:when test="${param.categoryId == 4}">가전, 디지털, 컴퓨터</c:when>
													<c:when test="${param.categoryId == 5}">스포츠, 레저, 자동차</c:when>
													<c:when test="${param.categoryId == 6}">도서, e쿠폰</c:when>
													
													<c:otherwise>
														전체보기
													</c:otherwise>
												</c:choose>
											</a>
										</li>
									</ol>
								</nav>
							</div>
							<div class="tap_set">
								<ul>
									<li <c:if test="${saleVO[0].deadlineInfo == false}">class="on"</c:if>><a href="./list?deadlineInfo=0">진행중인 경매</a></li>
									<li <c:if test="${saleVO[0].deadlineInfo == true}">class="on"</c:if>><a href="./list?deadlineInfo=1"">마감된 경매</a></li>
								</ul>
							</div>
							<div class="sub_tit">온라인경매</div>
							<div class="total_product">
								<strong>총 ${saleVO.size()}개</strong><!-- 임시 -->
								<c:if test="${not saleVO[0].deadlineInfo}">
									<div class="order_by">
										<a href="./list?<c:if test="${param.categoryId != null}">categoryId=${param.categoryId}&</c:if>sc=1&list;" <c:if test="${classCheck == '1'}">class="on"</c:if>>등록순</a>
										<a href="./list?<c:if test="${param.categoryId != null}">categoryId=${param.categoryId}&</c:if>sc=2&list;" <c:if test="${classCheck == '2'}">class="on"</c:if>>참여높은순</a>
										<a href="./list?<c:if test="${param.categoryId != null}">categoryId=${param.categoryId}&</c:if>sc=3&list;" <c:if test="${classCheck == '3'}">class="on"</c:if>>임박순</a>
										<a href="./list?<c:if test="${param.categoryId != null}">categoryId=${param.categoryId}&</c:if>sc=4&list;" <c:if test="${classCheck == '4'}">class="on"</c:if>>낮은가격순</a>
										<a href="./list?<c:if test="${param.categoryId != null}">categoryId=${param.categoryId}&</c:if>sc=5&list;" <c:if test="${classCheck == '5'}">class="on"</c:if>>높은가격순</a>
									</div>
								</c:if>
							</div>
							
							<div class="auction_list">
								<ul>
									<c:choose>
											<c:when test="${classCheck == '2'}">
												<c:forEach items="${saleVO}" var="VO" varStatus="status" end="${saleVO.size()}">
																
																		<c:set var="testVO" value="testVO${status.index}" />
																		<c:set var="bidAmountCheck" value="bidAmountCheck${status.index}" />
																		<c:set var="deadLineCompare" value="${requestScope[testVO].auctionPeriod}" />
																	<li>
																		
																		<input type="hidden" class="productDate" name="tdate" value="${VO.productDate}">
																		<input type="hidden" class="timeLength" value="${requestScope[testVO].auctionPeriod}">
																		<div class="imgbox">
																			<a href="./detail?productId=${VO.productId}" class="img"><img src="/file/product/${requestScope[testVO].productFileVOs[0].fileName}" alt="">${requestScope[testVO].productName}</a>
																			
																			<div class="timedate" id="timertest" >
								
																			</div>
																		</div>
																		<div class="text">
																			<c:if test ="${not VO.deadlineInfo}">
																			<a href="#" class="title">${requestScope[testVO].productName}</a>
																			<div class="pricebox">
																				
																				<div>시작가 <span class="through"><fmt:formatNumber value="${requestScope[testVO].productPrice}" pattern="###,###"/></span> </div>
																				<div>현재가 <span class="price"><fmt:formatNumber value="${requestScope[bidAmountCheck]}" pattern="###,###"/> <em style="font-size:14px;vertical-align:top">↑</em></span></div>
																				<a href="#" class="shophome">COBI</a>
																			</div>
																			</c:if>
																			<c:if test = "${VO.deadlineInfo}">
																				<a href="#" class="title">${requestScope[testVO].productName}</a>
																				<div class="pricebox">
																					경매가 종료되었습니다.
																					<a href="#" class="shophome">COBI</a>
																				</div>
																			</c:if>
																		</div>
																	</li> 
																
															</c:forEach>
											</c:when>
											<c:when test="${classCheck == '3'}">
												<c:forEach items="${orderTime}" var="orderT">
													<c:forEach items="${saleVO}" var="VO" varStatus="status" end="${saleVO.size()}">
																
																		<c:set var="testVO" value="testVO${status.index}" />
																		<c:set var="bidAmountCheck" value="bidAmountCheck${status.index}" />
																		<c:set var="deadLineCompare" value="${requestScope[testVO].auctionPeriod*24*3600*1000+VO.productDate.getTime()}" />
																		<c:if test="${orderT == deadLineCompare}">
																	<li>
																		
																		<input type="hidden" class="productDate" name="tdate" value="${VO.productDate}">
																		<input type="hidden" class="timeLength" value="${requestScope[testVO].auctionPeriod}">
																		<div class="imgbox">
																			<a href="./detail?productId=${VO.productId}" class="img"><img src="/file/product/${requestScope[testVO].productFileVOs[0].fileName}" alt="">${requestScope[testVO].productName}</a>
																			
																			<div class="timedate" id="timertest" >
								
																			</div>
																		</div>
																		<div class="text">
																			<c:if test ="${not VO.deadlineInfo}">
																			<a href="#" class="title">${requestScope[testVO].productName}</a>
																			<div class="pricebox">
																				
																				<div>시작가 <span class="through"><fmt:formatNumber value="${requestScope[testVO].productPrice}" pattern="###,###"/></span> </div>
																				<div>현재가 <span class="price"><fmt:formatNumber value="${requestScope[bidAmountCheck]}" pattern="###,###"/> <em style="font-size:14px;vertical-align:top">↑</em></span></div>
																				<a href="#" class="shophome">COBI</a>
																			</div>
																			</c:if>
																			<c:if test = "${VO.deadlineInfo}">
																				<a href="#" class="title">${requestScope[testVO].productName}</a>
																				<div class="pricebox">
																					경매가 종료되었습니다.
																					<a href="#" class="shophome">COBI</a>
																				</div>
																			</c:if>
																		</div>
																	</li> 
																</c:if>
															</c:forEach>
												</c:forEach>
											</c:when>
											<c:when test="${classCheck == '4' || classCheck == '5'}">
									
													<c:forEach items="${saleVO}" var="VO" varStatus="status" end="${saleVO.size()}">
																				<!-- 넘어오는 리스트의 사이즈와 상품의 개수가 같으면 break문을걸기위해 true-->
																					<li>
																						<input type="hidden" class="productDate" name="tdate" value="${VO.productDate}">
																						<input type="hidden" class="timeLength" value="${requestScope[testVO].auctionPeriod}">
																						<div class="imgbox">
																							<a href="./detail?productId=${VO.productId}" class="img"><img src="/file/product/${VO.productVOs[0].productFileVOs[0].fileName}" alt="">${VO.productVOs[0].productName}</a>
																							
																							<div class="timedate" id="timertest" >
												
																							</div>
																						</div>
																						<div class="text">
																							<c:if test ="${not VO.deadlineInfo}">
																							<a href="#" class="title">${VO.productVOs[0].productName}</a>
																							<div class="pricebox">
																								<div>시작가 <span class="through"><fmt:formatNumber value="${VO.productVOs[0].productPrice}" pattern="###,###"/></span> </div>
																								<div>현재가 <span class="price"><fmt:formatNumber value="${VO.maxBidAmount}" pattern="###,###"/> <em style="font-size:14px;vertical-align:top">↑</em></span></div>
																								<a href="#" class="shophome">COBI</a>
																							</div>
																							</c:if>
																							<c:if test = "${VO.deadlineInfo}">
																								<a href="#" class="title">${VO.productVOs[0].productName}</a>
																								<div class="pricebox">
																									경매가 종료되었습니다.
																									<a href="#" class="shophome">COBI</a>
																								</div>
																							</c:if>
																						</div>
																					</li> 
																		
																</c:forEach>
																			
															</c:when>
											
											<c:otherwise>
												<c:forEach items="${saleVO}" var="VO" varStatus="status" end="${saleVO.size()}">
																
																		<c:set var="testVO" value="testVO${status.index}" />
																		<c:set var="bidAmountCheck" value="bidAmountCheck${status.index}" />
																		<c:set var="deadLineCompare" value="${requestScope[testVO].auctionPeriod}" />
																	<li>
																		
																		<input type="hidden" class="productDate" name="tdate" value="${VO.productDate}">
																		<input type="hidden" class="timeLength" value="${requestScope[testVO].auctionPeriod}">
																		<div class="imgbox">
																			<a href="./detail?productId=${VO.productId}" class="img"><img src="/file/product/${requestScope[testVO].productFileVOs[0].fileName}" alt="">${requestScope[testVO].productName}</a>
																			
																			<div class="timedate" id="timertest" >
								
																			</div>
																		</div>
																		<div class="text">
																			<c:if test ="${not VO.deadlineInfo}">
																			<a href="#" class="title">${requestScope[testVO].productName}</a>
																			<div class="pricebox">
																				
																				<div>시작가 <span class="through"><fmt:formatNumber value="${requestScope[testVO].productPrice}" pattern="###,###"/></span> </div>
																				<div>현재가 <span class="price"><fmt:formatNumber value="${requestScope[bidAmountCheck]}" pattern="###,###"/> <em style="font-size:14px;vertical-align:top">↑</em></span></div>
																				<a href="#" class="shophome">COBI</a>
																			</div>
																			</c:if>
																			<c:if test = "${VO.deadlineInfo}">
																				<a href="#" class="title">${requestScope[testVO].productName}</a>
																				<div class="pricebox">
																					경매가 종료되었습니다.
																					<a href="#" class="shophome">COBI</a>
																				</div>
																			</c:if>
																		</div>
																	</li> 
																
															</c:forEach>
											</c:otherwise>	
									</c:choose>
									
									
									
									
									
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
    <script src="/js/timerTest.js"></script> 
	<script src="/js/test466.js"></script>
	<script src="/js/test36.js"></script>
	<script src="/js/test48.js"></script>
	<!-- <script src="/js/test466.js"></script> -->
	<c:import url="../template/footer.jsp"></c:import>
</body>
</html>