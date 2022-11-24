<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- jQuery -->
<script type="text/javascript"
	src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<!-- iamport.payment.js -->
<script type="text/javascript"
	src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>

</head>
<body>
	<h2>결제</h2>
	<button onclick="requestPay()">결제하기</button>

	<script>
		var IMP = window.IMP; // 생략 가능
		IMP.init("imp55151468"); // 예: imp00000000

		function requestPay() {
			IMP.request_pay({
				pg : "kcp",
				pay_method : "card",
				merchant_uid : "ORD20180131-0000011", //주문번호
				name : "노르웨이 회전 의자",
				amount : 64900, // 숫자타입
				buyer_email : "gildong@gmail.com",
				buyer_name : "홍길동",
				buyer_tel : "010-4242-4242",
				buyer_addr : "서울특별시 강남구 신사동",
				buyer_postcode : "01181"
			}, function(rsp) { // callback
				if (rsp.success) {
					// jQuery로 HTTP 요청
					jQuery.ajax({
						url : "{서버의 결제 정보를 받는 가맹점 endpoint}",
						method : "POST",
						headers : {
							"Content-Type" : "application/json"
						},
						data : {
							imp_uid : rsp.imp_uid, //결제 고유번호     
							merchant_uid : rsp.merchant_uid //주문번호
						}
					}).done(function(data) {
						// 가맹점 서버 결제 API 성공시 로직
						alert("나이스 결제 성공~!");
					})
				} else {
					alert("결제에 실패하였습니다. 에러 내용: " + rsp.error_msg);
				}
			});
		}
	</script>
</body>
</html>