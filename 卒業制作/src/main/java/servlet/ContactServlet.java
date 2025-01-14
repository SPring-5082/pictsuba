package servlet;

import java.io.IOException;

import beans.Contact;
import dao.ContactDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/contact")
public class ContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String name = request.getParameter("name");
		final String mail = request.getParameter("mail");
		final String message = request.getParameter("message");
		Contact contact = new Contact(name, mail, message);
		boolean result = false;
		try {
			result = ContactDAO.insert(contact);
		}catch (Exception e) {}
		RequestDispatcher dispatcher = null;
		if(result) {
			dispatcher = request.getRequestDispatcher("WEB-INF/jsp/contact_comp.jsp");
			dispatcher.forward(request, response);
		}else {
			response.sendRedirect("contact/");
		}
		return;
	}

}
