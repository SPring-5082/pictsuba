package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

import beans.Customer;
import dao.CustomerDAO;
import exception.SQLDataNotFoundException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.EncryptionLogic;

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String name = request.getParameter("name");
		String password = null;
		try {
			password = EncryptionLogic.enc(request.getParameter("password"));
		} catch (IllegalBlockSizeException | BadPaddingException e) {}
		final String phone = request.getParameter("phone");
		final String mail = request.getParameter("mail");
		int age = -1;
		try{
			age = Integer.parseInt(request.getParameter("age"));
		}catch (Exception e) {}
		Date birth_day = null;
		final String date = request.getParameter("birth_day");
		if(date != null && !date.equals("")) {
			birth_day = java.sql.Date.valueOf(date);
		}
		final String geneder = request.getParameter("gender");
		Customer customer = new Customer(name, password, phone, mail, age, birth_day, geneder);
		try {
			CustomerDAO.insert(customer);
		} catch (SQLException e) {
			response.sendRedirect("signup/?error=true");
			return;
		}
		HttpSession session = request.getSession();
		Customer user = null;
		try {
			user = CustomerDAO.findByMailandPass(mail, password);
		} catch (SQLException | SQLDataNotFoundException e) {}
		session.setAttribute("user", user);
		response.sendRedirect("/pictsuba/");
	}
	
}
