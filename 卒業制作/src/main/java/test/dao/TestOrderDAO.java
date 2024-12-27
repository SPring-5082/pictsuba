package test.dao;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;

import beans.Order;
import dao.OrderDAO;

class TestOrderDAO {
	/*
	@Test
	void testinsert() throws SQLException{
		Order order = new Order(1, 1, 1, 2, 1000, 1, "支払済");
		assertEquals(true, OrderDAO.insert(order));
	}
	*/
	/*
	@Test
	void test() throws SQLException {
		List<Order> list = OrderDAO.findByCustomer_id(1);
		Order order = list.get(0);
		assertEquals(order.order_id(), 1);
		assertEquals(order.customer_id(), 1);
		assertEquals(order.product_id(), 1);
		assertEquals(order.quantity(), 2);
		assertEquals(order.price(), 1000);
		assertEquals(order.address_id(), 1);
		assertEquals(order.order_date(), new Date(124,11,19));
		assertEquals(order.state(), "支払済");
	}
	*/
	
	@Test
	void testFindByOrder_id() {
		try {
			List<Order> list = OrderDAO.findByOrder_id(1);
			for(Order o : list) {
				System.out.println(o.product_id());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	@Test
	void testGetFin_Order_id() throws SQLException {
		assertEquals(0, OrderDAO.getFin_order_id());
	}
	*/
	/*
	@Test
	void testDelete() throws SQLException {
		assertEquals(true, OrderDAO.deleteOrder(1));
	}
	*/
}
