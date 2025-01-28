<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0&" />
<link href="./img/sightIcon.jpg" rel="icon">
<link href="./css/comon.css" rel="stylesheet">
<link href="./css/check.css" rel="stylesheet">
<link href="./css/confirm.css" rel="stylesheet">
<title><c:out value="${ application }"/></title>
</head>
<body>
	
<main>
	<div id="main_parent">
		<form action="/${ application }/completion" method="post" id="main_box">
			<div id="main_top">
				<h2>購入確認</h2>
				<div class="progress-bar">
					<a href="./buy_confirm_step1.html" class="circle completed">
						<span>&#10003;</span>
					</a>
					<div class="line completed"></div>
					<div class="circle completed">
						<span>&#10003;</span>
					</div>
					<div class="line"></div>
					<div class="circle"></div>
				</div>
			</div>
			<section>
				<div class="section_title">
					<span class="material-symbols-outlined">
						shopping_cart
					</span>
					<h3>カート内商品</h3>
				</div>
				<div id="product_list">
					<c:forEach var="i" begin="0" end="${ products.size() - 1 }" step="1">
						
						<div class="product">
							<div class="frame">
								<div class="draw_area">
									<img src="${ products.get(i).image() }" alt="絵画">
								</div>
							</div>
							<div class="info">
								<h4 class="name">「<c:out value="${ products.get(i).name() }"/>」</h4>
								<p class="creator"><c:out value="${ products.get(i).creator_name() }"/></p>
							</div>
							<h4 class="price_area">
								\<c:out value="${ calc.price(products.get(i).price(),products.get(i).category_id()) }"/> × <c:out value="${ quantities.get(i) }"/>個 = \<c:out value="${ quantities.get(i) * products.get(i).price() }"/>
							</h4>
						</div>
						
					</c:forEach>
				</div>
				<div id="product_sum">
					<h4>合計金額：\<c:out value="${ sum_price }"/></h4>
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
					〒<c:out value="${ address.zip_code() }"/><br>
					<c:out value="${ address.pref() }"/>　<c:out value="${ address.municipalities() }"/><br>
					<c:out value="${ address.street() }"/>
				</div>
			</section>
			<section>
				<div class="section_title">
					<span class="material-symbols-outlined">
						payments
					</span>
				<h3>お支払い方法</h3>
				</div>
				<c:choose>
					<c:when test="${ pay_type eq 'card' }">
						<div class="choosed">
							<h5>クレジットカード支払い</h5>
							<p>カード番号：<c:out value="${ card.blindNumber() }"/></p>
						</div>
					</c:when>
					<c:otherwise>
						<div class="choosed">
							<h5>銀行振込</h5>
						</div>
					</c:otherwise>
				</c:choose>
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
					<h4>送料</h4><p>500</p>
					<h4>ポイント</h4><p>-<c:out value="${ point }"/></p>
					<h3 class="total">合計<span>(税込み)</span></h3>
					<h3 class="total">\<c:out value="${ sum_price + 500 - point }"/></h3>
				</div>
			</section>
			<div id="main_bottom">
			<!-- 
				<a href="/${ application }/check">
					<span class="material-symbols-outlined">
						chevron_left
					</span>
					修正
				</a>
			 -->
				<a href="#" onclick="postToServer()">
					<span class="material-symbols-outlined">
						chevron_left
					</span>
					修正
				</a>
				<script>
					function postToServer() {
						const form = document.createElement('form');
						form.method = 'POST';
						form.action = '/pictsuba/check'; // 送信先URLを指定
						document.body.appendChild(form);
						form.submit();
					}
				</script>
				
				<input type="submit" value="決済">
			</div>
			<input type="hidden" name="address_id" value="${ address.address_id() }">
			<input type="hidden" name="point" value="${ point }">
		</form>
	</div>
</main>
	
<script src="../js/comon.js"></script>
</body>
</html>