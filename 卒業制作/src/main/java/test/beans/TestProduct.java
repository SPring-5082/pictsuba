package test.beans;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;

import beans.Product;

class TestProduct {
	
	
	@Test
	void getterANDfullconst() {
		int pid = 1;
		String name = "じゃんごれ";
		Date date = new Date();
		int price = 100;
		int cre_id = 1;
		int cate_id = 0;
		int stock = 10;
		int lookup = 0;
		int point = 1;
		String images = "https://www.ota.ac.jp/info/";
		String descryption = "めちゃ売れてるよ";
		
		String creator_name = "大塚";
		String category_name = "自然";
		int sales_quantity = 100;
		Product p =
		new Product(pid, name, date,
				price, cre_id, cate_id,
				stock, lookup, point,
				images, descryption, creator_name,
				category_name,sales_quantity);
		assertEquals(pid, p.product_id());
		assertEquals(name, p.name());
		assertEquals(date, p.add_date());
		assertEquals(price, p.price());
		assertEquals(cre_id, p.creator_id());
		assertEquals(cate_id, p.category_id());
		assertEquals(stock, p.stock());
		assertEquals(lookup, p.lookup());
		assertEquals(point, p.point());
		assertEquals(images, p.image());
		assertEquals(descryption, p.descryption());
		assertEquals(creator_name, p.creator_name());
		assertEquals(category_name, p.category_name());
		assertEquals(sales_quantity, p.sales_quantity());
		
	}

}
