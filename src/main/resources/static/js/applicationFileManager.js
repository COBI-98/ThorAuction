console.log("FileManger");
// const files = document.querySelectorAll(".files");
//파일의 갯수를 지정하는 변수
let count=0;



let productNum = $("#productNum").val();

$("#fileAdd").click(function(){
    if(flag){
        let size = $("#fileAddResult").attr("data-file-size");
        if(size==undefined){
            size=0;
        }
        count=size;
        flag=false;    
    }
    
    console.log(productNum);
    if($(".files").length<8){
         let r = '<div style="width:100%;">';
         r = r+'<div class="file_form mt-2" style="width:80%;">';
        /* r = r+'<label for="contents" class="form-label">Files</label>'; label 여부 체크 */
        r = r+'<input type="file" name="files" class="files form-control">';
        r = r+'<span class="text">선택된 파일 없음</span> ';
        r = r+'<button type="button" class="del btn btn-danger" style="position: absolute; right:-50px; top:0px; ">X</button>'
        r = r+'</div>';
        r = r+'</div>';
        $("#fileAddResult").append(r);
    }else {
        swal("최대 8개만 가능","","warning")
    }

    for(let i=0; i<$(".files").length; i++){
        console.log("i확인 : "+i);
        $($(".files")[i]).on('change',function(){
            console.log("rr");
            var fileName = $($(".files")[i]).val();
            let result = fileName.split('\\'); // fileName c\\filepath\\fileName.jpg
            $($(".text")[i]).html(result[2]);
            // $.ajax({
            //     type:"POST",
            //     url :"./fileDelete",
            //     data:{
            //         "rowNum":i,
            //         "noticeNum":noticeNum
            //     },
            //     success:function(result){
            //         console.log("Result : ",result)
            //         console.log("After Result This", $(this));
                    
            //     },
            //     error:function(){
            //         console.log("Error 발생");
            //     }
    
            // });
        });
    
    }
    
    // console.log($(".files").length);     
});

for(let i=0; i<$(".files").length; i++){
    console.log("i확인 : "+i);

    $($(".files")[i]).on('change',function(){
        console.log("rr");
        var fileName = $($(".files")[i]).val();
        let result = fileName.split('\\'); // fileName c\\filepath\\fileName.jpg
        $($(".text")[i]).html(result[2]);
        console.log();
                    $.ajax({
                        type:"POST",
                        url :"./fileUpdateNumber",
                        data:{
                            rowNum:i,
                            productNum:productNum
                        },
                        success:function(result){
                            console.log("Result : ",result)
                            console.log("After Result This", $(this));
                            // if(result == 1 ){
                            //     swal("첨부파일이 삭제되었습니다.").then(function(){
                            //         location.replace("./update?noticeNum="+noticeNum);
                            //     })
                            // }
                            let r = '<input type="hidden" name="fileUpdateNumber" id="fileUpdateNumber" value="'+result
                            r = r + '">';
                            $("#fileAddResult").append(r);
                        },
                        error:function(){
                            console.log("Error 발생");
                        }

            });
        });
        
    
    $($(".del")[i]).click(function(){
        $.ajax({
            type:"POST",
            url :"./fileDelete",
            data:{
                "rowNum":i,
                "productNum":productNum
            },
            success:function(result){
                console.log("Result : ",result)
                console.log("After Result This", $(this));
                if(result == 1 ){
                    swal("첨부파일이 삭제되었습니다.").then(function(){
                        location.replace("./update?noticeNum="+noticeNum);
                    })
                }
                
            },
            error:function(){
                console.log("Error 발생");
            }

        });
    })

}


$("#fileAddResult").on("click", ".del", function(){
    $(this).parent().remove();
    
});

let flag=true;
/// 글 수정시 첨부파일 삭제
$(".deleteFile").click(function(){
    //DB, HDD에 파일 삭제
    let check = confirm("삭제 됩니다.. 복구 불가");

    if(flag){
        let size = $("#fileAddResult").attr("data-file-size");
        
        
        count=size;
        flag=false;
    }

    if(check){
        //post
        // /qna/fileDelete
        //파라미터 fileNum
        let fileNum = $(this).attr("data-file-num");
        console.log("Before Result This", $(this));
        const btn = $(this);
        $.ajax({
            type:"POST",
            url :"fileDelete",
            data:{
                fileNum:fileNum
            },
            success:function(result){
                console.log("Result : ",result)
                console.log("After Result This", $(this));
                $(btn).parent().remove();
                count--;
            },
            error:function(){
                console.log("Error 발생");
            }

        });

    }

});