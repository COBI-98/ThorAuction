<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>회원정보 수정</h1>
	<form action="update" method="POST">
		<table>
			<tr>
				<th>등급</th>
				<td><input type="text" name="id" value="${member.roleVOs[0].roleNum}"></td>
				<td><input type="text" name="id" value="${member.roleVOs[0].roleName}"></td>
			</tr>		
			<tr>
				<th>아이디</th>
				<td><input type="text" name="id" value="${member.id}"></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><input type="text" name="name" value="${member.name}"></td>
			</tr>
			<tr>
				<th>생년월일</th>
				<td><input type="text" name="birth" value="${member.birth}"></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type="email" name="email" value="${member.email}"></td>
			</tr>
			<tr>
				<th>우편번호</th>
				<td><input type="text" name="post" value="${member.post}"></td>
			</tr>
			<tr>
				<th>주소</th>
				<td><input type="text" name="addr" value="${member.addr}"></td>
			</tr>
			<tr>
				<th>상세주소</th>
				<td><input type="text" name="addr2" value="${member.addr2}"></td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td><input type="text" name="phone" value="${member.phone}"></td>
			</tr>
		</table>

		<button type="submit" id="update_btn">수정하기</button>
	</form>
</body>
</html>