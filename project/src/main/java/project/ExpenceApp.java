package project;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ExpenceApp {
	//second commit
	static Map<Date, List<Expence>> expenses = new HashMap<Date, List<Expence>>();

	public static void main(String[] args) {
		addExpense(new Expence("once", 3000.5, ExpensesType.ONETIME));
		addExpense(new Expence("daily", 5.0, ExpensesType.DAILY));
		addExpense(new Expence("weekly", 10.0, ExpensesType.WEEKLY));
		addExpense(new Expence("monthly", 15.0, ExpensesType.MONTHLY));

		// lookupOnce();
		// lookupDaily();
		// lookupWeekly();
		// lookupMonthly();
		biggestExpenceYear();
		biggestExpenceMonth(7);
	}

	public static void addExpense(Expence expense) {
		Date today;
		GregorianCalendar calendar = getCalendar();
		today = calendar.getTime();
		int currentYear = calendar.get(Calendar.YEAR);
		ExpensesType type = expense.type;

		switch (type) {
		case DAILY:
			while (calendar.get(Calendar.YEAR) == currentYear) {
				addExpenseAtDate(expense, today);
				calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 1);
				today = calendar.getTime();
			}
			break;
		case WEEKLY:
			while (calendar.get(Calendar.YEAR) == currentYear) {
				addExpenseAtDate(expense, today);
				calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 7);
				today = calendar.getTime();
			}
			break;
		case MONTHLY:
			while (calendar.get(Calendar.YEAR) == currentYear) {
				addExpenseAtDate(expense, today);
				calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
				today = calendar.getTime();
			}
			break;
		default:
			addExpenseAtDate(expense, today);
		}
	}

	public static void addExpenseAtDate(Expence expense, Date date) {
		List<Expence> todaysExpenses = expenses.get(date);
		if (todaysExpenses == null) {
			todaysExpenses = new ArrayList<Expence>();
		}
		todaysExpenses.add(expense);

		expenses.put(date, todaysExpenses);
	}

	public static GregorianCalendar getCalendar() {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar;

	}

	public static void lookupOnce() {
		Date today;
		GregorianCalendar calendar = getCalendar();
		today = calendar.getTime();
		int currentYear = calendar.get(Calendar.YEAR);

		while (calendar.get(Calendar.YEAR) == currentYear) {
			List<Expence> todaysExpenses = expenses.get(today);
			if (todaysExpenses != null) {
				for (Expence exp : todaysExpenses) {
					if (exp.type.name() == "ONETIME")
						System.out.println(exp.getName() + " " + exp.value);
				}
			}
			calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 1);
			today = calendar.getTime();
		}
	}

	public static void lookupDaily() {
		Date today;
		GregorianCalendar calendar = getCalendar();
		today = calendar.getTime();
		int currentYear = calendar.get(Calendar.YEAR);

		while (calendar.get(Calendar.YEAR) == currentYear) {
			List<Expence> todaysExpenses = expenses.get(today);
			if (todaysExpenses != null) {
				for (Expence exp : todaysExpenses) {
					if (exp.type.name() == "DAILY")
						System.out.println(exp.getName() + " " + exp.value);
				}
			}
			calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 1);
			today = calendar.getTime();
		}
	}

	public static void lookupWeekly() {
		Date today;
		GregorianCalendar calendar = getCalendar();
		today = calendar.getTime();
		int currentYear = calendar.get(Calendar.YEAR);

		while (calendar.get(Calendar.YEAR) == currentYear) {
			List<Expence> todaysExpenses = expenses.get(today);
			if (todaysExpenses != null) {
				for (Expence exp : todaysExpenses) {
					if (exp.type.name() == "WEEKLY")
						System.out.println(exp.getName() + " " + exp.value);
				}
			}
			calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 7);
			today = calendar.getTime();
		}
	}

	public static void lookupMonthly() {
		Date today;
		GregorianCalendar calendar = getCalendar();
		today = calendar.getTime();
		int currentYear = calendar.get(Calendar.YEAR);

		while (calendar.get(Calendar.YEAR) == currentYear) {
			List<Expence> todaysExpenses = expenses.get(today);
			if (todaysExpenses != null) {
				for (Expence exp : todaysExpenses) {
					if (exp.type.name() == "MONTHLY")
						System.out.println(exp.getName() + " " + exp.value);
				}
			}
			calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
			today = calendar.getTime();
		}
	}

	public static void biggestExpenceYear() {
		Date today;
		GregorianCalendar calendar = getCalendar();
		int currentYear = calendar.get(Calendar.YEAR);
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH,1);
		today = calendar.getTime();
		double biggestExpenceYear = 0;

		while (calendar.get(Calendar.YEAR) == currentYear) {
			List<Expence> todaysExpenses = expenses.get(today);
			if (todaysExpenses != null) {
				for (Expence exp : todaysExpenses) {
					if (exp.getValue() > biggestExpenceYear)
						biggestExpenceYear = exp.getValue();
				}
			}
			calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 1);
			today = calendar.getTime();
		}
		System.out.println("biggest expense per year: " + biggestExpenceYear);
	}

	public static void biggestExpenceMonth(int month) {
		Date today;
		GregorianCalendar calendar = getCalendar();
		int currentYear = calendar.get(Calendar.YEAR);
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH,1);
		int currentMonth = calendar.get(Calendar.MONTH);
		today = calendar.getTime();
		double biggestExpenceMonth = 0;

		while ((currentMonth != month) && (calendar.get(Calendar.YEAR) == currentYear))
		{
			calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
			currentMonth = calendar.get(Calendar.MONTH);
		}
		
		today = calendar.getTime();
		while ((currentMonth == month) && (calendar.get(Calendar.YEAR) == currentYear)) {
				List<Expence> todaysExpenses = expenses.get(today);
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
				System.out.println("biggest expense per month: " + biggestExpenceMonth);
		}
	
}