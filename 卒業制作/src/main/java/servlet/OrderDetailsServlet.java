package servlet;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import beans.Address;
import beans.Order;
import beans.Product;
import dao.AddressDAO;
import dao.OrderDAO;
import dao.ProductDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/order-details")
public class OrderDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int order_id = Integer.parseInt(request.getParameter("order_id"));
			Order order = OrderDAO.findOrdersOverView(order_id);
			Address address = AddressDAO.findByAddress_id(order.address_id());
			int total_quantity = 0;
			
			Map<Product, Integer> map = ProductDAO.findByOrderID(order_id);
			Set<Product> products = map.keySet();
			for(Product p : products) {
				total_quantity += map.get(p);
			}
			request.setAttribute("order", order);
			request.setAttribute("address", address);
			request.setAttribute("products", products);
			request.setAttribute("map", map);
			request.setAttribute("total_quantity", total_quantity);
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/order_details.jsp");
			dispatcher.forward(request, response);
		}catch (Exception e) {
			e.printStackTrace();
			response.setStatus(response.SC_INTERNAL_SERVER_ERROR);
		}
		
	}

}
