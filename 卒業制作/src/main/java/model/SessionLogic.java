package model;

import java.sql.SQLException;

import beans.Customer;
import dao.CustomerDAO;
/**
 * Cookieを用いてセッション管理を行うクラス
 */
public class SessionLogic {
	/**
	 * セッションIDからの顧客情報取得
	 * @param Cookieから取得したセッションID
	 * @return 取得した顧客情報(失敗の場合はnull)
	 * @throws SQLException 
	 */
	public static Customer execute(String session_id) throws SQLException {
		int id = Integer.parseInt(session_id);
		Customer user = CustomerDAO.findById(id);
		return user;
	}
}
