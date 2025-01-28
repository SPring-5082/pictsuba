package servlet.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import beans.Address;
import beans.Customer;
import dao.AddressDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/api/address_id.json")
public class AddAddressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String zip_code = request.getParameter("zip_code");
		final String pref = request.getParameter("pref");
		final String municipalities = request.getParameter("municipalities");
		final String street = request.getParameter("street");
		final String building= request.getParameter("building");
		HttpSession session = request.getSession();
		Customer user = (Customer)session.getAttribute("user");
		Address address = new Address(user.customer_id(), zip_code, pref, municipalities, street, building);
		System.out.println(building);
		try(PrintWriter pw =  response.getWriter();){
			AddressDAO.insert(address);
			Integer address_id = 0;
			List<Address> addresses =  AddressDAO.findByCustomer_id(user.customer_id());
			for(Address a : addresses) {
				address_id = address_id < a.address_id()?a.address_id():address_id;
			}
			final String JSON = 
			"{\"address_id\":"+ address_id +"}";
			pw.write(JSON);
		}catch (Exception e) {
			e.printStackTrace();
			response.setStatus(response.SC_INTERNAL_SERVER_ERROR);
		}
	}

}
