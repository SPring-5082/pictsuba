package beans;

public class Contact{
	private int contact_id;
	private String name;
	private String mail;
	private String message;
	public Contact(int contact_id, String name, String mail, String message) {
		if(name == null || name == null || message == null) throw new NullPointerException("引数にnullは許可されていません");
		this.contact_id = contact_id;
		this.name = name;
		this.mail = mail;
		this.message = message;
	}
	public Contact(String name, String mail, String message) {
		if(name == null || name == null || message == null) throw new NullPointerException("引数にnullは許可されていません");
		this.name = name;
		this.mail = mail;
		this.message = message;
	}
	
	public int contact_id() {
		return contact_id;
	}
	
	public String name() {
		return name;
	}
	
	public String mail(){
		return mail;
	}
	
	public String message() {
		return message;
	}
	
}
