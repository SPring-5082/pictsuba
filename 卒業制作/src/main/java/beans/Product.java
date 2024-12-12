package beans;

import java.io.Serializable;
import java.util.Date;

public class Product implements Serializable{
	/* S3内オブジェクトのURL
	 * httsp://pictsuba.s3.amazonaws.com/<商品ID>.jpg
	 */
	
	/*
	 * CREATE TABLE PRODUCTS(
	 * 		PRODUCT_ID INT PRIMARY KEY,
	 * 		NAME VARCHAR(128) NOT NULL,
	 * 		ADD_DATE DATE NOT NULL,
	 * 		PRICE INT NOT NULL CHECK(PRICE > 0),
	 * 		STOCK INT NOT NULL CHECK(STOCK >= 0) DEFAULT 0,
	 * 		SALES_QUANTITY INT NOT NULL CHECK(SALES_QUANTITY >= 0) DEFAULT 0,
	 * 		LOOKUP INT NOT NULL CHECK(LOOKUP >= 0) DEFAULT 0,
	 * 		POINT INT NOT NULL CHECK(POINT >= 0),
	 * 		IMAGE VARCHAR(50) NOT NULL CHECK(IMAGE LIKE 'https://%.s3.amazonaws.com/%.jpg'),
	 * 		DESCRYPTION VARCHAR(3000)
	 * );
	 */
	
	/*DBの列要素*/
	private int product_id;
	private String product_name;
	private Date add_date;
	private int price;
	private int creator_id;
	private int category_id;
	private int stock;
	private int lookup;
	private int point;
	private String image;
	private String descryption;
	
	
	private String creator_name;
	private String category_name;
	private int sales_quantity;
	
	public Product(int product_id, String product_name, Date add_date,
			int price, int creator_id, int category_id,
			int stock, int lookup, int point, String image,
			String descryption, String creator_name, String category_name,
			int sales_quantity) {
		this.product_id = product_id;
		this.product_name = product_name;
		this.add_date = add_date;
		this.price = price;
		this.creator_id = creator_id;
		this.category_id = category_id;
		this.stock = stock;
		this.lookup = lookup;
		this.point = point;
		this.image = image;
		this.descryption = descryption;
		
		this.creator_name = creator_name;
		this.category_name = category_name;
		this.sales_quantity = sales_quantity;
	}

	public int product_id() {
		return product_id;
	}

	public String name() {
		return product_name;
	}

	public Date add_date() {
		return add_date;
	}

	public int price() {
		return price;
	}

	public int creator_id() {
		return creator_id;
	}
	
	public int category_id() {
		return category_id;
	}

	public int stock() {
		return stock;
	}

	public int lookup() {
		return lookup;
	}

	public int point() {
		return point;
	}

	public String image() {
		return image;
	}

	public String descryption() {
		return descryption;
	}

	public String creator_name() {
		return creator_name;
	}

	public String category_name() {
		return category_name;
	}

	public int sales_quantity() {
		return sales_quantity;
	}

}
