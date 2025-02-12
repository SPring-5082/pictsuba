package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Cart;
import beans.Order;

public class OrderDAO extends DAO{
	/**
	 * 新しい注文のDBへのデータ格納メソッド
	 * @param order 登録する注文内容
	 * @return 登録の実行結果
	 * @throws SQLException
	 */
	public static boolean insert(Order order) throws SQLException {
		final String VALUES = "?,?,?,?,?,?,?";
		final String COLUMNS = "ORDER_ID,CUSTOMER_ID,PRODUCT_ID,QUANTITY,PRICE,ADDRESS_ID,STATE";
		final String sql = SQL.insert("ORDERS", VALUES,COLUMNS);
		try(Connection con = getConnection();
			PreparedStatement pstmt = getPsTmt(con,sql);){
			pstmt.setInt(1, order.order_id());
			pstmt.setInt(2, order.customer_id());
			pstmt.setInt(3, order.product_id());
			pstmt.setInt(4, order.quantity());
			pstmt.setInt(5, order.price());
			pstmt.setInt(6, order.address_id());
			pstmt.setString(7, "支払済");
			return pstmt.executeUpdate() > 0;
		}
	}
	
	/**
	 * DBからの最新(最大)のオーダーID取得メソッド
	 * @return 最新のオーダーID
	 * @throws SQLException
	 */
	public static int getFin_order_id() throws SQLException {
		final String sql = 
			  "SELECT "
			+ "		(CASE "
			+ "			WHEN MAX(ORDER_ID) IS NULL THEN 0 "
			+ "			ELSE MAX(ORDER_ID)"
			+ "		 END) AS FIN_ORDER "
			+ "FROM "
			+ "		ORDERS";
		
		try(Connection con = getConnection();
			PreparedStatement pstmt = getPsTmt(con,sql);
			ResultSet rs = pstmt.executeQuery();){
			rs.next();
			int order_id = rs.getInt(1);
			rs.close();
			return order_id;
		}
	}
	
	/**
	 * 顧客番号に基づく注文リストの取得
	 * @param customer_id 顧客番号
	 * @return 注文リスト
	 * @throws SQLException
	 */
	public static List<Order> findByCustomer_id(int customer_id) throws SQLException{
		List<Order> list = new ArrayList<>();
		final String sql =
		  "SELECT"
		+ "		O.ORDER_ID,"
		+ "		O.ORDER_DATE,"
		+ "		O.STATE,\n"
		+ "		(SELECT IMAGE FROM PRODUCTS P WHERE O.PRODUCT_ID = P.PRODUCT_ID) AS IMAGE "
		+ "FROM "
		+ "		(SELECT * FROM ORDERS UNION ALL SELECT ORDER_HISTORY_ID,CUSTOMER_ID,PRODUCT_ID,QUANTITY,PRICE,ADDRESS_ID,ORDER_DATE,\"お届け済み\" FROM ORDER_HISTORIES) O "
		+ "WHERE"
		+ "		O.CUSTOMER_ID = " + customer_id + " "
		+ "ORDER BY"
		+ "		O.ORDER_DATE DESC";
		try(Connection con = getConnection();
			PreparedStatement pstmt = getPsTmt(con, sql);
			ResultSet rs = pstmt.executeQuery()){
			while(rs.next()) {
				int order_id = rs.getInt(1);
				Date order_date = rs.getDate(2);
				String state = rs.getString(3);
				String image = rs.getString(4);
				Order order = new Order(order_id, order_date, state, image);
				list.add(order);
			}
		}
		return list;
	}
	
	public static List<Cart> findByOrder_id(int order_id) throws SQLException{
		List<Cart> cart = new ArrayList<>();
		final String SQL = "SELECt customer_id,product_id,quantity FROM ORDERS WHERE order_id = " + order_id;
		try(Connection con = getConnection();
			PreparedStatement pstmt = getPsTmt(con, SQL);
			ResultSet rs = pstmt.executeQuery();){
			while(rs.next()) {
				cart.add(new Cart(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
			}
		}
		return cart;
	}
	
	/**
	 * 注文(履歴)番号に基づく注文基本
	 * @param order_id
	 * @throws SQLException 
	 */
	public static Order findOrdersOverView(int order_id) throws SQLException {
		String SQL =
		  "SELECT"
		+ "		ORDER_ID,"
		+ "		ADDRESS_ID,"
		+ "		ORDER_DATE,"
		+ "		STATE,"
		+ "		SUM(QUANTITY * PRICE) AS SUM_PRICE "
		+ "FROM"
		+ "		(SELECT * FROM ORDERS UNION ALL SELECT ORDER_HISTORY_ID,CUSTOMER_ID,PRODUCT_ID,QUANTITY,PRICE,ADDRESS_ID,ORDER_DATE,\"お届け済み\" FROM ORDER_HISTORIES) O "
		+ "WHERE"
		+ "		O.ORDER_ID = " + order_id + " "
		+ "GROUP BY "
		+ "		ORDER_ID,ADDRESS_ID,ORDER_DATE,STATE";
		try(Connection con = getConnection();
			PreparedStatement pstmt = getPsTmt(con, SQL);
			ResultSet rs = pstmt.executeQuery()){
			rs.next();
			//order_id = rs.getInt(1);
			int address_id = rs.getInt(2);
			Date order_date = rs.getDate(3);
			String state = rs.getString(4);
			int sum_price = rs.getInt(5);
			Order order = new Order(order_id,address_id, order_date, state, sum_price);
			return order;
		}
	}
	
	/**
	 * 注文内容を一部削除(キャンセル)するメソッド
	 * @param order_id 削除する注文のオーダーID
	 * @param product_id 削除する商品のID
	 * @return 削除の実行結果
	 * @throws SQLException 
	 */
	public static boolean deleteProduct(int order_id, int product_id) throws SQLException {
		final String WHERE = "WHERE ORDER_ID = ? AND PRODUCT_ID = ?";
		final String sql = SQL.delete("ORDERS").concat(WHERE);
		try(Connection con = getConnection();
			PreparedStatement pstmt = getPsTmt(con,sql);){
			pstmt.setInt(1, order_id);
			pstmt.setInt(2, product_id);
			return pstmt.executeUpdate() > 0;
		}
	}
	
	/**
	 * 注文内容を削除(キャンセル)するメソッド
	 * @param order_id 削除する注文のオーダーID
	 * @return 削除の実行結果
	 * @throws SQLException 
	 */
	public static boolean deleteOrder(int order_id) throws SQLException {
		final String WHERE = "WHERE ORDER_ID = ?";
		final String sql = SQL.delete("ORDERS").concat(WHERE);
		try(Connection con = getConnection();
			PreparedStatement pstmt = getPsTmt(con,sql);){
			pstmt.setInt(1, order_id);
			return pstmt.executeUpdate() > 0;
		}
	}
	
}
