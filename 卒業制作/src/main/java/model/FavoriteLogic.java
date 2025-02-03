package model;

import beans.Customer;
import beans.Favorite;
import dao.FavoriteDAO;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class FavoriteLogic {
	public static boolean exists(HttpServletRequest request) {
		int product_id = Integer.parseInt(request.getParameter("productId"));
		boolean inCookie = false,
				inDB = false;
		//Cookie内に存在するかの成否
		final Cookie[] cookies = request.getCookies();
		if(CookieLogic.existKey("favorite", cookies)) {
			final String favorite = CookieLogic.getValueFromKey("favorite", cookies);
			int[] array = ArrayLogic.decode(favorite);
			inCookie = ArrayLogic.exisits(array, product_id);
		}
		
		//DB内に存在するかの成否
		try {
			HttpSession session = request.getSession();
			Customer user = (Customer)session.getAttribute("user");
			Favorite favorite = new Favorite(user.customer_id(), product_id);
			inDB = FavoriteDAO.exists(favorite);
					
		}catch (Exception e) {}
		return inCookie || inDB;
	}
}
