package beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import model.JSON;

public class Product implements Serializable{
	/* S3内オブジェクトのURL
	 * httsp://pictsuba.s3.amazonaws.com/<商品ID>/<画像番号>.jpg
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
	 * 		IMAGES VARCHAR(50) NOT NULL CHECK(IMAGES LIKE 'https://%.s3.amazonaws.com/%/_.jpg'),
	 * 		DESCRYPTION VARCHAR(3000)
	 * );
	 */
	
	
	private int product_id;
	private String product_name;
	private Date add_date;
	private int price;
	private int creator_id;
	private int stock;
	private int lookup;
	private int point;
	private String images;
	private String descryption;
	
	private List<String> url;

	public Product(int product_id, String product_name, Date add_date, int price, int creator_id, int stock, int lookup,
			int point, String images, String descryption) {
		this.product_id = product_id;
		this.product_name = product_name;
		this.add_date = add_date;
		this.price = price;
		this.creator_id = creator_id;
		this.stock = stock;
		this.lookup = lookup;
		this.point = point;
		this.images = images;
		this.url = JSON.getURLs(images);
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

	public int stock() {
		return stock;
	}

	public int lookup() {
		return lookup;
	}

	public int point() {
		return point;
	}

	public String images() {
		return images;
	}

	public String descryption() {
		return descryption;
	}

	public List<String> url() {
		return url;
	}

}
