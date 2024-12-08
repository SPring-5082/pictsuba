package beans;

import java.io.Serializable;

public class Cart implements Serializable{
	private int customer_id;
	private int product_id;
	private int quantity;
	
	public Cart(int customer_id, int product_id,int quantity) {
		this.customer_id = customer_id;
		this.product_id = product_id;
		this.quantity = quantity;
	}
	
	public int customer_id() {
		return customer_id;
	}
	
	public int product_id() {
		return product_id;
	}
	
	public int quantity() {
		return quantity;
	}
	
}
