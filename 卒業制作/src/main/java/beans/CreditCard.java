package beans;

public class CreditCard {
	
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
	
	public String blindNumber(){;
		StringBuilder sb = new StringBuilder();
		for(int i = 0;i < number.length()-4;sb.append('*'),i ++) {}
		sb.append(number.substring(number.length()-4));
		return sb.toString();
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
