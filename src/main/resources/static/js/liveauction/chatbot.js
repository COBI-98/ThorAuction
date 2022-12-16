function getId(id){
	return document.getElementById(id);
}

$("#sendZone").click(function(event){
    var str = event.target.value;
    console.log(str);
    item = `<div class="me"> <img src="/images/user.jpg" style="width: 30px; margin-top: 5px;"> <span>${str}</span> </div><hr>`;
    talk.innerHTML += item;
    $.ajax({
        type:'POST',
        url : '/click',
        dataType: 'json',
        data : {
            str:str
        },
        success : function(result) {
            console.log("성공");
            console.log(result);
            console.log(result.content);
            item = `<div class="other chat-text">
                        <img src="/images/coin.png" style="width: 30px; margin-top: 5px;">
                        <div>
                            <ul><b>${str}</b></ul>
                        </div>
                        <div>
							<li class="text">${result.content1}</li> <br/>
							<li class="text">${result.content2}</li> <br/>
							<li class="text">${result.content3}</li> <br/>
                        </div>
					</div>
                    <hr>`
                    ;
            talk.innerHTML += item;
            talk.scrollTop=talk.scrollHeight;//스크롤바 하단으로 이동
        },
        error : function(result){
            console.log("시류ㅐ");
            console.log(result);

        }
    })
});

$("#talk").click(function(event){
    var str = event.target.innerText;
    console.log(str);

    item = `
            <div class="me"> <img src="/images/user.jpg" style="width: 30px; margin-top: 5px;"> <span>${str}</span> </div><hr>`;
    talk.innerHTML += item;

    if(str =="일반 경매" || str == "실시간 경매") {
        send2(str);
    }else{

        $.ajax({
            type:'POST',
            url : '/message',
            dataType: 'json',
            data : {
                str:str
            },
            success : function(result) {
                console.log("성공");
                console.log(result);
                console.log(result.content);
                
                item = `${result.content}`;
                talk.innerHTML += item;
                talk.scrollTop=talk.scrollHeight;//스크롤바 하단으로 이동
                console.log($(".text").html());
            },
            error : function(result){
                console.log("시류ㅐ");
                console.log(result);

            }
        })
    }
})

var msg = getId('msg');
msg.addEventListener("keyup", function (event) {
    if (event.keyCode === 13) {
      event.preventDefault();
      document.getElementById("msgSend").click();
    }
})

$("#msgSend").click(function(){
    let index = msg.value;
    var str;

    me(index);
    if(index.includes('일반')) {
        if(index.includes('일반') && index.includes("경매") && index.includes("신청") || index.includes("게시판")) {
            str = "일반 경매 신청하는 법";
            send1(str);
        }
        else if(index.includes('일반') && index.includes("경매") && index.includes("주의사항")){
            str = "일반 경매 주의사항";
            send1(str);
        }
        else if(index.includes('일반') && index.includes("경매") && index.includes("하러") || index.includes("하는") || index.includes("참여") || index.includes("방법") || index.includes("어떻게")){
            str = "일반 경매 하러가기";
            send1(str);
        }
        else if(index.includes('일반') && index.includes("경매")) {
            str = "일반 경매";
            send2(str);
        }else{
            str="일반 경매";
            send2(str);
        }
    }

    else if(index.includes('실시간')) {
        if(index.includes('실시간') && index.includes("경매") && index.includes("신청") || index.includes("게시판")) {
        str = "실시간 경매 신청하는 법";
        send1(str);
        }
        else if(index.includes('실시간') && index.includes("경매") && index.includes("주의사항")){
            str = "실시간 경매 주의사항";
            send1(str);
        }
        else if(index.includes('실시간') && index.includes("경매") && index.includes("하러") || index.includes("하는") || index.includes("참여") || index.includes("방법") || index.includes("어떻게")){
            str = "실시간 경매 하러가기";
            send1(str);
        }
        else if(index.includes('실시간') && index.includes("경매")) {
            str = "실시간 경매";
            send2(str);
        }else{
            str="실시간 경매";
            send2(str);
        }
    }

    else if(index.includes('경매')) {
        if(index.includes('경매') && index.includes('승인') || index.includes('기간')) {
            str = "경매 승인 기간";
            send1(str);

        }else if(index.includes('경매') && index.includes('신청')){
            str = "경매 신청하러 가기";
            send1(str);

        }else{
            str = "경매";
            $.ajax({
                type:'POST',
                url : '/bid',
                dataType: 'json',
                data : {
                    str:str
                },
                success : function(result) {
                    console.log("성공");
                    console.log(result);
                    console.log(result.content);
                    item = `<div class="other chat-text">
                                <img src="/images/coin.png" style="width: 30px; margin-top: 5px;">
                                <div>
                                    <ul>${str}</ul>
                                </div>
                                <div>
                                    <li><b class="text">${result.content1}</b></li> <br/>
                                    <li class="text">${result.content2}</li> <br/>
                                    <li class="text">${result.content3}</li> <br/>
                                </div>
                            </div>
                            <hr>`;
                    talk.innerHTML += item;
                    talk.scrollTop=talk.scrollHeight;//스크롤바 하단으로 이동
                },
                error : function(result){
                    console.log("시류ㅐ");
                    console.log(result);
        
                }
            })
        }
    }

    if(index.includes('배송')) {
        if(index.includes('배송') && index.includes('기간')) {
            str = "배송 기간";
            send1(str);

        }else if(index.includes('배송') && index.includes('주소') && index.includes('잘못')){
            str = "배송 주소 잘못적으셨다면?";
            send1(str);
        }else if(index.includes('배송') && index.includes('주소')){
            str = "배송 주소";
            send1(str);
        }
        else{
            str = "배송";
            send2(str);
        }
    }

    if(index.includes('포인트')){
        if(index.includes('포인트') && index.includes('충전')){
            str = "포인트 충전";
            send1(str);

        }else if(index.includes('포인트') && index.includes('사용') || index.includes('방법')) {
            str = "포인트 사용 방법";
            send1(str);
        }else {
            str = "포인트 충전";
            send1(str);
        }
    }

})


function me(index) {
    item = `<div class="me"> <img src="/images/user.jpg" style="width: 30px; margin-top: 5px;"> <span>${index}</span> </div><hr>`;
    talk.innerHTML += item;
    console.log("hihi");
    
    msg.value='';
}

function send1(str) {
    $.ajax({
        type:'POST',
        url : '/message',
        dataType: 'json',
        data : {
            str:str
        },
        success : function(result) {
            console.log("성공");
            console.log(result);
            console.log(result.content);
            
            // if((str == "일반 경매 하러가기") || (str == "실시간 경매 하러가기")) {
            //     item = `${result.content}`;
            //     talk.innerHTML += item;
            // }
            // else if((str == "일반 경매 신청하는 법") || (str == "실시간 경매 신청하는 법") || (str == "경매 신청하는 법"))  {
            //     item = `<div class="other">
            //                 <img src="/images/coin.png" style="width: 30px; margin-top: 5px;">
            //                 <div class="chat-text">
            //                     <a href =${result.url} class="text">신청하러 가기</a><br/>
            //                     <b class="text">${result.content}</b> <br/>
            //                 </div>
            //             </div>
            //             <hr>`;

            // }else if(str == "포인트 충전") {
            //     item = `<div class="other">
            //                 <img src="/images/coin.png" style="width: 30px; margin-top: 5px;">
            //                 <div class="chat-text">
            //                     <a href =${result.url} class="text">포인트 충전하러 가기</a><br/>
            //                     <b class="text">${result.content}</b> <br/>
            //                 </div>
            //             </div>
            //             <hr>`;
            // }
            // else{
            //     item = `<div class="other">
            //                 <img src="/images/coin.png" style="width: w 30px; margin-top: 5px;">
            //                 <div class="chat-text">
            //                     <b class="text">${result.content}</b> <br/>
            //                 </div>
            //             </div>
            //             <hr>`;
            // }
            item = `${result.content}`;
            //talk.innerHTML += item;
            talk.innerHTML += item;
            talk.scrollTop=talk.scrollHeight;//스크롤바 하단으로 이동
        },
        error : function(result){
            console.log("시류ㅐ");
            console.log(result);

        }
    })
}


function send2(str) {
    $.ajax({
        type:'POST',
        url : '/click',
        dataType: 'json',
        data : {
            str:str
        },
        success : function(result) {
            console.log("성공");
            console.log(result);
            console.log(result.content);
            item = `<div class="other">
                        <img src="/images/coin.png" style="width: 30px; margin-top: 5px;">
                        <div>
                            <ul>${str}</ul>
                        </div>
                        <div>
							<li><b class="text">${result.content1}</b></li> <br/>
							<li class="text">${result.content2}</li> <br/>
							<li class="text">${result.content3}</li> <br/>
                        </div>
					</div>
                    <hr>`;
            talk.innerHTML += item;
            talk.scrollTop=talk.scrollHeight;//스크롤바 하단으로 이동
        },
        error : function(result){
            console.log("시류ㅐ");
            console.log(result);

        }
    })
}