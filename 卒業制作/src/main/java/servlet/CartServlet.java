package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Cart;
import beans.Customer;
import beans.Product;
import dao.ProductDAO;
import exception.SQLDataNotFoundException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.CustomerLogic;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final String page = "WEB-INF/jsp/cart.jsp";
	/**
	 * 顧客のカート内情報のページを返す
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Customer user = (Customer)session.getAttribute("user");
		List<Cart> carts = null;
		try {
			carts = CustomerLogic.getCarts(user.customer_id());
			List<Product> products = new ArrayList<Product>();
			List<Integer> quantities = new ArrayList<Integer>();
			for(Cart cart : carts) {
				products.add(ProductDAO.findById(cart.product_id()));
				quantities.add(cart.quantity());
			}
			request.setAttribute("products", products);
			request.setAttribute("quantities", quantities);
		} catch (SQLException | SQLDataNotFoundException e) {}
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
	
	
	
}
