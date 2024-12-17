package test.dao;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import beans.Address;
import dao.AddressDAO;

class TestAddressDAO {

	@Test
	void testInsert() {
		Address address = new Address(1,"333-3333", "群馬県", "太田市石原町", "123-4576", "アパート"); 
		try {
			AddressDAO.insert(address);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	@Test
	void testFindAndUpdate() {
		try {
			List<Address> list = AddressDAO.findByCustomer_id(1);
			for(Address address : list) {
				System.out.println(address.address_id() + ":" + address.customer_id() + ":"
						+ address.pref() + ":" + address.municipalities() + ":" + 
						address.street() + ":" + address.building());
			}
			Address ad = new Address(1, 1, "374-0004", "群馬県", "館林市楠町", "1240-14", "東部8-4");
			boolean b = AddressDAO.update(ad);
			assertEquals(true, b);
			list = AddressDAO.findByCustomer_id(1);
			for(Address address : list) {
				System.out.println(address.address_id() + ":" + address.customer_id() + ":"
						+ address.pref() + ":" + address.municipalities() + ":" + 
						address.street() + ":" + address.building());
				if(address.address_id() == 1) {
					assertEquals(address.address_id(), ad.address_id());
					assertEquals(address.customer_id(), ad.customer_id());
					assertEquals(address.pref(), ad.pref());
					assertEquals(address.municipalities(), ad.municipalities());
					assertEquals(address.street(), ad.street());
					assertEquals(address.building(), ad.building());
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testdel() {
		try {
			boolean b = AddressDAO.delByAddress_id(1);
			assertEquals(true, b);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}*/
	
	/*
	@Test
	void testAll() {
		Address address = new Address(1,"333-3333", "群馬県", "太田市石原町", "123-4576", "アパート"); 
		try {
			AddressDAO.insert(address);
			
			
			List<Address> list = AddressDAO.findByCustomer_id(1);
			for(Address address2 : list) {
				System.out.println(address2.address_id() + ":" + address2.customer_id() + ":"
						+ address2.pref() + ":" + address2.municipalities() + ":" + 
						address2.street() + ":" + address2.building());
			}
			Address ad = new Address(1, 1, "374-0004", "群馬県", "館林市楠町", "1240-14", "東部8-4");
			boolean b = AddressDAO.update(ad);
			assertEquals(true, b);
			list = AddressDAO.findByCustomer_id(1);
			for(Address address2 : list) {
				System.out.println(address2.address_id() + ":" + address2.customer_id() + ":"
						+ address2.pref() + ":" + address2.municipalities() + ":" + 
						address2.street() + ":" + address2.building());
				if(address.address_id() == 1) {
					assertEquals(address2.address_id(), ad.address_id());
					assertEquals(address2.customer_id(), ad.customer_id());
					assertEquals(address2.pref(), ad.pref());
					assertEquals(address2.municipalities(), ad.municipalities());
					assertEquals(address2.street(), ad.street());
					assertEquals(address2.building(), ad.building());
				}
			}
			boolean b2 = AddressDAO.delByAddress_id(1);
			assertEquals(true, b2);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	*/
}
