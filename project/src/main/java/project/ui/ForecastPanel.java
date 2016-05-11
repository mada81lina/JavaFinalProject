package project.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import project.expenses.operation.Forecast;

public class ForecastPanel {
	public static JPanel ForecastPanel() {
		JPanel forecastPanel = new JPanel();
		forecastPanel.setLayout(new GridLayout(5, 3));
		forecastPanel.setBackground(Color.lightGray);

		JLabel title = new JLabel("Forecast");
		title.setFont(new Font("Verdana", Font.BOLD, 16));
		title.setForeground(Color.green);

		JLabel title2 = new JLabel("(future expence)");
		title2.setFont(new Font("Verdana", Font.BOLD, 16));
		title2.setForeground(Color.green);

		JLabel title3 = new JLabel("     ");
		title3.setFont(new Font("Verdana", Font.BOLD, 16));
		title3.setForeground(Color.green);

		forecastPanel.add(title);
		forecastPanel.add(title2);
		forecastPanel.add(title3);

		JLabel monthYearLabel = new JLabel("Month");
		monthYearLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
		monthYearLabel.setForeground(Color.green);

		JTextField monthYearField = new JTextField("");

		JButton viewMYBtn = new JButton("View");
		viewMYBtn.setFont(new Font("Verdana", Font.BOLD, 12));
		viewMYBtn.setForeground(Color.green);
		viewMYBtn.setBackground(Color.gray);

		forecastPanel.add(monthYearLabel);
		forecastPanel.add(monthYearField);
		forecastPanel.add(viewMYBtn);

		JLabel forecastLabel = new JLabel("", 10);
		forecastLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
		forecastLabel.setForeground(Color.green);
		JLabel forecastLabel2 = new JLabel("", 10);
		JLabel forecastLabel3 = new JLabel("", 10);

		forecastPanel.add(forecastLabel);
		forecastPanel.add(forecastLabel2);
		forecastPanel.add(forecastLabel3);

		viewMYBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int month = Integer.parseInt(monthYearField.getText());
				double forecast = Forecast.forecastMonth(month);
				forecastLabel.setText(forecast + "");
			}
		});

		JLabel yearLabel = new JLabel("Year");
		yearLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
		yearLabel.setForeground(Color.green);

		JTextField yearField = new JTextField("");

		JButton viewYearBtn = new JButton("View");
		viewYearBtn.setFont(new Font("Verdana", Font.BOLD, 12));
		viewYearBtn.setForeground(Color.green);
		viewYearBtn.setBackground(Color.gray);

		forecastPanel.add(yearLabel);
		forecastPanel.add(yearField);
		forecastPanel.add(viewYearBtn);

		JLabel forecastLabel4 = new JLabel("", 10);
		forecastLabel4.setFont(new Font("Verdana", Font.PLAIN, 12));
		forecastLabel4.setForeground(Color.green);
		JLabel forecastLabel5 = new JLabel("", 10);
		JLabel forecastLabel6 = new JLabel("", 10);

		forecastPanel.add(forecastLabel4);
		forecastPanel.add(forecastLabel5);
		forecastPanel.add(forecastLabel6);
		viewYearBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int year = Integer.parseInt(yearField.getText());
				double forecast = Forecast.forecastYear(year);
				forecastLabel4.setText(forecast + "");

			}
		});

		return forecastPanel;
	}
}
