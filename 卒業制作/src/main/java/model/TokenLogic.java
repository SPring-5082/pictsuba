package model;

import java.security.SecureRandom;
import java.util.Base64;

public class TokenLogic {
	public static void main(String[] args) {
		for(int i = 0;i < 10;i ++) {
			System.out.println(generate());
		}
	}
	
	private static SecureRandom sr = new SecureRandom();
	public static String generate() {
		byte[] iv = new byte[16];
		sr.nextBytes(iv);
		return new String(Base64.getEncoder().encode(iv));
	}
	
}
