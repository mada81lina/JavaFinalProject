package project.expenses.operation;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import project.Expence;
import project.ExpenceApp;
import project.SetGregorianCalendar;

public class Forecast {
	public static double forecastMonth(int month) {
		GregorianCalendar calendar = SetGregorianCalendar.getCalendar();
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 1);
		Date oldDate = calendar.getTime();
		int oldMonth = calendar.get(Calendar.MONTH);
		int oldYear = calendar.get(Calendar.YEAR);
		double forecastOldMonth = 0;

		while (oldMonth == month) {
			List<Expence> oldExpenses = ExpenceApp.expenses.get(oldDate);
			if (oldExpenses != null) {
				for (Expence exp : oldExpenses) {
					forecastOldMonth = forecastOldMonth + exp.getValue();
				}
			}
			calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 1);
			oldMonth = calendar.get(Calendar.MONTH);
			oldDate = calendar.getTime();
		}
		forecastOldMonth = forecastOldMonth * 1.05;
		DecimalFormat twoDForm = new DecimalFormat("#.##");

//		System.out.println("Forecast month " + (month + 1) + " is total expenses fron month " + (month + 1) + "."
//				+ oldYear + " multiply with 5% = " + Double.valueOf(twoDForm.format(forecastOldMonth)));
		return Double.valueOf(twoDForm.format(forecastOldMonth));
	}

	public static double forecastYear() {
		GregorianCalendar calendar = SetGregorianCalendar.getCalendar();
		int currentYear = calendar.get(Calendar.YEAR);
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 1);
		Date oldDate = calendar.getTime();
		int oldYear = calendar.get(Calendar.YEAR);
		double forecastOldYear = 0;

		while (oldYear != currentYear) {
			List<Expence> oldExpenses = ExpenceApp.expenses.get(oldDate);
			if (oldExpenses != null) {
				for (Expence exp : oldExpenses) {
					forecastOldYear = forecastOldYear + exp.getValue();
				}
			}
			calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 1);
			oldDate = calendar.getTime();
			oldYear = calendar.get(Calendar.YEAR);
		}
		forecastOldYear = forecastOldYear * 1.05;
		DecimalFormat twoDForm = new DecimalFormat("#.##");

		System.out.println("Forecast current year is total expenses fron last year multiply with 5% = "
				+ Double.valueOf(twoDForm.format(forecastOldYear)));
		return Double.valueOf(twoDForm.format(forecastOldYear));
	}
}
