package beans;

import java.io.Serializable;

public class Address implements Serializable{
	/*
	 * CREATE TABLE ADDRESS(
	 * 		ADDRESS_ID INT AUTO_INCREMENT PRIMARY KEY,
	 * 		CUSTOMER_ID INT REFERENCES CUSTOMERS(CUSTOMER_ID),
	 * 		ZIP_CODE VARCHAR(8) NOT NULL CHECK( LENGTH(ZIP_CODE) = 7 OR LENGTH(ZIP_CODE) = 8 ),
	 * 		PREF VARCHAR(8) NOT NULL,
	 * 		MUNICIPALITIES VARCHAR(32) NOT NULL,
	 * 		STREET VARCHAR(32) NOT NULL,
	 * 		BUILDING VARCHAR(64) 
	 * );
	 */
	
	/**アドレスID*/
	private int address_id;
	/**カスタマーID*/
	private int customer_id;
	/**郵便番号*/
	private String zip_code;
	/**都道府県*/
	private String pref;
	/**市区町村*/
	private String municipalities;
	/**番地*/
	private String street;
	/**番地以降*/
	private String building;
	
	/**INSERT用コンストラクタ*/
	public Address(int customer_id, String zip_code, String pref, String municipalities, String street,
			String building) {
		this.customer_id = customer_id;
		this.zip_code = zip_code;
		this.pref = pref;
		this.municipalities = municipalities;
		this.street = street;
		this.building = building;
	}
	
	public Address(int address_id, int customer_id, String zip_code, String pref, String municipalities, String street,
			String building) {
		this.address_id = address_id;
		this.customer_id = customer_id;
		this.zip_code = zip_code;
		this.pref = pref;
		this.municipalities = municipalities;
		this.street = street;
		this.building = building;
	}

	
	public int address_id() {
		return address_id;
	}
	
	public int customer_id() {
		return customer_id;
	}

	public String zip_code() {
		return zip_code;
	}
	
	public static String zip_code(String zip_code) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ;i < zip_code.length();i ++) {
			sb.append(zip_code.charAt(i));
			if(i == 2)sb.append('-');
		}
		return sb.toString();
	}

	public String pref() {
		return pref;
	}

	public String municipalities() {
		return municipalities;
	}

	public String street() {
		return street;
	}

	public String building() {
		return building;
	}

}
