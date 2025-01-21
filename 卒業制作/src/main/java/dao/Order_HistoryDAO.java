package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Order;
import beans.Order_History;

public class Order_HistoryDAO extends DAO{
	/**
	 * Ordersテーブル内のお届け済みの注文内容データの
	 * Order_Historiesテーブルへの移行
	 * @param order 移行するお届け済オーダー内容
	 * @throws SQLException 
	 */
	public static boolean insert(Order order) throws SQLException {
		final String VALUES = "?,?,?,?,?,?,?";
		final String sql = SQL.insert("ORDER_HISTORIES", VALUES);
		try(Connection con = getConnection();
			PreparedStatement pstmt = getPsTmt(con,sql);){
			pstmt.setInt(1, order.order_id());
			pstmt.setInt(2, order.customer_id());
			pstmt.setInt(3, order.product_id());
			pstmt.setInt(4, order.quantity());
			pstmt.setInt(5, order.price());
			pstmt.setInt(6, order.address_id());
			pstmt.setDate(7, new Date(order.order_date().getTime()));
			return pstmt.executeUpdate() > 0;
		}
	}
	
	/**
	 * 注文履歴IDに基づく注文内容取得
	 * @param order_history_id 注文履歴ID
	 * @return 注文内容リスト
	 * @throws SQLException 
	 */
	public static List<Order_History> findByOrder_History_id(int order_history_id) throws SQLException{
		List<Order_History> list = new ArrayList<Order_History>();
		final String WHERE = "WHERE ORDER_HISTORY_ID = ?";
		final String sql = SQL.select("ORDER_HISTORIES").concat(WHERE);
		try(Connection con = getConnection();
			PreparedStatement pstmt = getPsTmt(con,sql);){
			pstmt.setInt(1, order_history_id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				//order_history_id
				int customer_id = rs.getInt(2);
				int product_id = rs.getInt(3);
				int quantity = rs.getInt(4);
				int price = rs.getInt(5);
				int address_id = rs.getInt(6);
				java.util.Date add_date = rs.getDate(7);
				list.add(new Order_History(order_history_id, customer_id, product_id, quantity, price, address_id, add_date));
			}
			rs.close();
			return list;
		}
		
	}
	
	
	/**
	 * 顧客番号に基づく注文履歴リストの取得
	 * @param customer_id 顧客番号
	 * @return 注文履歴リスト
	 * @throws SQLException
	 */
	public static List<Order_History> findByCustomr_id(int customer_id) throws SQLException{
		List<Order_History> list = new ArrayList<>();
		final String WHERE = "WHERE CUSTOMER_ID = ?";
		final String sql = SQL.select("ORDER_HISTORIES").concat(WHERE);
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
				list.add(new Order_History(order_id, customer_id, product_id, quantity, price, address_id, order_date));
			}
			rs.close();
			return list;
		}
	}
}
