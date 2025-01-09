package test.dao;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import beans.Contact;
import dao.ContactDAO;

class TestContactDAO {

	@Test
	void test() {
		Contact contact = new Contact("大塚", "oibc35222@ojs.ac.jp", "sample");
		try {
			ContactDAO.insert(contact);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

}
