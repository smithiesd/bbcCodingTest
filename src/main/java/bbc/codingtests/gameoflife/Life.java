package bbc.codingtests.gameoflife;

import com.google.common.collect.TreeBasedTable;

import bbc.codingtests.gameoflife.cells.Cell;
import bbc.codingtests.gameoflife.gamestate.GameState;

public interface Life
{
	/**
	 * Apply the rules of the Game of Life to the given state and return the resulting state
     */
	GameState evolve(GameState currentState);
	
	/**
	 * Expand the grid if new life is going to be created in cells out
	 * of initial state boundaries 
	 * 
	 * @param currentState the current game state
	 * @return the new cellgrid
	 */
	TreeBasedTable<Integer,Integer, Cell> checkLifeOusideInitialGrid(GameState currentState);
	
	/**
	 * Checks the surrounding cells and alters the cell
	 * depending if it is a live or dead cell.
	 * If it is a dead cell given it should be kept as is or 
	 * given life if and only if the cell has exactly three neighbouring live
	 * cells. If given a live cell should be kept as is or 
	 * killed if:
	 * There are fewer than two live neighbours
	 * There are more than three live.
	 * @param cell The current cell being checked
	 * @param rowPos The row position of the current cell being checked
	 * @param colPos The column position of the cell being checked
	 * @param cellGrid The cell grid of the current game state
	 * @return Whether to create or kill the cell dependent on the cell parameter
	 */
	boolean checkSurroundingCell(Cell cell, int rowPos, int colPos, 
			TreeBasedTable<Integer,Integer, Cell> cellGrid );
}
