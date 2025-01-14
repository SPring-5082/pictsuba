package model;

public class TimeLogic {
	public static int toSecond(int day, int hour, int minute, int second) {
		return dayToSecond(day) + hourToSecond(hour) + minuteToSecond(minute) + second;
	}
	
	public static int dayToSecond(int day) {
		return minuteToSecond(hourToMinute(dayToHour(day)));
	}
	
	public static int hourToSecond(int hour) {
		return minuteToSecond(hourToMinute(hour));
	}
	
	public static int dayToHour(int day) {
		return day*24;
	}
	
	public static int hourToMinute(int hour) {
		return hour*60;
	}
	
	public static int minuteToSecond(int minute) {
		return minute*60;
	}
	
}
