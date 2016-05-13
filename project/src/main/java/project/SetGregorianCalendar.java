package project;

import java.util.Calendar;
import java.util.GregorianCalendar;
/**
 * Class SetGregorianCalendar set a calendar with time 00:00:00
 * @author Madalina&Maria
 *
 */
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
