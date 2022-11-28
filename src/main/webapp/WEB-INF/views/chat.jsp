<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<c:import url="./template/boot.jsp"></c:import>
	<link href="/css/reset.css" rel="stylesheet">
	<link href="/images/Thor.jpg" rel="shortcut icon" type="image/x-icon">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<meta charset="UTF-8">
	<title>Chating</title>
	<style>
		*{
	box-sizing: border-box;
}

#chatt{
	width: 700px;
	margin: 20px auto;
	/* text-align: center; */
}

#chatt #talk{
	width: 600px;
	height: 650px;
	overflow: scroll;
	border : 1px solid #aaa;
	/* text-align: center; */
}
#chatt #msg{
	width: 490px;
	height:100px;
	display: inline-block;
}

#chatt #sendZone > *{
	vertical-align: top;
	
}
#chatt #btnSend{
	width: 54px;
	height: 100px;
	background-color: #fea532;
	color: white;
	border-style: none;
}

#chatt #talk div{
	width: 60%;
	display: inline-block;
	padding: 6px;
	border-radius:10px;	
}

#chatt .me{
	background-color : #eee;
	margin : 1px 0px 2px 40%;	
}

#chatt .other{
	background-color : #eee;
	margin : 2px;
}

#chatt .me#enter{
	background-color: white;
	border-style:solid;
	border-color: #fea532;
	margin : 1px 0px 2px 40%;	
}

#chatt .other#enter{
	background-color: white;
	border-style:solid;
	border-color: #fea532;
	margin : 2px;
}

#text{
	text-align: center;
}

.container{
	padding: 0px;
}

#auction{
	background-color: white;
	color: #fea532;
	font-weight: bold;
	border-style: none;
}
	</style>
</head>


<body>
	<c:import url="./template/header.jsp"></c:import>
	<section class="container d-flex flex-wrap justify-content-center">
	<div id='chatt'>
		<div id="text">
			<h3>실시간 경매</h3>
			<h3>물품 : </h3>
			<h3>현재금액</h3>
			<h3 id="amount">${value}</h3>
			<h3 id="count"></h3>
		</div>	
		<h2 id="id">${member}</h2>
		<input type='text' id='mid' value="홍길동" placeholder="닉네임을 입력해주세요">
		<input type='button' value='로그인' id='btnLogin'>
		<br/>
		<div id='talk'></div>
		<div id='sendZone'>
			<input type='button' value="경매" id="auction">
			<textarea id='msg' value='hi...' placeholder="메시지를 입력해보세요!"></textarea>
			<input type='button' value='보내기' id='btnSend'>
			<input type="button" value="얼리기" id='stop'>
			<input type="button" value="경매종료" id="auctionend"> <!--배열 controller로 보내짐 / 가격 안변하게하기-->
			<input type="button" value="종료" id="end"> <!--session 닫힘-->
		</div>

		
	</div>

    <!-- <video id="localVideo" autoplay playsinline width="480px"></video>
   <video id="remoteVideo" autoplay playsinline width="480px"></video> -->

    
    <script src="/socket.io/socket.io.js"></script>
    
    </section>
	<c:import url="./template/footer.jsp"></c:import>
	<script src='/js/chat.js'></script>
</body>
</html>