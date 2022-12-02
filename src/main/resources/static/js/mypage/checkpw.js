// checkpw.js

$("#pw").on({
	blur: function () {
		$("#checkpw_btn").removeAttr("disabled");
	},

	click: function () {
		var id = $('input[name=id]').val();
		var pw = $('input[name=pw]').val();

		$.get("./checkpw?pw=" + $("#pw").val(), function (data) {
			console.log("Data : ", data);
	
			if (data == '0') {
				results[0] = false;
	
				Swal.fire({
					icon: 'warning',
					title: '실패',
					html: '비밀번호가 틀렸습니다.',
				})          
			} else {
				results[0] = true;
	
				Swal.fire({
				icon: 'success',
				title: '완료',
				html: '정보 수정이 완료되었습니다.<br> 로그인 페이지로 이동합니다',
			}).then(function () {
				$("#upadte_form").submit();
			})
			}
		});
	}
});