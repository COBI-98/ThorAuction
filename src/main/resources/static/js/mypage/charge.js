// charge.js

$("#pay").click(function () {
    // 사용자가 원하는 금액을 선택
    var money = $('input[name="cp_item"]:checked').val();
    console.log(money);

    var IMP = window.IMP; // 생략 가능
    IMP.init("imp55151468"); // 예: imp00000000

    IMP.request_pay({
        pg: 'kakaopay',
        pay_method: 'card', //생략 가능
        merchant_uid: 'merchant_' + new Date().getTime(), // 상점에서 관리하는 주문 번호
        name: '토르의 실시간 대장간',
        amount: money,
        buyer_email: $("#email").val(),
        buyer_name: $("#name").val(),
        buyer_tel: $("#phone").val(),
        buyer_addr: $("#addr").val() + $("#addr2").val(),
        buyer_postcode: $("#post").val()
    }, function (rsp) { // callback 로직
        if (rsp.success) {
            console.log(rsp.imp_uid);
            //[1] 서버단에서 결제정보 조회를 위해 jQuery ajax로 imp_uid 전달하기
            jQuery.ajax({
                //db저장
                // type: "GET",
                // url: "/mypage/charge/point",
                // data: {
                //     rsp,
                //     "amount": money
                // },

                type: 'POST',
                url: "/mypage/charge/point", //cross-domain error가 발생하지 않도록 주의해주세요
                dataType: 'json',
                data: {
                    //기타 필요한 데이터가 있으면 추가 전달
                    payUid : rsp.imp_uid,
                    payTotal : rsp.paid_amount,
                    payResult : rsp.status,
                    payDate : rsp.paid_at

                }
            }).done(function (data) {
                //[2] 서버에서 REST API로 결제정보확인 및 서비스루틴이 정상적인 경우
                if (everythings_fine) {
                    var msg = '결제가 완료되었습니다.';
                    msg += '\n고유ID : ' + rsp.imp_uid;
                    msg += '\n상점 거래ID : ' + rsp.merchant_uid;
                    msg += '\결제 금액 : ' + rsp.paid_amount;
                    msg += '카드 승인번호 : ' + rsp.apply_num;

                    window.location.reload()

                    alert(msg);
                } else {
                    //[3] 아직 제대로 결제가 되지 않았습니다.
                    //[4] 결제된 금액이 요청한 금액과 달라 결제를 자동취소처리하였습니다.
                }
            });
        } else {
            var msg = '결제에 실패하였습니다.';
            msg += '에러내용 : ' + rsp.error_msg;

            alert(msg);
        }
        alert(msg);
        location.href = "/mypage/"
    });
});

// 직접입력 란
$("#input_money").on({
    click: function () {
        $('#value_money').prop("checked", true);
    },

    keyup: function () {
        var rgx1 = /\D/g;
        var rgx2 = /(\d+)(\d{3})/;
        var num = this.value.replace(rgx1, "");

        while (rgx2.test(num)) num = num.replace(rgx2, '$1' + ',' + '$2');
        this.value = num;

        $("#value_money").attr('value', $("#input_money").val());

    }
});