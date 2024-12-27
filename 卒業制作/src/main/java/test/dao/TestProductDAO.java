package test.dao;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;

import beans.CartSet;
import beans.Product;
import dao.ProductDAO;

class TestProductDAO {
	/*
	@Test
	void testfindbyid() throws SQLException {
		Product p = ProductDAO.findById(1);
		assertEquals(1, p.product_id());
		assertEquals("必殺!!仕事人", p.name());
		assertEquals(new Date(124,11,18), p.add_date());
		assertEquals(1000, p.price());
		assertEquals(4, p.creator_id());
		assertEquals(6, p.category_id());
		assertEquals(1, p.stock());
		assertEquals(0, p.lookup());
		assertEquals(10, p.point());
		assertEquals("https://vo-metsoffice.jp/wp-content/uploads/2016/11/201611illust-head1.jpg", p.image());
		assertEquals(null, p.descryption());
		
	}*/
	/*
	@Test
	void testFindByPopularity() {
		List<Product> list = null;
		try {
			list = ProductDAO.findByPopularity();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		for(Product p : list) {
			System.out.println(p.name());
		}
	}*/
	
	@Test
	void test() {
		try {
			CartSet cartSet = ProductDAO.findByCustomers_cart(1);
			List<Product> products = cartSet.products();
			List<Integer> quantities = cartSet.quantities();
			for(int i = 0;i < cartSet.products().size();i ++) {
				System.out.println(products.get(i).name() + " : " + quantities.get(i).toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/*
	@Test
	void testfindbyRecomend() {
		List<Product> list = null;
		try {
			list = ProductDAO.findByRecommendation(2);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for(Product p : list) {
			System.out.println(p.name() + ":" + (p.sales_quantity() + p.lookup()/10));
		}
		
	}*/
	
}
