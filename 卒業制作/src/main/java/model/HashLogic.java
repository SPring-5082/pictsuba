package model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashLogic {
	public static String execute(String password) {
		StringBuffer sb = new StringBuffer();
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] cipherBytes = md.digest(password.getBytes());
			
			for (int i=0; i<cipherBytes.length; i++) {
				sb.append(String.format("%02x", cipherBytes[i]&0xff));
			}
		} catch (NoSuchAlgorithmException ex1) {
			throw new IllegalArgumentException("ハッシュアルゴリズム名が不正です");
		} catch (NullPointerException ex2) {
			throw new IllegalArgumentException("ハッシュアルゴリズム名が指定されていません");
		}
		return sb.toString();
	}
}
