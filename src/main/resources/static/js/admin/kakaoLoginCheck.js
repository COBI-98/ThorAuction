// 카카오 로그인시 DB에 저장된 값과 비교하기
// ajax로 연결 어떻게 하는지 찾기
// 어느 JSP에서 해야 할지

function idcheck() {
    // let kNickName = $("#kaName").val();
    let kNickName = $(".kNickName").val();

    console.log(kNickName);

    $.ajax({
        url: '/socialMember/IdCheck',
        type: "POST",
        data: { kaNickName: kNickName },
        traditional: true,
        success: function (rs) {
            console.log(rs);
            swal({
                title: "카카오 로그인",
                icon: "info",
            }).then(function () {
                if (rs == 0) {
                    swal({
                        title: "카카오 회원가입",
                        icon: "info",
                    }).then(function () {
                        location = '/oauth2/authorization/kakao';
                    })
                    // location = '/kakaoLogin';
                }
                else {
                    swal({
                        title: "연결이 유효함",
                        text: '카카오로 로그인합니다.',
                        icon: "success",
                    }).then(function () {
                        location = '/oauth2/authorization/kakao';
                    })
                    // location = '/';
                }
            });
        },
        error: function (textStatus, errorThrown) {
            alert("서버 요청 실패");
            console.log(textStatus);
            console.log(errorThrown);
        }
    })
}
