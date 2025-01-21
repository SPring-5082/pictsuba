package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

import beans.Customer;
import dao.CustomerDAO;
import exception.SQLDataNotFoundException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.DateLogic;
import model.EncryptionLogic;

@WebServlet("/account")
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String page = "WEB-INF/jsp/account.jsp";
	/**
	 * 現在の顧客情報の表示ページを返す
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String edit = request.getParameter("edit");
		RequestDispatcher dispatcher = null;
		if(edit == null) {
			dispatcher = request.getRequestDispatcher(page);
		}else {
			dispatcher = request.getRequestDispatcher("WEB-INF/jsp/account_edit.jsp");
		}
		dispatcher.forward(request, response);
		return;
	}
	
	/**
	 * 入力された顧客情報から顧客情報を更新し、
	 * 更新後の顧客情報を返す
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String password = null;
		try {
			password = EncryptionLogic.enc(request.getParameter("password"));
		} catch (IllegalBlockSizeException | BadPaddingException e) {}
		String phone = request.getParameter("phone");
		String mail = request.getParameter("mail");
		int age = -1;
		try {
			age = Integer.parseInt(request.getParameter("age"));
		}catch (NumberFormatException e) {}
		Date birth_day = DateLogic.valueOf(request.getParameter("birth_day"));
		String gender = request.getParameter("gender");
		HttpSession session = request.getSession();
		Customer user = (Customer)session.getAttribute("user");
		
		Customer update = new Customer(user.customer_id(), name, password, phone, mail, age, birth_day, gender, age, user.first_log(), new Date(), user.address_id(), user.card_id());
		try {
			CustomerDAO.updateByCustomer_id(update);
			user = CustomerDAO.findById(user.customer_id());
		} catch (SQLException | SQLDataNotFoundException e) {}
		
		
		//セッション顧客情報の更新
		//DB顧客情報の更新
		session.removeAttribute("user");
		session.setAttribute("user", user);
		doGet(request, response);
	}
	
}
