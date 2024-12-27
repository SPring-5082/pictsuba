package test.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import beans.Customer;
import dao.CustomerDAO;
import model.HashLogic;

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
*/
	@Test
	void testfindByMailAndPass() {
		try {
			Customer c = CustomerDAO.findByMailandPass("oibc34242@ojs.ac.jp", HashLogic.execute("dadminpass"));
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
