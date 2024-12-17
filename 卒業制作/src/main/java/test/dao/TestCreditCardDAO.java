package test.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import beans.CreditCard;
import dao.CreditCardDAO;

class TestCreditCardDAO {
	@Test
	void testInsert() throws SQLException {
		CreditCard card = new CreditCard(1, "1234543678991222", "05/25", 169, "HARU TAKATORI");
		boolean b = CreditCardDAO.insert(card);
		assertEquals(true, b);
	}
	/*
	@Test
	void testFindByCustomer_id() throws SQLException {
		List<CreditCard> list =  CreditCardDAO.findByCustomer_id(1);
		for(CreditCard card : list) {
			System.out.println(card.card_id() + ":" + card.customer_id() + ":"
			+ ":" + card.number() + ":" + card.expire() + ":" + card.security_code() + ":" + card.owner_name());
		}
	}
	
	@Test
	void testdelByCard_id() throws SQLException {
		boolean b2 = CreditCardDAO.delByCard_id(1);
		assertEquals(true, b2);
	}
*/	
	/*
	@Test
	void testAll() throws SQLException {
		CreditCard card1 = new CreditCard(1, "1234543678991222", "05/25", 169, "HARU TAKATORI");
		boolean b = CreditCardDAO.insert(card1);
		assertEquals(true, b);
		
		List<CreditCard> list =  CreditCardDAO.findByCustomer_id(1);
		for(CreditCard card : list) {
			System.out.println(card.card_id() + ":" + card.customer_id() + ":"
			+ card.number() + ":" + card.expire() + ":" + card.security_code() + ":" + card.owner_name());
		}
		
		boolean b2 = CreditCardDAO.delByCard_id(1);
		assertEquals(true, b2);
	}
	*/
}
