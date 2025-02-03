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

@WebServlet("/api/cart")
public class AddCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/*
	 *
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String product_id = request.getParameter("product_id");
		if(product_id == null)return;
		final String key = "cart";
		Cookie[] cookies = request.getCookies();
		Cookie cookie = null;
		if(CookieLogic.existKey(key,cookies)) {
			//既にcookienにある場合return
			if(ArrayLogic.exisits(ArrayLogic.decode(CookieLogic.getValueFromKey(key, cookies)), Integer.parseInt(product_id)))return;
			cookie = CookieLogic.getCookie(key, cookies);
			cookie = CookieLogic.addId(cookie, Integer.parseInt(product_id));
		}else {
			cookie = new Cookie(key, ArrayLogic.encode(new int[]{Integer.parseInt(product_id)}));
		}
		cookie.setPath("/");
		cookie.setMaxAge(60 * 60 * 24 * 31);
		response.addCookie(cookie); 
		
	}

}
