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
<title><c:out value="${ application.replaceFirst('/','') } お気に入り"/></title>
</head>
<body>
<jsp:include page="../../jsp/background.jsp"></jsp:include>
<jsp:include page="../../jsp/header.jsp"></jsp:include>
<div id="alert_window">
</div>
<main>
	<div id="main_parent">
	<h2>お気に入り</h2>
	<div id="main_window">
		<div id="products" class="view">
			<c:forEach items="${ products }" var="product">
				<div class="product">
					<a class="product_box" href="${ application }/product?productId=${product.product_id()}">
						<div class="frame">
							<div class="draw_area">
								<img src="${ product.image() }" alt="絵画">
							</div>
						</div>
						<div class="info">
							<h4 class="name"><c:out value="${ product.name() }"/></h4>
							<p class="creator"><c:out value="${ product.creator_name() }"/></p>
							<h4 class="price">&yen;<c:out value="${ calc.price(product.price(),product.category_id()) }"/></h4>
						</div>
					</a>
					<c:choose>
					<c:when test="${ product.stock() > 0 }">
						<div class="product_buttom">
							<button class="in_cart_btn" onclick="add_cart(${ product.product_id() })">カートへ入れる</button>
							<div class="delete_button" id="${ product.product_id() }" onclick="delete_favorite(${ product.product_id() })">削除</div>
						</div>
					</c:when>
					<c:otherwise>
						<div class="product_buttom">
							<button class="in_cart_btn">現在在庫切れ中</button>
							<div class="delete_button" id="${ product.product_id() }" onclick="delete_favorite(${ product.product_id() })">削除</div>
						</div>
					</c:otherwise>
					</c:choose>
				</div>
			</c:forEach>
		</div>
		<!-- お気に入りがない時 -->
		<div id="is_null">
			<span class="material-symbols-outlined" id="favorite_icon">
				favorite
			</span>
			<h3>お気に入りは空です</h3>
		</div>
	</div>
</div>
</main>
<jsp:include page="../../jsp/footer.jsp"></jsp:include>
<script src="./js/show_alert.js"></script>
<script src="./js/delete_favorite.js"></script>
<script src="./js/favorite_onload.js"></script>
<script src="./js/favorite_add_cart.js"></script>
<script src="./js/check_null.js"></script>
<script src="./js/comon.js"></script>
</body>
</html>