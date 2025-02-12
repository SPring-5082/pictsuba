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
	</div>
</main>
<jsp:include page="../../jsp/footer.jsp"></jsp:include>
<script src="./js/comon.js"></script>
<script src="./js/payment_modal_window.js" type="module"></script>
<script src="./js/del_card.js"></script>
<script src="./js/payment_validation.js"></script>
<script src="./js/add_card.js" type="module"></script>
</body>
</html>