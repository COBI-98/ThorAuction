
$(".selectRole").change(function () {
	// console.log($('.selectRole option:selected').val());
	$(this).children('.role').each(function (index, r) {

		if ($(r).prop('selected')) {
			var num = $(r).attr('value');
			// DB에서 등급 하나만 주고 버튼 바꿀때 계속 update되게 바꾸기
			// 카카오 소셜 로그인에서 로그아웃하고 다시 로그인할때
			//primary key 중복 되는거 질문하기
			if (num == "Manager") {
				console.log("매니저재ㅓ재ㅓ");
				swal("매니저", "등급이 변경되었습니다.", "success");
				console.log($(this).val());

				return false;
			}
			else if (num == 'User') {
				console.log("유저ㅓ저ㅓㅓ");
				swal("유저", "등급이 변경되었습니다.", "success");
				console.log($(this).val());

				return false;
			}
			else {
				console.log("베네에에에ㅔ");
				swal("로그인 차단", "차단되었습니다.", "error");
				console.log($(this).val());

				return false;
			}
		}
	});
	// console.log($(this).children('.role').val());
	console.log("--------------------------------");
});

