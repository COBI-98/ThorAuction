// /oauth2/authorization/kakao
let kaNickName = $("#nickName").val();

function kakao() {
	// alert("야");
	// return;
	$.ajax({
		url: "/admin/IdCheck",
		type: "POST",
		data: {
			kaNickName: kaNickName
		},

		success: function(rs) {
			// alert(rs);
			// location.history();
			// return;
			if (rs == 1) {
				swal({
					title: "회원가입 되어 있는 아이디입니다. ",
					text: "자동 로그인",
					icon: "success",
					buttons: '확인'
				}).then((value) => {
					if (value) {
						location.href = '/';
					}
				});
				return;
			}
			else {
				swal({
					title: "카카오 로그인",
					text: "You clicked the button!",
					icon: "info",
					buttons: '확인'
				}).then((value) => {
					if (value) {
						location.href = '/oauth2/authorization/kakao';
					}
				});
				// return false;
			}
		},
		error: function(status, error) {
			console.log("status: ", status);
			console.log("error: ", error);
		}
	})
}