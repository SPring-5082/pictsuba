package filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.LimitedPage;
import model.LoginLogic;

@WebFilter("/*")
public class SigninFilter extends HttpFilter implements Filter {
	/**
	 * ログインが必要なページにアクセスする際、
	 * ログインしていなければ、ログインを要求する
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		HttpSession session = httpRequest.getSession();
		String page = httpRequest.getRequestURI().replaceFirst((String)httpRequest.getServletContext().getAttribute("application"), "").replaceAll("/", "");
		if(LimitedPage.isLimited(page)) {
			if(LoginLogic.isLogin(httpRequest.getSession())){
				session.removeAttribute("page");
			}else {
				//ログインページに移動
				httpResponse.sendRedirect("signin/?page=" + page);
				return;
			}
		}
		chain.doFilter(request, response);
	}
	
}
