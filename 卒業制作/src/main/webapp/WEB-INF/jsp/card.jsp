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
<link href="./css/address_payment_info.css" rel="stylesheet">
<title><c:out value="${ application.replaceFirst('/','') } カード情報"/></title>
</head>
<body>

<jsp:include page="../../jsp/background.jsp"></jsp:include>

<jsp:include page="../../jsp/header.jsp"></jsp:include>
<main>
	<div id="main_parent">
		<h2>決済情報</h2>
		<div id="main_window">
			<div class="section">
				<div class="section_title">
					<span class="material-symbols-outlined">
						credit_card
					</span>
					<h3>クレジットカード</h3>
				</div>
				<div class="payment" id="items">
					<c:forEach items="${ cards }" var="card">
						<div class="item">
							<div class="item_info">カード番号：<c:out value="${ card.blindNumber() }"/></div>
							<button class="delete_button" id="${ card.card_id() }" onclick="delete_payment(${ card.card_id() })">削除</button>
						</div>
					</c:forEach>
				</div>
				<button id="payment_add_button" class="add_button">
					新しいカードを追加
					<span class="material-symbols-outlined">
						add
					</span>
				</button>
			</div>
			<!-- クレジットカード入力フォーム -->
			<div id="payment_modal_window" class="modal_window">
				<div id="payment_modal_content" class="modal_content">
					<span class="material-symbols-outlined" id="payment_close_icon">
						close
					</span>
					<h2>クレジットカードの追加</h2>
					<div class="add_form">
						<div class="input_box">
							<h4>お名前<span class="must">*</span></h4>
							<input type="text" name="name" id="credit_card_name">
						</div>
						<div class="input_box">
							<h4>カード番号<span class="must">*</span></h4>
							<input type="number" name="number" id="credit_card_number">
						</div>
						<div class="input_box">
							<h4>有効期限<span class="must">*</span></h4>
							<div id="expire_area">
								<input type="text" id="expire_month" name="expire_month" pattern="[0-9]{2}" placeholder="MM">
								/
								<input type="text" id="expire_year" name="expire_year" pattern="[0-9]{2}" placeholder="YY">
							</div>
						</div>
						<div class="input_box">
							<h4>セキュリティコード<span class="must">*</span></h4>
							<input type="number" name="security_code" id="security_code">
						</div>
						<input type="submit" value="追加" id="add_credit_card">
					</div>
				</div>
			</div>
		</div>
	</div>
</main>
<jsp:include page="../../jsp/footer.jsp"></jsp:include>
<script src="./js/comon.js"></script>
<script src="./js/payment_modal_window.js" type="module"></script>
<script src="./js/del_card.js"></script>
<script src="./js/add_card.js" type="module"></script>
</body>
</html>