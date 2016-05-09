package project.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import project.ExpMngGUI;
import project.ExpensesType;

public class addExpensePanel {
	public static JPanel addExpensePanel() {
		JPanel addExpensePanel = new JPanel();
		addExpensePanel.setBackground(Color.lightGray);

		JLabel addExpenseLabel = new JLabel("Add expense");
		addExpenseLabel.setFont(new Font("Verdana", Font.BOLD, 16));
		addExpenseLabel.setForeground(Color.green);

		JLabel nameLabel = new JLabel("Name");
		nameLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
		nameLabel.setForeground(Color.green);
		JTextField nameField = new JTextField("", 10);

		JLabel valueLabel = new JLabel("Value");
		valueLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
		valueLabel.setForeground(Color.green);
		JTextField valueField = new JTextField("", 10);

		JLabel typeLabel = new JLabel("Type");
		typeLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
		typeLabel.setForeground(Color.green);
		JComboBox<ExpensesType> typeCmbBox = new JComboBox<>(ExpensesType.values());
		for (ExpensesType exp : ExpensesType.values()) {
			typeCmbBox.addItem(exp);
		}

		JButton addExpBtn = new JButton("Add");
		addExpBtn.setForeground(Color.green);
		addExpBtn.setBackground(Color.gray);

		addExpBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ExpMngGUI.LOGGER.fine("adding expense");
				try {
					double expenseValue = (double) Integer.parseInt(valueField.getText());
					// Expense expense = new Expense(nameField.getText(),
					// expenseValue,
					// (ExpensesType)typeCmbBox.getSelectedItem());
					// expMng.addExpense(expense);
				} catch (NumberFormatException e1) {
					ExpMngGUI.LOGGER.warning("failed adding expense " + nameField.getText() + " " + e1);
					JOptionPane.showMessageDialog(addExpensePanel, e1.getMessage());
				}
			}
		});

		JLabel dateLabel = new JLabel("Date");
		dateLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
		dateLabel.setForeground(Color.green);
		JTextField dateField = new JTextField("", 10);
		dateField.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				dateField.setText("");
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				dateField.setText("DD-mm-YYYY");

			}
		});

		JButton addExpAtDateBtn = new JButton("Add");
		addExpBtn.setForeground(Color.green);
		addExpBtn.setBackground(Color.gray);

		addExpAtDateBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ExpMngGUI.LOGGER.fine("adding expense");
				double expenseValue = (double) Integer.parseInt(valueField.getText());
				// Expense expense = new Expense(nameField.getText(),
				// expenseValue, (ExpensesType)typeCmbBox.getSelectedItem());
				// expMng.addExpenseAtDate(expense, dateField.getText());
			}
		});

		addExpensePanel.add(addExpenseLabel);
		addExpensePanel.add(nameLabel);
		addExpensePanel.add(nameField);
		addExpensePanel.add(valueLabel);
		addExpensePanel.add(valueField);
		addExpensePanel.add(typeLabel);
		// addExpensePanel.add(typeCmbBox);
		addExpensePanel.add(addExpBtn);
		addExpensePanel.add(dateLabel);
		addExpensePanel.add(dateField);
		addExpensePanel.add(addExpAtDateBtn);

		return addExpensePanel;
	}
}
