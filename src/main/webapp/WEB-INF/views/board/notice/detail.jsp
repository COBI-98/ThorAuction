<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../../template/boot.jsp"></c:import>

<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<body>
	<section class="container-fluid col-lg-8 mt-5">


	<h1>공지게시판 상세보기</h1>
	
	<table class="table table-bordered border-primary">
  <tbody>
  
    <tr >
    	<th style="width: 70x;">글제목</th>
    	<td>${noticeVO.title}</td>
      
    </tr>
     <tr >
    	<th>작성자</th>
    	<td>${noticeVO.writer}</td>
    </tr>
     <tr>
    	<th>내용</th>
    	<td>${noticeVO.contents}</td>
    </tr>
    </tbody>
</table>
<div  class="board-filetitle">💾첨부 파일</div>
    <table class="table table-bordered border-primary">
    <tbody>
    <tr class="table-primary">
	    <th>파일</th>
    <c:forEach items="${noticeVO.noticeFileVOs }" var="file">
    	<c:if test="${not file.sort}">
	    
		     <tr>
				<!-- <td><a href ="/file/notice/${file.noticeFileName}"></a>test</td> -->
				<td><a href="/fileDown/notice?noticeFileName=${file.noticeFileName}">${file.noticeOriName}</a></td>
	    	</tr>
	    </c:if>
	</c:forEach>
	</tbody>
    </table>


</section>
<section class="container-fluid col-lg-2 mt-5">

<div> <a href="/board/notice/update?noticeNum=${noticeVO.noticeNum}" class="btn btn-info" >글 수정</a></div>
<div> <a href="/board/notice/delete?noticeNum=${noticeVO.noticeNum}" class="btn btn-info" style="margin-top: 10px;">글 삭제</a></div>
</section>
	
	
</body>
</html>