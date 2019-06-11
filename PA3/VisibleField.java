// Name: Dunxuan Li (Annie)
// USC NetID: dunxuanl
// USC ID: 6625999096
// CS 455 PA3
// Spring 2019


/**
 * VisibleField class
 * This is the data that's being displayed at any one point in the game (i.e., visible field, because it's what the
 * user can see about the minefield), Client can call getStatus(row, col) for any square.
 * It actually has data about the whole current state of the game, including
 * the underlying mine field (getMineField()).  Other accessors related to game status: numMinesLeft(), isGameOver().
 * It also has mutators related to actions the player could do (resetGameDisplay(), cycleGuess(), uncover()),
 * and changes the game state accordingly.
 * <p>
 * It, along with the MineField (accessible in mineField instance variable), forms
 * the Model for the game application, whereas GameBoardPanel is the View and Controller, in the MVC design pattern.
 * It contains the MineField that it's partially displaying.  That MineField can be accessed (or modified) from
 * outside this class via the getMineField accessor.
 */
public class VisibleField {
    // ----------------------------------------------------------
    // The following public constants (plus numbers mentioned in comments below) are the possible states of one
    // location (a "square") in the visible field (all are values that can be returned by public method
    // getStatus(row, col)).

    // Covered states (all negative values):
    public static final int COVERED = -1;   // initial value of all squares
    public static final int MINE_GUESS = -2;
    public static final int QUESTION = -3;

    // Uncovered states (all non-negative values):
    private static final int UNCOVERED = 0;

    // values in the range [0,8] corresponds to number of mines adjacent to this square

    public static final int MINE = 9;      // this loc is a mine that hasn't been guessed already (end of losing game)
    public static final int INCORRECT_GUESS = 10;  // is displayed a specific way at the end of losing game
    public static final int EXPLODED_MINE = 11;   // the one you uncovered by mistake (that caused you to lose)
    // ----------------------------------------------------------

    // <put instance variables here>
    private static final int RUNNING = 12;
    private static final int WIN = 13;

    private MineField mineField;
    private int numRows;
    private int numCols;
    private int numMines;
    private int[][] state; // 2D array represents state of each square in game
    private int result; // Result of each game, which has 3 different situations: running / win / lose
    private int numMinesGuessed; // Total number of mines that user has guessed so far


    /**
     * Create a visible field that has the given underlying mineField.
     * The initial state will have all the mines covered up, no mines guessed, and the game
     * not over.
     *
     * @param mineField the minefield to use for for this VisibleField
     */
    public VisibleField(MineField mineField) {

        this.mineField = mineField;

        numRows = mineField.numRows();
        numCols = mineField.numCols();
        numMines = mineField.numMines();

        state = new int[numRows][numCols];
        // Initially all squares are covered
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                state[i][j] = COVERED;
            }
        }

        // Initially user has not made any decision & the program is running
        result = RUNNING;

        // Initially user has not guessed any mines
        numMinesGuessed = 0;
    }


    /**
     * Reset the object to its initial state (see constructor comments), using the same underlying
     * MineField.
     */
    public void resetGameDisplay() {

        // The user will play another game by clicking on the happy/sad face.
        // It goes back to a happy face at the start of the new game, the game board shows all squares as unopened
        // and shows the original number of mines in the display of how many left to guess (top left)

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                state[i][j] = COVERED;
            }
        }

        result = RUNNING;

        numMinesGuessed = 0;
    }


    /**
     * Returns a reference to the mineField that this VisibleField "covers"
     *
     * @return the minefield
     */
    public MineField getMineField() {

        return mineField;
    }


    /**
     * Returns the visible status of the square indicated.
     *
     * @param row row of the square
     * @param col col of the square
     * @return the status of the square at location (row, col).  See the public constants at the beginning of the class
     * for the possible values that may be returned, and their meanings.
     * PRE: getMineField().inRange(row, col)
     */
    public int getStatus(int row, int col) {

        assert getMineField().inRange(row, col);

        return state[row][col];
    }


    /**
     * Returns the the number of mines left to guess.  This has nothing to do with whether the mines guessed are correct
     * or not.  Just gives the user an indication of how many more mines the user might want to guess.  So the value can
     * be negative, if they have guessed more than the number of mines in the minefield.
     *
     * @return the number of mines left to guess.
     */
    public int numMinesLeft() {

        // Number of mines left = total number of mines - number of mines user has already guessed
        int numMinesLeft = numMines - numMinesGuessed;

        return numMinesLeft;
    }


    /**
     * Cycles through covered states for a square, updating number of guesses as necessary.  Call on a COVERED square
     * changes its status to MINE_GUESS; call on a MINE_GUESS square changes it to QUESTION;  call on a QUESTION square
     * changes it to COVERED again; call on an uncovered square has no effect.
     *
     * @param row row of the square
     * @param col col of the square
     *            PRE: getMineField().inRange(row, col)
     */
    public void cycleGuess(int row, int col) {

        assert getMineField().inRange(row, col);

        if (state[row][col] == COVERED) {
            state[row][col] = MINE_GUESS;
            // Guessing a mine will increase the total number of mines user has guessed by one whether the guess was correct or not
            numMinesGuessed++;
        } else if (state[row][col] == MINE_GUESS) {
            state[row][col] = QUESTION;
            // Questioning a mine will reduce the total number of mines user has guessed by one
            numMinesGuessed--;
        } else if (state[row][col] == QUESTION) {
            state[row][col] = COVERED;
        }
    }


    /**
     * Uncovers this square and returns false iff you uncover a mine here.
     * If the square wasn't a mine or adjacent to a mine it also uncovers all the squares in
     * the neighboring area that are also not next to any mines, possibly uncovering a large region.
     * Any mine-adjacent squares you reach will also be uncovered, and form
     * (possibly along with parts of the edge of the whole field) the boundary of this region.
     * Does not uncover, or keep searching through, squares that have the status MINE_GUESS.
     * Note: this action may cause the game to end: either in a win (opened all the non-mine squares)
     * or a loss (opened a mine).
     *
     * @param row of the square
     * @param col of the square
     * @return false   iff you uncover a mine at (row, col)
     * PRE: getMineField().inRange(row, col)
     */
    public boolean uncover(int row, int col) {

        // When a player opens a mine location, that mine explodes, and they lose the game
        if (mineField.hasMine(row, col)) {

            result = EXPLODED_MINE;
            // The cell where user exploded a mine there will display EXPLODED_MINE
            state[row][col] = EXPLODED_MINE;

            // Display the visible field after mine exploded
            displayMineExploded();
            return false;

        } else {

            // When uncover a cell, check three different cases to reveal
            reveal(row, col);
            // Check if a player has won
            displayWin();
            return true;
        }
    }


    /**
     * Returns whether the game is over.
     * (Note: This is not a mutator.)
     *
     * @return whether game over
     */
    public boolean isGameOver() {

        // When a player wins (opened all non-mine locations) / loses (exploded a mine), the game is over
        if (result == EXPLODED_MINE || result == WIN) return true;

        return false;

    }


    /**
     * Returns whether this square has been uncovered.  (i.e., is in any one of the uncovered states,
     * vs. any one of the covered states).
     *
     * @param row of the square
     * @param col of the square
     * @return whether the square is uncovered
     * PRE: getMineField().inRange(row, col)
     */
    public boolean isUncovered(int row, int col) {

        assert getMineField().inRange(row, col);

        if (state[row][col] != COVERED && state[row][col] != MINE_GUESS && state[row][col] != QUESTION) {
            return true;
        } else {
            return false;
        }
    }


    // <put private methods here>

    /**
     * Reveal the cell which user has clicked on & determine what to display
     * Recursively open cells with no adjacent mines until it gets to boundary of the field or cells that are adjacent to other mines
     *
     * @param row of the square
     * @param col of the square
     *            <p>
     *            PRE: getMineField().inRange(row, col)
     */
    private void reveal(int row, int col) {

        // In each round, the cell must be in the range
        if (mineField.inRange(row, col)) {

            // Case 1: If user has already flagged or questioned the cell, skip that cell
            if (state[row][col] == MINE_GUESS || state[row][col] == QUESTION) return;

            // Case 2: If there are no mines adjacent to an covered square, it displays no number
            // And then recursively do the same search for adjacent 8 cells
            if (mineField.numAdjacentMines(row, col) == 0 && state[row][col] == COVERED) {
                state[row][col] = UNCOVERED;

                // Recursively check for adjacent 8 cells
                reveal(row - 1, col - 1);
                reveal(row - 1, col);
                reveal(row - 1, col + 1);
                reveal(row, col - 1);
                reveal(row, col + 1);
                reveal(row + 1, col - 1);
                reveal(row + 1, col);
                reveal(row + 1, col + 1);
                reveal(row + 1, col + 1);
            }

            // Case 3: If a cell has adjacent mines, the cell will display the number of adjacent hidden mines
            else if (mineField.numAdjacentMines(row, col) > 0 && state[row][col] == COVERED) {

                state[row][col] = mineField.numAdjacentMines(row, col);
            }
        }
    }


    /**
     * Display the field after a mine has exploded
     */
    private void displayMineExploded() {

        // Change the display of field
        // Any previously made incorrect guesses are shown with an X
        // The correctly guessed mine locations are still shown as guesses (yellow)
        // And the other unopened mines are shown as "mines"
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                // Unopened mines will display as MINE
                if (mineField.hasMine(i, j) && state[i][j] != MINE_GUESS && state[i][j] != EXPLODED_MINE) {
                    state[i][j] = MINE;
                }
                // Any previously made incorrect guesses will display as INCORRECT_GUESS
                if (!mineField.hasMine(i, j) && state[i][j] == MINE_GUESS) {
                    state[i][j] = INCORRECT_GUESS;
                }
            }
        }
    }

    /**
     * Check if a player has won the game & display the field after player has won
     */
    private void displayWin() {

        // Check if user won the game
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                // User has to open ALL the non-mine locations without explode any mines
                if (!mineField.hasMine(i, j) && !isUncovered(i, j)) {

                    // Count for how many non-mine locations left to be opened (which are still covered)
                    // If it equals to zero (result will not increase), it means that all non-mine locations have been opened
                    result++;
                }
            }
        }

        // The win-condition is to open all the non-mine locations
        // Only when result = RUNNING, that is, result is not increased in previous step, user will win the game
        if (result + (WIN - RUNNING) == WIN) {
            result = WIN;

            // When a player successfully opens all of the non-mine locations
            // the field will automatically display changes to show where the other mines are (yellow squares)
            for (int i = 0; i < numRows; i++) {
                for (int j = 0; j < numCols; j++) {
                    if (mineField.hasMine(i, j) && state[i][j] != MINE_GUESS) {
                        state[i][j] = MINE_GUESS;
                    }
                }
            }
        }
        // Otherwise, user has not won the game yet. Game will continue.
        else {
            result = RUNNING;
        }
    }
}
