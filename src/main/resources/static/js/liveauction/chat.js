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
let aaa = amount.innerText*1;
var rank = [aaa,"id"]; //최고값, id
var end = getId('end');
var point =getId('point');
var userlist = [];
var username;
var win = false;
var add = getId('add');

var pattern_num = /[0-9]/;	// 숫자 판별

ws = new WebSocket("wss://" + location.host + "/chatt");


//입장 시
ws.onopen = function(){
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

	//경매 시작시
	if(data.start != null){
		talk.innerHTML += "*경매가 시작되었습니다.*";
	}
	//단위가격 클릭시
	else if(data.id != null){
		console.log(data);
		rank[0] = data.value;
		rank[1] = data.id;
		amount.innerHTML = rank[0];
	}

	//방송 일시정지 시
	else if(data.pause != null){
		let muteVideo = document.querySelector("#muteVideo");
		muteVideo.click();
	 }

	//경매 종료 시
	else if(data.amount != null) {
		final.innerText = "최종 금액";
		let ff = rank[0];
		$('#amount').css("display","none");
		finalamount.innerText = ff;
		talk.innerHTML += "*경매가 종료 되었습니다.*";
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

		if(data.mid == userid.innerText){
			css = 'class=me';
		}else{
			css = 'class=other';
		}

		let a = data.msg;
		var b = a.substr(4)*1;
		
		rank[0] = data.value;
		rank[1] = data.win;
		amount.innerHTML = rank[0];
		console.log(rank);
		
		if(auctionend.className == "start") {
			if(data.msg.substr(0,4) =="[경매]" && pattern_num.test(b) && data.win == data.mid && b==rank[0]){
				cssid = 'id=enter';
			}
		}

		var item = `<div ${css} ${cssid}>
						<span><b class="name">${data.mid}</b></span> [ ${data.date} ]<br/>
					<span class="text">${data.msg}</span>
						</div>`;
					
		talk.innerHTML += item;
		talk.scrollTop=talk.scrollHeight;//스크롤바 하단으로 이동
		
	}

	//얼리기
	else if(data.stop != null){
		let value = data.stop;
		$('#msg').attr("readonly",value);
	}

	//입장시
	else if(data.usercome != null) {
		var user = data.usercome.substr(1,data.usercome.length-2);
		userlist = user.split(',');
		
		iddd.innerHTML="";
		for(let i=0;i<userlist.length;i++){
			iddd.innerHTML += `<div>`+userlist[i] + `</div>`;
		}
		talk.innerHTML += `<div class="hi">` + data.come + "님이 입장하셨습니다." +`</div>`;
		usercount.innerHTML = userlist.length;
	}

	//퇴장시
	else if(data.name != null){
		iddd.innerHTML="";
		for(let i=0;i<data.list.length;i++) {
			iddd.innerHTML += `<div>`+data.list[i] + `</div>`;
		}
		usercount.innerHTML = data.list.length;
		talk.innerHTML += `<div class="hi">` + data.name + "님이 퇴장하셨습니다." +`</div>`;

		console.log(data);
	}	
}


//회원 강퇴시키기
iddd.addEventListener("click",function(event){
	let even = event.target;

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
			data7.out = even.innerText;
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
		data.date = new Date().toLocaleString();
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
	if(auctionend.value == "경매시작")	 {
		auctionend.value = "경매 종료";
		auctionstart();
	}else{
		sendresult();
	}
})

//경매 시작 함수
function auctionstart(){
	data10.start = "경매가 시작되었습니다.";
	var temp = JSON.stringify(data10);
	ws.send(temp);
}

//경매 종료 함수
function sendresult() {
	data4.amount = rank[0];
	data4.winner = rank[1];
	var temp = JSON.stringify(data4);
	ws.send(temp);
}

//user 입장 함수
function usercome(){
	data5.usercome = "emocresu"; //list 
	data5.come = userid.innerText;
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

//단위 가격 누를시
add.addEventListener("click",function(){
	if(auctionend.className == "start") {

		var ii = getId('id').innerHTML;
		var mm = amount.innerText*1 ;
		var pp = point.innerText*1;
		var aa = 1000 *1;
		if(mm + aa > pp) {
			Swal.fire({
				title: "보유중인 포인트보다 높게 입력하셨습니다.",  // title, text , html  로 글 작성
				icon: "error",    //상황에 맞는 아이콘
		
				showCancelButton: true,
				confirmButtonColor: '#3085d6',
				confirmButtonText: '확인',
				//reverseButtons: true   // 버튼 순서 변경
			} ).then((result) => {   // 아무 버튼이나 누르면 발생
			})
		}else{
			data9.id = ii;
			data9.value = mm+aa;
			var temp = JSON.stringify(data9);
			ws.send(temp);
		}
	}
})

