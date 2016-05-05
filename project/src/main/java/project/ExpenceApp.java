package project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.lang.Object;
import java.util.TimeZone;

public class ExpenceApp {
	// test FR commit
	static Map<Date, List<Expence>> expenses = new HashMap<Date, List<Expence>>();
	static Path file = Paths.get("Expences.txt");

	public static void main(String[] args) throws IOException, ParseException {
		load();
		// addExpense(new Expence("once", 3000.5, ExpensesType.ONETIME));
		// addExpense(new Expence("once2", 3000.5, ExpensesType.ONETIME));
		// addExpense(new Expence("once3", 3000.5, ExpensesType.ONETIME));
		// addExpense(new Expence("daily", 5.0, ExpensesType.DAILY));
		// addExpense(new Expence("weekly", 10.0, ExpensesType.WEEKLY));
		// addExpense(new Expence("monthly", 15.0, ExpensesType.MONTHLY));
		// save();
		// lookupDate(ExpensesType.ONETIME, "Thu May 05 00:00:00 EEST 2016");
		//System.out.println("----------");
		// lookupMonthly(ExpensesType.MONTHLY,4);
		// lookupMonthly(ExpensesType.WEEKLY,4);
		//lookupMonthly(ExpensesType.ONETIME, 4);
		//lookupYear(ExpensesType.ONETIME, 2015);
		//biggestExpenceYear(2015);
		//biggestExpenceMonth(4);
		forecastMonth(4);
		forecastYear();
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
		calendar.set(Calendar.HOUR, 12);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar;

	}

	private static void save() throws IOException {
		Date today, date;
		GregorianCalendar calendar = getCalendar();
		calendar.set(Calendar.MONTH, 11);
		calendar.set(Calendar.DAY_OF_MONTH, 31);
		date = calendar.getTime();
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		today = calendar.getTime();

		BufferedWriter writer = Files.newBufferedWriter(file);
		int i = 0;
		while ((today != date) && (i < 356)) {
			i++;
			List<Expence> todaysExpenses = expenses.get(today);
			if (todaysExpenses != null) {
				for (Expence e : todaysExpenses) {
					// System.out.println(e.toStream() + ":" + today);
					writer.write(e.toStream() + "-" + today);
					writer.newLine();
				}
			}
			calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 1);
			today = calendar.getTime();
		}
		writer.close();
	}

	static void load() throws IOException, ParseException {
		if (Files.exists(file)) {
			// read accounts from file
			BufferedReader reader = Files.newBufferedReader(file);
			String line;
			while ((line = reader.readLine()) != null) {
				String[] items = line.split("-");
				ExpensesType type = ExpensesType.valueOf(items[1]);

				double value = Double.parseDouble(items[2]);
				String dateString = items[3];
				SimpleDateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
				Date parsed = df.parse(dateString);
				Expence expence = new Expence(items[0], value, type);
				addExpenseAtDate(expence, parsed);
			}
			reader.close();
		}
	}

	@SuppressWarnings("deprecation")
	public static void lookupDate(ExpensesType typeSearch, String date) throws ParseException {
		Date today;
		GregorianCalendar calendar = getCalendar();
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		today = calendar.getTime();
		int currentYear = calendar.get(Calendar.YEAR);
		String dateString = date;
		SimpleDateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
		Date parsed = df.parse(dateString);

		while (calendar.get(Calendar.YEAR) == currentYear) {
			if ((today.getDate() == parsed.getDate()) && (today.getMonth() == parsed.getMonth())) {
				List<Expence> todaysExpenses = expenses.get(parsed);
				if (todaysExpenses != null) {
					for (Expence exp : todaysExpenses) {
						if (exp.getType() == typeSearch) {
							System.out.println(exp.getName() + " " + exp.value + " " + exp.getType());
						}
					}
				}
			}
			calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 1);
			today = calendar.getTime();
		}
	}

	public static void lookupMonthly(ExpensesType typeSearch, int month) {
		Date today;
		GregorianCalendar calendar = getCalendar();
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		today = calendar.getTime();
		int currentYear = calendar.get(Calendar.YEAR);

		while ((calendar.get(Calendar.YEAR) == currentYear) && (month == calendar.get(Calendar.MONTH))) {
			List<Expence> todaysExpenses = expenses.get(today);
			if (todaysExpenses != null) {
				for (Expence exp : todaysExpenses) {
					if (exp.getType() == typeSearch)
						System.out.println(exp.getName() + " " + exp.value);
				}
			}
			calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 1);
			today = calendar.getTime();
		}
	}

	public static void lookupYear(ExpensesType typeSearch, int year) {
		Date today;
		GregorianCalendar calendar = getCalendar();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.YEAR, year);
		today = calendar.getTime();
//		int currentYear = calendar.get(Calendar.YEAR);

		while ((calendar.get(Calendar.YEAR) == year)) {
			List<Expence> todaysExpenses = expenses.get(today);
			if (todaysExpenses != null) {
				for (Expence exp : todaysExpenses) {
					if (exp.getType() == typeSearch)
						System.out.println(exp.getName() + " " + exp.value);
				}
			}
			calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 1);
			today = calendar.getTime();
		}
	}
	
	public static void biggestExpenceYear(int year) {
		Date today;
		GregorianCalendar calendar = getCalendar();
		//int currentYear = calendar.get(Calendar.YEAR);
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
			List<Expence> todaysExpenses = expenses.get(today);
			if (todaysExpenses != null) {
				for (Expence exp : todaysExpenses) {
					if (exp.getType().name() == "DAILY") daily = daily + exp.getValue();
					if (exp.getType().name() == "WEEKLY") weekly = weekly + exp.getValue();
					if (exp.getType().name() == "MONTHLY") monthly = monthly + exp.getValue();
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
			

		System.out.println("biggest expense per year: " + expenceValue +  " value " + biggestExpenceYear);
	}

	public static void biggestExpenceMonth(int month) {
		Date today;
		GregorianCalendar calendar = getCalendar();
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


public static void forecastMonth(int month) {
	GregorianCalendar calendar = getCalendar();
	calendar.set(Calendar.MONTH, month);
	calendar.set(Calendar.DAY_OF_MONTH, 1);
	calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 1);
	Date oldDate = calendar.getTime();
	int oldMonth = calendar.get(Calendar.MONTH);
	int oldYear = calendar.get(Calendar.YEAR);
	double forecastOldMonth = 0;

	while (oldMonth == month) {
		List<Expence> oldExpenses = expenses.get(oldDate);
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

	System.out.println("Forecast month " + (month+1) + " is total expenses fron month " + (month+1) + "." + oldYear +
			" multiply with 5% = " + Double.valueOf(twoDForm.format(forecastOldMonth)));
}


public static void forecastYear() {
	GregorianCalendar calendar = getCalendar();
	int currentYear = calendar.get(Calendar.YEAR);
	calendar.set(Calendar.MONTH, 0);
	calendar.set(Calendar.DAY_OF_MONTH, 1);
	calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 1);
	Date oldDate = calendar.getTime();
	int oldYear = calendar.get(Calendar.YEAR);
	double forecastOldYear = 0;

	while (oldYear != currentYear) {
		List<Expence> oldExpenses = expenses.get(oldDate);
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
}
}

