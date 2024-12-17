package test.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import beans.Product;
import model.SearchResultLogic;

class TestSwarchResultLogic {

	@Test
	void execute() {
		List<Product> productList = new ArrayList<Product>();
		String searchWord = "マウス";
		
		productList.add(new Product(1, "マウス", new Date(), 100, 1, 2, 10, 100, 1, "{\"url\":[\"https://www.ota.ac.jp/info/\"]}", "説明", "つば", "人物", 20));//score:80
		productList.add(new Product(2, "マウス", new Date(), 100, 1, 2, 10, 100, 1, "{\"url\":[\"https://www.ota.ac.jp/info/\"]}", "説明", "つば", "人物", 10));//score:70
		productList.add(new Product(3, "マウ", new Date(), 100, 1, 2, 10, 100, 1, "{\"url\":[\"https://www.ota.ac.jp/info/\"]}", "説明", "つば", "人物", 10));//score:60
		productList.add(new Product(4, "マウス", new Date(), 100, 1, 2, 10, 100, 1, "{\"url\":[\"https://www.ota.ac.jp/info/\"]}", "説明", "つば", "人物", 30));//score:90
		productList.add(new Product(5, "マウス", new Date(), 100, 1, 2, 10, 100, 1, "{\"url\":[\"https://www.ota.ac.jp/info/\"]}", "説明", "つば", "人物", 0));//score:60
		
		List<Product> respList = SearchResultLogic.execute(productList, searchWord);
		//求める検索結果順序
		int[] idArray = {4, 1, 2, 5, 3};
		int cnt = 0;
		for(Product p : respList) {
			assertEquals(p.product_id(), idArray[cnt++]);
		}
	}

}
