<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0&" />
<link href="./img/sightIcon.jpg" rel="icon">
<link href="./css/comon.css" rel="stylesheet">
<link href="./css/favorite.css" rel="stylesheet">
<title><c:out value="${ application } お気に入り"/></title>
</head>
<body>
<jsp:include page="../../jsp/header.jsp"></jsp:include>
<main>
	<div id="main_parent">
		<h2>お気に入り</h2>
		<div id="main_window">
			<div id="products">
				<c:forEach items="${ products }" var="product">
					<a href="/${ application }/product?productId=${ product.product_id() }" class="product_box">
						<div class="product">
							<div class="frame">
								<div class="draw_area">
									<img src="${ product.image() }" alt="絵画">
								</div>
							</div>
							<div class="info">
								<div class="info_bottom">
									<h4 class="name"><c:out value="${ product.name() }"/></h4>
									<p class="creator"><c:out value="${ product.creator_name() }"/></p>
									<h4 class="price">\<c:out value="${ calc.price(product.price(),product.category_id()) }"/></h4>
									<br>
									<p class="description">
										<c:out value="${ product.descryption() }"/>
									</p>
								</div>
							</div>
						</div>
						<button class="in_cart_btn">カートへ入れる</button>
					</a>
				</c:forEach>
			</div>
		</div>
	</div>
</main>
<jsp:include page="../../jsp/footer.jsp"></jsp:include>
<script src="./js/comon.js"></script>
</body>
</html>