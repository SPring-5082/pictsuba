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

<jsp:include page="../../jsp/background.jsp"></jsp:include>

<jsp:include page="../../jsp/header.jsp"></jsp:include>
<main>
	<div id="main_parent">
		<h2>カート</h2>
		<div id="main_window">
			<form action="/${ application }/check" method="post" id="products" class="view">
				<c:if test="${ products.size() > 0 }">
				<c:forEach var="i" begin="0" end="${ products.size() - 1 }" step="1">
					<div class="cart_box">
						<div class="box_right">
							<a class="product" href="/${ application }/product?productId=${ products.get(i).product_id() }">
								<div class="frame">
									<div class="draw_area">
										<img src="${ products.get(i).image() }" alt="絵画">
									</div>
								</div>
								<div class="info">
									<div class="info_left">
										<h4 class="name">「<c:out value="${ products.get(i).name() }"/>」</h4>
										<p class="creatord"><c:out value="${ products.get(i).creator_name() }"/></p>
										<h4 class="price">\<c:out value="${ calc.price(products.get(i).price(),products.get(i).category_id()) }"/></h4>
									</div>
								</div>
							</a>
							<div class="product_bottom">
								<div class="quantity_area">
									<label for="quantity">数量：</label>
									<input type="number" name="${ products.get(i).product_id() }" step="1" value="${ quantities.get(i) }" min="0" max="${ products.get(i).stock() }" required>
								</div>
								<a class="delete_button" id="${ products.get(i).product_id() }" onclick="delete_cart(${ products.get(i).product_id()})">削除</a>
							</div>
						</div>
					</div>
				</c:forEach>
				</c:if>
				<input type="submit" value="購入へ進む">
			</form>
			
			
			<!-- カートが空のとき -->
			<div id="is_null">
			  <span class="material-symbols-outlined" id="bag_icon">
			  	shopping_cart
			  </span>
			  <h3>カートの中身は空です</h3>
			</div>
			
			
			
			
		</div>
	</div>
</main>
<jsp:include page="../../jsp/footer.jsp"></jsp:include>
<script src="./js/comon.js"></script>
<script src="./js/del_cart.js"></script>
<script src="./js/check_null.js"></script>
<script src="./js/cart_onload.js"></script>
</body>
</html>