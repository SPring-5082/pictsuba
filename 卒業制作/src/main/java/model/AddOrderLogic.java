package model;

import java.sql.SQLException;
import java.util.List;

import beans.Cart;
import beans.Order;
import beans.Product;
import dao.CustomerDAO;
import dao.OrderDAO;
import dao.ProductDAO;
import exception.SQLDataNotFoundException;

public class AddOrderLogic {
	/**
	 * 
	 * @param cutomer_id 顧客ID
	 * @param address_id 住所ID
	 * @param cartSet 注文内容
	 * @return 実行の成否
	 */
	public static boolean execute(int cutomer_id, int address_id, List<Cart> carts){
		try {
			int order_id = OrderDAO.getFin_order_id() + 1;
			for(Cart cart :carts) {
				Product product = ProductDAO.findById(cart.product_id());
				int quantity = cart.quantity();
				int price = quantity * product.price();
				Order order = new Order(order_id, cutomer_id, product.product_id(), quantity, price, address_id, "支払済");
				OrderDAO.insert(order);
			}
			CustomerDAO.updateAddress_id(cutomer_id, address_id);
			return true;
		}catch (SQLException | SQLDataNotFoundException e) {
			return false;
		}
	}
}
