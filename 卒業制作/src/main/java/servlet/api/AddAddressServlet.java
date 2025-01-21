package servlet.api;

import java.io.IOException;
import java.io.PrintWriter;

import beans.Address;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/api/address_id.json")
public class AddAddressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Address address = null;//リクエストをDBに格納し再取得
		int address_id = 1;//address.address_id();
		final String JSON = 
		"{\"address_id\":"+ address_id +"}";
		try(PrintWriter pw =  response.getWriter();){
			pw.write(JSON);
		}catch (Exception e) {
			response.setStatus(response.SC_BAD_REQUEST);
		}
	}

}
