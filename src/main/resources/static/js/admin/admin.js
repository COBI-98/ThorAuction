
$(".selectRole").change(function () {
	// console.log($('.selectRole option:selected').val());
	$(this).children('.role').each(function (index, r) {

		if ($(r).prop('selected')) {
			var num = $(r).attr('value');
			// console.log("seleted");

			if (num == "Manager") {
				console.log("매니저재ㅓ재ㅓ");
				swal("매니저", "등급이 변경되었습니다.", "success");

				return false;
			}
			else if (num == 'User') {
				console.log("유저ㅓ저ㅓㅓ");
				swal("유저", "등급이 변경되었습니다.", "success");

				return false;
			}
			else {
				console.log("베네에에에ㅔ");
				swal("로그인 차단", "차단되었습니다.", "error");

				return false;
			}
		}
	});
	// console.log($(this).children('.role').val());
	console.log("--------------------------------");
});

