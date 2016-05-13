package project.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import project.expenses.operation.BudgetLimit;
import project.expenses.operation.Forecast;
/**
 * Class InsertBudgetLimitPanel display warning in GUI interface, 
 * when month budged is exceeded
 * @author Madalina&Maria
 *
 */
public class InsertBudgetLimitPanel {
	public static Box insertBudgetLimitPanel() {
		Box budgetLimitPanel = Box.createVerticalBox();
		budgetLimitPanel.setBackground(Color.lightGray);
		
		JLabel budgetLimitLabel = new JLabel("Budget limit");
		budgetLimitLabel.setFont(new Font("Verdana", Font.BOLD, 16));
		budgetLimitLabel.setForeground(Color.green);
		
		budgetLimitPanel.add(budgetLimitLabel);
		
		JTextField limit = new JTextField("", 20);
		limit.setMaximumSize(limit.getPreferredSize());
		
		JButton addBtn = new JButton("Add");
		addBtn.setForeground(Color.green);
		addBtn.setBackground(Color.gray);
		
		JLabel bugedtLimitLabel=new JLabel("",10);
		
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
