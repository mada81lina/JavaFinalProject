package project.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import project.expenses.operation.BudgetLimit;
import project.expenses.operation.Forecast;

public class InsertBudgetLimitPanel {
	public static JPanel insertBudgetLimitPanel() {
		JPanel budgetLimitPanel = new JPanel();
		budgetLimitPanel.setLayout(new GridLayout(3, 2));
		budgetLimitPanel.setBackground(Color.lightGray);
		
		JLabel budgetLimitLabel = new JLabel("Budget");
		budgetLimitLabel.setFont(new Font("Verdana", Font.BOLD, 16));
		budgetLimitLabel.setForeground(Color.green);
		

		JLabel budgetLimitLabel2 = new JLabel("   (set buget limit)");
		budgetLimitLabel2.setFont(new Font("Verdana", Font.BOLD, 16));
		budgetLimitLabel2.setForeground(Color.green);
		
		budgetLimitPanel.add(budgetLimitLabel);
		budgetLimitPanel.add(budgetLimitLabel2);
		
		JLabel budgetLimitLabel3 = new JLabel("Value");
		budgetLimitLabel2.setFont(new Font("Verdana", Font.BOLD, 16));
		budgetLimitLabel2.setForeground(Color.green);
		
		JTextField limit = new JTextField("", 10);
		
		JButton addBtn = new JButton("Add");
		addBtn.setForeground(Color.green);
		addBtn.setBackground(Color.gray);
		
		JLabel bugedtLimitLabel=new JLabel("",10);
		
		budgetLimitPanel.add(budgetLimitLabel3);
		budgetLimitPanel.add(limit);
		budgetLimitPanel.add(addBtn);
		budgetLimitPanel.add(bugedtLimitLabel);
		
		addBtn.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				double limitValue = Double.parseDouble(limit.getText());
				boolean warning = BudgetLimit.bugetMonthLimit(limitValue);
				if (warning == true) {
					bugedtLimitLabel.setText("Warning - monthly budget exceeded");
				}			
			}});
		
		return budgetLimitPanel;
	}
}
