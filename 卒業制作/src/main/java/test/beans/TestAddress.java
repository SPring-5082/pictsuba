package test.beans;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import beans.Address;

class TestAddress {

	@Test
	void getterANDfullconst() {
		int address_id = 1;
		int customer_id = 1;
		String zip_code = "374-0004";
		String pref = "群馬県";
		String municipalities = "館林市楠町";
		String street = "1240-14";
		String building = "東部8-4";
		Address address = new Address(address_id, customer_id, zip_code, pref, municipalities, street, building);
		assertEquals(address_id,address.address_id());
		assertEquals(customer_id, address.customer_id());
		assertEquals(zip_code, address.zip_code());
		assertEquals(pref, address.pref());
		assertEquals(municipalities, address.municipalities());
		assertEquals(street, address.street());
		assertEquals(building, address.building());
	}
	
	void getterANDinsertconst() {
		int customer_id = 1;
		String zip_code = "374-0004";
		String pref = "群馬県";
		String municipalities = "館林市楠町";
		String street = "1240-14";
		String building = "東部8-4";
		Address address = new Address(customer_id, zip_code, pref, municipalities, street, building);
		assertEquals(customer_id, address.customer_id());
		assertEquals(zip_code, address.zip_code());
		assertEquals(pref, address.pref());
		assertEquals(municipalities, address.municipalities());
		assertEquals(street, address.street());
		assertEquals(building, address.building());
	}
	
	void constbuilnull() {
		int customer_id = 1;
		String zip_code = "374-0004";
		String pref = "群馬県";
		String municipalities = "館林市楠町";
		String street = "1240-14";
		String building = null;
		Address address = new Address(customer_id, zip_code, pref, municipalities, street, building);
		assertEquals(null, address.building());
	}
	

}
