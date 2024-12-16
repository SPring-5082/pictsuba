package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import beans.Address;

public class AddressDAO extends DAO {
	/**DBへの住所追加メソッド
	 * @throws SQLException */
	public static boolean insert(Address address) throws SQLException{
		final String VALUES = "CUSTOMER_ID,ZIP_CODE,PREF,MUNICIPALITIES,STREET,BUILDING";
		final String COLUMNS = "?,?,?,?,?,?";
		final String sql  = SQL.insert("ADDRESS",VALUES, COLUMNS);
		PreparedStatement pstmt = getPsTmt(sql);
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
	
	/**顧客情報に基づくDBからの住所の取得
	 * @throws SQLException */
	public static List<Address> findByCustomer_id(int customer_id) throws SQLException{
		List<Address> list = new ArrayList<Address>();
		final String WHERE = "WHERE CUSTOMER_ID = ?";
		final String sql = SQL.select("ADDRESS").concat(WHERE);
		PreparedStatement pstmt = getPsTmt(sql);
		pstmt.setInt(1, customer_id);
		for(ResultSet rs = pstmt.executeQuery();rs.next();) {
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
		return list;
	}
	
	/**特定住所の削除*/
	public static boolean delByAddress_id(int address_id) {return false;}
}
