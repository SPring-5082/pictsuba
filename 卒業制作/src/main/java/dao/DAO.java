package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class DAO {
	
	private final static String
		/**接続先データベース名*/
		DATABASE = "site-name";
	/**
	 * ConnectionからPreparedStatementを取得するメソッド。
	 */
	static PreparedStatement getPsTmt(String sql) throws SQLException {
		/**接続先URL*/
		String URL = "jdbc:mysql://localhost/DATABASE"
			.replaceFirst("DATABASE", DATABASE),
		/**ユーザー名*/
		USER = "admin",
		/**パスワード*/
		PASS = "adminpass",
		/**ドライバ名*/
		DRIVER = "com.mysql.cj.jdbc.Driver";
	
		Connection con;
		try{
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL,USER,PASS);
		}catch (Exception e) {
			System.err.println("ドライバのダウンロードに失敗しました。");
			return null;
		}
		return con.prepareStatement(sql);
	}
	
}
