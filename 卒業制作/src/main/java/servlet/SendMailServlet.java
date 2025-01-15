package servlet;

import java.io.IOException;
import java.net.URLEncoder;

import beans.Mail;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.MailLogic;
import model.MessageGenerator;
import model.TokenLogic;

@WebServlet("/send_mail")
public class SendMailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/mail_form.jsp");
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String mail = request.getParameter("mail");
		final String application = (String)request.getServletContext().getAttribute("application");
		final String domain = (String)request.getServletContext().getAttribute("domain");
		final String token = TokenLogic.generate();
		final String message = MessageGenerator.generate(domain, application, URLEncoder.encode(token));
		boolean result = MailLogic.execute(new Mail(mail, "アカウント作成ページURL", message));
		if(result) {
			response.addCookie(new Cookie("token", token));
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/mail_send.jsp");
			dispatcher.forward(request, response);
		}else {
			request.setAttribute("link", "send_mail");
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/mail_failed.jsp");
			dispatcher.forward(request, response);
		}
		
	}

}
