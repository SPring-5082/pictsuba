<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0&" />
<link href="./img/sightIcon.jpg" rel="icon">
<link rel="stylesheet" href="./css/comon.css">
<link rel="stylesheet" href="./css/check.css">
<link rel="stylesheet" href="./css/order_details.css">
<title><c:out value="${ application } 注文詳細"/></title>
</head>
<body>
<jsp:include page="../../jsp/background.jsp"></jsp:include>
<jsp:include page="../../jsp/header.jsp"></jsp:include>

<main>
	<div id="main_parent">
		<div id="main_box">
			<div id="main_top">
				<h2>注文確認</h2>
			</div>
			<section>
				<div class="section_title">
					<span class="material-symbols-outlined">
						menu_book
					</span>
					<h3>注文詳細</h3>
				</div>
				<div id="order_details">
					<h4>注文状況</h4><p>お届け済み</p>
					<h4>注文日</h4><p>2025/02/12</p>
					<h4>注文合計</h4><p>\<c:out value="${ order.sum_price() }"/></p>
				</div>
			</section>
			<section>
				<div class="section_title">
					<span class="material-symbols-outlined">
						shopping_cart
					</span>
					<h3>購入商品</h3>
				</div>
				<div id="product_list">
					<c:forEach items="${ products }" var="product">
						<div class="product">
							<div class="frame">
								<div class="draw_area">
									<img src="${ product.image() }" alt="絵画">
								</div>
							</div>
							<div class="info">
								<h4 class="name">「<c:out value="${ product.name() }"/>」</h4>
								<p class="creator"><c:out value="${ product.creator_name() }"/></p>
							</div>
							<h4 class="price_area">
								\<c:out value="${ product.price() }"/>× <c:out value="${ map.get(product) }"/>個 = ￥値段
							</h4>
						</div>
					</c:forEach>
				</div>
				<div id="product_sum">
					<h4>合計金額：\<c:out value="${ order.sum_price() }"/></h4>
				</div>
			</section>
			<section>
				<div class="section_title">
					<span class="material-symbols-outlined">
						house
					</span>
					<h3>住所</h3>
				</div>
				<div class="choosed">
					〒<c:out value="${ address.zip_code(address.zip_code()) }"/><br>
					<c:out value="${ address.pref() }"/> <c:out value="${ address.municipalities() }"/><br>
					<c:out value="${ address.street() }"/>
				</div>
			</section>
			<section>
				<div class="section_title">
					<span class="material-symbols-outlined">
						attach_money
					</span>
					<h3>ご請求金額</h3>
				</div>
				<div id="details_amount">
					<h4>商品合計</h4><p><c:out value="${ total_quantity }"/></p>
					<h4>送料</h4><p>\500</p>
					<h3 class="total">合計<span>(税込み)</span></h3>
					<h3 class="total">\<c:out value="${ order.sum_price() + 500 }"/></h3>
				</div>
			</section>
			<a href="/${ application }/order-history" id="main_bottom">
				<span class="material-symbols-outlined">
					chevron_left
				</span>
				戻る
			</a>
		</div>
	</div>
</main>


<jsp:include page="../../jsp/footer.jsp"></jsp:include>
</body>
</html>