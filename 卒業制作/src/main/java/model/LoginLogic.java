package model;

import beans.Customer;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class LoginLogic {
	
	
	/**
	 * ログイン状態の判別
	 * @param session ユーザーのセッション
	 * @return ログイン状態
	 */
	public static boolean isLogin(HttpSession session) {
		return session.getAttribute("user") != null;
	}
	
	/**
	 * クッキー情報によるログインの試行
	 * @param request リクエストパラメーター
	 * @return クッキー情報から取得した顧客情報(nullの場合はクッキーがない、または期限切れ)
	 */
	public static Customer doLogin(HttpServletRequest request) {
		Customer customer = null;
		Cookie[] cookies = request.getCookies();
		if(cookies == null)return null;
		String session_id = CookieLogic.getValueFromKey("session_id", cookies);
		customer = SessionLogic.execute(session_id);
		if(customer != null) {
			return customer;
		}
		return null;
	}
	
}
