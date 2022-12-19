// likeProduct.js

const id = $("#id");

$(".deleteLike_btn").click(function() {
    console.log(id.val());
    console.log($(this).val());
    Swal.fire({
        title: '관심상품',
        text: "관심상품을 삭제하시겠습니까?",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: '삭제하기',
        cancelButtonText: '취소'
    }).then(function (result) {
        if (result.isConfirmed) { // confirm button 클릭 시
            $.ajax({
                type: 'POST',
                url: '/mypage/deleteLikeProduct',
                data: {
                    id: $("#id").val(),
                    likeNum: $("#likeNum").val()
                },
                datatype: "text"
            }).done(function(result) {
                Swal.fire({
                    title: '삭제 완료',
                    html: '삭제가 완료되었습니다',
                    icon: 'success',
                    confirmButtonColor: '#3085d6',
                    confirmButtonText: '확인'
                }).then(function(result) {
                    $("#deleteLike_form").submit();
                    location.reload();
                })
            })
        }
    })
});