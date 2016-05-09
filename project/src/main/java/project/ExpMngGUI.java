package project;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.logging.Logger;

import javax.swing.JFrame;

public class ExpMngGUI extends JFrame{
	
	//logger for ExpMngGUI
	public static final Logger LOGGER = Logger.getGlobal();
	
	public static void main(String[] args) {

	}
	
	public ExpMngGUI() {
		super();
		setTitle("Expense Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// listeners that save expense list on exit
				addWindowListener(new WindowAdapter() {

					@Override
					public void windowClosing(WindowEvent e) {
						try {
							Storage.save(ExpenceApp.file);
						} catch (IOException e1) {
							System.out.println("IOException: " + e1.getStackTrace());
							e1.printStackTrace();
						}
					}

				});
				
		setSize(900, 700);
		setVisible(true);
//		getContentPane().setLayout(new GridLayout(2, 2));
	}
}