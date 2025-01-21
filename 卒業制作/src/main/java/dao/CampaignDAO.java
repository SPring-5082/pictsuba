package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exception.SQLDataNotFoundException;

public class CampaignDAO extends DAO{
	/**
	 * カテゴリーIDについて現在開催中の
	 * キャンペーンが存在するかの真偽値を返す
	 * @param category_id カテゴリーID
	 * @return 存在の真偽
	 * @throws SQLException 
	 */
	public static boolean exists(int category_id) throws SQLException {
		final String WHERE = "WHERE CATEGORY_ID = ?";
		final String sql = SQL.select("CAMPAIGN").concat(WHERE);
		try(Connection con = getConnection();
			PreparedStatement pstmt = getPsTmt(con,sql);){
			pstmt.setInt(1, category_id);
			return pstmt.executeQuery().next();
		}
	}
	
	/**
	 * 特定カテゴリー商品の割引率を返す
	 * @param category_id カテゴリーID
	 * @return 割引率
	 * @throws SQLException 
	 * @throws SQLDataNotFoundException 
	 */
	public static int discount_rate(int category_id) throws SQLException, SQLDataNotFoundException {
		final String WHERE = "WHERE CATEGORY_ID = ?";
		final String sql = SQL.select("CAMPAIGN").concat(WHERE);
		try(Connection con = getConnection();
			PreparedStatement pstmt = getPsTmt(con,sql);){
			pstmt.setInt(1, category_id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				int discount_rate = rs.getInt("DISCOUNT_RATE");;
				rs.close();
				return discount_rate;
			}else {
				throw new SQLDataNotFoundException();
			}
		}
		
	}
	
}
