
function dobid(tmp) {
	var user_check_id= $("#id").val();
    
	if(user_check_id=='') {
		var login = swal("입찰하려면 로그인을 하셔야 합니다.n로그인하시겠습니까");
		if(login==true) {
			location.href='/member/login';
			return;
		}
	}
	var ans=swal("입찰하시겠습니까?")
    swal({
        title: "입찰하시겠습니까?",
        text: "입찰하시면 포인트가 차감됩니다.",
        icon: "warning",
        buttons: true,
        dangerMode: true,
      })
      .then((wileDelete) => {
            if(wileDelete==true) {
                var product_money_t = $("#startAmount").val();
                var product_Id= $("#productId").val();
                var amountUnit = $("#amountUnit").val();
                if(tmp == 'B'){
                    product_money_t=$("#bid_money").val();
                }
                console.log("1 : "+wileDelete);
                console.log("2 : "+user_check_id);
                console.log("3 : "+tmp);
                console.log("4 : "+product_money_t);
                console.log("5 : "+product_Id);
                console.log("6 : " + amountUnit);

                 if(product_money_t=='' || product_money_t=='0') {
                     swal("입찰가격을 입력해주세요");
                    return;
                 }

               
                $.ajax({
                    url:'bid',
                    type:'post',
                    data:{
                        "productId" : product_Id,
                        "bidAmount" : product_money_t,
                        "id" :user_check_id
                    },success:function(data) {
                        if(data==1) {
                            swal("시작기간 전입니다");
                        } else if(data==2) {
                            swal("경매가 종료되었습니다");
                        } else if(data==3) {
                            swal("입찰은 현가격보다 높아야 합니다");
                        } else if(data==4) {
                            swal("입찰은 시작가격보다 높아야 합니다.");
                        } else if(data==5) {
                            swal("경매 중단 기간이라 참여가 불가합니다.");
                        } else if(data==6) {
                            swal("해당 가격으로 이미 입찰되었습니다.");
                            location.reload();
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
