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
<link href="./css/account_edit.css" rel="stylesheet">
<title><c:out value="${ application } アカウント情報"/></title>
</head>
<body>
<jsp:include page="../../jsp/background.jsp"></jsp:include>

	<jsp:include page="../../jsp/header.jsp"></jsp:include>
	
	<main>
        <div id="main_parent">
            <h2>アカウント情報 編集</h2>
                
            <div id="info_window">
                <form id="info_form" action="/${ application }/account" method="post" autocomplete="off">
                    <div class="input_box">
						<label for="name"><h4>お名前</h4></label>
                    	<input id="name" type="text" name="name" value="${ user.name() }" required="required">
					</div>
                    
                    
                    <div class="input_box">
						<label for="password"><h4>パスワード<span class="must">*</span></h4></label>
						<div class="password_area">
							<input value="${ user.decryptPassword() }" type="password" name="password" required="required" autocomplete="off" pattern="[a-zA-Z0-9!@#$%^&*()_+=\-]{8,16}" id="password">
							<span class="material-symbols-outlined eye_icon" id="eye_icon">
								visibility_off
							</span>
						</div>
						<p id="password_error" class="error_msg"></p>
					</div>

					<div class="input_box">
						<label for="check_password"><h4>パスワード（確認用）<span class="must">*</span></h4></label>
						<div class="password_area">
							<input value="${ user.decryptPassword() }" type="password" name="check_password" required="required" autocomplete="off" pattern="[a-zA-Z0-9!@#$%^&*()_+=\-]{8,16}" id="check_password">
							<span class="material-symbols-outlined eye_icon" id="check_eye_icon">
								visibility_off
							</span>
						</div>
						<p id="check_password_error" class="error_msg"></p>
						<p id="match_error" class="error_msg"></p>
					</div>
                    
                    
                    <div class="input_box">
                    	<label for="phone"><h4>電話番号</h4></label>
                    	<input id="phone" type="tel" placeholder="ハイフンなし" name="phone"  value="${ user.phone() }" pattern="[0-9]{10,11}">
                    	<p id="tel_error" class="error_msg"></p>
                    </div>
                    
                    
                    <div class="input_box">
                    	<label for="mail"><h4>メールアドレス</h4></label>
	                    <input type="email" name="mail" value="${ user.mail() }" required="required">
                    </div>
                    
                    <div class="input_box">
                    	<label for="age"><h4>年齢<span class="must">　*自動入力</span></h4></label>
	                    <c:choose>
	                    	<c:when test="${ user.age() > 0 }">
	                    		<input id="age" type="number" name="age" min="0" value="${ user.age() }" readonly="readonly">
	                    	</c:when>
	                    	<c:otherwise>
	                    		<input id="age" type="number" name="age" min="0" readonly="readonly">
	                    	</c:otherwise>
	                    </c:choose>
                    </div>
                    

                    
                    <div class="input_box">
                    	<label for="barth_day"><h4>誕生日</h4></label>
	                    <input id="birth_day" type="date" name="birth_day" value="${ user.birth_day().toString() }" min="1900-01-01" max="${ user.first_log().toString() }">
                    </div>
                    
                    
                    <div class="input_box">
                    	<label for="gender"><h4>性別</h4></label>
	                    <select name="gender">
	                    	<c:if test="${ empty user.gender() }"><option value="" selected="selected" disabled="disabled">-</option></c:if>
	                        <c:choose>
	                        	<c:when test="${ user.gender() eq 'male' }">
	                        		<option value="male" selected="selected">男</option>
	                        	</c:when>
	                        	<c:otherwise>
	                        		<option value="male">男</option>
	                        	</c:otherwise>
	                        </c:choose>
	                        <c:choose>
	                        	<c:when test="${ user.gender() eq 'female' }">
	                        		<option value="female" selected="selected">女</option>
	                        	</c:when>
	                        	<c:otherwise>
	                        		<option value="female">女</option>
	                        	</c:otherwise>
	                        </c:choose>
	                        <c:choose>
	                        	<c:when test="${ user.gender() eq 'nothing' }">
	                        		<option value="nothing" selected="selected">回答しない</option>
	                        	</c:when>
	                        	<c:otherwise>
	                        		<option value="nothing">回答しない</option>
	                        	</c:otherwise>
	                        </c:choose>
	                    </select>
                    </div>
                    
                    <input type="submit" id="submit_btn">
                </form>
            </div>
            <a id="back_anchor" href="/${ application }/account">戻る</a>
        </div>
    </main>
	
	<jsp:include page="../../jsp/footer.jsp"></jsp:include>
<script src="./js/comon.js"></script>
<script src="./js/signup.js"></script>
</body>
</html>