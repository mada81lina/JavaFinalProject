package project;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class SetGregorianCalendar {
	
	public static GregorianCalendar getCalendar() {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.set(Calendar.HOUR, 00);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar;
	}
}
