<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>회원탈퇴</h1>
	<form action="delete" method="POST">
			<table>
				<tr>
					<th>아이디</th>
					<td><input type="text" name="id" value="${member.id}"></td>
				</tr> 
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="pw"></td>
				</tr>
			</table>

			<button type="submit">탈퇴하기</button>
		</form>
		
		<c:if test="${msg == false}">
			<p>입력하신 비밀번호가 틀렸습니다.</p>
		</c:if>
	
</body>
</html>