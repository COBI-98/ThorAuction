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

</head>
<body>
  
  <section class="make-center">
    <input type="text" id="room-id" value="abcdef" autocorrect=off autocapitalize=off size=20>
    <button id="open-room">Open Room</button>
    <button id="join-room">Join Room</button>
    <button id="open-or-join-room">Auto Open Or Join Room</button>

    <div id="videos-container" style="margin: 20px 0;"></div>
    
    
	<div id="room-urls" style="text-align: center;display: none;background: #F1EDED;margin: 15px -10px;border: 1px solid rgb(189, 189, 189);border-left: 0;border-right: 0;"></div>
  </section>

<button id="cameraStatus"> 방송 종료 </button>
<button id="cameraPause" class= ""> 방송 일시 정지 </button>


<div id='chatt'>
		<h1>WebSocket Chatting</h1>
		<input type='text' id='mid' value='홍길동'>
		<input type='button' value='로그인' id='btnLogin'>
		<br/>
		<div id='talk'></div>
		<div id='sendZone'>
			<textarea id='msg' value='hi...' ></textarea>
			<input type='button' value='전송' id='btnSend'>
		</div>
		
	</div>

<script src="/socket.io/socket.io.js"></script>
	<script src='/js/chat.js'></script>
<script src="/js/RTCMultiConnection.js"></script>

<!-- <script src="https://muazkhan.com:9001/socket.io/socket.io.js"></script> -->
<script src="https://192.168.1.83:80/socket.io/socket.io.js"></script>
<!-- custom layout for HTML5 audio/video elements -->
<link rel="stylesheet" href="/css/getHTMLMediaElement.css">
<script src="/js/getHTMLMediaElement.js"></script>
<script src='/js/index.js'></script>

  <footer>
    <small id="send-message"></small>
  </footer>

  <script src="https://www.webrtc-experiment.com/common.js"></script>
  
  
</body>
</html>