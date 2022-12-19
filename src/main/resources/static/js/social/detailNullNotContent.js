var a = $(".detailInfoNull").val();

console.log(a);

if (a == '') {
    swal({
        title: '정보가 부족하여 활동할 수 없습니다.',
        icon: "error",
        className: 'swal-wide',
    }).then(function () {
        location = 'socialMember/kakaoLogin';
        return false;
    })
}

