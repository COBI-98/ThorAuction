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
		<h1>회원가입</h1>

		<form action="join" method="POST">
			<table>
				<tr>
					<th>아이디</th>
					<td><input type="text" id="id" name="id">
					
					<button type="button" class="idCheck">중복확인</button>
					
					<p class="result">
						<span class="msg"></span>
					</p>
					</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="pw"></td>
				</tr>
				<tr>
					<th>이름</th>
					<td><input type="text" name="name"></td>
				</tr>
				<tr>
					<th>생년월일</th>
					<td><input type="text" name="birth"></td>
				</tr>
				<tr>
					<th>이메일</th>
					<td><input type="email" name="email"></td>
				</tr>
				<tr>
					<th>우편번호</th>
					<td><input type="text" name="post"></td>
				</tr>
				<tr>
					<th>주소</th>
					<td><input type="text" name="addr"></td>
				</tr>
				<tr>
					<th>상세주소</th>
					<td><input type="text" name="addr"></td>
				</tr>
				<tr>
					<th>전화번호</th>
					<td><input type="text" name="phone"></td>
				</tr>
			</table>

			<button type="submit" id="join_btn" disabled="disabled">가입하기</button>
		</form>

	</section>
	<script>
		$(".idCheck").click(function() {
			var query = {id : $("#id").val()};
			
			$.ajax({
				url : "/member/idCheck",
				type : "POST",
				data : query,
				success : function(data) {
					if(data == 1) {
						$(".result .msg").text("중복되는 아이디가 있습니다");
						$(".result .msg").attr("style", "color : #f00");
						$("#join_btn").attr("disabled", "disabled");
					} else {
						$(".result .msg").text("사용가능한 아이디입니다");
						$(".result .msg").attr("style", "color : #00f");
						$(".idcheck").attr("disabled", "disabled");
						$("#join_btn").removeAttr("disabled");
					}
				}
				
			});
		});
		$("#id").blur(function(){
			 $(".result .msg").text("중복확인을 해주세요");
			 $(".result .msg").attr("style", "color:#000");
			 
			 $("#submit").attr("disabled", "disabled");
			 
			});
	</script>
	<c:import url="../template/footer.jsp"></c:import>
</body>
</html>