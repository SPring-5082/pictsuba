package filter;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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
import model.CookieLogic;

@WebFilter("/cart")
public class CartPageFilter extends HttpFilter implements Filter {
	/**
	 * カートページを表示する際、現在Cookie内に存在するカート情報を
	 * DBに反映させ、Cookie内のカート情報をリセットするメソッド
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest hsr = (HttpServletRequest)request;
		HttpSession session = hsr.getSession();
		Customer user = (Customer)session.getAttribute("user");
		Cookie[] cookies = hsr.getCookies();
		String key = "cart";
		if(CookieLogic.existKey(key, cookies)) {
			Cookie cookie = CookieLogic.getCookie(key, cookies);
			List<Integer> product_ids =  CookieLogic.toIdList(cookie.getValue());
			for(Integer product_id : product_ids) {
				Cart cart = new Cart(product_id, user.customer_id(), 1);
				try {
					CartDAO.insert(cart);
				} catch (SQLException e) {}
			}
			//Cookie内カート情報の削除
			cookie.setMaxAge(0);
			((HttpServletResponse)response).addCookie(cookie);
		}
		
		chain.doFilter(request, response);
	}
	
}
