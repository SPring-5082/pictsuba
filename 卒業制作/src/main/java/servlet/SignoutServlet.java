package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.CookieLogic;

@WebServlet("/signout")
public class SignoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * ログアウトするサーブレット
	 * ログアウト後はトップページに戻る
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ユーザーのクッキー内のセッションIDを削除
		Cookie[] cookies = request.getCookies();
		String session_id = CookieLogic.getValueFromKey("session_id", cookies);
		Cookie sessionCookie = new Cookie("session_id", session_id);
		sessionCookie.setMaxAge(0);
		response.addCookie(sessionCookie);
		//セッションスコープからユーザー情報を削除
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		response.sendRedirect(request.getContextPath() + "/");
	}

}
