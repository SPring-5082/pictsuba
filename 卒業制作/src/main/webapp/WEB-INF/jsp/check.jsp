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
<link href="./css/check.css" rel="stylesheet">
<title><c:out value="${ application.replaceFirst('/','') } 注文確認"/></title>
</head>
<body>
<jsp:include page="../../jsp/background.jsp"></jsp:include>
<jsp:include page="../../jsp/header.jsp"></jsp:include>

<main>
	<div id="main_parent">
		<form action="${ application }/confirm" method="post" id="main_box">
			<div id="main_top">
				<h2>購入確認</h2>
				<div class="progress-bar">
					<div class="circle completed">
						<span>&#10003;</span>
					</div>
					<div class="line"></div>
					<div class="circle"></div>
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
					<c:forEach var="i" begin="0" step="1" end="${ products.size() - 1 }"> 
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
								￥<c:out value="${ calc.price(products.get(i).price(),products.get(i).category_id()) }"/> × <c:out value="${ quantities.get(i) }"/>個 = ￥<c:out value="${products.get(i).price() * quantities.get(i)}"/>
							</h4>
						</div>
					</c:forEach>
				</div>
				<div id="product_sum">
					<h4>合計金額：&yen;<c:out value="${ sum_price }"/></h4>
					<input type="hidden" name="product_total" id="product_total" value="${ sum_price }">
				</div>
			</section>
			<section>
				<div class="section_title">
					<span class="material-symbols-outlined">
						house
					</span>
					<h3>住所</h3>
				</div>
				<div class="choose_list" id="address_choose_list">
					<c:forEach items="${ addresses }" var="address">
						<label for="address_radio${ address.address_id() }" class="choose">
							<div class="left_box">
								<input type="radio" name="address_radio" value="${ address.address_id() }" id="address_radio${ address.address_id() }" required>
							</div>
							<div class="right_box">
								<div class="address_info">
									〒<c:out value="${ address.zip_code() }"/><br>
									<c:out value="${ address.pref() }"/> <c:out value="${ address.municipalities() }"/><br>
									<c:out value="${ address.street() }"/>
								</div>
							</div>
						</label>
					</c:forEach>
				</div>
				<div id="address_add_button" class="add_button">
					新しい住所を追加
					<span class="material-symbols-outlined">
						add
					</span>
				</div>
				<p id="non_address_error" class="error_msg"></p>
			</section>
			<section>
				<div class="section_title">
					<span class="material-symbols-outlined">
						payments
					</span>
					<h3>お支払い方法</h3>
				</div>
				<div class="choose_list">
					<label for="payment_radio1" class="choose accordion_menu">
						<div class="summary">
							<div class="left_box">
								<input type="radio" name="pay_type" value="card" id="payment_radio1" required>
							</div>
							<div class="right_box">
								クレジットカード支払い
							</div>
						</div>
						<div id="details" class="details">
							<div id="items">
								<c:forEach items="${ cards }" var="card">
									<div class="item">
										<input type="radio" name="card" value="${ card.card_id() }" id="credit_card${ card.card_id() }">
										<label for="credit_card${ card.card_id() }">
											<div class="item_info">カード番号：<c:out value="${ card.blindNumber() }"/></div>
										</label>
									</div>
								</c:forEach>
							</div>
							<div id="payment_add_button" class="add_button">
								新しいカードを追加
								<span class="material-symbols-outlined">
									add
								</span>
							</div>
							<p id="non_card_error" class="error_msg"></p>
						</div>
					</label>
					<label for="payment_radio2" class="choose">
						<div class="left_box">
							<input type="radio" name="pay_type" value="bank" id="payment_radio2" required>
						</div>
						<div class="right_box">
							銀行振込
						</div>
					</label>
				</div>
				<div id="point_box">
					<input type="checkbox" name="use_point" value="true" id="is_use_point">
					<label for="point"></label><h4>ポイントを使う：</h4>
					<input type="number" name="point" id="point" value="0" readonly="readonly" max="${ user.point() }" min="0" step="1">
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
					<h4>商品合計</h4><p id="show_product_total"><c:out value="${ total_quantity }"/></p>
					<h4>送料</h4><p id="show_postage">\500</p>
					<h4>ポイント</h4><p id="use_point">-0</p>
					<h3 class="total">合計<span>(税込み)</span></h3>
					<h3 class="total" id="total_amount">\<c:out value="${ sum_price + 500 }"/></h3>
				</div>
			</section>
			<input type="submit" value="次へ進む">
		</form>
		<!-- 住所入力フォーム　開始 -->
		<div id="address_modal_window" class="modal_window">
			<div id="address_modal_content" class="modal_content">
				<span class="material-symbols-outlined close_icon" id="address_close_icon">
					close
				</span>
				<h2>住所の追加</h2>
				<form id="address_form" class="add_form" onsubmit="return false;">
					<div class="input_box">
						<h4>郵便番号<span class="must">*</span></h4>
						<input type="text" name="zip_code" id="zip_code" placeholder="ハイフンなし" pattern="[0-9]{7}" inputmode="numeric" required>
						<p id="zip_code_error" class="error_msg"></p>
					</div>
	
					<div class="input_box">
						<h4>都道府県<span class="must">(自動入力)</span></h4>
						<input type="text" name="pref" id="pref" readonly>
						<p id="pref_error" class="error_msg"></p>
					</div>
	
					<div class="input_box">
						<h4>市区町村<span class="must">(自動入力)</span></h4>
						<input type="text" name="municipalities" id="municipalities" readonly>
					</div>
	
					<div class="input_box">
						<h4>番地<span class="must">*</span></h4>
						<input type="text" name="street" id="street" required>
					</div>
	
					<div class="input_box">
						<h4>建物名</h4>
						<input type="text" name="building" id="building">
					</div>
	
					<input type="submit" id="add_address" value="追加">
				</form>
			</div>
		</div>
		<!-- 住所入力フォーム 終了 -->
		<!-- クレジットカード入力フォーム　開始 -->
		<div id="payment_modal_window" class="modal_window">
			<div id="payment_modal_content" class="modal_content">
				<span class="material-symbols-outlined close_icon" id="payment_close_icon">
					close
				</span>
				<h2>クレジットカードの追加</h2>
				<form id="payment_form" class="add_form" onsubmit="return false;">
					<div class="input_box">
						<h4>カード名義人<span class="must">*</span></h4>
						<input type="text" name="name" id="credit_card_name" required>
					</div>
	
					<div class="input_box">
						<h4>カード番号<span class="must">*</span></h4>
						<input type="text" name="number" id="credit_card_number" pattern="[0-9]{14,16}" required>
						<p id="number_error" class="error_msg"></p>
					</div>
	
					<div class="input_box">
						<h4>有効期限<span class="must">*</span></h4>
						<div id="expire_area">
							<input type="text" name="expire_month" placeholder="MM" id="expire_month"
								pattern="0[1-9]|1[0-2]" required>
							/
							<input type="text" name="expire_year" placeholder="YY" id="expire_year"
								pattern="[0-9]{2}" required>
						</div>
						<p id="expire_month_error" class="error_msg"></p>
						<p id="expire_year_error" class="error_msg"></p>
					</div>
	
					<div class="input_box">
						<h4>セキュリティコード<span class="must">*</span></h4>
						<input type="text" name="security_code" id="security_code" pattern="[0-9]{3,4}" required>
						<p id="code_error" class="error_msg"></p>
					</div>
	
					<input type="submit" id="add_credit_card" value="追加">
				</form>
			</div>
		</div>
		<!-- クレジットカード入力フォーム　終了 -->
	</div>
</main>

<jsp:include page="../../jsp/footer.jsp"></jsp:include>
<script src="./js/radio_accordion_menu.js"></script>
<script type="module" src="./js/payment_modal_window.js"></script>
<script type="module" src="./js/address_modal_window.js"></script>
<script src="./js/zipcode.js"></script>
<script type="module" src="./js/send_payment.js"></script>
<script type="module" src="./js/send_address.js"></script>
<script src="./js/comon.js"></script>
<script src="./js/calc_amount.js"></script>
<script src="./js/address_validation.js"></script>
<script src="./js/payment_validation.js"></script>
</body>
</html>