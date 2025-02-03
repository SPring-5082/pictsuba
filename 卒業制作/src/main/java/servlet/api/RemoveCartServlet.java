package servlet.api;

import java.io.IOException;

import beans.Cart;
import beans.Customer;
import dao.CartDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/api/rm-cart")
public class RemoveCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Customer user = (Customer)session.getAttribute("user");
		int product_id = Integer.parseInt(request.getParameter("product_id"));
		try {
			Cart cart = new Cart(user.customer_id(), product_id, 0);
			CartDAO.delByCustomerANDProduct_Id(cart);
		}catch (Exception e) {e.printStackTrace();}
	}

}
