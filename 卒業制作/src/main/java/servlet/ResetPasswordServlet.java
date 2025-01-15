package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

import beans.Customer;
import dao.CustomerDAO;
import exception.SQLDataNotFoundException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.CookieLogic;
import model.EncryptionLogic;

@WebServlet("/reset-pass")
public class ResetPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String page = "WEB-INF/jsp/reset_password.jsp";
	/**
	 * トークン情報がある場合、トークン情報に該当するユーザーの
	 * パスワード変更ページを表示する
	 * tokenが指定されていない、または
	 * トークン情報が不正な場合、
	 * ページを表示させない
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext application = request.getServletContext();
		final String token = request.getParameter("token");
		boolean flag = false;
		Customer user = null;
		try {
			user = CustomerDAO.findByMail(EncryptionLogic.dec(token));
		} catch (IllegalBlockSizeException | BadPaddingException | SQLException | SQLDataNotFoundException e) {}
		if(user != null & token.equals(CookieLogic.getValueFromKey("token", request.getCookies()))) {
			flag = true;
		}
		if(flag) {
			request.setAttribute("token", token);
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		}else {
			response.setStatus(response.SC_NOT_FOUND);
		}
		return;
		
	}
	
	/**
	 * パスワード更新ページで入力された
	 * 新しいパスワードのDBの更新を行い、
	 * 結果ページを返す
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String token = request.getParameter("token");
		String mail = null;
		try {
			mail = EncryptionLogic.dec(token);
		} catch (IllegalBlockSizeException | BadPaddingException e) {}
		String password = null;
		try {
			password = EncryptionLogic.enc(request.getParameter("password"));
			CustomerDAO.updatePasswordByMail(mail, password);
		} catch (IllegalBlockSizeException | BadPaddingException | SQLException e) {}
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/reset_success.jsp");
		dispatcher.forward(request, response);
	}
	
}
