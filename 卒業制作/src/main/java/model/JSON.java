package model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSON {
	
	String json;
	
	public JSON(String json) {
		this.json = json;
	}
	
	private JsonNode readTree() throws JsonMappingException, JsonProcessingException {
		ObjectMapper om = new ObjectMapper();
		return om.readTree(json);
	}
	
	public String getText(String key) throws JsonMappingException, JsonProcessingException {
		return readTree().get(key).textValue();
	}
	
	public List<String> getList(String key) {
		List<String> list = new ArrayList<String>();
		try {
			readTree().get(key).forEach(
				node->{
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
