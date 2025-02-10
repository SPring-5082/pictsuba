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
    <link href="./css/product_details.css" rel="stylesheet">
    <title><c:out value="${ application.replaceFirst('/','') } ${ product.name() }"/></title>
</head>
<body>

<jsp:include page="../../jsp/background.jsp"></jsp:include>
<jsp:include page="../../jsp/header.jsp"></jsp:include>
<div id="alert_window">
</div>
<main>
	<div id="main_parent">
		<div id="product_window">
			<div class="pictura">
				<div class="frame">
					<div class="draw_area">
						<img src="${ product.image() }" alt="絵画">
					</div>
				</div>
				<c:choose>
				<c:when test="${ fav }">
					<button id="favorite_btn" class="click" onclick="click_icon(${ product.product_id() })">
						<img id="non_icon" alt="お気に入りでないアイコン" src="./img/non_favorite_icon.png">
						<img class="view" id="favorite_icon" alt="お気に入りアイコン" src="./img/favorite_icon.png">
					</button>
				</c:when>
				<c:otherwise>
					<button id="favorite_btn" onclick="click_icon(${ product.product_id() })">
						<img class="view" id="non_icon" alt="お気に入りでないアイコン" src="./img/non_favorite_icon.png">
						<img id="favorite_icon" alt="お気に入りアイコン" src="./img/favorite_icon.png">
					</button>
				</c:otherwise>
				</c:choose>
			</div>
			<div class="info">
				<h4 class="name"><c:out value="${ product.name() }"/></h4>
				<h4 class="price">&yen;<c:out value="${ calc.price(product.price(),product.category_id()) }"/></h4>
				<p class="creator"><c:out value="${ product.creator_name() }"/></p>
				<p class="description">
					<c:out value="${ product.descryption() }"/>
				</p>
			</div>
			<c:choose>
			<c:when test="${ product.stock() > 0 }">
				<button class="in_cart_btn" onclick="add_cart(${ product.product_id()})">カートへ入れる</button>
			</c:when>
			<c:otherwise>
				<button class="in_cart_btn">現在在庫切れ中</button>
			</c:otherwise>
			</c:choose>
		</div>
	</div>
</main>
<jsp:include page="../../jsp/footer.jsp"></jsp:include>
<script src="./js/comon.js"></script>
<script src="./js/details_add_cart.js"></script>
<script src="./js/send_favorite.js"></script>
<script src="./js/show_alert.js"></script>
</body>
</html>