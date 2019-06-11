// Name: Dunxuan Li (Annie)
// USC NetID: dunxuanl
// USC ID: 6625999096
// CS 455 PA3
// Spring 2019

import java.util.Random;

/**
 * MineField
 * class with locations of mines for a game.
 * This class is mutable, because we sometimes need to change it once it's created.
 * mutators: populateMineField, resetEmpty
 * includes convenience method to tell the number of mines adjacent to a location.
 */
public class MineField {

    // <put instance variables here>
    private boolean[][] mineData; // 2D array represents location of mines
    private int numRows;
    private int numCols; // Size of game board
    private int numMines; // Total number of mines


    /**
     * Create a minefield with same dimensions as the given array, and populate it with the mines in the array
     * such that if mineData[row][col] is true, then hasMine(row,col) will be true and vice versa.  numMines() for
     * this minefield will corresponds to the number of 'true' values in mineData.
     *
     * @param mineData the data for the mines; must have at least one row and one col.
     */
    public MineField(boolean[][] mineData) {

        // mineData must have at least one row and one col
        assert mineData.length >= 1 && mineData[0].length >= 1;

        this.mineData = new boolean[mineData.length][mineData[0].length];
        // Create a defensive copy of the given array
        for (int i = 0; i < mineData.length; i++) {
            for (int j = 0; j < mineData[0].length; j++) {
                this.mineData[i][j] = mineData[i][j];
            }
        }

        // Number of rows in minefield = Number of rows in 2D array
        this.numRows = this.mineData.length;

        // Number of columns in minefield = Number of columns in 2D array
        this.numCols = this.mineData[0].length;

        // Count number of mines in minefield
        numMines = 0;
        for (int i = 0; i < this.mineData.length; i++) {
            for (int j = 0; j < this.mineData[0].length; j++) {
                if (mineData[i][j] == true) {
                    numMines++;
                }
            }
        }
    }


    /**
     * Create an empty minefield (i.e. no mines anywhere), that may later have numMines mines (once
     * populateMineField is called on this object).  Until populateMineField is called on such a MineField,
     * numMines() will not correspond to the number of mines currently in the MineField.
     *
     * @param numRows  number of rows this minefield will have, must be positive
     * @param numCols  number of columns this minefield will have, must be positive
     * @param numMines number of mines this minefield will have,  once we populate it.
     *                 PRE: numRows > 0 and numCols > 0 and 0 <= numMines < (1/3 of total number of field locations).
     */
    public MineField(int numRows, int numCols, int numMines) {

        assert numRows > 0 && numCols > 0;
        this.numRows = numRows;
        this.numCols = numCols;

        int limit = numRows * numCols;
        assert 0 <= numMines && numMines < limit / 3.0;
        this.numMines = numMines;

        // Create an empty minefield (no mines anywhere)
        mineData = new boolean[this.numRows][this.numCols];
    }


    /**
     * Removes any current mines on the minefield, and puts numMines() mines in random locations on the minefield,
     * ensuring that no mine is placed at (row, col).
     *
     * @param row the row of the location to avoid placing a mine
     * @param col the column of the location to avoid placing a mine
     *            PRE: inRange(row, col)
     */
    public void populateMineField(int row, int col) {

        assert inRange(row, col);

        // Create a new field: default value = all false's
        mineData = new boolean[numRows][numCols];

        //  On each game played, the mine locations are chosen at random, and will be different for each game.
        //  For the first game, and any subsequent ones for a particular run of the program, the game guarantees that the first square opened will not be a mine.
        //  That means that the program can't choose the mine locations until the user opens the first square.
        Random rand = new Random();

        int numGeneratedMine = 0;
        // Generate given number of mines
        while (numGeneratedMine < numMines) {
            int randRow = rand.nextInt(numRows);
            int randCol = rand.nextInt(numCols);

            // If the generated position has a mine already OR is at (row, col) => loop again to generate a new random position
            // Otherwise => put a new mine there
            if (hasMine(randRow, randCol) || (randRow == row && randCol == col)) {
                continue;
            } else {
                mineData[randRow][randCol] = true;
                numGeneratedMine++;
            }
        }
    }


    /**
     * Reset the minefield to all empty squares.  This does not affect numMines(), numRows() or numCols()
     * Thus, after this call, the actual number of mines in the minefield does not match numMines().
     * Note: This is the state the minefield is in at the beginning of a game.
     */
    public void resetEmpty() {

        // Put the minefield into its starting state
        // Create a new minefield w/ default value of all false's
        mineData = new boolean[numRows][numCols];
    }


    /**
     * Returns the number of mines adjacent to the specified mine location (not counting a possible
     * mine at (row, col) itself).
     * Diagonals are also considered adjacent, so the return value will be in the range [0,8]
     *
     * @param row row of the location to check
     * @param col column of the location to check
     * @return the number of mines adjacent to the square at (row, col)
     * PRE: inRange(row, col)
     */
    public int numAdjacentMines(int row, int col) {

        assert inRange(row, col);

        int numAdjMines = 0;

        // Need to check for 3 different rows: row - 1, row, row + 1
        // Plus 3 different cols: col - 1, col, col + 1
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {

                // If it is at position (row, col) => do not count it
                // It it is outside the field => do not count it
                if (i == row && j == col || inRange(i, j) == false) {
                    continue;
                }
                if (hasMine(i, j)) {
                    numAdjMines++;
                }
            }
        }
        return numAdjMines;
    }


    /**
     * Returns true iff (row,col) is a valid field location.  Row numbers and column numbers
     * start from 0.
     *
     * @param row row of the location to consider
     * @param col column of the location to consider
     * @return whether (row, col) is a valid field location
     */
    public boolean inRange(int row, int col) {

        // Only if row number and column number are in the given range, it is valid
        // The range is in between [0, size - 1]
        if ((0 <= row && row < numRows) && (0 <= col && col < numCols)) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * Returns the number of rows in the field.
     *
     * @return number of rows in the field
     */
    public int numRows() {

        return numRows;
    }


    /**
     * Returns the number of columns in the field.
     *
     * @return number of columns in the field
     */
    public int numCols() {

        return numCols;
    }


    /**
     * Returns whether there is a mine in this square
     *
     * @param row row of the location to check
     * @param col column of the location to check
     * @return whether there is a mine in this square
     * PRE: inRange(row, col)
     */
    public boolean hasMine(int row, int col) {

        assert inRange(row, col);

        if (mineData[row][col] == true) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * Returns the number of mines you can have in this minefield.  For mines created with the 3-arg constructor,
     * some of the time this value does not match the actual number of mines currently on the field.  See doc for that
     * constructor, resetEmpty, and populateMineField for more details.
     *
     * @return
     */
    public int numMines() {

        return numMines;
    }


    // <put private methods here>

    /**
     * Returns four different tests for the whole class
     */
    // Returns the string representation of the object.
    public String toString() {

        String result = "";

        // 1. Draw the given field
        drawField();

        // 2. Draw a matrix of number of adjacent mines
        System.out.println(" ----- Return Adjacent Mines -----");
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                System.out.print(numAdjacentMines(i, j) + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();

        // 3. Populate mine field
        System.out.println(" ----- POPULATE MINE FIELD (1st) -----");
        populateMineField(numRows, numCols);
        drawField();

        System.out.println(" ----- POPULATE MINE FIELD (2nd) -----");
        populateMineField(numRows, numCols);
        drawField();

        // 4. Empty mine field
        System.out.println(" ----- EMPTY THE WHOLE FIELD: resetEmpty() is called -----");
        resetEmpty();
        drawField();

        System.out.println(" ----- Return Adjacent Mines -----");
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                System.out.print(numAdjacentMines(i, j) + " ");
            }
            System.out.println();
        }

        return result;
    }

    /**
     * Returns the string representation of the minefield.
     * Mine is represented by *, and empty field is represented by -
     */
    private void drawField() {
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (hasMine(i, j)) {
                    System.out.print("*");
                } else {
                    System.out.print("-");
                }
            }
            System.out.println();
        }
        System.out.println("The total number of mines is " + numMines());
        System.out.println("The total number of rows is " + numRows());
        System.out.println("The total number of columns is " + numCols());
        System.out.println();
        System.out.println();
    }


}

