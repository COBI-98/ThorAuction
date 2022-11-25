/**
 * web socket
 */

function getId(id){
	return document.getElementById(id);
}

var data = {};//전송 데이터(JSON)

var ws ;
var mid = getId('mid');
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
let aaa = amount.innerText*1;
var rank = [aaa,"id"]; //3등, 2등, 1등 순 초기값 : 시작값으로 변경할 예정 -> ajax로 controller에 보내서 저장해서 뿌려줄 예정
var enterid = [];

var pattern_num = /[0-9]/;	// 숫자 판별

btnLogin.onclick = function(){
	ws = new WebSocket("ws://" + location.host + "/chatt");

	ws.onmessage = function(msg){
		var data = JSON.parse(msg.data);
		var css;
		
		if(data.mid == mid.value){
			css = 'class=me';
		}else{
			css = 'class=other';
		}
		
		var cc = `<div>현재인원 : ${data.count}</div>`;
		usercount.innerHTML = cc;

		// var am = `<div>현재 금액 : ${data.value}</div>`;
		// amount.innerHTML = am;

		
		var item = `<div ${css} >
		                <span><b>${data.mid}</b></span> [ ${data.date} ]<br/>
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
				}
			}
			console.log(rank);
			amount.innerHTML = rank[0];
			rank[1] = id;
			
	}
}

msg.onkeyup = function(ev){
	if(ev.keyCode == 13){
		send();
	}
}

btnSend.onclick = function(){
	send();
}

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
		data.mid = getId('mid').value;
		data.msg = msg.value;
		data.date = new Date().toLocaleString();
		data.count = count;
		data.value = max;
		var temp = JSON.stringify(data);
		ws.send(temp);
	}
	msg.value =''
}

auction.addEventListener("click",function(){
	var text = msg.value;
	msg.value='';
	msg.value = "[경매]" + text;
})

stop.addEventListener("click",function(){
	$('#msg').attr("readonly",true);
})


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



