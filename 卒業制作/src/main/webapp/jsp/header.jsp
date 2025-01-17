<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<header>
    <div id="nav_parent">
        <div id="hamburger">
            <span></span>
            <span></span>
            <span></span>
        </div>
        <a href="/${ application }/" id="header_logo">
            <img src="./img/pi.kuro.l.png" alt="ロゴ">
        </a>
        <div id="search_box">
            <form method="post" id="search_product_form" action="/${ application }/search">
                <input id="search_word" name="word" type="text" placeholder="キーワードを入力" value="${ search }">
                <button id="search_btn">
                    <span class="material-symbols-outlined">
                        search
                    </span>
                </button>
            </form>
        </div>
        <!-- pc用サイト内リンク -->
        <div id="pc_menu">
            <a href="/${ application }/cart" class="menu_icon"><img class="nav_icon" src="./img/UI.ks-to.png" alt="カート"></a>
            <c:choose>
            <c:when test="${ empty user }">
            	<a href="/${ application }/signin/" class="menu_icon"><img class="nav_icon" src="./img/UIroguin.png" alt="ログイン"></a>
            </c:when>
            <c:otherwise>
            	<a href="/${ application }/signout" class="menu_icon"><img class="nav_icon" src="./img/UIroguin.png" alt="ログイン"></a>
            </c:otherwise>
            </c:choose>
            <a href="/${ application }/mypage" class="menu_icon"><img class="nav_icon" src="./img/UI.account.png" alt="マイページ"></a>
        </div>
        <!-- モバイル用サイト内リンク -->
        <div id="mobile_menu">
            <a href="/${ application }/cart">カート</a>
           	<c:choose>
            <c:when test="${ empty user }">
            	<a href="/${ application }/signin/">ログイン</a>
            </c:when>
            <c:otherwise>
            	<a href="/${ application }/signout">ログアウト</a>
            </c:otherwise>
            </c:choose>
            <a href="/${ application }/mypage">マイページ</a>
            <a href="/${ application }/contcact.html">お問い合わせ</a>
        </div>
        <a href="/${ application }/cart" id="mobile_icon" class="cart menu_icon"><img src="./img/UI.ks-to.png" alt="カート"></a>
        <div id="bg_cover"></div>
    </div>
</header>