// Name: Dunxuan Li (Annie)
// USC NetID: dunxuanl
// USC ID: 6625999096
// CS 455 PA4
// Spring 2019

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * WordFinder -- main class for the Scrabble program .
 * <p>
 * When given letters that could comprise a Scrabble rack,
 * creates a list of all legal words that can be formed from the letters on that rack.
 */

public class WordFinder {


    /**
     * main method is responsible for precessing the command-line argument,
     * and handling any error processing
     * PRE: The strings in the file are unique.
     *
     * @param args command-line argument: if exists, the first argument will be the name of the file to read from
     */
    public static void main(String[] args) {

        // If command line argument is left over, use default dictionary file: "sowpods.txt"
        String dictFile = "sowpods.txt";
        AnagramDictionary dictionary = null;
        try {
            // If there is a command line argument for a specific dictionary, take that as dictionary file
            if (args.length > 0) {
                dictFile = args[0];
            }
            dictionary = new AnagramDictionary(dictFile);
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: File not found.");
        }

        System.out.println("Type . to quit.");
        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.print("Rack? ");
            String target = in.nextLine();

            // If user has entered ".", exit the program
            if (".".equals(target)) {
                break;
            }

            Rack rack = new Rack(target); // Create a rack
            ArrayList<String> subsets = rack.getAllSubsets(); // Get all possible subsets of the rack
            ArrayList<String> anagrams = allAnagrams(subsets, dictionary); // Only consider words that can be found in dictionary

            System.out.println("We can make " + anagrams.size() + " words from \"" + target + "\"");
            if (anagrams.size() > 0) {
                System.out.println("All of the words with their scores (sorted by score): ");
                ScoreTable scoreTable = new ScoreTable(anagrams);
                scoreTable.printSortedTable(); // Print out the sorted score table
            }
        }
    }

    /**
     * Find all anagrams of a rack that are legal words in dictionary
     *
     * @param subsets    all possible subsets of a rack
     * @param dictionary dictionary used to search words from
     * @return words that can be found in the dictionary
     */
    private static ArrayList<String> allAnagrams(ArrayList<String> subsets, AnagramDictionary dictionary) {
        ArrayList<String> anagrams = new ArrayList<>();
        for (int i = 0; i < subsets.size(); i++) {
            if (dictionary.getAnagramsOf(subsets.get(i)).size() != 0) {
                anagrams.addAll(dictionary.getAnagramsOf(subsets.get(i)));
            }
        }
        return anagrams;
    }
}
