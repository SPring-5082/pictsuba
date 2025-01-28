package servlet;

import java.io.IOException;
import java.sql.SQLException;

import beans.Customer;
import dao.CustomerDAO;
import exception.SQLDataNotFoundException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.EncryptionLogic;
import model.SessionLogic;

@WebServlet("/signin")
public class SignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * サインイン認証メソッド
	 * サインインページから情報を受け取り、
	 * 成功した場合はセッションにユーザ情報を登録し、
	 * デフォルトでTOPページを表示し、
	 * ページ指定がある場合は指定ページを表示する。
	 * 失敗した場合は再度サインインを要求する。
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String page = request.getParameter("page");
		String mail = request.getParameter("mail");
		String password  = request.getParameter("password");
		try {
			password = EncryptionLogic.enc(password);
		} catch (Exception e) {}
		Customer user = null;
		try {
			user = CustomerDAO.findByMailandPass(mail, password);
		} catch (SQLException | SQLDataNotFoundException e) {}
		
		if(user != null) {
			session.setAttribute("user", user);
			final String session_id = SessionLogic.header + user.customer_id() + SessionLogic.footer;
			Cookie cookie = new Cookie("session_id", session_id);
			cookie.setPath("/");
			response.addCookie(cookie	);
		}
		if(user != null) {
			page = page == null? "/pictsuba/" : page ;
			response.sendRedirect(page);
		}else {
			response.sendRedirect("signin/?error=true" + (page != null?page:""));
		}
		
	}

}
