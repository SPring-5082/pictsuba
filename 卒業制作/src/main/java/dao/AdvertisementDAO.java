package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Advertisement;

public class AdvertisementDAO extends DAO{
	/**
	 * 広告全件取得メソッド
	 * @return 現存する広告一覧
	 * @throws SQLException
	 */
	public static List<Advertisement> findAll() throws SQLException {
		List<Advertisement> list = new ArrayList<Advertisement>();
		final String sql = SQL.select("ADVERTISEMENTS");
		PreparedStatement pstmt = getPsTmt(sql);
		for(ResultSet rs = pstmt.executeQuery();rs.next();) {
			int advertisement_id = rs.getInt(1);
			String advertisement_name = rs.getString(2);
			String url = rs.getString(3);
			Advertisement advertisement = new Advertisement(advertisement_id, advertisement_name, url);
			list.add(advertisement);
		}
		return list;
	}
}
