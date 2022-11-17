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
<c:import url="../../temp/boot.jsp"></c:import>
<c:import url="../../temp/summer.jsp"></c:import>
</head>
<body>
	
	<section class="container-fluid col-lg-8 mt-5">
		<h1>Notice Write Page</h1>
		<div class="row">
	 <form class="align-center" action="./add" method="post" enctype="multipart/form-data">
        
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
        <textarea id="contents" name="contents" rows="30" cols="100"></textarea>
        </div>
        
        <!-- <div>
        <input type="file" name="files" class="files">
    	
        </div>
        
        <div>
        <input type="file" name="files" class="files"> 
    	
        </div> -->
        
  
        <!-- <div id="addFiles">
            <button type="button" id="fileadd" class="btn btn-success">파일 추가</button>
        </div> -->
				
        
        
        <input type="submit" name="add" value="책 추가"  class ="btn btn-info"style="margin-left: 300px;">
        </form>
</div>
	
</section>


<script type="text/javascript">
	$("#contents").summernote({
        height: 300,                 // 에디터 높이
        minHeight: null,             // 최소 높이
        maxHeight: null,             // 최대 높이
        focus: true,                  // 에디터 로딩후 포커스를 맞출지 여부
        lang: "ko-KR",               // 한글 설정
        placeholder: '최대 2048자까지 쓸 수 있습니다'   //placeholder 설정
          
   });
</script>
</body>
</html>