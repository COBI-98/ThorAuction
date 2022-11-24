const selectRole = document.querySelectorAll("#selectRole");
let option = document.querySelectorAll("option");
let select = document.querySelectorAll("select");

// console.log(select.length);
// console.log(select);
// console.log(option.length);
// console.log(option);

let count = 0;
for (count = 0; count = option.length; count = count + 3) {
  console.log(count);
}

$("#selectRole").change(function () {
  $("#selectRole").val() == "";

  let b = $("#selectRole").val();
  if (b == 'Manager') {
    // swal("매니저", "등급이 변경되었습니다.", "success");
  }
  else if (b == 'User') {
    // swal("유저", "등급이 변경되었습니다.", "success");
  }
  else if (b == 'Ban') {
    // swal("로그인 차단", "차단되었습니다.", "error");
  }
  console.log(b);
})

