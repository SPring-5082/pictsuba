package beans;

import java.util.Date;

public class Order_History {
	private int order_id;
	private int customer_id;
	private int product_id;
	private int quantity;
	private int address_id;
	private Date order_date;
	
	public Order_History(int order_id, int customer_id, int product_id, int quantity, int address_id, Date order_date) {
		this.order_id = order_id;
		this.customer_id = customer_id;
		this.product_id = product_id;
		this.quantity = quantity;
		this.address_id = address_id;
		this.order_date = order_date;
	}
	

	public int order_id() {
		return order_id;
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

	public int address_id() {
		return address_id;
	}

	public Date order_date() {
		return order_date;
	}

	
}
