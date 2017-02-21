package bbc.codingtests.gameoflife;

import bbc.codingtests.gameoflife.cells.Cell;
import bbc.codingtests.gameoflife.exceptions.InvalidGameStateException;
import bbc.codingtests.gameoflife.gamestate.GameState;
import bbc.codingtests.gameoflife.gamestate.GameStateImpl;
import org.junit.Test;

import com.google.common.collect.TreeBasedTable;

import static org.junit.Assert.*;

public class LifeTest {

	//TODO make this test pass
	@Test
	public void testEmptyGrid() throws InvalidGameStateException {
		String emptyStateInput = "...\n...\n...";

		Life testLife = new LifeImpl();
		GameState emptyState = new GameStateImpl(emptyStateInput);
		assertEquals("An empty grid should stay the same", emptyStateInput, testLife.evolve(emptyState).toString());
	}
	
	@Test
	public void testTopRowCreated() throws InvalidGameStateException {
		String stateInput = "***\n...\n...";
		Life testLife = new LifeImpl();
		GameState testState = new GameStateImpl(stateInput);
		TreeBasedTable<Integer,Integer, Cell> cellGrid = testLife.checkLifeOusideInitialGrid(testState);
		assertNotNull(cellGrid.get(-1, 0));
		assertNotNull(cellGrid.get(-1, 1));
		assertNotNull(cellGrid.get(-1, 2));
	}
	
	@Test
	public void testBottomRowCreated() throws InvalidGameStateException {
		String stateInput = "...\n...\n***";
		Life testLife = new LifeImpl();
		GameState testState = new GameStateImpl(stateInput);
		TreeBasedTable<Integer,Integer, Cell> cellGrid = testLife.checkLifeOusideInitialGrid(testState);
		assertNotNull(cellGrid.get(3, 0));
		assertNotNull(cellGrid.get(3, 1));
		assertNotNull(cellGrid.get(3, 2));
	}
	
	@Test
	public void testLeftColumnCreated() throws InvalidGameStateException {
		String stateInput = "*..\n*..\n*..";
		Life testLife = new LifeImpl();
		GameState testState = new GameStateImpl(stateInput);
		TreeBasedTable<Integer,Integer, Cell> cellGrid = testLife.checkLifeOusideInitialGrid(testState);
		assertNotNull(cellGrid.get(0, -1));
		assertNotNull(cellGrid.get(1, -1));
		assertNotNull(cellGrid.get(2, -1));
	}
	
	@Test
	public void testRightColumnCreated() throws InvalidGameStateException {
		String stateInput = "..*\n..*\n..*";
		Life testLife = new LifeImpl();
		GameState testState = new GameStateImpl(stateInput);
		TreeBasedTable<Integer,Integer, Cell> cellGrid = testLife.checkLifeOusideInitialGrid(testState);
		assertNotNull(cellGrid.get(0, 3));
		assertNotNull(cellGrid.get(1, 3));
		assertNotNull(cellGrid.get(2, 3));
	}
	
	@Test
	public void testTopLeftCornerCreated() throws InvalidGameStateException {
		String stateInput = "***\n*..\n*..";
		Life testLife = new LifeImpl();
		GameState testState = new GameStateImpl(stateInput);
		TreeBasedTable<Integer,Integer, Cell> cellGrid = testLife.checkLifeOusideInitialGrid(testState);
		assertNotNull(cellGrid.get(-1, -1));
	}

	@Test
	public void testTopRightCornerCreated() throws InvalidGameStateException {
		String stateInput = "***\n..*\n..*";
		Life testLife = new LifeImpl();
		GameState testState = new GameStateImpl(stateInput);
		TreeBasedTable<Integer,Integer, Cell> cellGrid = testLife.checkLifeOusideInitialGrid(testState);
		assertNotNull(cellGrid.get(-1, 3));
	}
	
	@Test
	public void testBottomLeftCornerCreated() throws InvalidGameStateException {
		String stateInput = "*..\n*..\n***";
		Life testLife = new LifeImpl();
		GameState testState = new GameStateImpl(stateInput);
		TreeBasedTable<Integer,Integer, Cell> cellGrid = testLife.checkLifeOusideInitialGrid(testState);
		assertNotNull(cellGrid.get(3, -1));
	}
	
	@Test
	public void testBottomRightCornerCreated() throws InvalidGameStateException {
		String stateInput = "..*\n..*\n***";
		Life testLife = new LifeImpl();
		GameState testState = new GameStateImpl(stateInput);
		TreeBasedTable<Integer,Integer, Cell> cellGrid = testLife.checkLifeOusideInitialGrid(testState);
		assertNotNull(cellGrid.get(3, 3));
	}
	
	@Test
	public void testDeadCellCreateNewCell() throws InvalidGameStateException {
		String stateInput = "...\n***\n...";
		GameState testState = new GameStateImpl(stateInput);
		Life testLife = new LifeImpl();
		TreeBasedTable<Integer,Integer, Cell> cellGrid = testLife.checkLifeOusideInitialGrid(testState);
		Cell testCell = cellGrid.get(0, 1);
		assertFalse(testCell.isAlive());
		assertTrue(testLife.checkSurroundingCell(testCell, 0, 1, cellGrid));
	}
	
	@Test
	public void testDeadCellNoNewCell() throws InvalidGameStateException {
		String stateInput = "...\n*.*\n...";
		GameState testState = new GameStateImpl(stateInput);
		Life testLife = new LifeImpl();
		TreeBasedTable<Integer,Integer, Cell> cellGrid = testLife.checkLifeOusideInitialGrid(testState);
		Cell testCell = cellGrid.get(0, 1);
		assertFalse(testCell.isAlive());
		assertFalse(testLife.checkSurroundingCell(testCell, 0, 1, cellGrid));
	}
	
	@Test
	public void testLiveCellUnderpopulated() throws InvalidGameStateException {
		String stateInput = "...\n***\n...";
		GameState testState = new GameStateImpl(stateInput);
		Life testLife = new LifeImpl();
		TreeBasedTable<Integer,Integer, Cell> cellGrid = testLife.checkLifeOusideInitialGrid(testState);
		Cell testCell = cellGrid.get(1, 0);
		assertTrue(testCell.isAlive());
		assertTrue(testLife.checkSurroundingCell(testCell, 1, 0, cellGrid));
	}
	
	@Test
	public void testLiveCellOverpopulated() throws InvalidGameStateException {
		String stateInput = ".**\n.**\n..*";
		GameState testState = new GameStateImpl(stateInput);
		Life testLife = new LifeImpl();
		TreeBasedTable<Integer,Integer, Cell> cellGrid = testLife.checkLifeOusideInitialGrid(testState);
		Cell testCell = cellGrid.get(1, 2);
		assertTrue(testCell.isAlive());
		assertTrue(testLife.checkSurroundingCell(testCell, 1, 2, cellGrid));
	}
	
	@Test
	public void testLiveCellSurvival() throws InvalidGameStateException {
		String stateInput = "...\n***\n...";
		GameState testState = new GameStateImpl(stateInput);
		Life testLife = new LifeImpl();
		TreeBasedTable<Integer,Integer, Cell> cellGrid = testLife.checkLifeOusideInitialGrid(testState);
		Cell testCell = cellGrid.get(1, 1);
		assertTrue(testCell.isAlive());
		assertFalse(testLife.checkSurroundingCell(testCell, 1, 1, cellGrid));
	}
	
	@Test
	public void testOneEvolution() throws InvalidGameStateException {
		String stateInput = "...\n***\n...";
		GameState testState = new GameStateImpl(stateInput);
		Life testLife = new LifeImpl();
		testState = testLife.evolve(testState);
		assertEquals(".*.\n.*.\n.*.", testState.toString());
		
	}
	
	@Test
	public void testTwoEvolution() throws InvalidGameStateException {
		String stateInput = "...\n***\n...";
		GameState testState = new GameStateImpl(stateInput);
		Life testLife = new LifeImpl();
		testState = testLife.evolve(testState);
		testState = testLife.evolve(testState);
		assertEquals("...\n***\n...", testState.toString());
		
	}
}
