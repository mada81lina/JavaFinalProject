package project.expenses.operation;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import project.Expense;
import project.ExpenseApp;
import project.SetGregorianCalendar;

public class Forecast {
	public static double forecastMonth(int month) {
		GregorianCalendar calendar = SetGregorianCalendar.getCalendar();
		calendar.set(Calendar.MONTH,month-1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 1);
		calendar.set(Calendar.HOUR, 00);
		Date oldDate = calendar.getTime();
		oldDate.setHours(00);
		int oldMonth = calendar.get(Calendar.MONTH);
		int oldYear = calendar.get(Calendar.YEAR);
		double forecastOldMonth = 0;
		while (oldMonth == month-1) {
			List<Expense> oldExpenses = ExpenseApp.expenses.get(oldDate);
			if (oldExpenses != null) {
				for (Expense exp : oldExpenses) {
					forecastOldMonth = forecastOldMonth + exp.getValue();
				}
			}
			calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 1);
			oldMonth = calendar.get(Calendar.MONTH);
			oldDate = calendar.getTime();
			oldDate.setHours(0);
		}
		forecastOldMonth = forecastOldMonth * 1.05;
		DecimalFormat twoDForm = new DecimalFormat("#.##");
		return Double.valueOf(twoDForm.format(forecastOldMonth));
	}

	public static double forecastYear(int year) {
		GregorianCalendar calendar = SetGregorianCalendar.getCalendar();
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.YEAR, year);
		Date oldDate = calendar.getTime();
		int oldYear = calendar.get(Calendar.YEAR);
		int currentYear = oldYear + 1;
		double forecastOldYear = 0;

		while (oldYear != currentYear) {
			List<Expense> oldExpenses = ExpenseApp.expenses.get(oldDate);
			if (oldExpenses != null) {
				for (Expense exp : oldExpenses) {
					forecastOldYear = forecastOldYear + exp.getValue();
				}
			}
			calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 1);
			oldDate = calendar.getTime();
			oldDate.setHours(00);
			oldYear = calendar.get(Calendar.YEAR);
		}
		forecastOldYear = forecastOldYear * 1.05;
		DecimalFormat twoDForm = new DecimalFormat("#.##");

		return Double.valueOf(twoDForm.format(forecastOldYear));
	}
}
