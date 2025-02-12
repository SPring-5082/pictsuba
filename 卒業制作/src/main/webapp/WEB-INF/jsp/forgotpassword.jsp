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
<link href="./css/forgotpassword.css" rel="stylesheet">
<title><c:out value="${ application.replaceFirst('/','') } "/></title>
</head>
<body>

<jsp:include page="../../jsp/background.jsp"></jsp:include>

	<jsp:include page="../../jsp/header.jsp"/>
	<main>
		<div id="main_parent">
			<div id="main_window">
				<div id="window_top">
					<a href="${ application }/">
						<img src="./img/pi.kuro.l.png" alt="ロゴ">
					</a>
					<h2>確認URLを送信するメールアドレスを入力してください</h2>
				</div>
				<form id="sendURL_form" action="${ application }/forgotpassword" method="post" autocomplete="off">
					<input type="email" name="mail" placeholder="メールアドレス" required>
					<input type="submit" value="送信">
				</form>
				<a href="${ application }/signin/" class="back_anchor">戻る</a>
			</div>
		</div>
	</main>
	<jsp:include page="../../jsp/footer.jsp"/>
<script src="./js/comon.js"></script>
</body>
</html>