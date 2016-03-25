import java.awt.Color;
/**
 * MazeCell - Represents a single cell within a Maze. 
 * 
 * 
 */

public class MazeCell {
	// Basic contents of a MazeCell
	private int row; 
	private int column; 
	private CellType myCellType;
	
	// Additional instance variables to be used during search
	private boolean addedToSearchList = false; 
	private MazeCell parent  = null; 

	// Constructor
	public MazeCell (int inputRow, int inputColumn, CellType type){
		this.row = inputRow; 
		this.column = inputColumn; 
		this.myCellType = type;
	}
	
	/************************************
	 * toString and toString Helper
	 ************************************/
	// toString returns the string representation of a MazeElement
	public String toString() {
		return "[" + this.row + ", " + this.column + ", " + this.toStringType() + "]";
	}
	// get the contents of the MazeCell
	public String toStringType() {
		return this.myCellType.getDisplayChar();
	}
	public String toStringParent(){
		if (this.parent == null){
			return "[null]";
		}
		else {
			return "[" + this.parent.row + ", " + this.parent.column + "]";
		}
	}

	/************************************
	 * Access basic information about a cell
	 ************************************/
	// returns the row of this MazeCell.
	public int getRow() {
		return this.row;
	}
	// returns the column of this MazeCell
	public int getColumn() {
		return this.column;
	}
	// check if it is a wall
	public boolean isWall() {
		return this.myCellType == CellType.WALL;
	}
	// check if it is open (not a wall or a pede)
	public boolean isOpen() {
		return this.myCellType == CellType.OPEN || this.isSpam();
	}
	// check if it is spam
	public boolean isSpam() {
		return this.myCellType == CellType.SPAM;
	}
	// check if it is part of the pede (not the head)
	public boolean isBody() {
		return this.myCellType == CellType.BODY;
	}

	// check if it is the head of the pede
	public boolean isHead() {
		return this.myCellType == CellType.HEAD;
	}
	
	// get the color for a cell
	public Color getCellColor(){
		if (this.isWall()) {
			return Preferences.COLOR_WALL;
		} else if (this.isSpam()) {
			return Preferences.COLOR_SPAM;
		} else if (this.isOpen()) {
			return Preferences.COLOR_OPEN;
		} else if (this.isHead()) {
			return Preferences.COLOR_HEAD;
		} else if (this.isBody()) {
			return Preferences.COLOR_BODY;
		} else {
			return Preferences.COLOR_OPEN;
		}
	}
	
	/************************************
	 * Modify basic info about a cell
	 ************************************/
	// Make the particular MazeCell be Spam
	public void becomeSpam() {
		this.myCellType = CellType.SPAM;
	}
	// Make the particular MazeCell be Open
	public void becomeOpen() {
		this.myCellType = CellType.OPEN;
	}
	// Make the particular MazeCell the pede head
	public void becomeHead() {
		this.myCellType = CellType.HEAD;
	}
	// Make the particular MazeCell the pede body
	public void becomeBody() {
		this.myCellType = CellType.BODY;
	}
	

	/************************************
	 * Methods used to access and set search info 
	 ************************************/
	// setting addedToSearchList to be true
	public void setAddedToSearchList() {
		this.addedToSearchList = true;
	}

	// accessing addedToSearchList
	public boolean inSearchListAlready() {
		return this.addedToSearchList;
	}

	// setting visit to be false
	public void clear_RestartSearch() {
		this.addedToSearchList = false;
		this.parent = null;
	}

	// set parent
	public void setParent(MazeCell p) {
		this.parent = p;
	}
	
	// access parent
	public MazeCell getParent() {
		return this.parent;
	}
}
