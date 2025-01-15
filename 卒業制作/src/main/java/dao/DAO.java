package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

abstract class DAO {
	
	private final static String
		/**接続先データベース名*/
		DATABASE = "pictsuba";
	/**
	 * ConnectionからPreparedStatementを取得するメソッド。
	 */
	static PreparedStatement getPsTmt(String sql) throws SQLException {
		/**接続先URL*/
		String URL = "jdbc:mysql://"+ System.getProperty("url") +"/DATABASE"
			.replaceFirst("DATABASE", DATABASE),
		/**ユーザー名*/
		USER = System.getProperty("user"),
		/**パスワード*/
		PASS = System.getProperty("pass"),
		/**ドライバ名*/
		DRIVER = "com.mysql.cj.jdbc.Driver";
	
		Connection con;
		try{
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL,USER,PASS);
			Timer timer = new Timer();
			TimerTask task = new TimerTask() {
				@Override
				public void run() {
					try {
						con.close();
					} catch (SQLException e) {}
				}
			};
			timer.schedule(task, 10*1000);
		}catch (Exception e) {
			System.err.println("ドライバのダウンロードに失敗しました。");
			return null;
		}
		PreparedStatement pstmt = con.prepareStatement(sql);
		Timer timer2 = new Timer();
		TimerTask task2 = new TimerTask() {
			@Override
			public void run() {
				try {
					pstmt.close();
				} catch (SQLException e) {}
			}
		};
		timer2.schedule(task2, 10*1000);
		return pstmt;
	}
	
}
