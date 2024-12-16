package dao;

import java.util.List;

import beans.Address;

public class AddressDAO extends DAO {
	/**DBへの住所追加メソッド*/
	public static boolean insert(){return false;}
	
	/**顧客情報に基づくDBからの住所の取得*/
	public static List<Address> findByCustomer_id(){return null;}
	
	/**特定住所の削除*/
	public static boolean delByAddress_id(int address_id) {return false;}
}
