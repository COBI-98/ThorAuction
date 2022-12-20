
$("#modalSubmit").click(function(){
    console.log($("#approvalCheck").val());
    console.log($("#application_num").val());

    if($("#approvalCheck").val() == ""){
        swal("취소사유를 선택해주세요","","warning");
    }else{
        $.ajax({
            type:"post",
            url:"./approvalCheckUpdate",
            data:{
                applicationNum : $("#application_num").val(),
                approvalCheck : $("#approvalCheck").val()
            },success:function(result){
                if(result == 1){
                    swal("취소사유 작성 완료","","success").then(function(){
                        location.reload();
                    })
                }
            },error:function(){
                swal("에러");
            }
    
        })
    }
})

$("#approval_btn").click(function(){

    $.ajax({
        type:"post",
        url:"./approvalCheckUpdate",
        data:{
            applicationNum : $("#application_num").val(),
            approvalCheck : 1
        },success:function(result){
            if(result == 1){
                swal("승인완료","","success")
            }
        },error:function(){
            swal("에러");
        }

    })
})

$("#approval_btn2").click(function(){

    $.ajax({
        type:"post",
        url:"./approvalCheckUpdate",
        data:{
            applicationNum : $("#application_num").val(),
            approvalCheck : 1
        },success:function(result){
           
        },error:function(){
            swal("에러");
        }

    })
})