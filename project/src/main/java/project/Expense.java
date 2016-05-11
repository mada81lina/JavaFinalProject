package project;

public class Expense {
	public String name;
	public Double value;
	public ExpensesType type;
	//test second commit
	public Expense(String name, Double value, ExpensesType type) {
		this.name = name;
		this.value = value;
		this.type = type;
	}

	@Override
	public String toString() {
		// TODO Auto-generated hod stub
		return name;
	}

	public String toStream() {
		// TODO Auto-generated hod stub
		return name + "-" + type + "-" + value;
	}

	public String getName() {
		return this.name;
	}

	public double getValue() {
		return this.value;
	}
	
	public ExpensesType getType() {
		return this.type;
	}
}
