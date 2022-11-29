/**
 * web socket
 */

function getId(id){
	return document.getElementById(id);
}

var data = {};//전송 데이터(JSON)
var data3 = {};
var data4 = {};
var data5 = {};

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
var userlist = [];
var username;

var pattern_num = /[0-9]/;	// 숫자 판별

ws = new WebSocket("ws://" + location.host + "/chatt");
// //로그인 , 메세지 전송 받아오기
// btnLogin.onclick = function(){


ws.onopen = function(){
	// enterid.push(userid.innerText);
	// for(let i=0;i<enterid.length;i++) {
	// 	console.log(enterid[i]);
	// 	iddd.innerHTML += enterid[i];
	// }
	var user = userid.innerText;
	// $.ajax({
	// 	type:"POST",
	// 	url:"chatid",
	// 	data:{
	// 		user : user

	// 	},
	// 	success:function(result) {
	// 		console.log("Result : ",  result);

	// 		for(let i=0;i<result.length;i++) {
	// 			enterid.push(result[i]);
	// 			iddd.innerHTML += enterid[i];
	// 		}
	// 	},
	// 	error:function(){
	// 		console.log("error");
	// 	}
	// })
	usercome();
	//userlist.push(userid.innerText);
	//console.log(userlist);
	//iddd.innerHTML += user;

}

ws.onclose = function() {
	usercome();

}

ws.onmessage = function(msg){
	var data = JSON.parse(msg.data);
	var css;
	var cssid;
	if(data.mid != null) {

		if(data.mid == userid.innerText){
			css = 'class=me';
		}else{
			css = 'class=other';
		}

		let a = data.msg;
		var b = a.substr(4)*1;
		
		if(data.msg.substr(0,4) =="[경매]" && pattern_num.test(b) && b>rank[0]){
			cssid = 'id=enter';
		}
		
		var cc = `<div>현재인원 : ${data.count}</div>`;
		usercount.innerHTML = cc;

		// var am = `<div>현재 금액 : ${data.value}</div>`;
		// amount.innerHTML = am;

		
		var item = `<div ${css} ${cssid}>
						<span><b class="name">${data.mid}</b></span> [ ${data.date} ]<br/>
					<span class="text">${data.msg}</span>
						</div>`;
					
		talk.innerHTML += item;
		talk.scrollTop=talk.scrollHeight;//스크롤바 하단으로 이동
			let index = talk.childNodes.length;
			var ttt = talk.children[index-1].childNodes[5].innerText;
			var id = talk.children[index-1].childNodes[1].innerText;
			console.log(id);
			//var t4 = ttt.substr(4)*1;
		// 	var max = amount.innerText;
			// if(ttt.substr(0,4) == "[경매]" && pattern_num.test(t4)){
			// 	if(t4 > max) {
			// 		max=t4;	
			// 		console.log(max);
			// 		amount.innerHTML = max;
			// 	}
			// }
			//console.log(rank);

		//let index = msg.value;
		var t4 = ttt.substr(4)*1;
		if(ttt.substr(0,4) == "[경매]" && pattern_num.test(t4)){

				if(t4 > rank[0]) {
					rank[0] = t4;
					amount.innerHTML = rank[0];
					rank[1] = id;
				}
			}
			console.log(rank);
	}
	else if(data.stop != null){
		let value = data.stop;
		$('#msg').attr("readonly",value);
	}else{
		iddd.innerHTML="";
		iddd.innerHTML += data;
	}
	// let size = talk.childNodes.length;
	// for(let i=0;i<size;i++) {
	// 	var userName = talk.children[i].childNodes[1].innerText;
	// 	userName.addEventListener("click",function(event){
	// 		console.log(event);
	// 	})
	// }
	
}
//}

//회원 강퇴시키기
iddd.addEventListener("click",function(event){
	let even = event.target;
	if(even.classList[0] == 'name') {
		alert(even.innerText +" 님을 강퇴시키겠습니까?");
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

	
	// let index = msg.value;
	// var t4 = index.substr(4)*1;
	// 	if(index.substr(0,4) == "[경매]" && pattern_num.test(t4)){
	
	// 			if(t4 > rank[0]) {
	// 				if(t4>rank[1]) {
	// 					if(t4 > rank[2]) {
	// 						rank[2] = t4;
	// 					}else{
	// 						rank[1] = t4;
	// 					}
	// 				}else{
	// 					rank[0]= t4;
	// 				}
	// 			}
	// 		}
	// 		console.log(rank);
	let index = msg.value;
	var max = amount.innerText*1;
	var t4 = index.substr(4)*1;
	if(index.substr(0,4) == "[경매]" && pattern_num.test(t4)){
		if(t4 > max) {
			max=t4;	
			console.log(max);
			amount.innerHTML = max;
		}
	}
	
	if(msg.value.trim() != ''){
		data.mid = getId('id').innerHTML;
		data.msg = msg.value;
		data.date = new Date().toLocaleString();
		data.count = count;
		data.value = max;
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


//경매 종료 시 
auctionend.addEventListener("click",function(){
	final.innerText = "최종 금액";
	let ff = rank[0];
	$('#amount').css("display","none");
	finalamount.innerText = ff;
	sendresult();
})

function sendresult() {
	data4.amount = rank[0];
	data4.winner = rank[1];
	var temp = JSON.stringify(data4);
	ws.send(temp);

}

function usercome(){
	data5.usercome = userid.innerText;
	var temp =JSON.stringify(data5);
	ws.send(temp);
}




// reset.addEventListener("click",function(){
// 	$.ajax({
// 		type:"POST",
// 		url:"chat",
// 		data:{
// 			rank : rank[2]

// 		},
// 		success:function(result) {
// 			console.log("Result : ",  result);
// 		},
// 		error:function(){
// 			console.log("error");
// 		}

// 	})
	
	
// })

// setInterval(function() {
// 	$.ajax({
// 		type:"POST",
// 		url:"chat",
// 		data:{
// 			rank : rank[2]

// 		},
// 		success:function(result) {
// 			console.log("Result : ",  result);
// 		},
// 		error:function(){
// 			console.log("error");
// 		}

// 	})
// },30000);


// let ii = talk.childNodes.length;
// var nikname = talk.children[ii-1];
// //nikname.addEventListener("click",function(){
// 	console.log(ii);
// 	console.log(talk.children[ii-1]);
	//console.log(nikname);
//})
