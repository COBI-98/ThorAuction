// login.js

let results = [false, false]

$("#id").blur(function () {
    if (!($("#id").val()) == "") {
        results[0] = true;
    } else { }
});

$("#pw").blur(function(){
    if(!($("#pw").val()) == "") {
        results[1] = true;
    } 
});

$("#login_submit_btn").click(function () {
	if (results.includes(false)) {
        Swal.fire({
			icon: 'warning',
			title: '다시 시도해주세요',
			text: 'ID가 존재하지 않거나 비밀번호가 일치하지 않습니다.',
		  });
	} else {
		$("#loginForm").submit();
	}
});

$("#pw").keyup(function(key) {
    if (key.keyCode == 13) {
        if (results.includes(false)) {
            Swal.fire({
                icon: 'warning',
                title: '다시 시도해주세요',
                text: 'ID가 존재하지 않거나 비밀번호가 일치하지 않습니다.',
              });
        } else {
            $("#loginForm").submit();
        }
    }
});