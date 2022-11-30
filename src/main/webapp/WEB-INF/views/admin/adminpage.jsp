<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>토르의 실시간 대장간</title>
<link href="/css/reset.css" rel="stylesheet">
<link href="/images/Thor.jpg" rel="shortcut icon" type="image/x-icon">
<link rel="stylesheet" href="/css/admin/admin.css">
<script defer src="/js/admin/admin.js"></script>
</head>

<body>
	<c:import url="../template/boot.jsp"></c:import>
	<c:import url="../template/header.jsp"></c:import>
	<section class="container-fluid justify-content-center col-lg-11">
		<table class="table table-hover">
			<tr>
				<th>아이디</th>
				<th>이메일</th>
				<th>이름</th>
				<th>생년월일</th>
				<th>우편번호</th>
				<th>주소</th>
				<th>상세 주소</th>
				<th>전화번호</th>
				<th>포인트</th>
				<th>등급</th>
				<th>등급 변경</th>
			</tr>
			<c:forEach items="${member}" var="m">
				<input type="hidden" value="${m.roleVOs }" class="roleName">
				<tr>
					<td>${m.id }</td>
					<td>${m.email }</td>
					<td>${m.name }</td>
					<td>${m.birth }</td>
					<td>${m.post }</td>
					<td>${m.addr }</td>
					<td>${m.addr2 }</td>
					<td>${m.phone }</td>
					<td>${m.point }</td>
					<td>${m.roleVOs[0].roleName }</td>
					<td>
						<c:choose>
							<c:when test="${m.roleVOs[0].roleName == 'ROLE_MANGER' }">
								<select name="select" class="selectRole" id="s">
									<option class="role" value="Manager" selected>Manager</option>
									<option class="role" value="User">User</option>
									<option class="role" value="Ban">Ban</option>
								</select>
							</c:when>
							<c:when test="${m.roleVOs[0].roleName == 'ROLE_USER' }">
								<select name="select" class="selectRole" id="s">
									<option class="role" value="Manager">Manager</option>
									<option class="role" value="User" selected>User</option>
									<option class="role" value="Ban">Ban</option>
								</select>
							</c:when>
							<c:when test="${m.roleVOs[0].roleName == 'ROLE_BAN' }">
								<select name="select" class="selectRole" id="s">
									<option class="role" value="Manager">Manager</option>
									<option class="role" value="User">User</option>
									<option class="role" value="Ban" selected>Ban</option>
								</select>
							</c:when>
							<c:otherwise>에러</c:otherwise>
						</c:choose>
						<!-- <select name="select" class="selectRole" id="s">
							<option class="role" value="Manager">Manager</option>
							<option class="role" value="User" selected>User</option>
							<option class="role" value="Ban">Ban</option>
						</select> -->
					</td>
				</tr>
			</c:forEach>
			<c:forEach items="${kakao }" var="ka">
				<tr>
					<td>${ka.kaNickName }</td>
					<td>${ka.kaEmail }</td>
					<td>${ka.kaName }</td>
					<td>${ka.kakaoDetailVOs[0].kaBirth }</td>
					<td>${ka.kakaoDetailVOs[0].kaPost }</td>
					<td>${ka.kakaoDetailVOs[0].kaAddr }</td>
					<td>${ka.kakaoDetailVOs[0].kaAddrDetail }</td>
					<td>${ka.kakaoDetailVOs[0].kaPhone }</td>
					<td>${ka.kakaoDetailVOs[0].kaPoint }</td>
					<td>${ka.kaRoleVOs[0].kaRoleName}</td>
					<td>
						<select name="select" class="selectRole">
							<option class="role" value="Manager">Manager</option>
							<option class="role" value="User" selected>User</option>
							<option class="role" value="Ban">Ban</option>
						</select>
					</td>
				</tr>
			</c:forEach>
		</table>
	</section>
	<br>
	<br>
	<c:import url="../template/footer.jsp"></c:import>
</body>
</html>