const selectRole = document.querySelectorAll("#selectRole");
let option = document.querySelectorAll("option");
let select = document.querySelectorAll("select");

// console.log(select.length);
// console.log(select);
// console.log(option[0]);
// console.log(option);

// console.log($("option")[0]);
// console.log("333: ",$("option")[3]);


// 3개씩 짤라서 option[0,1,2]부터 시작해서 selected, selected 넣기
// for (let i = 0; i < 3; i++){
// 	console.log(option[i]);
// }

$(".selectRole").change(function () {
	$(".selectRole").val() == "";

	let b = $(".selectRole").val();

	for (let j = 0; j < 3; j++){
		console.log($("option")[j]); // option 0,1,2
		// $("option")[j]
	}


	// if (b == 'Manager') {
	// 	// swal("매니저", "등급이 변경되었습니다.", "success");
	// 	console.log("매니저재ㅓ재ㅓ");
	// }
	// else if (b == 'User') {
	// 	// swal("유저", "등급이 변경되었습니다.", "success");
	// 	console.log("유ㅜ지ㅓ저저ㅓ");
	// }
	// else if (b == 'Ban') {
	// 	// swal("로그인 차단", "차단되었습니다.", "error");
	// 	console.log("베네네네에에ㅔㅇ");
	// }
	
	// console.log(b);
	// console.log("aaaaa: ", $("#selectRole").val().length);
})
