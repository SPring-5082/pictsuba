package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import beans.Customer;

public class CustomerDAO extends DAO {
	
	public static boolean insert(Customer customer) throws SQLException {
		String sql =
		SQL.insert("CUSTOMERS",
		"NAME,PASSWORD,PHONE,MAIL,AGE,BIRTH_DAY,GENDER,FIRST_LOG,FIN_LOG");
		String values = "?,?,?,?,?,?,?,?,?";
		sql = sql.replaceFirst("STATEMENT", values);
		PreparedStatement pstmt = getPsTmt(sql);
		pstmt.setString(1, customer.name());
		
		pstmt.setString(2, customer.password());
		
		if(customer.phone() == null) {
			pstmt.setNull(3, Types.CHAR);
		}else {
			pstmt.setString(3, customer.phone());
		}
		
		pstmt.setString(4, customer.mail());
		
		pstmt.setInt(5, customer.age());
		
		if(customer.birth_day() == null){
			pstmt.setNull(6, Types.DATE);
		}else {
			pstmt.setDate(6, new Date(customer.birth_day().getTime()));
		}
		
		pstmt.setString(7, customer.gender());
		pstmt.setDate(8, new Date(customer.first_log().getTime()));
		pstmt.setDate(9, new Date(customer.fin_log().getTime()));
		return pstmt.executeUpdate() > 0;
	}
	
	public static Customer findByMailandPass(String mail, String password) throws SQLException{
		final String where = "WHERE MAIL = ? AND PASSWORD = ?";
		final String sql = SQL.select("CUSTOMERS").concat(where);
		PreparedStatement pstmt = getPsTmt(sql);
		pstmt.setString(1, mail);
		pstmt.setString(2, password);
		ResultSet rs = pstmt.executeQuery();	
		if(rs.next()){
			int customer_id =  rs.getInt(1);
			String name = rs.getString(2);
			//rs.getString(3)
			String phone = rs.getString(4);
			//rs.getString(5);
			int age = rs.getInt(6);
			Date birth_day = rs.getDate(7);
			String gender = rs.getString(8);
			int point = rs.getInt(9);
			Date first_log = rs.getDate(10);
			Date fin_log = rs.getDate(11);
			
			int address_id;
			int card_id;
			try {
				address_id = rs.getInt(12);
			}catch (Exception e) {
				address_id = -1;
			}
			try {
				card_id = rs.getInt(13);
			}catch (Exception e) {
				card_id = -1;
			}
			Customer customer = new Customer(customer_id, name, phone, mail, age, birth_day, gender, point, first_log, fin_log, address_id, card_id);
			return customer;
		}else{
			return null;
		}
	}
	
	public static boolean updatePoint(int id, int point) throws SQLException {
		final String SET = " SET POINT = ? ";
		final String WHERE = "WHERE CUSTOMER_ID = ?";
		final String sql = SQL.update("CUSTOMERS").concat(SET).concat(WHERE);
		PreparedStatement pstmt = getPsTmt(sql);
		pstmt.setInt(1, point);
		pstmt.setInt(2, id);
		return pstmt.executeUpdate() > 0;
	}
	
	public static boolean updateFin_log(int id, java.util.Date date) throws SQLException {
		final String SET = "SET FIN_LOG = ?";
		final String WHERE = "WHERE CUSTOMER_ID = ?";
		final String sql = SQL.update("CUSTOMERS").concat(SET).concat(WHERE);
		PreparedStatement pstmt = getPsTmt(sql);
		pstmt.setDate(1, new Date(date.getTime()));
		pstmt.setInt(2, id);
		return pstmt.executeUpdate() > 0;
	}
	
	public static boolean updateByCustomer_id(Customer customer) throws SQLException {
		final String SET = "SET "
				+ "NAME = ?,"
				+ "`PASSWORD` = ?,"
				+ "PHONE = ?,"
				+ "MAIL = ?,"
				+ "AGE = ?,"
				+ "BIRTH_DAY = ?,"
				+ "GENDER = ? ";
		final String WHERE = "WHERE CUSTOMER_ID = ?";
		final String sql = SQL.update("CUSTOMERS").concat(SET).concat(WHERE);
		PreparedStatement pstmt = getPsTmt(sql);
		pstmt.setString(1, customer.name());
		pstmt.setString(2, customer.password());
		if(customer.phone() == null) {
			pstmt.setNull(3, Types.CHAR);
		}else {
			pstmt.setString(3, customer.phone());
		}
		pstmt.setString(4, customer.mail());
		pstmt.setInt(5, customer.age());
		if(customer.birth_day() == null) {
			pstmt.setNull(6, Types.DATE);
		}else {
			pstmt.setDate(6, new Date(customer.birth_day().getTime()));
		}
		if(customer.gender() == null) {
			pstmt.setNull(7, Types.VARCHAR);
		}else {
			pstmt.setString(7, customer.gender());
		}
		pstmt.setInt(8, customer.customer_id());
		return pstmt.executeUpdate() > 0;
	}
	
	public static boolean delByCustomer_id(int customer_id) throws SQLException {
		final String where = "WHERE CUSTOMER_ID = ?";
		final String sql = SQL.delete("CUSTOMERS").concat(where);
		PreparedStatement pstmt = getPsTmt(sql);
		pstmt.setInt(1, customer_id);
		return pstmt.executeUpdate() > 0;
	}
	
}
