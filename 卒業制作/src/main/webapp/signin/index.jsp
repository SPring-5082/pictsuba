<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><c:out value="${ application }"/></title>
<style>
	#error_message{
		color: red;
	}
	
</style>
</head>
<body>
	<form action="/卒業制作/signin" method="post">
		<c:if test="${ error }">
			<p id="error_message">入力情報に誤りがあります</p>
		</c:if>
		<input type="email" name="mail">
		<input type="password" name="password">
		<input type="hidden" name="page" value="${ page }">
		<input type="submit" name="ログイン">
		<c:out value="${ page }"></c:out>
	</form>
</body>
</html>