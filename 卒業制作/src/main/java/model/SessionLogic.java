package model;

import java.sql.SQLException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

import beans.Customer;
import dao.CustomerDAO;
import exception.SQLDataNotFoundException;
/**
 * Cookieを用いてセッション管理を行うクラス
 */
public class SessionLogic {
	public final static String header = "WdYyBPWBA";
	public final static String footer = "hGkw0kOSj";
	/**
	 * セッションIDからの顧客情報取得
	 * @param Cookieから取得したセッションID
	 * @return 取得した顧客情報(失敗の場合はnull)
	 * @throws SQLException 
	 * @throws SQLDataNotFoundException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 */
	
	public static Customer execute(String session_id) throws SQLException, SQLDataNotFoundException, IllegalBlockSizeException, BadPaddingException {
		final String cid = EncryptionLogic.dec(session_id).replaceFirst(header, "").replaceFirst(footer, "");
		int id = Integer.parseInt(cid);
		Customer user = CustomerDAO.findById(id);
		return user;
	}
}
