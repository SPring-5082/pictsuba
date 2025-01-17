const address_completion_btn = document.getElementById("address_completion_btn");
const close_icon = document.getElementById("close_icon");
const modal_content = document.getElementById("modal_content");
let zip_code = document.getElementById("zip_code");
const error_msg = document.getElementById("error_msg");

close_icon.addEventListener("click", (event) => {
    error_msg.innerText = "";
});
modal_window.addEventListener("click", () => {
    error_msg.innerText = "";
});

address_completion_btn.addEventListener("click", () => {

    let xhr = new XMLHttpRequest();
    xhr.open('GET', 'https://zip-cloud.appspot.com/api/search?zipcode=' + zip_code.value);

    xhr.onload = function() {
    if(xhr.status === 200){
        // レスポンス取得成功
        let respData = JSON.parse(xhr.responseText);
        console.log(respData.message);
        
        if(respData.status === 400){
            error_msg.innerText = "郵便番号から住所が検索できませんでした。";
            return;
        }
        
        // JSONデータの処理
        error_msg.innerText = "";
        let respaddress = respData.results[0];
        document.getElementById("pref").value = respaddress.address1;
        document.getElementById("municipalities").value = respaddress.address2 + respaddress.address3;
    }
    };

    xhr.send();
});