package project.expenses.operation;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import project.Expense;
import project.ExpenseApp;
import project.SetGregorianCalendar;
/**
 * Class BugetLimit display warning when monthly budget exceeded 
 * @author Madalina&Maria
 *
 */		
public class BudgetLimit {
	public static boolean bugetMonthLimit(double limit) {
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

		if (bugetLimit > limit)
			warning = true;
		return warning;
	}
}
