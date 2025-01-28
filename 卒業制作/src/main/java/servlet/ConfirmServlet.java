package servlet;

import java.io.IOException;
import java.util.List;

import beans.Customer;
import beans.Product;
import dao.AddressDAO;
import dao.CartDAO;
import dao.CreditCardDAO;
import dao.ProductDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.CampaignLogic;
@WebServlet("/confirm")
public class ConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Customer user = (Customer)session.getAttribute("user");
		int sum_price = 0;
		int total_quantity = 0;
		try {
			int address_id = Integer.parseInt(request.getParameter("address_radio"));
			List<Product> products = ProductDAO.findByCustomerCart(user.customer_id());
			List<Integer> quantities = CartDAO.findQuantities(user.customer_id(), products);
			if(request.getParameter("pay_type").equals("card")) {
				request.setAttribute("pay_type", "card");
				int card_id = Integer.parseInt(request.getParameter("card"));
				request.setAttribute("card",CreditCardDAO.findById(card_id));
			}
			for(int i = 0;i < products.size();i ++) {
				sum_price += CampaignLogic.price(products.get(i).price(), products.get(i).category_id()) * quantities.get(i);
			}
			for(int i = 0; i< quantities.size();i ++) {
				total_quantity += quantities.get(i);
			}
			final String point_str = request.getParameter("point");
			int point = point_str != null && !point_str.equals("")?Integer.parseInt(point_str):0;
			request.setAttribute("total_quantity", total_quantity);
			request.setAttribute("sum_price", sum_price);
			request.setAttribute("address", AddressDAO.findByAddress_id(address_id));
			request.setAttribute("point", point);
			request.setAttribute("products", products);
			request.setAttribute("quantities", quantities);
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/confirm.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(response.SC_INTERNAL_SERVER_ERROR);
		}
	}

}
