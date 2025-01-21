package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Cart;

public class CartDAO extends DAO{
	/**
	 * カート情報の追加メソッド
	 * @param cart カート情報
	 * @return 追加の成否
	 * @throws SQLException
	 */
	public static boolean insert(Cart cart) throws SQLException {
		final String VALUES = "?,?,?";
		final String sql = SQL.insert("CART", VALUES);
		try(Connection con = getConnection();
			PreparedStatement pstmt = getPsTmt(con,sql);){
			pstmt.setInt(1, cart.customer_id());
			pstmt.setInt(2, cart.product_id());
			pstmt.setInt(3, cart.quantity());
			return pstmt.executeUpdate() > 0;
		}
		
	}
	
	/**
	 * 特定顧客のカート内容を返すメソッド
	 * @return カート内容
	 * @throws SQLException
	 */
	public static List<Cart> findByCustomer_id(int customer_id) throws SQLException{
		List<Cart> carts = new ArrayList<Cart>();
		final String WHERE = "WHERE CUSTOMER_ID = ?";
		final String sql = SQL.select("CART").concat(WHERE);
		try(Connection con = getConnection();
			PreparedStatement pstmt = getPsTmt(con,sql);){
			pstmt.setInt(1, customer_id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Cart cart = new Cart(rs.getInt(1), rs.getInt(2), rs.getInt(3));
				carts.add(cart);
			}
			rs.close();
			return carts;
		}
	}
	
	/**
	 * カート内の数量変更メソッド
	 * @param cart 更新するカート情報
	 * @return 更新の成否
	 * @throws SQLException
	 */
	public static boolean updateQuantityByCustomer_id(Cart cart) throws SQLException {
		final String SET = "SET QUANTITY = ? ";
		final String WHERE = "WHERE CUSTOMER_ID = ? AND PRODUCT_ID = ?";
		final String sql = SQL.update("CART").concat(SET).concat(WHERE);
		try(Connection con = getConnection();
			PreparedStatement pstmt = getPsTmt(con,sql);){
			pstmt.setInt(1, cart.quantity());
			pstmt.setInt(2, cart.customer_id());
			pstmt.setInt(3, cart.product_id());
			return pstmt.executeUpdate() > 0;
		}
	}
	
	
	/**
	 * カートから商品を削除するメソッド
	 * @param cart 削除対象のカート内商品情報
	 * @return 削除の成否
	 * @exception SQLException
	 */
	public static boolean delByCustomerANDProduct_Id(Cart cart) throws SQLException {
		final String WHERE = "WHERE CUSTOMER_ID = ? AND PRODUCT_ID = ?";
		final String sql = SQL.delete("CART").concat(WHERE);
		try(Connection con = getConnection();
			PreparedStatement pstmt = getPsTmt(con,sql);){
			pstmt.setInt(1, cart.customer_id());
			pstmt.setInt(2,cart.product_id());
			return pstmt.executeUpdate() > 0;
		}
	}
	
}
