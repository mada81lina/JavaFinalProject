package project.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
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

/**
 * Class ExpensePanel add expense in file using GUI interface
 * 
 * @author Madalina&Maria
 *
 */
public class ExpensePanel {
	public static Box addExpensePanel() {
		Box addExpensePanel = Box.createVerticalBox();
		addExpensePanel.setBackground(Color.lightGray);

		JLabel addExpenseLabel = new JLabel("Add expense");
		addExpenseLabel.setFont(new Font("Verdana", Font.BOLD, 16));
		addExpenseLabel.setForeground(Color.green);

		addExpensePanel.add(addExpenseLabel);

		JLabel nameLabel = new JLabel("Name");
		nameLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
		nameLabel.setForeground(Color.blue);
		JTextField nameField = new JTextField("", 20);
		nameField.setMaximumSize(nameField.getPreferredSize());

		addExpensePanel.add(nameLabel);
		addExpensePanel.add(nameField);

		JLabel valueLabel = new JLabel("Value");
		valueLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
		valueLabel.setForeground(Color.blue);
		JTextField valueField = new JTextField("", 20);
		valueField.setMaximumSize(valueField.getPreferredSize());

		addExpensePanel.add(valueLabel);
		addExpensePanel.add(valueField);

		JLabel typeLabel = new JLabel("Type");
		typeLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
		typeLabel.setForeground(Color.blue);
		JPanel cbPanel = new JPanel();
		JComboBox<ExpensesType> typeCmbBox = new JComboBox<>(ExpensesType.values());
		typeCmbBox.setAlignmentX(Component.LEFT_ALIGNMENT);
		cbPanel.add(typeCmbBox);
		cbPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		cbPanel.setBorder(BorderFactory.createLineBorder(Color.green, 3));

		JLabel dateLabel = new JLabel("Date");
		dateLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
		dateLabel.setForeground(Color.blue);
		JTextField dateField = new JTextField("", 20);
		dateField.setMaximumSize(dateField.getPreferredSize());

		addExpensePanel.add(dateLabel);
		addExpensePanel.add(dateField);

		addExpensePanel.add(typeLabel);
		addExpensePanel.add(cbPanel);

		JButton addExpAtDateBtn = new JButton("Add");
		addExpAtDateBtn.setForeground(Color.green);
		addExpAtDateBtn.setBackground(Color.gray);

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
				nameField.setText("");
				valueField.setText("");
				dateField.setText("");

			}
		});

		addExpensePanel.add(addExpAtDateBtn);

		return addExpensePanel;

	}

}
