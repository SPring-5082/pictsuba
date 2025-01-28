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
    <link href="./css/comon.css" rel="stylesheet">
    <link href="./css/product_details.css" rel="stylesheet">
    <title><c:out value="${ application } ${ product.name() }"/></title>
</head>
<body>
    <jsp:include page="../../jsp/header.jsp"></jsp:include>
     <main>
        <div id="main_parent">
            <div id="product_window">
                <div class="pictura">
                    <div class="frame">
                        <div class="draw_area">
                            <img src="${ product.image() }" alt="絵画">
                        </div>
                    </div>
                    <button id="favorite_icon">
                        <span class="material-symbols-outlined">
                            favorite
                        </span>
                    </button>
                </div>
                <div class="info">
                    <h4 class="name"><c:out value="${ product.name() }"/></h4>
                    <p class="creator"><c:out value="${ product.creator_name() }"/></p>
                    <h4 class="price">\ <c:out value="${ calc.price(product.price(),product.category_id()) }"/></h4>
                    <p class="description">
                    	<c:out value="${ product.descryption() }"/>
                    </p>
                </div>
                <button class="in_cart_btn" onclick="add_cart(${ product.product_id()})">カートへ入れる</button>
            </div>
        </div>
    </main>
    <jsp:include page="../../jsp/footer.jsp"></jsp:include>
    <script>
        const favorite_icon = document.getElementById("favorite_icon");

        favorite_icon.addEventListener("click", () => {            
            favorite_icon.classList.toggle("click");
        })
    </script>
    <script src="./js/comon.js"></script>
    <script src="./js/add_cart.js"></script>
</body>
</html>