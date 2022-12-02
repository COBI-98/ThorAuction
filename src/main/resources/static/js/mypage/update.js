// update.js

results = [false];

$("#update_btn").click(function () {
    $.get("./checkpw?pw=" + $("#pw").val(), function (data) {
        console.log("Data : ", data);

        if (data == '0') {
            results[0] = false;

            Swal.fire({
                icon: 'warning',
                title: '수정 실패',
                html: '비밀번호가 틀렸습니다.',
            })          
        } else {
            results[0] = true;

            Swal.fire({
            icon: 'success',
            title: '수정 완료',
            html: '정보 수정이 완료되었습니다.<br> 로그인 페이지로 이동합니다',
        }).then(function () {
            $("#upadte_form").submit();
        })
        }
    });

});

// // 비밀번호, 이메일, 우편번호, 주소, 전화번호
// let results = [false, false, false, false, false];

// $("#pw").on({

// })

// $("#update_btn").click(function () {
//     // if (results.includes(false)) {
//     //     alert("빈칸 있음ㅇㅇ");
//     // } else {
//         Swal.fire({
//             icon: 'success',
//             title: '수정 완료',
//             html: '정보 수정이 완료되었습니다.<br> 로그인 페이지로 이동합니다',
//         }).then(function () {
//             $("#upadte_form").submit();
//         })
//     // }
// });

// //     if (results.includes(false)) {
// //         alert("빈칸 있음ㅇㅇ");
// //     } else {
// //         $.ajax({
// //             type: "POST",
// //             url: "/mypage/update",
// //             data: {
// //                 id: $("#id").val(),
// //                 pw: $("#pw").val(),
// //                 name: $("#name").val(),
// //                 birth: $("#birth").val(),
// //                 email: $("#email").val(),
// //                 post: $("#post").val(),
// //                 addr: $("#addr").val(),
// //                 addr2: $("#addr2").val(),
// //                 phone: $("#phone").val()
// //             },
// //             success: function () {
// //                 Swal.fire({
// //                     icon: 'success',
// //                     title: '수정 완료',
// //                     html: '정보 수정이 완료되었습니다.<br> 로그인 페이지로 이동합니다',
// //                 }).then(function () {
// //                     $("#upadte_form").submit();
// //                 })

// //             },
// //             error: function () {

// //                 Swal.fire({
// //                     icon: 'error',
// //                     title: '수정 실패',
// //                     text: '빈칸을 다시 확인해 주세요',
// //                 });
// //             }

// //         });
// //     }
// // });

// // });

// // let index = {
// //     init: function () {
// //         //function () { } 대신 () => { } : this를 바인딩하기 위해서
// //         $("#update_btn").on("click", () => {
// //             this.update();
// //         });

// //     },

// //     update: function () {
// //         let data = {
// //             id: $("#id").val(),
// //             password: $("password").val(),
// //             name: $("#name").val(),
// //             birth: $("#birth").val(),
// //             email: $("#email").val(),
// //             post: $("#post").val(),
// //             addr: $("#addr").val(),
// //             addr2: $("#addr2").val(),
// //             phone: $("#phone").val()
// //         };

// //         $.ajax({
// //             type: "PUT", // 회원정보 수정 요청
// //             url: "/member",
// //             date: JSON.stringify(date), // http body 데이터
// //             contentType: "application/json; charset=utf-8",	// body 데이터가 어떤 타입인지
// // 			dataType: "json" // 서버로 요청 -> 응답이 왔을 때 기본적으로 모든 것이 문자열(String) => javascript 오브젝트로 변경
// //         }).done(function (resp) {
// //             alert("수정완료");
// //             location.href("/mypage/");
// //         }).fail(function (resp) {
// //             alert(JSON.stringify(error));
// //         })
// //     }
// // }

// // index.init();