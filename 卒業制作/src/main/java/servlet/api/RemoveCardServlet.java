package servlet.api;

import java.io.IOException;

import dao.CreditCardDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/api/rm-card")
public class RemoveCardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int card_id = Integer.parseInt(request.getParameter("card_id"));
		try {
			CreditCardDAO.delByCard_id(card_id);
		}catch (Exception e) {}
	}

}
