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
		alert("필수 항목을 모두 입력해 주세요.");
	} else {
		$("#loginForm").submit();
	}
});