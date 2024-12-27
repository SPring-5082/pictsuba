package servlet;

import java.io.IOException;
import java.util.Date;

import beans.Customer;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.DateLogic;

@WebServlet("/account")
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String page = "WEB-INF/jsp/";
	/**
	 * 現在の顧客情報の表示
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
		return;
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");
		String mail = request.getParameter("mail");
		int age = -1;
		try {
			age = Integer.parseInt(request.getParameter("age"));
		}catch (NumberFormatException e) {}
		Date birth_day = DateLogic.execute(request.getParameter("birth_day"));
		String gender = request.getParameter("gender");
		
		HttpSession session = request.getSession();
		Customer user = (Customer)session.getAttribute("user");
		//セッション顧客情報の更新
		//DB顧客情報の更新
		session.removeAttribute("user");
		session.setAttribute("user", user);
		doGet(request, response);
	}
	
}
