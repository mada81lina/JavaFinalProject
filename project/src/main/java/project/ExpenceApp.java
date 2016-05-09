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
import javax.swing.SwingUtilities;

import project.expenses.operation.BugetLimit;
import project.expenses.operation.Forecast;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
public class ExpenceApp{
	// test FR commit
	public static Map<Date, List<Expence>> expenses = new HashMap<Date, List<Expence>>();
	public static Path file = Paths.get("Expences.txt");

	public static void main(String[] args) throws IOException, ParseException {
		Storage.load(file);
		// addExpense(new Expence("once", 3000.5, ExpensesType.ONETIME));
		// addExpense(new Expence("once2", 3000.5, ExpensesType.ONETIME));
		// addExpense(new Expence("once3", 3000.5, ExpensesType.ONETIME));
		// addExpense(new Expence("daily", 5.0, ExpensesType.DAILY));
		// addExpense(new Expence("weekly", 10.0, ExpensesType.WEEKLY));
		// addExpense(new Expence("monthly", 15.0, ExpensesType.MONTHLY));
		// Storage.save(file);
		// lookupDate(ExpensesType.ONETIME, "Thu May 05 00:00:00 EEST 2016");
		// System.out.println("----------");
//		 Lookup.lookupMonthly(ExpensesType.MONTHLY,4);
//		 Lookup.lookupMonthly(ExpensesType.WEEKLY,4);
//		 Lookup.lookupMonthly(ExpensesType.ONETIME, 4);
//		 Lookup.lookupYear(ExpensesType.ONETIME, 2015);
//		BiggestExpence.biggestExpenceYear(2015);
//		BiggestExpence.biggestExpenceMonth(4);
		Forecast.forecastMonth(4);
		Forecast.forecastYear();
		boolean warning = BugetLimit.bugetMonthLimit(2000);
		if (warning == true)
			System.out.println("warning");
		else
			System.out.println("OK");

	}

	public static void addExpense(Expence expense) {
		Date today;
		GregorianCalendar calendar = SetGregorianCalendar.getCalendar();
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

}
