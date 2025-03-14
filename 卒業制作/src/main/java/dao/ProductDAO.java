package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import beans.Cart;
import beans.Product;
import exception.SQLDataNotFoundException;

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
		+ " WHERE\n"
		+ "	(\n"
		+ "		CATEGORY_ID IN\n"
		+ "			(SELECT CATEGORY_ID FROM PRODUCTS WHERE PRODUCT_ID IN (SELECT PRODUCT_ID FROM ORDER_HISTORIES WHERE CUSTOMER_ID = ?))\n"
		+ "        or\n"
		+ "        CREATOR_ID IN\n"
		+ "			(SELECT CREATOR_ID FROM PRODUCTS WHERE PRODUCT_ID IN (SELECT PRODUCT_ID FROM ORDER_HISTORIES WHERE CUSTOMER_ID = ?))\n"
		+ "		or\n"
		+ "        CATEGORY_ID IN\n"
		+ "			(SELECT CATEGORY_ID FROM PRODUCTS WHERE PRODUCT_ID IN (SELECT PRODUCT_ID FROM ORDERS WHERE CUSTOMER_ID = ?))\n"
		+ "		or\n"
		+ "		CREATOR_ID IN\n"
		+ "            (SELECT CREATOR_ID FROM PRODUCTS WHERE PRODUCT_ID IN (SELECT PRODUCT_ID FROM ORDERS WHERE CUSTOMER_ID = ?))\n"
		+ "	)\n"
		+ "    AND\n"
		+ "    PRODUCT_ID NOT IN\n"
		+ "		(SELECT PRODUCT_ID FROM ORDER_HISTORIES WHERE CUSTOMER_ID = ?)\n"
		+ "	AND\n"
		+ "    PRODUCT_ID NOT IN\n"
		+ "		(SELECT PRODUCT_ID FROM ORDERS WHERE CUSTOMER_ID = ?)\n"
		+ "ORDER BY\n"
		+ "	(SALES_QUANTITY+LOOKUP/10) DESC"
		+ " LIMIT 30";
	
	/**
	 * 商品IDに基づく特定商品の情報取得
	 * 
	 * @param product_id 商品ID
	 * @return 商品情報
	 * @throws SQLException
	 * @throws SQLDataNotFoundException 
	 */
	public static Product findById(int product_id) throws SQLException, SQLDataNotFoundException {
		final String where  = "WHERE PRODUCT_ID = ?";
		final String sql = SELECT.concat(where);
		try(Connection con = getConnection();
			PreparedStatement pstmt = getPsTmt(con,sql);){
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
				rs.close();
				return new Product(product_id, product_name, add_date, price, creator_id, category_id, stock, lookup, point, image, descryption, creator_name, category_name);
			}
			rs.close();
			throw new SQLDataNotFoundException();
		}
		
	}
	
	/**
	 * 人気度が高い商品の取得
	 * @return 人気度の高い商品のリスト
	 * @throws SQLException
	 */
	public static List<Product> findByPopularity() throws SQLException{
		List<Product> list = new ArrayList<Product>();
		try(Connection con = getConnection();
			PreparedStatement pstmt = getPsTmt(con,POPULARITY);){
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
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
			rs.close();
			return list;
		}
	}
	
	/**
	 * 顧客IDに基づくおすすめ商品の取得
	 * 数量が少ない場合は、Popularityを使用する
	 * @param customer_id 顧客ID
	 * @return おすすめ商品リスト
	 * @throws SQLException
	 */
	public static List<Product> findByRecommendation(int customer_id) throws SQLException{
		List<Product> list = new ArrayList<Product>();
		final String sql = RECOMEND;
		
		try(Connection con = getConnection();
			PreparedStatement pstmt = getPsTmt(con,sql);){
			pstmt.setInt(1, customer_id);
			pstmt.setInt(2, customer_id);
			pstmt.setInt(3, customer_id);
			pstmt.setInt(4, customer_id);
			pstmt.setInt(5, customer_id);
			pstmt.setInt(6, customer_id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
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
			rs.close();
			List<Product> popularities = findByPopularity();
			for(int i = 0;i < popularities.size() && list.size() < 30;i ++) {
				if(list.indexOf(popularities.get(i)) < 0) {
					list.add(popularities.get(i));
				}
			}
			return list;
		}
		
	}
	
	public static List<Product> findByCustomerCart(int customer_id) throws SQLException{
		List<Product> products = new ArrayList<>();
		final String SELECT = 
		  "SELECT"
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
		+ "		PRODUCTS P "
		+ "WHERE"
		+ "		PRODUCT_ID IN"
		+ "		(SELECT"
		+ "			PRODUCT_ID"
		+ "		FROM"
		+ "			CART"
		+ "		WHERE"
		+ "			CUSTOMER_ID = " +customer_id + ")";
		try(Connection con = getConnection();
			PreparedStatement pstmt = getPsTmt(con, SELECT);
			ResultSet rs = pstmt.executeQuery();){
			while(rs.next()) {
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
				products.add(new Product(product_id, product_name, add_date, price, creator_id, category_id, stock, lookup, point, image, descryption, creator_name, category_name));
			}
		}
		return products;
	}
	
	/**
	 * 
	 * @param customer_id
	 * @return
	 * @throws SQLException 
	 */
	public static List<Product> findByCustomerFavorite(int customer_id) throws SQLException{
		List<Product> products = new ArrayList<>();
		final String WHERE = "WHERE PRODUCT_ID IN (SELECT PRODUCT_ID FROM FAVORITES WHERE CUSTOMER_ID = ?)";
		final String sql = SELECT.concat(WHERE);
		try(Connection con = getConnection();
			PreparedStatement pstmt = getPsTmt(con,sql);){
			pstmt.setInt(1, customer_id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
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
				products.add(new Product(product_id, product_name, add_date, price, creator_id, category_id, stock, lookup, point, image, descryption, creator_name, category_name));
			}
			rs.close();
			return products;
		}
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
		try(Connection con = getConnection();
			PreparedStatement pstmt = getPsTmt(con,sql);){
			pstmt.setString(1, LIKE);
			pstmt.setString(2, LIKE);
			pstmt.setString(3, LIKE);
			pstmt.setString(4, LIKE);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
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
				results.add(new Product(product_id, product_name, add_date, price, creator_id, category_id, stock, lookup, point, image, descryption, creator_name, category_name));
			}
			rs.close();
			return results;
		}
		
	}
	
	public static Map<Product, Integer> findByOrderID(int order_id) throws SQLException {
		Map<Product, Integer> map = new HashMap<>();
		String sql =
		"SELECT\n"
		+ "	PRODUCT_ID,\n"
		+ "	PRODUCT_NAME,\n"
		+ "	ADD_DATE,\n"
		+ "	(SELECT PRICE FROM (SELECT ORDER_ID,PRODUCT_ID,PRICE FROM ORDERS UNION SELECT ORDER_HISTORY_ID,PRODUCT_ID,PRICE FROM ORDER_HISTORIES) O WHERE O.ORDER_ID = " + order_id + " AND O.PRODUCT_ID = P.PRODUCT_ID) AS PRICE,\n"
		+ "	CREATOR_ID,\n"
		+ "	(SELECT CREATOR_NAME FROM CREATORS C WHERE P.CREATOR_ID = C.CREATOR_ID) AS CREATOR_NAME,\n"
		+ "	CATEGORY_ID,\n"
		+ "	(SELECT CATEGORY_NAME FROM CATEGORIES C WHERE P.CATEGORY_ID = C.CATEGORY_ID) AS CATEGORY_NAME,\n"
		+ "	STOCK,\n"
		+ "    LOOKUP,\n"
		+ "    POINT,\n"
		+ "    IMAGE,\n"
		+ "    DESCRYPTION,\n"
		+ "    (SELECT QUANTITY FROM (SELECT ORDER_ID,PRODUCT_ID,QUANTITY FROM ORDERS UNION SELECT ORDER_HISTORY_ID,PRODUCT_ID,QUANTITY FROM ORDER_HISTORIES) O WHERE O.ORDER_ID = " + order_id + " AND O.PRODUCT_ID = P.PRODUCT_ID) AS QUANTITY\n"
		+ "FROM \n"
		+ "	PRODUCTS P\n"
		+ "WHERE\n"
		+ "	PRODUCT_ID IN\n"
		+ "	(SELECT PRODUCT_ID FROM (SELECT ORDER_ID,PRODUCT_ID FROM ORDERS UNION SELECT ORDER_HISTORY_ID,PRODUCT_ID FROM ORDER_HISTORIES) O WHERE O.ORDER_ID = " + order_id + ")";
		try(Connection con = getConnection();
			PreparedStatement pstmt = getPsTmt(con, sql);
			ResultSet rs = pstmt.executeQuery();){
			while(rs.next()) {
				int product_id = rs.getInt(1);
				String product_name = rs.getString(2);
				Date date = rs.getDate(3);
				int price = rs.getInt(4);
				int creator_id = rs.getInt(5);
				String creator_name = rs.getString(6);
				int cateory_id = rs.getInt(7);
				String category_name = rs.getString(8);
				int stock = rs.getInt(9);
				int lookup = rs.getInt(10);
				int point = rs.getInt(11);
				String image = rs.getString(12);
				String descryption = rs.getString(13);
				int quantity = rs.getInt(14);
				Product product = new Product(product_id, product_name, date, price, creator_id, cateory_id, stock, lookup, point, image, descryption, creator_name, category_name);
				map.put(product, quantity);
			}
		}
		return map;
	}
	
	public static boolean updateAddStock(Cart cart) throws SQLException {
		final String WHERE = " WHERE PRODUCT_ID = ?";
		final String SET = " SET STOCK = STOCK + ?";
		final String sql = SQL.update("PRODUCTS").concat(SET).concat(WHERE);
		try(Connection con = getConnection();
			PreparedStatement pstmt = getPsTmt(con, sql);){
			pstmt.setInt(1, cart.quantity());
			pstmt.setInt(2, cart.product_id());
			return pstmt.executeUpdate() > 0;
		}
	}
	
	public static boolean updateStockByCart(Cart cart) throws SQLException {
		final String WHERE = " WHERE PRODUCT_ID = ?";
		final String SET = " SET STOCK = STOCK - ?";
		final String sql = SQL.update("PRODUCTS").concat(SET).concat(WHERE);
		try(Connection con = getConnection();
			PreparedStatement pstmt = getPsTmt(con, sql);){
			pstmt.setInt(1, cart.quantity());
			pstmt.setInt(2, cart.product_id());
			return pstmt.executeUpdate() > 0;
		}
	}
	
}
