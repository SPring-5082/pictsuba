package test.beans;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import beans.Creator;

class TestCreator {

	@Test
	void getterANDfullconst() {
		int cid = 1;
		String cname = "じゃんごれ";
		int pop = 100;
		Creator c = new Creator(cid, cname, pop);
		assertEquals(cid, c.creator_id());
		assertEquals(cname, c.creator_name());
		assertEquals(pop, c.popularity());
		
	}

}
