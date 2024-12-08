<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
	<header>
		
		<nav>
			<span id="menu-btn" class="material-symbols-outlined">
				menu
			</span>
			<h1 id="logo">
				<img src="./logo.jpg">
			</h1>
			<a href="">
				<svg xmlns="http://www.w3.org/2000/svg" fill="currentColor" class="bi bi-cart" viewBox="0 0 16 16">
					<path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM3.102 4l1.313 7h8.17l1.313-7H3.102zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
				</svg>
			</a>
		</nav>
		<form action="/卒業制作/search" method="post">
			<input type="search" name="" placeholder="検索">
			<button>
				<span class="material-symbols-outlined">
					search
				</span>
			</button>
		</form>
		<div id="menu">
			<span id="close-btn" class="material-symbols-outlined">
				close
			</span>
			
			<c:choose>
				<c:when test="${ not empty user }">
					<a href="">アカウントサービス</a>
					<a href="">マイリスト</a>
					<a href="">注文履歴</a>
					<a href="">閲覧履歴</a>
					<a href="">お問い合わせ</a>
					<a href="">ログアウト</a>
				</c:when>
				<c:otherwise>
					<a href="">会員登録</a>
					<a href="">ログイン</a>
					<a href="">お問い合わせ</a>
				</c:otherwise>
			</c:choose>
		</div>
	</header>