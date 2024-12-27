package test.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import beans.Cart;
import dao.CartDAO;

class TestCartDAO {

	@Test
	void test() {
		
		try {
			Cart cart = new Cart(1, 1, 3);
			boolean b = CartDAO.insert(cart);
			assertEquals(true, b);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
