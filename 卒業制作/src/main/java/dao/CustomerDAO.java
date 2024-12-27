package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import beans.Customer;

public class CustomerDAO extends DAO {
	/**
	 * 新しい顧客情報の登録
	 * @param customer 登録する顧客情報
	 * @return 登録の成否
	 * @throws SQLException
	 */
	public static boolean insert(Customer customer) throws SQLException {
		String columns = "NAME,PASSWORD,PHONE,MAIL,AGE,BIRTH_DAY,GENDER,FIRST_LOG,FIN_LOG";
		String values = "?,?,?,?,?,?,?,?,?";
		String sql = SQL.insert("CUSTOMERS", values, columns);
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
	/*
	public static List<Customer> findALL() throws SQLException{
		List<Customer> list = new ArrayList<Customer>();
		final String sql = SQL.select("CUSTOMERS");
		PreparedStatement pstmt = getPsTmt(sql);
		
		for(ResultSet rs = pstmt.executeQuery();rs.next();){
			int customer_id =  rs.getInt(1);
			String name = rs.getString(2);
			String pass = rs.getString(3);
			String phone = rs.getString(4);
			String mail = rs.getString(5);
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
			Customer customer = new Customer(customer_id, name,pass, phone, mail, age, birth_day, gender, point, first_log, fin_log, address_id, card_id);
			list.add(customer);
		}
		return list;
	}
	*/
	
	/**
	 * メールアドレスとパスワードによる顧客情報の取得
	 * 条件に一致するものがない場合、nullを返す
	 * @param mail メールアドレス
	 * @param password パスワード
	 * @return 条件に一致する顧客情報
	 * @throws SQLException
	 */
	public static Customer findByMailandPass(String mail, String password) throws SQLException{
		final String where = "WHERE MAIL = ? AND `PASSWORD` = ?";
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
	/**
	 * 最後に利用されたカード情報の更新
	 * @param customer_id カードを使用した顧客ID
	 * @param card_id 使用したカードのID
	 * @return 更新の成否
	 * @throws SQLException
	 */
	public static boolean updateCard_id(int customer_id, int card_id) throws SQLException {
		final String SET = "SET CARD_ID = ? ";
		final String WHERE = "WHERE CUSTOMER_ID = ?";
		final String sql = SQL.update("CUSTOMERS").concat(SET).concat(WHERE);
		PreparedStatement pstmt = getPsTmt(sql);
		pstmt.setInt(1, card_id);
		pstmt.setInt(2, customer_id);
		return pstmt.executeUpdate() > 0;
	}
	
	/**
	 * 最後に利用された住所情報の更新
	 * @param customer_id 住所情報をもつ顧客ID
	 * @param address_id 利用された住所のID
	 * @return 更新の成否
	 * @throws SQLException
	 */
	public static boolean updateAddress_id(int customer_id, int address_id) throws SQLException {
		final String SET = "SET ADDRESS_ID = ? ";
		final String WHERE = "WHERE CUSTOMER_ID = ?";
		final String sql = SQL.update("CUSTOMERS").concat(SET).concat(WHERE);
		PreparedStatement pstmt = getPsTmt(sql);
		pstmt.setInt(1, address_id);
		pstmt.setInt(2, customer_id);
		return pstmt.executeUpdate() > 0;
	}
	
	/**
	 * ユーザーが所持するポイント情報の更新
	 * @param customer_id 更新対象の顧客ID
	 * @param point 変更後のポイント
	 * @return 更新の成否
	 * @throws SQLException
	 */
	public static boolean updatePoint(int customer_id, int point) throws SQLException {
		final String SET = " SET POINT = ? ";
		final String WHERE = "WHERE CUSTOMER_ID = ?";
		final String sql = SQL.update("CUSTOMERS").concat(SET).concat(WHERE);
		PreparedStatement pstmt = getPsTmt(sql);
		pstmt.setInt(1, point);
		pstmt.setInt(2, customer_id);
		return pstmt.executeUpdate() > 0;
	}
	
	/**
	 * 最終ログイン日の更新
	 * @param customer_id 更新対象の顧客ID
	 * @return 更新の成否
	 * @throws SQLException
	 */
	public static boolean updateFin_log(int customer_id) throws SQLException {
		final String SET = "SET FIN_LOG = ?";
		final String WHERE = "WHERE CUSTOMER_ID = ?";
		final String sql = SQL.update("CUSTOMERS").concat(SET).concat(WHERE);
		PreparedStatement pstmt = getPsTmt(sql);
		pstmt.setDate(1, new Date(new java.util.Date().getTime()));
		pstmt.setInt(2, customer_id);
		return pstmt.executeUpdate() > 0;
	}
	
	/**
	 * 顧客情報の更新
	 * @param customer 更新する顧客情報の内容
	 * @return 更新の成否
	 * @throws SQLException
	 */
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
	
	/**
	 * 顧客情報の削除
	 * @param customer_id 削除対象の顧客ID
	 * @return 削除の成否
	 * @throws SQLException
	 */
	public static boolean delByCustomer_id(int customer_id) throws SQLException {
		final String where = "WHERE CUSTOMER_ID = ?";
		final String sql = SQL.delete("CUSTOMERS").concat(where);
		PreparedStatement pstmt = getPsTmt(sql);
		pstmt.setInt(1, customer_id);
		return pstmt.executeUpdate() > 0;
	}
	
}
