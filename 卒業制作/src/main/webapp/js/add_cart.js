//カート内商品の削除

function add_cart(product_id){
	
	//サーバーサイドに送る
	
	let xhr = new XMLHttpRequest();
	
	// //サーバーサイドに送るデータ
	let req_data = `product_id=${product_id}`;
	
	xhr.open('POST', '/pictsuba/api/cart');
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	
	xhr.send(req_data);
}