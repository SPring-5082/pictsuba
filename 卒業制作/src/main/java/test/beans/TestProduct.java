package test.beans;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
		String images = "{\"array\":[\"https://www.ota.ac.jp/info/\"]}";
		String descryption = "めちゃ売れてるよ";
		Product p =
		new Product(pid, name, date, price, cre_id, cate_id, stock, lookup, point, images, descryption);
		assertEquals(pid, p.product_id());
		assertEquals(name, p.name());
		assertEquals(date, p.add_date());
		assertEquals(price, p.price());
		assertEquals(cre_id, p.creator_id());
		assertEquals(cate_id, p.category_id());
		assertEquals(stock, p.stock());
		assertEquals(lookup, p.lookup());
		assertEquals(point, p.point());
		assertEquals(images, p.images());
		assertEquals(descryption, p.descryption());
		List<String> list = new ArrayList<String>();
		list.add("https://www.ota.ac.jp/info/");
		assertEquals(list, p.url());
		assertEquals(list.get(0), p.url().get(0));
		System.out.println(p.url().get(0));
	}

}
