let p = true;
var check = $("input[type='checkbox']");
check.click(function () {
    if (p == true) { // 차단 했을 때
        $("p").toggle();
        // alert("차단");
        p = false;
        return;
    }
    else if (p == false) { // 차단 해제 했을 때
        $("p").toggle();
        // alert("차단 해제");
        p = true;
    }
});