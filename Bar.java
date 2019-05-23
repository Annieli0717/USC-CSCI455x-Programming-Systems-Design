// Name: Dunxuan Li (Annie)
// USC NetID: 6625999096
// CS 455 PA1
// Spring 2019

// we included the import statements for you
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

/**
 * Bar class 
 * A labeled bar that can serve as a single bar in a bar graph. The
 * text for the label is centered under the bar.
 * 
 * NOTE: we have provided the public interface for this class. Do not change the
 * public interface. You can add private instance variables, constants, and
 * private methods to the class. You will also be completing the implementation
 * of the methods given.
 * 
 */
public class Bar {

	private int labelBottom;
	private int barLeft;
	private int barWidth;
	private int barHeight;
	private double barScale;
	private Color barColor;
	private String barLabel;

	/**
	 * Creates a labeled bar. You give the height of the bar in application units
	 * (e.g., population of a particular state), and then a scale for how tall to
	 * display it on the screen (parameter scale).
	 * 
	 * @param bottom    location of the bottom of the label
	 * @param left      location of the left side of the bar
	 * @param width     width of the bar (in pixels)
	 * @param barHeight height of the bar in application units
	 * @param scale     how many pixels per application unit
	 * @param color     the color of the bar
	 * @param label     the label at the bottom of the bar
	 */
	public Bar(int bottom, int left, int width, int barHeight, double scale, Color color, String label) {
		labelBottom = bottom;
		barLeft = left;
		barWidth = width;
		this.barHeight = barHeight;
		barScale = scale;
		barColor = color;
		barLabel = label;
	}

	/**
	 * Draw the labeled bar.
	 * 
	 * @param g2 the graphics context
	 */
	public void draw(Graphics2D g2) {
		Font font = g2.getFont();
		FontRenderContext context = g2.getFontRenderContext();
		Rectangle2D labelBounds = font.getStringBounds(barLabel, context);
		// Get the width and height of label
		int widthOfLabel = (int) (Math.round(labelBounds.getWidth()));
		int heightOfLabel = (int) (Math.round(labelBounds.getHeight()));

		int rectWidth = barWidth;
		int rectHeight = (int) (this.barHeight * barScale);
		int rectX = barLeft;
		int rectY = labelBottom - rectHeight - heightOfLabel;

		// Draw a bar using Rectangle class
		Rectangle barRect = new Rectangle(rectX, rectY, rectWidth, rectHeight);
		g2.setColor(barColor);
		g2.fill(barRect);

		// Center label
		int labelX = barLeft + (barWidth / 2) - (widthOfLabel / 2);
		g2.setColor(Color.BLACK);
		g2.drawString(barLabel, labelX, labelBottom);
	}
}
