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
    <link href="../css/signup.css" rel="stylesheet">
    <title><c:out value="${ applicaiotn } 新規登録"/></title>
</head>
<body>
<jsp:include page="../jsp/nodispbackground.jsp"></jsp:include>

	<main>
		<div id="main_parent">
			<div id="main_window">
				<div id="window_top">
                    <a href="./index.html">
                        <img src="../img/pi.kuro.l.png" alt="ロゴ">
                    </a>
                    <h2>新規登録</h2>
                </div>
				<form id="info_form" action="${ application }/signup" method="post" autocomplete="off">
					<c:if test="${ not empty param.error }"><p style="color: red;">*登録に失敗しました<br>メールアドレスは既に使用されている可能性があります</p></c:if>
					<div class="input_box">
						<label for="name"><h4>名前<span class="must">*</span></h4></label>
						<input type="text" name="name" required="required" autocomplete="off">
					</div>
		
					<div class="input_box">
						<label for="password"><h4>パスワード<span class="must">*</span></h4></label>
						<div class="password_area">
							<input type="password" name="password" required="required" autocomplete="off" pattern="[a-zA-Z0-9!@#$%^&*()_+=\-]{8,16}" id="password">
							<span class="material-symbols-outlined eye_icon" id="eye_icon">
								visibility_off
							</span>
						</div>
						<p id="password_error" class="error_msg"></p>
					</div>

					<div class="input_box">
						<label for="check_password"><h4>パスワード（確認用）<span class="must">*</span></h4></label>
						<div class="password_area">
							<input type="password" name="check_password" required="required" autocomplete="off" pattern="[a-zA-Z0-9!@#$%^&*()_+=\-]{8,16}" id="check_password">
							<span class="material-symbols-outlined eye_icon" id="check_eye_icon">
								visibility_off
							</span>
						</div>
						<p id="check_password_error" class="error_msg"></p>
						<p id="match_error" class="error_msg"></p>
					</div>
					
					<div class="input_box">
						<label for="mail"><h4>メールアドレス<span class="must">*</span></h4></label>
						<input type="email" name="mail" required="required" autocomplete="off">
					</div>
					
					<div class="input_box">
						<label for="phone"><h4>電話番号</h4></label>
						<input type="tel" name="phone" placeholder="ハイフンなし" autocomplete="off" pattern="[0-9]{10,11}" id="phone">
						<p id="tel_error" class="error_msg"></p>
					</div>
					
					<div class="input_box">
						<label for="barth_day"><h4>誕生日</h4></label>
						<input type="date" name="birth_day" class="info_box" autocomplete="off" min="1900-01-01" max="${ today.toString() }" id="birth_day">
						<input type="hidden" name="age" id="age">
					</div>
					
					<div class="input_box">
						<label for="gender"><h4>性別</h4></label>
						<select name="gender" autocomplete="off">
							<option selected="selected" disabled="disabled">-</option>
							<option value="male">男</option>
							<option value="female">女</option>
							<option value="nothing">回答しない</option>
						</select>
					</div>
					
					<input type="submit" id="submit_btn">
					<a href="${ application }/signin/">アカウントをお持ちの方</a>
				</form>
			</div>
		</div>
	</main>
<script src="../js/comon.js"></script>
<script src="../js/signup.js"></script>
</body>
</html>