/**
 * web socket
 */


function getId(id){
	return document.getElementById(id);
}

var data = {};//전송 데이터(JSON)
var data2 = {};//전송 데이터(JSON)
var data3 = {};
var data4 = {};
var data5 = {};
var data6 = {};
var data7 = {};
var data8 = {};
var data9 = {};
var data10 = {};
var data11 = {};
var data12 = {};
var data13 = {};

var ws ;

var userid = getId('id');
var btnLogin = getId('btnLogin');
var btnSend = getId('btnSend');
var talk = getId('talk');
var msg = getId('msg');
var auction = getId('auction');
var stop = getId('stop');
var count = 'tnuoc';
var reset = getId('reset');
var usercount = getId('count');
var amount = getId('amount');
var iddd = getId('iddd');
var auctionend = getId('auctionend');
var final = getId('final');
var finalamount = getId('finalamount');
var end = getId('end');
var point =getId('point');
var hidden = getId('hidden');
var add = getId('add');
var list = getId('list');
var listttt = getId('listttt');
var unit = getId('unit');
var unitsend = getId('unitsend');
var items = getId('items');
var itemsend = getId('itemsend');
var selecteditem = getId('selecteditem');
var loginnum = getId('loginnum');

var username;
var win = false;
let aaa = amount.innerText*1;
var pattern_num = /[0-9]/;	// 숫자 판별

var userlist = [];
var rank = [aaa,"id"]; //최고값, id

ws = new WebSocket("wss://" + location.host + "/chatt");


//입장 시
ws.onopen = function(){
	if(auctionend)
	usercome();
}

//퇴장 시
// ws.onclose = function() {
// 	usercome();
// }

//메시지 받기
ws.onmessage = function(msg){
	var data = JSON.parse(msg.data);
	var css;
	var cssid;

	//로그인 종류
	if(data.loginnum != null) {
		data13.loginnum = loginnum.innerText;
		var temp = JSON.stringify(data13);
		ws.send(temp);
	}

	//경매 물품 설정 시
	else if(data.item != null) {
		selecteditem.innerText = data.item;
	}
	
	//단위 경매 설정 시
	if(data.unit != null){
		talk.innerHTML += `<div class="hi">`+"단위 가격이 " + data.unit + "원 으로 변경되었습니다." +`</div>`;
		add.value = "+" + data.unit;
	}

	//경매 시작시
	else if(data.start != null){
		if(data.gogo == true) {
			auctionend.value="경매종료";
			talk.innerHTML += `<div class="hi">`+ "*경매가 시작되었습니다.*" +`</div>`;
	
			final.innerText ="경매 최고가:";
			//최종금액 초기화, 안보이게
			finalamount.innerHTML = 0;
			$('#finalamount').css("display","none");
	
			//최고금액 보이게
			$('#amount').css("display","inline");
			
		}else{
			auctionend.value="경매시작";
		}


	}

	// //단위가격 클릭시
	// else if(data.id != null){
	// 	console.log(data);
	// 	rank[0] = data.value;
	// 	rank[1] = data.id;
	// 	amount.innerHTML = rank[0];
	// }

	//방송 일시정지 시
	else if(data.pause != null){
		let muteVideo = document.querySelector("#muteVideo");
		muteVideo.click();
	 }

	//경매 종료 시
	else if(data.amount != null) {
		if(data.gg == false) {
			auctionend.value="경매시작";
			final.innerText = "최종 금액:";
			let ff = rank[0];
			$('#amount').css("display","none");
			finalamount.innerText = ff;
			amount.innerHTML = 0;
			rank[0] =aaa;
			rank[1] = "id";
			max = 0;
			win = false;
			$('#finalamount').css("display","inline");
			talk.innerHTML += `<div class="hi">`+ "*경매가  종료되었습니다.*" +`</div>`;
			selecteditem.innerText = "";
		}else{
			auctionend.value="경매종료";
		}
	}

	//강퇴 당했을 시
	else if(data.out != null) {
		Swal.fire({
			title: "강퇴 되었습니다.",  // title, text , html  로 글 작성
			icon: "error",    //상황에 맞는 아이콘
	
			showCancelButton: true,
			confirmButtonColor: '#3085d6',
			confirmButtonText: '확인',
			//reverseButtons: true   // 버튼 순서 변경
		} ).then((result) => {   // 아무 버튼이나 누르면 발생
			location.href="../../";
		})
	}

	//방송 종료
	else if(data.end != null) {
		ws.close();
		location.href="../../";
	}

	//채팅 메세지 
	else if(data.mid != null) {

		

		let a = data.msg;
		var b = a.substr(4)*1;
		
		if(auctionend.className == "start") {
			if(data.msg.substr(0,4) =="[경매]" && pattern_num.test(b) && data.win == data.mid && b==data.value){
				if(rank[0]==0 && rank[1] == ''){
					cssid = 'id=enter';
				}else if(!(data.mid == rank[1] && b == rank[0])) {
					cssid = 'id=enter';
				}
			}
		}
		rank[0] = data.value;
		rank[1] = data.win;
		amount.innerHTML = rank[0];
		console.log(rank);
		
		if(auctionend.className == "start") {
			if(data.msg.substr(0,4) =="[경매]" && pattern_num.test(b) && data.win == data.mid && b==rank[0]){
				cssid = 'id=enter';
			}
		}
		
		var item;
		if(data.mid == userid.innerText){
			css = 'class=me';
			item = `
				
					<div ${css} ${cssid}>
						
						<div class="chat-date">
							 ${data.date} 
						</div>
						
						<div class="chat-text">
							<span><b class="name">${data.mid}</b></span> <br/>
							<span class="text">${data.msg}</span>
						</div>
					</div>`;
			
		}else{
			css = 'class=other';
			item = `
				<div ${css} ${cssid}>
						
						<div class="chat-text">
							<span><b class="name">${data.mid}</b></span> <br/>
							<span class="text">${data.msg}</span>
						</div>
						<div class="chat-date">
							 ${data.date} 
						</div>
					</div>`;
		}
		
		
		
						
						
						
						
						
					
		talk.innerHTML += item;
		talk.scrollTop=talk.scrollHeight;//스크롤바 하단으로 이동
		
	}

	//얼리기
	else if(data.stop != null){
		console.log(data);
		if(data.stop ==true){
			
			msg.innerHTML ='';
			$('#msg').attr("readonly",true);
		}else{
			$('#msg').attr("readonly",false);
		}
	}

	//입장시
	else if(data.usercome != null) {
		
		console.log(data);
		console.log(data.gogo);

		var user = data.usercome.substr(1,data.usercome.length-2);
		userlist = user.split(',');
		
		iddd.innerHTML="";
		for(let i=0;i<userlist.length;i++){
			iddd.innerHTML += `<div id="user">`+"👀"+ userlist[i].trim() + `</div>`;
		}
		talk.innerHTML += `<div class="hi">` + data.come + "님이 입장하셨습니다." +`</div>`;
		usercount.innerHTML = userlist.length;
		
		//얼리기 설정
		if(data.ppp =="true"){
			stop.value="얼리기 해제";
			$('#msg').attr("readonly",true);
		}else{
			stop.value="얼리기";
			$('#msg').attr("readonly",false);
		}

		//경매시작 설정
		if(data.gogo == "true") {
			auctionend.value = "경매종료";
			auctionend.className = 'start';
		}else if(data.gogo == "false"){
			auctionend.value = "경매시작";
		}

		rank[0] = data.value *1;
		rank[1] = data.winner;
		console.log(rank);

		//단위 가격 설정
		add.value = "+"+data.price;

		//경매 물품 설정
		selecteditem.innerText = data.goods;
	}

	//퇴장시
	else if(data.name != null){
		iddd.innerHTML="";

		for(let i=0;i<data.list.length;i++) {
			iddd.innerHTML += `<div>`+"👀"+data.list[i].trim() + `</div>`;
		}

		usercount.innerHTML = data.list.length;
		talk.innerHTML += `<div class="hi">` + data.name + "님이 퇴장하셨습니다." +`</div>`;

		console.log(data);
	}	
}


//회원 강퇴시키기
iddd.addEventListener("click",function(event){
	let even = event.target;
	if(even.id== "user") {
		Swal.fire({
			title: even.innerText + " 님을 강퇴시키겠습니까?",  // title, text , html  로 글 작성
			icon: "warning",    //상황에 맞는 아이콘
	
			showCancelButton: true,
			confirmButtonColor: '#3085d6',
			confirmButtonText: '강퇴',
			cancelButtonText: '취소',
			reverseButtons: true   // 버튼 순서 변경
		} ).then((result) => {   // 아무 버튼이나 누르면 발생
			if (result.isConfirmed) {  // confirm 버튼을 눌렀다면,
				
				//강퇴 진행
				data7.out = even.innerText.substr(2);
				var temp = JSON.stringify(data7);
				ws.send(temp);
				
				Swal.fire({    
					title: "강퇴 되었습니다.",
					icon: "success",
					confirmButtonColor: '#3085d6',
					
					confirmButtonText: '확인'
				} ).then((result) => {
				})
			}
		})
	}
})

msg.onkeyup = function(ev){
	if(ev.keyCode == 13){
		send();
	}
}

btnSend.onclick = function(){
	send();
}

//메세지 전송
function send(){

	let index = msg.value;
	var max = amount.innerText*1;
	var t4 = index.substr(4)*1;
	var mypoint = point.innerText;

	if(auctionend.className == "start") {

		if(index.substr(0,4) == "[경매]" && pattern_num.test(t4)){
			if(t4 <= mypoint){
				if(t4 > max) {
					max=t4;	
					console.log(max);
					amount.innerHTML = max;
					win = getId('id').innerHTML;
				}else{
					win = false;
				}
			}else{
				win=false;
				Swal.fire({
					title: "보유중인 포인트보다 높게 입력하셨습니다.",  // title, text , html  로 글 작성
					icon: "error",    //상황에 맞는 아이콘
			
					showCancelButton: true,
					confirmButtonColor: '#3085d6',
					confirmButtonText: '확인',
					//reverseButtons: true   // 버튼 순서 변경
				} ).then((result) => {   // 아무 버튼이나 누르면 발생
				})
			}
		}else{
			win=false;
		}
	}
	
	if(msg.value.trim() != ''){
		data.mid = getId('id').innerHTML;
		data.msg = msg.value;
		data.date = new Date().toTimeString().split(' ')[0];
		data.value = max;
		data.point = point.innerText;
		data.win = win;
		var temp = JSON.stringify(data);
		ws.send(temp);
	}
	msg.value =''
}

//경매 버튼
auction.addEventListener("click",function(){
	var text = msg.value;
	msg.value='';
	msg.value = "[경매]" + text;
})


//얼리기
stop.addEventListener("click",function(){
	stop.classList.toggle("stop");
	sendstop();
})

function stopchat(){
	if(stop.classList.contains("stop")){
		return true;
	}
	return false;
}

function sendstop(){
	data3.stop = stopchat();

	var temp = JSON.stringify(data3);
	ws.send(temp);
}


//경매 시작,종료 시 
auctionend.addEventListener("click",function(){
	auctionend.classList.toggle("start");

	//경매 시작 클릭 시
	if(auctionend.value == "경매시작")	 {
		auctionstart();
	
	//경매 종료 클릭 시
	}else{
		sendresult();
	}
})

//경매 시작 함수
function auctionstart(){

	data10.start = "경매가 시작되었습니다.";
	data10.gogo = start();
	var temp = JSON.stringify(data10);
	ws.send(temp);
}

function start() {
	if(auctionend.classList.contains("start")){
		return true;
	}
	return false;
}

//경매 종료 함수
function sendresult() {
	data4.amount = rank[0];
	data4.winner = rank[1];
	data4.gg = start();
	data4.loginnnn = "dlkjslkfj";
	var temp = JSON.stringify(data4);
	ws.send(temp);
}

//user 입장 함수
function usercome(){
	data5.usercome = "emocresu"; //list 
	data5.come = userid.innerText;
	data5.ppp = "potsss";
	data5.gogo = "trats";
	data5.price = "ecirp";
	data5.winner = "renniw";
	data5.value = "eulav";
	data5.goods = "sdoog";
	var temp =JSON.stringify(data5);
	ws.send(temp);
}

//실시간 경매 종료 (소켓 닫음)
end.addEventListener("click",function(){

	Swal.fire({
		title: "실시간 경매를 종료하시겠습니까?",  // title, text , html  로 글 작성
		icon: "warning",    //상황에 맞는 아이콘

		showCancelButton: true,
		confirmButtonColor: '#3085d6',
		confirmButtonText: '종료',
		cancelButtonText: '취소',
		reverseButtons: true   // 버튼 순서 변경
	} ).then((result) => {   // 아무 버튼이나 누르면 발생
		if (result.isConfirmed) {  // confirm 버튼을 눌렀다면,
			
			data6.end = true;
			var temp = JSON.stringify(data6);

			Swal.fire({    
				title: "종료 되었습니다.",
				icon: "success",
				confirmButtonColor: '#3085d6',
				
				confirmButtonText: '확인'
			} ).then((result) => {
				if (result.isConfirmed) {
					ws.send(temp);
				}
			})
		}
	})

})

//방송 일시정지
let cameraPause = document.querySelector("#cameraPause")

cameraPause.addEventListener("click",function(){
   cameraPause.classList.toggle("pause");
   sendPause();
})

function pauseCam(){
   if(cameraPause.classList.contains("pause")){
      return true;
   }   
   return false;
}

function sendPause(){
   data2.pause = pauseCam();
   var temp = JSON.stringify(data2);
   ws.send(temp);
}

//최고가 변경시 hidden 값 변경 (1초 뒤)
$('#amount').on('DOMSubtreeModified propertychange',function(){
	setTimeout(() => hidden.innerText = amount.innerText,1000);
}) 

//단위 가격 누를 시
add.addEventListener("click",function(){
	if(auctionend.className == "start") {
		var mm = hidden.innerHTML*1;
		var aa = add.value.substr(1) *1;
		var text = mm+aa;

		msg.value='';
		msg.value = "[경매]" + text;
	}
})

//채팅창, 회원목록 tab 클릭 시
$('ul.tabs li').click(function(){
	var tab_id = $(this).attr('data-tab');
	$('ul.tabs li').removeClass('current');
	$('.tab-content').removeClass('current');

	$(this).addClass('current');
	$("#"+tab_id).addClass('current');
})

//단위가격 설정
unitsend.addEventListener("click",function(){
	console.log(unit.value);
	data11.unit = unit.value;
	var temp = JSON.stringify(data11);
	ws.send(temp);
})

//경매 물품 선택
itemsend.addEventListener("click",function(){
	//items.options[items.selectedIndex].value;
	console.log(items.options[items.selectedIndex].value);
	data12.item = items.options[items.selectedIndex].value;
	var temp = JSON.stringify(data12);
	ws.send(temp);
})
