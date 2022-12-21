
var kakaoRole = $(".krole").val();
console.log(kakaoRole);

$.ajax({
    url: './list',
    type: 'GET',
    traditional: true,
    data: {
        role: kakaoRole
    },
    success: function () {
        console.log("통신완료");
        if (kakaoRole == 'ROLE_BAN') {
            swal({
                title: "로그인 차단",
                text: '관리자에 의해 로그인이 차단되었습니다.',
                icon: "error"
            }).then(function () {
                location = '/member/logout';
                return false;
            })
        }
    },
    error: function (status, error) {
        console.log("안돼");
        console.log(status);
        console.log(error);
    }
});

