package project;

public class Expence {
	String name;
	Double value;
	ExpensesType type;
	//test second commit
	public Expence(String name, Double value, ExpensesType type) {
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
		return name;
	}

	public String getName() {
		return this.name;
	}

	public double getValue() {
		return this.value;
	}
}
