package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

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
		
		return null;
	}
}
