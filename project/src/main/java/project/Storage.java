package project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Class Storage save dates in file and load from file
 * 
 * @param file
 *            - Expense.file
 * @author Maria&Madalina
 *
 */
public class Storage {
	public static String aux;
	public static int nrLines;

	public static void save(Path file) throws IOException, ParseException {
		Date today;
		GregorianCalendar calendar = SetGregorianCalendar.getCalendar();
		SimpleDateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
		Date parsed = df.parse(aux);
		today = parsed;
		calendar.setTime(today);
		today.setHours(00);
		int i = 1;
		BufferedWriter writer = Files.newBufferedWriter(file);
		while (i < nrLines) {
			List<Expense> todaysExpenses = ExpenseApp.expenses.get(today);
			if (todaysExpenses != null) {
				for (Expense e : todaysExpenses) {
					i++;
					writer.write(e.toStream() + "-" + today);
					writer.newLine();
				}
			}
			calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 1);
			today = calendar.getTime();
			today.setHours(00);
		}
		writer.close();
	}

	public static void load(Path file) throws IOException, ParseException {
		if (Files.exists(file)) {
			BufferedReader reader = Files.newBufferedReader(file);
			String line;
			nrLines = 0;
			while ((line = reader.readLine()) != null) {
				String[] items = line.split("-");
				ExpensesType type = ExpensesType.valueOf(items[1]);
				double value = Double.parseDouble(items[2]);
				String dateString = items[3];
				SimpleDateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
				Date parsed = df.parse(dateString);
				Expense expence = new Expense(items[0], value, type);
				ExpenseApp.addExpenseAtDate(expence, parsed);
				if (nrLines == 1)
					aux = dateString;
				nrLines++;

			}
			reader.close();
		}
	}

}
