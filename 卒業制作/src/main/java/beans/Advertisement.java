package beans;

import java.io.Serializable;

public class Advertisement implements Serializable {
	private int advertisement_id;
	private String advertisement_name;
	private String image;
	
	public Advertisement(int advertisement_id, String advertisement_name, String image) {
		this.advertisement_id = advertisement_id;
		this.advertisement_name = advertisement_name;
		this.image = image;
	}
	
	public int advertisement_id() {
		return advertisement_id;
	}
	
	public String advertisement_name() {
		return advertisement_name;
	}
	
	public String image() {
		return  Product.imagePath+image;
	}
	
	public String simpleImage() {
		return image;
	}
}
