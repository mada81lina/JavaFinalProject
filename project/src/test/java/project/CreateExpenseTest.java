package project;
import static org.junit.Assert.*;
import org.junit.Test;

public class CreateExpenseTest {
	@Test
	public void testIsTrueName() {
		Expense exp = new Expense("paine",5.0,ExpensesType.DAILY);
		String test = "paine";
		assertTrue(exp.getName().equals(test));	
	}
	
	@Test
	public void testIsTrueValue() {
		Expense exp = new Expense("paine",5.3,ExpensesType.DAILY);
		assertEquals("Validate value ",Double.doubleToLongBits(5.3),Double.doubleToLongBits(exp.getValue()));
	}

	
	@Test
	public void testIsTrueType() {
		Expense exp = new Expense("paine",5.0,ExpensesType.DAILY);
		assertEquals("Validate type ",ExpensesType.DAILY,exp.getType());		
	}
	
	public void testIsFalseName() {
		Expense exp = new Expense("paine",5.0,ExpensesType.DAILY);
		assertFalse(exp.getName().equals("lapte"));		
	}
	
	@Test
	public void testIsFalseType() {
		Expense exp = new Expense("paine",5.0,ExpensesType.DAILY);
		assertFalse(ExpensesType.DAILY.toString().equals(exp.getType()));	
	}

}
