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
				<th>등급 변경</th>
			</tr>
			<c:forEach items="${member}" var="m">
				<input type="hidden" value="${m.roleVOs[0].roleName }" class="roleName">
				<tr>
					<td>${m.id }</td>
					<td>${m.email }</td>
					<td>${m.name }</td>
					<td>${m.point }</td>
					<td>${m.roleVOs[0].roleName }</td>
					<td>
						<select name="select" class="selectRole" id="s">
							<option class="role" value="Manager">Manager</option>
							<option class="role" value="User" selected>User</option>
							<option class="role" value="Ban">Ban</option>
						</select>
					</td>
				</tr>
			</c:forEach>
			<c:forEach items="${kakao }" var="ka">
				<tr>
					<td>${ka.kaNickName }</td>
					<td>${ka.kaEmail }</td>
					<td>${ka.kaName }</td>
					<td>${ka.kakaoDetailVOs[0].kaPoint }</td>
					<td>${ka.kakaoRoleVOs}</td>
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