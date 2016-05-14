package project.expenses.operation;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import project.Expense;
import project.ExpenseApp;
import project.ExpensesType;
import project.SetGregorianCalendar;
import project.Storage;

/**
 * Class Lookup find all expense from a specific date, month or year
 * 
 * @author Madalina&Maria
 *
 */
public class Lookup {
	/**
	 * logger for this class
	 */
	public static final Logger LOGGER = Logger.getGlobal();

	/**
	 * return all expenses from a specified date
	 * @param typeSearch
	 * @param date
	 * @param expensesDate
	 * @throws ParseException
	 */
	@SuppressWarnings("deprecation")
	public static ArrayList<String> lookupDate(ExpensesType typeSearch, String date) {
		
		LOGGER.fine("Printing " + typeSearch + " expenses from " + date);
		
		String[] splitDate = date.split("-");
		Date today;
		GregorianCalendar calendar = SetGregorianCalendar.getCalendar();
		calendar.set(Calendar.YEAR, Integer.parseInt(splitDate[2]));
		calendar.set(Calendar.MONTH, Integer.parseInt(splitDate[1]) - 1);
		calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(splitDate[0]));
		Date parsed = calendar.getTime();
		parsed.setHours(00);
		int currentYear = calendar.get(Calendar.YEAR);
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		today = calendar.getTime();
		today.setHours(00);
		ArrayList<String> text = new ArrayList<String>();
		String addText = "";
		int m;
		while (calendar.get(Calendar.YEAR) == currentYear) {
			if ((today.getDate() == parsed.getDate()) && (today.getMonth() == parsed.getMonth())) {
				List<Expense> todaysExpenses = ExpenseApp.expenses.get(today);
				if (todaysExpenses != null) {
					for (Expense exp : todaysExpenses) {
						if (exp.getType() == typeSearch) {
							System.out.println("test2");
							addText = "";
							m = today.getMonth() + 1;
							addText = exp.name + " " + exp.getValue() + " " + exp.getType().name() + " "
									+ today.getDate() + "-" + m + "-" + calendar.get(Calendar.YEAR);
							text.add(addText);
						}
					}
				}
				break;
			}
			calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 1);
			today = calendar.getTime();
			today.setHours(00);
		}

		LOGGER.info("Displayed " + typeSearch + " expenses for " + date);
		return text;
	}

	/**
	 * lookupMonthly find all expense from a specific month
	 * 
	 * @param typeSearch
	 * @param month
	 * @param expensesMonth
	 */
	public static ArrayList<String> lookupMonthly(ExpensesType typeSearch, int month) {
		
		LOGGER.fine("Printing " + typeSearch + " expenses from " + 	(month+1));
		
		Date today;
		GregorianCalendar calendar = SetGregorianCalendar.getCalendar();
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		today = calendar.getTime();
		today.setHours(00);
		int currentYear = calendar.get(Calendar.YEAR);
		ArrayList<String> text = new ArrayList<String>();
		String addText = "";
		int m;
		while ((calendar.get(Calendar.YEAR) == currentYear) && (month == calendar.get(Calendar.MONTH))) {
			List<Expense> todaysExpenses = ExpenseApp.expenses.get(today);
			if (todaysExpenses != null) {
				for (Expense exp : todaysExpenses) {
					if (exp.getType() == typeSearch) {
						addText = "";
						m = today.getMonth() + 1;
						addText = exp.name + " " + exp.getValue() + " " + exp.getType().name() + " " + today.getDate()
								+ "-" + m + "-" + calendar.get(Calendar.YEAR);
						text.add(addText);
					}
				}
			}
			calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 1);
			today = calendar.getTime();
			today.setHours(00);
		}
		LOGGER.info("Displayed " + typeSearch + " expenses for " + (month+1));
		return text;
	}

	/**
	 * lookupYear find all expense from a specific year
	 * 
	 * @param typeSearch
	 * @param year
	 * @param expensesYear
	 */
	public static ArrayList<String> lookupYear(ExpensesType typeSearch, int year) {
		
		LOGGER.fine("Printing " + typeSearch + " expenses from " + 	year);
		
		Date today;
		GregorianCalendar calendar = SetGregorianCalendar.getCalendar();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.YEAR, year);
		today = calendar.getTime();
		today.setHours(00);
		ArrayList<String> text = new ArrayList<String>();
		String addText = "";
		int m;
		while ((calendar.get(Calendar.YEAR) == year)) {
			List<Expense> todaysExpenses = ExpenseApp.expenses.get(today);
			if (todaysExpenses != null) {
				for (Expense exp : todaysExpenses) {
					if (exp.getType() == typeSearch) {
						addText = "";
						m = today.getMonth() + 1;
						addText = exp.name + " " + exp.getValue() + " " + exp.getType().name() + " " + today.getDate()
								+ "-" + m + "-" + calendar.get(Calendar.YEAR);
						text.add(addText);
					}
				}
			}
			calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 1);
			today = calendar.getTime();
			today.setHours(00);
		}
		LOGGER.info("Displayed " + typeSearch + " expenses for " + year);
		return text;
	}
/**
 * 	return all expenses saved in text file
 * @return
 * @throws ParseException
 */
	public static ArrayList<String> lookupAll() throws ParseException {
		ArrayList<String> text = new ArrayList<String>();
		String addText = "";
		Date today;
		GregorianCalendar calendar = SetGregorianCalendar.getCalendar();
		SimpleDateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
		Date parsed = df.parse(Storage.aux);
		today = parsed;
		calendar.setTime(today);
		today.setHours(00);
		int i = 1;
		int m;
		while (i < Storage.nrLines) {
			List<Expense> todaysExpenses = ExpenseApp.expenses.get(today);
			if (todaysExpenses != null) {
				for (Expense exp : todaysExpenses) {
					i++;
					addText = "";
					m = today.getMonth() + 1;
					addText = exp.name + " " + exp.getValue() + " " + exp.getType().name() + " " + today.getDate()
							+ "-" + m + "-" + calendar.get(Calendar.YEAR);
					text.add(addText);
				}
			}
			calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 1);
			today = calendar.getTime();
			today.setHours(00);
		}
		return text;
	}
}
