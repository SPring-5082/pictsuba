package model;

import java.util.Date;

public class DateLogic {
	public static Date valueOf(String date) {
		return date != null? java.sql.Date.valueOf(date): null;
	}
	
	public static Date nextDate() {
		Date date = new Date();
		int year = date.getYear();
		date.setDate(date.getDate()+1);
		return date;
	}
}
