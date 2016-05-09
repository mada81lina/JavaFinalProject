package project.expenses.operation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import project.Expence;
import project.ExpenceApp;
import project.ExpensesType;
import project.SetGregorianCalendar;

public class Lookup {
	@SuppressWarnings("deprecation")
	public static void lookupDate(ExpensesType typeSearch, String date, JTable expensesDate) throws ParseException {
		Date today;
		GregorianCalendar calendar = SetGregorianCalendar.getCalendar();
		;
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		today = calendar.getTime();
		int currentYear = calendar.get(Calendar.YEAR);
		String dateString = date;
		SimpleDateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
		Date parsed = df.parse(dateString);

		while (calendar.get(Calendar.YEAR) == currentYear) {
			if ((today.getDate() == parsed.getDate()) && (today.getMonth() == parsed.getMonth())) {
				List<Expence> todaysExpenses = ExpenceApp.expenses.get(parsed);
				if (todaysExpenses != null) {
					DefaultTableModel model = (DefaultTableModel) expensesDate.getModel();
					model.setColumnCount(0);
					model.setRowCount(0);
					model.addColumn("Name");
					model.addColumn("Value");
					model.addColumn("Type");
					for (Expence exp : todaysExpenses) {
						if (exp.getType() == typeSearch) {
							Object[] rowData = new Object[] { exp.getName(), exp.value, exp.getType() };
							model.addRow(rowData);
						}
					}
					model.fireTableStructureChanged();
				}
			}
		}
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 1);
		today = calendar.getTime();
	}

	public static void lookupMonthly(ExpensesType typeSearch, int month, JTable expensesMonth) {
		Date today;
		GregorianCalendar calendar = SetGregorianCalendar.getCalendar();
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		today = calendar.getTime();
		int currentYear = calendar.get(Calendar.YEAR);

		while ((calendar.get(Calendar.YEAR) == currentYear) && (month == calendar.get(Calendar.MONTH))) {
			List<Expence> todaysExpenses = ExpenceApp.expenses.get(today);
			if (todaysExpenses != null) {
				DefaultTableModel model = (DefaultTableModel) expensesMonth.getModel();
				model.setColumnCount(0);
				model.setRowCount(0);
				model.addColumn("Name");
				model.addColumn("Value");
				for (Expence exp : todaysExpenses) {
					if (exp.getType() == typeSearch) {
						Object[] rowData = new Object[] { exp.getName(), exp.value };
						model.addRow(rowData);
					}
				}
				model.fireTableStructureChanged();
			}
			calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 1);
			today = calendar.getTime();
		}
	}

	public static void lookupYear(ExpensesType typeSearch, int year, JTable expensesYear) {
		Date today;
		GregorianCalendar calendar = SetGregorianCalendar.getCalendar();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.YEAR, year);
		today = calendar.getTime();

		while ((calendar.get(Calendar.YEAR) == year)) {
			List<Expence> todaysExpenses = ExpenceApp.expenses.get(today);
			if (todaysExpenses != null) {
				DefaultTableModel model = (DefaultTableModel) expensesYear.getModel();
				model.setColumnCount(0);
				model.setRowCount(0);
				model.addColumn("Name");
				model.addColumn("Value");
				for (Expence exp : todaysExpenses) {
					if (exp.getType() == typeSearch) {
						Object[] rowData = new Object[] { exp.getName(), exp.value };
						model.addRow(rowData);
					}
				}
			}
			calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 1);
			today = calendar.getTime();
		}
	}
}

// @SuppressWarnings("deprecation")
// public static void lookupDate(ExpensesType typeSearch, String date) throws
// ParseException {
// Date today;
// GregorianCalendar calendar = SetGregorianCalendar.getCalendar();;
// calendar.set(Calendar.MONTH, 0);
// calendar.set(Calendar.DAY_OF_MONTH, 1);
// today = calendar.getTime();
// int currentYear = calendar.get(Calendar.YEAR);
// String dateString = date;
// SimpleDateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
// Date parsed = df.parse(dateString);
//
// while (calendar.get(Calendar.YEAR) == currentYear) {
// if ((today.getDate() == parsed.getDate()) && (today.getMonth() ==
// parsed.getMonth())) {
// List<Expence> todaysExpenses = ExpenceApp.expenses.get(parsed);
// if (todaysExpenses != null) {
// for (Expence exp : todaysExpenses) {
// if (exp.getType() == typeSearch) {
// System.out.println(exp.getName() + " " + exp.value + " " + exp.getType());
// }
// }
// }
// }
// calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 1);
// today = calendar.getTime();
// }
// }
//
// public static void lookupMonthly(ExpensesType typeSearch, int month) {
// Date today;
// GregorianCalendar calendar = SetGregorianCalendar.getCalendar();
// calendar.set(Calendar.MONTH, month);
// calendar.set(Calendar.DAY_OF_MONTH, 1);
// today = calendar.getTime();
// int currentYear = calendar.get(Calendar.YEAR);
//
// while ((calendar.get(Calendar.YEAR) == currentYear) && (month ==
// calendar.get(Calendar.MONTH))) {
// List<Expence> todaysExpenses = ExpenceApp.expenses.get(today);
// if (todaysExpenses != null) {
// for (Expence exp : todaysExpenses) {
// if (exp.getType() == typeSearch)
// System.out.println(exp.getName() + " " + exp.value);
// }
// }
// calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 1);
// today = calendar.getTime();
// }
// }
//
// public static void lookupYear(ExpensesType typeSearch, int year) {
// Date today;
// GregorianCalendar calendar = SetGregorianCalendar.getCalendar();
// calendar.set(Calendar.DAY_OF_MONTH, 1);
// calendar.set(Calendar.MONTH, 0);
// calendar.set(Calendar.YEAR, year);
// today = calendar.getTime();
//
// while ((calendar.get(Calendar.YEAR) == year)) {
// List<Expence> todaysExpenses = ExpenceApp.expenses.get(today);
// if (todaysExpenses != null) {
// for (Expence exp : todaysExpenses) {
// if (exp.getType() == typeSearch)
// System.out.println(exp.getName() + " " + exp.value);
// }
// }
// calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 1);
// today = calendar.getTime();
// }
// }
