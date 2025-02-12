package model;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class KeyStorage {
	private static Cipher cryptoCipher, decryptoCipher;
	private static SecretKey secretKey = null;
	private static byte[] iv = null;
	private static IvParameterSpec ivParameter = null;
	private static final String padding = "AES/CBC/PKCS5Padding";
	/**
	 * 外部から読み込んだ情報をもとに鍵を作成する
	 * @param KEY_String エンコードされた暗号化鍵
	 * @param IV_String エンコードされたiv(byte[])
	 */
	public static void init(String KEY_String,String IV_String){
		KeyGenerator kg = null;
		try {
			// 初期化
			kg = KeyGenerator.getInstance("AES");
			kg.init(128);
			// 既存のキーとIVを読み込む			// Base64文字列をデコードしてバイト配列に変換
			byte[] decodedKey = Base64.getDecoder().decode(KEY_String);
			byte[] decodedIV = Base64.getDecoder().decode(IV_String);
			// バイト配列からSecretKeyとIVを生成
			secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
			iv = decodedIV;
			ivParameter = new IvParameterSpec(iv);
			// 暗号化および復号化の設定
			
			
			decryptoCipher = Cipher.getInstance(padding);
			decryptoCipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameter);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 暗号化鍵をStringに変換して保存
	 * @param secretKey 変換対象の暗号化鍵
	 * @return 変換後のString値
	 */
	public static String keyToString(SecretKey secretKey) {
		return Base64.getEncoder().encodeToString(secretKey.getEncoded());
	}
	
	/**
	 * 鍵に使用するIV(byte[])をStringに変換して保存
	 * @param iv 変換対象のbyte配列
	 * @return 変換後のString値
	 */
	public static String ivToString(byte[] iv) {
		return Base64.getEncoder().encodeToString(iv);
	}
	
	/**
	 * 暗号化に使用するCipherの取得
	 * @return 暗号化に使用するCipher
	 */
	static Cipher encCipher() {
		try {
			cryptoCipher = Cipher.getInstance(padding);
			cryptoCipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameter);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}
		return cryptoCipher;
	}
	/**
	 * 復号に使用するCipherの取得
	 * @return 復号に使用するCipher
	 */
	static Cipher decCipher() {
		try {
			decryptoCipher = Cipher.getInstance(padding);
			decryptoCipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameter);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}
		return decryptoCipher;
	}
	
}
