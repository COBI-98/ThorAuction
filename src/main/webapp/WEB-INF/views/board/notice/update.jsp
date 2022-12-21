<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri ="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>세상의 모든 경매: BIDCOIN</title>
  <link href="/images/miniLogo_BidCoin.png" rel="shortcut icon" type="image/x-icon">
<!-- jquery -->
<c:import url="../../template/boot.jsp"></c:import>
<c:import url="../../template/summer.jsp"></c:import>
<!-- 서머노트를 위해 추가해야할 부분 -->
<script src="/js/summernote/summernote-ko-KR.js"></script>
<script src="/js/summernote/summernote-lite.js"></script>
<link rel="stylesheet" href="/css/board.css">
<link rel="stylesheet" href="/css/summernote/summernote-lite.css">
<link href="/css/reset.css" rel="stylesheet">
</head>
<body>
	<c:import url="../../template/header.jsp"></c:import>
	<section class="container-fluid col-lg-8 mt-5">
		<div class="row mt-5">
	 <form class="align-center" action="./update" method="post" enctype="multipart/form-data">
        <input type="hidden" name="noticeNum" id="noticeNum" value="${noticeUpdate.noticeNum}" >
        <div class="form-check">
            <input type="checkbox" id="radioDate" name="importCheck" <c:if test="${noticeUpdate.importCheck == true}">checked</c:if>>
            <label for="" style="font-size: 18px; font-weight: 600; margin-bottom: 15px;">
                ❗중요 공지사항
            </label>
            
        </div>
         <div style="display: flex; justify-content: space-between; width: 100%; margin: 10px 0;">
	        <div style="width: 70%;">
	    		<span style="font-size: 18px; font-weight: 600;">제목</span> 
	      	  	<input type="text" name="title" value="${noticeUpdate.title}" placeholder="제목을 입력하세요" style="width: 80%; height: 40px;">
	        </div>
	        <div style="width: 30%;">
	       		<span style="font-size: 18px; font-weight: 600;">작성자</span> 
	        	<input type="text" name="writer" value="${noticeUpdate.writer}" style="width: 70%;height: 40px;" value="${memberVO.id}" readonly>
	        </div>
        
        
        </div>
        
        
        
        <div>
        <div style="margin: 10px 0; font-size: 18px; font-weight: 600;">내용</div>
        <textarea id="summernote" name="contents" rows="30" cols="100" >${noticeUpdate.contents}</textarea>
        </div>
        <div  class="board-filetitle mb-3" id="refresh">💾첨부 파일  
            <div class="mb-3" id="fileAddResult">
        <c:forEach items="${noticeUpdate.noticeFileVOs}" var="file" varStatus="status">
            <c:if test="${not file.sort}">
                
                    <div class="file_form mt-2">
                        <!-- <label for="contents" class="form-label"></label> -->

                        <input type="file" name="files"  class="files form-control">
                        <span class="text" >${file.noticeOriName}</span> 
                        <button type="button" class="del btn btn-danger" style="margin:auto;display: block;">X</button>
                        
                    </div>
                
            </c:if>
        </c:forEach>
          </div>
      </div>  
			<div class="mb-3">
				<button type="button" id="fileAdd" class="btn btn-success">첨부파일 추가</button>
			</div>
        
         
        <input type="submit" name="add" value="수정하기"  class ="btn btn-info"style="margin-left: 300px;">
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