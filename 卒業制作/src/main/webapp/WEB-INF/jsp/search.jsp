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
	<c:choose>
		<c:when test="${ not empty products }">
			<c:forEach items="${ products }" var="product">
				<c:out value="${ product.name() }"/>
			</c:forEach>
		</c:when>
		<c:otherwise>
			検索結果が得られませんでした
		</c:otherwise>
	</c:choose>
	
</body>
</html>