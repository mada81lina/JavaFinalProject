package project.ui;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import project.ExpMngGUI;
import project.ExpensesType;
import project.expenses.operation.*;
public class ViewExpensePanel {
	public static JPanel ViewExpensePanel() {
		JPanel viewExpPanel = new JPanel();
		viewExpPanel.setBackground(Color.lightGray);
		
		JLabel title = new JLabel("Expense preview");
		title.setFont(new Font("Verdana", Font.BOLD, 16));
		title.setForeground(Color.green);
		
		JLabel typeLabel = new JLabel("Type");
		typeLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
		typeLabel.setForeground(Color.green);
		JComboBox<Enum> typeCmbBox = new JComboBox<>(ExpensesType.values());
		for(ExpensesType exp : ExpensesType.values()) {
			typeCmbBox.addItem(exp);
		}
		
		JLabel dateLabel = new JLabel("Date");
		dateLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
		dateLabel.setForeground(Color.green);
		JTextField dateField = new JTextField("", 10);
		dateField.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent arg0) {
				dateField.setText("");
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				dateField.setText("DD-mm-YYYY");
				
			}});
		
		JButton viewDateBtn = new JButton("View");
		viewDateBtn.setFont(new Font("Verdana", Font.BOLD, 12));
		viewDateBtn.setForeground(Color.green);
		viewDateBtn.setBackground(Color.gray);
		JTable expensesDate = new JTable();
		viewDateBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Lookup.lookupDate((ExpensesType)typeCmbBox.getSelectedItem(), dateField.getText(), expensesDate);
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				
			}});
		JScrollPane scrollPane1 = new JScrollPane(expensesDate);
		expensesDate.setFillsViewportHeight(true);
		expensesDate.add(scrollPane1);
		
		JLabel monthYearLabel = new JLabel("Month/Year");
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
		

		JTable expensesMonth = new JTable();
		JButton viewMYBtn = new JButton("View");
		viewMYBtn.setFont(new Font("Verdana", Font.BOLD, 12));
		viewMYBtn.setForeground(Color.green);
		viewMYBtn.setBackground(Color.gray);
		viewMYBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int month = Integer.parseInt(monthYearField.getText());
				Lookup.lookupMonthly((ExpensesType)typeCmbBox.getSelectedItem(), month, expensesMonth);
				
			}});
		JScrollPane scrollPane2 = new JScrollPane(expensesMonth);
		expensesMonth.setFillsViewportHeight(true);
		expensesMonth.add(scrollPane2);
		
		JLabel yearLabel = new JLabel("Year");
		yearLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
		yearLabel.setForeground(Color.green);
		JTextField yearField = new JTextField("", 7); //la parametru year
		yearField.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent arg0) {
				yearField.setText("");
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				yearField.setText("YYYY");			
			}});
		
		JTable expensesYear = new JTable();		
		JButton viewYBtn = new JButton("View");
		viewYBtn.setFont(new Font("Verdana", Font.BOLD, 12));
		viewYBtn.setForeground(Color.green);
		viewYBtn.setBackground(Color.gray);
		viewYBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int year = Integer.parseInt(yearField.getText());
				Lookup.lookupYear((ExpensesType)typeCmbBox.getSelectedItem(), year, expensesYear);
			}});
		JScrollPane scrollPane3 = new JScrollPane(expensesYear);
		expensesYear.setFillsViewportHeight(true);
		expensesYear.add(scrollPane3);
		
		viewExpPanel.add(title);
		viewExpPanel.add(typeLabel);
        viewExpPanel.add(typeCmbBox);
		viewExpPanel.add(dateLabel);
		viewExpPanel.add(dateField);
		viewExpPanel.add(viewDateBtn);
		viewExpPanel.add(expensesDate);
		viewExpPanel.add(monthYearLabel);
		viewExpPanel.add(monthYearField);
		viewExpPanel.add(viewMYBtn);
		viewExpPanel.add(expensesMonth);
		viewExpPanel.add(yearLabel);
		viewExpPanel.add(yearField);
		viewExpPanel.add(viewYBtn);
		viewExpPanel.add(expensesYear);
		
		return viewExpPanel;
	}
}
