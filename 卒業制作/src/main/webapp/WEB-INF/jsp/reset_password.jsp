<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0&" />
<link href="./img/sightIcon.jpg" rel="icon">
<link href="./css/comon.css" rel="stylesheet">
<link href="./css/reset_password.css" rel="stylesheet">
<title><c:out value="${ application }"/></title>
</head>
<body>

<jsp:include page="../../jsp/background.jsp"></jsp:include>

	<jsp:include page="../../jsp/header.jsp"/>
	<main>
		<div id="main_parent">
			<div id="main_window">
				<div id="window_top">
					<a href="/${ application }/">
						<img src="./img/pi.kuro.l.png" alt="ロゴ">
					</a>
					<h2>パスワード再設定</h2>
				</div>
				<form id="reset_password_form" action="/${ application }/reset-pass" method="post">
					
					<label for="password"><h4>パスワード<span class="must">*</span></h4></label>
					<div class="password_area">
						<input type="password" name="password" required="required" autocomplete="off" pattern="[a-zA-Z0-9!@#$%^&*()_+=\-]{8,16}" id="password">
						<span class="material-symbols-outlined eye_icon" id="eye_icon">
							visibility_off
						</span>
					</div>
					<p id="password_error" class="error_msg"></p>
					
					
					
					<label for="check_password"><h4>パスワード（確認用）<span class="must">*</span></h4></label>
					<div class="password_area">
						<input type="password" name="check_password" required="required" autocomplete="off" pattern="[a-zA-Z0-9!@#$%^&*()_+=\-]{8,16}" id="check_password">
						<span class="material-symbols-outlined eye_icon" id="check_eye_icon">
							visibility_off
						</span>
					</div>
					<p id="check_password_error" class="error_msg"></p>
					<p id="match_error" class="error_msg"></p>
					
					
					
					
					<input type="hidden" name="token" value="${ token }">
					<input type="submit" value="登録">
				</form>
			</div>
		</div>
	</main>
	<jsp:include page="../../jsp/footer.jsp"/>
<script src="./js/comon.js"></script>
<script src="./js/reset_password.js"></script>
</body>
</html>