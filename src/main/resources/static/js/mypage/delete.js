// delete.js


$("#delete_btn").click(function () {
    const checkPassword = $('#pw').val();

    if ($("#pw").val() == "") {
        Swal.fire({
            title: '탈퇴 실패',
            html: '비밀번호를 입력해 주세요',
            icon: 'warning',
            confirmButtonColor: '#3085d6',
            confirmButtonText: '확인'
        });
    } else {
        $.ajax({
            type: 'POST',
            url: '/mypage/checkpw',
            data: {
                'checkPassword': checkPassword,
            },
            datatype: "text"
        }).done(function (result) {
            console.log(result);

            if (result) {
                console.log("비번 일치");
                //$('#delete_form').submit();
                //location.href="../";
            } else if (!result) {
                console.log("비번 틀림");

                alert("비번 땡");
            }
        }).fail(function (error) {
            alert("서버 에러");
        })

    }
});