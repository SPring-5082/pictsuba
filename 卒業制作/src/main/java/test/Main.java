package test;

import java.util.Map;
import java.util.TreeMap;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

public class Main {
	public static void main(String[] args) throws IllegalBlockSizeException, BadPaddingException {
		//KeyStorage.init("O52HO0G5C3NtkegLeQE0Kg==", "jxCxl18YNgUEY97WEhbE/Q==");
		//System.out.println(EncryptionLogic.enc("Takatori5082"));
		String name = "product_id:9";
		String quantity = "quantity:9;";
		Map<String, String> map = new TreeMap<String, String>();
		map.put(quantity, "12");
		System.out.println("" + name.replaceFirst("product_id:", ""));
		
		
	}
}
