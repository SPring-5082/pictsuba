package model;

import java.util.Date;

public class DateLogic {
	public static Date execute(String date) {
		return date != null? java.sql.Date.valueOf(date): null;
	}
}
