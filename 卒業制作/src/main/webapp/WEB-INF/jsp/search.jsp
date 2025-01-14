<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0&" />
<link href="./img/sightIcon.jpg" rel="icon">
<link href="./css/comon.css" rel="stylesheet">
<link href="./css/search.css" rel="stylesheet">
<title><c:out value="${ application }"/></title>
</head>
<body>
	<jsp:include page="../../jsp/header.jsp"></jsp:include>
	<main>
		<div id="main_parent">
			<h2>"<c:out value="${ search }"/>"　の検索結果</h2>
			<div id="products_window">
				<div id="products">
					<c:forEach items="${ products }" var="product">
						<a href="/${ application }/product?productId=${ product.product_id() }">
							<div class="product">
								<div class="info">
									<h4 class="name"><c:out value="${ product.name() }"/></h4>
									<p class="creator"><c:out value="${ product.creator_name() }"/></p>
									<h4 class="price">\<c:out value="${ product.price() }"/></h4>
								</div>
								<div class="frame">
									<div class="draw_area">
									<img src="${ product.image() }" alt="絵画">
									</div>
								</div>
							</div>
						</a>
					</c:forEach>
				</div>
			</div>
		</div>
	</main>
	<jsp:include page="../../jsp/footer.jsp"></jsp:include>
</body>
</html>