package project;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;

import org.junit.Test;

import project.expenses.operation.Forecast;

public class ForecastTest {
	public static Path file = Paths.get("Expenses.txt");
	@Test(expected = IllegalArgumentException.class)
	public void testIllegalArgumentMonth() throws IOException, ParseException {
		Storage.load(file);
		double result = Forecast.forecastMonth(15);
	}
	
	@Test
	public void testIsTrueMonth() throws IOException, ParseException {
		Storage.load(file);
		double forecastM = Forecast.forecastMonth(9);
		Boolean result = false;
		
		if (forecastM != 0)
			result = true;

		assertTrue(result);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testIllegalArgumentYear() throws IOException, ParseException {
		Storage.load(file);
		double result = Forecast.forecastYear(1999);
	}
	
	@Test
	public void testIsTrueYear() throws IOException, ParseException {
		Storage.load(file);
		double forecastY = Forecast.forecastYear(2016);
		Boolean result = false;
		
		if (forecastY != 0)
			result = true;

		assertTrue(result);
	}
}
