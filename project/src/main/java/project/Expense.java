package project;

/**
 * Expense class create obj expense
 * 
 * @author Madalina&Maria
 *
 */
public class Expense {
	public String name;
	public Double value;
	public ExpensesType type;

	/**
	 * Constructor Expense, create expenses with
	 * 
	 * @param name
	 *            - expense name
	 * @param value
	 *            - expense value
	 * @param type
	 *            - expense type like: onetime/daily/weekly/monthly
	 *
	 */
	public Expense(String name, Double value, ExpensesType type) {
		this.name = name;
		this.value = value;
		this.type = type;
	}

	@Override
	public String toString() {
		return name;
	}

	public String toStream() {
		return name + "-" + type + "-" + value;
	}

	/**
	 * getter for expense name
	 * 
	 * @return name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * getter for expense value
	 * 
	 * @return value
	 */
	public double getValue() {
		return this.value;
	}

	/**
	 * getter for expense type
	 * 
	 * @return type
	 */
	public ExpensesType getType() {
		return this.type;
	}
}
