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

	<script defer src="/js/mypage/updatepw.js"></script>
</head>
<body>
	<h1>비밀번호 수정</h1>
	<form action="updatepw" method="POST" id="updatepw_form">
		<table>	
			<tr>
 				<th>아이디</th>
				<td><input type="text" id="id" name="id" readonly="readonly" value="${memberDB.id}"></td>
			</tr>
			<tr>
				<th>현재 비밀번호</th>
				<td><input type="password" id="pw" name="pw"></td>
			</tr>
			<tr>
				<th>새 비밀번호</th>
				<td><input type="password" id="newPassword" name="newPassword"></td>
				<td><div id="newPasswordMsg"></div></td>
			</tr>
			<tr>
				<th>새 비밀번호 재확인</th>
				<td><input type="password" id="newPasswordEquals" name="newPasswordEquals"></td>
				<td><div id="newPasswordEqualsMsg"></div></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><input type="hidden" id="name" name="name" readonly="readonly" value="${memberDB.name}"></td>
			</tr>
			<tr>
				<th>생년월일</th>
				<td><input type="hidden" id="birth" name="birth" readonly="readonly" value="${memberDB.birth}"></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type="hidden" id="email" name="email" value="${memberDB.email}"></td>
			</tr>
			<tr>
				<th>우편번호</th>
				<td><input type="hidden" id="post" name="post" value="${memberDB.post}"></td>
			</tr>
			<tr>
				<th>주소</th>
				<td><input type="hidden" id="addr" name="addr" value="${memberDB.addr}"></td>
			</tr>
			<tr>
				<th>상세주소</th>
				<td><input type="hidden" id="addr2" name="addr2" value="${memberDB.addr2}"></td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td><input type="hidden" id="phone" name="phone" value="${memberDB.phone}"></td>
			</tr>						
		</table>
		<div style="color: red;">${message}</div>
		<button type="button" id="updatepw_btn">수정하기</button>
	</form>
</body>
</html>