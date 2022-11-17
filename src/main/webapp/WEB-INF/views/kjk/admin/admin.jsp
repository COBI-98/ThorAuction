<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../../temp/boot.jsp"></c:import>
<link rel="stylesheet" href="/css/admin.css">
<link rel="stylesheet" href="/js/admin.js">
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
<style>
section {
	text-align: center;
}
</style>

<body>
	<br>
	<br>
	<br>
	<br>
	<section class="col-lg-8 container-fluid mt-4">
		<table class="table table-hover">
			<tr>
				<th>Num</th>
				<th>ID</th>
				<th>Name</th>
				<th>차단 여부</th>
			</tr>
			<tr>
				<td>1</td>
				<td>id1</td>
				<td>Name1</td>
				<td>
					<label class="switch">
						<input type="checkbox"> <span class="slider round"></span>
					</label>
					<p>OFF</p>
					<p style="display: none;">ON</p>
				</td>
			</tr>
			<tr>
				<td>2</td>
				<td>id2</td>
				<td>Name2</td>
				<td>
					<label class="switch">
						<input type="checkbox"> <span class="slider round"></span>
					</label>
					<p>OFF</p>
					<p style="display: none;">ON</p>
				</td>
			</tr>
		</table>
		<a href="./loginPhoto">login</a>
		<a href="../../">home</a>
	</section>
</body>

</html>