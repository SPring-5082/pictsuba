package test.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class TestSQL {

	@Test
	void testInsert() {
		String table = "A";
		String insert = "INSERT INTO A VALUES (STATEMENT)";
		assertEquals(insert, SQL.insert(table));
	}
	
	@Test
	void testInsertover() {
		String table = "A";
		String col = "NAME,PASSWORD";
		String insert = "INSERT INTO A (NAME,PASSWORD) VALUES (STATEMENT)";
		assertEquals(insert, SQL.insert(table, col));
	}
	
	@Test
	void testSelect() {
		String table = "A";
		String select = "SELECT * FROM A ";
		assertEquals(select, SQL.select(table));
	}
	
	@Test
	void testUpdate() {
		String table = "A";
		String update = "UPDATE A ";
		assertEquals(update, SQL.update(table));
	}
	
	@Test
	void testDelete() {
		String table = "A";
		String delete = "DELETE A ";
		assertEquals(delete, SQL.delete(table));
	}

}
