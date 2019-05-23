// Name: Dunxuan Li (Annie)
// USC NetID: dunxuanl
// USC ID: 6625999096
// CSCI455 PA2
// Spring 2019

import java.util.Scanner;
import java.util.ArrayList;

/**
 * BulgarianSolitaireSimulator
 * Simulate the game Bulgarian Solitaire. The game will continue run until it reaches the final/stable result.
 * It allows to have input either from user or generating random piles.
 * It also allows to run the game step by step, or run all steps at once.
 */

public class BulgarianSolitaireSimulator {

	public static void main(String[] args) {

		boolean singleStep = false;
		boolean userConfig = false;

		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("-u")) {
				userConfig = true;
			} else if (args[i].equals("-s")) {
				singleStep = true;
			}
		}

		// <add code here>
		Scanner scanner = new Scanner(System.in); // Create a scanner here (used later for other methods)
		SolitaireBoard pile = new SolitaireBoard();
		
		// If we have input from user, then use user's input
		if (userConfig == true) {
			pile = userConfigPile(scanner);
		}

		bulgarainSolitaireTester(pile, singleStep, scanner);
	}

	// <add private static methods here>
	/**
	 * Run Bulgarian Solitaire game from the starting point to stable result and print out every step
	 * @param pile: the pile used to run the game
	 * @param singleStep: equals to true if a user wants to run the game step by step
	 * @param scanner: the Scanner object used for system I/O
	 */
	private static void bulgarainSolitaireTester(SolitaireBoard pile, boolean singleStep, Scanner scanner) {
		System.out.println("Initial configuration: " + pile.configString());
		int counter = 1;
		
		// Run the game until it reaches final/stable result
		while (!pile.isDone()) {
			pile.playRound();
			System.out.println("[" + counter + "]" + " Current configuration: " + pile.configString());
			counter++;
		
			// If user chooses to run step by step, run each step by typing return
			if (singleStep) {
				System.out.print("<Type return to continue>");
				scanner.nextLine(); // Read the returned line
			}
		}
		System.out.println("Done!");
	}
	
	/**
	 * Create piles based on user's input 
	 * @param scanner: the Scanner object used for system I/O
	 * @return user defined piles
	 */
	private static SolitaireBoard userConfigPile(Scanner scanner) {
		ArrayList<Integer> inputPiles = readInput(scanner); // Get a valid input pile from user input
		SolitaireBoard pile = new SolitaireBoard(inputPiles);
		return pile;
	}

	/**
	 * Read piles from user and check if the piles are valid 
	 * @param scanner: the Scanner object used for system I/O
	 * @return an ArrayList stored with valid input piles
	 */
	private static ArrayList<Integer> readInput(Scanner scanner) {
		System.out.println("Number of total cards is " + SolitaireBoard.CARD_TOTAL);
		System.out.println("You will be entering the initial configuration of the cards (i.e., how many in each pile).");
		System.out.println("Please enter a space-separated list of positive integers followed by newline: ");

		// Read the whole line as input
		// Store each pile in an array list
		ArrayList<Integer> inputPiles = new ArrayList<Integer>();
		String line = scanner.nextLine();
		Scanner in = new Scanner(line);
		while (in.hasNextInt()) {
			inputPiles.add(in.nextInt());
		}

		// Prompt user to input piles until input is valid
		while (!isInputValid(inputPiles)) {
			System.out.println("ERROR: Each pile must have at least one card and the total number of cards must be "
					+ SolitaireBoard.CARD_TOTAL);
			System.out.println("Please enter a space-separated list of positive integers followed by newline: ");

			if (scanner.hasNextLine()) {
				inputPiles = new ArrayList<Integer>();
				line = scanner.nextLine();
				in = new Scanner(line);
				while (in.hasNextInt()) {
					inputPiles.add(in.nextInt());
				}
			}
		}
		return inputPiles;
	}

	/**
	 * Check if the user input piles are valid 
	 * @param inputPiles: the input piles that needs to be checked
	 * @return true if the input is valid; false if the input is not valid
	 */
	private static boolean isInputValid(ArrayList<Integer> inputPiles) {
		boolean isValid = true;
		int numPiles = inputPiles.size();
		int cardSum = 0;

		// Check the length of partially filled array
		if (numPiles > SolitaireBoard.CARD_TOTAL) {
			return !isValid;
		}

		for (int i = 0; i < numPiles; i++) {

			// Check value for each single card
			// No need to check if value > 45
			if (inputPiles.get(i) <= 0) {
				return !isValid;
			}
			cardSum += inputPiles.get(i);
		}

		// Check total value of cards
		if (cardSum != SolitaireBoard.CARD_TOTAL) {
			return !isValid;
		}
		return isValid;
	}
}
