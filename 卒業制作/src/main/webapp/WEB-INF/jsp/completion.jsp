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
<link href="./css/completion.css" rel="stylesheet">
<title><c:out value="${ application }"/></title>
</head>
<body>
<jsp:include page="../../jsp/background.jsp"></jsp:include>
<jsp:include page="../../jsp/header.jsp"/>
<main>
	<div id="main_parent">
		<div id="main_box">
			<div id="main_top">
				<h2>購入完了</h2>
				<div class="progress-bar">
					<div class="circle completed">
						<span>&#10003;</span>
					</div>
					<div class="line completed"></div>
					<div class="circle completed">
						<span>&#10003;</span>
					</div>
					<div class="line completed"></div>
					<div class="circle completed">
						<span>&#10003;</span>
					</div>
				</div>
			</div>
			<div id="main_bottom">
				<p>
					決済が完了しました。<br>
					ご利用いただきありがとうございます。
				</p>
				<a href="/${ application }/">トップページへ戻る</a>
			</div>
		</div>
	</div>
</main>
<jsp:include page="../../jsp/footer.jsp"/>
<script src="./js/comon.js"></script>
</body>
</html>