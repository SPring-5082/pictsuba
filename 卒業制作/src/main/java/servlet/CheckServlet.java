package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Address;
import beans.Cart;
import beans.CreditCard;
import beans.Customer;
import beans.Product;
import dao.AddressDAO;
import dao.CreditCardDAO;
import dao.ProductDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.CheckLogic;

@WebServlet("/check")
public class CheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * カートページで選択された商品を
	 * セッションのカートに追加し、
	 * 購入情報確認ページを表示する
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Customer user = (Customer)session.getAttribute("user");
		List<Cart> cart = CheckLogic.execute(request);
		session.setAttribute("cart", cart);
		try {
			List<Product> products = new ArrayList<>();
			List<Integer> quantities = new ArrayList<>();
			cart.forEach(element -> {
				try {
					products.add(ProductDAO.findById(element.product_id()));
				} catch (SQLException e) {}
				quantities.add(element.quantity());
			});
			List<Address> addresses = AddressDAO.findByCustomer_id(user.customer_id());
			List<CreditCard> cards = CreditCardDAO.findByCustomer_id(user.customer_id());
			
			request.setAttribute("addresses", addresses);
			request.setAttribute("cards", cards);
		}catch (SQLException e) {}
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/check.jsp");
		dispatcher.forward(request, response);
	}

}
