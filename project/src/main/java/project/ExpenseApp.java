package project;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import project.ui.*;

import project.expenses.operation.*;

public class ExpenseApp {
	// 11 may commit
	public static Map<Date, List<Expense>> expenses = new HashMap<Date, List<Expense>>();
	public static Path file = Paths.get("Expences.txt");

	// /**
	// * logger for this class
	// */
	// public static final Logger LOGGER = Logger.getGlobal();
	//
	// /**
	// * @param args
	// */
	public static void main(String[] args) throws IOException, ParseException {
		// new Logging().configure("expense.log");
		Storage.load(file);
		
//		// create the GUI and handle events in EDT
		javax.swing.SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				ExpMngGUI expenseFrame = new ExpMngGUI();
				expenseFrame.setVisible(true);
				// LOGGER.info("ExpenseApp GUI available");
			}
		});
		// Storage.save(file);
		// LOGGER.info("ExpenseApp started");

	}

	public static void addExpense(Expense expense, String dateString) {
		String[] splitDate = dateString.split("-");
		Date today;
		GregorianCalendar calendar = SetGregorianCalendar.getCalendar();
		calendar.set(Calendar.YEAR, Integer.parseInt(splitDate[2]));
		calendar.set(Calendar.MONTH, Integer.parseInt(splitDate[1]) - 1);
		calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(splitDate[0]));
		today = calendar.getTime();
		today.setHours(00);
		int currentYear = calendar.get(Calendar.YEAR);
		ExpensesType type = expense.type;
		switch (type) {
		case DAILY:
			while (calendar.get(Calendar.YEAR) == currentYear) {
				addExpenseAtDate(expense, today);
				calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 1);
				today = calendar.getTime();
				today.setHours(00);
			}
			break;
		case WEEKLY:
			while (calendar.get(Calendar.YEAR) == currentYear) {
				addExpenseAtDate(expense, today);
				calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 7);
				today = calendar.getTime();
				today.setHours(00);
			}
			break;
		case MONTHLY:
			while (calendar.get(Calendar.YEAR) == currentYear) {
				addExpenseAtDate(expense, today);
				calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
				today = calendar.getTime();
				today.setHours(00);
			}
			break;
		default:
			addExpenseAtDate(expense, today);
		}
	}

	public static void addExpenseAtDate(Expense expense, Date date) {
		List<Expense> todaysExpenses = expenses.get(date);
		if (todaysExpenses == null) {
			todaysExpenses = new ArrayList<Expense>();
		}
		todaysExpenses.add(expense);
		expenses.put(date, todaysExpenses);
	}

}
