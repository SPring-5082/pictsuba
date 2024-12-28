package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import beans.Address;
import beans.Customer;
import dao.AddressDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.LoginLogic;

@WebServlet("/address")
public class AddressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String page = "WEB-INF/jsp/address.jsp";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(LoginLogic.isLogin(session)) {
			List<Address> addresses = null;
			Customer user = (Customer)session.getAttribute("user");
			try {
				addresses = AddressDAO.findByCustomer_id(user.customer_id());
			} catch (SQLException e) {}
			request.setAttribute("addresses", addresses);
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		}else {
			//ログインページに移動
			session.setAttribute("page", "address");
			response.sendRedirect("signin/");
		}
		
		
		
	}

}
