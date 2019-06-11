// Name: Dunxuan Li (Annie)
// USC NetID: dunxuanl
// USC ID: 6625999096
// CS 455 PA4
// Spring 2019

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 * A dictionary of all anagram sets.
 * Note: the processing is case-sensitive; so if the dictionary has all lower
 * case words, you will likely want any string you test to have all lower case
 * letters too, and likewise if the dictionary words are all upper case.
 */

public class AnagramDictionary {

    // To get anagram of a word efficiently, we will simply compare a sorted version of the characters in a word
    // Thus, the sorted word will be the key in dictionary
    // All anagrams will be stored in the corresponding arrayList
    private Map<String, ArrayList<String>> dictionary;

    /**
     * Create an anagram dictionary from the list of words given in the file
     * indicated by fileName.
     * PRE: The strings in the file are unique.
     *
     * @param fileName the name of the file to read from
     * @throws FileNotFoundException if the file is not found
     */
    public AnagramDictionary(String fileName) throws FileNotFoundException {

        dictionary = new HashMap<String, ArrayList<String>>(); // Because order does not matter in this case, we use HashMap

        File file = new File(fileName);
        Scanner in = new Scanner(file);

        while (in.hasNextLine()) {
            String word = in.nextLine();

            // Figure out if anagram of given word already in the dictionary, we compare its sorted version
            String key = sortWord(word);
            if (dictionary.containsKey(key)) {
                dictionary.get(key).add(word); // If anagram of given word already exits, add new word to its anagram array
            } else {
                // If anagram of given word is not in the dictionary, create a new array list to store the word
                ArrayList<String> anagramArr = new ArrayList<String>();
                anagramArr.add(word);
                dictionary.put(key, anagramArr);
            }
        }
        in.close();
    }


    /**
     * Get all anagrams of the given string. This method is case-sensitive.
     * E.g. "CARE" and "race" would not be recognized as anagrams.
     *
     * @param s string to process
     * @return a list of the anagrams of s
     */
    public ArrayList<String> getAnagramsOf(String s) {
        String key = sortWord(s);

        // If dictionary has anagrams of s, return its anagrams; if not, return an empty arrayList
        if (dictionary.containsKey(key)) {
            return dictionary.get(key);
        } else {
            return new ArrayList<String>();
        }
    }


    /**
     * Sort the given word in alphabetical order
     * E.g. give word "back", we sort it as "abck"
     *
     * @param word unsorted word from file
     * @return new sorted word in alphabetical order of the given word
     */
    private String sortWord(String word) {

        char[] charWord = word.toCharArray(); // Convert the given word to char array
        Arrays.sort(charWord); // Arrays.sort(char chars[]) method will sort characters based on their ASCII value

        return new String(charWord); // Return new sorted string
    }
}
