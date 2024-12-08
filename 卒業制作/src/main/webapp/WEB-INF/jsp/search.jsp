<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<link rel="stylesheet" href="./css/header.css">
<style>
	
	*{
		padding: 0;
		margin: 0;
	}
	
	.product{
		display: flex;
		border: solid 1px;
	}
	
	.product img{
		width: 35dvw;
		height:35dvw;
		border: solid 1px;
	}
	
	.overview{
		font-size: 5dvw;
	}
	
	
</style>
</head>
<body>
	<jsp:include page="../../html/header.html"></jsp:include>
	<main>
		<c:forEach items="${ list }" var="item">
			<div class="product">
				<img src="${ item.images() }">
				<div class="overview">
					<p><c:out value="${ item.name() }"></c:out></p>
					<p class="price"><c:out value="${ item.price() }"></c:out></p>
					<p class="point"><c:out value="${ item.point() }"></c:out></p>
					<a href="/卒業制作/product?productId=${ item.product_id() }">
						<button>買う</button>
					</a>
				</div>
			</div>	
		</c:forEach>
	</main>
</body>
</html>