<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- security tag -->
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<div class="wrap">
<nav class="py-2 bg-light border-bottom" style="background-color: #fea532 !important; height: 40px;">

<c:import url="/WEB-INF/views/template/recentItem.jsp"></c:import>

	<!-- <nav class="py-2 bg-light border-bottom"> -->
		<div class="container d-flex flex-wrap">
			<ul class="nav me-auto">

			</ul>
		</div>
	</nav>
	
	
	
	<nav class=" border-bottom" style="display: flex; justify-content: center;">
	
	
		
	
	<div class="container " >
	<sec:authorize access="hasRole('ROLE_ADMIN')" >
		<ul class="nav"   style="float: left; margin-left: 50px;">
				<li class="nav-item nav-member"><a href="/admin/adminpage" class="nav-link">관리자 기능</a></li>
				<li class="nav-item nav-member"><a href="#" class="nav-link">관리자 페이지</a></li>
		</ul>
	</sec:authorize>
		
		<ul class="nav" style="float: right;">
				<!-- 로그인 전 -->
				<sec:authorize access="!isAuthenticated()">
					<li class="nav-item"><a href="/member/login" class="nav-link link-dark px-2">로그인</a></li>
					<li class="nav-item"><a href="/member/agree" class="nav-link link-dark px-2">회원가입</a></li>
				</sec:authorize>

				<%-- 			<!-- 로그인 후 -->
				<c:if test="${member != null}">
					<li class="nav-item"><a href="/member/logout" class="nav-link link-dark px-2">로그아웃</a></li>
					<%-- <li class="nav-item"><a href="/mypage/id=${member.id}" class="nav-link link-dark px-2">마이페이지</a></li>
				</c:if>--%>

				<%-- <sec:authorize access="!isAuthenticated()">
					<li class="nav-item"><a href="/member/joinchoose" class="nav-link link-dark px-2">|회원가입|</a></li>
				</sec:authorize> --%>

				<!-- 로그인 후 -->
				<sec:authentication property="Principal" var="member" />
				<sec:authorize access="isAuthenticated()">
					<c:choose>
						<c:when test="${kakaoInfo != null }">
							<li class="nav-item">
								<a class="nav-link link-dark px-2">
									<b>${kakaoInfo.kaName }님 안녕하세요</b>
								</a>
							</li>
							<li class="nav-item">
								<a href="/mypage/info?id=${kakaoInfo.kaNickName}" class="nav-link link-dark px-2">마이페이지</a>
							</li>
						</c:when>
						<c:otherwise>
							<li class="nav-item">
								<a class="nav-link link-dark px-2">
									<b>${member.name }님 안녕하세요</b>
								</a>
							</li>
							<li class="nav-item">
								<a href="/mypage/info?id=${member.id}" class="nav-link link-dark px-2">마이페이지</a>
							</li>
						</c:otherwise>
					</c:choose>
					<li class="nav-item">
						<a href="/member/logout" class="nav-link link-dark px-2">로그아웃</a>
					</li>

					<c:if test="${empty memID}">
						<c:forEach items="${kakaoRole }" var="kk">
							<c:if
								test="${((kk.kaRoleVOs[0].kaRoleName == 'ROLE_ADMIN')==true) && ((kk.kakaoRoleVOs[0].kaNickName == kakaoInfo.kaNickName)==true)}">
								<li class="nav-item">
									<a href="/admin/adminpage" class="nav-link link-dark px-2">관리자 페이지</a>
								</li>
							</c:if>
						</c:forEach>
					</c:if>

					<c:if test="${not empty memID && memID == 'admin'}">
						<c:if test="${member.roleVOs[0].roleName == 'ROLE_ADMIN'}">
							<li class="nav-item">
								<a href="/admin/adminpage" class="nav-link link-dark px-2">관리자 페이지</a>
							</li>
						</c:if>
					</c:if>

				</sec:authorize>
			</ul>
			
			</div>
		</nav>
	
	
	
	
	
	
	
	
	
	<header class="py-3 mb-4 border-bottom">
		<div class="container d-flex flex-wrap justify-content-center" style="align-items: flex-end;">
			<a href="/product/list" class="d-flex align-items-center mb-3 mb-lg-0 me-lg-auto text-dark text-decoration-none">
				<svg class="bi me-2" width="40" height="32">
					<use xlink:href="#bootstrap"></use></svg>
				<span class="fs-4"><img alt="" src="/images/logo_BidCoin.png" style="width: 200px;"></span>
			</a>
			<ul class="nav">
				<li class="nav-item nav-menu" ><a href="/product/list" class="nav-link link-dark" style="font-weight: bold;">경매장</a></li>
		        <li class="nav-item nav-menu" ><a href="https://192.168.1.83:80/" class="nav-link link-dark" style="font-weight: bold">실시간 경매장</a></li>
		        <li class="nav-item nav-menu" ><a href="/board/application/list" class="nav-link link-dark" style="font-weight: bold">신청게시판</a></li>
		        <li class="nav-item nav-menu" ><a href="/board/notice/list" class="nav-link link-dark" style="font-weight: bold">공지게시판</a></li>
		         <li class="nav-item nav-menu" ><a href="/chatbot" class="nav-link link-dark" style="font-weight: bold">QNA챗봇</a></li>
		       
			</ul>
			
			
			</div>
	
		
	</header>

	<script>
		var width = '500';
		var height = '600';
		var left = Math.ceil((window.screen.width) / 2);
		var top = Math.ceil((window.screen.height) / 2);
		// /oauth2/authorization/kakao
		function popup() {
			var url = "/member/delete";
			var name = "popup test";
			var option = "width=" + width + ", height=" + height + ", top="
					+ top + ", left=" + left
			window.open(url, name, option);
		}
	</script>
