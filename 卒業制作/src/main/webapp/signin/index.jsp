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
<link href="../css/login.css" rel="stylesheet">
<title><c:out value="${ application.replaceFirst('/','') } ログイン"/></title>
</head>
<body>
<jsp:include page="../jsp/nodispbackground.jsp"></jsp:include>

	<main>
		<div id="main_parent">
			<div id="main_window">
				<div id="window_top">
				<a href="${ application }/">
					<img src="../img/pi.kuro.l.png" alt="ロゴ">
				</a>
				<h2>ログイン</h2>
				</div>
				<form id="login_form" action="${ application }/signin" method="post" autocomplete="off">
					<c:if test="${ not empty param.page }"><input type="hidden" name="page" value="${ param.page }"></c:if>
					<c:if test="${ param.error }"><p style="color:red;"><c:out value="※入力情報に誤りがあります"/></p></c:if>
					<label for="mail">メールアドレス</label>
					<input type="email" name="mail" required>
					
					<label for="password">パスワード</label>
					<div class="password_area">
						<input type="password" name="password" required id="password">
						<span class="material-symbols-outlined eye_icon" id="eye_icon">
							visibility_off
						</span>
					</div>
					<a href="${ application }/forgotpassword" class="anchor">パスワードをお忘れですか？</a>
					
					<input type="submit" value="ログイン">
					<a href="${ application }/send_mail">アカウントの作成</a>
				</form>
			</div>
		</div>
	</main>
<script src="../js/comon.js"></script>
<script src="../js/login.js"></script>
</body>
</html>