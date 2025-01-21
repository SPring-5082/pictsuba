package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import beans.Contact;

public class ContactDAO extends DAO{
	/**
	 * お問い合わせ内容の登録
	 * @param contact 格納するお問い合わせ内容
	 * @return 実行の成否
	 * @throws SQLException 
	 */
	public static boolean insert(Contact contact) throws SQLException {
		if(contact == null) throw new NullPointerException("nullは挿入不可能です"); 
		final String sql = SQL.insert("CONTACTS","?,?,?", "NAME,MAIL,MESSAGE");
		try(Connection con = getConnection();
			PreparedStatement pstmt = getPsTmt(con,sql);){
			pstmt.setString(1, contact.name());
			pstmt.setString(2, contact.mail());
			pstmt.setString(3, contact.message());
			return pstmt.executeUpdate() > 0;
		}
	}
	
}
