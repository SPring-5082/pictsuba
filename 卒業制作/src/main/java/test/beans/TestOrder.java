package test.beans;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;

import beans.Order;

class TestOrder {

	@Test
	void getterANDfullconst() {
		int order_id = 1;
		int customer_id = 2;
		int product_id = 3;
		int quantity = 10;
		int address_id = 4;
		Date order_date = new Date();;
		String state = "支払い待ち";
		Order order =
		new Order(order_id, customer_id, product_id, quantity, address_id, order_date, state);
		assertEquals(order_id, order.order_id());
		assertEquals(customer_id, order.customer_id());
		assertEquals(product_id, order.product_id());
		assertEquals(quantity, order.quantity());
		assertEquals(address_id, order.address_id());
		assertEquals(order_date, order.order_date());
		assertEquals(state, order.state());
	}

}
