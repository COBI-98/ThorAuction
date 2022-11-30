

// ......................................................
// .......................UI Code........................
// ......................................................
document.getElementById('open-room').onclick = function() {
    disableInputButtons();
    connection.open(document.getElementById('room-id').value, function() {
        showRoomURL(connection.sessionid);
    });
};

document.getElementById('join-room').onclick = function() {
    disableInputButtons();

    connection.sdpConstraints.mandatory = {
        OfferToReceiveAudio: true,
        OfferToReceiveVideo: true
    };
    connection.join(document.getElementById('room-id').value);
};

document.getElementById('open-or-join-room').onclick = function() {
    disableInputButtons();
    connection.openOrJoin(document.getElementById('room-id').value, function(isRoomExist, roomid) {
        if (isRoomExist === false && connection.isInitiator === true) {
            // if room doesn't exist, it means that current user will create the room
            showRoomURL(roomid);
        }

        if(isRoomExist) {
          connection.sdpConstraints.mandatory = {
              OfferToReceiveAudio: true,
              OfferToReceiveVideo: true
          };
        }
    });
};




// ......................................................
// ..................RTCMultiConnection Code.............
// ......................................................

var connection = new RTCMultiConnection();

// by default, socket.io server is assumed to be deployed on your own URL
//connection.socketURL = 'https://muazkhan.com:9001/';
connection.socketURL = 'https://192.168.1.83:80/';

// comment-out below line if you do not have your own socket.io server
// connection.socketURL = 'https://muazkhan.com:9001/';

connection.socketMessageEvent = 'video-broadcast-demo';

connection.session = {
    audio: true,
    video: true,
    oneway: true
};

connection.sdpConstraints.mandatory = {
    OfferToReceiveAudio: false,
    OfferToReceiveVideo: false
};

// https://www.rtcmulticonnection.org/docs/iceServers/
// use your own TURN-server here!
connection.iceServers = [{
    'urls': [
        'stun:stun.l.google.com:19302',
        'stun:stun1.l.google.com:19302',
        'stun:stun2.l.google.com:19302',
        'stun:stun.l.google.com:19302?transport=udp',
    ]
}];

connection.videosContainer = document.getElementById('videos-container');
connection.onstream = function(event) {
    var existing = document.getElementById(event.streamid);
    if(existing && existing.parentNode) {
      existing.parentNode.removeChild(existing);
    }

    event.mediaElement.removeAttribute('src');
    event.mediaElement.removeAttribute('srcObject');
    event.mediaElement.muted = true;
    event.mediaElement.volume = 0;



 var video = document.querySelector("#localVideo");


    if(event.type === 'local') {
      video.volume = 0;
      try {
          video.setAttributeNode(document.createAttribute('muted'));
      } catch (e) {
          video.setAttribute('muted', true);
      }
    }
    
    video.srcObject = event.stream;
    getCameras(video.srcObject);
  ///  
  	const cameraBlack = document.querySelector("#cameraBlack");
  	let cameraSelect=document.querySelector("#cameras")
  	cameraBlack.addEventListener("click",function(){
	
		video.srcObject.getVideoTracks().forEach((track) => (console.log(track)));
		console.log(cameraSelect.value);
	
	
		 video.srcObject.getVideoTracks().forEach((track) => (track.enabled = !track.enabled));
		 video.srcObject.getAudioTracks().forEach((track) => (track.enabled = !track.enabled));
	})
    
    
    document.querySelector("#screenShare").addEventListener("click",function(){ // 얼떨결에 화면공유.. ? 
			var videoStream =  video.srcObject;
			
			 navigator.mediaDevices.getDisplayMedia({
		        video: true,
		        audio: true,
		      })
		      .then((stream) => {
		          video.srcObject = stream; // 내 비디오 공유 화면으로 변경
		          var videoTrack = stream.getVideoTracks()[0];
		          
		          connection.replaceTrack(videoTrack,event.userId,true);
			          
			   
		          videoTrack.onended = function () {
	          	  const screenTrack = videoStream.getVideoTracks()[0];
	      		  connection.replaceTrack(screenTrack,event.userId,true);
			          
	        	  stream.getTracks().forEach((track) => track.stop());
		          video.srcObject = videoStream; // 내 비디오로 변경
	       		 };
		        
		        
				})
				//video.srcObject.getVideoTracks().forEach((track) => (track.stop()));
	//replaceTrack RTCM. 으로 이용 가능하다 (트랙, 아이디? , 비디오트랙인지) 
	
		 
	})
    
    
    ////
  
    document.querySelector("#cameras").addEventListener("input",function(){ 
			
			 navigator.mediaDevices.getUserMedia({
		        video: {
						'deviceId' : document.querySelector("#cameras").value
					},
		        audio: true,
		      })
		      .then((stream) => {
				video.srcObject = stream; // 내 비디오 공유 화면으로 변경
		          var videoTrack = stream.getVideoTracks()[0];
		          
		          connection.replaceTrack(videoTrack,event.userId,true);
			          
			   });
	})
    
    
    
    showVideo();
    function  showVideo(){
	
      	var width = parseInt(connection.videosContainer.clientWidth / 3) - 20;
        var mediaElement = getHTMLMediaElement(video, {
            title: event.userid,
            buttons: ['full-screen','volume-slider','mute-video','mute-audio'],
           	width: width,
            showOnMouseEnter: true
        });
    
        connection.videosContainer.appendChild(mediaElement);
    
        setTimeout(function() {
            mediaElement.media.play();
        }, 5000);
    
        mediaElement.id = event.streamid;

    }
   
};

async function getCameras(myStream){
    try{
        const devices = await navigator.mediaDevices.enumerateDevices();
        const cameras = devices.filter((device) => (device.kind === "videoinput"));
        const currentCamera = myStream.getAudioTracks()[0];
        cameras.forEach((camera) => {
            const option = document.createElement("option");
            option.value = camera.deviceId;
            option.innerText = camera.label;
            if(currentCamera.label === camera.label) {
                option.selected = true;
            }
            document.querySelector("#cameras").appendChild(option);
        });
    }catch(e){
        console.log(e);
    }
}



connection.onstreamended = function(event) {
    var mediaElement = document.getElementById(event.streamid);
    if (mediaElement) {
        mediaElement.parentNode.removeChild(mediaElement);

        if(event.userid === connection.sessionid && !connection.isInitiator) {
          alert('Broadcast is ended. We will reload this page to clear the cache.');
          location.reload();
        }
    }
};

connection.onMediaError = function(e) {
    if (e.message === 'Concurrent mic process limit.') {
        if (DetectRTC.audioInputDevices.length <= 1) {
            alert('Please select external microphone. Check github issue number 483.');
            return;
        }

        var secondaryMic = DetectRTC.audioInputDevices[1].deviceId;
        connection.mediaConstraints.audio = {
            deviceId: secondaryMic
        };

        connection.join(connection.sessionid);
    }
};

// ..................................
// ALL below scripts are redundant!!!
// ..................................

function disableInputButtons() {
    document.getElementById('room-id').onkeyup();

    document.getElementById('open-or-join-room').disabled = true;
    document.getElementById('open-room').disabled = true;
    document.getElementById('join-room').disabled = true;
    document.getElementById('room-id').disabled = true;
}

// ......................................................
// ......................Handling Room-ID................
// ......................................................

function showRoomURL(roomid) {
    var roomHashURL = '#' + roomid;
    var roomQueryStringURL = '?roomid=' + roomid;

    var html = '<h2>Unique URL for your room:</h2><br>';

    html += 'Hash URL: <a href="' + roomHashURL + '" target="_blank">' + roomHashURL + '</a>';
    html += '<br>';
    html += 'QueryString URL: <a href="' + roomQueryStringURL + '" target="_blank">' + roomQueryStringURL + '</a>';

    var roomURLsDiv = document.getElementById('room-urls');
    roomURLsDiv.innerHTML = html;

    roomURLsDiv.style.display = 'block';
}

(function() {
    var params = {},
        r = /([^&=]+)=?([^&]*)/g;

    function d(s) {
        return decodeURIComponent(s.replace(/\+/g, ' '));
    }
    var match, search = window.location.search;
    while (match = r.exec(search.substring(1)))
        params[d(match[1])] = d(match[2]);
    window.params = params;
})();

var roomid = '';
if (localStorage.getItem(connection.socketMessageEvent)) {
    roomid = localStorage.getItem(connection.socketMessageEvent);
} else {
    roomid = connection.token();
}
document.getElementById('room-id').value = roomid;
document.getElementById('room-id').onkeyup = function() {
    localStorage.setItem(connection.socketMessageEvent, document.getElementById('room-id').value);
};

var hashString = location.hash.replace('#', '');
if (hashString.length && hashString.indexOf('comment-') == 0) {
    hashString = '';
}

var roomid = params.roomid;
if (!roomid && hashString.length) {
    roomid = hashString;
}

if (roomid && roomid.length) {
    document.getElementById('room-id').value = roomid;
    localStorage.setItem(connection.socketMessageEvent, roomid);

    // auto-join-room
    (function reCheckRoomPresence() {
        connection.checkPresence(roomid, function(isRoomExist) {
            if (isRoomExist) {
                connection.join(roomid);
                return;
            }

            setTimeout(reCheckRoomPresence, 5000);
        });
    })();

    disableInputButtons();
}

// detect 2G
if(navigator.connection &&
   navigator.connection.type === 'cellular' &&
   navigator.connection.downlinkMax <= 0.115) {
  alert('2G is not supported. Please use a better internet service.');
}





