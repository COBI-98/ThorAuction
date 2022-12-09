// updatepw.js

results = [false, false, false]

// 비밀번호 정규식
let userPwCheck = RegExp(/[^a-zA-Z0-9]$/);

// 현재 비밀번호 일치 검증
$('#pw').blur(function () {
    const checkPassword = $('#pw').val();

    $.ajax({
        type: 'POST',
        url: '/mypage/checkpw',
        data: {
            'checkPassword': checkPassword,
        },
        datatype: "text"
    }).done(function (result) {
        console.log(result);
        console.log(checkPassword);

        if (result) {
            console.log("비번 일치");

            results[0] = true

        } else if (!result) {
            console.log("비번 틀림");
            
            results[0] = false
        }
    }).fail(function (error) {
        Swal.fire({
            title: '서버 에러',
            html: '잠시 후에 다시 이용해 주세요',
            icon: 'error',
            confirmButtonColor: '#3085d6',
            confirmButtonText: '확인'
        })
    })

});

// 새 비밀번호 검증
$("#newPassword").on({
    blur: function() {
        if($('#newPassword').val() == "") {
            $('#newPasswordMsg').html("필수항목임");
            $("#newPasswordMsg").attr("style", "color:#f00");
        }
    },

    change: function() {

        // 새 비밀번호 변경 시 새 비밀번호 재확인 창 초기화
		$("#newPasswordEquals").val("");
		results[1] = false;
		$("#newPasswordEqualsMsg").text("비밀번호가 일치하지 않습니다");
		$("#newPasswordEqualsMsg").attr("style", "color:#f00");

        if ($('#newPassword').val().length < 8 || $('#newPassword').val().length > 16) {
            $('#newPasswordMsg').html("8~16자 영문 대ㆍ소문자, 숫자만 사용해 주세요");
            $('#newPasswordMsg').attr("style", "color:#f00");
    
            results[1] = false;
        } else {
            $('#newPasswordMsg').html("");
    
            results[1] = true;
        }

        if(userPwCheck.test($('#newPassword').val())) {
            $('#newPasswordMsg').html("8~16자 영문 대ㆍ소문자, 숫자만 사용해 주세요");
            $('#newPasswordMsg').attr("style", "color:#f00");

            results[1] = false;
        }
    }
});

// 새 비밀번호 재확인 검증
$("#newPasswordEquals").blur(function () {
	if ($("#newPassword").val() != $("#newPasswordEquals").val()) {
		$("#newPasswordEqualsMsg").text("비밀번호가 일치하지 않습니다");
		$("#newPasswordEqualsMsg").attr("style", "color:#f00");

		results[2] = false;
	} else {
		$("#newPasswordEqualsMsg").text("");

		results[2] = true;
	}
});


$('#updatepw_btn').click(function() {
    const data = {
        id: $('#id').val(),
        pw: $('#newPassword').val(), // 새 비밀번호 id로 변경
        name: $('#name').val(),
        birth: $('#birth').val(),
        email: $('#email').val(),
        post: $('#post').val(),
        addr: $('#addr').val(),
        addr2: $('#addr2').val(),
        phone: $("#phone").val()
    };

    const confirmCheck = confirm("수정하시겠습니까?");

    if (results.includes(false)) {
        alert("필수 항목을 모두 입력해 주세요.");
    } else {
        if (confirmCheck == true) {
            $.ajax({
                type: 'POST',
                url: '/mypage/updatepw',
                data: JSON.stringify(data),
                contentType: "application/json; charset=utf-8", // body 데이터가 어떤 타입인지(MIME)
                // dataType: "json" // 요청을 서버로 해서 응답이 왔을 때 기본적으로 모든 것이 문자열(String)=>javascript 오브젝트로 변경 
                // dataType을 생략하면 요청한 자료에 맞게 자동으로 형식이 설정되기 때문에 생략해도 된다.
            }).done(function (result) {
                    alert("비밀번호 수정이 완료되었습니다. 다시 로그인해 주세요");
                    location.href = "../";
            }).fail(function (error) {
                  alert(JSON.stringify(error));
             });
        }

    }
});


