package beans;

import java.io.Serializable;

public class Favorite implements Serializable {
	
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
