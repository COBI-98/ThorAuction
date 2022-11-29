// charge.js

// 아임포트 paid_at가 timestamp이기 때문에 date 타입으로 변경
// 타임스탬프 값을 년월일로 변환
function Unix_timestamp(t){
    var date = new Date(t*1000);
    var year = date.getFullYear();
    var month = "0" + (date.getMonth()+1);
    var day = "0" + date.getDate();
    var hour = "0" + date.getHours();
    var minute = "0" + date.getMinutes();
    var second = "0" + date.getSeconds();
    return year + "-" + month.substr(-2) + "-" + day.substr(-2) + " " + hour.substr(-2) + ":" + minute.substr(-2) + ":" + second.substr(-2);
}

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
            alert("결제 성공!");
            //location.href = "/mypage/"

            //[1] 서버단에서 결제정보 조회를 위해 jQuery ajax로 imp_uid 전달하기
            jQuery.ajax({
                type: 'POST',
                url: "/mypage/charge/point", //cross-domain error가 발생하지 않도록 주의해주세요
               // headers: { "Content-Type": "application/json" },
                data: {
                    payUid : rsp.imp_uid,
                    payTotal : rsp.paid_amount,
                    payResult : rsp.status,
                    payDate : Unix_timestamp(rsp.paid_at) // 선언한 함수 안에 아임포트 파라미터 넣어주기
                    //기타 필요한 데이터가 있으면 추가 전달
                }
            }).done(function (data) { // 응답 처리
                //[2] 서버에서 REST API로 결제정보확인 및 서비스루틴이 정상적인 경우
                switch (data.status) {
                    case "vbankIssued":
                        // 가상계좌 발급 시 로직
                        break;
                    case "success":
                        // 결제 성공 시 로직
                        break;
                }
            });
        } else {
            alert("결제에 실패하였습니다. 에러 내용 : " + rsp.error_msg);
        }
    });

    // 가맹점 서버 코드

    app.use(bodyParser.json());

    // "/payments/complete"에 대한 POST 요청을 처리
    app.post("/payments/complete", async (req, res) => {
        try {
            const { imp_uid, merchant_uid } = req.body; // req의 body에서 imp_uid, merchant_uid 추출

            // 액세스 토큰(access token) 발급 받기
            const getToken = await axios({
                url: "https://api.iamport.kr/users/getToken",
                method: "post", // POST method
                headers: { "Content-Type": "application/json" }, // "Content-Type": "application/json"
                data: {
                    imp_key: "5173404823210033", // REST API키
                    imp_secret: "vUSuCPZyeCNbWp2KqRiVpKWpnCd8uk3VbU3myyIoFpVZ4cjmBecCD9fa60CL03U68HvjpXZKME4oB8bz" // REST API Secret
                }
            });
            const { access_token } = getToken.data.response; // 인증 토큰

            // imp_uid로 아임포트 서버에서 결제 정보 조회
            const getPaymentData = await axios({
                url: `https://api.iamport.kr/payments/${imp_uid}`, // imp_uid 전달
                method: "get", // GET method
                headers: { "Authorization": access_token } // 인증 토큰 Authorization header에 추가
            });
            const paymentData = getPaymentData.data.response; // 조회한 결제 정보

            // DB에서 결제되어야 하는 금액 조회
            const order = await Orders.findById(paymentData.merchant_uid);
            const amountToBePaid = order.amount; // 결제 되어야 하는 금액

            // 결제 검증하기
            const { amount, status } = paymentData;
            if (amount === amountToBePaid) { // 결제 금액 일치. 결제 된 금액 === 결제 되어야 하는 금액
                await Orders.findByIdAndUpdate(merchant_uid, { $set: paymentData }); // DB에 결제 정보 저장

                switch (status) {
                    case "ready": // 가상계좌 발급
                        // DB에 가상계좌 발급 정보 저장
                        const { vbank_num, vbank_date, vbank_name } = paymentData;
                        await Users.findByIdAndUpdate("/* 고객 id */", { $set: { vbank_num, vbank_date, vbank_name } });
                        // 가상계좌 발급 안내 문자메시지 발송
                        SMS.send({ text: `가상계좌 발급이 성공되었습니다. 계좌 정보 ${vbank_num} \${vbank_date} \${vbank_name}` });
                        res.send({ status: "vbankIssued", message: "가상계좌 발급 성공" });
                        break;
                    case "paid": // 결제 완료
                        res.send({ status: "success", message: "일반 결제 성공" });
                        break;
                }
            } else { // 결제 금액 불일치. 위/변조 된 결제
                throw { status: "forgery", message: "위조된 결제시도" };
            }
        } catch (e) {
            res.status(400).send(e);
        }
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