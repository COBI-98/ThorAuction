const selectRole = document.getElementById("selectRole");

$("#selectRole").css("background", "#FFCCCC");
$("#selectRole").css("color", "#000000");

selectRole.addEventListener("change", function () {
  if ($("#opBan:selected").text() == "Ban") { // > 여기에 쓴 값 </option>
    $("#selectRole").css("background", "#000000");
    $("#selectRole").css("color", "#FF0000");
  }
  else if ($("#opMem:selected").text() == "Member") {
    $("#selectRole").css("background", "#FFCCCC");
    $("#selectRole").css("color", "#000000");
  }
  else if ($("#opMana:selected").text() == "Manager") {
    $("#selectRole").css("background", "#FFCC00");
    $("#selectRole").css("color", "#000000");
  }
})