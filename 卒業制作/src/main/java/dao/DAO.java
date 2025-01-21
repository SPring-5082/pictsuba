package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

abstract class DAO {

	private static final String
		DATABASE = "pictsuba",
		DRIVER = "com.mysql.cj.jdbc.Driver",
		URL = "jdbc:mysql://" + System.getProperty("url") + "/" + DATABASE,
		USER = System.getProperty("user"),
		PASS = System.getProperty("pass");
	static Connection getConnection() throws SQLException{
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			throw new SQLException("ドライバのロードに失敗しました。", e);
		}
		Connection con = DriverManager.getConnection(URL, USER, PASS);
		return con;
	}
	
	static PreparedStatement getPsTmt(Connection con,String sql) throws SQLException {
		return con.prepareStatement(sql);
	}
}
