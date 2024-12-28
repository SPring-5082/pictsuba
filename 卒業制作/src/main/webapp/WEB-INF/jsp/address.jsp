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
		<c:when test="${ not empty addresses }">
			<c:forEach items="${ addresses }" var="address">
				<c:out value="${ address.pref() }"/>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<p>住所情報がありません</p><br>
			<a href="">住所情報を追加する</a>
		</c:otherwise>
	</c:choose>
</body>
</html>