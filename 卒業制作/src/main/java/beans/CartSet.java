package beans;

import java.util.List;

public class CartSet {
	private List<Product> products;
	private List<Integer> quantities;
	
	public CartSet(List<Product> products, List<Integer> quantities) {
		this.products = products;
		this.quantities = quantities;
	}
	
	public List<Product> products(){
		return products;
	}
	
	public List<Integer> quantities(){
		return quantities;
	}
	
}
