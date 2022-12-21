<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>세상의 모든 경매: BIDCOIN</title>
  <link href="/images/miniLogo_BidCoin.png" rel="shortcut icon" type="image/x-icon">
<c:import url="../../template//boot.jsp"></c:import>

<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<link href="/css/reset.css" rel="stylesheet">
</head>
<body>
	<c:import url="../../template/header.jsp"></c:import>
	<section class="container-fluid col-lg-8 mt-5">
	
	<table class="table table-bordered border-warning">
		<tbody>
		
		  <tr >
			  <th style="width: 200px;">글제목</th>
			  <td>${applicationVO.title}</td>
			 
			
		  </tr>
		   <tr >
			  <th>작성자</th>
			  <td>${applicationVO.writer}</td>
		  </tr>
		  </tbody>
	  </table>
	  <div class="board-filetitle" style="font-size: 23px; font-weight: 600; margin: 10px;">상세 정보</div>
		  <table class="table table-bordered border-warning">
		  <tbody>
		  <tr class="table-warning">
			  
		  <c:forEach items="${applicationVO.productVOs}" var="product">
			  
				   <tr>
					   <th style="width: 200px;">상품이름</th>
					  <td>${product.productName}</td>
					  
				  </tr>
				  <tr>
					<th>초기가격</th>
					<td><fmt:formatNumber value="${product.productPrice}" pattern="###,###"/></td>
				</tr>
				<tr>
					<th>경매기간</th>
					<td>${product.auctionPeriod}</td>
				</tr>
				<tr>
					<th>상품상세정보</th>
					<td>${product.productInformation}</td>
				</tr>
				
				<th>첨부파일</th>
				<td>
				<c:forEach items="${product.productFileVOs}" var="file">
					<c:if test="${not file.sort}">
						
							
							<img src="/file/product/${file.fileName}" alt="" width="100px">					
						
						
					</c:if>
				</c:forEach>
				</td>
		  </c:forEach>
		  </tbody>
		  </table>



<div style="display: flex; justify-content: flex-end;">


	<div class="btnRight"> <a href="/board/application/update?applicationNum=${applicationVO.applicationNum}" class="btn btn-warning" style="color: white; width: 75px; height: 40px; font-size: 18px; margin-left: 10px;" >수정</a></div>
	<div class="btnRight"> <a href="/board/application/delete?applicationNum=${applicationVO.applicationNum}" class="btn btn-warning" style=" color: white; width: 75px; height: 40px; font-size: 18px; margin-left: 10px;  ">삭제</a></div>
	<c:if test="${memberVO.roleVOs[0].roleNum == 1 || memberVO.roleVOs[0].roleNum == 2}">
		<c:if test="${applicationVO.auctionSort == true}">
			<div class="btnRight"> <a href="#" id="approval_btn" class="btn btn-success" style=" color: white; width: 75px; height: 40px; font-size: 18px; margin-left: 10px;" >승인</a></div>
		</c:if>
		<c:if test="${applicationVO.auctionSort != true}">
			<div class="btnRight"> <a href="/product/approval?productNum=${applicationVO.productVOs[0].productNum}" id="approval_btn2" class="btn btn-success" style="width: 75px; height: 40px; font-size: 18px; margin-left: 10px;" >승인</a></div>
		</c:if>
		<!-- Button trigger modal -->
		<!-- Button trigger modal -->
		<div class="btnRight">
			<button type="button" id="refuseBtn" class="btn btn-danger btn-lg" style="width: 75px; height: 40px; font-size: 18px; margin-left: 10px;" >
			  거부
			</button>
		</div>
	</c:if>


</div>

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
                        <td><input class="form-control" id="userName" disabled type="text" value="${applicationVO.writer}"></td>
                    </tr>
                    <tr>
                        <td>취소사유<td>
							<input type="hidden" id="application_num"value="${applicationVO.applicationNum}">
                        <label for="approvalCheck" hidden="">취소사유</label>
							<select name="approvalCheck" id="approvalCheck">
							    <option value="0">사유를 선택해주세요</option>
							    <option value="2">상세정보 부족</option>
							    <option value="3">부적절한 이미지</option>
								<option value="4">부적절한 상품</option>
							</select>
                    </tr>                  
                </table>
            </div>
            <div class="modal-footer">
                <button id="modalSubmit" type="button" class="btn btn-success">전송</button>
                <button id="modalClose" type="button" class="btn btn-default" data-dismiss="modal">취소</button>
            </div>
        </div>
    </div>
</div>
   
</section>
<c:import url="../../template/footer.jsp"></c:import>
<script src="/js/approvalSubmit.js"></script>
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