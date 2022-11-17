<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>토르의 실시간 대장간</title>
<c:import url="../template/boot.jsp"></c:import>
<link href="/css/reset.css" rel="stylesheet">
<link href="/images/Thor.jpg" rel="shortcut icon" type="image/x-icon">
</head>
<body>
	<c:import url="../template/header.jsp"></c:import>
	<section class="container d-flex flex-wrap justify-content-center">
	
		<form action="login" method="POST">
			<table>
				<tr>
					<th>아이디</th>
					<td><input type="text" name="id" value="p"></td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="pw" value="p"></td>
				</tr>
			</table>

			<button type="submit">로그인</button>
		</form>
	</section>
	<c:import url="../template/footer.jsp"></c:import>
</body>
</html>