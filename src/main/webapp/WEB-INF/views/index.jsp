<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function Frameset(page) {
		framecode = "<frameset rows='1*'>"
				+ "<frame name=main src='" + page + "'>" + "</frameset>";

		page = window.open("");
		page.document.open();
		page.document.write(framecode);
		page.document.close();
	}
</script>
</head>
<body>
	<h1>Index Page</h1>
	<h2>다 새창에서 열림</h2>
	<a href="./kjk/admin/admin">로그인</a>
	<!-- <a href="javascript:Frameset('loginForm.jsp')">카카오 로그인 버튼</a>
	<a href="javascript:Frameset('admin.jsp')">리스트 예시</a> -->
	<br>
</body>
</html>
