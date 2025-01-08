package filter;

import java.io.IOException;

import beans.Customer;
import beans.Favorite;
import dao.FavoriteDAO;
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

@WebFilter("/favorite")
public class FavoriteFilter extends HttpFilter implements Filter {
	/**
	 * カートページを表示する際、現在Cookie内に存在するお気に入り情報を
	 * DBに反映させ、Cookie内のお気に入り情報をリセットするメソッド
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpRResponse = (HttpServletResponse)response;
		HttpSession session = httpRequest.getSession();
		Customer user = (Customer)session.getAttribute("user");
		Cookie[] cookies = httpRequest.getCookies();
		final String key = "favorite";
		if(CookieLogic.existKey(key, cookies)) {
			Cookie favoriteCookie = CookieLogic.getCookie(key, cookies);
			int[] favorite = ArrayLogic.decode(favoriteCookie.getValue());
			for(int id : favorite) {
				try {
					FavoriteDAO.insert(new Favorite(user.customer_id(), id));
				}catch (Exception e) {e.printStackTrace();}
			}
		}
		Cookie cookie = new Cookie(key, "");
		cookie.setMaxAge(0);
		httpRResponse.addCookie(cookie);
		chain.doFilter(request, response);
	}
	
}
