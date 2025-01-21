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
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Customer user = (Customer)session.getAttribute("user");
		final String zip_code = request.getParameter("zip_code");
		final String pref = request.getParameter("pref");
		final String municipalities  =request.getParameter("municipalities");
		final String street = request.getParameter("street");
		final String building = request.getParameter("building");
		int customer_id = user.customer_id();
		Address address = new Address(customer_id, zip_code, pref, municipalities, street, building);
		try {
			AddressDAO.insert(address);
			doGet(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			response.setStatus(response.SC_NOT_FOUND);
		}
		return;
	}

}
