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

}

