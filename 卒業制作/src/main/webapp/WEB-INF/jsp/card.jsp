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
		<c:when test="${ not empty cards }">
			<c:forEach items="${ cards }" var="card">
				<c:out value="${ card.owner_name() }"/>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<p>カード情報がありません</p><br>
			<a href="">カード情報を追加する</a>
		</c:otherwise>
	</c:choose>
</body>
</html>