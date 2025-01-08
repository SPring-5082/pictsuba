package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import beans.Favorite;

public class FavoriteDAO extends DAO{
	/**
	 * お気に入り情報の追加
	 * @param favorite お気に入り情報
	 * @return 実行の成否
	 * @throws SQLException 
	 */
	public static boolean insert(Favorite favorite) throws SQLException {
		final String sql = SQL.insert("FAVORITES", "?,?");
		PreparedStatement pstmt = getPsTmt(sql);
		pstmt.setInt(1, favorite.customer_id());
		pstmt.setInt(2, favorite.product_id());
		return pstmt.executeUpdate() > 0;
	}
	
	/**
	 * お気に入り情報の削除
	 * @param favorite お気に入り情報
	 * @return 実行の成否
	 * @throws SQLException 
	 */
	public static boolean delete(Favorite favorite) throws SQLException{
		final String sql = SQL.delete("FAVORITES");
		final String WHERE = "WHERE CUSTOMER_ID = ? AND PRODUCT_ID = ?";
		PreparedStatement pstmt = getPsTmt(sql);
		pstmt.setInt(1, favorite.customer_id());
		pstmt.setInt(2, favorite.product_id());
		return pstmt.executeUpdate() > 0;
	}
}
