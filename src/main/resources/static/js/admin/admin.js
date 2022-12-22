$(".selectRole").each(function (index, r) {
	// console.log(r);
	// console.log($(this).children('.role:selected').val());

	if ($(this).children('.role:selected').val() == 'Manager') {
		$(this).closest('tr').css("background-color", "#ffcc80");
		$(this).closest('tr').find(".selectRole").css("background-color", "#ffcc80");
	}
	else if ($(this).children('.role:selected').val() == 'User') {
		$(this).closest('tr').css("background-color", "#fea532");
		$(this).closest('tr').find(".selectRole").css("background-color", "#fea532");
	}
	else {
		$(this).closest('tr').css("background-color", "#f57c00");
		$(this).closest('tr').find(".selectRole").css("background-color", "#f57c00");
	}
});

$(".selectRole").change(function () {

	var thisRow = $(this).closest('tr'); // table tr의 정보 가져오기
	var MID = thisRow.find('.mId').text(); // tr에서 class가 mId인것의 text
	var kakaoID = thisRow.find('.kakaoID').text();
	var kakaoEmail = thisRow.find('.kakaoEmail').text();
	var kakaoName = thisRow.find('.kakaoName').text();
	// console.log(MID); // selectbox와 같은 열에 있는 ID
	// console.log(kakaoEmail);
	// console.log(kakaoName);

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
	// member의 id를 전부 가져오기 - list 전부


	$.ajax({
		url: './adminpage',
		type: 'POST',
		traditional: true,
		data: {
			roleName: $(this).val(), // 일반 회원 등급 보내기
			ID: MID, // 일반/카카오 회원 아이디 보내기
			kakaoEmail: kakaoEmail,
			kakaoName: kakaoName,
			kakaoID: kakaoID
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

