package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
		List<Order> list = new ArrayList<Order>();
		final String WHERE = "WHERE CUSTOMER_ID = ?";
		final String sql = SQL.select("ORDERS").concat(WHERE);
		try(Connection con = getConnection();
			PreparedStatement pstmt = getPsTmt(con,sql);){
			pstmt.setInt(1, customer_id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int order_id = rs.getInt(1);
				//customer_id = customer_id;
				int product_id = rs.getInt(3);
				int quantity = rs.getInt(4);
				int price = rs.getInt(5);
				int address_id = rs.getInt(6);
				Date order_date = rs.getDate(7);
				String state = rs.getString(8);
				list.add(new Order(order_id, customer_id, product_id, quantity, price, address_id, order_date, state));
			}
			rs.close();
			return list;
		}
	}
	
	/**
	 * 注文IDに基づく注文内容取得
	 * @param order_id 注文ID
	 * @return 注文内容リスト
	 * @throws SQLException 
	 */
	public static List<Order> findByOrder_id(int order_id) throws SQLException{
		List<Order> list = new ArrayList<Order>();
		final String WHERE = "WHERE ORDER_ID = ?";
		final String sql = SQL.select("ORDERS").concat(WHERE);
		try(Connection con = getConnection();
			PreparedStatement pstmt = getPsTmt(con,sql);){
			pstmt.setInt(1, order_id);
			for(ResultSet rs = pstmt.executeQuery();rs.next();) {
				//order_id
				int customer_id = rs.getInt(2);
				int product_id = rs.getInt(3);
				int quantity = rs.getInt(4);
				int price = rs.getInt(5);
				int address_id = rs.getInt(6);
				java.util.Date add_date = rs.getDate(7);
				String state = rs.getString(8);
				list.add(new Order(order_id, customer_id, product_id, quantity, price, address_id, add_date,state));
			}
			return list;
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
