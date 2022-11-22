<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>토르의 실시간 대장간</title>
<c:import url="../template/boot.jsp"></c:import>
<link href="/css/reset.css" rel="stylesheet">
<link href="/images/Thor.jpg" rel="shortcut icon" type="image/x-icon">
<link rel="stylesheet" href="/css/mypage.css">
<script defer src="/js/mypage.js"></script>
</head>
<body>
	<c:import url="../template/header.jsp"></c:import>
	<section class="container d-flex flex-wrap justify-content-center col-lg-8">
		<table class="table table-hover">
			<tr>
				<th>Name</th>
				<th>Email</th>
				<th>차단 여부</th>
			</tr>
			<tr>
				<td></td>
				<td>id1</td>
				<td>Name1</td>
				<td>
					<label class="switch">
						<input type="checkbox"> <span class="slider round"></span>
					</label>
					<p>OFF</p>
					<p style="display: none;">ON</p>
				</td>
			</tr>
		</table>
	</section>
	<c:import url="../template/footer.jsp"></c:import>
</body>
</html>
