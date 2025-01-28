package filter;

import java.io.IOException;
import java.sql.SQLException;

import beans.Cart;
import beans.Customer;
import dao.CartDAO;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@WebFilter("/check")
public class CheckFilter extends HttpFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest hsr = (HttpServletRequest)request;
		HttpSession session = hsr.getSession();
		Customer user = (Customer)session.getAttribute("user");
		int customer_id = user.customer_id();
		hsr.getParameterNames().asIterator().forEachRemaining(e -> {
			int product_id = Integer.parseInt(e);
			int quantity = Integer.parseInt(hsr.getParameter(e));
			Cart cart = new Cart(customer_id, product_id, quantity);
			try {
				CartDAO.updateQuantityByCustomer_id(cart);
			} catch (SQLException e1) {}
		});
		chain.doFilter(request, response);
	}

}
