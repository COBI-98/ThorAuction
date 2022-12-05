<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>${member.id}의 현재 포인트</h1>
	
	<h1>${member.id}의 현질내역</h1>
	<c:set var="total" value="0" />
	<c:forEach items="${pay}" var="p">
		<p>${p.payTotal}</p>
		<p>${p.payDate}</p>
		<p>------------------</p>
		<c:set var="total" value="${total + p.payTotal}" />
	</c:forEach>

	<c:out value="${total + member.point}"/>
</body>
</html>