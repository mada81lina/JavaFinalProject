package project;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;

import org.junit.Test;

import project.expenses.operation.BudgetLimit;

public class BugetLimitTest {
	public static Path file = Paths.get("Expenses.txt");
	@Test(expected = IllegalArgumentException.class)
	public void testIllegalArgument() throws IOException, ParseException {
		Storage.load(file);
		boolean result = BudgetLimit.bugetMonthLimit(0);
	}

}
