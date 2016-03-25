import java.awt.Color;
import java.lang.Math;
import java.util.Iterator;
import java.util.LinkedList;


/**
 * SpampedeData - Representation of the maze. Outside of the model, no one knows
 * how the maze is represented. They can only access the methods provided by the
 * class.
 * 
 */
class SpampedeData {
	// Collection of all MazeCells
	private MazeCell[][] mazeCells2D;

	// Spam specific instance variables
	private LinkedList<MazeCell> spamCells = new LinkedList<MazeCell>();
	private int freeSpots = 0;

	// Pede specific instance variables
	private PedeMode currentMode; //
	private LinkedList<MazeCell> pedeCells = new LinkedList<MazeCell>();

	// game state
	private boolean gameOver = false;

	/************************************
	 * Constructor and initialization methods
	 ************************************/
	// Constructor
	public SpampedeData() {
		int height = Preferences.NUM_CELLS_TALL;
		int width = Preferences.NUM_CELLS_WIDE;
		this.mazeCells2D = new MazeCell[height][width];

		// place walls around the outside of the maze
		this.addWalls();

		// fill the remaining cells not already filled!
		this.fillRemainingCells();

	}

	// private method to add walls to empty spots.
	private void addWalls() {
		int height = this.getNumRows();
		int width = this.getNumColumns();

		// Add Left and Right Walls
		for (int row = 0; row < height; row++) {
			this.mazeCells2D[row][0] = new MazeCell(row, 0, CellType.WALL);
			this.mazeCells2D[row][width - 1] = new MazeCell(row, width - 1,
					CellType.WALL);
		}
		// Add top and bottom walls
		for (int column = 0; column < width; column++) {
			this.mazeCells2D[0][column] = new MazeCell(0, column, CellType.WALL);
			this.mazeCells2D[height - 1][column] = new MazeCell(height - 1,
					column, CellType.WALL);
		}
	}

	// private method to fill the rest of the remaining cells (non-walls)
	private void fillRemainingCells() {
		int height = this.getNumRows();
		int width = this.getNumColumns();

		this.freeSpots = 0;
		for (int row = 0; row < height; row++) {
			for (int column = 0; column < width; column++) {
				if (this.mazeCells2D[row][column] == null) {
					this.mazeCells2D[row][column] = new MazeCell(row, column,
							CellType.OPEN);
					this.freeSpots++;
				}
			}
		}
	}

	// initialize the location of the pede
	public void placePedeAtStartLocation() {
		MazeCell body = this.getCell(1, 1);
		MazeCell head = this.getCell(1, 2);
		this.pedeCells.add(body);
		this.pedeCells.add(head);
		head.becomeHead();
		body.becomeBody();
	}

	/************************************
	 * Methods to access information about the maze
	 ************************************/
	// check if in AI mode
	public boolean inAImode() {
		return this.currentMode == PedeMode.AI_MODE;
	}

	// get the height (rows)
	public int getNumRows() {
		return this.mazeCells2D.length;
	}

	// get the width (columns)
	public int getNumColumns() {
		return this.mazeCells2D[0].length;
	}

	// access a cell at a particular location
	// ONLY public to facilitate testing!!!
	public MazeCell getCell(int r, int c) {
		if (r >= this.getNumRows() || c >= this.getNumColumns() || r < 0
				|| c < 0) {
			System.err.println("Trying to access cell outside of the Maze:");
			System.err.println("row: " + r + " col: " + c);
			System.exit(0);
		}
		return this.mazeCells2D[r][c];
	}

	/************************************
	 * Spam Methods
	 ************************************/
	// check if there are no spam
	public boolean noSpam() {
		return this.spamCells.isEmpty();
	}

	// add spam to an open spot.
	public void addSpam() {
		int row = (int) (this.getNumRows() * Math.random());
		int column = (int) (this.getNumColumns() * Math.random());
		MazeCell cell = this.getCell(row, column);
		if (cell.isOpen()) { // open
			cell.becomeSpam();
			spamCells.add(cell);
		} else {
			double totalSize = this.getNumColumns() * this.getNumRows();
			double currentFreeSpots = this.freeSpots - this.pedeCells.size()
					- this.spamCells.size();
			double ratioFree = currentFreeSpots / totalSize;
			if (ratioFree < 0.2) {
				System.err.println("Not adding more spam");
			} else {
				addSpam();
			}
		}
	}

	// periodically remove spam - optional functionality
	private void removeSpam() {
		if (!spamCells.isEmpty()) {
			spamCells.peekFirst().becomeOpen();
			spamCells.removeFirst();
		}
	}

	/************************************
	 * Pede movement methods
	 ************************************/
	//helper function to grow centipede by adding a new head and making oldhead a body
	public void growPede(MazeCell nextCell){
		nextCell.becomeHead();
		MazeCell oldHead = getPedeHead();
		oldHead.becomeBody();
		pedeCells.addLast(nextCell);

	}
	//helper function to make pede move around
	public void moveFreely(MazeCell nextCell){
		 pedeCells.peekFirst().becomeOpen();
		 pedeCells.removeFirst();
	     nextCell.becomeHead();;
	     pedeCells.addLast(nextCell);
	}

	/************************************
	 * Methods to support movement without AI
	 ************************************/
	// returns a cell North, South, East or West of the pede head based upon the
	// current mode of the pede
	public MazeCell getNextCellInDir() {
		MazeCell head = this.getPedeHead();
		int row = head.getRow();
		int col = head.getColumn();
		
		if(currentMode==PedeMode.GOING_NORTH){
			row--;
		}
		
		else if(currentMode==PedeMode.GOING_SOUTH){
			row++;
		}
		
		else if(currentMode==PedeMode.GOING_EAST){
			col++;
		}
		else if (currentMode==PedeMode.GOING_WEST){
			col--;
		}
		MazeCell nextCell = this.mazeCells2D[row][col];
		return nextCell;
	}

	// North of head
	public MazeCell getNorthNeighbor() {
		MazeCell head = this.getPedeHead();
		int row = head.getRow()-1;
		int col = head.getColumn();
		MazeCell north =  this.mazeCells2D[row][col];
		return north;
	}

	// South of head
	public MazeCell getSouthNeighbor() {
		MazeCell head = this.getPedeHead();
		int row = head.getRow()+1;
		int col = head.getColumn();
		MazeCell south =  this.mazeCells2D[row][col];
		return south;
	}

	// East of head
	public MazeCell getEastNeighbor() {
		MazeCell head = this.getPedeHead();
		int row = head.getRow();
		int col = head.getColumn()+1;
		MazeCell east =  this.mazeCells2D[row][col];
		return east;
	}

	// West of head
	public MazeCell getWestNeighbor() {
		MazeCell head = this.getPedeHead();
		int row = head.getRow();
		int col = head.getColumn()-1;
		MazeCell west =  this.mazeCells2D[row][col];
		return west;
	}

	/************************************
	 * Public methods to get all or one (random) neighbor
	 ************************************/
	// getNeighbors in an array
	public MazeCell[] getNeighbors(MazeCell center) {
		// neighbors to an array
		MazeCell[] neighborsArray = { getNorthNeighbor(center),
				getSouthNeighbor(center), getEastNeighbor(center),
				getWestNeighbor(center) };
		return neighborsArray;
	}

	// get a random cell when there is no good path!
	public MazeCell getRandomNeighboringCell(MazeCell start) {
		MazeCell[] neighborsArray = getNeighbors(start);
		for (MazeCell mc : neighborsArray) {
			if (mc.isOpen()) {
				return mc;
			}
		}
		// if we didn't find an open space, just return the north cell
		return neighborsArray[0];
	}

	/************************************
	 * Helpers - Get cells around another cell
	 ************************************/
	// North of cell
	public MazeCell getNorthNeighbor(MazeCell cell) {
		int row = cell.getRow()-1;
		int col = cell.getColumn();
		MazeCell north =  this.mazeCells2D[row][col];
		return north;
	}

	// South of cell
	public MazeCell getSouthNeighbor(MazeCell cell) {
		int row = cell.getRow()+1;
		int col = cell.getColumn();
		MazeCell south =  this.mazeCells2D[row][col];
		return south;
	}

	// East of cell
	public MazeCell getEastNeighbor(MazeCell cell) {
		int row = cell.getRow();
		int col = cell.getColumn()+1;
		MazeCell east =  this.mazeCells2D[row][col];
		return east;
	}

	// West of cell
	public MazeCell getWestNeighbor(MazeCell cell) {
		int row = cell.getRow();
		int col = cell.getColumn()-1;
		MazeCell west =  this.mazeCells2D[row][col];
		return west;
	}

	/************************************
	 * Methods to set the directions
	 ************************************/
	public void setDirectionNorth() {
		this.currentMode = PedeMode.GOING_NORTH;
	}

	public void setDirectionSouth() {
		this.currentMode = PedeMode.GOING_SOUTH;
	}

	public void setDirectionEast() {
		this.currentMode = PedeMode.GOING_EAST;
	}

	public void setDirectionWest() {
		this.currentMode = PedeMode.GOING_WEST;
	}

	public void setMode_AI() {
		this.currentMode = PedeMode.AI_MODE;
	}

	public void setStartDirection() {
		this.setDirectionEast();
	}

	/************************************
	 * Pede Access Methods
	 ************************************/
	//The head of the Pede is the last item (MazeCell) in the stack or queue of maze cells ie. peekLast
	public MazeCell getPedeHead() {
		return this.pedeCells.peekLast();
	}

	public MazeCell getPedeNeck() {
		int lastPedeCellIndex = this.pedeCells.size() - 1;
		return this.pedeCells.get(lastPedeCellIndex - 1);
	}

	/************************************
	 * Helper method used by the view
	 ************************************/
	public Color getCellColor(int row, int col) {
		MazeCell cell = getCell(row, col);
		return cell.getCellColor();
	}

	/************************************
	 * Helper methods for reverse
	 ************************************/
	//step 1: unlabels head
	public void unLabelHead(){
		getPedeHead().becomeBody();
	}
	
	//step 2: reverse every single Maze cell in pedeCells
	public void reverseHelper(){
		
		for (int i=0; i<pedeCells.size(); i++){
			MazeCell removeLast = pedeCells.remove(pedeCells.size()-1);
			pedeCells.add(i, removeLast);
			
		}
		
	}
	//step 3: relabel head
	public void labelHead(){
		this.getPedeHead().becomeHead();
	}
	
	//step 4: calculate new direction
	public void newDir(){
		int neckrow = this.getPedeNeck().getRow();
		int neckcol =this.getPedeNeck().getColumn();
		int headrow=this.getPedeHead().getRow();
		int headcol=this.getPedeHead().getColumn();
		if (neckrow==headrow+1){
			this.setDirectionNorth();
		}
		else if (neckrow==headrow-1){
			this.setDirectionSouth();
		}
		else if (neckcol==headcol+1){
			this.setDirectionWest();
		}
		else if (neckcol==headcol-1){
			this.setDirectionEast();
		}
	}
	

	/************************************
	 * Methods to set the model during search
	 ************************************/
	// reset all MazeCells
	public void resetCellsForNextSearch() {
		for (MazeCell[] row : this.mazeCells2D) {
			for (MazeCell cell : row) {
				cell.clear_RestartSearch();
			}
		}
	}

	/************************************
	 * Game Over Status
	 ************************************/
	// called when game is over (starts as false in new games)
	public void setGameOver() {
		this.gameOver = true;
	}

	// getter to check if "Game Over" should be displayed
	public boolean getGameOver() {
		return this.gameOver;
	}

	/************************************
	 * toString
	 ************************************/
	public String toString() {
		String result = "";
		for (int r = 0; r < this.getNumRows(); r++) {
			for (int c = 0; c < this.getNumColumns(); c++) {
				MazeCell cell = this.getCell(r, c);
				result += cell.toStringType();
			}
			result += "\n";
		}
		return result;
	}

	/************************************
	 * Testing Infrastructure
	 ************************************/
	// Constructor used exclusively for testing!
	public SpampedeData(TestGame gameNum) {
		// Want pictures of the test boards?
		// http://tinyurl.com/spampedeTestBoards
		this.mazeCells2D = new MazeCell[6][6];
		this.addWalls();
		this.fillRemainingCells();
		if (gameNum.pedeAtStart()) {
			this.testing_pedeAtStartLocation(gameNum);
			this.setDirectionEast();
		} else {
			this.testing_pedeNotAtStartLocation(gameNum);
		}

	}

	private void testing_pedeAtStartLocation(TestGame gameNum) {
		this.placePedeAtStartLocation();
		if (gameNum == TestGame.G1) {
			this.getCell(1, 3).becomeSpam();
		} else if (gameNum == TestGame.G2) {
			this.getCell(2, 2).becomeSpam();
		} else if (gameNum == TestGame.G3) {
			this.getCell(1, 4).becomeSpam();
		} else if (gameNum == TestGame.G4) {
			this.getCell(2, 1).becomeSpam();
		} else if (gameNum == TestGame.G5) {
			this.getCell(4, 1).becomeSpam();
		} else if (gameNum == TestGame.G6) {
			this.getCell(1, 3).becomeSpam();
			this.getCell(3, 1).becomeSpam();
		} else if (gameNum == TestGame.G7) {
			this.getCell(2, 2).becomeSpam();
			this.getCell(1, 4).becomeSpam();
		} else if (gameNum == TestGame.G8) {
			this.getCell(1, 4).becomeSpam();
			this.getCell(4, 2).becomeSpam();
		} else if (gameNum == TestGame.G9) {
			this.getCell(2, 1).becomeSpam();
			this.getCell(2, 4).becomeSpam();
		} else if (gameNum == TestGame.G10) {
			this.getCell(4, 1).becomeSpam();
			this.getCell(4, 4).becomeSpam();
		} else if (gameNum == TestGame.G11) {
			// No spam :)
		}
		// Add all spam to the spam cells
		int height = this.getNumRows();
		int width = this.getNumColumns();
		for (int row = 0; row < height; row++) {
			for (int column = 0; column < width; column++) {
				MazeCell cell = this.getCell(row, column);
				if (cell.isSpam()) {
					this.spamCells.add(cell);
				}
			}
		}
	}

	private void testing_pedeNotAtStartLocation(TestGame gameNum) {
		if (gameNum == TestGame.G12) {
			MazeCell body2 = this.getCell(2, 3);
			MazeCell body1 = this.getCell(2, 2);
			MazeCell head = this.getCell(2, 1);
			this.pedeCells.add(body2);
			this.pedeCells.add(body1);
			this.pedeCells.add(head);
			head.becomeHead();
			body2.becomeBody();
			body1.becomeBody();
		} else if (gameNum == TestGame.G13) {
			MazeCell body2 = this.getCell(3, 2);
			MazeCell body1 = this.getCell(2, 2);
			MazeCell head = this.getCell(2, 1);
			this.pedeCells.add(body2);
			this.pedeCells.add(body1);
			this.pedeCells.add(head);
			head.becomeHead();
			body2.becomeBody();
			body1.becomeBody();
		} else if (gameNum == TestGame.G14) {
			MazeCell body2 = this.getCell(2, 2);
			MazeCell body1 = this.getCell(3, 2);
			MazeCell head = this.getCell(3, 1);
			this.pedeCells.add(body2);
			this.pedeCells.add(body1);
			this.pedeCells.add(head);
			head.becomeHead();
			body2.becomeBody();
			body1.becomeBody();
		} else if (gameNum == TestGame.G15) {
			MazeCell body2 = this.getCell(3, 2);
			MazeCell body1 = this.getCell(3, 3);
			MazeCell head = this.getCell(3, 4);
			this.pedeCells.add(body2);
			this.pedeCells.add(body1);
			this.pedeCells.add(head);
			head.becomeHead();
			body2.becomeBody();
			body1.becomeBody();
		}
	}

	public String toStringParents() {
		String result = "";
		for (int r = 0; r < this.getNumRows(); r++) {
			for (int c = 0; c < this.getNumColumns(); c++) {
				MazeCell cell = this.getCell(r, c);
				result += cell.toStringParent() + "\t";
			}
			result += "\n";
		}
		return result;
	}

}
