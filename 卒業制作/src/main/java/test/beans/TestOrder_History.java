package test.beans;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;

import beans.Order_History;

class TestOrder_History {

	@Test
	void getterANDfullconst() {
		int order_id = 1;
		int customer_id = 13;
		int product_id = 12;
		int quantity = 10;
		int price = 10000;
		int address_id = 1;
		Date order_date = new Date();
		Order_History history =
			new Order_History(order_id, customer_id, product_id, quantity, price, address_id, order_date);
		
		assertEquals(order_id, history.order_id());
		assertEquals(customer_id, history.customer_id());
		assertEquals(product_id, history.product_id());
		assertEquals(quantity, history.quantity());
		assertEquals(price, history);
		assertEquals(address_id, history.address_id());
		assertEquals(order_date, history.order_date());
	}

}
