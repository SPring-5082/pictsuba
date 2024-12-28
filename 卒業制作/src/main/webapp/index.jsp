<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${ application }</title>
</head>
<body>
	<c:choose>
		<c:when test="${ not empty user }">
			<p>購入履歴に基づくおすすめ</p>
			<c:forEach items="${ recomends }" var="product">
				<c:out value="${ product.name() }"/>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<p>売上量TOPのおすすめ</p>
			<c:forEach items="${ popularities }" var="product">
				<c:out value="${ product.name() }"/>
			</c:forEach>
		</c:otherwise>
	</c:choose>
</body>
</html>