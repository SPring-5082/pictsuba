package model;

import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
/**
 * @author 高鳥 遥
 */
public class EncryptionLogic {
	/**
	 * 引数の文字列を暗号化し、
	 * 得られるbyte配列をエンコードした文字列を返す
	 * @param pass 暗号化対象の文字列
	 * @return 暗号化された文字列
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public static String enc(String pass) throws IllegalBlockSizeException, BadPaddingException {
		byte[] encryptedBytes = KeyStorage.encCipher().doFinal(pass.getBytes());
		return Base64.getEncoder().encodeToString(encryptedBytes);
	}
	
	/**
	 * 暗号化&エンコードされた文字列を受け取り、
	 * デコード、復号した文字列を返す
	 * @param encText 復号対象の文字列
	 * @return 原文
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public static String dec(String encText) throws IllegalBlockSizeException, BadPaddingException {
		byte[] decodedBytes = Base64.getDecoder().decode(encText);
		return new String(KeyStorage.decCipher().doFinal(decodedBytes));
	}
	
}
