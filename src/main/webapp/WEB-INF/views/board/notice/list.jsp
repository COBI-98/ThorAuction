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
	<h1>Notice List</h1>
	
	<table class="table table-bordered border-primary">
		 <thead >
		    <tr class="table-primary">
		      <th scope="col">번호</th>
		      <th scope="col">작성자</th>
		      <th scope="col">제목</th>
		      <th scope="col">내용</th>
		      <th scope="col">조회수</th>
		      <th scope="col">작성날짜</th>
		    </tr>
		  </thead>
		  <tbody>
		    <c:forEach items="${testdto}" var="dto">
		    <tr>
		      
			      <td><a href="./detail?noticeNum=${dto.noticeNum}">${dto.noticeNum}</a></td>
			      <td>${dto.writer}</td>
			      <td>${dto.title}</td>
			      <td>${dto.contents}</td>
			      <td>${dto.hit}</td>
			      <td>${dto.regDate}</td>      
			     
		    </tr>
		     </c:forEach>
		   </tbody>
	</table>
	
	<%-- <nav aria-label="Page navigation example">
				<ul class="pagination">
					<c:if test="${pager.pre}">
						<li class="page-item"><a class="page-link"
							href="./list?page=${pager.startNum-1}&kind=${pager.kind}&search=${pager.search}">Previous</a>
						</li>
					</c:if>
					<c:forEach begin="${pager.startNum}" end="${pager.lastNum}" var="i">
						<li class="page-item"><a class="page-link"
							href="./list?page=${i}&kind=${pager.kind}&search=${pager.search}">${i}</a></li>
					</c:forEach>

					<li class="page-item ${pager.next?'':'disabled'}"><a
						class="page-link"
						href="./list?page=${pager.lastNum+1}&kind=${pager.kind}&search=${pager.search}">Next</a>
					</li>
				</ul>
		</nav> --%>
		<div>
			<a href="./add" type="button" class="btn btn-info">글쓰기</a>
		</div>
		</section>
			<!-- <script type="text/javascript">
				let result = '${param.result}';
				if(result != ""){
					if(result == '1'){
						swal("등록성공","","success")
					} else{
						swal("등록실패","","error")
					}
				}
			</script> -->
</body>
</html>

