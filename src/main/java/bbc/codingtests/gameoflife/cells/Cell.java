package bbc.codingtests.gameoflife.cells;


/**
 * A class representing the cell in the infinite
 * grid in GameState. Each cell can be represented as an 
 * object within the two dimensional grid.
 * @author David Smithies
 *
 */
public class Cell {
	private boolean alive;
	
	/**
	 * Create a new cell and pass whether it is alive or dead
	 * @param status the parameter stating whether the cell
	 * is alive or dead
	 */
	public Cell(boolean status) {
		alive = status;
	}
	
	/**
	 * Returns the string format of a cell depending alive or dead.
	 * a "*" is for alive and "." for a dead cell.
	 */
	@Override
	public String toString() {
		if(this.alive) {
			return "*";
		} else {
			return ".";
		}
	}
	
	public boolean isAlive() {
		return alive;
	}
	
	public void setAlive(boolean alive) {
		this.alive = alive;
	}

}
