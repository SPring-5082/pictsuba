package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.CookieLogic;

@WebServlet("/cookie")
public class CookieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * リクエスト情報をもとに
	 * クッキーにカート情報を
	 * 追加、更新するメソッド
	 * @param request ブラウザからのリクエスト
	 * @param response サーバからのレスポンス
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String paramKey = "product_id";
		final String product_id = request.getParameter(paramKey);
		final String cookieKey = "cart";
		Cookie[] cookies = request.getCookies();
		Cookie cookie = null;
		if(CookieLogic.existKey(cookieKey,cookies)) {
			cookie = 
				new Cookie(cookieKey, CookieLogic.getValueFromKey(cookieKey,cookies) + '/' + product_id);
		}else {
			cookie = new Cookie(cookieKey, product_id);
		}
		response.addCookie(cookie);
		System.out.println(cookie.getName() + " : " + cookie.getValue());
	}

}
