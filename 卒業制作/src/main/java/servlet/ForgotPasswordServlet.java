package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

import beans.Customer;
import beans.Mail;
import dao.CustomerDAO;
import exception.SQLDataNotFoundException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.EncryptionLogic;
import model.MailLogic;
import model.MessageGenerator;

@WebServlet("/forgotpassword")
public class ForgotPasswordServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	/**
	 * パスワードを再設定するためのページURLを
	 * 送信するためのmメールアドレスを入力するページを表示する
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String page = "WEB-INF/jsp/forgotpassword.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
	
	/**
	 * 入力されたメールアドレスにパスワード変更ページのURLを送信する
	 * 送信するURLには24時間のみ有効なtokenをパラメータに付加し、
	 * 送信済みであることを示すページを表示する
	 * メールアドレスがCUSTOMERSテーブルに存在しないまたは
	 * メールの送信に失敗した場合は失敗を示すページを表示する
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext application = request.getServletContext();
		//トークンの生成
		final String dist_address = request.getParameter("mail");
		String token = null;
		try {
			token = EncryptionLogic.enc(dist_address);
		} catch (IllegalBlockSizeException | BadPaddingException e) {}
		//メールの送信
		RequestDispatcher dispatcher = null;
		try {
			Customer user = CustomerDAO.findByMail(dist_address);
			final String message = MessageGenerator.generate(user.name(),(String)application.getAttribute("domain"),(String)application.getAttribute("application"),token);
			Mail mail = new Mail(dist_address, "パスワードの変更",message);
			if(MailLogic.execute(mail)) {
				response.addCookie(new Cookie("token", token));
				dispatcher = request.getRequestDispatcher("WEB-INF/jsp/mail_send.jsp");
			}else {
				request.setAttribute("link", "forgotpassword");
				dispatcher = request.getRequestDispatcher("WEB-INF/jsp/mail_failed.jsp");
			}
		} catch (SQLException | SQLDataNotFoundException e) {
			e.printStackTrace();
			request.setAttribute("link", "forgotpassword");
			dispatcher = request.getRequestDispatcher("WEB-INF/jsp/mail_failed.jsp");
		}
		dispatcher.forward(request, response);
	}

}
