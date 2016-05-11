package project.ui;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
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
		viewExpPanel.setLayout(new GridLayout(5, 3));
		viewExpPanel.setBackground(Color.lightGray);
		
		JLabel title = new JLabel("Expense");
		title.setFont(new Font("Verdana", Font.BOLD, 16));
		title.setForeground(Color.green);
		
		JLabel title2 = new JLabel(" preview");
		title2.setFont(new Font("Verdana", Font.BOLD, 16));
		title2.setForeground(Color.green);
		
		JLabel title3 = new JLabel("  (filtre expence)");
		title3.setFont(new Font("Verdana", Font.BOLD, 16));
		title3.setForeground(Color.green);
		
		viewExpPanel.add(title);
		viewExpPanel.add(title2);
		viewExpPanel.add(title3);
		
		JLabel typeLabel = new JLabel("Type");
		typeLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
		typeLabel.setForeground(Color.green);
		JComboBox<Enum> typeCmbBox = new JComboBox<>(ExpensesType.values());
//		for(ExpensesType exp : ExpensesType.values()) {
//			typeCmbBox.addItem(exp);
//		}
		
		JLabel title4 = new JLabel("  ");
		title4.setFont(new Font("Verdana", Font.BOLD, 16));
		title4.setForeground(Color.green);
		
		viewExpPanel.add(typeLabel);
        viewExpPanel.add(typeCmbBox);
		viewExpPanel.add(title4);
		
		JLabel dateLabel = new JLabel("Date");
		dateLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
		dateLabel.setForeground(Color.green);
		
		JTextField dateField = new JTextField("", 10);
		
//		dateField.addFocusListener(new FocusListener(){
//
//			@Override
//			public void focusGained(FocusEvent arg0) {
//				dateField.setText("");
//			}
//
//			@Override
//			public void focusLost(FocusEvent arg0) {
//				dateField.setText("DD-mm-YYYY");
//				
//			}});
		
		JButton viewDateBtn = new JButton("View");
		viewDateBtn.setFont(new Font("Verdana", Font.BOLD, 12));
		viewDateBtn.setForeground(Color.green);
		viewDateBtn.setBackground(Color.gray);
		
		viewExpPanel.add(dateLabel);
		viewExpPanel.add(dateField);
		viewExpPanel.add(viewDateBtn);
		
		JLabel monthYearLabel = new JLabel("Month/Year");
		monthYearLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
		monthYearLabel.setForeground(Color.green);
		
		JTextField monthYearField = new JTextField("", 7); 
//		monthYearField.addFocusListener(new FocusListener(){

//			@Override
//			public void focusGained(FocusEvent arg0) {
//				monthYearField.setText("");
//			}
//
//			@Override
//			public void focusLost(FocusEvent arg0) {
//				monthYearField.setText("mm-YYYY");		
//			}});
		

		//JTable expensesMonth = new JTable();
		JButton viewMYBtn = new JButton("View");
		viewMYBtn.setFont(new Font("Verdana", Font.BOLD, 12));
		viewMYBtn.setForeground(Color.green);
		viewMYBtn.setBackground(Color.gray);
		
		viewExpPanel.add(monthYearLabel);
		viewExpPanel.add(monthYearField);
		viewExpPanel.add(viewMYBtn);
//		viewMYBtn.addActionListener(new ActionListener(){
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				int month = Integer.parseInt(monthYearField.getText());
//				Lookup.lookupMonthly((ExpensesType)typeCmbBox.getSelectedItem(), month, expensesMonth);
//				
//			}});
//		JScrollPane scrollPane2 = new JScrollPane(expensesMonth);
//		expensesMonth.setFillsViewportHeight(true);
//		expensesMonth.add(scrollPane2);
		
		JLabel yearLabel = new JLabel("Year");
		yearLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
		yearLabel.setForeground(Color.green);
		
		JTextField yearField = new JTextField("", 7); //la parametru year
//		yearField.addFocusListener(new FocusListener(){
//
//			@Override
//			public void focusGained(FocusEvent arg0) {
//				yearField.setText("");
//			}
//
//			@Override
//			public void focusLost(FocusEvent arg0) {
//				yearField.setText("YYYY");			
//			}});
		
	//	JTable expensesYear = new JTable();		
		JButton viewYBtn = new JButton("View");
		viewYBtn.setFont(new Font("Verdana", Font.BOLD, 12));
		viewYBtn.setForeground(Color.green);
		viewYBtn.setBackground(Color.gray);
		
		viewExpPanel.add(yearLabel);
		viewExpPanel.add(yearField);
		viewExpPanel.add(viewYBtn);
		
//		viewYBtn.addActionListener(new ActionListener(){
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				int year = Integer.parseInt(yearField.getText());
//				Lookup.lookupYear((ExpensesType)typeCmbBox.getSelectedItem(), year, expensesYear);
//			}});
//		JScrollPane scrollPane3 = new JScrollPane(expensesYear);
//		expensesYear.setFillsViewportHeight(true);
//		expensesYear.add(scrollPane3);
//		
		
//		JTable expensesDate = new JTable();
//		viewDateBtn.addActionListener(new ActionListener(){
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				try {
//					Lookup.lookupDate((ExpensesType)typeCmbBox.getSelectedItem(), dateField.getText(), expensesDate);
//				} catch (ParseException e1) {
//					e1.printStackTrace();
//				}
//				
//			}});
//		JScrollPane scrollPane1 = new JScrollPane(expensesDate);
//		expensesDate.setFillsViewportHeight(true);
//		expensesDate.add(scrollPane1);
		
		//viewExpPanel.add(expensesDate);
		
	//	viewExpPanel.add(expensesMonth);
		
	//	viewExpPanel.add(expensesYear);
		
		return viewExpPanel;
	}
}
