package test.beans;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import beans.Favorite;

class TestFavorite {

	@Test
	void getterANDfullconst() {
		int customer_id = 1;
		int product_id = 1;
		Favorite favorite = new Favorite(customer_id,product_id);
		assertEquals(customer_id, favorite.customer_id());
		assertEquals(product_id, favorite.product_id());
	}

}
