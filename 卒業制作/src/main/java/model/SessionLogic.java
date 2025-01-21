package model;

import java.sql.SQLException;

import beans.Customer;
import dao.CustomerDAO;
import exception.SQLDataNotFoundException;
/**
 * Cookieを用いてセッション管理を行うクラス
 */
public class SessionLogic {
	/**
	 * セッションIDからの顧客情報取得
	 * @param Cookieから取得したセッションID
	 * @return 取得した顧客情報(失敗の場合はnull)
	 * @throws SQLException 
	 * @throws SQLDataNotFoundException 
	 */
	public static Customer execute(String session_id) throws SQLException, SQLDataNotFoundException {
		int id = Integer.parseInt(session_id);
		Customer user = CustomerDAO.findById(id);
		return user;
	}
}
