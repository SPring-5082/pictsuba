package model;

import java.util.Comparator;

import beans.Product;

public enum ProductComparator {
	/**購入数と閲覧数に基づく人気順*/
	DEFAULT((Comparator<Product>)(p1,p2)->{
		return Integer.compare(p2.sales_quantity()+p2.lookup()/10,p1.sales_quantity()+p1.lookup()/10);
	}),
	PRICE_ASC((Comparator<Product>)(p1,p2)->{
		return Integer.compare(p1.price(), p2.price());
	}),
	PRICE_DESC((Comparator<Product>)(p1,p2)->{
		return Integer.compare(p2.price(), p1.price());
	}),
	NEWER((Comparator<Product>)(p1,p2)->{
		return p1.add_date().compareTo(p2.add_date());
	}),
	;
	private Comparator<Product> comparator;
	
	
	private ProductComparator(Comparator<Product> comparator) {
		this.comparator = comparator;
	}
	
	public Comparator<Product> comparator() {
		return comparator;
	}

}
