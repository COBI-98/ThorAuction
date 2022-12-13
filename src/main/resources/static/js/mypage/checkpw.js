// checkpw.js

$('#checkpw_btn').click(function() {
	const id = $('#id').val();
	const checkPassword = $('#pw').val();
	
	if(!checkPassword || checkPassword.trim() === ""){
		Swal.fire({
			icon: 'warning',
			title: '잠시만요!',
			text: '비밀번호를 입력하세요',
		})
	} else{
		$.ajax({
			type: 'POST',
			url: '/mypage/checkpw',
			data: {'checkPassword': checkPassword},
			datatype: "text"
		}).done(function(result){
			console.log(result);
			if(result){
				console.log("비밀번호 일치");
				location.href="/mypage/update?id=" + id;
			} else if(!result){
				console.log("비밀번호 틀림");
				// 비밀번호가 일치하지 않으면
				Swal.fire({
					icon: 'error',
					title: '잠시만요!',
					text: '비밀번호가 일치하지 않습니다',
				}).then(function() {
				window.location.reload();
			})
			}
		}).fail(function(error){
			alert(JSON.stringify(error));
		})
	}
});