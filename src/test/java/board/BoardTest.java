package board;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import jewel.Colour;
import jewel.Jewel;
import jewel.NormalJewel;
import observers.BoardObserver;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BoardTest {

	private Board board;

	@Before
	public void before() {
		board = new Board();
	}

	/**
	 * Test to test the getGrid() and setGrid() methods.
	 */
	@Test
	public void setGetGridTest() {
		Jewel[][] grid = {
				{ new NormalJewel(Colour.Red), new NormalJewel(Colour.Blue) },
				{ new NormalJewel(Colour.Green), new NormalJewel(Colour.Yellow) } };
		board.setGrid(grid);
		assertArrayEquals(grid, board.getGrid());
	}

	/**
	 * Test to test the getJewel() and setJewel() methods.
	 */
	@Test
	public void setGetJewel() {
		Jewel[][] grid = { { null, null }, { null, null } };
		board.setGrid(grid);
		Jewel expected = new NormalJewel(Colour.Red);
		Coordinate location = new Coordinate(0, 0);
		board.setJewel(expected, location);
		assertEquals(expected, board.getJewel(location));
	}

	/**
	 * Test to test the getSelectedJewel() and setSelectedJewel() methods.
	 */
	@Test
	public void setGetSelectedJewel() {
		Jewel[][] grid = { { null, null }, { null, null } };
		board.setGrid(grid);
		Coordinate expected = new Coordinate(0, 0);
		board.setSelectedPos(expected);
		assertEquals(expected, board.getSelectedPos());
	}

	/**
	 * Test to test the getBoardObservers() method.
	 */
	@Test
	public void getBoardObservers() {
		List<BoardObserver> result = board.getboardObservers();
		assertEquals(0, result.size());
	}

	/**
	 * Test to test the hasSelectedJewel() when there is no selected Jewel.
	 */
	@Test
	public void hasSelectedJewelFalse() {
		assertEquals(false, board.hasSelectedJewel());
	}

	/**
	 * Test to test the hasSelectedJewel() when there is a selected Jewel.
	 */
	@Test
	public void hasSelectedJewelTrue() {
		board.setSelectedPos(new Coordinate(0, 0));
		assertEquals(true, board.hasSelectedJewel());
	}

	/**
	 * Test to check whether the checkHorizontalMatches() method gives an empty
	 * list when there are no horizontal matches.
	 */
	@Test
	public void checkHorizontalMatchesNoMatches() {
		List<Match> matched = new ArrayList<Match>();
		board.checkHorizontalMatches(matched);
		assertEquals(0, matched.size());
	}

	/**
	 * Test to check whether the checkHorizontalMatches() method gives a list
	 * with one match when there is 1 horizontal 4-match.
	 */
	@Test
	public void checkHorizontalMatchesOneFourMatch() {
		Jewel[][] grid = {
				{ new NormalJewel(Colour.Red), new NormalJewel(Colour.Red),
						new NormalJewel(Colour.Red),
						new NormalJewel(Colour.Red),
						new NormalJewel(Colour.Blue),
						new NormalJewel(Colour.Orange),
						new NormalJewel(Colour.Green),
						new NormalJewel(Colour.Blue) },
				{ new NormalJewel(Colour.Red), new NormalJewel(Colour.Blue),
						new NormalJewel(Colour.Orange),
						new NormalJewel(Colour.Green),
						new NormalJewel(Colour.Blue),
						new NormalJewel(Colour.Orange),
						new NormalJewel(Colour.Green),
						new NormalJewel(Colour.Blue) },
				{ new NormalJewel(Colour.Red), new NormalJewel(Colour.Orange),
						new NormalJewel(Colour.Green),
						new NormalJewel(Colour.Blue),
						new NormalJewel(Colour.Orange),
						new NormalJewel(Colour.Green),
						new NormalJewel(Colour.Blue),
						new NormalJewel(Colour.Orange) },
				{ new NormalJewel(Colour.Red), new NormalJewel(Colour.Green),
						new NormalJewel(Colour.Blue),
						new NormalJewel(Colour.Orange),
						new NormalJewel(Colour.Green),
						new NormalJewel(Colour.Blue),
						new NormalJewel(Colour.Orange),
						new NormalJewel(Colour.Green) },
				{ new NormalJewel(Colour.White),
						new NormalJewel(Colour.Orange),
						new NormalJewel(Colour.Red),
						new NormalJewel(Colour.White),
						new NormalJewel(Colour.Orange),
						new NormalJewel(Colour.Orange),
						new NormalJewel(Colour.Yellow),
						new NormalJewel(Colour.White) },
				{ new NormalJewel(Colour.White), new NormalJewel(Colour.Red),
						new NormalJewel(Colour.Blue),
						new NormalJewel(Colour.Yellow),
						new NormalJewel(Colour.Yellow),
						new NormalJewel(Colour.Green),
						new NormalJewel(Colour.Red),
						new NormalJewel(Colour.Yellow) },
				{ new NormalJewel(Colour.Orange),
						new NormalJewel(Colour.Yellow),
						new NormalJewel(Colour.Blue),
						new NormalJewel(Colour.Purple),
						new NormalJewel(Colour.Red),
						new NormalJewel(Colour.Red),
						new NormalJewel(Colour.Green),
						new NormalJewel(Colour.Yellow) },
				{ new NormalJewel(Colour.White),
						new NormalJewel(Colour.Purple),
						new NormalJewel(Colour.Green),
						new NormalJewel(Colour.Blue),
						new NormalJewel(Colour.Yellow),
						new NormalJewel(Colour.Orange),
						new NormalJewel(Colour.Red),
						new NormalJewel(Colour.Green) } };
		board.setGrid(grid);

		List<Match> matched = new ArrayList<Match>();
		board.checkHorizontalMatches(matched);
		Coordinate expected = new Coordinate(3, 0);

		assertEquals(1, matched.size());
		assertEquals(4, matched.get(0).size());
		assertEquals(expected, matched.get(0).getChild(3));
	}

	/**
	 * Test to check whether the checkHorizontalMatches() method gives a list
	 * with one match when there is 1 horizontal 5-match.
	 */
	@Test
	public void checkHorizontalMatchesOneFiveMatch() {
		Jewel[][] grid = {
				{ new NormalJewel(Colour.Red), new NormalJewel(Colour.Red),
						new NormalJewel(Colour.Red),
						new NormalJewel(Colour.Red),
						new NormalJewel(Colour.Red),
						new NormalJewel(Colour.Orange),
						new NormalJewel(Colour.Green),
						new NormalJewel(Colour.Blue) },
				{ new NormalJewel(Colour.Red), new NormalJewel(Colour.Blue),
						new NormalJewel(Colour.Orange),
						new NormalJewel(Colour.Green),
						new NormalJewel(Colour.Blue),
						new NormalJewel(Colour.Orange),
						new NormalJewel(Colour.Green),
						new NormalJewel(Colour.Blue) },
				{ new NormalJewel(Colour.Red), new NormalJewel(Colour.Orange),
						new NormalJewel(Colour.Green),
						new NormalJewel(Colour.Blue),
						new NormalJewel(Colour.Orange),
						new NormalJewel(Colour.Green),
						new NormalJewel(Colour.Blue),
						new NormalJewel(Colour.Orange) },
				{ new NormalJewel(Colour.Red), new NormalJewel(Colour.Green),
						new NormalJewel(Colour.Blue),
						new NormalJewel(Colour.Orange),
						new NormalJewel(Colour.Green),
						new NormalJewel(Colour.Blue),
						new NormalJewel(Colour.Orange),
						new NormalJewel(Colour.Green) },
				{ new NormalJewel(Colour.White),
						new NormalJewel(Colour.Orange),
						new NormalJewel(Colour.Red),
						new NormalJewel(Colour.White),
						new NormalJewel(Colour.Orange),
						new NormalJewel(Colour.Orange),
						new NormalJewel(Colour.Yellow),
						new NormalJewel(Colour.White) },
				{ new NormalJewel(Colour.White), new NormalJewel(Colour.Red),
						new NormalJewel(Colour.Blue),
						new NormalJewel(Colour.Yellow),
						new NormalJewel(Colour.Yellow),
						new NormalJewel(Colour.Green),
						new NormalJewel(Colour.Red),
						new NormalJewel(Colour.Yellow) },
				{ new NormalJewel(Colour.Orange),
						new NormalJewel(Colour.Yellow),
						new NormalJewel(Colour.Blue),
						new NormalJewel(Colour.Purple),
						new NormalJewel(Colour.Red),
						new NormalJewel(Colour.Red),
						new NormalJewel(Colour.Green),
						new NormalJewel(Colour.Yellow) },
				{ new NormalJewel(Colour.White),
						new NormalJewel(Colour.Purple),
						new NormalJewel(Colour.Green),
						new NormalJewel(Colour.Blue),
						new NormalJewel(Colour.Yellow),
						new NormalJewel(Colour.Orange),
						new NormalJewel(Colour.Red),
						new NormalJewel(Colour.Green) } };
		board.setGrid(grid);

		List<Match> matched = new ArrayList<Match>();
		board.checkHorizontalMatches(matched);
		Coordinate expected = new Coordinate(3, 0);

		assertEquals(1, matched.size());
		assertEquals(5, matched.get(0).size());
		assertEquals(expected, matched.get(0).getChild(3));
	}

	/**
	 * Test to check whether the checkVerticalMatches() method gives a list with
	 * one match when there is 1 vertical 4-match.
	 */
	@Test
	public void checkVerticalMatchesOneFourMatch() {
		Jewel[][] grid = {
				{ new NormalJewel(Colour.Red), new NormalJewel(Colour.Red),
						new NormalJewel(Colour.Red),
						new NormalJewel(Colour.Red),
						new NormalJewel(Colour.Blue),
						new NormalJewel(Colour.Orange),
						new NormalJewel(Colour.Green),
						new NormalJewel(Colour.Blue) },
				{ new NormalJewel(Colour.Red), new NormalJewel(Colour.Blue),
						new NormalJewel(Colour.Orange),
						new NormalJewel(Colour.Green),
						new NormalJewel(Colour.Blue),
						new NormalJewel(Colour.Orange),
						new NormalJewel(Colour.Green),
						new NormalJewel(Colour.Blue) },
				{ new NormalJewel(Colour.Red), new NormalJewel(Colour.Orange),
						new NormalJewel(Colour.Green),
						new NormalJewel(Colour.Blue),
						new NormalJewel(Colour.Orange),
						new NormalJewel(Colour.Green),
						new NormalJewel(Colour.Blue),
						new NormalJewel(Colour.Orange) },
				{ new NormalJewel(Colour.Red), new NormalJewel(Colour.Green),
						new NormalJewel(Colour.Blue),
						new NormalJewel(Colour.Orange),
						new NormalJewel(Colour.Green),
						new NormalJewel(Colour.Blue),
						new NormalJewel(Colour.Orange),
						new NormalJewel(Colour.Green) },
				{ new NormalJewel(Colour.White),
						new NormalJewel(Colour.Orange),
						new NormalJewel(Colour.Red),
						new NormalJewel(Colour.White),
						new NormalJewel(Colour.Orange),
						new NormalJewel(Colour.Orange),
						new NormalJewel(Colour.Yellow),
						new NormalJewel(Colour.White) },
				{ new NormalJewel(Colour.White), new NormalJewel(Colour.Red),
						new NormalJewel(Colour.Blue),
						new NormalJewel(Colour.Yellow),
						new NormalJewel(Colour.Yellow),
						new NormalJewel(Colour.Green),
						new NormalJewel(Colour.Red),
						new NormalJewel(Colour.Yellow) },
				{ new NormalJewel(Colour.Orange),
						new NormalJewel(Colour.Yellow),
						new NormalJewel(Colour.Blue),
						new NormalJewel(Colour.Purple),
						new NormalJewel(Colour.Red),
						new NormalJewel(Colour.Red),
						new NormalJewel(Colour.Green),
						new NormalJewel(Colour.Yellow) },
				{ new NormalJewel(Colour.White),
						new NormalJewel(Colour.Purple),
						new NormalJewel(Colour.Green),
						new NormalJewel(Colour.Blue),
						new NormalJewel(Colour.Yellow),
						new NormalJewel(Colour.Orange),
						new NormalJewel(Colour.Red),
						new NormalJewel(Colour.Green) } };
		board.setGrid(grid);

		List<Match> matched = new ArrayList<Match>();
		board.checkVerticalMatches(matched);
		Coordinate expected = new Coordinate(0, 3);

		assertEquals(1, matched.size());
		assertEquals(4, matched.get(0).size());
		assertEquals(expected, matched.get(0).getChild(3));
	}

	/**
	 * Test to check whether the checkVerticalMatches() method gives a list with
	 * one match when there is 1 vertical 5-match.
	 */
	@Test
	public void checkVerticalMatchesOneFiveMatch() {
		Jewel[][] grid = {
				{ new NormalJewel(Colour.Red), new NormalJewel(Colour.Red),
						new NormalJewel(Colour.Red),
						new NormalJewel(Colour.Red),
						new NormalJewel(Colour.Blue),
						new NormalJewel(Colour.Orange),
						new NormalJewel(Colour.Green),
						new NormalJewel(Colour.Blue) },
				{ new NormalJewel(Colour.Red), new NormalJewel(Colour.Blue),
						new NormalJewel(Colour.Orange),
						new NormalJewel(Colour.Green),
						new NormalJewel(Colour.Blue),
						new NormalJewel(Colour.Orange),
						new NormalJewel(Colour.Green),
						new NormalJewel(Colour.Blue) },
				{ new NormalJewel(Colour.Red), new NormalJewel(Colour.Orange),
						new NormalJewel(Colour.Green),
						new NormalJewel(Colour.Blue),
						new NormalJewel(Colour.Orange),
						new NormalJewel(Colour.Green),
						new NormalJewel(Colour.Blue),
						new NormalJewel(Colour.Orange) },
				{ new NormalJewel(Colour.Red), new NormalJewel(Colour.Green),
						new NormalJewel(Colour.Blue),
						new NormalJewel(Colour.Orange),
						new NormalJewel(Colour.Green),
						new NormalJewel(Colour.Blue),
						new NormalJewel(Colour.Orange),
						new NormalJewel(Colour.Green) },
				{ new NormalJewel(Colour.Red), new NormalJewel(Colour.Orange),
						new NormalJewel(Colour.Red),
						new NormalJewel(Colour.White),
						new NormalJewel(Colour.Orange),
						new NormalJewel(Colour.Orange),
						new NormalJewel(Colour.Yellow),
						new NormalJewel(Colour.White) },
				{ new NormalJewel(Colour.White), new NormalJewel(Colour.Red),
						new NormalJewel(Colour.Blue),
						new NormalJewel(Colour.Yellow),
						new NormalJewel(Colour.Yellow),
						new NormalJewel(Colour.Green),
						new NormalJewel(Colour.Red),
						new NormalJewel(Colour.Yellow) },
				{ new NormalJewel(Colour.Orange),
						new NormalJewel(Colour.Yellow),
						new NormalJewel(Colour.Blue),
						new NormalJewel(Colour.Purple),
						new NormalJewel(Colour.Red),
						new NormalJewel(Colour.Red),
						new NormalJewel(Colour.Green),
						new NormalJewel(Colour.Yellow) },
				{ new NormalJewel(Colour.White),
						new NormalJewel(Colour.Purple),
						new NormalJewel(Colour.Green),
						new NormalJewel(Colour.Blue),
						new NormalJewel(Colour.Yellow),
						new NormalJewel(Colour.Orange),
						new NormalJewel(Colour.Red),
						new NormalJewel(Colour.Green) } };
		board.setGrid(grid);

		List<Match> matched = new ArrayList<Match>();
		board.checkVerticalMatches(matched);
		Coordinate expected = new Coordinate(0, 4);

		assertEquals(1, matched.size());
		assertEquals(5, matched.get(0).size());
		assertEquals(expected, matched.get(0).getChild(4));
	}

	/**
	 * Test to test the checkMatches() method when there are no matches.
	 */
	@Test
	public void checkMatchesNoMatches() {
		List<Match> result = board.checkMatches();
		assertEquals(0, result.size());
	}

	/**
	 * Test to test the checkMatches() method when there are multiple matches.
	 */
	@Test
	public void checkMatchesMultipleMatches() {
		Jewel[][] grid = {
				{ new NormalJewel(Colour.Red), new NormalJewel(Colour.Red),
						new NormalJewel(Colour.Red),
						new NormalJewel(Colour.Yellow),
						new NormalJewel(Colour.Blue),
						new NormalJewel(Colour.Orange),
						new NormalJewel(Colour.Green),
						new NormalJewel(Colour.Blue) },
				{ new NormalJewel(Colour.White), new NormalJewel(Colour.Blue),
						new NormalJewel(Colour.Orange),
						new NormalJewel(Colour.Green),
						new NormalJewel(Colour.Blue),
						new NormalJewel(Colour.Orange),
						new NormalJewel(Colour.Green),
						new NormalJewel(Colour.Blue) },
				{ new NormalJewel(Colour.Red), new NormalJewel(Colour.Orange),
						new NormalJewel(Colour.Green),
						new NormalJewel(Colour.Blue),
						new NormalJewel(Colour.Orange),
						new NormalJewel(Colour.Green),
						new NormalJewel(Colour.Blue),
						new NormalJewel(Colour.Orange) },
				{ new NormalJewel(Colour.Red), new NormalJewel(Colour.Green),
						new NormalJewel(Colour.Blue),
						new NormalJewel(Colour.Orange),
						new NormalJewel(Colour.Green),
						new NormalJewel(Colour.Blue),
						new NormalJewel(Colour.Orange),
						new NormalJewel(Colour.Green) },
				{ new NormalJewel(Colour.Red), new NormalJewel(Colour.Orange),
						new NormalJewel(Colour.Red),
						new NormalJewel(Colour.White),
						new NormalJewel(Colour.Orange),
						new NormalJewel(Colour.Orange),
						new NormalJewel(Colour.Yellow),
						new NormalJewel(Colour.White) },
				{ new NormalJewel(Colour.White), new NormalJewel(Colour.Red),
						new NormalJewel(Colour.Blue),
						new NormalJewel(Colour.Yellow),
						new NormalJewel(Colour.Yellow),
						new NormalJewel(Colour.Green),
						new NormalJewel(Colour.Red),
						new NormalJewel(Colour.Yellow) },
				{ new NormalJewel(Colour.Orange),
						new NormalJewel(Colour.Yellow),
						new NormalJewel(Colour.Blue),
						new NormalJewel(Colour.Purple),
						new NormalJewel(Colour.Red),
						new NormalJewel(Colour.Red),
						new NormalJewel(Colour.Green),
						new NormalJewel(Colour.Yellow) },
				{ new NormalJewel(Colour.White),
						new NormalJewel(Colour.Purple),
						new NormalJewel(Colour.Green),
						new NormalJewel(Colour.Blue),
						new NormalJewel(Colour.Yellow),
						new NormalJewel(Colour.Orange),
						new NormalJewel(Colour.Red),
						new NormalJewel(Colour.Green) } };
		board.setGrid(grid);
		List<Match> result = board.checkMatches();
		assertEquals(2, result.size());
		assertEquals(3, result.get(0).size());
		assertEquals(3, result.get(1).size());
	}

	@Test
	public void testSwapJewels() {
		Jewel jewel1 = new NormalJewel();
		Jewel jewel2 = new NormalJewel();
		Jewel[][] grid = {
				{ jewel1, jewel2, new NormalJewel(Colour.Red),
						new NormalJewel(Colour.Yellow),
						new NormalJewel(Colour.Blue),
						new NormalJewel(Colour.Orange),
						new NormalJewel(Colour.Green),
						new NormalJewel(Colour.Blue) },
				{ new NormalJewel(Colour.Red), new NormalJewel(Colour.Blue),
						new NormalJewel(Colour.Orange),
						new NormalJewel(Colour.Green),
						new NormalJewel(Colour.Blue),
						new NormalJewel(Colour.Orange),
						new NormalJewel(Colour.Green),
						new NormalJewel(Colour.Blue) },
				{ new NormalJewel(Colour.Red), new NormalJewel(Colour.Orange),
						new NormalJewel(Colour.Green),
						new NormalJewel(Colour.Blue),
						new NormalJewel(Colour.Orange),
						new NormalJewel(Colour.Green),
						new NormalJewel(Colour.Blue),
						new NormalJewel(Colour.Orange) },
				{ new NormalJewel(Colour.Blue), new NormalJewel(Colour.Green),
						new NormalJewel(Colour.Blue),
						new NormalJewel(Colour.Orange),
						new NormalJewel(Colour.Green),
						new NormalJewel(Colour.Blue),
						new NormalJewel(Colour.Orange),
						new NormalJewel(Colour.Green) },
				{ new NormalJewel(Colour.White),
						new NormalJewel(Colour.Orange),
						new NormalJewel(Colour.Red),
						new NormalJewel(Colour.White),
						new NormalJewel(Colour.Orange),
						new NormalJewel(Colour.Orange),
						new NormalJewel(Colour.Yellow),
						new NormalJewel(Colour.White) },
				{ new NormalJewel(Colour.White), new NormalJewel(Colour.Red),
						new NormalJewel(Colour.Blue),
						new NormalJewel(Colour.Yellow),
						new NormalJewel(Colour.Yellow),
						new NormalJewel(Colour.Green),
						new NormalJewel(Colour.Red),
						new NormalJewel(Colour.Yellow) },
				{ new NormalJewel(Colour.Orange),
						new NormalJewel(Colour.Yellow),
						new NormalJewel(Colour.Blue),
						new NormalJewel(Colour.Purple),
						new NormalJewel(Colour.Red),
						new NormalJewel(Colour.Red),
						new NormalJewel(Colour.Green),
						new NormalJewel(Colour.Yellow) },
				{ new NormalJewel(Colour.White),
						new NormalJewel(Colour.Purple),
						new NormalJewel(Colour.Green),
						new NormalJewel(Colour.Blue),
						new NormalJewel(Colour.Yellow),
						new NormalJewel(Colour.Orange),
						new NormalJewel(Colour.Red),
						new NormalJewel(Colour.Green) } };
		board.setGrid(grid);
		board.swapJewels(new Coordinate(0, 0), new Coordinate(1, 0));
		assertEquals(jewel2, board.getJewel(new Coordinate(0, 0)));
	}
}
