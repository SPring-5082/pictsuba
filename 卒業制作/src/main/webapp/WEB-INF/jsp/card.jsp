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
<title><c:out value="${ application } カード情報"/></title>
</head>
<body>
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
				
				<c:forEach items="${ cards }" var="card">
					<div class="payment items">
						<div class="item">
							<div class="item_info">カード番号：<c:out value="${ card.blindNumber() }"/></div>
							<button class="delete_button">削除</button>
						</div>
					</div>
				</c:forEach>
				
				<button id="add_button">
					新しいカードを追加
					<span class="material-symbols-outlined">
						add
					</span>
				</button>
			</div>
				<!-- クレジットカード入力フォーム -->
				<div id="modal_window">
					<div id="modal_content">
						<span class="material-symbols-outlined" id="close_icon">
							close
						</span>
						<h2>クレジットカードの追加</h2>
						<form action="" class="add_form">
							<div class="input_box">
								<label for="name"><h4>お名前<span class="must">*</span></h4></label>
								<input type="text" name="name" required>
							</div>
							<div class="input_box">
								<label for="number"><h4>カード番号<span class="must">*</span></h4></label>
								<input type="number" name="number" required>
							</div>
							<div class="input_box">
								<label for="expire"><h4>有効期限<span class="must">*</span></h4></label>
								<input type="month" name="expire" required>
							</div>
							<div class="input_box">
								<label for="security_code">セキュリティコード</label>
								<input type="number" name="security_code" required>
							</div>
							<input type="submit" value="追加">
						</form>
					</div>
				</div>
			</div>
			<a href="/${ application }/mypage" id="back_anchor">マイページへ</a>
		</div>
	</main>
<jsp:include page="../../jsp/footer.jsp"></jsp:include>
<script src="./js/modal_window.js"></script>
<script src="./js/comon.js"></script>
</body>
</html>