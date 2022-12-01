// checkpw.js

$("#pw").on({
	blur: function () {
		$("#checkpw_btn").removeAttr("disabled");
	},

	click: function () {
		var id = $('input[name=id]').val();
		var pw = $('input[name=pw]').val();

		$.ajax({
			type: 'POST',
			url: '/mypage/checkpw',
			data: { id, pw },
			success: function (result) {
				if (result.pw == pw) {
					location.href = "/mypage/"
				}
				else {
					alert("비밀번호가 일치하지않습니다");
				}
			}
			,
			error: function (result) {
			},
			complete: function () {
			}

		})
	}
});