package servlet.api;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ArrayLogic;
import model.CookieLogic;

@WebServlet("/api/favorite")
public class AddFavoriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * 現在のお気に入り情報をCookieから取得し、新しい商品IDを加えた
	 * Cookieを新しくセットするメソッド
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String product_id = request.getParameter("product_id");
		if(product_id == null)return;
		final String key = "favorite";
		Cookie[] cookies = request.getCookies();
		Cookie cookie = null;
		if(CookieLogic.existKey(key,cookies)) {
			cookie = CookieLogic.getCookie(key, cookies);
			cookie = CookieLogic.addId(cookie, Integer.parseInt(product_id));
		}else {
			cookie = new Cookie(key, ArrayLogic.encode(new int[]{Integer.parseInt(product_id)}));
		}
		response.addCookie(cookie);
		
	}
	
}
