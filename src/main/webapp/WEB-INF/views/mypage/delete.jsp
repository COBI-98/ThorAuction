<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- JQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<!-- SweetAlert2 -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.css">
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>

<!-- 회원탈퇴 JS -->
<script defer src="/js/mypage/delete.js"></script>
</head>
<body>
	<h1>회원탈퇴</h1>
	<form action="delete" method="POST" id="delete_form">
		<table>
			<tr>
				<th>아이디</th>
				<td><input type="text" name="id" id="id" value="${memberDB.id}"></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="pw" id="pw"></td>
			</tr>
		</table>

		<button type="button" id="delete_btn">탈퇴하기</button>

	</form>
</body>
</html>