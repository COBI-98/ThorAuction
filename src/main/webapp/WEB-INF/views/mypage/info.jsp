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
				<label>아이디<input type="text" name="id" disabled="disabled" value="${memberDB.id}"></label><br>
				<label>이름<input type="text" name="name" disabled="disabled" value="${memberDB.name}"></label><br>
				<label>생년월일<input type="text" name="birth" disabled="disabled" value="${memberDB.birth}"></label><br>
				<label>이메일<input type="text" name="email" disabled="disabled" value="${memberDB.email}"></label><br>
				<label>우편번호<input type="text" name="post" disabled="disabled" value="${memberDB.post}"></label>
				<label>주소<input type="text" name="addr" disabled="disabled" value="${memberDB.addr}"></label>
				<label>상세주소<input type="text" name="addr2" disabled="disabled" value="${memberDB.addr2}"></label><br>
				<label>전화번호<input type="text" name="phone" disabled="disabled" value="${memberDB.phone}"></label><br>
				<label>포인트<input type="text" name="point" disabled="disabled" value="${memberDB.point}"></label>
				<label>테스트<input type="text" name="payPoint" disabled="disabled" value="${pay.payTotal}"></label>
			</div>
			
			<div class="me-5">
				<a href="./charge">충전하기</a>
			</div>
			<div class="me-5">
				<a href="./paypoint">충전내역</a>
			</div>
			<div class="me-5">
				<a href="./checkpw">정보수정</a>
			</div>
			<div class="me-5">
				<a href="./delete">회원탈퇴</a>
			</div>
		
	</section>
	<c:import url="../template/footer.jsp"></c:import>
</body>
</html>