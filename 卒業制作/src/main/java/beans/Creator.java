package beans;

import java.io.Serializable;

public class Creator implements Serializable{
	
	private int creator_id;
	private String creator_name;
	private int popularity;
	/**INSERT用コンストラクタ*/
	public Creator(String creator_name, int popularity) {
		this.creator_name = creator_name;
		this.popularity = popularity;
	}

	public Creator(int creator_id, String creator_name ,int popularity) {
		this.creator_id = creator_id;
		this.creator_name = creator_name;
		this.popularity = popularity;
	}

	public int creator_id() {
		return creator_id;
	}

	public String creator_name() {
		return creator_name;
	}

	public int popularity() {
		return popularity;
	}

}
