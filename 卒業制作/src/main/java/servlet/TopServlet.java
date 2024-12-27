package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import beans.Advertisement;
import beans.Customer;
import beans.Product;
import dao.AdvertisementDAO;
import dao.ProductDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.LoginLogic;

@WebServlet("/top")
public class TopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = "WEB-INF/jsp/index.jsp";
		HttpSession session = request.getSession();
		
		//広告の取得
		List<Advertisement> advertisements;
		try {
			advertisements = AdvertisementDAO.findAll();
			request.setAttribute("advertisements", advertisements);
		} catch (SQLException e) {}
		
		//人気の商品またはおすすめの商品の取得
		if(LoginLogic.isLogin(session)) {
			Customer customer = (Customer)session.getAttribute("user");
			List<Product> recomends;
			try {
				recomends = ProductDAO.findByRecommendation(customer.customer_id());
				request.setAttribute("recomends", recomends);
			} catch (SQLException e) {}
		}else {
			List<Product> popularities;
			try {
				popularities = ProductDAO.findByPopularity();
				request.setAttribute("popularities", popularities);
			} catch (SQLException e) {}
		}
		
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
	
}
