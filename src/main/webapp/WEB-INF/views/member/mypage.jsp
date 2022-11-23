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
				<td>Name1</td>
				<td>Eamil1</td>
				<td>
					<select name="select" id="selectRole">
						<option id="opMana" value="Manager">Manager</option>
						<option id="opMem" value="Member" selected>Member</option>
						<option id="opBan" value="Ban">Ban</option>
					</select>
				</td>
			</tr>
		</table>
	</section>
	<c:import url="../template/footer.jsp"></c:import>
</body>
</html>
