package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.CreditCard;

public class CreditCardDAO extends DAO{
	/**
	 * クレジットカード情報入力メソッド
	 * @param card 登録するカード情報
	 * @return 登録の成否
	 * @throws SQLException
	 */
	public static boolean insert(CreditCard card) throws SQLException {
		final String values = "?,?,?,?,?";
		final String columns = "CUSTOMER_ID,NUMBER,EXPIRE,SECURITY_CODE,OWNER_NAME";
		final String sql = SQL.insert("CREDIT_CARDS", values, columns);
		PreparedStatement pstmt = getPsTmt(sql);
		pstmt.setInt(1, card.customer_id());
		pstmt.setString(2, card.number());
		pstmt.setString(3, card.expire());
		pstmt.setInt(4, card.security_code());
		pstmt.setString(5, card.owner_name());
		return pstmt.executeUpdate() > 0;
	}
	
	/**
	 * 顧客IDに基づくカード情報の取得
	 * @param customer_id 顧客ID
	 * @return 条件に合う顧客一覧
	 * @throws SQLException
	 */
	public static List<CreditCard> findByCustomer_id(int customer_id) throws SQLException{
		List<CreditCard> list = new ArrayList<CreditCard>();
		final String WHERE = "WHERE CUSTOMER_ID = ?";
		final String sql = SQL.select("CREDIT_CARDS").concat(WHERE);
		PreparedStatement pstmt = getPsTmt(sql);
		pstmt.setInt(1, customer_id);
		for(ResultSet rs = pstmt.executeQuery();rs.next();) {
			int card_id = rs.getInt(1);
			//customer_id
			String number = rs.getString(3);
			String expire = rs.getString(4);
			int security_code = rs.getInt(5);
			String owner_name = rs.getString(6);
			CreditCard card = new CreditCard(card_id, customer_id, number, expire, security_code, owner_name);
			list.add(card);
		}
		return list;
	}
	
	/**
	 * カードIDに基づくカード情報の削除
	 * @param card_id 削除対象のカードID
	 * @return 削除の成否
	 * @throws SQLException
	 */
	public static boolean delByCard_id(int card_id) throws SQLException {
		final String WHERE = "WHERE CARD_ID = ?";
		final String sql = SQL.delete("CREDIT_CARDS").concat(WHERE);
		PreparedStatement pstmt = getPsTmt(sql);
		pstmt.setInt(1, card_id);
		return pstmt.executeUpdate() > 0;
	}
}
