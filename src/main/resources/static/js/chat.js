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
		
		var item = `<div ${css} >
		                <span><b>${data.mid}</b></span> [ ${data.date} ]<br/>
                      <span class="text">${data.msg}</span>
						</div>`;
					
		talk.innerHTML += item;
		talk.scrollTop=talk.scrollHeight;//스크롤바 하단으로 이동
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
	if(msg.value.trim() != ''){
		data.mid = getId('mid').value;
		data.msg = msg.value;
		data.date = new Date().toLocaleString();
		data.count = count;
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

var pattern_num = /[0-9]/;	// 숫자

reset.addEventListener("click",function(){
	let index = talk.childNodes.length;
	for(let i=0;i<index;i++){
		var ttt = talk.children[i].childNodes[5].innerText
		if(ttt.substr(0,4) == "[경매]" && pattern_num.test(ttt.substr(4))){

			console.log(ttt.substr(4));
		}
		
	}
	// console.log(talk.children[0].childNodes[5].innerText);
	// console.log(talk.children[1].childNodes[5].innerText);
	// console.log(talk.children[2].childNodes[5].innerText);
	
})


