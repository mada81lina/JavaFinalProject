package project.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.Box;
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
/**
 * Class StatisticsPanel display in GUI interface
 * biggest expense from a specified month or year
 * @author Madalina&Maria
 *
 */
public class StatisticsPanel {

	public static Box StatisticsPanel() {
		Box statisticsPanel = Box.createVerticalBox();
		statisticsPanel.setBackground(Color.lightGray);

		JLabel title = new JLabel("Statistics      (check biggest expense)");
		title.setFont(new Font("Verdana", Font.BOLD, 16));
		title.setForeground(Color.green);
		statisticsPanel.add(title);

		JLabel monthLabel = new JLabel("Month");
		monthLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
		monthLabel.setForeground(Color.green);

		JTextField monthYearField = new JTextField("", 20);
		monthYearField.setMaximumSize(monthYearField.getPreferredSize());
		monthLabel.setForeground(Color.blue);
		
		JButton viewMonthExpBtn = new JButton("View");
		viewMonthExpBtn.setFont(new Font("Verdana", Font.BOLD, 12));
		viewMonthExpBtn.setForeground(Color.green);
		viewMonthExpBtn.setBackground(Color.gray);

		statisticsPanel.add(monthLabel);
		statisticsPanel.add(monthYearField);
		statisticsPanel.add(viewMonthExpBtn);

		JLabel statisticsLabel = new JLabel("", 10);
		statisticsLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
		statisticsLabel.setForeground(Color.red);

		statisticsPanel.add(statisticsLabel);
		
		viewMonthExpBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int month = Integer.parseInt(monthYearField.getText());
				String[] bigMonth = BiggestExpence.biggestExpenceMonth(month).split("-");
				statisticsLabel.setText(bigMonth[0] + "     " + bigMonth[1] + "    " + bigMonth[2]);
			}
		});

		JLabel yearLabel = new JLabel("Year");
		yearLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
		yearLabel.setForeground(Color.blue);

		JTextField yearField = new JTextField("", 20); 
		yearField.setMaximumSize(yearField.getPreferredSize());
		
		JButton viewYearExpBtn = new JButton("View");
		viewYearExpBtn.setFont(new Font("Verdana", Font.BOLD, 12));
		viewYearExpBtn.setForeground(Color.green);
		viewYearExpBtn.setBackground(Color.gray);

		statisticsPanel.add(yearLabel);
		statisticsPanel.add(yearField);
		statisticsPanel.add(viewYearExpBtn);

		JLabel statisticsLabel4 = new JLabel("", 10);
		statisticsLabel4.setFont(new Font("Verdana", Font.PLAIN, 12));
		statisticsLabel4.setForeground(Color.red);

		statisticsPanel.add(statisticsLabel4);
		viewYearExpBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int year = Integer.parseInt(yearField.getText());
				String[] bigYear = BiggestExpence.biggestExpenceYear(year).split("-");
				statisticsLabel4.setText(bigYear[0] + "    " + bigYear[1] + "    " + bigYear[2]);

			}
		});

		return statisticsPanel;
	}
}
