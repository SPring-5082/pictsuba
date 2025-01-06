package test.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

import beans.Customer;
import dao.CustomerDAO;
import model.EncryptionLogic;
import model.KeyStorage;

class TestCustomerDAO {
	/*
	@Test
	void testInsertnull() {
		String name = "IceCream";
		String pass = "rootpass";
		String phone = null;
		String mail = "oibc35282@ojs.ac.jp";
		int age = 20;
		Date birth_day = null;
		String gender = null;
		Customer c = 
		new Customer(name, pass, phone, mail, age, birth_day, gender);
		try {
			assertEquals(true, CustomerDAO.insert(c));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
*//*
	@Test
	void testfindByMailAndPass() {
		try {
			Customer c = CustomerDAO.findByMailandPass("oibc34242@ojs.ac.jp", "NO-JOBS");
			assertEquals(3, c.customer_id());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/*
	@Test
	void testupdateBycustomer_id() {
		String name = "IceCream";
		String pass = "rootpass";
		String phone = null;
		String mail = "oibc35282@ojs.ac.jp";
		int age = 20;
		Date birth_day = null;
		String gender = null;
		Customer c = 
		new Customer(name, pass, phone, mail, age, birth_day, gender);
		try {
			assertEquals(true, CustomerDAO.insert(c));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			Customer c3 = 
			new Customer(1, name,"adminpass","08067906400",mail, age, new Date(), gender, age, null, new Date(), -1, -1);
			boolean b = CustomerDAO.updateByCustomer_id(c3);
			assertEquals(b, true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/*
	@Test
	void testUpdateFin_log() {
		try {
			boolean b = CustomerDAO.updateFin_log(1);
			assertEquals(b, true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	*/
	/*
	@Test
	void testUpdatePoint() {
		try {
			boolean b = CustomerDAO.updatePoint(1, 100);
			assertEquals(true, b);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	*/
	@Test
	void testUpdateByCustomer_id() {
		try {
			KeyStorage.init("O52HO0G5C3NtkegLeQE0Kg==", "jxCxl18YNgUEY97WEhbE/Q==");
			Function<String, String> f = text -> {
				return "oibc" + text + "@ojs.ac.jp";
			};
			List<Customer> list = null;
			try {
				Customer c1 = CustomerDAO.findByMailandPass(f.apply("34018"), "GURA-SAN");
				Customer c2 = CustomerDAO.findByMailandPass(f.apply("34069"), "CCNA-GETTER");
				Customer c3 = CustomerDAO.findByMailandPass(f.apply("34242"), "NO-JOBS");
				Customer c4 = CustomerDAO.findByMailandPass(f.apply("35079"), "PICTSUBA");
				Customer c5 = CustomerDAO.findByMailandPass(f.apply("35282"), "PON-PON-PON");
				Customer c6 = CustomerDAO.findByMailandPass(f.apply("35256"), "PACHIYAKUZA");
				Customer c7 = CustomerDAO.findByMailandPass(f.apply("35182"), "Takatori5082");
				list = List.of(c1,c2,c3,c4,c5,c6,c7);
			}catch (Exception e) {}
			for(Customer c: list) {
				c =
				new Customer(c.customer_id(),c.name(), EncryptionLogic.enc(c.password()), c.phone(), c.mail(), c.age(), c.birth_day(), c.gender(), c.point(), c.first_log(), c.fin_log(), c.address_id(), c.card_id());
				CustomerDAO.updateByCustomer_id(c);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testupdateAddress_id() {
		try {
			boolean b = CustomerDAO.updateAddress_id(1, 1);
			assertEquals(true, b);
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testupdateCard_id() {
		try {
			boolean b = CustomerDAO.updateCard_id(1, 1);
			assertEquals(true, b);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	@Test
	void testdelByCustomer_id() {
		String name = "IceCream";
		String pass = "rootpass";
		String phone = null;
		String mail = "oibc35282@ojs.ac.jp";
		int age = 20;
		Date birth_day = null;
		String gender = null;
		Customer c = 
		new Customer(name, pass, phone, mail, age, birth_day, gender);
		try {
			assertEquals(true, CustomerDAO.insert(c));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			Customer c2 = CustomerDAO.findByMailandPass("oibc35282@ojs.ac.jp", "rootpass");
			boolean b = CustomerDAO.delByCustomer_id(c2.customer_id());
			assertEquals(true, b);
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	*/
}
