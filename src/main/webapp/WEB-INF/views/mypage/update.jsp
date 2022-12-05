<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

	<script defer src="/js/mypage/update.js"></script>
</head>
<body>
	<h1>회원정보 수정</h1>
	<form action="update" method="POST" id="upadte_form">
		<table>
			<tr>
				<th>등급</th>
				<td><input type="text" name="id" disabled="disabled" value="${member.roleVOs[0].roleNum}"></td>
				<td><input type="text" name="id" disabled="disabled" value="${member.roleVOs[0].roleName}"></td>
			</tr>		
			<tr>
				<th>아이디</th>
				<td><input type="text" id="id" name="id" readonly="readonly" value="${member.id}"></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" id="pw" name="pw" value=""></td>
			</tr>			
			<tr>
				<th>이름</th>
				<td><input type="text" id="name" name="name" readonly="readonly" value="${member.name}"></td>
			</tr>
			<tr>
				<th>생년월일</th>
				<td><input type="text" id="birth" name="birth" readonly="readonly" value="${member.birth}"></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type="email" id="email" name="email" value="${member.email}"></td>
			</tr>
			<tr>
				<th>우편번호</th>
				<td><input type="text" id="post" name="post" value="${member.post}"></td>
			</tr>
			<tr>
				<th>주소</th>
				<td><input type="text" id="addr" name="addr" value="${member.addr}"></td>
			</tr>
			<tr>
				<th>상세주소</th>
				<td><input type="text" id="addr2" name="addr2" value="${member.addr2}"></td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td><input type="text" id="phone" name="phone" value="${member.phone}"></td>
			</tr>
		</table>
		<div style="color: red;">${message}</div>
		<button type="button" id="update_btn">수정하기</button>
	</form>
</body>
</html>