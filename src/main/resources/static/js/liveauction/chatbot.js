$("#sendZone").click(function(event){
    var str = event.target.value;
    console.log(str);
    item = `<div class="me"> <span>${str}</span> </div>`;
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
            item = `<div class="other">
                        <div>
                            <ul>${str}</ul>
                        </div>
                        <div>
							<li><b class="text">${result.content1}</b></li> <br/>
							<li class="text">${result.content2}</li> <br/>
							<li class="text">${result.content3}</li> <br/>
                        </div>
					</div>`;
            talk.innerHTML += item;
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

    item = `<div class="me"> <span>${str}</span> </div>`;
    talk.innerHTML += item;

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
            if(str == "일반 경매 하러가기") {
                item = `<div class="other">
                        <div>
							<a href =${result.content} class="text">일반 경매</b> <br/>
                        </div>
					</div>`;
            }else{
                item = `<div class="other">
                            <div>
                                <b class="text">${result.content}</b> <br/>
                            </div>
                        </div>`;
            }
            talk.innerHTML += item;
        },
        error : function(result){
            console.log("시류ㅐ");
            console.log(result);

        }
    })
})