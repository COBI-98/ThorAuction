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
  
  
  <section class="">
    <!-- 방송 시작 부분 -->
  <div>
  
  	<div style="margin-left: 100px;">
	  	<input type="text" id="room-id" value="abcdef" autocorrect=off autocapitalize=off size=20>
	    <button id="open-room">Open Room</button>
	    <button id="join-room">Join Room</button>
	    <button id="open-or-join-room">Auto Open Or Join Room</button>
  	</div>
    
    
	
		<div id="videos-container" style="margin: 20px 0;">
			<div id="media-container" class = "media-container">
				<div id="media-broadcast">
					<div id="media-header" >
						<div id="media-title">
							<h2 id="media-titleText"> 제목 들어갑니다 ~ </h2>
						</div>
						<div>
							현재 인원<span id="count"></span>
						</div>
					</div>
				
					<div id="media-box" class="media-box">
						<div id= "blackVideo" style="width: 100%; height: 100%;"></div>
						<video id="localVideo" autoplay playsinline hidden ></video>
					</div>
					
					<div id="media-broad-option">
						<button id="cameraStatus"> 방송 종료 </button>
						<button id="cameraPause" class= ""> 방송 일시 정지 </button>
						<button id="cameraBlack" class= ""> 방송 일시 정지 2 </button>
						<button id="screenShare" class= ""> 화면 공유로 전환 </button>
						<div>카메라 설정<select id="cameras"></select></div>
						<div>해상도 설정<select ></select></div>
						
            <div>
            <span>단위가격</span>
            <input type="text" id="unit">
            <input type="button" value="설정" id="unitsend">
            </div>

						<input type="button" class="" value="얼리기" id='stop'>
					     <input type="button" class="" value="경매시작" id="auctionend"> <!--배열 controller로 보내짐 / 가격 안변하게하기-->
					     <input type="button" value="종료" id="end"> <!--session 닫힘-->
					</div>
				</div>
			
				
				
        <div class="container">
          <div id="text">
            <span id="final">경매 최고가:</span>
            <span id="amount">${value}</span>
            <span id="hidden" style="visibility: hidden;">${value}</span>
            <span id="finalamount"></span>
          </div>   

          <ul class="tabs">
            <li class="tab-link current" data-tab="chatt">채팅창</li>
            <li class="tab-link" data-tab="iddd">참가인원 목록</li>
          </ul>
          <div id='chatt' class="tab-content current">
              <div id="chat-box">
                <div id='talk'></div>
                <div id='sendZone'>
                  <textarea id='msg' value='hi...' placeholder="메시지를 입력해보세요!"></textarea>
                  <input type='button' value='보내기' id='btnSend'>
                </div>
              </div>
              <div>
                  <input type='button' value="직접입력" id="auction">
                  <input type='button' value="단위경매" id="add">
              </div>
          </div>
          <div id ="iddd" class="tab-content">
              
          </div>
        </div>
      </div>	
    </div>
  </div>
	
      <h2 id="id">${member}</h2>
      <h2 id="point">${point}</h2>

	<div id="room-urls" style="width : 200px;text-align: center;display: none;background: #F1EDED;margin: 15px -10px;border: 1px solid rgb(189, 189, 189);border-left: 0;border-right: 0;"></div>
  </div>
  </section>

<%-- <c:import url="../template/footer.jsp"></c:import> --%>

<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<script src="/socket.io/socket.io.js"></script>
<script src='/js/liveauction/chat.js'></script>
<script src="/js/liveauction/RTCMultiConnection.js"></script>
<!-- <script src="/socket.io/socket.io.js"></script> -->
<script src="https://192.168.1.83:80/socket.io/socket.io.js"></script>
<script src="/js/liveauction/getHTMLMediaElement.js"></script>
<script src='/js/index.js'></script>

  <footer>
    <small id="send-message"></small>
  </footer>

  <script src="https://www.webrtc-experiment.com/common.js"></script>

</body>
</html>