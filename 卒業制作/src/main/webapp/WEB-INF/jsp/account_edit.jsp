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
<link href="./css/account_info_edit.css" rel="stylesheet">
<title><c:out value="${ application } アカウント情報"/></title>
</head>
<body>
	<jsp:include page="../../jsp/header.jsp"></jsp:include>
	
	<main>
        <div id="main_parent">
            <h2>アカウント情報 編集</h2>
                
            <div id="info_window">
                <form id="info_form" action="/${ application }/account" method="post" autocomplete="off">
                    <label for="name"><h4>お名前</h4></label>
                    <input type="text" name="name" class="info_box" value="${ user.name() }" required="required">

                    <label for="password"><h4>パスワード</h4></label>
                    <input type="password" name="password" class="info_box" value="${ user.decryptPassword() }" required="required">

                    <label for="phone"><h4>電話番号</h4></label>
                    <input type="tel" name="phone" class="info_box" value="${ user.phone() }">
                    
                    <label for="mail"><h4>メールアドレス</h4></label>
                    <input type="email" name="mail" class="info_box" value="${ user.mail() }" required="required">

                    <label for="age"><h4>年齢</h4></label>
                    <c:choose>
                    	<c:when test="${ user.age() > 0 }">
                    		<input type="number" name="age" min="0" class="info_box" value="${ user.age() }">
                    	</c:when>
                    	<c:otherwise>
                    		<input type="number" name="age" min="0" class="info_box">
                    	</c:otherwise>
                    </c:choose>

                    <label for="barth_day"><h4>誕生日</h4></label>
                    <input type="date" name="birth_day" class="info_box" value="${ user.birth_day().toString() }" max="${ user.first_log().toString() }">

                    <label for="gender"><h4>性別</h4></label>
                    <select name="gender" class="info_box">
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
                    <input type="submit" id="submit_btn">
                </form>
            </div>
            <a id="back_anchor" href="/${ application }/account">戻る</a>
        </div>
    </main>
	
	<jsp:include page="../../jsp/footer.jsp"></jsp:include>
</body>
</html>