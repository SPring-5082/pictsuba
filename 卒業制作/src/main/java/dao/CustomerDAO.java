package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import beans.Customer;

public class CustomerDAO extends DAO {
	
	public static boolean insert(Customer customer) throws SQLException {
		String sql = SQL.insert("CUSTOMERS","NAME,PASSWORD,PHONE,MAIL,AGE,BIRTH_DAY,GENDER,POINT,FIRST_LOG,FIN_LOG");
		//NAME,PASSWORD,PHONE,MAIL,AGE,BIRTH_DAY,GENDER,POINT,FIRST_LOG,FIN_LOG
		String values = "?,?," + "PHONE" + ",?,?," + "BIRTH_DAY" + ",?,?,?,?";
		values = values.replaceFirst("PHONE", customer.phone()==null?"null":customer.phone());
		values = values.replaceFirst("PHONE", customer.birth_day()==null?"null":customer.phone());
		sql = sql.replaceFirst("STATEMENT", values);
		PreparedStatement pstmt = getPsTmt(sql);
		return pstmt.execute();
	}
	
	public static Customer findByMail(String mail) throws SQLException{
		final String where = "WHERE MAIL = ?";
		final String sql = SQL.select("CUSTOMERS").concat(where).replaceFirst("*", "PASSWORD");
		PreparedStatement pstmt = getPsTmt(sql);
		pstmt.setString(1, mail);
		ResultSet rs = pstmt.executeQuery();
		int customer_id = rs.getInt(1);
		String name = rs.getString(2);
		String password = rs.getString(3);
		String phone = rs.getString(4);
		//mail
		int age = rs.getInt(6);
		Date birth_day = rs.getDate(7);
		String gender = rs.getString(8);
		int point = rs.getInt(9);
		Date first_log = rs.getDate(10);
		Date fin_log = rs.getDate(11);
		int address_id;
		try {
			address_id = rs.getInt(12);
		}catch (Exception e) {
			address_id = -1;
		}
		return null;
	}
}
