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

@WebServlet("/address")
public class AddressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String page = "WEB-INF/jsp";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Customer user = (Customer)session.getAttribute("user");
		List<Address> list = null;
		try {
			list = AddressDAO.findByCustomer_id(user.customer_id());
		} catch (SQLException e) {}
		RequestDispatcher dispatcher = null;
		if(list != null) {
			dispatcher = request.getRequestDispatcher(page);
		}else {
			dispatcher = request.getRequestDispatcher("top");
		}
		
		
	}

}
