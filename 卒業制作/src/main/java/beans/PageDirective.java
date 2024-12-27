package beans;

public enum PageDirective {
	DEFAULT("top"),
	TOP("top"),
	ACCOUNT("account"),
	MYPAGE("mypage"),
	ADDRESS("address"),
	CARD("card"),
	ORDER_HISTORY("order_history"),
	PRODUCT("product"),
	;
	
	private String page;
	
	private PageDirective(String page) {
		this.page = page;
	}
	
	public String getPage() {
		return this.page;
	}
	
}
