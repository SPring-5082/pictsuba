package test.model;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;

import beans.Product;
import model.SearchLogic;

class TestSearchLogic {

	@Test
	void testExecute() {
		try {
			List<Product> products = SearchLogic.execute("キービ", "");
			for(Product product : products) {
				System.out.println(product.name());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
