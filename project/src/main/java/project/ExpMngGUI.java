package project;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.logging.Logger;
import java.awt.*;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JFrame;

import project.ui.ExpensePanel;
import project.ui.ForecastPanel;
import project.ui.InsertBudgetLimitPanel;
import project.ui.StatisticsPanel;
import project.ui.ViewExpensePanel;

/**
 * Expenses App
 * 
 * @author Madalina&Maria
 *
 */
public class ExpMngGUI extends JFrame {
	/**
	 * logger for this class
	 */
	public static Path file = Paths.get("Expenses.txt");
	public static final Logger LOGGER = Logger.getGlobal();

	/**
	 * create window on window close, save expenses to file
	 * 
	 * @throws ParseException
	 */
	public ExpMngGUI() throws ParseException {
		super();
		setTitle("Expense app");
		setSize(900, 750);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new GridLayout(0, 2));

		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				try {
					Storage.save(file);
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}

		});

		createGUI();

		LOGGER.info("ExpMngGUI GUI constructed");
	}

	/**
	 * create GUI
	 * 
	 * @throws ParseException
	 */
	public void createGUI() throws ParseException {
		Box leftPanel = Box.createVerticalBox();
		Box rightPanel = Box.createVerticalBox();

		Box insertBudgetLimitPanel = InsertBudgetLimitPanel.insertBudgetLimitPanel();
		insertBudgetLimitPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		insertBudgetLimitPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		Box addExpensePanel = ExpensePanel.addExpensePanel();
		addExpensePanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		addExpensePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		Box statisticsPanel = StatisticsPanel.StatisticsPanel();
		statisticsPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		statisticsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		Box forecastPanel = ForecastPanel.ForecastPanel();
		forecastPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		forecastPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		Box viewExpPanel = ViewExpensePanel.ViewExpensePanel();
		viewExpPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		viewExpPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

		leftPanel.add(insertBudgetLimitPanel);
		leftPanel.add(addExpensePanel);
		leftPanel.add(statisticsPanel);
		leftPanel.add(forecastPanel);
		leftPanel.setBorder(BorderFactory.createLineBorder(Color.green, 3));

		rightPanel.add(viewExpPanel);
		rightPanel.setBorder(BorderFactory.createLineBorder(Color.green, 3));

		add(leftPanel);
		add(rightPanel);

	}
}
