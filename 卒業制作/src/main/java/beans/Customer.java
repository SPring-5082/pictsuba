package beans;

import java.io.Serializable;
import java.util.Date;

public class Customer implements Serializable{
	/*
	 * CREATE TABLE CUSTOMERS(
	 * 		CUSTOMER_ID INT AUTO_INCREMENT PRIMARY KEY,
	 * 		NAME VARCHAR(32) NOT NULL,
	 * 		PASSWORD VARCHAR(300) NOT NULL,
	 * 		PHONE VARCHAR(16) NOT NULL UNIQUE,
	 * 		MAIL VARCHAR NOT NULL UNIQUE,
	 * 		AGE INT NOT NULL CHECK(AGE >= 0),
	 * 		BIRTH_DAY DATE NOT NULL,
	 * 		GENDER VACHAR NOT NULL
	 * 		POINT INT NOT NULL CHECK(POINT >= 0) DEFAULT 0,
	 * 		FIRST_LOG DATE NOT NULL,
	 * 		FIN_LOG DATE NOT NULL,
	 * 		ADDRESS_ID REFERENCES ADDRESS(ADDRESS_ID) NOT NULL
	 * );
	 */
	
	
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
	
	
	
	
	public Customer(String name, String password, String phone, String mail, int age, Date birth_day,
			String gender, int point, Date first_log, Date fin_log, int address_id) {
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
	}


	public Customer(int customer_id, String name,String password, String phone, int point, Date first_log, Date fin_log) {
		this.customer_id = customer_id;
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.point = point;
		this.first_log = first_log;
		this.fin_log = fin_log;
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

}
