<%@page import="test.Advertisement"%>
<%@page import="test.Products"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<link rel="stylesheet" href="./css/header.css">
<style>
	*{
		padding: 0;
		margin:0;
	}
	
	
	main{
		width: 100dvw;
		height: 100dvh;
	}
	
	.advertisement{
		width: 100dvw;
		display: none;
	}
	
	.advertisement.open{
		width: 100dvw;
		display: block;
	}
	
	
	
	.popularity{
		display: flex;
		flex-direction: row;
		flex-wrap: wrap;
		justify-content: space-evenly;
	}
	
	.popularity h1{
		text-align: left;
		width: 90dvw;
		margin-left: 10dvw;
		margin-right: 10dvw;
		margin-top: 2dvh;
		margin-bottom: 2dvh;
		font-size: 5dvw;
	}
	
	.popularity img{
		width: 40dvw;
		border: solid 1px;
		border-color: #999999;
	}
</style>
</head>
<body>
	<jsp:include page="../../jsp/header.jsp"></jsp:include>
	<main>
	
		<div id="advertisements">
			<c:forEach items="${ alist }" var="item">
				<img class="advertisement" src="${ item.img }">
			</c:forEach>
		</div>
		
		<div class="popularity">
			<h1>人気の商品</h1>
			<c:forEach items="${ plist }" var="item">
				<a href="">
					<img src="${ item.img }">
				</a>
			</c:forEach>
		</div>
		
	</main>
	<script src="./js/script.js"></script>
</body>
</html>