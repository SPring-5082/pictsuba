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
<link href="./css/contact_comp.css" rel="stylesheet">
<link href="./css/comon.css" rel="stylesheet">
<title><c:out value="${ application.replaceFirst('/','') } お問い合わせ"/></title>
</head>
<body>

<jsp:include page="../../jsp/background.jsp"></jsp:include>

    <jsp:include page="../../jsp/header.jsp"></jsp:include>
    <main>
        <div id="main_parent">
            <div id="msg_box">
                <span class="material-symbols-outlined">
                    mail
                </span>
                <p>
                    送信が完了しました<br>
                    お問い合わせいただきありがとうございます。
                </p>
            </div>
            <a href="${ application }/" id="back_anchor">トップページに戻る</a>
        </div>
    </main>
    <jsp:include page="../../jsp/footer.jsp"></jsp:include>
    <script src="./js/comon.js"></script>
</body>
</html>