package project.expenses.operation;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import project.Expense;
import project.ExpenseApp;
import project.ExpensesType;
import project.SetGregorianCalendar;
/**
 * Class BiggestExpense, calculate biggest expense in year and in month
 * @author Madalina&Maria
 *
 */
public class BiggestExpence {
	/**
	 * biggestExpenceYear calculate biggest expense in year
	 * @param year
	 *
	 */
	public static String biggestExpenceYear(int year) {
		Date today;
		String bigExpenceYear = null;
		GregorianCalendar calendar = SetGregorianCalendar.getCalendar();
		int currentYear = year;
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.YEAR, year);
		today = calendar.getTime();
		today.setHours(00);
		double biggestExpenceYear = 0;

		String bigYear = getBigYear(year);
		String[] items = bigYear.split("-");
		double valuexx = Double.parseDouble(items[1]);
		while (calendar.get(Calendar.YEAR) == currentYear) {
			List<Expense> todaysExpenses = ExpenseApp.expenses.get(today);
			if (todaysExpenses != null) {
				for (Expense exp : todaysExpenses) {
					if (exp.getValue() > biggestExpenceYear) {
						biggestExpenceYear = exp.getValue();
						bigExpenceYear = exp.getName();
					}
				}
			}
			calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 1);
			today = calendar.getTime();
			today.setHours(00);
		}

		if (valuexx > biggestExpenceYear)
			bigExpenceYear = bigYear;
		else
			bigExpenceYear = bigExpenceYear + "-" + biggestExpenceYear + "-" + "ONETIME";
		return bigExpenceYear;
	}

	public static String getBigYear(int year) {
		Date today;
		String bigExpenceYear;
		GregorianCalendar calendar = SetGregorianCalendar.getCalendar();
		int currentMonth = calendar.get(Calendar.MONTH);
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		today = calendar.getTime();
		today.setHours(00);
		String auxMonth = null;
		Double auxMonthValue = 0.0;
		String auxWeekly = null;
		Double auxWeeklyValue = 0.0;
		String auxDaily = null;
		Double auxDailyValue = 0.0;
		Double aux = 0.0;

		while (calendar.get(Calendar.MONTH) == currentMonth) {
			List<Expense> todaysExpenses = ExpenseApp.expenses.get(today);
			if (todaysExpenses != null) {
				for (Expense exp : todaysExpenses) {
					if (exp.getType().name() == "MONTHLY") {
						if (exp.getValue() > auxMonthValue) {
							auxMonth = exp.getName();
							auxMonthValue = exp.getValue();
						}
					}
					if (exp.getType().name() == "WEEKLY") {
						if (exp.getValue() > auxWeeklyValue) {
							auxWeekly = exp.getName();
							auxWeeklyValue = exp.getValue();
						}
					}
					if (exp.getType().name() == "DAILY") {
						if (exp.getValue() > auxDailyValue) {
							auxDaily = exp.getName();
							auxDailyValue = exp.getValue();
						}
					}
				}
			}
			calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 1);
			today = calendar.getTime();
			today.setHours(00);
		}

		auxMonthValue = auxMonthValue * 12;
		auxWeeklyValue = auxWeeklyValue * 52;
		auxDailyValue = auxDailyValue * 365;

		if (auxMonthValue > auxWeeklyValue) {
			bigExpenceYear = auxMonth + "-" + auxMonthValue + "-" + "monthly";
			aux = auxMonthValue;
		}

		else {
			bigExpenceYear = auxWeekly + "-" + auxWeeklyValue + "-" + "weekly";
			aux = auxWeeklyValue;
		}

		if (auxDailyValue > aux)
			bigExpenceYear = auxDaily + "-" + auxDailyValue + "-" + "daily";
		return bigExpenceYear;
	}
	/**
	 * biggestExpenceMonth calculate biggest expense in month
	 * @param month
	 *
	 */
	public static String biggestExpenceMonth(int month) {
		Date today;
		GregorianCalendar calendar = SetGregorianCalendar.getCalendar();
		int currentYear = calendar.get(Calendar.YEAR);
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		int currentMonth = calendar.get(Calendar.MONTH);
		today = calendar.getTime();
		today.setHours(00);
		double biggestExpenceMonth = 0;
		String bigExpenceMonth = null;

		String bigMonth = getBigMonth(month);
		String[] items = bigMonth.split("-");
		double valuexx = Double.parseDouble(items[1]);
		while ((currentMonth != month) && (calendar.get(Calendar.YEAR) == currentYear)) {
			calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
			currentMonth = calendar.get(Calendar.MONTH);
		}

		today = calendar.getTime();
		today.setHours(00);
		while ((currentMonth == month) && (calendar.get(Calendar.YEAR) == currentYear)) {
			List<Expense> todaysExpenses = ExpenseApp.expenses.get(today);
			if (todaysExpenses != null) {
				for (Expense exp : todaysExpenses) {
					if (exp.getValue() > biggestExpenceMonth) {
						biggestExpenceMonth = exp.getValue();
						bigExpenceMonth = exp.getName();
					}
				}
			}
			calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 1);
			currentMonth = calendar.get(Calendar.MONTH);
			today = calendar.getTime();
			today.setHours(00);
		}
		if (valuexx > biggestExpenceMonth)
			bigExpenceMonth = bigMonth;
		else
			bigExpenceMonth = bigExpenceMonth + "-" + biggestExpenceMonth + "-" + "ONETIME";
		return bigExpenceMonth;
	}

	public static String getBigMonth(int month) {
		Date today;
		String bigExpenceYear;
		GregorianCalendar calendar = SetGregorianCalendar.getCalendar();
		int currentMonth = calendar.get(Calendar.MONTH);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		today = calendar.getTime();
		today.setHours(00);
		String auxMonth = null;
		Double auxMonthValue = 0.0;
		String auxWeekly = null;
		Double auxWeeklyValue = 0.0;
		String auxDaily = null;
		Double auxDailyValue = 0.0;
		Double aux = 0.0;

		while (calendar.get(Calendar.MONTH) == currentMonth) {
			List<Expense> todaysExpenses = ExpenseApp.expenses.get(today);
			if (todaysExpenses != null) {
				for (Expense exp : todaysExpenses) {
					if (exp.getType().name() == "MONTHLY") {
						if (exp.getValue() > auxMonthValue) {
							auxMonth = exp.getName();
							auxMonthValue = exp.getValue();
						}
					}
					if (exp.getType().name() == "WEEKLY") {
						if (exp.getValue() > auxWeeklyValue) {
							auxWeekly = exp.getName();
							auxWeeklyValue = exp.getValue();
						}
					}
					if (exp.getType().name() == "DAILY") {
						if (exp.getValue() > auxDailyValue) {
							auxDaily = exp.getName();
							auxDailyValue = exp.getValue();
						}
					}
				}
			}
			calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 1);
			today = calendar.getTime();
			today.setHours(00);
		}

		auxWeeklyValue = auxWeeklyValue * 4;
		auxDailyValue = auxDailyValue * 30;

		if (auxMonthValue > auxWeeklyValue) {
			bigExpenceYear = auxMonth + "-" + auxMonthValue + "-" + "monthly";
			aux = auxMonthValue;
		}

		else {
			bigExpenceYear = auxWeekly + "-" + auxWeeklyValue + "-" + "weekly";
			aux = auxWeeklyValue;
		}

		if (auxDailyValue > aux)
			bigExpenceYear = auxDaily + "-" + auxDailyValue + "-" + "daily";
		return bigExpenceYear;
	}
}
