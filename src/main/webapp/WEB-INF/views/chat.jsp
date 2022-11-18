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
	width: 800px;
	margin: 20px auto;
}

#chatt #talk{
	width: 800px;
	height: 400px;
	overflow: scroll;
	border : 1px solid #aaa;
}
#chatt #msg{
	width: 740px;
	height:100px;
	display: inline-block;
}

#chatt #sendZone > *{
	vertical-align: top;
	
}
#chatt #btnSend{
	width: 54px;
	height: 100px;
}

#chatt #talk div{
	width: 70%;
	display: inline-block;
	padding: 6px;
	border-radius:10px;
	
}

#chatt .me{
	background-color : #ffc;
	margin : 1px 0px 2px 30%;	
}

#chatt .other{
	background-color : #eee;
	margin : 2px;
}
	</style>
</head>


<body>
	<c:import url="./template/header.jsp"></c:import>
	<section class="container d-flex flex-wrap justify-content-center">
	<div id='chatt'>
		<h1>실시간 경매</h1>
		<h1>물품 : </h1>
		<input type='text' id='mid' value='홍길동'>
		<input type='button' value='로그인' id='btnLogin'>
		<br/>
		<div id='talk'></div>
		<div id='sendZone'>
			<textarea id='msg' value='hi...' ></textarea>
			<input type='button' value='전송' id='btnSend'>
		</div>
		
	</div>

    <video id="localVideo" autoplay playsinline width="480px"></video>
   <video id="remoteVideo" autoplay playsinline width="480px"></video>

    
    <script src="/socket.io/socket.io.js"></script>
    
    </section>
	<c:import url="./template/footer.jsp"></c:import>
	<script src='/js/chat.js'></script>
</body>
</html>