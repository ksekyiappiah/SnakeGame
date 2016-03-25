import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Queue;
**
 * SpampedeBrain - The "View" in MVC
 *
 */
public class SpampedeDisplay extends SpampedeData {

	private SpampedeData theData;
	private Graphics theScreen;
	private int width;
	private int height;
	public static Image imageSpam;

	// Constructor
	public SpampedeDisplay(SpampedeData theMazeInput, Graphics theScreenInput,
			int widthInput, int heightInput) {
		this.theScreen = theScreenInput;
		this.theData = theMazeInput;
		this.height = heightInput;
		this.width = widthInput;
	}

	/************************************
	 * Displaying the board
	 ************************************/
	// Draw the visuals other than the buttons!
	void updateGraphics() {
		this.clear(); // Do not remove! Required for graphics!
		this.displayTitle();

		// Draw the board
		// TODO: Add your code here :) 
		
		int height = Preferences.NUM_CELLS_TALL;
		int width = Preferences.NUM_CELLS_WIDE;
		for (int row = 0; row < height; row++) {
			for (int column = 0; column < width; column++) {
				
				SpampedeData cell = this.theData;
				Color color = cell.getCellColor(row,column);
				drawSquare(column*Preferences.CELL_SIZE,row*Preferences.CELL_SIZE, color);
				
			}
		}
		
		
		// The method drawSquare (below) will likely be helpful :) 
		
		// Example of displaying an image
		if (SpampedeDisplay.imageSpam != null) {
			this.theScreen.drawImage(SpampedeDisplay.imageSpam, 500, 100, null);
		}
		if (this.theData.getGameOver()) {
			this.displayGameOver();
		}

	}

	// Drawing helper method
	public void drawSquare(int x, int y, Color cellColor) {
		int i = x+Preferences.NUM_CELL_ADJUST;
		int j = y+Preferences.NUM_CELL_ADJUST;
		this.theScreen.setColor(cellColor);
		this.theScreen.fillRect(i, j, Preferences.CELL_SIZE,
				Preferences.CELL_SIZE);
		
		
	}

	// Do not modify. Draws the background.
	void clear() {
		this.theScreen.setColor(Preferences.COLOR_BACKGROUND);
		this.theScreen.fillRect(0, 0, this.width, this.height);
		this.theScreen.setColor(Preferences.TITLE_COLOR);
		this.theScreen.drawRect(0, 0, this.width - 1,
				Preferences.GAMEBOARDHEIGHT - 1);
	}

	/************************************
	 * Text Display
	 ************************************/
	// title display
	public void displayTitle() {
		this.theScreen.setFont(Preferences.TITLE_FONT);
		this.theScreen.setColor(Preferences.TITLE_COLOR);
		this.theScreen.drawString(Preferences.TITLE, Preferences.TITLE_X,
				Preferences.TITLE_Y);
	}

	// Game Over display
	public void displayGameOver() {
		this.theScreen.setFont(Preferences.GAME_OVER_FONT);
		this.theScreen.setColor(Preferences.GAME_OVER_COLOR);
		this.theScreen.drawString(Preferences.GAME_OVER_TEXT,
				Preferences.GAME_OVER_X, Preferences.GAME_OVER_Y);
	}

}
