// update.js

// 주소
/* 다음 주소 연동 */
function execution_daum_address() {

	new daum.Postcode({
		oncomplete: function (data) {

			// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
			// 각 주소의 노출 규칙에 따라 주소를 조합한다.
			// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
			var addr = ''; // 주소 변수
			var extraAddr = ''; // 참고항목 변수

			//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
			if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
				addr = data.roadAddress;
			} else { // 사용자가 지번 주소를 선택했을 경우(J)
				addr = data.jibunAddress;
			}

			// 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
			if (data.userSelectedType === 'R') {
				// 법정동명이 있을 경우 추가한다. (법정리는 제외)
				// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
				if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
					extraAddr += data.bname;
				}
				// 건물명이 있고, 공동주택일 경우 추가한다.
				if (data.buildingName !== '' && data.apartment === 'Y') {
					extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
				}
				// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
				if (extraAddr !== '') {
					extraAddr = ' (' + extraAddr + ')';
				}
				// 조합된 참고항목을 해당 필드에 넣는다.
				addr += extraAddr;

			} else {
				addr += ' ';
			}

			// 우편번호와 주소 정보를 해당 필드에 넣는다.
			$("#post").val(data.zonecode);
			$("#addr").val(addr);

			// 상세주소 입력란 disabled 속성 변경 및 커서를 상세주소 필드로 이동한다.
            $("#addr2").val("");
			$("#addr2").attr("readonly", false);
			$("#addr2").focus();

		}
	}).open();
}

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

// 전화번호 정규식
const autoHyphen = (target) => {
    target.value = target.value
        .replace(/[^0-9]/g, '')
        .replace(/^(\d{3})(\d{3,4})(\d{4})$/, `$1-$2-$3`);
}

// 이메일 정규식

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

    function isEmail(asValue) {
        var regExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;

        return regExp.test(asValue);
    }

    // 공백 및 빈 문자열 체크
    if (!isEmail(data.email)) {
        Swal.fire({
            icon: 'warning',
            title: '잠시만요!',
            text: '이메일 형식에 맞게 입력해 주세요',
        })

        return false;
    }

    if (!data.email || data.email.trim() === "") {
        Swal.fire({
            icon: 'warning',
            title: '잠시만요!',
            text: '이메일을 입력해 주세요',
        })

        return false;
    }

    if (!data.post || data.post.trim() === "") {
        Swal.fire({
            icon: 'warning',
            title: '잠시만요!',
            text: '주소를 입력해 주세요',
        })

        return false;
    }
    if (!data.addr || data.addr.trim() === "") {
        Swal.fire({
            icon: 'warning',
            title: '잠시만요!',
            text: '주소를 입력해 주세요',
        })

        return false;
    }
    if (!data.phone || data.phone.trim() === "") {
        Swal.fire({
            icon: 'warning',
            title: '잠시만요!',
            text: '전화번호를 입력해 주세요',
        })

        return false;
    }

    //    if (!data.pw || data.pw.trim() === "") {
    //        alert("비밀번호를 입력해 주세요");

    //        return false;
    //    }

    //     // else if(!/(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\W)(?=\S+$).{8,16}/.test(data.password)){
    //     //     alert("비밀번호는 8~16자리수여야 합니다. 영문 대소문자, 숫자, 특수문자를 1개 이상 포함해야 합니다.");
    //     //     $('#password').focus();

    //     //     return false;
    //     // }

    Swal.fire({
        title: "회원 수정",  // title, text , html  로 글 작성
        html: "수정하시겠습니까?",
        icon: "question",    //상황에 맞는 아이콘
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        confirmButtonText: '수정',
        cancelButtonText: '취소',
        reverseButtons: false   // 버튼 순서 변경
    }).then((result) => { // 아무 버튼이나 누르면 발생
        if (result.isConfirmed) {  // confirm 버튼을 눌렀다면,
            $.ajax({
                type: 'POST',
                url: '/mypage/update',
                data: JSON.stringify(data),
                contentType: "application/json; charset=utf-8", // body 데이터가 어떤 타입인지(MIME)
                // dataType: "json" // 요청을 서버로 해서 응답이 왔을 때 기본적으로 모든 것이 문자열(String)=>javascript 오브젝트로 변경 
                // dataType을 생략하면 요청한 자료에 맞게 자동으로 형식이 설정되기 때문에 생략해도 된다.
            }).done(function (result) {
                Swal.fire({
                    icon: 'success',
                    title: '수정 완료',
                    text: '내 정보 수정이 완료되었습니다',
                }).then(function () {
                    location.href = "../mypage/info?id=" + data.id;
                })
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
        }
    })
});



//     const confirmCheck = confirm("수정하시겠습니까?");

//     if (confirmCheck == true) {
//         $.ajax({
//             type: 'POST',
//             url: '/mypage/update',
//             data: JSON.stringify(data),
//             contentType: "application/json; charset=utf-8", // body 데이터가 어떤 타입인지(MIME)
//             // dataType: "json" // 요청을 서버로 해서 응답이 왔을 때 기본적으로 모든 것이 문자열(String)=>javascript 오브젝트로 변경
//             // dataType을 생략하면 요청한 자료에 맞게 자동으로 형식이 설정되기 때문에 생략해도 된다.
//         }).done(function (result) {
//             Swal.fire({
//                 icon: 'success',
//                 title: '수정 완료',
//                 text: '내 정보 수정이 완료되었습니다',
//             }).then(function() {
//                 location.href = "../mypage/info?id=" + data.id;
//             })
//         }).fail(function (error) {
//             alert(JSON.stringify(error));
//         });
//     }
// });
