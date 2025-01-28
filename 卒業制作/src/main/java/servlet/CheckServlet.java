package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import beans.Address;
import beans.CreditCard;
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

@WebServlet("/check")
public class CheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * 購入情報確認ページを表示する
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Customer user = (Customer)session.getAttribute("user");
		int sum_price = 0;
		int total_quantity = 0;
		try {
			List<Product> products = ProductDAO.findByCustomerCart(user.customer_id());
			List<Integer> quantities = CartDAO.findQuantities(user.customer_id(), products);
			List<Address> addresses = AddressDAO.findByCustomer_id(user.customer_id());
			List<CreditCard> cards = CreditCardDAO.findByCustomer_id(user.customer_id());
			request.setAttribute("products", products);
			request.setAttribute("quantities",quantities);
			for(int i = 0;i < products.size();i ++) {
				sum_price += CampaignLogic.price(products.get(i).price(), products.get(i).category_id()) * quantities.get(i);
			}
			for(int i = 0; i< quantities.size();i ++) {
				total_quantity += quantities.get(i);
			}
			request.setAttribute("sum_price", sum_price);
			request.setAttribute("total_quantity", total_quantity);
			request.setAttribute("addresses", addresses);
			request.setAttribute("cards", cards);
		}catch (SQLException e) {e.printStackTrace();}
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/check.jsp");
		dispatcher.forward(request, response);
	}

}
