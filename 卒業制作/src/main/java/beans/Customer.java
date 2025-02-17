package beans;

import java.io.Serializable;
import java.util.Date;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

import model.EncryptionLogic;

public class Customer implements Serializable{
	
	//フィールド
	private int customer_id;
	private String name;
	private String password;
	private String phone;
	private String mail;
	private int age;
	private Date birth_day;
	private String gender;
	private int point;
	private Date first_log;
	private Date fin_log;
	private int address_id;
	private int card_id;
	
	/**INSERT用コンストラクタ*/
	public Customer(String name, String password, String phone, String mail, int age, Date birth_day, String gender) {
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.mail = mail;
		this.age = age;
		this.birth_day = birth_day;
		this.gender = gender;
		this.point = 0;
		this.first_log = new Date();
		this.fin_log = new Date();
	}

	/**パスワード抜きコンストラクタ*/
	public Customer(int customer_id, String name, String phone, String mail, int age, Date birth_day,
			String gender, int point, Date first_log, Date fin_log, int address_id, int card_id) {
		this.customer_id = customer_id;
		this.name = name;
		this.phone = phone;
		this.mail = mail;
		this.age = age;
		this.birth_day = birth_day;
		this.gender = gender;
		this.point = point;
		this.first_log = first_log;
		this.fin_log = fin_log;
		this.address_id = address_id;
		this.card_id = card_id;
	}
	
	/**全フィールドコンストラクタ*/
	public Customer(int customer_id, String name, String password, String phone, String mail, int age, Date birth_day,
			String gender, int point, Date first_log, Date fin_log, int address_id, int card_id) {
		this.customer_id = customer_id;
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.mail = mail;
		this.age = age;
		this.birth_day = birth_day;
		this.gender = gender;
		this.point = point;
		this.first_log = first_log;
		this.fin_log = fin_log;
		this.address_id = address_id;
		this.card_id = card_id;
	}

	public int customer_id() {
		return customer_id;
	}

	public String name() {
		return name;
	}

	public String password() {
		return password;
	}
	
	public String decryptPassword() throws IllegalBlockSizeException, BadPaddingException {
		return EncryptionLogic.dec(password);
	}
	
	public String blindPassword() throws IllegalBlockSizeException, BadPaddingException {
		StringBuilder sb = new StringBuilder();
		for(int i = 0;i < decryptPassword().length();i++) {
			sb.append('*');
		}
		return sb.toString();
	}

	public String phone() {
		return phone;
	}
	
	public String mail() {
		return mail;
	}
	public int age() {
		return age;
	}
	
	public Date birth_day() {
		return birth_day;
	}
	
	public String gender() {
		return gender;
	}

	public int point() {
		return point;
	}

	public Date first_log() {
		return first_log;
	}

	public Date fin_log() {
		return fin_log;
	}
	
	public int address_id() {
		return address_id;
	}
	
	public int card_id() {
		return card_id;
	}

}
