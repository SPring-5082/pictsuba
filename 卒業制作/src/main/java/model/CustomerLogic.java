package model;

import java.sql.SQLException;

import beans.Customer;
import dao.CustomerDAO;

public class CustomerLogic {
	public static boolean add(Customer customer) {
		boolean b = false;
		try {
			b = CustomerDAO.insert(customer);
		} catch (SQLException e) {}
		return b;
	}
	
}
