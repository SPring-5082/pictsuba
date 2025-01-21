package beans;

import java.util.List;

public record Order_Details(List<Cart> carts,Address address,CreditCard card){
	public Order_Details{
		if(carts == null || address == null) {
			throw new IllegalArgumentException();
		}
	}
}
