package model;

import jakarta.servlet.http.Cookie;

public class CookieLogic {
	
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
	
	public static String toJson(Cookie cookie) {
		return null;
	}
	
}
