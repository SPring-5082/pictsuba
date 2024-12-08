package model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSON {
	public static List<String> getURLs(String json) {
		List<String> list = new ArrayList<String>();
		ObjectMapper om = new ObjectMapper();
		try {
			JsonNode node1 =  om.readTree(json);
			JsonNode node2 = node1.get("array");
			node2.forEach(node->{
						list.add(node.textValue());
					});
			return list;
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
}
