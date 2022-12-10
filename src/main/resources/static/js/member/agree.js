// agree.js

// 전체 동의
$('#memberAgreeCheckbox2048').click(function() {
    let checkbox = $('#memberAgreeCheckbox2048').prop("checked");

    if(checkbox) {
        console.log(checkbox);

        $('#allAgree').addClass('member-agree-checkbox__state--checked');

        $('span[name="agree_check"]').addClass('member-agree-checkbox__state--checked');
        $('input[name="agree_check"]').prop("checked", true)

        $('.agree__btn').removeAttr("disabled");
    } else {
        console.log(checkbox);

        $('#allAgree').removeClass('member-agree-checkbox__state--checked');

        $('span[name="agree_check"]').removeClass('member-agree-checkbox__state--checked');
        $('input[name="agree_check"]').prop("checked", false)

        $('.agree__btn').attr("disabled", "disabled");
    }
});

// 전체 동의 후 필수약관 중 체크 해제하면 전체 동의 체크 해제
$('input[name="agree_check"]').click(function() {
    var total = $('input[name="agree_check"]').length; // 2개
    var checked = $('input[name="agree_check"]:checked').length;

    if(total != checked) {
        $("#memberAgreeCheckbox2048").prop("checked", false);
        $('#allAgree').removeClass('member-agree-checkbox__state--checked');

        $('.agree__btn').attr("disabled", "disabled");
    } else {
        $("#memberAgreeCheckbox2048").prop("checked", true);
        $('#allAgree').addClass('member-agree-checkbox__state--checked');

        $('.agree__btn').removeAttr("disabled");
    }
});

// 이용약관 동의
$('#memberAgreeCheckbox356').click(function() {
    let checkbox = $('#memberAgreeCheckbox356').prop("checked");

    if(checkbox) {
        console.log(checkbox);

        $('#agree1').addClass('member-agree-checkbox__state--checked');
    } else {
        console.log(checkbox);

        $('#agree1').removeClass('member-agree-checkbox__state--checked');
    }
 });

// 개인정보 수집 및 이용에 관한 동의
$('#memberAgreeCheckbox104').click(function() {
    let checkbox = $('#memberAgreeCheckbox104').prop("checked");

    if(checkbox) {
        console.log(checkbox);

        $('#agree2').addClass('member-agree-checkbox__state--checked');
    } else {
        console.log(checkbox);

        $('#agree2').removeClass('member-agree-checkbox__state--checked');
    }
 });

 // 선택 동의
$('#memberAgreeCheckbox4728').click(function() {
    let checkbox = $('#memberAgreeCheckbox4728').prop("checked");

    if(checkbox) {
        $('#agree3').addClass('member-agree-checkbox__state--checked');
    } else {
        $('#agree3').removeClass('member-agree-checkbox__state--checked');
    }
 });
 
  $('.agree__btn').click(function() {
    location.href="./join"
 });