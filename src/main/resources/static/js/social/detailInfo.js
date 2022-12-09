var info = $(".detail").val();

console.log("detail 값: " + info);

$.ajax({
    url: './',
    type: 'GET',
    traditional: true,
    data: {
        check: info
    },
    success: function () {
        console.log("디테일 완료");
    },
    error: function (status, error) {
        console.log(status);
        console.log(error);
    }
})

