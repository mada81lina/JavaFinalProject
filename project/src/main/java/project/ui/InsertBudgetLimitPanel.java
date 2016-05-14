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

import project.expenses.operation.BudgetLimit;

/**
 * Class InsertBudgetLimitPanel display warning in GUI interface, 
 * when month budged is exceeded
 * @author Madalina&Maria
 *
 */
public class InsertBudgetLimitPanel {
	/**
	 * logger for this class
	 */
	public static final Logger LOGGER = Logger.getGlobal();
	public static Box insertBudgetLimitPanel() {
		Box budgetLimitPanel = Box.createVerticalBox();
		budgetLimitPanel.setBackground(Color.lightGray);
		
		JLabel budgetLimitLabel = new JLabel("Budget limit/month");
		budgetLimitLabel.setFont(new Font("Verdana", Font.BOLD, 16));
		budgetLimitLabel.setForeground(Color.gray);		
		budgetLimitPanel.add(budgetLimitLabel);
		
		JTextField limit = new JTextField("eg. 2000", 20);
		limit.setMaximumSize(limit.getPreferredSize());		
		limit.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				limit.setText("");
			}

			public void focusLost(FocusEvent e) {
				// nothing
			}
		});
		
		JButton addBtn = new JButton("Add");
		addBtn.setForeground(Color.green);
		addBtn.setBackground(Color.gray);
		
		JLabel bugedtLimitLabel=new JLabel("",10);
		bugedtLimitLabel.setFont(new Font("Verdana", Font.ROMAN_BASELINE, 12));
		bugedtLimitLabel.setForeground(Color.red);
		budgetLimitPanel.add(limit);
		budgetLimitPanel.add(addBtn);
		budgetLimitPanel.add(bugedtLimitLabel);
		
		addBtn.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				LOGGER.fine("Inserting " + limit.getText() + " as budget limit.");
				try {
					double limitValue = Double.parseDouble(limit.getText());
					boolean warning = BudgetLimit.bugetMonthLimit(limitValue);
					if (warning == true) {
						bugedtLimitLabel.setText("WARNING - monthly budget exceeded");
					}
				} catch (IllegalArgumentException e1) {
					LOGGER.warning("Failed to insert budget limit");
				}	
			}});
		
		return budgetLimitPanel;
	}
}
