
$(".MID").each(function (index, m) {
})

var memRole = $(".MID").val();

console.log("member Role: ", memRole);

$.ajax({
    url: './list',
    type: 'GET',
    traditional: true,
    data: {
        role: memRole
    },
    success: function () {
        console.log("통신됫냐");
        if (memRole == 'ROLE_BAN') {
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
    error: function (status, a, error) {
        console.log(status);
        console.log(error);
    }
})


