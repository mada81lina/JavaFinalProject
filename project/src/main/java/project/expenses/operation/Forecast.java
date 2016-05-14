package project.expenses.operation;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Logger;

import project.Expense;
import project.ExpenseApp;
import project.SetGregorianCalendar;

/**
 * Class Forecast, display forecast The forecast considers current period and
 * adds 5% to it for a future similar period (same month or whole year). 
 * 
 * @author Madalina&Maria
 *
 */
public class Forecast {
	/**
	 * logger for this class
	 */
	public static final Logger LOGGER = Logger.getGlobal();

	/**
	 * calculate forecast/month
	 * 
	 * @param month
	 * @return
	 */
	public static double forecastMonth(int month) {
		LOGGER.fine("Calculating forecast for month " + (month+1));

		GregorianCalendar calendar = SetGregorianCalendar.getCalendar();

		if (month >= 0 && month < 13) {
			calendar.set(Calendar.MONTH, month);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 1);
			calendar.set(Calendar.HOUR, 00);
			Date oldDate = calendar.getTime();
			oldDate.setHours(00);
			int oldMonth = calendar.get(Calendar.MONTH);
			double forecastOldMonth = 0;
			while (oldMonth == month) {
				List<Expense> oldExpenses = ExpenseApp.expenses.get(oldDate);
				if (oldExpenses != null) {
					for (Expense exp : oldExpenses) {
						forecastOldMonth = forecastOldMonth + exp.getValue();
					}
				}
				calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 1);
				oldMonth = calendar.get(Calendar.MONTH);
				oldDate = calendar.getTime();
				oldDate.setHours(0);
			}
			forecastOldMonth = forecastOldMonth * 1.05;
			DecimalFormat twoDForm = new DecimalFormat("#.##");
			return Double.valueOf(twoDForm.format(forecastOldMonth));
		} else {
			LOGGER.warning("Miss match value");
			throw new IllegalArgumentException(
					"Insert a number between 1 to 12." + (month+1) + " cannot be converted to a month.");
		}
	}

	/**
	 * calculate forecast/year
	 * 
	 * @param year
	 * @return forecast year
	 */
	public static double forecastYear(int year) {

		LOGGER.fine("Calculating forecast for year " + year);

		GregorianCalendar calendar = SetGregorianCalendar.getCalendar();

		if (year > 2000 && year < 2020) {
			calendar.set(Calendar.MONTH, 0);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			calendar.set(Calendar.YEAR, year);
			Date oldDate = calendar.getTime();
			int oldYear = calendar.get(Calendar.YEAR);
			int currentYear = oldYear + 1;
			double forecastOldYear = 0;

			while (oldYear != currentYear) {
				List<Expense> oldExpenses = ExpenseApp.expenses.get(oldDate);
				if (oldExpenses != null) {
					for (Expense exp : oldExpenses) {
						forecastOldYear = forecastOldYear + exp.getValue();
					}
				}
				calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 1);
				oldDate = calendar.getTime();
				oldDate.setHours(00);
				oldYear = calendar.get(Calendar.YEAR);
			}
			forecastOldYear = forecastOldYear * 1.05;
			DecimalFormat twoDForm = new DecimalFormat("#.##");

			return Double.valueOf(twoDForm.format(forecastOldYear));
		} else {
			LOGGER.warning("Request cannot be solved.");
			throw new IllegalArgumentException("Forecast cannot be calculated for: " + year);
		}
	}
}
