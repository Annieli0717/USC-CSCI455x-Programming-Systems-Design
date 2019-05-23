// Name: Dunxuan Li (Annie)
// USC NetID: 6625999096
// CS 455 PA1
// Spring 2019

/**
 * CoinSimViewer Class
 * 
 * Prompts for the number of trials to simulate (a trial is two coin tosses) on the console.
 * Error checking that a positive value is entered.
 * Creates the JFrame containing the CoinSimComponent.
 * Display the bar plot of result.
 */

import javax.swing.JFrame;
import java.util.Scanner;

public class CoinSimViewer {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);

		int numTrials = 0;
		boolean validInput = false;

		// Error checking that a positive value is entered.
		while (!validInput) {
			System.out.print("Enter number of trials (>= 1): ");
			numTrials = in.nextInt();

			if (numTrials > 0) {
				validInput = true;
			}

			else {
				System.out.println("ERROR: Number entered must be greater than 0.");
			}
		}

		// Create the JFrame
		JFrame frame = new JFrame();

		frame.setSize(800, 500);
		frame.setTitle("CoinSim");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add component
		CoinSimComponent component = new CoinSimComponent(numTrials);
		frame.add(component);

		// Make the frame visible
		frame.setVisible(true);
	}
}
