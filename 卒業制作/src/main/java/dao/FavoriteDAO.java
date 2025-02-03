package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
		try(Connection con = getConnection();
			PreparedStatement pstmt = getPsTmt(con,sql);){
			pstmt.setInt(1, favorite.customer_id());
			pstmt.setInt(2, favorite.product_id());
			return pstmt.executeUpdate() > 0;
		}
	}
	
	public static boolean exists(Favorite f){
		final String WHERE = " WHERE CUSTOMER_ID = " + f.customer_id()
							+" 		AND PRODUCT_ID = " +f.product_id();
		final String sql = SQL.select("FAVORITES").concat(WHERE);
		try(Connection con = getConnection();
			PreparedStatement pstmt = getPsTmt(con, sql);
			ResultSet rs = pstmt.executeQuery();){
			return rs.next();
		}catch (SQLException e) {
			return false;
		}
	}
	
	/**
	 * お気に入り情報の削除
	 * @param favorite お気に入り情報
	 * @return 実行の成否
	 * @throws SQLException 
	 */
	public static boolean delete(Favorite favorite) throws SQLException{
		final String WHERE = "WHERE CUSTOMER_ID = ? AND PRODUCT_ID = ?";
		final String sql = SQL.delete("FAVORITES").concat(WHERE);
		try(Connection con = getConnection();
			PreparedStatement pstmt = getPsTmt(con,sql);){
			pstmt.setInt(1, favorite.customer_id());
			pstmt.setInt(2, favorite.product_id());
			return pstmt.executeUpdate() > 0;
		}
	}
}
