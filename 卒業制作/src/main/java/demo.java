import java.util.ArrayList;
import java.util.List;

import beans.Product;
import model.SearchResultLogic;

public class demo {
	public static void main(String[] args) {
		List<Product> productList = new ArrayList<Product>();
		productList.add(new Product(1, "マウス", null, 100, 1, 2, 10, 100, 1, null, "説明", "つば", "人物", 120));
		productList.add(new Product(2, "マウス", null, 100, 1, 2, 10, 100, 1, null, "説明", "つば", "人物", 110));
		productList.add(new Product(3, "マウス", null, 100, 1, 2, 10, 100, 1, null, "説明", "つば", "人物", 140));
		productList.add(new Product(4, "マウス", null, 100, 1, 2, 10, 100, 1, null, "説明", "つば", "人物", 130));
		productList.add(new Product(5, "マウス", null, 100, 1, 2, 10, 100, 1, null, "説明", "つば", "人物", 100));
		
		List<Product> respList = SearchResultLogic.execute(productList, "マウス");
		for(Product p : respList) {
			System.out.printf("id=%d", p.product_id());
		}
	}
}
