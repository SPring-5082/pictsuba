<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
</body>
</html>