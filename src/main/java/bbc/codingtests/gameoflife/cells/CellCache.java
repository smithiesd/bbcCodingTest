package bbc.codingtests.gameoflife.cells;

/**
 * A class for caching newly created or killed cells
 * so that can be added after the grid has been walked through.
 * @author David Smithies
 *
 */
public class CellCache {
	private int row;
	private int column;
	private Cell cell;
	
	/**
	 * Create a new cache of cells
	 * @param row The row of the cell
	 * @param column the column of the cell
	 * @param cell the cell to be added to the cellgrid
	 */
	public CellCache(int row, int column, Cell cell) {
		this.row = row;
		this.column = column;
		this.cell = cell;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public Cell getCell() {
		return cell;
	}

	public void setCell(Cell cell) {
		this.cell = cell;
	}
}
