// Name: Dunxuan Li (Annie)
// USC NetID: 6625999096
// CS 455 PA1
// Spring 2019

/**
 * class CoinTossSimulator
 * 
 * Simulates trials of repeatedly tossing two coins and allows the user to access the
 * cumulative results.
 * 
 * NOTE: we have provided the public interface for this class.  Do not change
 * the public interface.  You can add private instance variables, constants, 
 * and private methods to the class.  You will also be completing the 
 * implementation of the methods given. 
 * 
 * Invariant: getNumTrials() = getTwoHeads() + getTwoTails() + getHeadTails()
 * 
 */

import java.util.Random;

public class CoinTossSimulator {
	private static final int HEADS = 0;
	private static final int TAILS = 1;

	private int trials;
	private int numTwoHeads;
	private int numTwoTails;
	private int numHeadTails;

	/**
	 * Creates a coin toss simulator with no trials done yet.
	 */
	public CoinTossSimulator() {
		trials = 0;
		numTwoHeads = 0;
		numTwoTails = 0;
		numHeadTails = 0;
	}

	/**
	 * Runs the simulation for numTrials more trials. Multiple calls to this method
	 * without a reset() between them *add* these trials to the current simulation.
	 * 
	 * @param numTrials number of trials to for simulation; must be >= 1
	 */
	public void run(int numTrials) {
		Random randomToss = new Random();

		for (int i = 1; i <= numTrials; i++) {

			// Generate random number twice, one for each toss
			int toss1 = randomToss.nextInt(2);
			int toss2 = randomToss.nextInt(2);

			if (toss1 == HEADS && toss2 == HEADS) {
				numTwoHeads++;
			} else if (toss1 == TAILS && toss2 == TAILS) {
				numTwoTails++;
			} else {
				numHeadTails++;
			}
		}
		
		// Add number of trails to the total trail number
		trials += numTrials;
	}

	/**
	 * Get number of trials performed since last reset.
	 */
	public int getNumTrials() {

		return trials;
	}

	/**
	 * Get number of trials that came up two heads since last reset.
	 */
	public int getTwoHeads() {
		return numTwoHeads;
	}

	/**
	 * Get number of trials that came up two tails since last reset.
	 */
	public int getTwoTails() {
		return numTwoTails;
	}

	/**
	 * Get number of trials that came up one head and one tail since last reset.
	 */
	public int getHeadTails() {
		return numHeadTails;
	}

	/**
	 * Resets the simulation, so that subsequent runs start from 0 trials done.
	 */
	public void reset() {
		
		// Every instance variable is set to 0.
		trials = 0;
		numTwoHeads = 0;
		numTwoTails = 0;
		numHeadTails = 0;
	}

}
