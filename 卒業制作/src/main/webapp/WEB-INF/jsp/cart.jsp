<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0&" />
<link href="./img/sightIcon.jpg" rel="icon">
<link href="./css/comon.css" rel="stylesheet">
<link href="./css/cart.css" rel="stylesheet">
<title><c:out value="${ appication } カート"/></title>
</head>
<body>
<jsp:include page="../../jsp/header.jsp"></jsp:include>
<main>
	<div id="main_parent">
		<h2>カート</h2>
		<div id="main_window">
			<c:choose>
			<c:when test="${ not empty products }">
				<form action="/${ application }/check" method="post">
					<c:forEach items="${ products }" var="product">
						<div class="cart_box">
							<div class="box_left">
								<input type="checkbox" name="product_id:${ product.product_id() }" class="checkbox" checked>
							</div>
							<div class="box_right">
								<a class="product">
									<div class="frame">
										<div class="draw_area">
											<img src="${ product.image() }" alt="絵画">
										</div>
									</div>
									<div class="info">
										<div class="info_left">
											<h4 class="name">「<c:out value="${ product.name() }"/>」</h4>
											<p class="creatord"><c:out value="${ product.creator_name() }"/></p>
											<h4 class="price">\<c:out value="${ product.price() }"/></h4>
										</div>
									</div>
								</a>
								<div class="product_bottom">
									<div class="quantity_area">
										<label for="quantity">数量：</label>
										<input type="number" name="quantity:${ product.product_id() }" step="1" value="1" min="1" max="${ product.stock() }" required>
									</div>
									<a class="delete_button">削除</a>
								</div>
							</div>
						</div>
					</c:forEach>
					<input type="submit" value="購入へ進む">
				</form>
			</c:when>
			<c:otherwise>
				<span class="material-symbols-outlined" id="bag_icon">
					shopping_bag
				</span>
				<h3>カートの中身は空です</h3>
			</c:otherwise>
			</c:choose>
		</div>
	</div>
</main>
<jsp:include page="../../jsp/footer.jsp"></jsp:include>
<script src="./js/comon.js"></script>
</body>
</html>