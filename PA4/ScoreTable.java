// Name: Dunxuan Li (Annie)
// USC NetID: dunxuanl
// USC ID: 6625999096
// CS 455 PA4
// Spring 2019

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.Comparator;
import java.util.Collections;

/**
 * A score table about Scrabble scores for scrabble letters and words
 * It has information about how much each word is worth
 */

public class ScoreTable {
    private final int[] SCORE_TABLE = {1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};
    private ArrayList<String> words;
    private Map<String, Integer> scoreTable;

    public ScoreTable(ArrayList<String> words) {
        this.words = words;
        scoreTable = scoreMap(words);
    }

    /**
     * Print out sorted words according to its score
     * For words have the same score, sort the words alphabeticallys
     */
    public void printSortedTable() {

        // Define our own comparator to compare scores
        Comparator<Map.Entry<String, Integer>> comparator = new Comparator<Map.Entry<String, Integer>>() {

            @Override
            public int compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2) {
                return entry2.getValue() - entry1.getValue();
            }
        };

        ArrayList<Map.Entry<String, Integer>> listOfEntry = new ArrayList<>(scoreTable.entrySet());
        Collections.sort(listOfEntry, comparator);
        for (Map.Entry<String, Integer> curr : listOfEntry) {
            System.out.println(curr.getValue() + ": " + curr.getKey());
        }
    }


    /**
     * A map which takes each word as a key, and corresponding score as value
     *
     * @param words keys that we want to add to the map
     * @return a complete score map of given array words
     */
    private Map<String, Integer> scoreMap(ArrayList<String> words) {
        Map<String, Integer> scoreTable = new TreeMap<>(); // Use TreeMap here so that words are sorted alphabetically

        for (int i = 0; i < words.size(); i++) {
            String current = words.get(i);
            scoreTable.put(current, getScore(current)); // an entry = word, corresponding score
        }
        return scoreTable;
    }

    /**
     * Get score of given string
     *
     * @param str string that we need to calculate score for
     * @return total score of the given string
     */
    private int getScore(String str) {

        str = str.toLowerCase(); // Both lower case and upper case get the same score
        int score = 0;
        for (int i = 0; i < str.length(); i++) {
            score += SCORE_TABLE[str.charAt(i) - 'a'];
        }
        return score;
    }


}


