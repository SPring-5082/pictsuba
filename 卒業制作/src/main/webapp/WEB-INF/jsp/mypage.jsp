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
    <link href="./css/mypage.css" rel="stylesheet">
    <title><c:out value="${ application } マイページ"/></title>
</head>
<body>

<jsp:include page="../../jsp/background.jsp"></jsp:include>

    <jsp:include page="../../jsp/header.jsp"></jsp:include>
    <main>
        <div id="main_parent">
            <div id="top_box">
                <h2>マイページ</h2>
                <span class="material-symbols-outlined top_icon">
                    account_circle
                </span>
                <h4 id="account_name"><c:out value="${ user.name() }"/></h3>
            </div>
            <div id="bottom_box">
                <a href="/${ application }/account" class="menu_btn">
                    <span class="material-symbols-outlined bottom_icon">
                        manage_accounts
                    </span>
                    <h3>アカウント情報</h3>
                </a>
                <a href="/${ application }/favorite" class="menu_btn">
                    <span class="material-symbols-outlined bottom_icon">
                        favorite
                    </span>
                    <h3>お気に入り</h3>
                </a>
                <a href="/${ application }/order-history" class="menu_btn">
                    <span class="material-symbols-outlined bottom_icon">
                        menu_book
                    </span>
                    <h3>注文履歴</h3>
                </a>
                <a href="/${ application }/card" class="menu_btn">
                    <span class="material-symbols-outlined bottom_icon">
                        credit_card_gear
                    </span>
                    <h3>決済情報</h3>
                </a>
                <a href="/${ application }/address" class="menu_btn">
                    <span class="material-symbols-outlined bottom_icon">
                        house
                    </span>
                    <h3>住所情報</h3>
                </a>
                <a href="/${ application }/signout" class="menu_btn">
                    <span class="material-symbols-outlined bottom_icon">
                        logout
                    </span>
                    <h3>ログアウト</h3>
                </a>
            </div>
        </div>
    </main>
    <jsp:include page="../../jsp/footer.jsp"></jsp:include>
    <script src="./js/comon.js"></script>
</body>
</html>