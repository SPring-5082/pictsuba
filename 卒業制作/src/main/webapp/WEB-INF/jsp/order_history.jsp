<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0&" />
<link href="./img/sightIcon.jpg" rel="icon">
<link href="./css/comon.css" rel="stylesheet">
<link href="./css/order_history.css" rel="stylesheet">
<title><c:out value="${ application.replaceFirst('/','') } 注文履歴"/></title>
</head>
<body>

<jsp:include page="../../jsp/background.jsp"></jsp:include>
<jsp:include page="../../jsp/header.jsp"></jsp:include>	   


 <main>
	<div id="main_parent">
		<h2>注文履歴</h2>	
		<div id="main_window">
			<div id="products" class="view">
				<c:forEach items="${ orders }" var="order">
					<a href="${ application }/order-details?order_id=${ order.order_id() }" class="product">
						<div class="frame">
							<div class="draw_area">
								<img src="${ order.product_image() }" alt="絵画">
							</div>
						</div>
						<div class="info">
							<h3 class="state"><c:out value="${ order.state() }"/></h3>
							<div class="info_bottom">
							<p>注文日:<c:out value="${ order.order_date() }"/></p>
						</div>
						</div>
					</a>
				</c:forEach>
			</div>
			<!-- 注文履歴が空のとき -->
			<div id="is_null">
				<span class="material-symbols-outlined" id="book_icon">
					menu_book
				</span>
				<h3>注文履歴はありません</h3>
			</div>
		</div>
	</div>
</main>


<jsp:include page="../../jsp/footer.jsp"></jsp:include>
<script src="./js/comon.js"></script>
<script src="./js/check_null.js"></script>
<script src="./js/order_history_onload.js"></script>
</body>
</html>