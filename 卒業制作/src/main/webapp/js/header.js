let toggle_btn = document.getElementById("toggle-btn");
let menu = document.getElementById("menu");

window.onload = () =>{
	toggle_btn.textContent = "menu";
}



toggle_btn.onclick = () =>{
	
	menu.classList.toggle("open");
	toggle_btn.classList.toggle("open");
	if(toggle_btn.textContent == "menu"){
		toggle_btn.textContent = "close";
	}else{
		toggle_btn.textContent = "menu";
	};
};