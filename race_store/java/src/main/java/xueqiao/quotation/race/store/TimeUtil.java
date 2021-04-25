package xueqiao.quotation.race.store;

import java.util.Calendar;

public class TimeUtil {
	
	public static String getFormatDayTimestamMs(long timestampMs) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(timestampMs);
		
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int day = c.get(Calendar.DAY_OF_MONTH);
		
		return String.format("%d-%02d-%02d", year, month, day);
	}
	
	public static String getFormatMonthTimestampMs(long timestampMs) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(timestampMs);
		
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		
		return String.format("%d-%02d", year, month);
	}
	
}
