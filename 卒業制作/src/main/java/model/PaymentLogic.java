package model;

import java.sql.SQLException;
import java.util.List;

import beans.Cart;
import beans.Customer;
import beans.Product;
import dao.CustomerDAO;
import dao.ProductDAO;

public class PaymentLogic {
	/**
	 * 注文の総額を算出し、使用ポイントを差し引いた額を
	 * 支払うメソッド
	 * @param user 注文者情報
	 * @param carts 注文内容
	 * @param card_id カードID
	 * @param use_point ポイント使用量
	 * @return 実行の成否
	 */
	public static boolean execute(Customer user, List<Cart> carts, int card_id, int use_point){
		try {
			int price = 0;
			int add_point = 0;
			for(Cart cart : carts) {
				Product product = ProductDAO.findById(cart.product_id());
				price += product.price();
				add_point += product.point();
			}
			int final_price = price - use_point;
			/*
			 * 
			 * ここで支払い処理は正常に行われたものとする
			 * 
			 */
			int customer_id = user.customer_id();
			CustomerDAO.updateCard_id(customer_id, card_id);
			CustomerDAO.updatePoint(customer_id, user.point()-use_point+add_point);
			return true;
		}catch (SQLException e) {
			return false;
		}
		
		
		
		
		
		
	}
	
}
