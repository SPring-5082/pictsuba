<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0&" />
    <link href="./img/sightIcon.jpg" rel="icon">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css"/>
    <link href="./css/comon.css" rel="stylesheet">
    <link href="./css/index.css" rel="stylesheet">
    <title><c:out value="${ application } 絵の総合販売サイト"/></title>
</head>
<body>
    <jsp:include page="./jsp/header.jsp"></jsp:include>
    <main>
        <div id="main_parent">
            <div id="slide_show_window">
                <div class="swiper">
                  <div class="swiper-wrapper">
                    <c:forEach items="${ advertisements }" var="advertisement">
                    	<img class="swiper-slide" src="${ advertisement.image() }">
                    </c:forEach>
                  </div><!-- /swiper-wrapper -->
                </div><!-- /swiper -->
                <div class="arrow-box">
                  <div class="swiper-button-prev arrow"></div>
                  <div class="swiper-button-next arrow"></div>
                </div>
              <div class="swiper-pagination"></div>
            </div>

            <div id="products_window">
                <div id="products">
                    <c:choose>
                    <c:when test="${ not empty user }">
	                    <c:forEach items="${ recomends }" var="product">
		                    <a href="/${ application }/product?productId=${ product.product_id() }">
		                        <div class="product">
		                            <div class="info">
		                                <h4 class="name"><c:out value="${ product.name() }"/></h4>
		                                <p class="creator"><c:out value="${ product.creator_name() }"/></p>
		                                <h4 class="price">\<c:out value="${ calc.price(product.price(),product.category_id()) }"/></h4>
		                            </div>
		                            <div class="frame">
		                                <div class="draw_area">
		                                    <img src="${ product.image() }" alt="絵画">
		                                </div>
		                            </div>
		                        </div>
		                    </a>
	                    </c:forEach>
                    </c:when>
                    <c:otherwise>
						<c:forEach items="${ popularities }" var="product">
		                    <a href="/${ application }/product?productId=${ product.product_id() }">
		                        <div class="product">
		                            <div class="info">
		                                <h4 class="name"><c:out value="${ product.name() }"/></h4>
		                                <p class="creator"><c:out value="${ product.creator_name() }"/></p>
		                                <h4 class="price">\<c:out value="${ calc.price(product.price(),product.category_id()) }"/></h4>
		                            </div>
		                            <div class="frame">
		                                <div class="draw_area">
		                                    <img src="${ product.image() }" alt="絵画">
		                                </div>
		                            </div>
		                        </div>
		                    </a>
	                    </c:forEach>
                    </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </main>
    <jsp:include page="./jsp/footer.jsp"></jsp:include>
    <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
    <script src="./js/swiper.js"></script>
    <script src="./js/comon.js"></script>
</body>
</html>