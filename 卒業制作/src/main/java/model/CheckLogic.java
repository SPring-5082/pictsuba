package model;

import java.util.ArrayList;
import java.util.List;

import beans.Cart;
import beans.Customer;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class CheckLogic {
	/**
	 * カートページで入力された購入情報を取得、整理し
	 * 確定されたカート情報として返す
	 * @param request リクエスト情報
	 * @return 確定カート情報
	 */
	public static List<Cart> execute(HttpServletRequest request){
		HttpSession session = request.getSession();
		Customer user = (Customer)session.getAttribute("user");
		//カートにあるかつ今回購入予定の商品リスト
		List<Integer> idList = new ArrayList<Integer>();
		request.getParameterNames().asIterator().forEachRemaining(element -> {
			if(element.indexOf("product_id:") >= 0) {
				idList.add(Integer.parseInt(element.replaceFirst("product_id:", "")));
			}
		});
		List<Cart> cart = new ArrayList<>();
		idList.forEach(id -> {
			cart.add(new Cart(user.customer_id(), id, Integer.parseInt(request.getParameter("quantity:" + id))));
		});
		
		return cart;
	}
}
