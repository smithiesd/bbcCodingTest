package bbc.codingtests.gameoflife;

import org.junit.Test;
import org.junit.BeforeClass;
import java.util.Arrays;

import static org.junit.Assert.*;

public class LifeTest {

	@Test
	public void testEmptyGrid() {
		Life testLife = new LifeImpl();
		boolean[][] emptyGrid = { { false, false, false },
					  { false, false, false },
					  { false, false, false } };
		assertTrue("An empty grid should stay the same", Arrays.deepEquals(emptyGrid, testLife.evolve(emptyGrid)));

	}
}
