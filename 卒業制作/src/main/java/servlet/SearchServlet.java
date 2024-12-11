package servlet;

import java.io.IOException;
import java.util.ArrayList;

import beans.Product;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.SearchResultLogic;
@WebServlet("/search")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("list", SearchResultLogic.execute(new ArrayList<Product>(), "検索ワード"));
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/search.jsp");
		dispatcher.forward(request, response);
	}

}
