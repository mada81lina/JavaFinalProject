package project.expenses.operation;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import project.Expense;
import project.ExpenseApp;
import project.SetGregorianCalendar;
import java.util.logging.Logger;
/**
 * Class BugetLimit display warning when monthly budget exceeded 
 * @author Madalina&Maria
 *
 */		
public class BudgetLimit {
	public static final Logger LOGGER = Logger.getGlobal();
	/**
	 * 
	 * @param limit 
	 * @return true if limit is exceeded
	 */
	public static boolean bugetMonthLimit(double limit) {
		LOGGER.fine("Inserting " + limit + " as budget limit for current period.");
		Date today;
		GregorianCalendar calendar = SetGregorianCalendar.getCalendar();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		today = calendar.getTime();
		today.setHours(00);
		int currentYear = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		double bugetLimit = 0;
		boolean warning = false;

		while ((calendar.get(Calendar.YEAR) == currentYear) && (month == calendar.get(Calendar.MONTH))) {
			List<Expense> todaysExpenses = ExpenseApp.expenses.get(today);
			if (todaysExpenses != null) {
				for (Expense exp : todaysExpenses) {
					bugetLimit = bugetLimit + exp.getValue();
				}
			}
			calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 1);
			today = calendar.getTime();
			today.setHours(00);
		}

		if (limit > 0 && limit < 99999) {
			if (bugetLimit > limit)
				warning = true;
			LOGGER.warning("The budget limit has been reached!");
			return warning;
		} else {
			LOGGER.warning("Budget limit should be a positive number below 99999!");
			throw new IllegalArgumentException("Insert a positive number smaller than 99999");
		}
	}
}
