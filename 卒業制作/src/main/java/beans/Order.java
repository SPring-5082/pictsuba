package beans;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable{
	private int order_id;
	private int customer_id;
	private int product_id;
	private int quantity;
	private int price;
	private int address_id;
	private Date order_date;
	private String state;
	
	
	private int sum_price;
	private String product_image;
	
	
	/**INSERT用コンストラクタ*/
	public Order(int order_id, int customer_id, int product_id, int quantity, int price, int address_id,String state) {
		this.order_id = order_id;
		this.customer_id = customer_id;
		this.product_id = product_id;
		this.quantity = quantity;
		this.price = price;
		this.address_id = address_id;
		this.state = state;
	}
	
	public Order(int order_id, int customer_id, int product_id, int quantity, int price, int address_id, Date order_date,
			String state) {
		this.order_id = order_id;
		this.customer_id = customer_id;
		this.product_id = product_id;
		this.quantity = quantity;
		this.price = price;
		this.address_id = address_id;
		this.order_date = order_date;
		this.state = state;
	}
	
	public Order(int order_id,Date order_date,
			String state,String product_image) {
		this.order_id = order_id;
		this.order_date = order_date;
		this.state = state;
		this.product_image = product_image;
	}
	
	public Order(int order_id,int address_id, Date order_date, String state,int sum_price) {
		this.order_id = order_id;
		this.address_id = address_id;
		this.order_date = order_date;
		this.state = state;
		this.sum_price = sum_price;
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

	public int price() {
		return price;
	}

	public int address_id() {
		return address_id;
	}

	public Date order_date() {
		return order_date;
	}

	public String state() {
		return state;
	}

	public int sum_price() {
		return sum_price;
	}

	public String product_image(){
		return Product.imagePath+product_image;
	}

}
