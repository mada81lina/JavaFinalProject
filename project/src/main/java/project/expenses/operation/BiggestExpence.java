package project.expenses.operation;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import project.Expense;
import project.ExpenseApp;
import project.ExpensesType;
import project.SetGregorianCalendar;
import java.util.logging.Logger;

/**
 * Class BiggestExpense, calculate biggest expense in year and in month
 * 
 * @author Madalina&Maria
 *
 */
public class BiggestExpence {
	/**
	 * logger for this class
	 */
	public static final Logger LOGGER = Logger.getGlobal();

	/**
	 * biggestExpenceYear calculate biggest expense in year
	 * 
	 * @param year
	 * @return biggest expense in year
	 */
	public static String biggestExpenceYear(int year) {
		LOGGER.fine("Extracting biggest expense from year " + year);
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

		if (year > 2000 && year < 2020) {
			String bigYear = getBigYear(year);
			String[] items = bigYear.split("-");
			double bigValue = Double.parseDouble(items[1]);
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

			if (bigValue > biggestExpenceYear)
				bigExpenceYear = bigYear;
			else
				bigExpenceYear = bigExpenceYear + "-" + biggestExpenceYear + "-" + "ONETIME";
		} else {
			LOGGER.warning("No data available.");
			throw new IllegalArgumentException("There is no data available for: " + year);
		}
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
	 * 
	 * @param month
	 * @return biggest expense in month
	 */
	public static String biggestExpenceMonth(int month) {
		LOGGER.fine("Extracting biggest expence from month " + (month+1));
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

		if (month > 0 && month < 13) {
			String bigMonth = getBigMonth(month);
			String[] items = bigMonth.split("-");
			double bigValue = Double.parseDouble(items[1]);
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
			if (bigValue > biggestExpenceMonth)
				bigExpenceMonth = bigMonth;
			else
				bigExpenceMonth = bigExpenceMonth + "-" + biggestExpenceMonth + "-" + "ONETIME";
		} else {
			LOGGER.warning("Miss match value");
			throw new IllegalArgumentException(
					"Insert a number between 1 to 12." + (month+1) + " cannot be converted to a month.");
		}
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
