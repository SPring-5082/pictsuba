package model;

import java.util.Map;
import java.util.TreeMap;

import jakarta.servlet.http.Cookie;

public class CookieLogic {
	/**
	 * Cookie配列から任意のキー値を持つ
	 * Cookieを返すメソッド
	 * @param key キー値
	 * @param cookies 検索対象のCookie配列
	 * @return キー値に対応するCookie
	 */
	public static Cookie getCookie(String key, Cookie[] cookies) {
		if(cookies == null)return null;
		if(!existKey(key, cookies))return null;
		Map<String, String> map = toMap(cookies);
		return new Cookie(key, map.get(key));
	}
	
	/**
	 * Cookie配列をCookieのキーをMapのキー、
	 * Cookieの値をMapの値としてMap形式で返すメソッド
	 * @param cookies
	 * @return Cookieを変換したMap
	 */
	public static Map<String, String> toMap(Cookie[] cookies){
		if(cookies == null)return null;
		Map<String, String> map = new TreeMap<String, String>();
		for(Cookie cookie : cookies) {
			map.put(cookie.getName(), cookie.getValue());
		}
		return map;
	}
	
	/**
	 * Cookieの配列内から任意のキー値に基づいて
	 * 値を返すメソッド
	 * @param key Cookieのキー値
	 * @param cookies 検索対象のクッキーの配列
	 * @return キー値に対応する値
	 */
	public static String getValueFromKey(String key, Cookie[] cookies) {
		for(Cookie cookie : cookies) {
			if(cookie.getName().equals(key))return cookie.getValue();
		}
		return null;
	}
	
	/**
	 * Cookieの配列内に任意のキー値を含むか
	 * 判定するメソッド
	 * @param key Cookieのキー値
	 * @param cookies 検索対象のクッキーの配列
	 * @return キー値の有無の真偽
	 */
	public static boolean existKey(String key, Cookie[] cookies){
		/*配列がnullの場合クッキーそのものが存在しないとみなす*/
		if(cookies == null)return false;
		
		for(Cookie cookie : cookies) {
			if(cookie.getName().equals(key))return true;
		}
		return false;
	}
	
	/**
	 * Base64にエンコードされた商品IDのCookie情報の
	 * 末尾に新しく商品IDを追加するメソッド
	 * @param cookie 現在のCookie情報
	 * @param product_id 新しく追加する商品ID
	 * @return 更新したCookie
	 */
	public static Cookie addId(Cookie cookie, int product_id) {
		if(cookie == null)return cookie;
		final String key = cookie.getName();
		int[] array = ArrayLogic.decode(cookie.getValue());
		array = ArrayLogic.addEnd(array, product_id);
		final String value = ArrayLogic.encode(array);
		return new Cookie(key, value);
	}
	
}
