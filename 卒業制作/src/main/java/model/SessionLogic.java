package model;

import beans.Customer;
/**
 * Cookieを用いてセッション管理を行うクラス
 */
public class SessionLogic {
	/**
	 * セッションIDからの顧客情報取得
	 * @param Cookieから取得したセッションID
	 * @return 取得した顧客情報(失敗の場合はnull)
	 */
	public static Customer execute(String session_id) {
		//DynamoDBからsession_idをキーとして顧客IDを取得
		return null;
	}
}
