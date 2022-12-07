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
  
  	
    
    
	
		<div id="videos-container" style="margin: 20px 0;">
			<div id="media-container" class = "media-container shadowBox">
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
					
					
				</div>
		<div id="zoom" style="height: 75px; width: 75px;"></div>
		
				
				
        <div class="chat-container">
          <div id="text">
            <span id="final">경매 최고가:</span>
            <span id="amount">${value}</span>
            <span id="hidden" style="visibility: hidden;">${value}</span>
            <span id="finalamount"></span>
          </div>
          <div id="item">
            <span>경매 물품</span>
            <span id="selecteditem"></span>
          </div>  

          <ul class="tabs">
            <li class="tab-link current" data-tab="chatt">채팅창</li>
            <li class="tab-link" data-tab="iddd">참가인원 목록</li>
          </ul>
          <div id='chatt' class="tab-content current">
              <div class="chat-box">
                <div id='talk'></div>
                <div id='sendZone'>
                  <input type='text' id='msg'  placeholder="메시지를 입력해보세요!">
                  <input class ="bidcoinBtn" type='button' value='보내기' id='btnSend'>
                </div>
              </div>
              <div>
                  <input type='button' value="직접입력" id="auction" class ="bidcoinBtn">
                  <input type='button' value="단위경매" id="add" class ="bidcoinBtn">
              </div>
          </div>
          <div id ="iddd" class="tab-content">
              
          </div>
        </div>
      </div>	
    </div>
    <div style="display: flex; justify-content: center;">
    
    <div class="shadowBox media-container">
    	<div id="media-broad-option">
						<div class="optionBox">
							<div >방송설정</div>
							<div>
							  	<input  type="hidden" id="room-id" value="123" readonly="readonly" autocorrect=off autocapitalize=off size=20>
							    <button class= "bidcoinBtn" id="open-room">방송 시작</button>
							    <button class= "bidcoinBtn" id="join-room">Join Room</button>
						  	</div>
						  	<div>카메라 설정<select id="cameras"></select></div>
							<button id="cameraPause" class= "bidcoinBtn"> 방송 일시 정지 </button>
							<button id="cameraBlack" class= "bidcoinBtn"> 방송 송출 정지 </button>
							<button id="screenShare" class= "bidcoinBtn"> 화면 공유로 전환 </button>
							
							<div>
								<button id="cameraStatus" class= "bidcoinBtn"> 방송 종료 </button>
							</div>
						</div>
						
						<div class="optionBox">
							<div>경매설정</div>
							<div>
				              <span>경매 물건</span>
                      <select id="items" name="items" style="width: 60%;">
                        <c:forEach items="${itemList}" var="items">
                        <option value="${items.productNum}" data-price="${items.productPrice}">${items.productName}</option>
                      </c:forEach>
                      </select>
				              <input type="button" value="설정" id="itemsend" class="bidcoinBtn">
				            </div>
				             
				            <div>
				           		<span>단위가격</span>
					            <input type="text" id="unit">
					            <input type="button" value="설정" id="unitsend" class="bidcoinBtn">
				            </div>
						
							<input type="button" class="bidcoinBtn" value="채팅 정지" id='stop'>
						    <input type="button" class="bidcoinBtn" value="경매시작" id="auctionend"> <!--배열 controller로 보내짐 / 가격 안변하게하기-->
						    <input type="button" class="bidcoinBtn" value="경매종료" id="end"> <!--session 닫힘-->
						
					
						</div>
					</div>
    
    
    </div>
    </div>
  </div>
	
      <h2 id="id">${member}</h2>
      <h2 id="loginnum">${num}</h2>
      <h2 id="point">${point}</h2>
      <h2 id="role">${role[0].roleName}</h2>

	<div id="room-urls" style="width : 200px;text-align: center;display: none;background: #F1EDED;margin: 15px -10px;border: 1px solid rgb(189, 189, 189);border-left: 0;border-right: 0;"></div>
  </div>
  </section>

<%-- <c:import url="../template/footer.jsp"></c:import> --%>

<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>


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