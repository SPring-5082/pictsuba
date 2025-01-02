package model;

import java.sql.SQLException;
import java.util.List;

import beans.Cart;
import beans.Customer;
import dao.CartDAO;
import dao.CustomerDAO;

public class CustomerLogic {
	public static boolean add(Customer customer) {
		boolean b = false;
		try {
			b = CustomerDAO.insert(customer);
		} catch (SQLException e) {}
		return b;
	}
	
	public static List<Cart> getCarts(int customer_id) throws SQLException{
		return CartDAO.findByCustomer_id(customer_id);
	}
	
}
