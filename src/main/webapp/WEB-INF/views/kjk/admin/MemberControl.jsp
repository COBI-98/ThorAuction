<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> --%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet"
	integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<script defer src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

<link rel="stylesheet" href="/js/admin.js">
<link rel="stylesheet" href="/css/admin.css">
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
	</section>
</body>

</html>