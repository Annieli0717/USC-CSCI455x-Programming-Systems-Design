
// Name: Dunxuan Li (Annie)
// USC NetID: 6625999096
// CS 455 PA1
// Spring 2019

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import java.awt.Color;

/**
 * 
 * CoinSimComponent Class
 * 
 * This component draws bar shapes for each output. Extends JComponent.
 * Overrides paintComponent to draw the bar graph, using Bar Class.
 */

public class CoinSimComponent extends JComponent {

	// 5% buffer space for bar height
	private static final double BUFFER_SPACE = 0.05;

	// Constant color for each bar
	private final Color TWO_HEADS_COLOR = Color.RED;
	private final Color HEAD_TAIL_COLOR = Color.GREEN;
	private final Color TWO_TAINS_COLOR = Color.BLUE;

	private int totalTrials;
	private int numTwoHeads;
	private int numTwoTails;
	private int numHeadTails;

	private int PercentOfTwoHeads;
	private int PercentOfTwoTails;
	private int PercentOfHeadTails;

	/**
	 * Runs the simulation with user input. Calculate probability for each output.
	 * 
	 * @param numTrials number of trials to for simulation; must be >= 1
	 */
	public CoinSimComponent(int numTrials) {

		CoinTossSimulator coinToss = new CoinTossSimulator();
		coinToss.run(numTrials);

		totalTrials = coinToss.getNumTrials();
		numTwoHeads = coinToss.getTwoHeads();
		numTwoTails = coinToss.getTwoTails();
		numHeadTails = coinToss.getHeadTails();
		// Use percentage to represent the probability of each output
		// Since both numTwoHeads and totalTrials are integers, we need a double type
		// number added to avoid round-off error
		PercentOfTwoHeads = (int) Math.round((numTwoHeads * 100.0) / totalTrials);
		PercentOfTwoTails = (int) Math.round((numTwoTails * 100.0) / totalTrials);
		PercentOfHeadTails = (int) Math.round((numHeadTails * 100.0) / totalTrials);
	}

	/**
	 * This component draws the bar graph, using Bar objects.
	 * 
	 * @param g graphics parameter.
	 */
	public void paintComponent(Graphics g) {

		// Recover Graphics2D
		Graphics2D g2 = (Graphics2D) g;

		int componentHeight = getHeight();
		int componentWidth = getWidth();
		// Add 5% buffer space for label so it will not be displayed at very bottom
		int labelBottom = componentHeight - (int) Math.round(componentHeight * BUFFER_SPACE);
		// Bar width is set to be 5% of the whole component width
		int barWidth = (int) Math.round(getWidth() * BUFFER_SPACE);

		// Calculate top left x coordinate for each bar

		// Divided by 4 since the first bar is at the 1/4 of the component width, and
		// moves one half of the bar width to reach the top left corner.
		int twoHeadsLeft = (componentWidth / 4) - (barWidth / 2);
		// Divided by 2 since the second car is at the center
		int headTailLeft = (componentWidth / 2) - (barWidth / 2); // Divides width in (2) equal parts and removes half
		// Divided by 3/4 since the second car is at the 3/4 of the component width bar
		// width
		int twoTailsLeft = (componentWidth * 3) / 4 - (barWidth / 2);

		// Set the scale to be the ratio of component height (removed 10% buffer space)
		// and total number of trails.
		double pixelsPerUnit = (double) (componentHeight - (int) Math.round(componentHeight * 2 * BUFFER_SPACE))
				/ totalTrials;

		Bar twoHeadsBar = new Bar(labelBottom, twoHeadsLeft, barWidth, numTwoHeads, pixelsPerUnit, TWO_HEADS_COLOR,
				"Two Heads: " + numTwoHeads + " (" + PercentOfTwoHeads + "%)");
		Bar headTailBar = new Bar(labelBottom, headTailLeft, barWidth, numHeadTails, pixelsPerUnit, HEAD_TAIL_COLOR,
				"A head and a Tail: " + numHeadTails + " (" + PercentOfHeadTails + "%)");
		Bar twoTailsBar = new Bar(labelBottom, twoTailsLeft, barWidth, numTwoTails, pixelsPerUnit, TWO_TAINS_COLOR,
				"Two Tails: " + numTwoTails + " (" + PercentOfTwoTails + "%)");

		twoHeadsBar.draw(g2);
		headTailBar.draw(g2);
		twoTailsBar.draw(g2);
	}

}
