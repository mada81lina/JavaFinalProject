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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import project.expenses.operation.Forecast;
/**
 * Class ForecastPanel display in GUI interface forecast from a specified month/year
 * @author Madalina&Maria
 *
 */
public class ForecastPanel {
	public static Box ForecastPanel() {
		Box forecastPanel = Box.createVerticalBox();
		forecastPanel.setBackground(Color.lightGray);

		JLabel title = new JLabel("Forecast    (predict future expenses)");
		title.setFont(new Font("Verdana", Font.BOLD, 16));
		title.setForeground(Color.green);

		forecastPanel.add(title);
		
		JLabel monthYearLabel = new JLabel("Month");
		monthYearLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
		monthYearLabel.setForeground(Color.blue);

		JTextField monthYearField = new JTextField("", 20);
		monthYearField.setMaximumSize(monthYearField.getPreferredSize());

		JButton viewMYBtn = new JButton("View");
		viewMYBtn.setFont(new Font("Verdana", Font.BOLD, 12));
		viewMYBtn.setForeground(Color.green);
		viewMYBtn.setBackground(Color.gray);
		
		forecastPanel.add(monthYearLabel);
		forecastPanel.add(monthYearField);
		forecastPanel.add(viewMYBtn);

		JLabel forecastLabel = new JLabel("", 10);
		forecastLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
		forecastLabel.setForeground(Color.red);

		forecastPanel.add(forecastLabel);
		
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
		yearLabel.setForeground(Color.blue);

		JTextField yearField = new JTextField("", 20);
		yearField.setMaximumSize(yearField.getPreferredSize());
		
		JButton viewYearBtn = new JButton("View");
		viewYearBtn.setFont(new Font("Verdana", Font.BOLD, 12));
		viewYearBtn.setForeground(Color.green);
		viewYearBtn.setBackground(Color.gray);

		forecastPanel.add(yearLabel);
		forecastPanel.add(yearField);
		forecastPanel.add(viewYearBtn);

		JLabel forecastLabel4 = new JLabel("", 10);
		forecastLabel4.setForeground(Color.red);
	
		forecastPanel.add(forecastLabel4);
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
