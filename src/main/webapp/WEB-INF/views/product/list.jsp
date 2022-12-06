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
	<link href="/css/reset.css" rel="stylesheet">
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
	<c:import url="../template/header.jsp"></c:import>-
		<div class="category-feed-container">
			<div class="category-feed-wrap container">
				<div class="category-feed row">
					<div class="category-feed__side-bar col-12 col-md-3">
						<section class="commerce-category-list">
							<h2 class="commerce-category-list__title">
								<a href="./list?category=${categoryVO[0].categoryId}">${categoryVO[0].categoryName}</a>
							</h2>
							<ul class="commerce-category-tree commerce-category-list__categories">
								<li class="commerce-category-tree__entry">
									<div class="commerce-category-tree__entry__header">
										<a class="commerce-category-tree__entry__title" 
										href="#">의류테스트1</a>
									</div>
								</li>
								<li class="commerce-category-tree__entry">
									<div class="commerce-category-tree__entry__header">
										<a class="commerce-category-tree__entry__title" href="#">의류테스트2</a>
										<button class="commerce-category-tree__entry__expand" title="펼치기" type="button">
											<svg class="icon" width="12" height="12" fill="currentColor" viewBox="0 0 12 12" preserveAspectRatio="xMidYMid meet">
												<path d="M6.07 7.56l4.39-4.55.87.87-5.25 5.45L.67 3.9 1.53 3z">
												</path>
											</svg>
										</button>
									</div>
									<div class="" style="overflow: hidden;">
									</div>
								</li>
								<!-- 여기 반복-->
								<li class="commerce-category-tree__entry">
									<div class="commerce-category-tree__entry__header">
										<a class="commerce-category-tree__entry__title" href="#">의류테스트3</a>
										<button class="commerce-category-tree__entry__expand" title="펼치기" type="button">
											<svg class="icon" width="12" height="12" fill="currentColor" viewBox="0 0 12 12" preserveAspectRatio="xMidYMid meet">
												<path d="M6.07 7.56l4.39-4.55.87.87-5.25 5.45L.67 3.9 1.53 3z">
					
												</path>
											</svg>
										</button>
									</div>
									<div class="" style="overflow: hidden;">
									</div>
								</li>
							</ul>
							<ul class="commerce-category-list__others">
							<c:forEach items="${categoryVO}" var="category" varStatus="status">
								<li class="commerce-category-list__others__item">
									<a href="./list?category=${category.categoryId}">${category.categoryName}</a>
								</li>
							</c:forEach>
							</ul>
							<!-- <ul class="commerce-category-list__others">
								<li class="commerce-category-list__others__item">
									<a href="/store/category?category=2">데코·식물</a>
								</li>
								<li class="commerce-category-list__others__item">
									<a href="/store/category?category=0">가구</a>
								</li>
							</ul> -->
						</section>
					</div>
					<div class="category-feed__content col-12 col-md-9">
						<div class="commerce-category-header category-feed__content__header">
							<div class="commerce-category-header__breadcrumb-wrap">
								<nav class="commerce-category-breadcrumb-wrap commerce-category-header__breadcrumb">
									<ol class="commerce-category-breadcrumb">
										<li class="commerce-category-breadcrumb__entry">
											<a class="link" href="/store/category?category=1">패브릭</a>
										</li>
									</ol>
								</nav>
							</div>
							<div class="tap_set">
								<ul>
									<li class="on"><a href="auction_list.asp?sing=S">진행중인 경매</a></li>
									<li><a href="auction_list.asp?sing=E">마감된 경매</a></li>
								</ul>
							</div>
							<div class="sub_tit">온라인경매</div>
							<div class="total_product">
								<strong>총 ${saleVO.size()}개</strong><!-- 임시 -->
								<div class="order_by">
									<a href="auction_list.asp?sc=1&amp;" class="on">등록순</a>
									<a href="auction_list.asp?sc=2&amp;">참여높은순</a>
									<a href="auction_list.asp?sc=3&amp;">임박순</a>
									<a href="auction_list.asp?sc=4&amp;">낮은가격순</a>
									<a href="auction_list.asp?sc=5&amp;">높은가격순</a>
								</div>
							</div>
							<div class="auction_list">
								<ul>
									
									
									<!-- 올린시간 -->
									<c:forEach items="${saleVO}" var="VO">
										${VO.productNum}
									</c:forEach>
									
									<input type="hidden" id="cobi" name="tdate" value="${saleVO[0].productDate}"> 
									<li>
										<input type="hidden" id="timeLength" value="${testVO.auctionPeriod}" /> 
										<div class="imgbox">
											<a href="#" class="img"><img src="/file/product/${testVO.productFileVOs[0].fileName}" alt="">${testVO.productName}</a>
											
											<div class="timedate" id="timertest" >

											</div>
										</div>
										<div class="text">
											<a href="#" class="title">${testVO.productName}</a>
											<div class="pricebox">
												
												<div>시작가 <span class="through">${testVO.productPrice}</span> </div>
												<div>현재가 <span class="price">${testVO.productPrice}  <em style="font-size:14px;vertical-align:top">↑</em></span></div>
												<a href="#" class="shophome">COBI : (관리자이름 예정)</a>
											</div>
											
										</div>
										
									</li>
									
									<!-- <input type="hidden" name="tdate" value="2022/12/16 19:04:00">
									<li>
										<div class="imgbox">
											<a href="auction_view.asp?product_number=105" class="img"><img src="/userfiles/auction/2022/12/22022120517453_755006956.jpg" alt="마루망 마제스티 아이언 세트 새상품 3 (남성용)"></a>
											<div class="timedate">남은시간 <strong><span id="d-day-day0">10</span>일<span id="d-day-hour0">08</span>:<span id="d-day-min0">17</span>:<span id="d-day-sec0">33</span></strong></div>
										</div>
										<div class="text">
											<a href="auction_view.asp?product_number=105" class="title">마루망 마제스티 아이언 세트 새상품 3 (남성용)</a>
											<div class="pricebox">
												
												<div>시작가 <span class="through">2,800,000</span> </div>
												<div>현재가 <span class="price">2,800,000  <em style="font-size:14px;vertical-align:top">↑</em></span></div>
												<a href="#" class="shophome">금강</a>
											</div>
											
										</div>
										
									</li>
									
									<input type="hidden" name="tdate" value="2022/12/15 19:03:00">
									<li>
										<div class="imgbox">
											<a href="auction_view.asp?product_number=104" class="img"><img src="/userfiles/auction/2022/12/22022120517324_755006956.jpg" alt="마루망 마제스티 아이언 세트 새상품 2 (남성용)"></a>
											<div class="timedate">남은시간 <strong><span id="d-day-day1">09</span>일<span id="d-day-hour1">08</span>:<span id="d-day-min1">16</span>:<span id="d-day-sec1">33</span></strong></div>
										</div>
										<div class="text">
											<a href="auction_view.asp?product_number=104" class="title">마루망 마제스티 아이언 세트 새상품 2 (남성용)</a>
											<div class="pricebox">
												
												<div>시작가 <span class="through">2,800,000</span> </div>
												<div>현재가 <span class="price">2,800,000  <em style="font-size:14px;vertical-align:top">↑</em></span></div>
												<a href="#" class="shophome">금강</a>
											</div>
											
										</div>
										
									</li>
									
									<input type="hidden" name="tdate" value="2022/12/22 19:02:00">
									<li>
										<div class="imgbox">
											<a href="auction_view.asp?product_number=103" class="img"><img src="/userfiles/auction/2022/12/2202212051727_755006956.jpg" alt="마루망 마제스티 아이언 세트 새상품 1 (남성용)"></a>
											<div class="timedate">남은시간 <strong><span id="d-day-day2">16</span>일<span id="d-day-hour2">08</span>:<span id="d-day-min2">15</span>:<span id="d-day-sec2">33</span></strong></div>
										</div>
										<div class="text">
											<a href="auction_view.asp?product_number=103" class="title">마루망 마제스티 아이언 세트 새상품 1 (남성용)</a>
											<div class="pricebox">
												
												<div>시작가 <span class="through">3,300,000</span> </div>
												<div>현재가 <span class="price">3,300,000  <em style="font-size:14px;vertical-align:top">↑</em></span></div>
												<a href="#" class="shophome">금강</a>
											</div>
											
										</div>
										
									</li> -->
									
									
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