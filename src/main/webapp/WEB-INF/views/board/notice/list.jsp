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
<link rel="stylesheet" href="/css/importTR.css">
</head>
<body>

	<section class="container-fluid col-lg-8 mt-5">
	<h1>공지게시판</h1>
	<div class="row mb-3">
		<form action="./list"
			class="row row-cols-lg-auto g-3 align-items-center">

			<div class="col-12">
				<label class="visually-hidden" for="Kind" >Kind</label> 
				<select name="kind" class="form-select" id="Kind">
					<option class="kinds" value="noticeNum" <c:if test="${param.kind eq 'noticeNum' }"> selected </c:if>>글번호</option>
					<!-- <option class="kinds" value="contents">글내용</option> -->
					<option class="kinds" value="title" <c:if test="${param.kind eq 'title' }"> selected </c:if> >글제목</option>
					<option class="kinds" value="writer"  <c:if test="${param.kind eq 'writer' }"> selected </c:if> >작성자</option>
				</select>
			</div>

			<div class="col-12">
				<label class="visually-hidden" for="search">검색</label>
				<div class="input-group">
					<input type="text" name="search" value="${param.search}"
						class="form-control" id="search" placeholder="Search">
				</div>
			</div>
			<div class="col-12">
				<button type="submit" class="btn btn-primary">검색어찾기</button>
			</div>
		</form>

	</div>
	<table class="table table-bordered border-primary">
		 <thead >
		    <tr class="table-primary">
		      <th scope="col">번호</th>
		      <th scope="col">제목</th>
		      <th scope="col">작성자</th>
		      <th scope="col">작성날짜</th>
		    </tr>
		  </thead>
		  <tbody>
		  	<c:forEach items="${importNotice}" var="dto">
			  	<c:if test="${!not dto.importCheck}">
					<tr class="importTr"> 
						<td class="importTd">공지</td>
					    <td><a href="./detail?noticeNum=${dto.noticeNum}" >${dto.title}</a></td>  
						<td>${dto.writer}</td>
					    <td>${dto.regDate}</td> 
					</tr>
				</c:if>
		  	</c:forEach>
		    <c:forEach items="${testdto}" var="dto">
			
		    <tr>
			      <td>${dto.noticeNum}</td>
			  	  <td><a href="./detail?noticeNum=${dto.noticeNum}" >${dto.title}</a></td>  
				  <td>${dto.writer}</td>
			      <td>${dto.regDate}</td>      		     
		    </tr>
		     </c:forEach>
		   </tbody>
	</table>
	  
	 <nav aria-label="Page navigation example">
				<ul class="pagination">
					<c:if test="${boardPageMaker.pre}">
						<li class="page-item"><a class="page-link"
							href="./list?page=${boardPageMaker.startNum-1}&kind=${boardPageMaker.kind}&search=${boardPageMaker.search}">Previous</a>
						</li>
					</c:if>
					<c:forEach begin="${boardPageMaker.startNum}" end="${boardPageMaker.lastNum}" var="i">
						
						<li class="page-item <c:if test="${param.page == i || param.page == null && i == 1 }">active</c:if>" >
						<a class="page-link"
							href="./list?page=${i}&kind=${boardPageMaker.kind}&search=${boardPageMaker.search}">${i}</a></li>
					</c:forEach>

					<li class="page-item ${boardPageMaker.next?'':'disabled'}"><a
						class="page-link"
						href="./list?page=${boardPageMaker.lastNum+1}&kind=${boardPageMaker.kind}&search=${boardPageMaker.search}">Next</a>
					</li>
				</ul>
		</nav>
		<div>
			<a href="./add" type="button" class="btn btn-info">글쓰기</a>
		</div>
		</section>
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

