package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import beans.Product;

public class ProductDAO extends DAO {
	public static Product findById(int id) throws SQLException {
		final String where  = "WHERE PRODUCT_ID = ?";
		final String sql = SQL.select("PRODUCTS").concat(where);
		PreparedStatement pstmt = getPsTmt(sql);
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next())	{
			int product_id = rs.getInt(1);
			String product_name = rs.getString(2);
			Date add_date = rs.getDate(3);
			int price = rs.getInt(4);
			int creator_id = rs.getInt(5);
			int stock = rs.getInt(6);
			int lookup = rs.getInt(7);
			int point = rs.getInt(8);
			String images = rs.getString(9);
			String descryption = rs.getString(10);
			return new Product(product_id, product_name, add_date, price, creator_id, stock, lookup, point, images, descryption);
		}
		return null;
	}
	
	public static List<Product> findByRecommendation() throws SQLException{
		List<Product> list = new ArrayList<Product>();
		final String sql = "SELECT \n"
				+ "	PRODUCT_ID,\n"
				+ "	PRODUCT_NAME,\n"
				+ "	ADD_DATE,\n"
				+ "	PRICE,\n"
				+ "	CREATOR_ID,\n"
				+ "	STOCK,\n"
				+ "	LOOKUP,\n"
				+ "	POINT,\n"
				+ "	IMAGES,\n"
				+ "	DESCRYPTION,\n"
				+ "	(\n"
				+ "		SELECT\n"
				+ "			SUM(PRODUCT_ID)\n"
				+ "		FROM\n"
				+ "			ORDER_HISTORIES\n"
				+ "	)AS SALES_QUANTITY\n"
				+ "	\n"
				+ "FROM \n"
				+ "	PRODUCTS\n"
				+ "WHERE\n"
				+ "	\n"
				+ "	PRODUCT_ID IN (\n"
				+ "		SELECT\n"
				+ "			PRODUCT_ID\n"
				+ "		FROM\n"
				+ "			ORDER_HISTORIES\n"
				+ "		WHERE\n"
				+ "			CUSTOMER_ID IN (\n"
				+ "				SELECT\n"
				+ "					CUSTOMER_ID\n"
				+ "				FROM\n"
				+ "					CUSTOMERS\n"
				+ "				WHERE\n"
				+ "					AGE BETWEEN 顧客-5 AND 顧客+5\n"
				+ "			)\n"
				+ "	)\n"
				+ "	\n"
				+ "	AND\n"
				+ "	\n"
				+ "	PRODUCT_ID NOT IN(\n"
				+ "		SELECT\n"
				+ "			PRODUCT_ID\n"
				+ "		FROM\n"
				+ "			ORDER_HISTORIES\n"
				+ "		WHERE\n"
				+ "			CUSTOMER_ID = 顧客番号\n"
				+ "	)\n"
				+ "ORDER BY\n"
				+ "	SALE QUANTITY DESC,\n"
				+ "	LOOKUP DESC\n"
				+ ";";
		PreparedStatement pstmt = getPsTmt(sql);
		for(ResultSet rs = pstmt.executeQuery();rs.next();
				new Product(
						rs.getInt(1), 
						rs.getString(2), 
						rs.getDate(3), 
						rs.getInt(4), 
						rs.getInt(5), 
						rs.getInt(6),
						rs.getInt(7), 
						rs.getInt(8), 
						rs.getString(9), 
						rs.getString(10))
				) {}
		return list;
	}
	
}
