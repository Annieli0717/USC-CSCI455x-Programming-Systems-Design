
// Name: Dunxuan Li (Annie)
// USC NetID: dunxuanl
// USC ID: 6625999096
// CSCI455 PA2
// Spring 2019

import java.util.ArrayList;
import java.util.Random;

/*
  class SolitaireBoard
  The board for Bulgarian Solitaire.  You can change what the total number of cards is for the game
  by changing NUM_FINAL_PILES, below.  Don't change CARD_TOTAL directly, because there are only some values
  for CARD_TOTAL that result in a game that terminates.
  (See comments below next to named constant declarations for more details on this.)
*/

public class SolitaireBoard {

	public static final int NUM_FINAL_PILES = 9;
	// number of piles in a final configuration
	// (note: if NUM_FINAL_PILES is 9, then CARD_TOTAL below will be 45)

	public static final int CARD_TOTAL = NUM_FINAL_PILES * (NUM_FINAL_PILES + 1) / 2;
	// bulgarian solitaire only terminates if CARD_TOTAL is a triangular number.
	// see: http://en.wikipedia.org/wiki/Bulgarian_solitaire for more details
	// the above formula is the closed form for 1 + 2 + 3 + . . . + NUM_FINAL_PILES

	// Note to students: you may not use an ArrayList -- see assgt description for
	// details.

	/**
	 * Representation invariant:
	 * 
	 * 1. Value of each pile should be >0 and <= CARD_TOTAL (45) 
	 * 2. Solitaire board array size must be >= 1 and <= CARD_TOTAL (45) 
	 * 3. Total value of cards must be = CARD_TOTAL (45)
	 * 4. 0 <= Number of piles (numPiles)<= CARD_TOTAL (45)
	 * 
	 */

	// <add instance variables here>
	private int[] piles;
	private int numPiles; // Keep track of number of piles in the partially filled array piles. 
						  // Count from 0 when array piles is empty

	/**
	 * Creates a solitaire board with the configuration specified in piles. 
	 * piles has the number of cards in the first pile, then the number of cards in the second pile, etc. 
	 * PRE: piles contains a sequence of positive numbers that sum to SolitaireBoard.CARD_TOTAL 
	 * PRE: the size of piles must be > 0 and <= 45
	 */
	public SolitaireBoard(ArrayList<Integer> piles) {

		this.piles = new int[CARD_TOTAL]; // Initialize Array piles with constant size 45
		
		// Read input from given ArrayList to Array
		for (int i = 0; i < piles.size(); i++) {
			this.piles[i] = piles.get(i);
		}

		numPiles = piles.size(); // Initialize number of piles based on the input

		assert isValidSolitaireBoard(); // sample assert statement (you will be adding more of these calls)
	}

	/**
	 * Creates a solitaire board with a random initial configuration.
	 */
	public SolitaireBoard() {
		
		Random rand = new Random(); // Initialize new random number generator
		
		piles = new int[CARD_TOTAL]; // Initialize Array piles with constant size CARD_TOTAL (45)

		numPiles = 0;
		int cardSum = 0;
		
		// Randomly generate piles to fill the array until the total card value reaches CARD_TOTAL (45)
		while (cardSum != CARD_TOTAL) {

			// Generate random numbers from the range [1, total value of card left]
			// We plus 1 here because the upper bound of nextInt is exclusive
			piles[numPiles] = rand.nextInt(CARD_TOTAL - cardSum) + 1;
			cardSum += piles[numPiles];
			numPiles++;
		}

		assert isValidSolitaireBoard();
	}

	/**
	 * Plays one round of Bulgarian solitaire. Updates the configuration according
	 * to the rules of Bulgarian solitaire: Takes one card from each pile, and puts
	 * them all together in a new pile. The old piles that are left will be in the
	 * same relative order as before, and the new pile will be at the end.
	 * 
	 * We use two pointer algorithm here to reduce the time complexity to O(n) and space complexity to O(1)
	 */
	public void playRound() {

		int nonZeroPointer = 0; // One pointer is for non-zero entries

		for (int i = 0; i < numPiles; i++) {
			piles[i] -= 1;

			// When an entry is not zero, move the nonZeroPointer by one 
			if (piles[i] != 0) {
				piles[nonZeroPointer] = piles[i];
				nonZeroPointer++;
			}
		}
		
		piles[nonZeroPointer] = numPiles; // Append the last pile at the end
		numPiles = nonZeroPointer + 1; // number of piles is total number of non-zero entries plus the last updated pile

		assert isValidSolitaireBoard();
	}

	/**
	 * Returns true iff the current board is at the end of the game. That is, there
	 * are NUM_FINAL_PILES piles that are of sizes 1, 2, 3, . . . , NUM_FINAL_PILES,
	 * in any order.
	 */

	public boolean isDone() {

		// Create a count array that counts the frequency of appearance of each number through 1-9
		int[] count = new int[NUM_FINAL_PILES];

		// Check if each number in 1-9 appears exactly once
		// If a number in 1-9 appears exactly once, countOneToNine will increase by one
		// If each number in 1-9 appears exactly once, countOneToNine will be nine
		int countOneToNine = 0;

		// Board is done only if the length of partially filled array is 9
		if (numPiles == NUM_FINAL_PILES) {
			for (int i = 0; i < numPiles; i++) {
				
				// In final round, each entry must be <= 9. 
				// Otherwise, it is not final round 
				if (piles[i] <= NUM_FINAL_PILES) {
					count[piles[i] - 1]++;
					
					// Check if the counted value appears exactly once
					if (count[piles[i] - 1] == 1) {
						countOneToNine++;
					}
				}
			}

			if (countOneToNine == NUM_FINAL_PILES) {
				return true;
			}

		}
		assert isValidSolitaireBoard();
		return false;

	}

	/**
	 * Returns current board configuration as a string with the format of a
	 * space-separated list of numbers with no leading or trailing spaces. The
	 * numbers represent the number of cards in each non-empty pile.
	 */
	public String configString() {
		String boardConfig = Integer.toString(piles[0]);

		for (int i = 1; i < numPiles; i++) {
			boardConfig += " " + Integer.toString(piles[i]);
		}
		
		assert isValidSolitaireBoard();
		return boardConfig;
	}

	/**
	 * Returns true iff the solitaire board data is in a valid state (See
	 * representation invariant comment for more details.)
	 */
	private boolean isValidSolitaireBoard() {
		boolean isValid = true;
		int cardSum = 0;

		// Check the length of partially filled array
		if (numPiles > CARD_TOTAL) {
			return !isValid;
		}

		for (int i = 0; i < numPiles; i++) {

			// Check value for each single card
			// No need to check if a single card value exceeds 45 since we will check total value of cards below
			// If a single card value exceeds 45, then total value must be greater than 45, or we have cards with negative value
			if (piles[i] <= 0) {
				return !isValid;
			}
			cardSum += piles[i];
		}

		// Check total value of cards
		if (cardSum > CARD_TOTAL) {
			return !isValid;
		}

		return isValid;
	}

	// <add any additional private methods here>

}
