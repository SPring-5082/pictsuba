package test.beans;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import beans.Cart;

class TestCart {

	@Test
	void getterANDfullconst() {
		int cid = 1;
		int pid = 2;
		int quantity = 10;
		Cart cart = new Cart(cid, pid, quantity);
		assertEquals(cid, cart.customer_id());
		assertEquals(pid, cart.product_id());
		assertEquals(quantity, cart.quantity());
	}

}
