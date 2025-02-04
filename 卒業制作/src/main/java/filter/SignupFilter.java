package filter;

import java.io.IOException;

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
import model.CookieLogic;

@WebFilter(urlPatterns = {"/signup/index.jsp","/signup/"})
public class SignupFilter extends HttpFilter implements Filter {
	/**
	 * パラメータとCookieから取得したtokenが不正であればページを表示させない
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		final String token = request.getParameter("token");
		Cookie[] cookies = ((HttpServletRequest)request).getCookies();
		boolean pageflag = false;
		if(CookieLogic.existKey("token", cookies)) {
			if(CookieLogic.getValueFromKey("token", cookies).equals(token)) {
				pageflag = true;
			}
		}
		if(pageflag == false) {
			((HttpServletResponse)response).setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		chain.doFilter(request, response);
		
	}
	
}
