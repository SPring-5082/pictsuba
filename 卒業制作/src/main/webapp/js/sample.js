//let bt = document.querySelectorAll(".add");
let bt = document.getElementsByClassName("add");
window.onload= () => {
	alert("js");
};

for(let i = 0;i < bt.length; i++){
	alert("f");
	bt[i].addEventListener('onclick',function(){
		alert("add");
	});
};



/*
bt.forEach(item =>{
	
	/*item.addEventListener('click', function() {
		footer.classList.toggle("open");
		menu_btn.classList.toggle("open");
		menu.classList.toggle("open");
		if(span.textContent == "menu"){
			span.textContent = "close";
		}else{
			span.textContent = "menu";
		}
	});*/
	/*item.onclick = () =>{
		alert("add");
	};*/
	//item.addEventListener('click', function() {
		//alert("add");
	//});
//});
