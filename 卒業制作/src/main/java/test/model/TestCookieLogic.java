package test.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.Cookie;
import model.CookieLogic;

class TestCookieLogic {

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
