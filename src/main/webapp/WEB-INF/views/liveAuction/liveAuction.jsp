<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
  <meta charset="utf-8">
  <title>토르의 실시간 대장간</title>
  <meta http-equiv="Content-Security-Policy" content="upgrade-insecure-requests">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
  
  <c:import url="../template/boot.jsp"></c:import>
  <link href="/css/reset.css" rel="stylesheet">
  <link href="/css/chat.css" rel="stylesheet">
  <link href="/images/Thor.jpg" rel="shortcut icon" type="image/x-icon">
  <link rel="stylesheet" href="/css/getHTMLMediaElement.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.css">

</head>
<body>
  
  <c:import url="../template/header.jsp"></c:import>
  
  
  <section class="make-center">
    <!-- 방송 시작 부분 -->
  <div>
    <input type="text" id="room-id" value="abcdef" autocorrect=off autocapitalize=off size=20>
    <button id="open-room">Open Room</button>
    <button id="join-room">Join Room</button>
    <button id="open-or-join-room">Auto Open Or Join Room</button>
    
	<div class="wrap">
		<div id="videos-container" style="margin: 20px 0;">
			<div id="media-box">
				<video id="localVideo" autoplay playsinline ></video>
			</div>
	    </div>
	</div>
    <button id="cameraStatus"> 방송 종료 </button>
	<button id="cameraPause" class= ""> 방송 일시 정지 </button>
	<button id="cameraBlack" class= ""> 방송 일시 정지 2 </button>
	<button id="screenShare" class= ""> 화면 공유로 전환 </button>
	<select id="cameras"></select>
	
    
	<div id="room-urls" style="width : 200px;text-align: center;display: none;background: #F1EDED;margin: 15px -10px;border: 1px solid rgb(189, 189, 189);border-left: 0;border-right: 0;"></div>
  </div>
  
  
  <!-- 방송 마지막 부분 -->
  
  <!-- 채팅 시작 부분 -->
  <div id='chatt'>
      <div id="text">
         <h3>실시간 경매</h3>
         <h3>물품 : </h3>
         <h3 id="final">현재 금액</h3>
         <h3 id="amount">${value}</h3>
         <h3 id="finalamount"></h3>
         <h3>현재 인원</h3>
         <h3 id="count"></h3>
      </div>   
      <h2 id="id">${member}</h2>
      <h2 id="point">${point}</h2>
      <!-- <input type='text' id='mid' value="홍길동" placeholder="닉네임을 입력해주세요">
      <input type='button' value='로그인' id='btnLogin'>
      <br/> -->
      <div id='talk'></div>
      <div id='sendZone'>
         <input type='button' value="경매" id="auction">
         <textarea id='msg' value='hi...' placeholder="메시지를 입력해보세요!"></textarea>
         <input type='button' value='보내기' id='btnSend'>
         <input type='button' value='+단위 가격' id='add'>
         <input type="button" class="" value="얼리기" id='stop'>
         <input type="button" value="경매종료" id="auctionend"> <!--배열 controller로 보내짐 / 가격 안변하게하기-->
         <input type="button" value="종료" id="end"> <!--session 닫힘-->
      </div>

      <h3>현재 채팅 참가자</h3>
      <div id = "iddd">
      
      </div>

      
   </div>
  
  
  <!-- 채팅 마지막 부분 -->
  
  
  
  
  
  </section>

	
	








<c:import url="../template/footer.jsp"></c:import>

<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<script src="/socket.io/socket.io.js"></script>
<script src='/js/liveauction/chat.js'></script>
<script src="/js/liveauction/RTCMultiConnection.js"></script>
<script src="https://192.168.1.83:80/socket.io/socket.io.js"></script>
<script src="/js/liveauction/getHTMLMediaElement.js"></script>
<script src='/js/index.js'></script>

  <footer>
    <small id="send-message"></small>
  </footer>

  <script src="https://www.webrtc-experiment.com/common.js"></script>

</body>
</html>