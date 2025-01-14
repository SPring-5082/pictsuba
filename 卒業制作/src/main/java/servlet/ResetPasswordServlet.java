package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

import dao.CustomerDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.EncryptionLogic;

@WebServlet("/reset-pass")
public class ResetPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String page = "WEB-INF/jsp/reset_password.jsp";
	/**
	 * トークン情報がある場合、トークン情報に該当するユーザーの
	 * パスワード変更ページを表示する
	 * tokenが指定されていない、または
	 * トークン情報がアプリケーションスコープにない場合は
	 * ページを表示させない
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext application = request.getServletContext();
		final String token = request.getParameter("token");
		final String dist_address = token != null? (String)application.getAttribute(token) : null;
		System.out.println(token);
		System.out.println(dist_address);
		if(token == null || dist_address == null) {
			response.setStatus(response.SC_NOT_FOUND);
			return;
		}
		request.setAttribute("token", token);
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
		
	}
	
	/**
	 * パスワード更新ページで入力された
	 * 新しいパスワードのDBの更新を行い、
	 * 結果ページを返す
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext application = request.getServletContext();
		final String token = request.getParameter("token");
		application.removeAttribute(token);
		final String mail = new String(Base64.getDecoder().decode(token));
		String password = null;
		try {
			password = EncryptionLogic.enc(request.getParameter("password"));
			CustomerDAO.updatePasswordByMail(mail, password);
		} catch (IllegalBlockSizeException | BadPaddingException | SQLException e) {}
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/reset_success.jsp");
		dispatcher.forward(request, response);
	}
	
}
