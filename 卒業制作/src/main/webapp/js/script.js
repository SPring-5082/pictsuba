let menu_btn = document.getElementById("menu-btn");
let close_btn = document.getElementById("close-btn");
let menu = document.getElementById("menu");
let advaList = document.getElementsByClassName("advertisement");
let current = 0;
let next =1;
let max = advaList.length;

window.onload = () =>{
	
	
	advaList[0].classList.toggle("open");
	adva();
	
	
};

function adva(){
	setTimeout(nextView, 5000);
};



function nextView() {
    advaList[current].classList.remove("open");
    current = (current + 1) % max; // 現在のインデックスを次に進める
    advaList[current].classList.add("open");
    adva(); // 再度次のビューを表示するタイマーをセット
}



menu_btn.onclick = () =>{
	menu.classList.toggle("open");
	menu_btn.classList.toggle("open");
};

close_btn.onclick = () =>{
	menu.classList.toggle("open");
	menu_btn.classList.toggle("open");
};
