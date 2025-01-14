package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Timer;
import java.util.TimerTask;

import beans.Customer;
import beans.Mail;
import dao.CustomerDAO;
import exception.SQLDataNotFoundException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DateLogic;
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
		//トークンの生成とアプリケーションスコープへの追加
		final String dist_address = request.getParameter("mail");
		final String token = Base64.getEncoder().encodeToString(dist_address.getBytes());
		ServletContext application = request.getServletContext();
		application.setAttribute(token, dist_address);
		//期限終了後のトークン削除task生成
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			ServletContext application = request.getServletContext();
			@Override
			public void run() {
				application.removeAttribute(token);
			}
		};
		timer.schedule(task, DateLogic.nextDate());
		//メールの送信
		RequestDispatcher dispatcher = null;
		try {
			Customer user = CustomerDAO.findByMail(dist_address);
			final String message = MessageGenerator.generate(user.name(),(String)application.getAttribute("domain"),(String)application.getAttribute("application"),token);
			Mail mail = new Mail(dist_address, "パスワードの変更",message);
			if(MailLogic.execute(mail)) {
				dispatcher = request.getRequestDispatcher("WEB-INF/jsp/mail_send.jsp");
			}else {
				dispatcher = request.getRequestDispatcher("WEB-INF/jsp/mail_failed.jsp");
			}
		} catch (SQLException | SQLDataNotFoundException e) {
			dispatcher = request.getRequestDispatcher("WEB-INF/jsp/mail_failed.jsp");
		}
		dispatcher.forward(request, response);
	}

}
