package filter;

import java.io.IOException;

import beans.Cart;
import beans.Customer;
import dao.CartDAO;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.ArrayLogic;
import model.CookieLogic;

@WebFilter("/cart")
public class CartFilter extends HttpFilter implements Filter {
	/**
	 * カートページを表示する際、現在Cookie内に存在するカート情報を
	 * DBに反映させ、Cookie内のカート情報をリセットするメソッド
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpRResponse = (HttpServletResponse)response;
		HttpSession session = httpRequest.getSession();
		Customer user = (Customer)session.getAttribute("user");
		Cookie[] cookies = httpRequest.getCookies();
		if(user != null) {
			final String key = "cart";
			if(CookieLogic.existKey(key, cookies)) {
				Cookie cartCookie = CookieLogic.getCookie(key, cookies);
				int[] cart = ArrayLogic.decode(cartCookie.getValue());
				for(int id : cart) {
					try {
						CartDAO.insert(new Cart(user.customer_id(), id ,1));
					}catch (Exception e) {e.printStackTrace();}
				}
			}
			Cookie cookie = new Cookie(key, "");
			cookie.setPath("/");
			cookie.setMaxAge(0);
			httpRResponse.addCookie(cookie);
		}
		chain.doFilter(request, response);
	}
	
}
