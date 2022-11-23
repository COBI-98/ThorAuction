// signup.js

let results = [false, false, false];

// 아이디 검증
$("#id").blur(function () {

	if ($("#id").val() == "") {
		$(".idErrorMessage").text("필수 항목입니다");
		$(".idErrorMessage").attr("style", "color:#f00");

		$("#signup_next_btn").attr("disabled", "disabled");
	} else { }

});

// 아이디 중복확인
$(".idCheck").click(function () {

	// 아이디 정규식
	let userIdCheck = RegExp(/[^a-zA-Z0-9]$/);

	let query = {
		id: $("#id").val()
	};

	$.ajax({
		url: "/member/idCheck",
		type: "POST",
		data: query,
		success: function (data) {
			if (data == 1) {
				$(".idErrorMessage").text("중복되는 아이디가 있습니다");
				$(".idErrorMessage").attr("style", "color : #f00");

				$("#signup_next_btn").attr("disabled", "disabled");
			} else {
				$(".idErrorMessage").text("사용가능한 아이디입니다");
				$(".idErrorMessage").attr("style", "color : #00f");
				$("#signup_next_btn").removeAttr("disabled");

				results[0] = true;
			}

			// 아이디 3자 이상 + 아이디 정규식
			// 아이디의 길이가 3자미만이고, 특수문자가 있을 경우
			if (userIdCheck.test($('#id').val()) || $("#id").val().length < 3) {
				$(".idErrorMessage").text("3~15자의 영문 대ㆍ소문자, 숫자만 사용 가능합니다");
				$(".idErrorMessage").attr("style", "color:#f00");

				$("#signup_next_btn").attr("disabled", "disabled");
			} else { }

			// // 아이디 정규표현식 영문 대ㆍ소문자, 숫자만 가능
			// if(userIdCheck.test($('#id').val())){
			// 	$(".idErrorMessage").text("3~15자의 영문 대ㆍ소문자, 숫자만 사용 가능합니다");
			// 	$(".idErrorMessage").attr("style", "color:#f00");

			// 	$("#signup_next_btn").attr("disabled", "disabled");
			// } else{ }
	
		}

	});

});


// 비밀번호 검증

// 비밀번호 정규식
let userPwCheck = RegExp(/[^a-zA-Z0-9]$/);

$("#pw").on({

	blur: function () {
		if ($("#pw").val() == "") {
			$(".pwErrorMessage").text("필수 항목입니다");
			$(".pwErrorMessage").attr("style", "color:#f00");
		}
	},

	change: function () {
		if ($("#pw").val().length < 8 || $("#pw").val().length > 16) {
			$(".pwErrorMessage").text("8~16자 영문 대ㆍ소문자, 숫자를 사용하세요");
			$(".pwErrorMessage").attr("style", "color:#f00");
		} else {
			$(".pwErrorMessage").text("");

			results[1] = true;
		}
		if(userIdCheck.test($('#pw').val())) {
			$(".pwErrorMessage").text("8~16자 영문 대ㆍ소문자, 숫자를 사용하세요");
			$(".pwErrorMessage").attr("style", "color:#f00");
		}
	}
});

// 비밀번호 재확인 검증
$("#pwEquals").blur(function () {
	if ($("#pw").val() != $("#pwEquals").val()) {
		$(".pwEqualsErrorMessage").text("비밀번호가 일치하지 않습니다");
		$(".pwEqualsErrorMessage").attr("style", "color:#f00");
	} else {
		$(".pwEqualsErrorMessage").text("");

		results[2] = true;
	}
});

// 필수항목 체크
$("#signup_next_btn").click(function () {
	if (results.includes(false)) {
		// alert("필수 항목을 모두 입력해 주세요.");
		Swal.fire({
			icon: 'warning',
			title: '잠시만요!',
			text: '필수 항목을 모두 입력해 주세요.',
		  });
	} else {
		$("#signupForm").submit();
	}
});

// 취소 버튼 클릭 시
$("#signup_cancel_btn").click(function () {
	location.href = "../";
});
