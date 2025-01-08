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
    <link href="./css/product_details.css" rel="stylesheet">
    <title>チームA</title>
</head>
<body>
    <jsp:include page="../../jsp/header.jsp"></jsp:include>
    <main>
        <div id="main_parent">
            <div id="product_window">
                <div class="pictura">
                    <img src="img/gakubuti.png" alt="額縁">
                    <button id="favorite_icon">
                        <span class="material-symbols-outlined">
                            favorite
                        </span>
                    </button>
                </div>
                <div class="info">
                    <h4 class="name">タイトル名</h4>
                    <p class="creator">作者名</p>
                    <h4 class="price">￥値段</h4>
                    <p class="description">
                        text text text text text text text text text text text text
                        text text text text text text text text text text text text
                    </p>
                </div>
                <button class="in_cart_btn">カートへ入れる</button>
            </div>
        </div>
    </main>
    <footer>
        <div id="footer_parent">
            <div class="content">
                <a href="./index.html" id="logo_footer">
                    <img src="../img/pi.kuro.l 1.png" alt="フッターのロゴ">
                </a>
                <a href="./registration.html" class="footer_link">新規会員登録</a>
                <a href="./contact.html" class="footer_link">お問い合わせ</a>
                <a href="./about_us.html" class="footer_link">会社概要</a>
                <a href="./rule.html" class="footer_link">利用規約</a>
                <a href="./privacy_policy.html" class="footer_link">プライバシーポリシー</a>
            </div>
            <p id="copy_right">&copy;pictsuba.com</p>
        </div>
    </footer>
    <script>
        const favorite_icon = document.getElementById("favorite_icon");

        favorite_icon.addEventListener("click", () => {            
            favorite_icon.classList.toggle("click");
        })
    </script>
    <script src="../js/comon.js"></script>
</body>
</html>