<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>토르의 실시간 대장간</title>
	<c:import url="../template/boot.jsp"></c:import>
	<link href="/css/reset.css" rel="stylesheet">
	<link href="/images/Thor.jpg" rel="shortcut icon" type="image/x-icon">
	
	 <!-- 아임포트 -->
    <script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
</head>
<body>
	<c:import url="../template/header.jsp"></c:import>
	<section class="container d-flex flex-wrap justify-content-center">
	
		<div class="card-body bg-white mt-0">
	        <p style="font-weight: bold">카카오페이 현재 사용가능</p>
	        <label class="box-radio-input"><input type="radio" name="cp_item" value="5000"><span>5,000원</span></label>
	        <label class="box-radio-input"><input type="radio" name="cp_item" value="10000"><span>10,000원</span></label>
	        <label class="box-radio-input"><input type="radio" name="cp_item" value="15000"><span>15,000원</span></label>
	        <label class="box-radio-input"><input type="radio" name="cp_item" value="20000"><span>20,000원</span></label>
	        <label class="box-radio-input"><input type="radio" name="cp_item" value="25000"><span>25,000원</span></label>
	        <label class="box-radio-input"><input type="radio" name="cp_item" value="30000"><span>30,000원</span></label>
	        <label class="box-radio-input"><input type="radio" name="cp_item" value="35000"><span>35,000원</span></label>
	        <label class="box-radio-input"><input type="radio" name="cp_item" value="40000"><span>40,000원</span></label>
	        <label class="box-radio-input"><input type="radio" name="cp_item" value="50000"><span>50,000원</span></label>
	        <p style="color: #ac2925; margin-top: 30px">카카오페이의 최소 충전금액은 5,000원이며 <br/>최대 충전금액은 50,000원 입니다.</p>
        
        	<button type="button" class="btn btn-lg btn-block btn-custom" id="pay">충전하기</button>
		</div>
		
	</section>
	<c:import url="../template/footer.jsp"></c:import>
	
	<!-- 충전하기 JS -->
    <script src="/js/mypage/charge.js"></script>
</body>
</html>