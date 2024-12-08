package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Favorite;

public class FavoriteDAO extends DAO{
	public static List<Integer> findByCustomer_id(int customer_id) throws SQLException{ 
		List<Integer> list = new ArrayList<Integer>();
		final String where = "WHERE CUSTOMER_ID = ?";
		final String sql = SQL.select("FAVORITES").concat(where);
		PreparedStatement pstmt = getPsTmt(sql);
		pstmt.setInt(1, customer_id);
		for(ResultSet rs = pstmt.executeQuery();rs.next();list.add(rs.getInt("PRODUCT_ID"))) {}
		return list;
	}
	
	public static boolean insert(Favorite f) throws SQLException {
		final String sql = SQL.insert("FAVORITES").replaceFirst("STATEMENT", "?,?");
		PreparedStatement pstmt = getPsTmt(sql);
		pstmt.setInt(1, f.customer_id());
		pstmt.setInt(2, f.product_id());
		return pstmt.execute();
	}
	
	public static boolean delete(Favorite f) throws SQLException {
		final String where = "WHERE CUSTOMER_ID = ? AND PRODUCT_ID = ?";
	final String sql = SQL.delete("PRODUCTS").concat(where);
		PreparedStatement pstmt = getPsTmt(sql);
		pstmt.setInt(1, f.customer_id());
		pstmt.setInt(2, f.product_id());
		return pstmt.execute();
	}
	
}
