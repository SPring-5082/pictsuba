package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import beans.Customer;
import beans.Product;
import dao.ProductDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/favorite")
public class FavoriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * 顧客のお気に入りリストを表示する
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Customer user = (Customer)session.getAttribute("user");
		try {
			List<Product> products = ProductDAO.findByCustomerFavorite(user.customer_id());
			request.setAttribute("products", products);
		} catch (SQLException e) {}
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/favorite.jsp");
		dispatcher.forward(request, response);
		
	}
	
}
