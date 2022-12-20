<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
  <meta charset="utf-8">
  <title>세상의 모든 경매: BIDCOIN</title>
  <meta http-equiv="Content-Security-Policy" content="upgrade-insecure-requests">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  
  <c:import url="../template/boot.jsp"></c:import>
  <link href="/css/reset.css" rel="stylesheet">
  <link href="/css/chat.css" rel="stylesheet">
  <link rel="stylesheet" media="only screen and (min-width:200px) and (max-width:480px)" href="/css/liveAuctionM.css">
  <link href="/images/miniLogo_BidCoin.png" rel="shortcut icon" type="image/x-icon">
  <link rel="stylesheet" href="/css/getHTMLMediaElement.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.css">

</head>
<body>
  
  <c:import url="../template/header.jsp"></c:import>
  
  <div>
  <section class="">
	<div>
		<!-- Button trigger modal -->
		<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal" style="background-color : white; border : none;" >
			<img src="/images/warning.png" alt="" width="10%;" height="100%">
		</button> 
		
		<!-- Modal -->
		<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
				<h1 class="modal-title fs-5" id="exampleModalLabel" style="text-align: center;">실시간 경매 주의사항</h1>
				<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<p>✅ 입찰 시, 본인의 포인트보다 높게 금액을 입력할 수 없습니다.</p>
					<p>✅ 경매 시작 전 입찰 금액 입력 시 반영이 되지 않습니다.</p>
					<p>✅ 경매 진행 중 욕설이나 타인을 비난하는 행위, 도배를 하는 행위는 강퇴를 당하실 수 있습니다. </p>
					<p>✅ 강퇴를 당하면 실시간 경매에 참여할 수 없습니다.</p>
					<p>✅ 경매 종료 시, 낙찰되셨을 때 바로 포인트가 차감됩니다.</p>
					<p>✅ 입찰은 1000원 단위로만 가능합니다.</p>

				</div>
				<div class="modal-footer">
				<button type="button" class="btn btn-primary" data-bs-dismiss="modal">확인</button>
				</div>
			</div>
			</div>
		</div>
	</div>
    <!-- 방송 시작 부분 -->
		<div id="videos-container" style="margin: 20px 0;">
			<div id="media-container" class = "media-container shadowBox col-sm-12">
				<div id="media-broadcast">
					<div id="media-header" >
						<div id="media-title">
							<h2 id="media-titleText"></h2>
						</div>
						<div id="item">
			          	  	<span id="selecteditem"></span>
			         	</div>  
					</div>
				
					<div id="media-box" class="media-box" style="position: relative;">
						<div id= "blackVideo" style="width: 100%; height: 100%; color: #a8a8a8; display:flex; align-items: center; justify-content: center;">
							<div>현재 실시간 경매를 진행하고 있지 않습니다.</div>
						</div>
						<video id="localVideo" autoplay playsinline hidden style="left: 50%;" ></video>
						
						
						<div id = "mediaControls" style="width: 100%;  position: absolute;">
						
							<div id="muteVideo" style="width: 35px; height: 35px;"></div>
							<div id="mute-audio" style="width: 35px; height: 35px;"></div>
							<div id="volume-slider" style="width: 35px; height: 35px;"></div>
							<div id="zoom" style="width: 35px; height: 35px; float: right;"></div>
						</div>
						
					</div>
					
				</div>
		
	
		
        <div class="chat-container">	
			<div id= "chat-header" >
	          <div id="text">
	            <span id="final">현재 최고가:</span>
	            <span id="amount">${value}</span>
	            <span id="hidden" style="display: none;">${value}</span>
	            <span id="finalamount"></span>
	          </div>
	          
	
	          <ul class="tabs">
	            <li class="tab-link current" data-tab="chatt">채팅창</li>
	            <li class="tab-link" data-tab="iddd">시청자 (<span id="count"></span>)</li>
	          </ul>
		
			</div>
          <div id='chatt' class="tab-content current">
              <div class="chat-box">
                <div id='talk'></div>
                <div id='sendZone'>
                  <input type='text' id='msg'  placeholder="메시지를 입력해보세요!">
                  <input class ="bidcoinBtn" type='button' value='보내기' id='btnSend'>
                </div>
              </div>
              <div id="bidZone">
                  <input type='button' value="직접입력" id="auction" class ="bidcoinBtn">
                  <input type='button' value="단위경매" id="add" class ="bidcoinBtn">
              </div>
          </div>
          <div id ="iddd" class="tab-content">
              
          </div>
        </div>
      </div>	
    </div>
    <input  type="hidden" id="room-id" value="123" readonly="readonly" autocorrect=off autocapitalize=off size=20>
    <sec:authorize access="hasRole('ROLE_ADMIN')" >
    
    <div style="display: flex; justify-content: center;">
    <div class="shadowBox media-container">
    	<div id="media-broad-option">
						<div class="optionBox">
							<div style="font-size: 20px; font-weight: bold;">방송설정</div>
							<div>
								방송 제목<input type="text" id="broadName" style="margin: 10px;"><input type="button" value="설정" id="setBroadNameBtn" class="bidcoinBtn">
								<button class= "bidcoinBtn" id="open-room">방송 시작</button>
								<button id="cameraStatus" class= "bidcoinBtn"> 방송 종료 </button>
							</div>
						  	<div>카메라 설정<select id="cameras"></select></div>
							<button id="cameraBlack" class= "bidcoinBtn"> 방송 송출 일시 정지 </button>
							<button id="screenShare" class= "bidcoinBtn"> 화면 공유로 전환 </button>
							
							
						</div>
						
						<div class="optionBox">
							<div style="font-size: 20px; font-weight: bold;">경매설정</div>
							<div>
				              <span>경매 물건</span>
                      <select id="items" name="items" style="width: 60%;">
                        <option value="none"selected>=== 선택 ===</option>
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
						
							<input type="button" class="bidcoinBtn" value="채팅정지" id='stopStart'>
						    <input type="button" class="bidcoinBtn" value="경매시작" id="auctionStart"> <!--배열 controller로 보내짐 / 가격 안변하게하기-->
						    <input type="button" class="bidcoinBtn" value="경매종료" id="end"> <!--session 닫힘-->
						
					
						</div>
					</div>
    
    
    </div></div>
    </sec:authorize>

	
    
  </div>
	<div style="display: none;">
      <h2 id="id">${member}</h2>
      <h2 id="loginnum">${num}</h2>
      <h2 id="point">${point}</h2>
      <h2 id="role">${role[0].roleName}</h2>
	</div>
	<div id="room-urls" style="width : 200px;text-align: center;display: none;background: #F1EDED;margin: 15px -10px;border: 1px solid rgb(189, 189, 189);border-left: 0;border-right: 0;"></div>
  </div>
  </section>
</div>

<!-- <c:import url="../template/footer.jsp"></c:import> -->

<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>


<script src='/js/liveauction/chat.js'></script>
<script src="/js/liveauction/RTCMultiConnection.js"></script>
<!-- <script src="/socket.io/socket.io.js"></script> -->
<script src="https://192.168.1.83:80/socket.io/socket.io.js"></script>
<script src="/js/liveauction/getHTMLMediaElement.js"></script>
<script src='/js/liveauction/index.js'></script>

  <footer>
    <small id="send-message"></small>
  </footer>

  <script src="https://www.webrtc-experiment.com/common.js"></script>

<script type="text/javascript">

	<sec:authorize access="hasRole('ROLE_ADMIN')" >
		adminChat();
		adminBroadCast();
	</sec:authorize>
	
	
	
	joinRoom();
	
	
</script>


</body>
</html>