package servlet.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import beans.CreditCard;
import beans.Customer;
import dao.CreditCardDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/api/card_id.json")
public class AddCardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Customer user = (Customer)session.getAttribute("user");
		final String owner_name = request.getParameter("owner_name");
		final String number = request.getParameter("number");
		final String expire = request.getParameter("month")+"/"+request.getParameter("year");
		final int security_code = Integer.parseInt(request.getParameter("security_code"));
		CreditCard card = new CreditCard(user.customer_id(), number, expire, security_code, owner_name);
		try (PrintWriter pw = response.getWriter();){
			CreditCardDAO.insert(card);
			List<CreditCard> cards = CreditCardDAO.findByCustomer_id(user.customer_id());
			Integer card_id = 0;
			for(CreditCard c : cards) {
				card_id = card_id < c.card_id()?c.card_id():card_id;
			}
			final String JSON = 
			"{\"card_id\":"+ card_id +"}";
			pw.print(JSON);
		}catch (Exception e) {
			e.printStackTrace();
			response.setStatus(response.SC_INTERNAL_SERVER_ERROR);
		}
	}

}
