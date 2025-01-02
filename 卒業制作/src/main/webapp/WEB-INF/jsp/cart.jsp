<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
<meta charset="UTF-8">
<title><c:out value="${ appication } カート"/></title>
</head>
<body>
	<c:forEach items="${ products }" var="product">
		
		<c:out value="${ product.name() }"/><br>
		
	</c:forEach>
</body>
</html>