package bbc.codingtests.gameoflife.gamestate;

import java.util.Map;

import com.google.common.collect.TreeBasedTable;

import bbc.codingtests.gameoflife.cells.Cell;
import bbc.codingtests.gameoflife.exceptions.InvalidGameStateException;



public class GameStateImpl implements GameState {

	private static final int ALIVE_CHAR = 42;
	private static final int DEAD_CHAR = 46;
	// An infinite two dimensional grid of cells that can either be alive or dead.
	private TreeBasedTable<Integer,Integer, Cell> cellGrid = TreeBasedTable.create();

    /**
     * Converts the cell grid into a string representation of the grid
     * Allows for testing of string returned is correct representation
     * after game has run an arbitrary number of evolutions.
     */
    @Override
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	//begin looping through the rows
    	Map<Integer, Map<Integer,Cell>> rowMap = cellGrid.rowMap();
    	for(Integer row : rowMap.keySet()) {
    		Map<Integer, Cell> tempMap = rowMap.get(row);
    		tempMap.forEach((k,v) -> sb.append(v));
    		sb.append("\n");
    	}
        //remove last \n
        String inputGrid = sb.toString();
        return inputGrid.substring(0, sb.length()-1);
    }

    /**
     * Takes the a string input and creates a 2d grid with alive or dead cells
     * based on the input of "." for a live cell and "*" for a dead one.
     * @param input he input string to be parsed.
     * @throws InvalidGameStateException Thrown exception if the input doesn't 
     * match the correct format.
     */
    public GameStateImpl(String input) throws InvalidGameStateException {
    	String[] txtRows = input.split("[\\r\\n]+");
    	for(int row = 0; row < txtRows.length; row ++) {
    		for(int col = 0 ; col < txtRows[row].length(); col++) {
    			if(txtRows[row].charAt(col) == ALIVE_CHAR) {
    				cellGrid.put(row, col, new Cell(true));    				
    			} else if (txtRows[row].charAt(col) == DEAD_CHAR) {
    				cellGrid.put(row, col, new Cell(false));
    			} else {
    				throw new InvalidGameStateException("input for game state is in incorrect format"); 
    			}
    		}
    	}
    }

    public boolean isCellAliveAt(int row, int col) {
    	return cellGrid.get(row, col).isAlive();
    }

    @Override
    public int getRows() {
    	return cellGrid.rowKeySet().size();
    }

    @Override
    public int getCols() {
    	return cellGrid.columnKeySet().size();
    }
    
    public TreeBasedTable<Integer, Integer, Cell> getCellGrid() {
		return cellGrid;
	}
    
    public void setCellGrid(TreeBasedTable<Integer, Integer, Cell> cellGrid) {
		this.cellGrid = cellGrid;
	}
}
