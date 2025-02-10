package servlet;

import java.io.IOException;

import beans.Product;
import dao.ProductDAO;
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
		}catch (Exception e) {}
		Product product = null;
		try {
			product = ProductDAO.findById(product_id);
		}catch (Exception e) {}
		//お気に入りであるかどうか
		boolean isFav = FavoriteLogic.exists(request);
		request.setAttribute("fav", isFav);
		request.setAttribute("product", product);
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
	
}
