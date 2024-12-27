<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<link href="../css/header.css" rel="stylesheet">
<header>
	<div id="hamburger">
		<span></span>
		<span></span>
		<span></span>
	</div>
	<a href="${ application }/top" id="header_logo">
		<img src="../img/logo.png">
	</a>
	<form action="" method="post">
		<input type="text" name="search" placeholder="検索">
		<button>
			<span class="material-symbols-outlined">
				search
			</span>
		</button>
	</form>
</header>
<script src="../js/header.js"></script>