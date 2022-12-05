// signup.js

let results = [false, false, false];

// 아이디 정규식
let userIdCheck = RegExp(/[^a-z0-9]$/);
// 비밀번호 정규식
let userPwCheck = RegExp(/[^a-zA-Z0-9]$/);

// 아이디 검증
$("#id").on({
	blur: function() {
	if ($("#id").val() == "") {
		$(".idErrorMessage").text("필수 항목입니다");
		$(".idErrorMessage").attr("style", "color:#f00");

		$("#signup_next_btn").attr("disabled", "disabled");
	} else { }
	},

	change: function() {
		$(".idErrorMessage").text("중복확인을 해주세요");
		$(".idErrorMessage").attr("style", "color:#f00");		

		results[0] = false;
	},

	// 아이디에 대문자가 있을 시 소문자로 변환
	bind: function() {
		$(this).val($(this).val().toLowerCase());
	}
})

// 아이디 중복확인
$(".idCheck").click(function () {
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
				$(".idErrorMessage").text("사용 가능한 아이디입니다");
				$(".idErrorMessage").attr("style", "color : #00f");
				$("#signup_next_btn").removeAttr("disabled");

				results[0] = true;
			}

			// 아이디 3자 이상 + 아이디 정규식
			// 아이디의 길이가 3자미만이고, 특수문자가 있을 경우
			if (userIdCheck.test($("#id").val()) || $("#id").val().length < 3) {
				$(".idErrorMessage").text("3~15자의 영문 소문자, 숫자만 사용해 주세요");
				$(".idErrorMessage").attr("style", "color:#f00");

				$("#signup_next_btn").attr("disabled", "disabled");
			} else { }

		}
	});

});


// 비밀번호 검증
$("#pw").on({
	blur: function () {
		if ($("#pw").val() == "") {
			$(".pwErrorMessage").text("필수 항목입니다");
			$(".pwErrorMessage").attr("style", "color:#f00");
		}
	},

	change: function () {
		// 비밀번호 변경 시 비밀번호 재확인 창 초기화
		$("#pwEquals").val("");
		results[1] = false;
		$(".pwEqualsErrorMessage").text("비밀번호가 일치하지 않습니다");
		$(".pwEqualsErrorMessage").attr("style", "color:#f00");

		if ($("#pw").val().length < 8 || $("#pw").val().length > 16) {
			$(".pwErrorMessage").text("8~16자 영문 대ㆍ소문자, 숫자만 사용해 주세요");
			$(".pwErrorMessage").attr("style", "color:#f00");

			results[1] = false;
		} else {
			$(".pwErrorMessage").text("");

			results[1] = true; // true
		}
		if (userPwCheck.test($('#pw').val())) {
			$(".pwErrorMessage").text("8~16자 영문 대ㆍ소문자, 숫자만 사용해 주세요");
			$(".pwErrorMessage").attr("style", "color:#f00");

			results[1] = false;
		}
	}
});

// 비밀번호 재확인 검증
$("#pwEquals").blur(function () {
	if ($("#pw").val() != $("#pwEquals").val()) {
		$(".pwEqualsErrorMessage").text("비밀번호가 일치하지 않습니다");
		$(".pwEqualsErrorMessage").attr("style", "color:#f00");

		results[2] = false;
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
		// location.href="join"
		$("#signupForm").submit();
	}
});

// 취소 버튼 클릭 시
$("#signup_cancel_btn").click(function () {
	location.href = "../";
});
