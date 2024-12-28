package servlet;

import java.io.IOException;
import java.util.List;

import beans.Product;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.SearchLogic;
@WebServlet("/search")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * 検索ワードに当てはまる商品リストを取得し
	 * 表示するメソッド
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String page = "WEB-INF/jsp/search.jsp";
		String word = request.getParameter("word");
		String order_word = request.getParameter("order");
		List<Product> products = null;
		try {
			products =  SearchLogic.execute(word,(order_word != null?order_word:""));
		}catch (Exception e) {}
		request.setAttribute("products", products);
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

}
