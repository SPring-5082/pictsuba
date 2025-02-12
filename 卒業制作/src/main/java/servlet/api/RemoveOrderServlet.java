package servlet.api;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import beans.Cart;
import dao.OrderDAO;
import dao.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/api/rm-order")
public class RemoveOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int order_id = Integer.parseInt(request.getParameter("order_id"));
			List<Cart> cart = OrderDAO.findByOrder_id(order_id);
			cart.forEach(element -> {
				try {
					ProductDAO.updateAddStock(element);
				} catch (SQLException e) {}
			});
			boolean result = OrderDAO.deleteOrder(order_id);
			response.setContentType("application/json; charset=UTF-8");
			final String JSON = 
			  "{"
			+ "		\"result\":" + result
			+ "}"
			;
			response.getWriter().write(JSON);
		}catch (NumberFormatException e) {
			response.sendRedirect("order-history");
			return;
		}catch (SQLException | IOException e) {
			e.printStackTrace();
			response.setStatus(response.SC_INTERNAL_SERVER_ERROR);
			return;
		}
	}

}
