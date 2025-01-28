package model;

import java.sql.SQLException;
import java.util.List;

import beans.Cart;
import beans.Order;
import beans.Product;
import dao.CartDAO;
import dao.OrderDAO;
import dao.ProductDAO;
import exception.SQLDataNotFoundException;

public final class OrderCommitLogic {
	public static boolean commit(final List<Cart> carts,final int customer_id,final int address_id) {
		try {
			int order_id = OrderDAO.getFin_order_id() + 1;
			for(Cart c : carts) {
				Product p =  ProductDAO.findById(c.product_id());
				if(p.stock() > 0) {
					ProductDAO.updateStockByCart(c);
					Order order = new Order(order_id, customer_id,c.product_id(), c.quantity(),p.price(), address_id, "");
					OrderDAO.insert(order);
				}
			}
			return CartDAO.delByCustomer_id(customer_id);
		}catch (SQLException | SQLDataNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}
}
