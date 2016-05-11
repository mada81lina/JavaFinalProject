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

public class Storage {

		public static void save(Path file) throws IOException {
			Date today, date;
			GregorianCalendar calendar = SetGregorianCalendar.getCalendar();
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


		public static void load(Path file) throws IOException, ParseException {
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
					Expense expence = new Expense(items[0], value, type);
					ExpenseApp.addExpenseAtDate(expence, parsed);
				}
				reader.close();
			}
		}
		
}
