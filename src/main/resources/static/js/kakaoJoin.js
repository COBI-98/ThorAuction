let namea = $("#nickName").val();

$("#kakaoJoinBtn").click(function () {
    $.ajax({
        url: "/member/kakaoLogin",
        type: "GET",
        data: { kaNickName: namea },
        
        success: function (rs) {
            if (rs == 1) {
                alert("중복된 아이디");
                // location.history();
                return;
            }
        },
        error: function (status, error) {
            console.log("status: ", status);
            console.log("error: ", error);
        }
    })
})
