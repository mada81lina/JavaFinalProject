package project;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;

import org.junit.Test;

import project.expenses.operation.BiggestExpence;

public class BiggestExpensesTest {
	public static Path file = Paths.get("Expenses.txt");
	@Test(expected = IllegalArgumentException.class)
	public void testIllegalArgumentYear() throws IOException, ParseException {
		Storage.load(file);
		String result = BiggestExpence.biggestExpenceYear(2021);
	}

	@Test
	public void testIsTrueYear() throws IOException, ParseException {
		Storage.load(file);
		Boolean result = false;
		String bigYear = BiggestExpence.biggestExpenceYear(2016);
		if (bigYear != null) 
			result = true;

		assertTrue(result);
	}
	
	@Test
	public void testIsFalseYear() throws IOException, ParseException {
		Storage.load(file);
		Boolean result = false;
		String bigYear = BiggestExpence.biggestExpenceYear(2021);
		if (bigYear != null) 

		assertFalse(result);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIllegalArgumentMonth() throws IOException, ParseException {
		Storage.load(file);
		String result = BiggestExpence.biggestExpenceMonth(20);
	}

	@Test
	public void testIsTrueMonth() throws IOException, ParseException {
		Storage.load(file);
		Boolean result = false;
		String bigMonth = BiggestExpence.biggestExpenceMonth(5);
		if (bigMonth != null) 
			result = true;

		assertTrue(result);
	}
	
	@Test
	public void testIsFalseMonth() throws IOException, ParseException {
		Storage.load(file);
		Boolean result = false;
		String bigMonth = BiggestExpence.biggestExpenceMonth(20);
		if (bigMonth != null) 
			result = true;

		assertTrue(result);
	}

}
