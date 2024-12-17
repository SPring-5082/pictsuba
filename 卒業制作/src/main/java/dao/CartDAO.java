package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import beans.Cart;

public class CartDAO extends DAO{
	/**
	 * カート情報の追加メソッド
	 * @param cart カート情報
	 * @return 追加の実行結果
	 * @throws SQLException
	 */
	public static boolean insert(Cart cart) throws SQLException {
		final String VALUES = "?,?,?";
		final String sql = SQL.insert("CART", VALUES);
		PreparedStatement pstmt = getPsTmt(sql);
		pstmt.setInt(1, cart.customer_id());
		pstmt.setInt(2, cart.product_id());
		pstmt.setInt(3, cart.quantity());
		return pstmt.executeUpdate() > 0;
	}
	
	/**
	 * カート内の数量変更メソッド
	 * @param cart 更新するカート情報
	 * @return 更新の実行結果
	 * @exception SQLException
	 */
	public static boolean updateQuantityByCustomer_id(Cart cart) throws SQLException {
		final String SET = "SET QUANTITY = ? ";
		final String WHERE = "WHERE CUSTOMER_ID = ? AND PRODUCT_ID = ?";
		final String sql = SQL.update("CART").concat(SET).concat(WHERE);
		PreparedStatement pstmt = getPsTmt(sql);
		pstmt.setInt(1, cart.quantity());
		pstmt.setInt(2, cart.customer_id());
		pstmt.setInt(3, cart.product_id());
		return pstmt.executeUpdate() > 0;
	}
	
	
	/**
	 * カートから商品を削除するメソッド
	 * @param cart 削除対象のカート内商品情報
	 * @return 削除の実行結果
	 * @exception SQLException
	 */
	public static boolean delByCustomerANDProduct_Id(Cart cart) throws SQLException {
		final String WHERE = "WHERE CUSTOMER_ID = ? AND PRODUCT_ID = ?";
		final String sql = SQL.delete("CART").concat(WHERE);
		PreparedStatement pstmt = getPsTmt(sql);
		pstmt.setInt(1, cart.customer_id());
		pstmt.setInt(2,cart.product_id());
		return pstmt.executeUpdate() > 0;
	}
	
}
