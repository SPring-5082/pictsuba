package servlet;

import java.io.IOException;
import java.sql.SQLException;

import beans.Product;
import dao.ProductDAO;
import exception.SQLDataNotFoundException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.FavoriteLogic;

@WebServlet("/product")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String page = "WEB-INF/jsp/product.jsp";
	/**
	 * 指定された商品IDをもとに取得した商品ページを返す
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int product_id = -1;
		try {
			product_id = Integer.parseInt(request.getParameter("productId"));
			Product product = ProductDAO.findById(product_id);
			boolean isFav = FavoriteLogic.exists(request);
			request.setAttribute("fav", isFav);
			request.setAttribute("product", product);
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		}catch (NumberFormatException | SQLDataNotFoundException e) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/product_failed.jsp");
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			response.setStatus(response.SC_INTERNAL_SERVER_ERROR);
		}
	}
	
}
