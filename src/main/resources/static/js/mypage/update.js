// update.js

// let pw = $('#pw').val();
// let email = $('#email').val();
// let post = $('#post').val();
// let addr = $('#addr').val();
// let addr2 = $('#addr2').val();
// let phone = $("#phone").val();

// $("#update_btn").click(function () {
//     console.log("TLqkf");

//     if (email || email.trim() === "") {
//         alert("이메일을 입력해 주세요");

//         return false;
//     }
//     else if (post || post.trim() === "") {
//         alert("주소를 입력해 주세요");

//         return false;
//     }
//     else if (addr || addr.trim() === "") {
//         alert("주소를 입력해 주세요");

//         return false;
//     }
//     else if (phone || phone.trim() === "") {
//         alert("전화번호를 입력해 주세요");

//         return false;
//     }

// });

// 비밀번호, 이메일, 우편번호, 주소, 상세주소, 전화번호
$("#update_btn").click(function () {
    const data = {
        id: $('#id').val(),
        pw: $('#pw').val(),
        name: $('#name').val(),
        birth: $('#birth').val(),
        email: $('#email').val(),
        post: $('#post').val(),
        addr: $('#addr').val(),
        addr2: $('#addr2').val(),
        phone: $("#phone").val()
    };

    // 공백 및 빈 문자열 체크

    if (!data.pw || data.pw.trim() === "") {
        alert("비밀번호를 입력해 주세요");

        return false;
    }
    if (!data.email || data.email.trim() === "") {
        alert("이메일을 입력해 주세요");

        return false;
    }
    if (!data.post || data.post.trim() === "") {
        alert("주소를 입력해 주세요");

        return false;
    }
    if (!data.addr || data.addr.trim() === "") {
        alert("주소를 입력해 주세요");

        return false;
    }
    if (!data.phone || data.phone.trim() === "") {
        alert("전화번호를 입력해 주세요");

        return false;
    }

//     // 유효성 검사
//     // else if(!/^[가-힣a-zA-Z0-9]{2,10}$/.test(data.nickname)){
//     //     alert("닉네임은 특수문자를 포함하지 않은 2~10자리여야 합니다.");

//     //     $('#newNickname').focus();
//     //     return false;
//     // }

//     // else if(!/(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\W)(?=\S+$).{8,16}/.test(data.password)){
//     //     alert("비밀번호는 8~16자리수여야 합니다. 영문 대소문자, 숫자, 특수문자를 1개 이상 포함해야 합니다.");
//     //     $('#password').focus();

//     //     return false;
//     // }

    const confirmCheck = confirm("수정하시겠습니까?");

    if (confirmCheck == true) {
        $.ajax({
            type: 'POST',
            url: '/mypage/update',
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8", // body 데이터가 어떤 타입인지(MIME)
            // dataType: "json" // 요청을 서버로 해서 응답이 왔을 때 기본적으로 모든 것이 문자열(String)=>javascript 오브젝트로 변경 
            // dataType을 생략하면 요청한 자료에 맞게 자동으로 형식이 설정되기 때문에 생략해도 된다.
        }).done(function (result) {
                alert("회원 수정이 완료되었습니다.");
                window.location.href = "../";
        }).fail(function (error) {
              alert(JSON.stringify(error));
         });
    }
});