<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- security tag -->
<%-- <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%> --%>
<div class="wrap">
	<nav class="py-2 bg-light border-bottom">
		<div class="container d-flex flex-wrap">
			<ul class="nav me-auto">

			</ul>
			<ul class="nav">
				<!-- 로그인 전 -->
				<c:if test="${member == null}">
					<li class="nav-item"><a href="../member/login" class="nav-link link-dark px-2">로그인</a></li>
					<li class="nav-item"><a href="../member/signup" class="nav-link link-dark px-2">회원가입</a></li>
				</c:if>

				<!-- 로그인 후 -->
				<c:if test="${member != null}">
					<li class="nav-item"><a href="../member/logout" class="nav-link link-dark px-2">로그아웃</a></li>
					<li class="nav-item"><a href="../mypage/update" class="nav-link link-dark px-2">마이페이지</a></li>
				</c:if>

				<%-- <sec:authorize access="!isAuthenticated()">
					<li class="nav-item"><a href="../member/joinchoose" class="nav-link link-dark px-2">|회원가입|</a></li>
				</sec:authorize> --%>
				<!-- 로그인 후 -->
				<!-- <sec:authentication property="Principal" var="member" />
				<sec:authorize access="isAuthenticated()">
					<form action="/member/logout" method="post">
						<sec:csrfInput />
						<li class="nav-item"><button class="btn btn-outline-none">logout</button></li>
					</form>
					<li class="nav-item"><a href="../admin/adminpage" class="nav-link link-dark px-2">|카카오 정보|</a></li>
				</sec:authorize>
				
				<li class="nav-item"><a href="#" class="nav-link link-dark px-2">관리자 페이지</a></li> -->
			</ul>
		</div>
	</nav>
	<header class="py-3 mb-4 border-bottom">
		<div class="container d-flex flex-wrap justify-content-center">
			<a href="/" class="d-flex align-items-center mb-3 mb-lg-0 me-lg-auto text-dark text-decoration-none">
				<svg class="bi me-2" width="40" height="32">
					<use xlink:href="#bootstrap"></use></svg>
				<span class="fs-4">토실토실대</span>
			</a>
			<ul class="nav">
				<li class="nav-item"><a href="#" class="nav-link link-dark px-2">경매장</a></li>
				<li class="nav-item"><a href="#" class="nav-link link-dark px-2">실시간 경매장</a></li>
				<li class="nav-item"><a href="#" class="nav-link link-dark px-2">고객센터</a></li>
				<li class="nav-item"><a href="/board/application/list" class="nav-link link-dark px-2">신청게시판</a></li>
				<li class="nav-item"><a href="/board/notice/list" class="nav-link link-dark px-2">공지게시판</a></li>
				<li class="nav-item"><a href="#" class="nav-link link-dark px-2">QNA챗봇</a></li>
			</ul>
			<form class="col-12 col-lg-auto mb-3 mb-lg-0" role="search">
				<input type="search" class="form-control" placeholder="Search..." aria-label="Search">
			</form>
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