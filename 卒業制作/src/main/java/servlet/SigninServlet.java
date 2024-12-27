package servlet;

import java.io.IOException;
import java.sql.SQLException;

import beans.Customer;
import dao.CustomerDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/signin")
public class SigninServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * サインイン認証メソッド
	 * サインインページから情報を受け取り、
	 * 成功した場合はセッションにユーザ情報を登録し、
	 * 失敗した場合はサインインページを返す
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("page");
		String mail = request.getParameter("mail");
		String password  = request.getParameter("password");
		Customer user = null;
		try {
			user = CustomerDAO.findByMailandPass(mail, password);
		} catch (SQLException e) {}
		
		if(user != null) {
			session.setAttribute("user", user);
		}
		String page = null;
		if((page = request.getParameter("page")) != null) {
			response.sendRedirect(page);
		}else {
			request.setAttribute("error", true);
			RequestDispatcher dispatcher = request.getRequestDispatcher("signin/");
			dispatcher.forward(request, response);
		}
		
	}

}
