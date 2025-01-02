package model;

import java.util.ArrayList;
import java.util.List;
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
		Map<String, String> map = new TreeMap();
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
	 * cartやfavoriteなどの[ number/number/… ]Cookie内に格納された商品IDリスト情報を
	 * IDのリスト形式で返すメソッド
	 * @param cookieValue Cookieの値
	 * @return IDのリスト
	 */
	public static List<Integer> toIdList(String cookieValue){
		List<Integer> id_list = new ArrayList<Integer>();
		StringBuilder sb = new StringBuilder();
		for(char c : cookieValue.toCharArray()) {
			if(c == '/') {
				id_list.add(Integer.parseInt(sb.toString()));
				sb = new StringBuilder();
			}else {
				sb.append(c);
			}
		}
		return id_list;
	}
	
	/**
	 * cartやfavoriteなどの[ number/number/… ]Cookie内に格納された商品IDリスト情報の
	 * 末尾に新しく商品IDを追加するメソッド
	 * @param cookie 現在のCookie情報
	 * @param product_id 新しく追加する商品ID
	 * @return 更新したCookie
	 */
	public static Cookie addId(Cookie cookie, int product_id) {
		//if(cookie == null)throw new NullPointerException("");
		final String key = cookie.getName();
		String value = cookie.getValue();
		value += String.valueOf(product_id).concat("/");
		return new Cookie(key, value);
	}
	/*
	public static String toJson(Cookie[] cookies) {
		StringBuilder sb = new StringBuilder();
		final String HEAD = "{";
		final String FORMAT = '\"' + "%" + '\"';
		final String FOOT = "}";
		for(int i = 0;i < cookies.length;i ++) {
			String key = FORMAT.replaceFirst("%", cookies[i].getName());
			String value = toJsonValue(cookies[i].getValue());
			try {
				Integer.parseInt(value);
			}catch (Exception e) {
				if(value.indexOf("[") < 0) {
					value = FORMAT.replaceFirst("%", value);
				}
			}
			sb.append(key).append(':').append(value);
			if(i != cookies.length-1) {
				sb.append(',');
			}
		}
		return HEAD + sb.toString() + FOOT;
	}*/
	/*
	private static String toJsonValue(String cValue) {
		String value = null;
		if(cValue.indexOf("/") >= 0) {
			value = cValue.replaceAll("/", ",");
			return "[" + value + "]";
		}else {
			return cValue;
		}
	}*/
	
}
