package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import beans.Cart;
import beans.Customer;
import beans.Product;
import dao.CartDAO;
import dao.CustomerDAO;
import dao.ProductDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.CampaignLogic;
import model.OrderCommitLogic;

@WebServlet("/completion")
public class CompletionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final String page = "WEB-INF/jsp/completion.jsp";
	/**
	 * 注文内容の確定を受け、支払いと
	 * 注文の確定・情報登録を行うメソッド
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Customer user = (Customer)session.getAttribute("user");
		
		try {
			int card_id = Integer.parseInt(request.getParameter("card_id"));
			CustomerDAO.updateCard_id(user.customer_id(), card_id);
		}catch (Exception e) {}
		int address_id = Integer.parseInt(request.getParameter("address_id"));
		int use_point = Integer.parseInt(request.getParameter("point"));
		try {
			List<Product> products = ProductDAO.findByCustomerCart(user.customer_id());
			int point = 0;
			for(Product p : products) {
				point += CampaignLogic.point(p.price(), p.category_id());
			}
			List<Cart> carts = CartDAO.findByCustomer_id(user.customer_id());
			OrderCommitLogic.commit(carts, user.customer_id(), address_id);
			CustomerDAO.updateAddress_id(user.customer_id(), address_id);
			CustomerDAO.updatePoint(user.customer_id(), user.point()+point-use_point);
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		}catch (SQLException e) {
			response.setStatus(response.SC_INTERNAL_SERVER_ERROR);
		}
	}
	
}
