<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0&" />
<link href="../img/sightIcon.jpg" rel="icon">
<link href="../css/comon.css" rel="stylesheet">
<link href="../css/contact.css" rel="stylesheet">
<title><c:out value="${ application }"/></title>
</head>
<body>
<jsp:include page="../jsp/nodispbackground.jsp"></jsp:include>

<jsp:include page="../jsp/nodispHeader.jsp"></jsp:include>

<main>
	<div id="main_parent">
		<h2>よくある質問（FAQ）</h2>
		<div id="faq">
			<button class="faq_box" onclick="toggleAccordion(0)">
				<div class="summary">
					<span class="material-symbols-outlined">
						arrow_drop_down
					</span>
					<h3>Q: 商品の配送にどのくらい時間がかかりますか？</h3>
				</div>
				<div class="details">A: 通常、3～5営業日でお届けします。ただし、地域や配送状況により多少前後する場合がございます。</div>
			</button>
			<button class="faq_box" onclick="toggleAccordion(1)">
				<div class="summary">
					<span class="material-symbols-outlined">
						arrow_drop_down
					</span>
					<h3>Q: 支払い方法は何がありますか？</h3>
				</div>
				<div class="details">A: クレジットカード、銀行振込がご利用いただけます。</div>
			</button>
			<button class="faq_box" onclick="toggleAccordion(2)">
				<div class="summary">
					<span class="material-symbols-outlined">
						arrow_drop_down
					</span>
					<h3>Q: 返品や交換は可能ですか？</h3>
				</div>
				<div class="details">A: 商品到着後7日以内であれば可能です。詳細は返品ポリシーをご確認ください。</div>
			</button>
		</div>
		<a href="/${ application }/contact/">お問い合わせフォームはこちらから</a>
	</div>
</main>

<jsp:include page="../jsp/nodispFooter.jsp"></jsp:include>

<script src="../js/accordion_menu.js"></script>
<script src="../js/comon.js"></script>
</body>
</html>