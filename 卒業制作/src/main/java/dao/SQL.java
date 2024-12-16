package dao;

public class SQL {
	
	private static final String 
		/**INSERTの定型文*/
		INSERT = "INSERT INTO TABLE_NAME VALUES (STATEMENT)",
		
		INSERTcol = "INSERT INTO TABLE_NAME (COLUMNS) VALUES (STATEMENT)", 
		
		/**SELECTの定型文*/
		SELECT = "SELECT * FROM TABLE_NAME ",
		
		/**DELETEの定型文*/
		DELETE = "DELETE FROM TABLE_NAME ",
		
		/**UPDATEの定型文*/
		UPDATE = "UPDATE TABLE_NAME "
	;
	
	static String insert(String table_name, String statement) {
		return INSERT.replaceFirst("TABLE_NAME", table_name).replaceFirst("STATEMENT", statement);
	}
	
	static String insert(String table_name, String statement, String columns) {
		return INSERTcol.replaceFirst("TABLE_NAME", table_name).replaceFirst("COLUMNS", columns).replaceFirst("STATEMENT", statement);
	}
	
	static String select(String table_name) {
		return SELECT.replaceFirst("TABLE_NAME", table_name);
	}
	
	static String delete(String table_name) {
		return DELETE.replaceFirst("TABLE_NAME", table_name);
	}
	
	static String update(String table_name) {
		return UPDATE.replaceFirst("TABLE_NAME", table_name);
	}
	
}
