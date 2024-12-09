package test.beans;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import beans.CreditCard;

class TestCreditCard {

	@Test
	void getterANDfullconst() {
		int card_id = 1;
		int customer_id = 2;
		String number = "1234123456467865";
		String expire = "05/25";
		int security_code = 169;
		String owner_name  = "HARU TAKATORI";
		CreditCard card = new CreditCard(card_id, customer_id, number, expire, security_code, owner_name);
		assertEquals(card_id, card.card_id());
		assertEquals(customer_id, card.customer_id());
		assertEquals(number, card.number());
		assertEquals(expire, card.expire());
		assertEquals(security_code, card.security_code());
		assertEquals(owner_name, card.owner_name());
		
	}
	
	@Test 
	void getterANDinsertconst() {
		int customer_id = 2;
		String number = "1234123456467865";
		String expire = "05/25";
		int security_code = 169;
		String owner_name  = "HARU TAKATORI";
		CreditCard card = new CreditCard(customer_id, number, expire, security_code, owner_name);
		assertEquals(customer_id, card.customer_id());
		assertEquals(number, card.number());
		assertEquals(expire, card.expire());
		assertEquals(security_code, card.security_code());
		assertEquals(owner_name, card.owner_name());
	}

}
