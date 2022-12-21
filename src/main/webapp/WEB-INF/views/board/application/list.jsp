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
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<link rel="stylesheet" href="/css/application.css">
<link href="/css/reset.css" rel="stylesheet">
</head>
<body>
	<c:import url="../../template/header.jsp"></c:import>
	<section class="container-fluid col-lg-8 mt-5 ">
	<div class="row mb-3">
		<form action="./list" class="row formStyle">
			<div>
				<label class="test_obj">
					<input type="submit" name="auctionSort" <c:if test="${param.auctionSort == null || param.auctionSort == 1}">class="checked"</c:if> value="1">
					<span>실시간경매 게시판</span>
					
				</label>
				 
				<label class="test_obj">
					<input type="submit" name="auctionSort" <c:if test="${param.auctionSort == 0}">class="checked"</c:if> value="0" >
					<span>온라인경매 게시판</span>
				</label>
				<c:if test="${param.auctionSort == null || param.auctionSort == 1}">
					<br><span>본 게시판은 실시간 경매장의 상품판매를 위해 운영되고있습니다.<br>
						입력 폼에 맞게 판매하시려는 상품을 신청하시면 승인, 반려 여부를 알려드립니다.<br>
						승인, 판매상품을 해당주소로 보내주시면 판매를 도와드립니다.<br>
						반려, 판매상품의 취소 사유를 확인하시고 수정해서 작성해주시면 판매를 도와드리겠습니다. <br>
						<br>
						실시간 경매 날짜는 추후 공지됩니다. 
					</span>
				</c:if>
				<c:if test="${param.auctionSort == 0}">
					<br><span>본 게시판은 온라인 경매장의 상품판매를 위해 운영되고있습니다.<br>
						입력 폼에 맞게 판매하시려는 상품을 신청하시면 승인, 반려 여부를 알려드립니다.<br>
						승인, 판매상품을 해당주소로 보내주시면 판매를 도와드립니다.<br>
						반려, 판매상품의 취소 사유를 확인하시고 수정해서 작성해주시면 판매를 도와드리겠습니다.<br><br>

						승인 이후, 온라인 경매장에 상품에서 확인하실 수 있습니다.
					</span>
				</c:if>
			 </div>
		</form>
	</div> 

	<div class="row mb-3 bbs_search">
		<form action="./list">
			<input type="hidden" value="${param.auctionSort}">
			<div class="col-3" style="display : inline-block;">
				<label class="visually-hidden" for="Kind" >Kind</label> 
				<select name="kind" class="form-select" id="Kind">
					
					<option class="kinds" value="applicationNum" <c:if test="${param.kind eq 'applicationNum' }"> selected </c:if>>글번호</option>
		
					<!-- <option class="kinds" value="contents">글내용</option> -->
					<option class="kinds" value="title" <c:if test="${param.kind eq 'title' }"> selected </c:if> >글제목</option>
					<option class="kinds" value="writer"  <c:if test="${param.kind eq 'writer' }"> selected </c:if> >작성자</option>
				</select>
			</div>

			<div class="col-3" style="display : inline-block;">
				<label class="visually-hidden" for="search">검색</label>
				<div class="input-group">
					<input type="text" name="search" value="${param.search}"
						class="form-control" id="search" placeholder="검색어를 입력하세요">
				</div>
			</div>
			<div class="col-3" style="display : inline-block;">
				<button type="submit" class="btn btn-warning">검색</button>
			</div>
				<label class="visually-hidden" for="auctionSort"></label>
				<div class="input-group">
					<input type="hidden" name="auctionSort" <c:if test="${param.auctionSort == null}">value="1"</c:if>
															<c:if test="${param.auctionSort != null}">value="${param.auctionSort}"</c:if>
						class="form-control" id="auctionSort" >
				</div>
		</form>

	</div>
	<table class="table table-bordered border-warning">
		 <thead >
		    <tr class="table-warning">
		      <th scope="col">번호</th>
		      <th scope="col">제목</th>
		      <th scope="col">작성자</th>
		      <th scope="col">작성날짜</th>
			  <c:if test="${memberVO.roleVOs[0].roleNum == 1 || memberVO.roleVOs[0].roleNum == 2}">
				  
				<th scope="col">승인상태</th>
			  </c:if>
				
			</tr>
		  </thead>
		  <tbody>
		    <c:forEach items="${applicationVO}" var="VO">
		    <tr>		      
			      <td>${VO.applicationNum}</td>
			      <td><a href="./detail?applicationNum=${VO.applicationNum}">${VO.title}</a></td>
			      <td>${VO.writer}</td>
			      <td>${VO.regDate}</td>  
				  <c:if test="${memberVO.roleVOs[0].roleNum == 1 || memberVO.roleVOs[0].roleNum == 2}">				  
					<c:choose>
						<c:when test="${VO.approvalCheck == '0'}"><td style="color: #fea532; font-weight: bold; text-align: center;">대기</td></c:when>
						<c:when test="${VO.approvalCheck == '1'}"><td style="color: blue; font-weight: bold; text-align: center;">승인</td></c:when>
						<c:when test="${VO.approvalCheck == '2'}"><td style="color: red; font-weight: bold; text-align: center;">거부/상세정보 부족</td></c:when>
						<c:when test="${VO.approvalCheck == '3'}"><td style="color: red; font-weight: bold; text-align: center;">거부/부적절한 이미지</td></c:when>
						<c:when test="${VO.approvalCheck == '4'}"><td style="color: red; font-weight: bold; text-align: center;">거부/부적절한 상품</td></c:when>
							<c:otherwise><td>거부</td></c:otherwise>
					</c:choose>
			  	</c:if>
		    </tr>
		     </c:forEach>
		   </tbody>
	</table>
	  
			<nav aria-label="Page navigation example">
					<ul class="pagination">
						<c:if test="${boardPageMaker.pre}">
							<li class="page-item"><a class="pageColorChange"
								href="./list?auctionSort=${param.auctionSort}&page=${boardPageMaker.startNum-1}&kind=${boardPageMaker.kind}&search=${boardPageMaker.search}"><<</a>
							</li>
						</c:if>
						<c:forEach begin="${boardPageMaker.startNum}" end="${boardPageMaker.lastNum}" var="i">
							
								
							
							<li class="page-item <c:if test="${param.page == i || param.page == null && i == 1 }">active</c:if>" >
							<a class="pageColorChange"
								href="./list?auctionSort=${param.auctionSort}&page=${i}&kind=${boardPageMaker.kind}&search=${boardPageMaker.search}">${i}</a></li>
						</c:forEach>
						<c:if test="${boardPageMaker.next}">
							<li class="page-item ${boardPageMaker.next?'':'disabled'}">
								<a class="pageColorChange"
								href="./list?auctionSort=${param.auctionSort}&page=${boardPageMaker.lastNum+1}&kind=${boardPageMaker.kind}&search=${boardPageMaker.search}">>></a>
							</li>
						</c:if>
					</ul>
			</nav>
		<div class="btnAdd">
			<a href="./add" type="button" class="btn btn-warning">글쓰기</a>
		</div>
		</section>
		<c:import url="../../template/footer.jsp"></c:import>
			<script type="text/javascript">
				let result = '${param.result}';
				if(result != ""){
					if(result == '1'){
						swal("등록성공","","success")
					} else{
						swal("등록실패","","error")
					}
				}
			</script> 
</body>
</html>

