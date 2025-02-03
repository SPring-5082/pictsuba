package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import beans.Customer;
import beans.Order;
import dao.OrderDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/order-history")
public class Order_HistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String page = "WEB-INF/jsp/order_history.jsp";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Customer user = (Customer)session.getAttribute("user");
		try {
			List<Order> orders = OrderDAO.findByCustomer_id(user.customer_id());
			request.setAttribute("orders", orders);
			request.setAttribute("orders", orders);
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			response.setStatus(response.SC_INTERNAL_SERVER_ERROR);
		}
	}

}
