package project.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import project.ExpensesType;
import project.expenses.operation.*;
import javax.swing.*;

/**
 * Class ViewExpensePanel display in GUI date lookup expenses by type, month
 * lookup expenses by type, year lookup expenses by type,
 * 
 * @author Madalina&Maria
 *
 */
public class ViewExpensePanel {

	/**
	 * logger for this class
	 */
	public static final Logger LOGGER = Logger.getGlobal();
	static ArrayList<String> text;

	/**
	 * 
	 * @return
	 * @throws ParseException
	 */
	public static Box ViewExpensePanel() throws ParseException {
		Box viewExpPanel = Box.createVerticalBox();
		viewExpPanel.setBackground(Color.lightGray);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(5, 5, 100, 200);
		textArea.setEditable(false);
		textArea.setVisible(true);
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(3, 3, 400, 400);
		
		text = Lookup.lookupAll();
		textArea.setText("");
		textArea.append("            ALL EXPENSES"+ "\n");
		if (text != null) {
			for (String aux : text) {
				textArea.append(aux + "\n");
			}
		}

		JLabel title = new JLabel("Expense preview    (filter expenses)");
		title.setFont(new Font("Verdana", Font.BOLD, 16));
		title.setForeground(Color.gray);

		viewExpPanel.add(title);

		JLabel typeLabel = new JLabel("Type");
		typeLabel.setFont(new Font("Verdana", Font.BOLD, 15));
		typeLabel.setForeground(Color.green);
		JPanel cbPanel = new JPanel();
		JComboBox<ExpensesType> typeCmbBox = new JComboBox<>(ExpensesType.values());
		typeCmbBox.setAlignmentX(Component.LEFT_ALIGNMENT);
		cbPanel.add(typeCmbBox);
		cbPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		cbPanel.setBorder(BorderFactory.createLineBorder(Color.green, 3));

		viewExpPanel.add(typeLabel);
		viewExpPanel.add(cbPanel);

		JLabel dateLabel = new JLabel("Date");
		dateLabel.setFont(new Font("Verdana", Font.BOLD, 15));
		dateLabel.setForeground(Color.green);

		JTextField dateField = new JTextField("eg. 5-05-2016", 20);
		dateField.setMaximumSize(dateField.getPreferredSize());
		dateField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				dateField.setText("");
			}

			public void focusLost(FocusEvent e) {
				// nothing
			}
		});
		
		viewExpPanel.add(dateLabel);
		viewExpPanel.add(dateField);

		JButton viewDateBtn = new JButton("View");
		viewDateBtn.setFont(new Font("Verdana", Font.BOLD, 12));
		viewDateBtn.setForeground(Color.green);
		viewDateBtn.setBackground(Color.gray);
		viewExpPanel.add(viewDateBtn);
		viewDateBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				LOGGER.fine("Displaying" + typeCmbBox.getSelectedItem()+ " expenses for " + dateField.getText());
				textArea.setText("");
				String date = dateField.getText();
				text = Lookup.lookupDate((ExpensesType) typeCmbBox.getSelectedItem(), date);
				textArea.setText("");
				textArea.setForeground(Color.red);
				if (text != null) {
					for (String aux : text) {
						textArea.append(aux + "\n");
					}
				}
			}
		});

		JLabel monthYearLabel = new JLabel("Month/Year");
		monthYearLabel.setFont(new Font("Verdana", Font.BOLD, 15));
		monthYearLabel.setForeground(Color.green);

		JTextField monthYearField = new JTextField("eg. 5", 20);
		monthYearField.setMaximumSize(monthYearField.getPreferredSize());
		monthYearField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				monthYearField.setText("");
			}

			public void focusLost(FocusEvent e) {
				// nothing
			}
		});
		
		viewExpPanel.add(monthYearLabel);
		viewExpPanel.add(monthYearField);

		JButton viewMYBtn = new JButton("View");
		viewMYBtn.setFont(new Font("Verdana", Font.BOLD, 12));
		viewMYBtn.setForeground(Color.green);
		viewMYBtn.setBackground(Color.gray);

		viewMYBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				LOGGER.fine("Displaying" + typeCmbBox.getSelectedItem()+ 
						" expenses for " + monthYearField.getText());
				textArea.setText("");
				int month = Integer.parseInt(monthYearField.getText());
				text = Lookup.lookupMonthly((ExpensesType) typeCmbBox.getSelectedItem(), month-1);
				textArea.setText("");
				textArea.setForeground(Color.red);
				if (text != null) {
					for (String aux : text) {
						textArea.append(aux + "\n");
					}
				}
			}
		});

		viewExpPanel.add(viewMYBtn);

		JLabel yearLabel = new JLabel("Year");
		yearLabel.setFont(new Font("Verdana", Font.BOLD, 15));
		yearLabel.setForeground(Color.green);

		JTextField yearField = new JTextField("eg. 2016", 20);
		yearField.setMaximumSize(yearField.getPreferredSize());
		yearField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				yearField.setText("");
			}

			public void focusLost(FocusEvent e) {
				// nothing
			}
		});
		
		viewExpPanel.add(yearLabel);
		viewExpPanel.add(yearField);

		JButton viewYBtn = new JButton("View");
		viewYBtn.setFont(new Font("Verdana", Font.BOLD, 12));
		viewYBtn.setForeground(Color.green);
		viewYBtn.setBackground(Color.gray);
		viewExpPanel.add(viewYBtn);
		viewYBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				LOGGER.fine("Displaying" + typeCmbBox.getSelectedItem()+ 
						" expenses for " + monthYearField.getText());
				textArea.setText("");
				int year = Integer.parseInt(yearField.getText());
				text = Lookup.lookupYear((ExpensesType) typeCmbBox.getSelectedItem(), year);
				textArea.setText("");
				textArea.setForeground(Color.red);
				if (text != null) {
					for (String aux : text) {
						textArea.append(aux + "\n");
					}
				}
			}
		});

		viewExpPanel.add(scrollPane);
		return viewExpPanel;
	}

}
