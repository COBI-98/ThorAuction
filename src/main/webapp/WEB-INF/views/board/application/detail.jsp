<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../../temp/boot.jsp"></c:import>

<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<body>
	<section class="container-fluid col-lg-8 mt-5">


	<h1>경매신청게시판 상세화면</h1>
	
	<table class="table table-bordered border-primary">
		<tbody>
		
		  <tr >
			  <th style="width: 70px;">글제목</th>
			  <td>${applicationVO.title}</td>
			
		  </tr>
		   <tr >
			  <th>작성자</th>
			  <td>${applicationVO.writer}</td>
		  </tr>
		  </tbody>
	  </table>
	  <div class="board-filetitle">💾첨부 파일</div>
		  <table class="table table-bordered border-primary">
		  <tbody>
		  <tr class="table-primary">
			  
		  <c:forEach items="${applicationVO.productVOs}" var="product">
			  
				   <tr>
					   <th>상품이름</th>
					  <td>${product.productName}</td>
					  
				  </tr>
				  <tr>
					<th>초기가격</th>
					<td>${product.productPrice}</td>
				</tr>
				<tr>
					<th>상품상세정보</th>
					<td>${product.productInformation}</td>
				</tr>
				<tr>
					<th>경매기간</th>
					<td>${product.auctionPeriod}</td>
				</tr>
				<th>파일</th>
				<c:forEach items="${product.productFileVOs}" var="file">
					<c:if test="${not file.sort}">
						<tr>
							
							<td>${file.fileName}</td>
							
						</tr>
						
					</c:if>
				</c:forEach>
		  </c:forEach>
		  </tbody>
		  </table>


</section>
<section class="container-fluid col-lg-2 mt-5">

<div> <a href="/board/application/update?applicationNum=${applicationVO.applicationNum}" class="btn btn-info" >글 수정</a></div>
<div> <a href="/board/application/delete?applicationNum=${applicationVO.applicationNum}" class="btn btn-info" style="margin-top: 10px;">글 삭제</a></div>
<div> <a href="#" class="btn btn-success" >승인</a></div>
<!-- Button trigger modal -->
<!-- Button trigger modal -->
<button type="button" id="refuseBtn" class="btn btn-danger btn-lg"  >
  거부
</button>

<!-- Modal -->
<div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
 
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <!-- <button type="button" class="close" data-dismiss="modal">&times;</button> -->
                <h4 id="modal-title" class="modal-title"></h4>
            </div>
            <div class="modal-body">
                <table class="table">
                    <tr>
                        <td>사용자명</td>
                        <td><input class="form-control" id="userName" type="text"></td>
                    </tr>
                    <tr>
                        <td>취소사유<td>
                        <label for="pet-select" hidden="">취소사유</label>
							<select name="pets" id="pet-select">
							    <option value="">사유를 선택해주세요</option>
							    <option value="contents">상세내용 부족</option>
							    <option value="image">부적절한 이미지</option>
							</select>
                    </tr>  
                    <tr>
                        <td>내용</td>
                        <td><textarea class="form-control" id="contents" rows="10"></textarea></td>
                    </tr>                    
                </table>
            </div>
            <div class="modal-footer">
                <button id="modalSubmit" type="button" class="btn btn-success">Submit</button>
                <button id="modalClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
   
</section>
<script type="text/javascript">
$(function(){
	$("#refuseBtn").click(function(){
        /* action='create';
        type = 'POST' */
        $("#modal-title").text("취소 사유 작성");
        $("#myModal").modal('show');
    });
	
	$("#modalClose").click(function(){
        /* action='create';
        type = 'POST' */
        $("#myModal").modal('hide');
    });
})
</script>

	
</body>
</html>