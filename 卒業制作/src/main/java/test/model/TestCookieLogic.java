package test.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import jakarta.servlet.http.Cookie;
import model.CookieLogic;

class TestCookieLogic {
/*
	@Test
	void toJson() {
		Cookie[] c = 
		{
			new Cookie("session_id", "190882"),
			new Cookie("cart", "19/501/2000"),
			new Cookie("favorite", "10/21/555")
		};
		String text =
			"{"+
				'\"' +c[0].getName() + '\"' + ':' + c[0].getValue() + ',' +
				'\"' +c[1].getName() + '\"' + ':' + "[19,501,2000]" + ',' +
				'\"' +c[2].getName() + '\"' + ':' + "[10,21,555]" +
			"}";
		ObjectMapper om = new ObjectMapper();
		String json = CookieLogic.toJson(c);
		System.out.println(text);
		System.out.println(json);
		assertEquals(text, json);
	}
*/
	@Test
	void testgetCookie() {
		Cookie cookie = new Cookie("name", "otuka");
		Cookie cookie2 = new Cookie("age", "18");
		Cookie cookie3 = new Cookie("String", "java.lang");
		Cookie[] cookies = {cookie,cookie2,cookie3};
		assertEquals(cookie, CookieLogic.getCookie("name", cookies));
	}
	
	@Test
	void testToIdListANDaddID() {
		int[] array = {11,234,5671,91};
		Cookie cookie = new Cookie("name", "");
		for(int num : array) {
			cookie = CookieLogic.addId(cookie, num);
		}
		List<Integer> list = CookieLogic.toIdList(cookie.getValue());
		assertEquals(11, list.get(0));
		assertEquals(234, list.get(1));
		assertEquals(5671, list.get(2));
		assertEquals(91, list.get(3));
	}
	
	@Test
	void existKey() {
		Cookie[] c = 
		{
			new Cookie("session_id", "190882"),
			new Cookie("cart", "19/501/2000"),
			new Cookie("favorite", "10/21/555")
		};
		boolean b = CookieLogic.existKey("cart", c);
		assertEquals(b, true);
		b = CookieLogic.existKey("session_id", c);
		assertEquals(b, true);
		b = CookieLogic.existKey("favorite", c);
		assertEquals(b, true);
		
		
		b = CookieLogic.existKey("psd", c);
		assertEquals(b, false);
	}
	
	@Test
	void getValueFromKey() {
		Cookie[] c = 
		{
			new Cookie("session_id", "190882"),
			new Cookie("cart", "19/501/2000"),
			new Cookie("favorite", "10/21/555")
		};
		String value = CookieLogic.getValueFromKey("cart", c);
		assertEquals("19/501/2000", value);
		assertEquals("190882", CookieLogic.getValueFromKey("session_id", c));
		assertEquals("10/21/555", CookieLogic.getValueFromKey("favorite", c));
	}
	

}
