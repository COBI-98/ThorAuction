<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>세상의 모든 경매: BIDCOIN</title>
  <link href="/images/miniLogo_BidCoin.png" rel="shortcut icon" type="image/x-icon">
<c:import url="../../template/boot.jsp"></c:import>
<link href="/css/reset.css" rel="stylesheet">
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<body>
	<c:import url="../../template/header.jsp"></c:import>
	<section class="container-fluid col-lg-8 mt-5">
	
	<table class="table table-bordered border-warning">
  <tbody>
  
    <tr >
    	<th style="width: 200px;">글제목</th>
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
    <table class="table table-bordered border-warning">
    <tbody>
    <tr class="table-warning">
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





<div class="btnRight" > <a href="/board/notice/update?noticeNum=${noticeVO.noticeNum}" class="btn btn-warning" >글 수정</a></div>
<div class="btnRight"> <a href="/board/notice/delete?noticeNum=${noticeVO.noticeNum}" class="btn btn-warning" style="margin-top: 10px;">글 삭제</a></div>
</section>
<c:import url="../../template/footer.jsp"></c:import>
	
	
</body>
</html>