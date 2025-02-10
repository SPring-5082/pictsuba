package servlet.api;

import java.io.IOException;
import java.sql.SQLException;

import dao.OrderDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RemoveOrderServlet
 */
public class RemoveOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int order_id = Integer.parseInt(request.getParameter("order_id"));
			OrderDAO.deleteOrder(order_id);
			
		}catch (NumberFormatException e) {
			response.sendRedirect("order-history");
			return;
		}catch (SQLException e) {
			response.setStatus(response.SC_INTERNAL_SERVER_ERROR);
			return;
		}
	}

}
