
$(".selectRole").change(function () {
	// console.log($('.selectRole option:selected').val());
	$(this).children('.role').each(function (index, r) {

		if ($(r).prop('selected')) {
			var num = $(r).attr('value');

			if (num == "Manager") {
				swal({
					title: "매니저",
					text: '등급이 변경되었습니다.',
					icon: "success",
				}).then(function () {
					location.reload();
				});
				$(this).val('ROLE_MANGER');

				return false;
			}
			else if (num == 'User') {
				swal({
					title: "유저",
					text: '등급이 변경되었습니다.',
					icon: "success",
				}).then(function () {
					location.reload();
				});
				$(this).val('ROLE_USER');

				return false;
			}
			else {
				swal({
					title: "로그인 차단",
					text: '차단되었습니다.',
					icon: "error",
				}).then(function () {
					location.reload();
				});
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
		data: {
			roleName: $(this).val(),
		},
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
