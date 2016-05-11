package project.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
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
import project.Expense;
import project.ExpensesType;
import project.expenses.operation.BiggestExpence;

public class StatisticsPanel {

	public static JPanel StatisticsPanel() {
		JPanel statisticsPanel = new JPanel();
		statisticsPanel.setLayout(new GridLayout(5, 3));
		statisticsPanel.setBackground(Color.lightGray);

		JLabel title = new JLabel("Statistics");
		title.setFont(new Font("Verdana", Font.BOLD, 16));
		title.setForeground(Color.green);

		JLabel explanation = new JLabel("(check     ");
		explanation.setFont(new Font("Verdana", Font.PLAIN, 12));
		explanation.setForeground(Color.green);

		JLabel explanation2 = new JLabel("biggest expense)");
		explanation2.setFont(new Font("Verdana", Font.PLAIN, 12));
		explanation2.setForeground(Color.green);

		statisticsPanel.add(title);
		statisticsPanel.add(explanation);
		statisticsPanel.add(explanation2);

		JLabel monthLabel = new JLabel("Month");
		monthLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
		monthLabel.setForeground(Color.green);

		JTextField monthYearField = new JTextField("", 7);

		JButton viewMonthExpBtn = new JButton("View");
		viewMonthExpBtn.setFont(new Font("Verdana", Font.BOLD, 12));
		viewMonthExpBtn.setForeground(Color.green);
		viewMonthExpBtn.setBackground(Color.DARK_GRAY);

		statisticsPanel.add(monthLabel);
		statisticsPanel.add(monthYearField);
		statisticsPanel.add(viewMonthExpBtn);

		JLabel statisticsLabel = new JLabel("", 10);
		statisticsLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
		statisticsLabel.setForeground(Color.green);
		JLabel statisticsLabel2 = new JLabel("", 10);
		JLabel statisticsLabel3 = new JLabel("", 10);

		statisticsPanel.add(statisticsLabel);
		statisticsPanel.add(statisticsLabel2);
		statisticsPanel.add(statisticsLabel3);

		viewMonthExpBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int month = Integer.parseInt(monthYearField.getText());
				String[] bigMonth = BiggestExpence.biggestExpenceMonth(month).split("-");
				statisticsLabel.setText(bigMonth[0] + "");
				statisticsLabel2.setText(bigMonth[1] + "");
				statisticsLabel3.setText(bigMonth[2] + "");
			}
		});

		JLabel yearLabel = new JLabel("Year");
		yearLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
		yearLabel.setForeground(Color.green);

		JTextField yearField = new JTextField("", 7); // la parametru year

		JButton viewYearExpBtn = new JButton("View");
		viewYearExpBtn.setFont(new Font("Verdana", Font.BOLD, 12));
		viewYearExpBtn.setForeground(Color.green);
		viewYearExpBtn.setBackground(Color.gray);

		statisticsPanel.add(yearLabel);
		statisticsPanel.add(yearField);
		statisticsPanel.add(viewYearExpBtn);

		JLabel statisticsLabel4 = new JLabel("", 10);
		statisticsLabel4.setFont(new Font("Verdana", Font.PLAIN, 12));
		statisticsLabel4.setForeground(Color.green);
		JLabel statisticsLabel5 = new JLabel("", 10);
		JLabel statisticsLabel6 = new JLabel("", 10);

		statisticsPanel.add(statisticsLabel4);
		statisticsPanel.add(statisticsLabel5);
		statisticsPanel.add(statisticsLabel6);
		viewYearExpBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int year = Integer.parseInt(yearField.getText());
				String[] bigYear = BiggestExpence.biggestExpenceYear(year).split("-");
				statisticsLabel4.setText(bigYear[0] + "");
				statisticsLabel5.setText(bigYear[1] + "");
				statisticsLabel6.setText(bigYear[2] + "");

			}
		});

		return statisticsPanel;
	}
}
