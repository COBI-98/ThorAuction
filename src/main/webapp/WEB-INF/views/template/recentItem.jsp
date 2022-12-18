<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" href="/css/recentItem.css">

<!-- 최근 본 상품 -->
<nav class="css-1uufpby e1tjwjt913">
	<div class="css-1mv62lp e1tjwjt97">
		<button onclick="scrollWin(0, -50)" class="css-c584eq e1tjwjt91"></button>
		<strong class="css-1qe3ztt e1tjwjt96">최근 본 상품</strong>
		<div class="css-1wbo0ol e1tjwjt95" id="e1tjwjt95">
			<ul class="css-8erlkd e1tjwjt94">
			
				<c:forEach items="${productList}" var="product">
					<li class="css-1vgyrcu e1tjwjt93">
						<a href="#" class="css-10nxrmy e1tjwjt92">
							<img src="#" class="css-68s41j e1tjwjt90" style="width: 60px; height: 80px;">
						</a>
					</li>
				</c:forEach>
			</ul>
		</div>
		<button onclick="scrollWin(0, 50)" class="css-1klnrae e1tjwjt91"></button>
	</div>
</nav>
<script>
	function scrollWin(x, y) {
		const e1tjwjt95 = document.getElementById("e1tjwjt95");

		e1tjwjt95.scrollBy(x, y);
	}
</script>