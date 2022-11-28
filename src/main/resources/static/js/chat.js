/**
 * web socket
 */

let cameraPause = document.querySelector("#cameraPause")


cameraPause.addEventListener("click",function(){
	
	cameraPause.classList.toggle("pause");
	sendPause();
	
	
})





function getId(id){
	return document.getElementById(id);
}

var data = {};//전송 데이터(JSON)
var data2 = {};//전송 데이터(JSON)
var ws ;
var mid = getId('mid');
var btnLogin = getId('btnLogin');
var btnSend = getId('btnSend');
var talk = getId('talk');
var msg = getId('msg');


	ws = new WebSocket("wss://" + location.host + "/chatt");
	
	ws.onmessage = function(msg){
		
		var data = JSON.parse(msg.data);
		var css;
		
		
		
		if(data.pause == null){
			if(data.mid == mid.value){
			css = 'class=me';
			}else{
				css = 'class=other';
			}
			
			var item = `<div ${css} >
			                <span><b>${data.mid}</b></span> [ ${data.date} ]<br/>
	                      <span>${data.msg}</span>
							</div>`;
			talk.innerHTML += item;
			talk.scrollTop=talk.scrollHeight;//스크롤바 하단으로 이동
			
		}else{
			
			
				let muteVideo = document.querySelector("#muteVideo");
				muteVideo.click();
			
			
			
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
		
		var temp = JSON.stringify(data);
		ws.send(temp);
	}
	msg.value ='';
	
}

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
