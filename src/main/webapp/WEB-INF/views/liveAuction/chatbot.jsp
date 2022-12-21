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
  <link href="/css/chatbot.css" rel="stylesheet">
  <link href="/images/Thor.jpg" rel="shortcut icon" type="image/x-icon">
  <link rel="stylesheet" href="/css/getHTMLMediaElement.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.css">

</head>
<body>
  
  <c:import url="../template/header.jsp"></c:import>
  
  <div>
  <section class="" style="padding-bottom: 100px;">

        <div class="chat-container show-grid" style="margin-left: 40px;">
            
			<div class="row">
				<div class="col-sm-12" style="margin-left: 20px;">
					<div class="row">
						<div class="col-xs-8 col-sm-8">
							<div id='chatt' class="tab-content current">
								<div class="chat-box" style="height : 0px;">
								  	<div id='talk' class="form-control" style="height: 650px;"></div>
									<div id='send'>
										<div>
											<input class="form-control" type='text' id='msg'  placeholder="메세지를 입력해보세요!" style="width: 50%; margin-top: 10px; margin-bottom: 10px;">
											<button type="button" class="bidcoinBtn" id="msgSend"><img src="/images/send.jpg" alt="" width="100%;" height="100%"></button>
										</div>
									</div>
								</div>
							</div>
						</div>


							<div class="col-xs-4 col-sm-3" style="display: block; height: 60%; padding-right: 0px;padding-left: 0px;" >
								<div class="card mb-5">
									<div class="card-header" id="qna"><b>질문 카테고리</b></div>
									<div class="card-body" id="sendZone">
										<c:forEach items="${ar}" var="key">
										<div>
											<input class ="bidcoinBtn form-control" type='button' value='${key}' id='btnSend'>
										</div>
										</c:forEach>
									</div>
								</div>
							<div class="card mb-4">
								<div class="card-header" id="qna"><b>질문 예시</b></div>
								<div class="card-body">
									<div>
										<p>✔실시간 경매 신청하려면 어떻게 하나요?</p>
										<p>✔일반 경매에 참여하려면 어떻게 하나요?</p>
										<p>✔일반 경매시 주의사항으로는 어떤 것이 있을까요?</p>
										<p>✔포인트는 어떻게 사용하나요?</p>
									</div>
								</div>
							</div>
							</div>
				</div>
			</div>
				
		</div>
          
    </div>
    
	
  </section>

 <c:import url="../template/footer.jsp"></c:import>
</div>

<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>


<script src='/js/liveauction/chatbot.js'></script>
<!-- <script src="/js/liveauction/RTCMultiConnection.js"></script> -->
<!-- <script src="/socket.io/socket.io.js"></script> -->
<!-- <script src="https://192.168.1.83:80/socket.io/socket.io.js"></script>
<script src="/js/liveauction/getHTMLMediaElement.js"></script> -->
<!-- <script src='/js/index.js'></script> -->
<!-- <c:import url="../template/footer.jsp"></c:import> -->

  <footer>
    <small id="send-message"></small>
  </footer>

  <!-- <script src="https://www.webrtc-experiment.com/common.js"></script> -->

</body>

</html>