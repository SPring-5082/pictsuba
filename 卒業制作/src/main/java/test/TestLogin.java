package test;

import java.io.IOException;
import java.sql.SQLException;

import beans.Customer;
import dao.CustomerDAO;
import exception.SQLDataNotFoundException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/tesLog")
public class TestLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			Customer user = CustomerDAO.findByMailandPass("oibc34069@ojs.ac.jp", "713bfda78870bf9d1b261f565286f85e97ee614efe5f0faf7c34e7ca4f65baca");
			session.setAttribute("user", user);
		} catch (SQLException | SQLDataNotFoundException e) {}
		response.sendRedirect("top");
	}

}
