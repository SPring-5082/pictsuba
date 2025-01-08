package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import beans.Customer;
import beans.Order;
import beans.Order_History;
import dao.OrderDAO;
import dao.Order_HistoryDAO;
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
		List<Order> orders = null;
		List<Order_History> order_histories = null;
		try {
			orders = OrderDAO.findByCustomer_id(user.customer_id());
			order_histories = Order_HistoryDAO.findByCustomr_id(user.customer_id());
		} catch (SQLException e) {}
		//リクエストスコープに履歴情報を追加
		request.setAttribute("orders", orders);
		request.setAttribute("order_histories", order_histories);
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
		return;
		
	}

}
