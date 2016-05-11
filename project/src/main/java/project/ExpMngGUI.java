package project;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;
import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

import project.ui.ExpensePanel;
import project.ui.ForecastPanel;
import project.ui.InsertBudgetLimitPanel;
import project.ui.StatisticsPanel;
import project.ui.ViewExpensePanel;

public class ExpMngGUI extends JFrame {
	/**
	 * logger for this class
	 */
	// public static final Logger LOGGER = Logger.getGlobal();
	public static Path file = Paths.get("Expences.txt");

	public ExpMngGUI() {
		super();
		setTitle("Expense app");
		setSize(1200, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new GridLayout(2, 3));

		// listeners that saves catalog on exit
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				try {
					Storage.save(file);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});

		createGUI();

		// LOGGER.info("ExpMngGUI GUI constructed");
	}

	private void createGUI() {
		JPanel addExpensePanel = ExpensePanel.addExpensePanel();
		add(addExpensePanel);
		JPanel statisticsPanel = StatisticsPanel.StatisticsPanel();
		add(statisticsPanel);
		JPanel viewExpPanel = ViewExpensePanel.ViewExpensePanel();
		add(viewExpPanel);
		JPanel insertBudgetLimitPanel = InsertBudgetLimitPanel.insertBudgetLimitPanel();
		add(insertBudgetLimitPanel);
		JPanel forecastPanel = ForecastPanel.ForecastPanel();
		add(forecastPanel);
	}
}
