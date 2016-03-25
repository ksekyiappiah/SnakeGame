import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Queue;

/**
 * SpampedeBrain - The "Controller" in MVC, which includes all of the AI and
 * handling key presses
 * 
 * 
 */

public class SpampedeBrain extends SpampedeBrainParent {

	private SpampedeDisplay theDisplay; // The "View" in MVC
	private SpampedeData theData; // The "Model" in MVC
	private int cycleNum = 0;

	// Mapping between direction (names) and keys
	private static final char REVERSE = 'r';
	private static final char UP = '8';
	private static final char DOWN = '2';
	private static final char LEFT = '4';
	private static final char RIGHT = '6';
	private static final char AI_MODE = 'a';
	private static final char PLAY_SPAM_NOISE = 's';

	/************************************
	 * Constructing the board
	 ************************************/
	// start a new game :)
	public void startNewGame() {
		this.theData = new SpampedeData();
		this.theData.placePedeAtStartLocation();
		this.theData.setStartDirection();
		this.theDisplay = new SpampedeDisplay(this.theData, this.screen,
				this.getSize().width, getSize().height);

		this.playSound_spam();
		this.theDisplay.updateGraphics();
	}

	public static SpampedeBrain getTestGame(TestGame gameNum) {
		SpampedeBrain brain = new SpampedeBrain();
		brain.theData = new SpampedeData(gameNum);
		return brain;
	}

	/************************************
	 * Cycle
	 ************************************/
	// cycle() is called called once per frame
	public void cycle() {

		// update the Pede
		this.updateCentipede();

		// update the list of Spam
		this.updateSpam();

		// draw the maze
		this.theDisplay.updateGraphics();

		// make the new display visible - sends the buffer to the screen
		this.repaint();

		// a variable to keep track of how many cycles have elapsed.
		this.cycleNum++;
	}

	/************************************
	 * Key Presses
	 ************************************/
	// called by Java when a key is pressed
	public void keyPressed(KeyEvent evt) {
		switch (evt.getKeyChar()) {
		
		case UP: 
			this.theData.setDirectionNorth();
			break;
		case DOWN: 
			this.theData.setDirectionSouth();
			break;
		case LEFT: 
			this.theData.setDirectionWest();
			break;
		case RIGHT: 
			this.theData.setDirectionEast();
			break;
		case REVERSE:
			this.reversePede();
			break;
		case AI_MODE:
			this.theData.setMode_AI();
			break;
		case PLAY_SPAM_NOISE:
			this.playSound_spam();
			break;
		default:
			this.theData.setDirectionEast();
		}
	}

	/************************************
	 * Pede Movement
	 ************************************/
	// Advance the pede every REFRESH_RATE cycles
	// Updates the position of centipede as it moves. Also increases length if eat eats a spam. Called by cycle method
	void updateCentipede() {
		if (this.cycleNum % Preferences.REFRESH_RATE == 0) {
			MazeCell nextCell;
			if (this.theData.inAImode()) {
				nextCell = this.getNextCellFromBFS();
			} else {
				nextCell = this.theData.getNextCellInDir();
			}
			this.advancePede(nextCell);
		}
	}

	// Move the pede to the next cell (and possibly eat spam)
	// Method is public only to allow for testing!
	public void advancePede(MazeCell nextCell) {
		// Note - do not modify provided code.
		if (nextCell.isWall() || nextCell.isBody()) {
			this.gameOver();
			return;
		} else if (nextCell.isSpam()) {
			this.playSound_spamEaten();
			this.theData.growPede(nextCell);
			
			
		} else {
			// just regular movement
			this.theData.moveFreely(nextCell);
		}
		
	}

	// Update the model if there game is over
	public void gameOver() {
		super.pause(); // pause the game
		this.theData.setGameOver(); // tell the model that the game is over
		if (this.audioMeow != null) { // play a sound
			this.audioMeow.play();
		}
	}

	/************************************
	 * Spam management
	 ************************************/
	// Add spam every SPAM_ADD_RATE cycles
	void updateSpam() {
		if (this.theData.noSpam()) {
			this.theData.addSpam();
		} else if (this.cycleNum % Preferences.SPAM_ADD_RATE == 0) {
			this.theData.addSpam();
		}
	}

	/************************************
	 * BFS resources
	 ************************************/
	// Uses BFS to find the next cell to move onto to reach the nearest spam
	public MazeCell getNextCellFromBFS() {
		theData.resetCellsForNextSearch();
		// initialize the cellsToSearch with the pede head
		Queue<MazeCell> cellsToSearch = new LinkedList<MazeCell>();
		MazeCell pedeHead = theData.getPedeHead();
		pedeHead.setAddedToSearchList();
		cellsToSearch.add(pedeHead);

		MazeCell cellToReturn = null;
		// Search!
		// TODO: Make sure you understand the code above and then implement your
		// search here!
		
		// Note: we encourage you to write the helper method below
		// getFirstCellInPath to do the backtracking to calculate the next cell!

		// No more cells to search! Just move in a random direction!
		return this.theData.getRandomNeighboringCell(pedeHead);
	}

	// Does the backtracking through the parents for multiBFS
	private MazeCell getFirstCellInPath(MazeCell spamCell) {
		return null;
	}

	/************************************
	 * Reverse method
	 ************************************/
	// reverse the pede
	public void reversePede() {
		this.theData.unLabelHead();
		this.theData.reverseHelper();
		this.theData.labelHead();
		this.theData.newDir();
	}

	/************************************
	 * Sounds
	 ************************************/
	// Play crunch noise
	public void playSound_spamEaten() {
		if (this.audioCrunch != null) {
			this.audioCrunch.play();
		}
	}

	// Play spam noise
	public void playSound_spam() {
		if (this.audioSpam != null) {
			this.audioSpam.play();
		}
	}

	// play meow noise
	public void playSound_meow() {
		if (this.audioMeow != null) {
			this.audioMeow.play();
		}
	}

	// not used - a variable added to remove a Java warning:
	private static final long serialVersionUID = 1L;

	/************************************
	 * Testing Infrastructure
	 ************************************/
	public String testing_toStringParent() {
		return this.theData.toStringParents();
	}

	public MazeCell testing_getNextCellInDir() {
		return this.theData.getNextCellInDir();
	}

	public String testing_toStringSpampedeData() {
		return this.theData.toString();
	}
}
