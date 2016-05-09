package project.ui;

import java.awt.Color;
import java.awt.Font;
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
		forecastPanel.setBackground(Color.lightGray);
		
		JLabel title = new JLabel("Forecast");
		title.setFont(new Font("Verdana", Font.BOLD, 16));
		title.setForeground(Color.green);
		
		JLabel monthYearLabel = new JLabel("Month");
		monthYearLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
		monthYearLabel.setForeground(Color.green);
		JTextField monthYearField = new JTextField("", 7); 
		monthYearField.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent arg0) {
				monthYearField.setText("");
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				monthYearField.setText("mm-YYYY");		
			}});
		
		JButton viewMYBtn = new JButton("View");
		viewMYBtn.setFont(new Font("Verdana", Font.BOLD, 12));
		viewMYBtn.setForeground(Color.green);
		viewMYBtn.setBackground(Color.gray);
		viewMYBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int month = Integer.parseInt(monthYearLabel.getText());
				Forecast.forecastMonth(month);
				
			}});
		
		JLabel yearLabel = new JLabel("Year");
		yearLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
		yearLabel.setForeground(Color.green);
		JTextField yearField = new JTextField("", 7); 
		yearField.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent arg0) {
				yearField.setText("");
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				yearField.setText("YYYY");			
			}});
		
		JButton viewYearBtn = new JButton("View");
		viewYearBtn.setFont(new Font("Verdana", Font.BOLD, 12));
		viewYearBtn.setForeground(Color.green);
		viewYearBtn.setBackground(Color.gray);
		viewYearBtn.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				Forecast.forecastYear();
				
			}});
		
		forecastPanel.add(title);
		forecastPanel.add(monthYearLabel);
		forecastPanel.add(monthYearField);
		forecastPanel.add(viewMYBtn);
		forecastPanel.add(yearLabel);
		forecastPanel.add(yearField);
		forecastPanel.add(viewYearBtn);
		
		return forecastPanel;
	}
}
