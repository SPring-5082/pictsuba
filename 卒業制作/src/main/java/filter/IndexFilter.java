package filter;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import beans.Advertisement;
import beans.Customer;
import beans.Product;
import dao.AdvertisementDAO;
import dao.ProductDAO;
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

@WebFilter(urlPatterns = {"/index.jsp","/"})
public class IndexFilter extends HttpFilter implements Filter {
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		List<Advertisement> advertisements;
		try {
			advertisements = AdvertisementDAO.findAll();
			request.setAttribute("advertisements", advertisements);
		} catch (SQLException e) {}
		
		if(request instanceof HttpServletRequest) {
			HttpSession session = ((HttpServletRequest)request).getSession();
			if(LoginLogic.isLogin(session)) {
				Customer customer = (Customer)session.getAttribute("user");
				List<Product> recomends;
				try {
					recomends = ProductDAO.findByRecommendation(customer.customer_id());
					request.setAttribute("recomends", recomends);
				} catch (SQLException e) {}
			}else {
				List<Product> popularities = null;
				try {
					popularities = ProductDAO.findByPopularity();
					request.setAttribute("popularities", popularities);
				} catch (SQLException e) {}
			}
		}
		chain.doFilter(request, response);
	}
}
