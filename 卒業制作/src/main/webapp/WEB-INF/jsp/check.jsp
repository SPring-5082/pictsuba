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
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css"/>
<title><c:out value="${ application }"/></title>
</head>
<body>
	<c:forEach var="i" begin="0" end="${ products.size() - 1 }" step="1">
		<c:out value="${ products.get(i).name() }"/>:\
		<c:out value="${ products.get(i).price() }"/>　×　
		<c:out value="${ quantities.get(i) }"/>　=　
		<c:out value="${ products.get(i).price() * quantities.get(i) }"/>
		<br>
	</c:forEach>
	<c:forEach	items="${ addresses }" var="address">
		<c:out value="${ address.pref() }"/>
	</c:forEach>
	<c:forEach	items="${ cards }" var="card">
		<c:out value="${ card.blindNumber() }"/>
	</c:forEach>
</body>
</html>