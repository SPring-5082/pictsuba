package beans;

import java.io.Serializable;

public class Favorite implements Serializable {
	/*
	 * CREATE TABLE FAVORITES(
	 * 		CUSTOMER_ID INT REFERENCES CUSTOMERS(CUSTOMER_ID) NOT NULL,
	 * 		PRODUCT_ID INT REFERENCES PRODUCTS(PRODUCT_ID) NOT NULL
	 * );
	 */
	
	private int customer_id;
	private int product_id;
	
	public Favorite(int customer_id, int product_id) {
		this.customer_id = customer_id;
		this.product_id = product_id;
	}
	
	public int customer_id() {
		return customer_id;
	}
	
	public int product_id() {
		return product_id;
	}
	
}
