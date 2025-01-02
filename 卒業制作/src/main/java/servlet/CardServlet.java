package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import beans.CreditCard;
import beans.Customer;
import dao.CreditCardDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.LoginLogic;

@WebServlet("/card")
public class CardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String page = "WEB-INF/jsp/card.jsp";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(LoginLogic.isLogin(session)) {
			Customer user = (Customer)session.getAttribute("user");
			List<CreditCard> creditCards = null;
			try {
				creditCards = CreditCardDAO.findByCustomer_id(user.customer_id());
			} catch (SQLException e) {}
			//リクエストスコープにカード情報を追加
			request.setAttribute("cards", creditCards);
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
			return;
		}else {
			//ログインページに移動
			session.setAttribute("page", "card");
			response.sendRedirect("signin/");
		}
	}
}
