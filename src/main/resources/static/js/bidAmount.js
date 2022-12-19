
// var xmlHttp;
// var product_auction_state='Y';
//진행중인 경매만
// if(product_auction_state=='Y') {
// 	var interval = setInterval(remainTime,1000);
// }


// function remainTime() {
// 	var obj=document.auction_form;
// 	var st=srvTime();
// 	var nt = new Date(st); // 현재의 시간만 가져온다
// 	var tmpdate='';
// 	var tdate='';

// 	var open='';
// 	var ot='';

// 	tmpdate=new Date(obj.tdate.value);

// 	tdate=tmpdate.getFullYear()+","+tmpdate.getMonth()+","+tmpdate.getDate()+","+tmpdate.getHours()+","+tmpdate.getMinutes()+","+tmpdate.getSeconds();

// 	ot=tmpdate.getTime();
// 	var i=0;

// 	if(nt<ot){ //현재시간이 오픈시간보다 이르면 오픈시간까지의 남은 시간을 구한다.   
// 		sec = parseInt(ot - nt) / 1000;
// 		day = parseInt(sec/60/60/24);
// 		hour = Math.floor((sec / (60*60)) % 24);
// 		min = Math.floor((sec / (60)) % 60)
// 		sec = Math.floor(sec % 60);

// 		if(day<10){day="0"+day;}
// 		if(hour<10){hour="0"+hour;}
// 		if(min<10){min="0"+min;}
// 		if(sec<10){sec="0"+sec;}
// 		$("#d-day-day"+i+"").html(day);
// 		$("#d-day-hour"+i+"").html(hour);
// 		$("#d-day-min"+i+"").html(min);
// 		$("#d-day-sec"+i+"").html(sec);
// 	} else{ //현재시간이 종료시간보다 크면
// 		$("#d-day-day"+i+"").html('00');
// 		$("#d-day-hour"+i+"").html('00');
// 		$("#d-day-min"+i+"").html('00');
// 		$("#d-day-sec"+i+"").html('00');		

// 		var param="product_number=105";

// 		$.ajax({
// 			url:'x_bid_end.asp',
// 			data:param,
// 			type:'post',
// 			success:function(data, status, req) {	
// 				if(data != 'E') {
// 					document.getElementById("div_money_n").innerHTML=formatnumber(data);
// 					do_bid_run();
// 					clearInterval(interval);
// 					return;
// 				}
// 			},
// 			error:function(req, status, error) {
// 				swal(error);
// 			}
// 		});		
// 	}

// }
// 컴마 없애기
function uncomma(str) {
    str = String(str);
    return str.replace(/[^\d]+/g, '');
}
//컴마 찍기
function comma(str) {
    str = String(str);
    return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
}
//컴마 만들기
function numberFormat(txtObj) {
	var ostr=comma(uncomma(txtObj.value));
	txtObj.value=ostr;
}

function priceCutting(stype, n) { // 금액, 타입, 절삭금액 단위
	// var obj=document.auction_form;
	// var aprice=obj.bid_money.value;

    var aprice = $("#bid_money").val();
    // 원단위처리(R:반올림, C:올림, F:버림)
    aprice=uncomma(aprice);
    var remove_price = 0;
    remove_price = aprice / n;
 
    if (stype == "C") {
        remove_price = Math.ceil(remove_price);
    }
     
    remove_price = remove_price * n;
	var ostr=comma(uncomma(remove_price));
	$("#bid_money").val(ostr);
}

function dobid(tmp) {
	var user_check_id= $("#id").val();
	if(user_check_id=='' || user_check_id == null) {
		
		Swal.fire({
			title: "로그인하셔야 가능합니다.\n로그인하시겠습니까??",  // title, text , html  로 글 작성
			icon: "warning",    //상황에 맞는 아이콘

			showCancelButton: true,
			confirmButtonColor: '#fea532',
			confirmButtonText: '확인',
			cancelButtonText: '취소',
			reverseButtons: true   // 버튼 순서 변경
		}).then((result) =>{
			if(result.isConfirmed){
				location.href='/member/login';
			}
		})
		
	}
	else{

	
    swal({
        title: "입찰하시겠습니까?",
        text: "입찰하시면 포인트가 차감됩니다.",
        icon: "warning",
        buttons: true,
        dangerMode: true,
      })
      .then((wileDelete) => {
            if(wileDelete==true) {
                var product_money_t = $("#bidAmountCheck").val();
                var product_Id= $("#productId").val();
                var amountUnit = $("#amountUnit").val();

				var startAmount = $("#startAmount").val();
				var productDetailDate = $("#productDetailDate").val();
				var timeLength = $("#timeLength").val();
				var deadCheck = "";
                if(tmp == 'B'){
                    product_money_t=$("#bid_money").val();
					product_money_t = uncomma(product_money_t);
                }
                console.log("1 : "+wileDelete);
                console.log("2 : "+user_check_id);
                console.log("3 : "+tmp);
                console.log("4 : "+product_money_t);
                console.log("5 : "+product_Id);
                console.log("6 : " + amountUnit);
				
				const startDate = new Date(productDetailDate);
				const deadLine = new Date();
				
				if(((timeLength*24*3600*1000)+startDate.getTime()) > deadLine.getTime()){
					console.log("아직 진행");
					deadCheck = "0";
				}else{
					console.log("마감됨")
					deadCheck = "1";
				}
                 if(product_money_t=='' || product_money_t=='0') {
                     swal("입찰가격을 입력해주세요");s
                    return;
                 }

               
                $.ajax({
                    url:'./bid',
                    type:'post',
                    data:{
                        "productId" : product_Id,
                        "bidAmount" : product_money_t,
                        "id" :user_check_id,
						"startAmount" : startAmount,
						"deadCheck": deadCheck
                    },success:function(data) {
                        if(data==1) {
                            swal("경매가 종료되었습니다");
                        } else if(data==2) {
                            swal("입찰은 현가격보다 높아야 합니다");
                        } else if(data==3) {
                            swal("입찰은 시작가격보다 높아야 합니다.");
                        } else if(data==4) {
                            swal("해당 가격으로 이미 입찰되었습니다.").then(function(success){
								if(success){

									location.reload();
								}
							})
                        } else {
                            swal("정상적으로 입찰되었습니다","","success").then(function(){

                                location.reload();
                            })
                        }
                    },
                    error:function(req, status, error) {
                        (error);
                    }
                });	
            }
        }) 
	} 
}


function do_bid_run() {
	var param="p_number=105";

	$.ajax({
		url:'x_bid_run.asp',
		data:param,
		type:'post',
		success:function(data, status, req) {
			document.getElementById("div_auction_run").innerHTML=data;	
		},
		error:function(req, status, error) {
			swal(error);
		}
	});	
}

function do_fav() {

	var user_check_id=$("#id").val();
	var product_check_id =$("#productId").val();
	if(user_check_id=='' || user_check_id == null) {
		Swal.fire({
			title: "로그인하셔야 가능합니다.\n로그인하시겠습니까??",  // title, text , html  로 글 작성
			icon: "warning",    //상황에 맞는 아이콘

			showCancelButton: true,
			confirmButtonColor: '#fea532',
			confirmButtonText: '확인',
			cancelButtonText: '취소',
			reverseButtons: true   // 버튼 순서 변경
		}).then((result) =>{
			if(result.isConfirmed){
				location.href='/member/login';
			}
		})

		return;
	}

	console.log("rr");

	$.ajax({
		type:'POST',
		url:'./likeProduct',
		data:{
			productId : product_check_id,
			id : user_check_id  ,
			
		},success:function(data) {
			if(data == "Y") {
				swal("이미 관심경매로 등록되었습니다","마이페이지에서 확인 가능합니다.","warning");
			} else {
				swal("관심경매로 등록되었습니다.","마이페이지에서 확인 가능합니다.","success");
			}
		},
		error:function(error) {
			swal(error);
		}
	});	
	
}

function open_layer_qna() {
		var u_id=$("#id").val();
		var param=$("#productId").val();

		if(u_id=='' || u_id == null) {
			Swal.fire({
				title: "로그인하셔야 가능합니다.\n로그인하시겠습니까??",  // title, text , html  로 글 작성
				icon: "warning",    //상황에 맞는 아이콘
	
				showCancelButton: true,
				confirmButtonColor: '#fea532',
				confirmButtonText: '확인',
				cancelButtonText: '취소',
				reverseButtons: true   // 버튼 순서 변경
			}).then((result) =>{
				if(result.isConfirmed){
					location.href='/member/login'
				}
			})
		}else{
			document.getElementById("layer_pop_qna").style.display="block";	
			document.getElementById("fixed_pop").style.display="block";
		}
		
		
		// 		if (data=='n') {
		// 			document.getElementById("layer_pop_qna").style.display="none";
		// 			swal("상품문의는 로그인을 해야만 작성이 가능합니다.");
		// 			return;
		// 		} else {
		// 			document.getElementById("layer_pop_qna").style.display="block";	
		// 			document.getElementById("fixed_pop").style.display="block";
		// 			document.getElementById("layer_pop_qna").innerHTML=data;
		// 		}
				
		// 	},
		// 	error:function(req, status, error) {
		// 		swal(error);
		// 	}
		// });		
		
	}

	function do_save_qna() {
		var u_id=$("#id").val();
		if(u_id=='') {
			Swal.fire({
				title: "로그인하셔야 가능합니다.\n로그인하시겠습니까??",  // title, text , html  로 글 작성
				icon: "warning",    //상황에 맞는 아이콘
	
				showCancelButton: true,
				confirmButtonColor: '#fea532',
				confirmButtonText: '확인',
				cancelButtonText: '취소',
				reverseButtons: true   // 버튼 순서 변경
			}).then((result) =>{
				if(result.isConfirmed){
					location.href='/member/login';
				}
			})
		}
		
		

		var obj=document.write_form2;
		if($("#commentTitle").val() != "" && $("#commentContents").val() != ""){

			obj.action="./questionAdd";
			obj.submit();
		}else {
			swal("제목과 문의내용을 입력해주세요.","","warning")
		}
	}

	function do_ment_view2(qna_idx) {
		if(document.getElementById("do_menu2"+qna_idx).className=='over') {
			document.getElementById("do_menu2"+qna_idx).className="";	
		} else {
			document.getElementById("do_menu2"+qna_idx).className="over";	
		}
	}

	function close_layer() {
		document.getElementById("fixed_pop").style.display="none";
		document.getElementById("layer_pop_qna").style.display="none";		
	}

function do_delete_question_user(qna_dep_num){

	Swal.fire({
		title: "댓글을 삭제하시겠습니까?",  // title, text , html  로 글 작성
		icon: "warning",    //상황에 맞는 아이콘

		showCancelButton: true,
		confirmButtonColor: '#fea532',
		confirmButtonText: '확인',
		cancelButtonText: '취소',
		reverseButtons: true   // 버튼 순서 변경
	}).then((result) =>{
		if(result.isConfirmed){
			$.ajax({
				type :"POST",
				url :"./questionDelete",
				data:{
					questionId : qna_dep_num
				},success:function(data) {
					if(data == 1){
						swal("삭제되었습니다","","success").then(function(){
							location.reload();
						})
					}else{
						swal("삭제 실패!","","error")
					}
				},error:function(){
					console.log("Error 발생");
				}
			})
		}else{
			swal("취소하셨습니다");
		}
		
	})
}

function do_delete_question_admin(qna_admin_num){
	Swal.fire({
		title: "댓글을 삭제하시겠습니까?",  // title, text , html  로 글 작성
		icon: "warning",    //상황에 맞는 아이콘

		showCancelButton: true,
		confirmButtonColor: '#fea532',
		confirmButtonText: '확인',
		cancelButtonText: '취소',
		reverseButtons: true   // 버튼 순서 변경
	}).then((result) =>{
		if(result.isConfirmed){
			$.ajax({
				type :"POST",
				url :"./adminQuestionDelete",
				data:{
					aqNum : qna_admin_num
				},success:function(data) {
					if(data == 1){
						swal("삭제되었습니다","","success").then(function(){
							location.reload();
						})
					}else{
						swal("삭제 실패!","","error")
					}
				},error:function(){
					console.log("Error 발생");
				}
			})
		}else{
			swal("취소하셨습니다");
		}
		
	})
}
