package project.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import project.ExpMngGUI;
import project.Expense;
import project.ExpenseApp;
import project.ExpensesType;

public class ExpensePanel {
	public static JPanel addExpensePanel() {
		JPanel addExpensePanel = new JPanel();
		addExpensePanel.setLayout(new GridLayout(6, 2));
		addExpensePanel.setBackground(Color.lightGray);

		JLabel addExpenseLabel = new JLabel("Add expense");
		addExpenseLabel.setFont(new Font("Verdana", Font.BOLD, 16));
		addExpenseLabel.setForeground(Color.green);

		JLabel addExpenseLabel2 = new JLabel("    (new expence)");
		addExpenseLabel2.setFont(new Font("Verdana", Font.BOLD, 16));
		addExpenseLabel2.setForeground(Color.green);

		addExpensePanel.add(addExpenseLabel);
		addExpensePanel.add(addExpenseLabel2);

		JLabel nameLabel = new JLabel("Name");
		nameLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
		nameLabel.setForeground(Color.green);
		JTextField nameField = new JTextField("", 10);

		addExpensePanel.add(nameLabel);
		addExpensePanel.add(nameField);

		JLabel valueLabel = new JLabel("Value");
		valueLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
		valueLabel.setForeground(Color.green);
		JTextField valueField = new JTextField("", 10);

		addExpensePanel.add(valueLabel);
		addExpensePanel.add(valueField);

		JLabel typeLabel = new JLabel("Type");
		typeLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
		typeLabel.setForeground(Color.green);
		JComboBox<ExpensesType> typeCmbBox = new JComboBox<>(ExpensesType.values());

		addExpensePanel.add(typeLabel);
		addExpensePanel.add(typeCmbBox);

		JLabel dateLabel = new JLabel("Date");
		dateLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
		dateLabel.setForeground(Color.green);
		JTextField dateField = new JTextField("", 10);

		JButton addExpAtDateBtn = new JButton("Add");
		addExpAtDateBtn.setForeground(Color.green);
		addExpAtDateBtn.setBackground(Color.gray);

		addExpensePanel.add(dateLabel);
		addExpensePanel.add(dateField);
		addExpensePanel.add(addExpAtDateBtn);

		addExpAtDateBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// ExpMngGUI.LOGGER.fine("adding expense");
				String nameExpense = nameField.getText();
				double valueExpense = Double.parseDouble(valueField.getText());
				String typeExpense = typeCmbBox.getSelectedItem().toString();
				String dateExpense = dateField.getText();
				ExpensesType type = null;
				if (typeExpense == "ONETIME")
					type = ExpensesType.ONETIME;
				if (typeExpense == "DAILY")
					type = ExpensesType.DAILY;
				if (typeExpense == "WEEKLY")
					type = ExpensesType.WEEKLY;
				if (typeExpense == "MONTHLY")
					type = ExpensesType.MONTHLY;

				Expense expense = new Expense(nameExpense, valueExpense, type);
				ExpenseApp.addExpense(expense, dateExpense);

			}
		});

		return addExpensePanel;

	}
}
