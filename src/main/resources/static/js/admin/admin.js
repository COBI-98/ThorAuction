const selectRole = document.getElementById("selectRole");


$('select').each(function () {

  selectRole.addEventListener("change", function () {
    // if ($("#opBan:selected").val() == "Ban") { // > 여기에 쓴 값 </option>
    //   $("#selectRole").css("background", "#000000");
    //   $("#selectRole").css("color", "#FF0000");
    // }
    // else if ($("#opMem:selected").val() == "Member") {
    //   $("#selectRole").css("background", "#FFCCCC");
    //   $("#selectRole").css("color", "#000000");
    // }
    // else if ($("#opMana:selected").val() == "Manager") {
    //   $("#selectRole").css("background", "#FFCC00");
    //   $("#selectRole").css("color", "#000000");
    // }

    if ($("option:selected").val() == 'Manager') {
      swal("등급 변경", "매니저 등급으로 변경되었습니다.", "success");
    }
    else if ($("option:selected").val() == 'Member') {
      swal("등급 변경", "일반 유저 등급으로 변경되었습니다.", "success");
    }
    else if ($("option:selected").val() == 'Ban') {
      swal("등급 변경", "차단 되었습니다.", "error");
    }

  });

});
