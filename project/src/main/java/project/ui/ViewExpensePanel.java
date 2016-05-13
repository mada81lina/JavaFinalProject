package project.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import project.ExpMngGUI;
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
		textArea.append("Name   Value   Type   Date" + "\n");
		if (text != null) {
			for (String aux : text) {
				textArea.append(aux + "\n");
			}
		}

		JLabel title = new JLabel("Expense preview    (filter expenses)");
		title.setFont(new Font("Verdana", Font.BOLD, 16));
		title.setForeground(Color.green);

		viewExpPanel.add(title);

		JLabel typeLabel = new JLabel("Type");
		typeLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
		typeLabel.setForeground(Color.blue);
		JPanel cbPanel = new JPanel();
		JComboBox<ExpensesType> typeCmbBox = new JComboBox<>(ExpensesType.values());
		typeCmbBox.setAlignmentX(Component.LEFT_ALIGNMENT);
		cbPanel.add(typeCmbBox);
		cbPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		cbPanel.setBorder(BorderFactory.createLineBorder(Color.green, 3));

		viewExpPanel.add(typeLabel);
		viewExpPanel.add(cbPanel);

		JLabel dateLabel = new JLabel("Date");
		dateLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
		dateLabel.setForeground(Color.blue);

		JTextField dateField = new JTextField("", 20);
		dateField.setMaximumSize(dateField.getPreferredSize());

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
				textArea.setText("");
				textArea.append("Name       Value      Type     Date" + "\n");
				String date = dateField.getText();
				text = Lookup.lookupDate((ExpensesType) typeCmbBox.getSelectedItem(), date);
				textArea.setText("");
				textArea.append("Name   Value   Type   Date" + "\n");
				if (text != null) {
					for (String aux : text) {
						textArea.append(aux + "\n");
					}
				}
			}
		});

		JLabel monthYearLabel = new JLabel("Month/Year");
		monthYearLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
		monthYearLabel.setForeground(Color.blue);

		JTextField monthYearField = new JTextField("", 20);
		monthYearField.setMaximumSize(monthYearField.getPreferredSize());

		viewExpPanel.add(monthYearLabel);
		viewExpPanel.add(monthYearField);

		JButton viewMYBtn = new JButton("View");
		viewMYBtn.setFont(new Font("Verdana", Font.BOLD, 12));
		viewMYBtn.setForeground(Color.green);
		viewMYBtn.setBackground(Color.gray);

		viewMYBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
				textArea.append("Name Value Type Date" + "\n");
				int month = Integer.parseInt(monthYearField.getText());
				text = Lookup.lookupMonthly((ExpensesType) typeCmbBox.getSelectedItem(), month);
				textArea.setText("");
				textArea.append("Name   Value   Type   Date" + "\n");
				if (text != null) {
					for (String aux : text) {
						textArea.append(aux + "\n");
					}
				}
			}
		});

		viewExpPanel.add(viewMYBtn);

		JLabel yearLabel = new JLabel("Year");
		yearLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
		yearLabel.setForeground(Color.blue);

		JTextField yearField = new JTextField("", 20);
		yearField.setMaximumSize(yearField.getPreferredSize());

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
				textArea.setText("");
				textArea.append("Name Value Type Date" + "\n");
				int year = Integer.parseInt(yearField.getText());
				text = Lookup.lookupYear((ExpensesType) typeCmbBox.getSelectedItem(), year);
				textArea.setText("");
				textArea.append("Name   Value   Type   Date" + "\n");
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
