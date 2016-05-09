package project.ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InsertBudgetLimitPanel {
	public static JPanel insertBudgetLimitPanel() {
		JPanel budgetLimitPanel = new JPanel();
		budgetLimitPanel.setBackground(Color.lightGray);
		
		JLabel budgetLimitLabel = new JLabel("Budget limit");
		budgetLimitLabel.setFont(new Font("Verdana", Font.BOLD, 16));
		budgetLimitLabel.setForeground(Color.green);
	
		JTextField limit = new JTextField("", 10);
		
		JButton addBtn = new JButton("Add");
		addBtn.setForeground(Color.green);
		addBtn.setBackground(Color.gray);
		
		budgetLimitPanel.add(budgetLimitLabel);
		budgetLimitPanel.add(limit);
		budgetLimitPanel.add(addBtn);
		
		return budgetLimitPanel;
	}
}
