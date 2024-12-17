package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import beans.Creator;

public class CreatorDAO extends DAO{
	/**
	 * クリエイター登録メソッド
	 * @param creator 登録するクリエイター情報
	 * @return 登録の実行結果
	 * @throws SQLException 
	 */
	public static boolean insert(Creator creator) throws SQLException {
		final String VALUES = "?";
		final String COLUMNS = "CREATOR_NAME";
		final String sql = SQL.insert("CREATORS",VALUES,COLUMNS);
		PreparedStatement pstmt = getPsTmt(sql);
		pstmt.setString(1, creator.creator_name());
		return pstmt.executeUpdate() > 0;
	}
	
	
	/**
	 * クリエイター削除メソッド
	 * @param creator_id 削除するクリエイターのID
	 * @return 削除の実行結果
	 * @exception SQLException
	 */
	public static boolean delByCreator_id(int creator_id) throws SQLException {
		final String WHERE = "WHERE CREATOR_ID = ?";
		final String sql = SQL.delete("CREATORS").concat(WHERE);
		PreparedStatement pstmt = getPsTmt(sql);
		pstmt.setInt(1, creator_id);
		return pstmt.executeUpdate() > 0;
	}
	
}
