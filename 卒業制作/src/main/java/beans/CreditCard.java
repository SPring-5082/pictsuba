package beans;

public class CreditCard {
	/*
	 * CREATE TABLE CREDIT_CARDS(
	 *  	CARD_ID INT AUTO_INCREMENT  PRIMARY KEY,
	 *  	CUSTOMER_ID INT NOT NULL REFERENCES CUSTOMERS(CUSTOMER_ID),
	 *  	NUMBER VARCHAR(16) NOT NULL,
	 *  	EXPIRE VARCHAR(8) NOT NULL,
	 *  	SECURITY_CORD INT NOT NULL,
	 *  	OWNER_NAME VARCHAR(32) NOT NULL
	 *  );
	 */
	
	
	private int card_id;
	private int customer_id;
	private String number;
	private String expire;
	private int security_code;
	private String owner_name;
	
	
	/**INSERT用コンストラクタ*/
	public CreditCard(int customer_id, String number, String expire, int security_code, String owner_name) {
		this.customer_id = customer_id;
		this.number = number;
		this.expire = expire;
		this.security_code = security_code;
		this.owner_name = owner_name;
	}

	public CreditCard(int card_id, int customer_id, String number, String expire, int security_code,
			String owner_name) {
		this.card_id = card_id;
		this.customer_id = customer_id;
		this.number = number;
		this.expire = expire;
		this.security_code = security_code;
		this.owner_name = owner_name;
	}
	
	public int card_id() {
		return card_id;
	}
	
	public int customer_id() {
		return customer_id;
	}
	
	public String number() {
		return number;
	}
	
	public String expire() {
		return expire;
	}
	
	public int security_code() {
		return security_code;
	}
	
	public String owner_name() {
		return owner_name;
	}
	
}
