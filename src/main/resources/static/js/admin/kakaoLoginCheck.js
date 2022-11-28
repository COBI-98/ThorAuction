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
        success: function (result) {
            console.log(result);
            if (result == 1) {
                swal({
                    title: "카카오 로그인",
                    text: "아이디가 이미 존재합니다.",
                    icon: "info",
                    // closeOnClickOutside: false
                }).then(function (rs) {
                    // 이벤트
                    if (rs) {
                        location.href = "/";
                        return false;
                    }
                });
                // swal("카카오 로그인", "아이디가 이미 존재합니다.", "info");
                // location.href = "/";
                // return false;
            }
            else {
                alert("아이디 없음");
            }
        },
        error: function (textStatus, errorThrown) {
            alert("서버 요청 실패");
            console.log(textStatus);
            console.log(errorThrown);
        }
    })

}
