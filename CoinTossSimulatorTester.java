// Name: Dunxuan Li (Annie)
// USC NetID: 6625999096
// CS 455 PA1
// Spring 2019

/**
 * CoinTossSimulatorTester Class
 * 
 * A test program to test CoinTossSimulator class independently from its use in the CoinSimViewer program.
 * It is expressly written to thoroughly test CoinTossSimulator class without any input from user.
 */

import java.util.Scanner;

// Test the CoinTossSimulator class
public class CoinTossSimulatorTester {

	public static void main(String[] args) {

		// Call the CoinTossSimulator to simulate the process
		CoinTossSimulator coinToss = new CoinTossSimulator();

		// Test case 1 - Test constructor
		System.out.println();
		System.out.println("After constructor: ");
		System.out.println("Number of trials [exp: 0]: " + coinToss.getNumTrials());
		System.out.println("Two-head tosses: " + coinToss.getTwoHeads());
		System.out.println("Two-tail tosses: " + coinToss.getTwoTails());
		System.out.println("One-head one-tail tosses: " + coinToss.getHeadTails());
		System.out.println("Tosses add up correctly? " + (coinToss.getTwoHeads() + coinToss.getTwoTails()
				+ coinToss.getHeadTails() == coinToss.getNumTrials()));

		// Test case 2 - Add 1 to the tester
		coinToss.run(1);
		System.out.println();
		System.out.println("After run(1) ");
		System.out.println("Number of trials [exp: 1]: " + coinToss.getNumTrials());
		System.out.println("Two-head tosses: " + coinToss.getTwoHeads());
		System.out.println("Two-tail tosses: " + coinToss.getTwoTails());
		System.out.println("One-head one-tail tosses: " + coinToss.getHeadTails());
		System.out.println("Tosses add up correctly? " + (coinToss.getTwoHeads() + coinToss.getTwoTails()
				+ coinToss.getHeadTails() == coinToss.getNumTrials()));

		// Test case 3 - Continue to add 10 to the tester
		coinToss.run(10);
		System.out.println();
		System.out.println("After run(10) ");
		System.out.println("Number of trials [exp: 11]: " + coinToss.getNumTrials());
		System.out.println("Two-head tosses: " + coinToss.getTwoHeads());
		System.out.println("Two-tail tosses: " + coinToss.getTwoTails());
		System.out.println("One-head one-tail tosses: " + coinToss.getHeadTails());
		System.out.println("Tosses add up correctly? " + (coinToss.getTwoHeads() + coinToss.getTwoTails()
				+ coinToss.getHeadTails() == coinToss.getNumTrials()));

		// Test case 4 - Continue to add 100 to the tester
		coinToss.run(100);
		System.out.println();
		System.out.println("After run(100) ");
		System.out.println("Number of trials [exp: 111]: " + coinToss.getNumTrials());
		System.out.println("Two-head tosses: " + coinToss.getTwoHeads());
		System.out.println("Two-tail tosses: " + coinToss.getTwoTails());
		System.out.println("One-head one-tail tosses: " + coinToss.getHeadTails());
		System.out.println("Tosses add up correctly? " + (coinToss.getTwoHeads() + coinToss.getTwoTails()
				+ coinToss.getHeadTails() == coinToss.getNumTrials()));

		// Test case 5 -- After Reset
		coinToss.reset();
		System.out.println();
		System.out.println("After reset: ");
		System.out.println("Number of trials [exp: 0]: " + coinToss.getNumTrials());
		System.out.println("Two-head tosses: " + coinToss.getTwoHeads());
		System.out.println("Two-tail tosses: " + coinToss.getTwoTails());
		System.out.println("One-head one-tail tosses: " + coinToss.getHeadTails());
		System.out.println("Tosses add up correctly? " + (coinToss.getTwoHeads() + coinToss.getTwoTails()
				+ coinToss.getHeadTails() == coinToss.getNumTrials()));

		// Test case 6 - Add 1000 to the tester
		coinToss.run(1000);
		System.out.println();
		System.out.println("After run(1000): ");
		System.out.println("Number of trials [exp: 1000]: " + coinToss.getNumTrials());
		System.out.println("Two-head tosses: " + coinToss.getTwoHeads());
		System.out.println("Two-tail tosses: " + coinToss.getTwoTails());
		System.out.println("One-head one-tail tosses: " + coinToss.getHeadTails());
		System.out.println("Tosses add up correctly? " + (coinToss.getTwoHeads() + coinToss.getTwoTails()
				+ coinToss.getHeadTails() == coinToss.getNumTrials()));

		// Test case 7 - Continue to add 1 to the tester
		coinToss.run(1);
		System.out.println();
		System.out.println("After run(1) ");
		System.out.println("Number of trials [exp: 1001]: " + coinToss.getNumTrials());
		System.out.println("Two-head tosses: " + coinToss.getTwoHeads());
		System.out.println("Two-tail tosses: " + coinToss.getTwoTails());
		System.out.println("One-head one-tail tosses: " + coinToss.getHeadTails());
		System.out.println("Tosses add up correctly? " + (coinToss.getTwoHeads() + coinToss.getTwoTails()
				+ coinToss.getHeadTails() == coinToss.getNumTrials()));

		// Test case 8 - Continue to add 10000 to the tester
		coinToss.run(10000);
		System.out.println();
		System.out.println("After run(10000) ");
		System.out.println("Number of trials [exp: 11001]: " + coinToss.getNumTrials());
		System.out.println("Two-head tosses: " + coinToss.getTwoHeads());
		System.out.println("Two-tail tosses: " + coinToss.getTwoTails());
		System.out.println("One-head one-tail tosses: " + coinToss.getHeadTails());
		System.out.println("Tosses add up correctly? " + (coinToss.getTwoHeads() + coinToss.getTwoTails()
				+ coinToss.getHeadTails() == coinToss.getNumTrials()));
	}

}
