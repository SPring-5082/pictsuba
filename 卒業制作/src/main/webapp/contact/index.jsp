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
		<div id="contact">
			<h2>お問い合わせフォーム</h2>
			<form id="contact_form" action="/${ application }/contact">
				<label for="name">
					<h4>お名前<span class="must">(必須)</span></h4>
				</label>
				<input class="input_area" type="text" id="name" name="name" placeholder="例: 山田 太郎" required><br>
				
				<label for="email">
					<h4>メールアドレス<span class="must">(必須)</span></h4>
				</label>
				<input class="input_area" type="email" id="email" name="mail" placeholder="例: example@example.com" required><br>
				
				<label for="message">
					<h4>お問い合わせ内容<span class="must">(必須)</span></h4>
				</label>
				<textarea class="input_area" id="message" name="message" rows="5" placeholder="お問い合わせ内容を詳しくご記入ください" required></textarea><br>
				
				<input id="submit_btn" type="submit" value="送信">
			</form>
		</div>
		<a href="/${ application }/faq/">よくあるご質問はこちらから</a>
	</div>
</main>

<jsp:include page="../jsp/nodispFooter.jsp"></jsp:include>
<script src="../js/comon.js"></script>
</body>
</html>