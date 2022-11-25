let count = 0;

$(".selectRole").change(function () {
	console.log(count); // 바꿀때마다 1씩 증가
	count = count - 12;
	$('.selectRole option:selected').each(function (item) {
		var num = $(this).attr('value');
		// console.log($(".selectRole").length);
		count++;

		if (num == "Manager") {
			console.log("매니저재ㅓ재ㅓ");
			swal("매니저", "등급이 변경되었습니다.", "success");
			console.log(num);

			return false;
		}
		else if (num == 'Ban') {
			console.log("베네에에에ㅔ");
			swal("로그인 차단", "차단되었습니다.", "error");
			console.log(num);

			return false;
		}
		else if (num == 'User') {
			console.log("유저ㅓ저ㅓㅓ");
			swal("유저", "등급이 변경되었습니다.", "success");
			console.log(num);

			return false;
		}
		else {
			alert("에러");
		}
	});
	console.log("--------------------------------");
})

