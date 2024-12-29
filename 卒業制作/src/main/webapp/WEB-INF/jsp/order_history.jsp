<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><c:out value="${ application } 注文履歴"/></title>
</head>
<body>
	<p>注文履歴</p>
	<c:choose>
		<c:when test="${ not empty orders || not empty order_histories }">
			<c:forEach items="${ orders }" var="order">
				<c:out value="${ order.product_id() }"/>
			</c:forEach>
			<c:forEach items="${ order_histories }" var="order_history">
				<c:out value="${ order_history.product_id() }"/>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<p>取得失敗したか、注文履歴が存在しません</p>
		</c:otherwise>
	</c:choose>
</body>
</html>