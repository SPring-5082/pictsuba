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
<link href="./css/address_payment_info.css" rel="stylesheet">
<title><c:out value="${ application } 住所"/></title>
</head>
<body>
<jsp:include page="../../jsp/header.jsp"></jsp:include>
<main>
<div id="main_parent">
	<h2>住所情報</h2>
	<div id="main_window">
		<div class="section">
			<div class="section_title">
				<span class="material-symbols-outlined">
					house
				</span>
				<h3>住所</h3>
			</div>
			<c:forEach items="${ addresses }" var="address">
				<div class="address items">
					<div class="item">
						<div class="item_info">
							〒<c:out value="${ address.zip_code(address.zip_code()) }"/><br>
							<c:out value="${ address.pref() }"/> <c:out value="${ address.municipalities() }"/><br>
							<c:out value="${ address.street() }"/>　<c:out value="${ address.building() }"/>
						</div>
						<button class="delete_button">削除</button>
					</div>
				</div>
			</c:forEach>
			<button id="add_button">
				新しい住所を追加
				<span class="material-symbols-outlined">
					add
				</span>
			</button>
		</div>
		<!-- 住所入力フォーム -->
		<div id="modal_window">
			<div id="modal_content">
				<span class="material-symbols-outlined" id="close_icon">
					close
				</span>
				<h2>住所の追加</h2>
				<form action="" id="address_form" class="add_form">
					<div class="input_box">
						<label for="zip_code"><h4>郵便番号<span class="must">*</span></h4></label>
						<div id="address_completion_area">
							<input type="number" name="zip_code" id="zip_code" placeholder="ハイフンなし" maxlength="7" required>
							<button id="address_completion_btn">住所検索</button>
						</div>
						<p id="error_msg"></p>
					</div>	
					<div class="input_box">
						<label for="pref"><h4>都道府県<span class="must">*</span></h4></label>
						<input type="text" name="pref" id="pref" required>
					</div>
					<div class="input_box">
						<label for="municipalities"><h4>市区町村<span class="must">*</span></h4></label>
						<input type="text" name="municipalities" id="municipalities" required>
					</div>
					<div class="input_box">
						<label for="street"><h4>番地<span class="must">*</span></h4></label>
						<input type="text" name="street" required>
					</div>
					<div class="input_box">
						<label for="building"><h4>建物名</h4></label>
						<input type="text" name="building">
					</div>
					<input type="submit" value="追加">
				</form>
			</div>
		</div>
	</div>
	<a href="/${ application }/mypage" id="back_anchor">マイページへ</a>
</div>
</main>
<jsp:include page="../../jsp/footer.jsp"></jsp:include>
<script src="./js/modal_window.js"></script>
<script src="./js/zipcode.js"></script>
<script src="./js/comon.js"></script>
</body>
</html>