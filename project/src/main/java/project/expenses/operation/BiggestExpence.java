package project.expenses.operation;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import project.Expence;
import project.ExpenceApp;
import project.SetGregorianCalendar;

public class BiggestExpence {
	public static double biggestExpenceYear(int year) {
		Date today;
		GregorianCalendar calendar = SetGregorianCalendar.getCalendar();
		int currentYear = year;
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.YEAR, year);
		today = calendar.getTime();
		double biggestExpenceYear = 0;
		double daily = 0;
		double weekly = 0;
		double monthly = 0;
		String dailyExpence = null;
		String weeklyExpence = null;
		String monthlyExpence = null;
		String expenceValue = null;

		while (calendar.get(Calendar.YEAR) == currentYear) {
			List<Expence> todaysExpenses = ExpenceApp.expenses.get(today);
			if (todaysExpenses != null) {
				for (Expence exp : todaysExpenses) {
					if (exp.getType().name() == "DAILY")
						daily = daily + exp.getValue();
					if (exp.getType().name() == "WEEKLY")
						weekly = weekly + exp.getValue();
					if (exp.getType().name() == "MONTHLY")
						monthly = monthly + exp.getValue();
					dailyExpence = exp.getName();
					weeklyExpence = exp.getName();
					monthlyExpence = exp.getName();

					if (exp.getValue() > biggestExpenceYear) {
						biggestExpenceYear = exp.getValue();
						expenceValue = exp.getName();
					}
				}
			}
			calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 1);
			today = calendar.getTime();
		}
		if (daily > biggestExpenceYear) {
			biggestExpenceYear = daily;
			expenceValue = dailyExpence;
		}

		if (weekly > biggestExpenceYear) {
			biggestExpenceYear = weekly;
			expenceValue = weeklyExpence;
		}

		if (monthly > biggestExpenceYear) {
			biggestExpenceYear = monthly;
			expenceValue = monthlyExpence;
		}

		//System.out.println("biggest expense per year: " + expenceValue + " value " + biggestExpenceYear);
		return biggestExpenceYear;
	}

	public static double biggestExpenceMonth(int month) {
		Date today;
		GregorianCalendar calendar = SetGregorianCalendar.getCalendar();
		int currentYear = calendar.get(Calendar.YEAR);
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		int currentMonth = calendar.get(Calendar.MONTH);
		today = calendar.getTime();
		double biggestExpenceMonth = 0;

		while ((currentMonth != month) && (calendar.get(Calendar.YEAR) == currentYear)) {
			calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
			currentMonth = calendar.get(Calendar.MONTH);
		}

		today = calendar.getTime();
		while ((currentMonth == month) && (calendar.get(Calendar.YEAR) == currentYear)) {
			List<Expence> todaysExpenses = ExpenceApp.expenses.get(today);
			if (todaysExpenses != null) {
				for (Expence exp : todaysExpenses) {
					if (exp.getValue() > biggestExpenceMonth)
						biggestExpenceMonth = exp.getValue();
				}
			}
			calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 1);
			currentMonth = calendar.get(Calendar.MONTH);
			today = calendar.getTime();
		}
		//System.out.println("biggest expense per month: " + biggestExpenceMonth);
		return biggestExpenceMonth;
	}
}
