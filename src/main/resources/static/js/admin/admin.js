
$(".selectRole").change(function () {
	// console.log($('.selectRole option:selected').val());
	$(this).children('.role').each(function (index, r) {

		if ($(r).prop('selected')) {
			var num = $(r).attr('value');
			// DB에서 등급 하나만 주고 버튼 바꿀때 계속 update되게 바꾸기
			// 카카오 소셜 로그인에서 로그아웃하고 다시 로그인할때
			//primary key 중복 되는거 질문하기
			if (num == "Manager") {
				swal("매니저", "등급이 변경되었습니다.", "success");
				// console.log($(this).val());
				$(this).val('ROLE_MANGER');

				return false;
			}
			else if (num == 'User') {
				swal("유저", "등급이 변경되었습니다.", "success");
				// console.log($(this).val());
				$(this).val('ROLE_USER');

				return false;
			}
			else {
				swal("로그인 차단", "차단되었습니다.", "error");
				// console.log($(this).val());
				$(this).val('ROLE_BAN');

				return false;
			}
		}
	});
	console.log($(this).val());
	console.log("--------------------------------");
	// 선택한 option의 val를 controller로 보내서 등급 update 하기
	$.ajax({
		url: './adminpage',
		type: 'POST',
		data: { roleName: $(this).val() },
		success: function () {
			console.log("통신완료");
		},
		error: function (status, error) {
			alert("안돼");
			console.log(status);
			console.log(error);
		}
	});
});
