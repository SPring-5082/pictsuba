package servlet.api;

import java.io.IOException;

import dao.AddressDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/api/rm-address")
public class RemoveAddressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int address_id = Integer.parseInt(request.getParameter("address_id"));
		try {
			AddressDAO.delByAddress_id(address_id);
		}catch (Exception e) {}
	}

}
