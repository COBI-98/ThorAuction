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
	<section class="container d-flex flex-wrap justify-content-center col-lg-10">
		<table class="table table-hover">
			<tr>
				<th>ID</th>
				<th>Email</th>
				<th>Name</th>
				<th>Point</th>
				<th>등급</th>
				<th>차단 여부</th>
			</tr>
			<c:forEach items="${member }" var="member">
				<tr>
					<td>${member.id }</td>
					<td>${member.email }</td>
					<td>${member.name }</td>
					<td>${member.point }</td>
					<td>${member.roleVOs[0].roleName }</td>
					<td>
						<select name="select" id="selectRole">
							<option id="opMana" value="Manager">Manager</option>
							<option id="opMem" value="User" selected>User</option>
							<option id="opBan" value="Ban">Ban</option>
						</select>
					</td>
				</tr>
			</c:forEach>
			<c:forEach items="${kakao }" var="kakao">
				<tr>
					<td>${kakao.kaNickName }</td>
					<td>${kakao.kaEmail }</td>
					<td>${kakao.kaName }</td>
					<td>${kakao.kakaoDetailVOs[0].kaPoint }</td>
					<td>X</td>
					<td>
						<select name="select" id="selectRole">
							<option id="opMana" value="Manager">Manager</option>
							<option id="opMem" value="Member" selected>User</option>
							<option id="opBan" value="Ban">Ban</option>
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