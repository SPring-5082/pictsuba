package servlet;

import java.io.IOException;
import java.util.List;

import beans.Cart;
import beans.Customer;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.AddOrderLogic;
import model.PaymentLogic;

@WebServlet("/completion")
public class PaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final String page = "WEB-INF/jsp/completion.jsp";
	/**
	 * 注文内容の確定を受け、支払いと
	 * 注文の確定・情報登録を行うメソッド
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Customer user = (Customer)session.getAttribute("user");
		int card_id = Integer.parseInt(request.getParameter("card_id"));
		int address_id = Integer.parseInt(request.getParameter("address_id"));
		int use_point = Integer.parseInt(request.getParameter("point"));
		List<Cart> carts = (List<Cart>)session.getAttribute("cart");
		PaymentLogic.execute(user, carts, card_id, use_point);
		AddOrderLogic.execute(user.customer_id(), address_id, carts);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
	
}
