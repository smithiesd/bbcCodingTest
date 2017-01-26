package bbc.codingtests.gameoflife;

import bbc.codingtests.gameoflife.gamestate.GameState;
import bbc.codingtests.gameoflife.gamestate.GameStateImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class LifeTest {

	//TODO make this test pass
	@Test
	public void testEmptyGrid() {
		String emptyStateInput = "...\n...\n...";
		GameState expectedState = new GameStateImpl(emptyStateInput);

		Life testLife = new LifeImpl();
		GameState emptyState = new GameStateImpl(emptyStateInput);
		assertEquals("An empty grid should stay the same", expectedState, testLife.evolve(emptyState));
	}

	//TODO implement further tests for the other cases in the instructions
}
