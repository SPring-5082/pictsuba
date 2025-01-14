<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0&" />
    <link href="../img/sightIcon.jpg" rel="icon">
    <link href="../css/comon.css" rel="stylesheet">
    <link href="../css/contact.css" rel="stylesheet">
    <title><c:out value="${ application } お問い合わせ"/></title>
</head>
<body>
    <jsp:include page="../jsp/nodispHeader.jsp"></jsp:include>
    <main>
        <div id="main_parent">
            <div id="contact">
                <h2>お問い合わせフォーム</h2>
                <form id="contact_form" action="/${ application }/contact" method="post" autocomplete="off">
                    <label for="name">
                        <h4>お名前<span class="must">(必須)</span></h4>
                    </label>
                    <input class="input_area" type="text" id="name" name="name" placeholder="例: 山田 太郎" required><br>

                    <label for="email">
                        <h4>メールアドレス<span class="must">(必須)</span></h4>
                    </label>
                    <input class="input_area" type="email" id="email" name="mail" placeholder="例: example@example.com" required><br>
                    
                    <label for="message">
                        <h4>お問い合わせ内容<span class="must">(必須)</span></h4>
                    </label>
                    <textarea class="input_area" id="message" name="message" rows="5" placeholder="お問い合わせ内容を詳しくご記入ください" required></textarea><br>
                    
                    <input id="submit_btn" type="submit" value="送信">
                </form>
            </div>
        
            <h2>よくある質問（FAQ）</h2>
            <div id="faq">
                <button class="faq_box" onclick="toggleAccordion(0)">
                    <div class="summary">
                        <span class="material-symbols-outlined">
                            arrow_drop_down
                        </span>
                       <h3>Q: 商品の配送にどのくらい時間がかかりますか？</h3>
                    </div>
                    <div class="details">A: 通常、3～5営業日でお届けします。ただし、地域や配送状況により多少前後する場合がございます。</div>
                </button>

                <button class="faq_box" onclick="toggleAccordion(1)">
                    <div class="summary">
                        <span class="material-symbols-outlined">
                            arrow_drop_down
                        </span>
                        <h3>Q: 支払い方法は何がありますか？</h3>
                    </div>
                    <div class="details">A: クレジットカード、銀行振込、代引きなどがご利用いただけます。</div>
                </button>
        
                <button class="faq_box" onclick="toggleAccordion(2)">
                    <div class="summary">
                        <span class="material-symbols-outlined">
                            arrow_drop_down
                        </span>
                        <h3>Q: 返品や交換は可能ですか？</h3>
                    </div>
                    <div class="details">A: 商品到着後7日以内であれば可能です。詳細は返品ポリシーをご確認ください。</div>
                </button>
            </div>
        </div>
    </main>
    <jsp:include page="../jsp/nodispFooter.jsp"></jsp:include>
    <script src="../js/accordion_menu.js"></script>
    <script src="../js/comon.js"></script>
</body>
</html>