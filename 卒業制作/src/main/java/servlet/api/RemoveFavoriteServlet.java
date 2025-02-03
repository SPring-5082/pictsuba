package servlet.api;

import java.io.IOException;
import java.sql.SQLException;

import beans.Customer;
import beans.Favorite;
import dao.FavoriteDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.ArrayLogic;
import model.CookieLogic;

@WebServlet("/api/rm-favorite")
public class RemoveFavoriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		HttpSession session = request.getSession();
		Customer user = (Customer)session.getAttribute("user");
		int product_id = Integer.parseInt(request.getParameter("product_id"));
		//DBにある場合の仮定して削除
		try {
			int customer_id = user.customer_id();
			Favorite favorite = new Favorite(customer_id, product_id);
			FavoriteDAO.delete(favorite);
		}catch (SQLException e) {}

		//Cookieにある場合の削除
		try {
			String fav = CookieLogic.getValueFromKey("favorite", cookies);
			int[] favorite = ArrayLogic.decode(fav);
			favorite = ArrayLogic.delete(favorite, product_id);
			Cookie favCookie = new Cookie("favorite", ArrayLogic.encode(favorite));
			favCookie.setPath("/");
			favCookie.setMaxAge(60 * 60 * 24 * 31);
			response.addCookie(favCookie);
		}catch (Exception e) {}
		
		
	}
	
}
