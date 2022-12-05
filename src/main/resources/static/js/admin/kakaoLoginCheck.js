// 카카오 로그인시 DB에 저장된 값과 비교하기
// ajax로 연결 어떻게 하는지 찾기
// 어느 JSP에서 해야 할지

function idcheck() {
    swal({
        title: "카카오 로그인",
        icon: "info",
    }).then(function () {
        location = '/oauth2/authorization/kakao';
    });
    
    // let kNickName = $(".kNickName").val();

    // console.log(kNickName);

    // $.ajax({
    //     url: '/socialMember/IdCheck',
    //     type: "POST",
    //     data: { kaNickName: kNickName },
    //     traditional: true,
    //     success: function (rs) {
    //         console.log(rs);
    //         swal({
    //             title: "카카오 로그인",
    //             icon: "info",
    //         }).then(function () {
    //             location = '/oauth2/authorization/kakao';
    //         });
    //     },
    //     error: function (textStatus, errorThrown) {
    //         alert("서버 요청 실패");
    //         console.log(textStatus);
    //         console.log(errorThrown);
    //     }
    // })
}
