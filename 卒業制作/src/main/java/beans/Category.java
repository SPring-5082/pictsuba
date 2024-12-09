package beans;

public class Category {
	private int category_id;
	private String category_name;
	private int large_category_id;
	
	/**INSERT用コンストラクタ*/
	public Category(String category_name, int large_category_id) {
		this.category_name = category_name;
		this.large_category_id = large_category_id;
	}
	
	public Category(int category_id,String category_name, int large_category_id) {
		this(category_name,large_category_id);
		this.category_id = category_id;
		
	}
	
	public int category_id() {
		return category_id;
	}
	
	public String category_name() {
		return category_name;
	}
	
	public int large_category_id() {
		return large_category_id;
	}
	
}
