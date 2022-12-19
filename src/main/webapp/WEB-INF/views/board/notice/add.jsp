<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri ="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- jquery -->
<c:import url="../../template/boot.jsp"></c:import>
<c:import url="../../template/summer.jsp"></c:import>
<!-- 서머노트를 위해 추가해야할 부분 -->
<script src="/js/summernote/summernote-ko-KR.js"></script>
<script src="/js/summernote/summernote-lite.js"></script>
<link href="/css/reset.css" rel="stylesheet">
<link rel="stylesheet" href="/css/board.css">
<link rel="stylesheet" href="/css/summernote/summernote-lite.css">
</head>
<body>
	<c:import url="../../template/header.jsp"></c:import>
	<section class="container-fluid col-lg-8 mt-5">
		<div class="row mt-5">
	 <form class="align-center" action="./add" method="post" enctype="multipart/form-data">
        <div class="form-check">
            <input type="checkbox" id="radioDate" name="importCheck">
            <label for="">
                중요 공지사항
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
        <div>
        글 내용<br>
        <textarea id="summernote" name="contents" th: rows="30" cols="100"></textarea>
        </div>    
         <!-- <div>
        <input type="file" name="files" class="files">
    	
        </div>
        
        <div>
        <input type="file" name="files" class="files"> 
    	
        </div>  -->
        
        <div  class="board-filetitle">💾첨부 파일  
         <div class="mb-3" id="fileAddResult">
				
			</div>

        </div>
			<div class="mb-3">
				<button type="button" id="fileAdd" class="btn btn-success">첨부파일 추가</button>
			</div>
            
         
        <input type="submit" name="add" value="공지사항 추가"  class ="btn btn-info"style="margin-left: 300px;">
        </form>
</div>
	
</section>
<c:import url="../../template/footer.jsp"></c:import>
<script src="/js/fileManager.js">

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


    $('#summernote').summernote(setting);
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