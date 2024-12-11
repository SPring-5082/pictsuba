package test.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import model.JSON;

class TestJSON {
	static String text =
			"{\n"
					+ "	\"size\":\"横100mm × 縦500mm\",\n"
					+ "	\"tag\":[\"海\",\"山\"],\n"
					+ "	\"detail\":\"でかでか\"\n"
					+ "}";
					
	@Test
	void getList() throws JsonMappingException, JsonProcessingException {
		JSON json = new JSON(text);
		List<String> list = new ArrayList<String>();
		list.add("海");
		list.add("山");
		assertEquals(list, json.getList("tag"));
	}
	
	@Test
	void getText() throws JsonMappingException, JsonProcessingException {
		JSON json = new JSON(text);
		assertEquals("横100mm × 縦500mm", json.getText("size"));
		assertEquals("でかでか", json.getText("detail"));
	}
	

}
