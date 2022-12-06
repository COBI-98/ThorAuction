<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- security tag -->
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>토르의 실시간 대장간</title>
<c:import url="./template/boot.jsp"></c:import>
<link href="/css/reset.css" rel="stylesheet">
<link href="/images/Thor.jpg" rel="shortcut icon" type="image/x-icon">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script defer src="/js/social/kakaoBan.js"></script>
<script defer src="/js/social/memberBan.js"></script>
</head>
<body>
	<c:import url="./template/header.jsp"></c:import>
	<section class="container d-flex flex-wrap justify-content-center">

		<h1>토르의 실시간 대장간 메인</h1>
	</section>

	<sec:authorize access="isAuthenticated()">
		<c:forEach items="${kakaoRole }" var="kk">
			<c:if test="${kk.kakaoRoleVOs[0].kaNickName == kakaoInfo.kaNickName }">
				<input type="hidden" value="${kk.kaRoleVOs[0].kaRoleName }" class="krole" readOnly>
			</c:if>
		</c:forEach>

		<c:forEach items="${member}" var="m">
			<c:if test="${m.id == memID && m.roleVOs[0].roleName == 'ROLE_BAN' }">
				<input type="hidden" value="${m.id}" class="iii" readOnly>
				<input type="hidden" value="${m.roleVOs[0].roleName}" class="MID" readOnly>
			</c:if>
		</c:forEach>
	</sec:authorize>
	<c:import url="./template/footer.jsp"></c:import>
</body>
</html>
