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
import java.util.Locale;

/**
 * Class Storage save dates in file and load from file
 * 
 * @param file
 *            - Expense.file
 * @author Maria&Madalina
 *
 */
public class Storage {
	public static String aux = "Tue May 05 00:00:00 EEST 2015";
	public static int nrLines;
/**
 * save expenses in text file
 * @param file - file name
 * @throws IOException
 * @throws ParseException
 */
	public static void save(Path file) throws IOException, ParseException {
		Date today;
		GregorianCalendar calendar = SetGregorianCalendar.getCalendar();
		calendar.set(Calendar.MONTH, 11);
		calendar.set(Calendar.DAY_OF_MONTH,31);
		Date endDate = calendar.getTime();
		endDate.setHours(00);
		SimpleDateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
		Date parsed = df.parse(aux);
		today = parsed;
		calendar.setTime(today);
		today.setHours(00);
		BufferedWriter writer = Files.newBufferedWriter(file);
		while (today.before(endDate)) {
			List<Expense> todaysExpenses = ExpenseApp.expenses.get(today);
			if (todaysExpenses != null) {
				for (Expense e : todaysExpenses) {
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
/**
 * load expenses from file text
 * @param file - file name
 * @throws IOException
 * @throws ParseException
 */
	public static void load(Path file) throws IOException, ParseException {
		if (Files.exists(file)) {
			BufferedReader reader = Files.newBufferedReader(file);
			String line ;
			nrLines = 0;
			while ((line=reader.readLine()) != null) {
				String[] items = line.split("-");
				ExpensesType type = ExpensesType.valueOf(items[1]);
				double value = Double.parseDouble(items[2]);
				String dateString = items[3];
				SimpleDateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
				Date parsed = df.parse(dateString);
				Expense expence = new Expense(items[0], value, type);
				ExpenseApp.addExpenseAtDate(expence, parsed);
				if (nrLines == 0)
					aux = dateString;
				nrLines++;

			}
			reader.close();
		}
	}

}
