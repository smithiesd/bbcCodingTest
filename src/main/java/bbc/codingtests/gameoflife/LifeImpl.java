package bbc.codingtests.gameoflife;

import java.util.ArrayList;
import java.util.Map;

import com.google.common.collect.TreeBasedTable;

import bbc.codingtests.gameoflife.cells.Cell;
import bbc.codingtests.gameoflife.cells.CellCache;
import bbc.codingtests.gameoflife.gamestate.GameState;

/**
 * A class th\t simulates the game of life based
 * on a specific ruleset.  As the specification states
 * that it is in infinite 2d grid I have factored in the 
 * grid being expanded based on new cells being created 
 * ouside of the initial grid boundaries.
 * @author David Smithies
 *
 */
public class LifeImpl implements Life
{
	public GameState evolve(GameState currentState) {
		TreeBasedTable<Integer,Integer, Cell> cellGrid = checkLifeOusideInitialGrid(currentState);
		ArrayList<CellCache> cellcache = new ArrayList<>();
		Map<Integer, Map<Integer,Cell>> rowMap = cellGrid.rowMap();
		for(Integer row : rowMap.keySet()) {
			Map<Integer, Cell> tempMap = rowMap.get(row);
			for(Map.Entry<Integer, Cell> cellEntry : tempMap.entrySet()) {
				Cell cell = cellGrid.get(row, cellEntry.getKey());
				boolean cellQuery = 
						checkSurroundingCell(cell, row, cellEntry.getKey(), cellGrid);
				if(cell.isAlive() && cellQuery) {
					cellcache.add(new CellCache(row, cellEntry.getKey(),new Cell(false)));
				}
				if(!cell.isAlive() && cellQuery) {
					cellcache.add(new CellCache(row, cellEntry.getKey(),new Cell(true)));
				}
			}
		}
		for(CellCache cacheItem : cellcache) {
			cellGrid.put(cacheItem.getRow(), cacheItem.getColumn(), cacheItem.getCell());
		}
		currentState.setCellGrid(cellGrid);
		return currentState;
	}

	/**
	 * A helper class that checks if the left, right, top and bottom rows
	 * contain 3 live cells.  Returns true if this is the case so that the grid can
	 * be expanded to contain the new live cell.
	 * @param edge The top, bottom, left and right edges of the grid
	 * @param currentState The game state as it currently is.
	 * @return The expanded grid or not.
	 */
	public TreeBasedTable<Integer,Integer, Cell> checkLifeOusideInitialGrid(GameState currentState) {
		TreeBasedTable<Integer,Integer, Cell> cellGrid = currentState.getCellGrid();
		ArrayList<Integer> rowList = new ArrayList<>(cellGrid.rowKeySet());
		ArrayList<Integer> columnList = new ArrayList<>(cellGrid.columnKeySet());
		int counter = 0;
		//check left column
		counter = 0;
		for(Integer row : rowList) {
			if(currentState.isCellAliveAt(row, 0)) {
				counter ++;
			}
		}
		boolean left =  rowOfCellsAlive(counter);
		//add a new column at -1 of left most column if needed
		if(left) {
			for(Integer row : rowList) {
				cellGrid.put(row, columnList.get(0)-1, new Cell(false));
			}
		}
		//check right column
		counter = 0;
		for(Integer row : rowList) {
			if(currentState.isCellAliveAt(row, columnList.size()-1)) {
				counter ++;
			}
		}
		boolean right = rowOfCellsAlive(counter);
		//add a new column at +1 of right most column if needed
		if(right) {
			for(Integer row : rowList) {
				cellGrid.put(row, columnList.size(), new Cell(false));
			}
		}
		//check top row
		counter = 0;
		for(Integer column : columnList) {
			if(currentState.isCellAliveAt(0, column)) {
				counter ++;
			}
		}
		boolean top =  rowOfCellsAlive(counter);
		//add a new row at -1 of  top most row if needed
		if(top) {
			for(Integer column : columnList) {
				cellGrid.put(rowList.get(0)-1, column, new Cell(false));
			}
		}
		//check bottom row
		counter = 0;
		for(Integer column : columnList) {
			if(currentState.isCellAliveAt(rowList.size()-1, column)) {
				counter ++;
			}
		}
		boolean bottom = rowOfCellsAlive(counter);
		//add a new row at +1 of  bottom most row if needed
		if(bottom) {
			for(Integer column : columnList) {
				cellGrid.put(rowList.size(), column, new Cell(false));
			}
		}
		// fill in the corners if necessary
		if(top && left) {
			cellGrid.put(rowList.get(0)-1, columnList.get(0) -1 , new Cell(false));
		}

		if(top && right) {
			cellGrid.put(rowList.get(0)-1, columnList.size(), new Cell(false));
		}

		if(bottom && left) {
			cellGrid.put(rowList.size(), columnList.get(0)-1, new Cell(false));
		}

		if(bottom && right) {
			cellGrid.put(rowList.size(), columnList.size(), new Cell(false));
		}
		return cellGrid;
	}

	/**
	 * Small helper method to save reusing code to check if new cell is needed.
	 * @param counter the number of live cells in a row
	 * @return whether to create a new row.
	 */
	private boolean rowOfCellsAlive(int counter) {
		if(counter >=2) {
			return true;
		} else {
			return false;
		}
	}

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
	public boolean checkSurroundingCell(Cell cell, int rowPos, int colPos, 
			TreeBasedTable<Integer,Integer, Cell> cellGrid ) {
		int counter = 0;
		//check top left (-1,-1)
		Cell cellCheck = cellGrid.get(rowPos-1, colPos-1);  
		if(cellCheck != null &&	cellCheck.isAlive()) {
			counter++;
		}
		//check top  (-1,0)
		cellCheck = cellGrid.get(rowPos-1, colPos);  
		if(cellCheck != null &&	cellCheck.isAlive()) {
			counter++;
		}
		//check top right (-1,+1)
		cellCheck = cellGrid.get(rowPos-1, colPos+1);  
		if(cellCheck != null &&	cellCheck.isAlive()) {
			counter++;
		}
		//check right (0,+1)
		cellCheck =cellGrid.get(rowPos, colPos+1);  
		if(cellCheck != null &&	cellCheck.isAlive()) {
			counter++;
		}
		//check bottom right (+1,+1)
		cellCheck =cellGrid.get(rowPos+1, colPos+1);  
		if(cellCheck != null &&	cellCheck.isAlive()) {
			counter++;
		}

		//check bottom (+1,0)
		cellCheck =cellGrid.get(rowPos+1, colPos);  
		if(cellCheck != null &&	cellCheck.isAlive()) {
			counter++;
		}

		//check bottom left (+1,-1)
		cellCheck =cellGrid.get(rowPos+1, colPos-1);  
		if(cellCheck != null &&	cellCheck.isAlive()) {
			counter++;
		}

		//check left (0,-1)
		cellCheck =cellGrid.get(rowPos, colPos-1);  
		if(cellCheck != null &&	cellCheck.isAlive()) {
			counter++;
		}
		//do we create a new cell?
		if(!cell.isAlive()) {
			if(counter == 3) {
				return true;
			} else {
				return false;
			}
		} else {
			//do we kill the cell?
			if(counter < 2) {
				return true;
			} else if (counter > 3) {
				return true;
			} else if(counter == 2 || counter == 3) {
				return false;
			}
		}
		return false;
	}
}
