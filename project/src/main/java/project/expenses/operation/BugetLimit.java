package project.expenses.operation;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import project.Expence;
import project.ExpenceApp;
import project.SetGregorianCalendar;

public class BugetLimit {
	public static boolean bugetMonthLimit(double limit) {
		Date today;
		GregorianCalendar calendar = SetGregorianCalendar.getCalendar();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		today = calendar.getTime();
		int currentYear = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		double bugetLimit = 0;
		boolean warning = false;

		while ((calendar.get(Calendar.YEAR) == currentYear) && (month == calendar.get(Calendar.MONTH))) {
			List<Expence> todaysExpenses = ExpenceApp.expenses.get(today);
			if (todaysExpenses != null) {
				for (Expence exp : todaysExpenses) {
					bugetLimit = bugetLimit + exp.getValue();
				}
			}
			calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 1);
			today = calendar.getTime();
		}
		//System.out.println("bugetlimit "+bugetLimit );
		if (bugetLimit > limit)
			warning = true;
		return warning;
	}
}
