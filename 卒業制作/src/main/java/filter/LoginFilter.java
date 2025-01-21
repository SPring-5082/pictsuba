package filter;

import java.io.IOException;
import java.sql.SQLException;

import beans.Customer;
import exception.SQLDataNotFoundException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import model.LoginLogic;

@WebFilter("/*")
public class LoginFilter extends HttpFilter implements Filter {
	/**
	 * ログインしていない場合、クッキー内のセッションIDを用いたログインを試みる
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if(request instanceof HttpServletRequest) {
			HttpSession session = ((HttpServletRequest)request).getSession();
			if(!LoginLogic.isLogin(session)) {
				try {
					Customer customer = LoginLogic.doLogin((HttpServletRequest)request);
					if(customer != null) {
						session.setAttribute("user", customer);
					}
				} catch (SQLException | SQLDataNotFoundException e) {}
			}
		}
		chain.doFilter(request, response);
	}
	
}
