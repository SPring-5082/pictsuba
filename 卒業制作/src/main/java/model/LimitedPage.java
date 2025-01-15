package model;

public enum LimitedPage {
	cart("cart"),
	address("address"),
	order_history("order-history"),
	account("account"),
	card("card"),
	mypage("mypage"),
	favorite("favorite"),
	;
	private String page;
	
	private LimitedPage(String page) {
		this.page = page;
	}
	
	public String getPage() {
		return page;
	}
	
	public static boolean isLimited(String pageName) {
		for(LimitedPage page : LimitedPage.values()) {
			if(pageName.equals(page.getPage()))return true;
		}
		return false;
	}
	
}
