// signUp.js

let results = [false, false, false];

// 아이디 검증
$("#id").blur(function () {
	
	if ($("#id").val() == "") {
		$(".idErrorMessage").text("필수 항목입니다");
		$(".idErrorMessage").attr("style", "color:#98a0a7");

		$("#signUp_next_btn").attr("disabled", "disabled");
	} else { }

});

// 아이디 중복확인
$(".idCheck").click(function () {

	var query = {
		id: $("#id").val()
	};

	$.ajax({
		url: "/member/idCheck",
		type: "POST",
		data: query,
		success: function (data) {
			if (data == 1) {
				$(".idErrorMessage").text("중복되는 아이디가 있습니다");
				$(".idErrorMessage").attr("style", "color : #98a0a7");
				$("#signUp_next_btn").attr("disabled", "disabled");
			} else {
				$(".idErrorMessage").text("사용가능한 아이디입니다");
				$(".idErrorMessage").attr("style", "color : #00f");
				$("#signUp_next_btn").removeAttr("disabled");

				results[0] = true;
			}

			// 아이디의 길이가 3자미만이면
			if ($("#id").val().length < 3) {
				$(".idErrorMessage").text("3~15자의 영문 소문자, 숫자만 사용 가능합니다");
				$(".idErrorMessage").attr("style", "color:#98a0a7");

				$("#signUp_next_btn").attr("disabled", "disabled");
			} else { }
		}

	});

});


// 비밀번호 검증
$("#pw").on({
	blur: function () {
		console.log("fffffffffff");

		if ($("#pw").val() == "") {
			$(".pwErrorMessage").text("필수 항목입니다");
			$(".pwErrorMessage").attr("style", "color:#98a0a7");
		}
	},

	change: function () {
		if ($("#pw").val().length < 8) {
			$(".pwErrorMessage").text("8~16자 영문 대ㆍ소문자, 숫자를 사용하세요");
			$(".pwErrorMessage").attr("style", "color:#98a0a7");
		} else {
			$(".pwErrorMessage").text("");

			results[1] = true;
		}
	}
});

// 비밀번호 재확인 검증
$("#pwEquals").blur(function () {
	if ($("#pw").val() != $("#pwEquals").val()) {
		$(".pwEqualsErrorMessage").text("비밀번호가 일치하지 않습니다");
		$(".pwEqualsErrorMessage").attr("style", "color:#98a0a7");
	} else {
		$(".pwEqualsErrorMessage").text("");

		results[2] = true;
	}
});

// 필수항목 체크
$("#signUp_next_btn").click(function () {
	if (results.includes(false)) {
		alert("필수 항목을 모두 입력해 주세요.");
	} else {
		location.href = "/member/join";
	}
});

// 취소 버튼 클릭 시
$("#signUp_cancel_btn").click(function () {
	location.href = "../";
});
