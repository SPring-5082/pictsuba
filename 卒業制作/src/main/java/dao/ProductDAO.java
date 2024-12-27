package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import beans.Auto_Increment;
import beans.CartSet;
import beans.Product;

public class ProductDAO extends DAO {
	private static final String SELECT =
		  "SELECT "
		+ "		PRODUCT_ID, "
		+ "		PRODUCT_NAME,"
		+ "		ADD_DATE,"
		+ "		PRICE,"
		+ "		CREATOR_ID,"
		+ "		(SELECT CREATOR_NAME FROM CREATORS C WHERE P.CREATOR_ID = C.CREATOR_ID) AS CREATOR_NAME,"
		+ "		CATEGORY_ID,"
		+ "		(SELECT CATEGORY_NAME FROM CATEGORIES C WHERE P.CATEGORY_ID = C.CATEGORY_ID) AS CATEGORY_NAME,"
		+ "		STOCK,"
		+ "		LOOKUP,"
		+ "		POINT,"
		+ "		IMAGE,"
		+ "		DESCRYPTION "
		+ "FROM"
		+ "		PRODUCTS P ";
	
	private static final String SELECTS = 
			  "SELECT\n"
			+ "		PRODUCT_ID, "
			+ "		PRODUCT_NAME,"
			+ "		ADD_DATE,"
			+ "		PRICE,"
			+ "		CREATOR_ID,"
			+ "		(SELECT CREATOR_NAME FROM CREATORS C WHERE P.CREATOR_ID = C.CREATOR_ID) AS CREATOR_NAME,"
			+ "		CATEGORY_ID,"
			+ "		(SELECT CATEGORY_NAME FROM CATEGORIES C WHERE P.CATEGORY_ID = C.CATEGORY_ID) AS CATEGORY_NAME,"
			+ "		STOCK,"
			+ "		LOOKUP,"
			+ "		POINT,"
			+ "		IMAGE,"
			+ "		DESCRYPTION, "
			+ "		(SELECT COUNT(*) FROM ORDER_HISTORIES O WHERE O.PRODUCT_ID = P.PRODUCT_ID) AS SALES_QUANTITY "
			+ "FROM\n"
			+ "	PRODUCTS P\n";
	
	private static final String POPULARITY =
		SELECTS
		+ "ORDER BY\n"
		+ "	SALES_QUANTITY DESC\n"
		+ "LIMIT 30";
	
	private static final  String RECOMEND = 
		SELECTS 
		+ "WHERE\n"
		+ "	(\n"
		+ "		CATEGORY_ID IN\n"
		+ "			(SELECT CATEGORY_ID FROM PRODUCTS WHERE PRODUCT_ID IN (SELECT PRODUCT_ID FROM ORDER_HISTORIES WHERE CUSTOMER_ID = ?))\n"
		+ "		or\n"
		+ "		CREATOR_ID IN\n"
		+ "			(SELECT CREATOR_ID FROM PRODUCTS WHERE PRODUCT_ID IN (SELECT PRODUCT_ID FROM ORDER_HISTORIES WHERE CUSTOMER_ID = ?))\n"
		+ "	)\n"
		+ "    AND\n"
		+ "    PRODUCT_ID NOT IN\n"
		+ "		(SELECT PRODUCT_ID FROM ORDER_HISTORIES WHERE CUSTOMER_ID = ?)\n"
		+ "	ORDER BY\n"
		+ "		(SALES_QUANTITY+LOOKUP/10) DESC";
	
	/**
	 * 商品IDに基づく特定商品の情報取得
	 * @param product_id 商品ID
	 * @return 商品情報
	 * @throws SQLException
	 */
	public static Product findById(int product_id) throws SQLException {
		final String where  = "WHERE PRODUCT_ID = ?";
		final String sql = SELECT.concat(where);
		PreparedStatement pstmt = getPsTmt(sql);
		pstmt.setInt(1, product_id);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next())	{
			//product_id = rs.getInt(1);
			String product_name = rs.getString(2);
			Date add_date = rs.getDate(3);
			int price = rs.getInt(4);
			int creator_id = rs.getInt(5);
			String creator_name = rs.getString(6);
			int category_id = rs.getInt(7);
			String category_name = rs.getString(8);
			int stock = rs.getInt(9);
			int lookup = rs.getInt(10);
			int point = rs.getInt(11);
			String image = rs.getString(12);
			String descryption = rs.getString(13);
			return new Product(product_id, product_name, add_date, price, creator_id, category_id, stock, lookup, point, image, descryption, creator_name, category_name);
		}
		return null;
	}
	
	/**
	 * 人気度が高い商品の取得
	 * @return 人気度の高い商品のリスト
	 * @throws SQLException
	 */
	public static List<Product> findByPopularity() throws SQLException{
		List<Product> list = new ArrayList<Product>();
		PreparedStatement pstmt = getPsTmt(POPULARITY);
		for(ResultSet rs = pstmt.executeQuery();rs.next();) {
			int product_id = rs.getInt(1);
			String product_name = rs.getString(2);
			Date add_date = rs.getDate(3);
			int price = rs.getInt(4);
			int creator_id = rs.getInt(5);
			String creator_name = rs.getString(6);
			int category_id = rs.getInt(7);
			String category_name = rs.getString(8);
			int stock = rs.getInt(9);
			int lookup = rs.getInt(10);
			int point = rs.getInt(11);
			String image = rs.getString(12);
			String descryption = rs.getString(13);
			int sales_quantity = rs.getInt(14);
			list.add(new Product(product_id, product_name, add_date, price, creator_id, category_id, stock, lookup, point, image, descryption, creator_name, category_name,sales_quantity));
		}
		return list;
	}
	
	/**
	 * 顧客IDに基づくおすすめ商品の取得
	 * @param customer_id 顧客ID
	 * @return おすすめ商品リスト
	 * @throws SQLException
	 */
	public static List<Product> findByRecommendation(int customer_id) throws SQLException{
		List<Product> list = new ArrayList<Product>();
		final String sql = RECOMEND;
		PreparedStatement pstmt = getPsTmt(sql);
		pstmt.setInt(1, customer_id);
		pstmt.setInt(2, customer_id);
		pstmt.setInt(3, customer_id);
		System.out.println(pstmt);
		for(ResultSet rs = pstmt.executeQuery();rs.next();) {
			int product_id = rs.getInt(1);
			String product_name = rs.getString(2);
			Date add_date = rs.getDate(3);
			int price = rs.getInt(4);
			int creator_id = rs.getInt(5);
			String creator_name = rs.getString(6);
			int category_id = rs.getInt(7);
			String category_name = rs.getString(8);
			int stock = rs.getInt(9);
			int lookup = rs.getInt(10);
			int point = rs.getInt(11);
			String image = rs.getString(12);
			String descryption = rs.getString(13);
			int sales_quantity = rs.getInt(14);
			list.add(new Product(product_id, product_name, add_date, price, creator_id, category_id, stock, lookup, point, image, descryption, creator_name, category_name,sales_quantity));
		}
		Collections.sort(list);
		return list;
	}
	
	/**
	 * 顧客IDに基づく商品と数量のセットの取得
	 * @param customer_id 顧客ID
	 * @return 商品と数量のセット
	 * @throws SQLException
	 */
	public static CartSet findByCustomers_cart(int customer_id) throws SQLException{
		//CartSet
		List<Product> products = new ArrayList<Product>();
		List<Integer> quantities = new ArrayList<Integer>();
		final String sql =
			    "SELECT\n"
			  + "	PRODUCT_ID, \n"
			  + "    PRODUCT_NAME,\n"
			  + "    ADD_DATE,\n"
			  + "    PRICE,\n"
			  + "    CREATOR_ID,\n"
			  + "    (SELECT CREATOR_NAME FROM CREATORS C WHERE P.CREATOR_ID = C.CREATOR_ID) AS CREATOR_NAME,\n"
			  + "    CATEGORY_ID,\n"
			  + "    (SELECT CATEGORY_NAME FROM CATEGORIES C WHERE P.CATEGORY_ID = C.CATEGORY_ID) AS CATEGORY_NAME,\n"
			  + "    STOCK,\n"
			  + "    LOOKUP,\n"
			  + "    POINT,\n"
			  + "    IMAGE,\n"
			  + "    DESCRYPTION, \n"
			  + "    (SELECT QUANTITY FROM CART C WHERE CUSTOMER_ID = ? AND C.PRODUCT_ID = P.PRODUCT_ID) AS QUANTITY\n"
			  + "FROM\n"
			  + "	PRODUCTS P\n"
			  + "WHERE\n"
			  + "	PRODUCT_ID IN (\n"
			  + "		SELECT PRODUCT_ID FROM CART WHERE CUSTOMER_ID = ?\n"
			  + "    )";
		PreparedStatement pstmt = getPsTmt(sql);
		pstmt.setInt(1, customer_id);
		pstmt.setInt(2, customer_id);
		for(ResultSet rs = pstmt.executeQuery();rs.next();) {
			int product_id = rs.getInt(1);
			String product_name = rs.getString(2);
			Date add_date = rs.getDate(3);
			int price = rs.getInt(4);
			int creator_id = rs.getInt(5);
			String creator_name = rs.getString(6);
			int category_id = rs.getInt(7);
			String category_name = rs.getString(8);
			int stock = rs.getInt(9);
			int lookup = rs.getInt(10);
			int point = rs.getInt(11);
			String image = rs.getString(12);
			String descryption = rs.getString(13);
			int quantity = rs.getInt(14);
			Product product = new Product(product_id, product_name, add_date, price, creator_id, category_id, stock, lookup, point, image, descryption, creator_name, category_name);
			
			products.add(product);
			quantities.add(quantity);
		}
		return new CartSet(products, quantities);
	}
	
	/**
	 * 
	 * @param word 
	 * @return
	 * @throws SQLException 
	 */
	public static List<Product> findBySearchWord(String word) throws SQLException{
		List<Product> results = new ArrayList<Product>();
		String LIKE = '%' + word + '%';
		final String WHERE =
			  "WHERE\n"
			+ "	CREATOR_ID IN (SELECT CREATOR_ID FROM CREATORS WHERE CREATOR_NAME LIKE ?)\n"
			+ "    OR\n"
			+ "    CATEGORY_ID IN (SELECT CATEGORY_ID FROM CATEGORIES WHERE CATEGORY_NAME LIKE ?)\n"
			+ "    OR\n"
			+ "    PRODUCT_NAME LIKE ?\n"
			+ "    OR\n"
			+ "    DESCRYPTION LIKE ?";
		final String sql = SELECT.concat(WHERE);
		PreparedStatement pstmt = getPsTmt(sql);
		Auto_Increment ai = new Auto_Increment();
		pstmt.setString(ai.next(), LIKE);
		pstmt.setString(ai.next(), LIKE);
		pstmt.setString(ai.next(), LIKE);
		pstmt.setString(ai.next(), LIKE);
		for(ResultSet rs = pstmt.executeQuery();rs.next();) {
			ai = new Auto_Increment();
			int product_id = rs.getInt(ai.next());
			String product_name = rs.getString(ai.next());
			Date add_date = rs.getDate(ai.next());
			int price = rs.getInt(ai.next());
			int creator_id = rs.getInt(ai.next());
			String creator_name = rs.getString(ai.next());
			int category_id = rs.getInt(ai.next());
			String category_name = rs.getString(ai.next());
			int stock = rs.getInt(ai.next());
			int lookup = rs.getInt(ai.next());
			int point = rs.getInt(ai.next());
			String image = rs.getString(ai.next());
			String descryption = rs.getString(ai.next());
			results.add(new Product(product_id, product_name, add_date, price, creator_id, category_id, stock, lookup, point, image, descryption, creator_name, category_name));
		}
		return results;
	}
	
}
