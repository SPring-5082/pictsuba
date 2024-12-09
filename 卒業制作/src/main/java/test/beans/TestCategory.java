package test.beans;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import beans.Category;

class TestCategory {

	
	@Test
	void getterANDfullconst() {
		int cate_id = 1;
		String c_name = "絵";
		int lcid = -1;
		Category ca = new Category(cate_id, c_name, lcid);
		assertEquals(cate_id, ca.category_id());
		assertEquals(c_name,ca.category_name());
		assertEquals(lcid, ca.large_category_id());
	}

}
