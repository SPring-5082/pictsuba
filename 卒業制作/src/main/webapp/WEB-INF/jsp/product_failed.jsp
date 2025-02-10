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
<link href="./css/mypage.css" rel="stylesheet">
<link>
<title><c:out value="${ application.replaceFirst('/','') }"/></title>
</head>
<body>
<jsp:include page="../../jsp/background.jsp"></jsp:include>

<main>

<h1>商品ないよ</h1>

</main>

<jsp:include page="../../jsp/header.jsp"></jsp:include>
<jsp:include page="../../jsp/footer.jsp"></jsp:include>
</body>
</html>