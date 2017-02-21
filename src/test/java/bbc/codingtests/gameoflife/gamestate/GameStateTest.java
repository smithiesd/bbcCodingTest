package bbc.codingtests.gameoflife.gamestate;

import org.junit.Test;

import bbc.codingtests.gameoflife.exceptions.InvalidGameStateException;

import static org.junit.Assert.*;

public class GameStateTest {

    @Test
    public void testParsing() throws InvalidGameStateException {
        String input = ".*.\n*.*\n...";
        GameState testState = new GameStateImpl(input);
        assertTrue("Row 0, col 1 should be alive",testState.isCellAliveAt(0,1));
        assertFalse("Row 2, col 2 should not be alive",testState.isCellAliveAt(2,2));
    }

    @Test
    public void testRowColCounts() throws InvalidGameStateException {
        String input = "...\n***\n..*";
        GameState testState = new GameStateImpl(input);
        assertEquals("The game should have 3 columns", 3, testState.getCols());
        assertEquals("The game should have 3 rows", 3, testState.getRows());
    }
    
    @Test
    public void testToStringOfInput() throws InvalidGameStateException {
    	String input = ".*.\n*.*\n...";
    	GameState teststate = new GameStateImpl(input);
    	assertEquals(input, teststate.toString());
    }
}
