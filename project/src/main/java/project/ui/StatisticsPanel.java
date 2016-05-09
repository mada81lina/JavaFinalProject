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
import project.expenses.operation.BiggestExpence;

public class StatisticsPanel {

	public static JPanel StatisticsPanel() {
		JPanel statisticsPanel = new JPanel();
		statisticsPanel.setBackground(Color.lightGray);

		JLabel title = new JLabel("Statistics");
		title.setFont(new Font("Verdana", Font.BOLD, 16));
		title.setForeground(Color.green);

		JLabel explanation = new JLabel("(check biggest expense)");
		explanation.setFont(new Font("Verdana", Font.PLAIN, 12));
		explanation.setForeground(Color.green);

		JLabel monthLabel = new JLabel("Month");
		monthLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
		monthLabel.setForeground(Color.green);
		JTextField monthYearField = new JTextField("", 7);
		monthYearField.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				monthYearField.setText("");
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				monthYearField.setText("mm-YYYY");
			}
		});

		JButton viewMonthExpBtn = new JButton("View");
		viewMonthExpBtn.setFont(new Font("Verdana", Font.BOLD, 12));
		viewMonthExpBtn.setForeground(Color.green);
		viewMonthExpBtn.setBackground(Color.DARK_GRAY);
		viewMonthExpBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int month = Integer.parseInt(monthYearField.getText());
				BiggestExpence.biggestExpenceMonth(month);

			}
		});

		JLabel yearLabel = new JLabel("Year");
		yearLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
		yearLabel.setForeground(Color.green);
		JTextField yearField = new JTextField("", 7); // la parametru year
		yearField.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				yearField.setText("");
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				yearField.setText("YYYY");
			}
		});

		JButton viewYearExpBtn = new JButton("View");
		viewYearExpBtn.setFont(new Font("Verdana", Font.BOLD, 12));
		viewYearExpBtn.setForeground(Color.green);
		viewYearExpBtn.setBackground(Color.gray);
		viewYearExpBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int year = Integer.parseInt(yearField.getText());
				BiggestExpence.biggestExpenceMonth(year);
			}
		});

		statisticsPanel.add(title);
		statisticsPanel.add(explanation);
		statisticsPanel.add(monthLabel);
		statisticsPanel.add(monthYearField);
		statisticsPanel.add(viewMonthExpBtn);
		statisticsPanel.add(yearLabel);
		statisticsPanel.add(yearField);
		statisticsPanel.add(viewYearExpBtn);

		return statisticsPanel;
	}
}
