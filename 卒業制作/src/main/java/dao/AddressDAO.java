package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import beans.Address;
import exception.SQLDataNotFoundException;

public class AddressDAO extends DAO {
	/**
	 * DBへの住所追加メソッド
	 * @param address 登録する住所情報
	 * @return 登録の実行結果
	 * @throws SQLException
	 */
	public static boolean insert(Address address) throws SQLException{
		final String VALUES = "CUSTOMER_ID,ZIP_CODE,PREF,MUNICIPALITIES,STREET,BUILDING";
		final String COLUMNS = "?,?,?,?,?,?";
		final String sql  = SQL.insert("ADDRESS",COLUMNS, VALUES);
		try(Connection con = getConnection();
			PreparedStatement pstmt = getPsTmt(con,sql);){
			pstmt.setInt(1, address.customer_id());
			pstmt.setString(2, address.zip_code());
			pstmt.setString(3, address.pref());
			pstmt.setString(4, address.municipalities());
			pstmt.setString(5, address.street());
			if(address.building() == null) {
				pstmt.setNull(6, Types.VARCHAR);
			}else {
				pstmt.setString(6, address.building());
			}
			return pstmt.executeUpdate() > 0;
		}
	}
	
	/**
	 * 顧客情報に基づくDBからの住所の取得
	 * @param customer_id 取得する住所情報の元となる顧客ID
	 * @return 顧客IDに基づく住所情報一覧
	 * @throws SQLException
	 */
	public static List<Address> findByCustomer_id(int customer_id) throws SQLException{
		List<Address> list = new ArrayList<Address>();
		final String WHERE = "WHERE CUSTOMER_ID = ?";
		final String sql = SQL.select("ADDRESS").concat(WHERE);
		try(Connection con = getConnection();
			PreparedStatement pstmt = getPsTmt(con,sql);){
			pstmt.setInt(1, customer_id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int address_id = rs.getInt(1);
				customer_id = rs.getInt(2);
				String zip_code = rs.getString(3);
				String pref = rs.getString(4);
				String municipalities = rs.getString(5);
				String street = rs.getString(6);
				String building = rs.getString(7);
				Address address = new Address(address_id, customer_id, zip_code, pref, municipalities, street, building);
				list.add(address);
			}
			rs.close();
			return list;
		}
	}
	
	public static Address findByAddress_id(int address_id) throws SQLException, SQLDataNotFoundException {
		final String WHERE = "WHERE ADDRESS_ID = ?";
		final String sql = SQL.select("ADDRESS").concat(WHERE);
		try(Connection con = getConnection();
			PreparedStatement pstmt = getPsTmt(con,sql);){
			pstmt.setInt(1, address_id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				//address_id = rs.getInt(1);
				int customer_id = rs.getInt(2);
				String zip_code = rs.getString(3);
				String pref = rs.getString(4);
				String municipalities = rs.getString(5);
				String street = rs.getString(6);
				String building = rs.getString(7);
				rs.close();
				return new Address(address_id, customer_id, zip_code, pref, municipalities, street, building);
			}
			rs.close();
			throw new SQLDataNotFoundException();
		}
		
	}
	
	/**
	 * 住所の登録内容変更
	 * @param 新しく登録する情所情報
	 * @return 変更の実行結果
	 * @throws SQLException
	 */
	public static boolean update(Address address) throws SQLException {
		final String WHERE = " WHERE ADDRESS_ID = ?";
		final String SET ="SET "
						+ "ZIP_CODE = ?,"
						+ "PREF = ?,"
						+ "MUNICIPALITIES = ?,"
						+ "STREET = ?,"
						+ "BUILDING = ?";
		final String sql = SQL.update("ADDRESS").concat(SET).concat(WHERE);
		try(Connection con = getConnection();
			PreparedStatement pstmt = getPsTmt(con,sql);){
			pstmt.setString(1, address.zip_code());
			pstmt.setString(2, address.pref());
			pstmt.setString(3, address.municipalities());
			pstmt.setString(4, address.street());
			if(address.building() == null) {
				pstmt.setNull(5, Types.VARCHAR);
			}else {
				pstmt.setString(5, address.building());
			}
			pstmt.setInt(6, address.address_id());
			return pstmt.executeUpdate() > 0;
		}
		
	}
	
	/**
	 * 特定住所の削除
	 * @param address_id 削除する対象データのID
	 * @return 削除の実行結果
	 * @throws SQLException
	 */
	public static boolean delByAddress_id(int address_id) throws SQLException {
		final String WHERE = "WHERE ADDRESS_ID = ?";
		final String sql = SQL.delete("ADDRESS").concat(WHERE);
		try(Connection con = getConnection();
			PreparedStatement pstmt = getPsTmt(con,sql);){
			pstmt.setInt(1, address_id);
			return pstmt.executeUpdate() > 0;
		}
	}
}
