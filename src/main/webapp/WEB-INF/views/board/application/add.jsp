<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri ="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- jquery -->
<script type="text/javascript" src="//code.jquery.com/jquery-3.6.0.min.js"></script>
<c:import url="../../template//boot.jsp"></c:import>
<c:import url="../../template//summer.jsp"></c:import>
<link rel="stylesheet" href="/css/application.css">
<script src="/js/summernote/summernote-ko-KR.js"></script>
<script src="/js/summernote/summernote-lite.js"></script>
<link rel="stylesheet" href="/css/board.css">
<link rel="stylesheet" href="/css/summernote/summernote-lite.css">
</head>
<body>
	
	<section class="container-fluid col-lg-8 mt-5">
		<h1>경매신청게시판 신청하기</h1>
		<div class="row">
	 <form class="align-center" action="./add" method="post" enctype="multipart/form-data">
        <div>
	        <label class="test_obj">
			    <input type="radio" name="auctionSort" value="1">
			    <span>실시간경매 상품신청</span>
			</label>
			 
			<label class="test_obj">
			    <input type="radio" name="auctionSort" value="0">
			    <span>온라인경매 상품신청</span>
			</label>
		 </div>

     
        <div>
        글 제목<br>
        <input type="text" name="title" placeholder="글 제목을 입력하세요"><br>
        </div>
         <div>
        작성자<br>
        <input type="text" name="writer"  ><br>
        </div>
        <!-- <input type="submit" name="add" value="신청하기 테스트"  class ="btn btn-info"> -->
       <div class="apllicationForm">
            신청하는 상품 정보 
            <input type="hidden" name="applicationNum" value="">
            <div>
                상품 이름<br>
                <input type="tex" name="productName" ><br>
            </div>
            <div>
                최저 경매가격<br>
                <input type="text" name="productPrice"  ><br>
            </div>
            <div>
                상세정보<br>
                <textarea id="detailContents" name="productInformation" rows="30" cols="100"></textarea>
            </div>
            <div>
                경매기간<br>
                <input type="text" name="auctionPeriod" ><br>
            </div>
            <div  class="board-filetitle">💾상품이미지 첨부  
                <div class="mb-3" id="fileAddResult">
                       
                   </div>
       
               </div>
                   <div class="mb-3">
                       <button type="button" id="fileAdd" class="btn btn-success">파일 추가</button>
            </div>
        </div>
        <input type="submit" id="applicationSubmit" value="신청하기"  class ="btn btn-info">
    </form>

				
        
        
        <!-- </form> -->
</div>
	
</section>
<script src="/js/fileManager.js"></script>

<script type="text/javascript">
	$("#contents").summernote({
        height: 500,                 // 에디터 높이
        minHeight: null,             // 최소 높이
        maxHeight: null,             // 최대 높이
        focus: true,                  // 에디터 로딩후 포커스를 맞출지 여부
        lang: "ko-KR",               // 한글 설정
        placeholder: '최대 2048자까지 쓸 수 있습니다'   //placeholder 설정
        // callbacks: {	//이미지 첨부하는 부분
        //        onImageUpload : function(files) {
        //             uploadSummernoteImageFile(files[0],this);
        //         }
                
        // }
   });
</script>

<script type="text/javascript">
   
	$(document).ready(function() {

    var toolbar = [
        // 글꼴 설정
        ['fontname', ['fontname']],
        // 글자 크기 설정
        ['fontsize', ['fontsize']],
        // 굵기, 기울임꼴, 밑줄,취소 선, 서식지우기
        ['style', ['bold', 'italic', 'underline','strikethrough', 'clear']],
        // 글자색
        ['color', ['forecolor','color']],
        // 표만들기
        ['table', ['table']],
        // 글머리 기호, 번호매기기, 문단정렬
        ['para', ['ul', 'ol', 'paragraph']],
        // 줄간격
        ['height', ['height']],
        // 그림첨부, 링크만들기, 동영상첨부
        ['insert',['picture','link','video']],
        // 코드보기, 확대해서보기, 도움말
        ['view', ['codeview','fullscreen', 'help']]
      ];

    var setting = {
        height : 500,
        minHeight : null,
        maxHeight : null,
        focus : true,
        lang : 'ko-KR',
        toolbar : toolbar,
        placeholder : '상품<br>상품',
        callbacks : { //여기 부분이 이미지를 첨부하는 부분
        onImageUpload : function(files, editor,
        welEditable) {
        for (var i = files.length - 1; i >= 0; i--) {
        uploadSummernoteImageFile(files[i],
        this);
                }
            }
        }
    };


    $('#detailContents').summernote(setting);
    });
    

    function uploadSummernoteImageFile(file, el) {
			data = new FormData();
			data.append("file", file);
			$.ajax({
				data : data,
				type : "POST",
				url : "./uploadSummernoteImageFile",
				contentType : false,
				enctype : 'multipart/form-data',
				processData : false,
				success : function(data) {
                    console.log(data.url);
					$(el).summernote('editor.insertImage', data.url);
                    console.log("rr");
				}
			});
        }
 
</script>
</body>
</html>