package project.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.logging.Logger;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import project.expenses.operation.Forecast;

/**
 * Class ForecastPanel display in GUI interface forecast from a specified
 * month/year
 * 
 * @author Madalina&Maria
 *
 */
public class ForecastPanel {
	/**
	 * logger for this class
	 */
	public static final Logger LOGGER = Logger.getGlobal();

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
				LOGGER.fine("Calculating forecast for " + monthYearField.getText());
				try {
					int month = Integer.parseInt(monthYearField.getText());
					double forecast = Forecast.forecastMonth(month - 1);
					forecastLabel.setText(forecast + "");
				} catch (IllegalArgumentException e1) {
					LOGGER.warning("Failed calculating forecast for " + monthYearField.getText() + e1);
				}
			}
		});

		JLabel yearLabel = new JLabel("Year");
		yearLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
		yearLabel.setForeground(Color.blue);

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
				LOGGER.fine("Calculating forecast for " + yearField.getText());
				try {
					int year = Integer.parseInt(yearField.getText());
					double forecast = Forecast.forecastYear(year);
					forecastLabel4.setText(forecast + "");
				} catch (IllegalArgumentException e1) {
					LOGGER.warning("Failed calculating forecast for " + yearField.getText() + e1);
				}

			}
		});

		return forecastPanel;
	}
}
