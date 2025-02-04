<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0&" />
    <link href="./img/sightIcon.jpg" rel="icon">
    <link href="./css/comon.css" rel="stylesheet">
    <link href="./css/account.css" rel="stylesheet">
<title><c:out value="${ application } アカウント情報"/></title>
</head>
<body>

<jsp:include page="../../jsp/background.jsp"></jsp:include>

	<jsp:include page="../../jsp/header.jsp"></jsp:include>
	<main>
        <div id="main_parent">
            <h2>アカウント情報</h2>
            <span class="material-symbols-outlined acoount_icon">
                account_circle
            </span>
                
            <div id="info_window">
                <a id="edit_btn" href="/${ application }/account?edit=do">編集</a>

                お名前
                <div class="info_box"><c:out value="${ user.name() }"/></div>

                パスワード
                <div class="info_box"><c:out value="${ user.blindPassword() }"/></div>

                電話番号
                <div class="info_box"><c:if test="${ not empty user.phone() }"><c:out value="${ user.phone() }"/></c:if>&nbsp;</div>

                メールアドレス
                <div class="info_box"><c:out value="${ user.mail() }"/></div>

                年齢
                <div class="info_box"><c:if test="${ user.age() >= 0 }"><c:out value=""/>${ user.age() }</c:if>&nbsp;</div>

                誕生日
                <div class="info_box"><c:if test="${ not empty user.birth_day() }"><c:out value="${ user.birth_day() }"/></c:if>&nbsp;</div>

                性別
                <div class="info_box">
                	<c:choose>
                		<c:when test="${ user.gender() eq 'male'}">男</c:when>
                		<c:when test="${ user.gender() eq 'female'}">女</c:when>
                		<c:when test="${ user.gender() eq 'nothing'}">回答しない</c:when>
                		<c:otherwise>	
                			&nbsp;
                		</c:otherwise>
                	</c:choose>
                </div>
            </div>
        </div>
    </main>
	<jsp:include page="../../jsp/footer.jsp"></jsp:include>
<script src="./js/comon.js"></script>
</body>
</html>