package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.CookieLogic;

@WebServlet("/favorite")
public class FavoriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String paramKey = "product_id";
		final String product_id = request.getParameter(paramKey);
		final String cookieKey = "favorite";
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
