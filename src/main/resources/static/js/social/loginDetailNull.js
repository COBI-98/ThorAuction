// join.js

let resultss = [true, false, false, true, false];


// 생년월일 검증
$("#birth").blur(function () {
	if ($("#birth").val() == "") {
		$(".birthErrorMessage").text("필수 항목입니다");
		$(".birthErrorMessage").attr("style", "color:#f00");

	} else if ($("#birth").val().length < 6) {
		$(".birthErrorMessage").text("생년월일 6자를 정확하게 입력해 주세요 ex)000101");
	} else {
		$(".birthErrorMessage").text("");

		resultss[1] = true;
	}
	console.log(resultss[1]);
});

// 핸드폰 검증
// 전화번호 정규식
const autoHyphen = (target) => {
	target.value = target.value
		.replace(/[^0-9]/g, '')
		.replace(/^(\d{2,3})(\d{3,4})(\d{4})$/, `$1-$2-$3`);
}

$("#phone").blur(function () {
	if ($("#phone").val() == "") {
		$(".phoneErrorMessage").text("필수 항목입니다");
		$(".phoneErrorMessage").attr("style", "color:#f00");
	} else if ($("#phone").val().length < 11) {
		$(".phoneErrorMessage").text("전화번호를 정확히 입력해 주세요");
	}
	else {
		$(".phoneErrorMessage").text("");

		resultss[2] = true;
	}
	console.log(resultss[2]);
});


// 이메일 검증
// 이메일 정규식
// function isEmail(asValue) {
// 	var regExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;

// 	return regExp.test(asValue);
// }

// $("#email").blur(function () {
// 	if ($("#email").val() == "") {
// 		$(".emailErrorMessage").text("필수 항목입니다");
// 		$(".emailErrorMessage").attr("style", "color:#f00");

// 	} else if (!isEmail($("#email").val())) {
// 		$(".emailErrorMessage").text("이메일 주소를 다시 확인해 주세요");
// 	}
// 	else {
// 		$(".emailErrorMessage").text("");

// 		resultss[3] = true;
// 	}
// 	console.log(resultss[3]);
// });

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
			resultss[4] = true;

			// 상세주소 입력란 disabled 속성 변경 및 커서를 상세주소 필드로 이동한다.
			$("#addr2").attr("readonly", false);
			$("#addr2").focus();
			console.log(resultss[4]);
		}
	}).open();
}

// 필수항목 체크
$("#join_submit_btn").click(function () {
	if (resultss.includes(false)) {
		Swal.fire({
			icon: 'warning',
			title: '잠시만요!',
			text: '필수 항목을 모두 입력해 주세요.',
		  });
	} else {
		$("#joinForm").submit();
	}
});

// 취소 버튼을 눌렀을 때
$("#join_cancel_btn").click(function () {
	location.href = "/member/login"
});

