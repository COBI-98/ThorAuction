<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>마이페이지 :: 토실대</title>
	<c:import url="../template/boot.jsp"></c:import>
	<link href="/css/reset.css" rel="stylesheet">
	<link href="/images/Thor.jpg" rel="shortcut icon" type="image/x-icon">
</head>
<body>
	<c:import url="../template/header.jsp"></c:import>
	<section class="container d-flex flex-wrap justify-content-center">
	
			<div class="me-5">
				<input type="text" name="id" disabled="disabled" value="${member.id}">
				<input type="text" name="id" disabled="disabled" value="${member.name}">
				<input type="text" name="id" disabled="disabled" value="${member.birth}">
				<input type="text" name="id" disabled="disabled" value="${member.email}">
				<input type="text" name="id" disabled="disabled" value="${member.post}">
				<input type="text" name="id" disabled="disabled" value="${member.addr}">
				<input type="text" name="id" disabled="disabled" value="${member.addr2}">
				<input type="text" name="id" disabled="disabled" value="${member.phone}">
			</div>
		
			<div class="me-5">
				<a href="./charge">충전하기</a>
			</div>
			<div class="me-5">
				<a href="./update">정보수정</a>
			</div>
			<div class="me-5">
				<a href="./delete">회원탈퇴</a>
			</div>
		
	</section>
	<c:import url="../template/footer.jsp"></c:import>
</body>
</html>