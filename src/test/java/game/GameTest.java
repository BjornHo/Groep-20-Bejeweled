package game;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import board.Board;
import board.Coordinate;
import board.Match;
import jewel.Colour;
import jewel.Jewel;
import jewel.NormalJewel;

import org.junit.Before;
import org.junit.Test;


public class GameTest {

	Game game;
	
	@Before
	public void before() {
		game = new Game();
	}
	
	/**
	 * Test processJewel() when no jewel is selected.
	 */
	@Test
	public void processJewelNoSelectedJewel() {
		Coordinate expected = new Coordinate(0,0);
		Board board = game.getBoard();
		game.processJewel(expected);
		assertEquals(expected, board.getSelectedPos());
	}
	
	/**
	 * Test processJewel() with non-adjacent jewel.
	 */
	@Test
	public void processJewelSelectedNotAdjacent() {
		Board board = game.getBoard();
		board.setSelectedPos(new Coordinate(0,0));
		Coordinate expected = new Coordinate(1,1);
		game.processJewel(expected);
		assertEquals(expected, board.getSelectedPos());
	}
	
	/**
	 * Test processJewel() when you process the selected Jewel.
	 */
	@Test
	public void processJewelSelectedJewel() {
		Coordinate expected = new Coordinate(0,0);
		Board board = game.getBoard();
		board.setSelectedPos(expected);
		game.processJewel(expected);
		assertEquals(expected, board.getSelectedPos());
	}
	
	/**
	 * Test processJewel() with adjacent jewel, no match made.
	 */
	@Test
	public void processJewelSelectedAdjacentNoMatch() {
		Jewel expected = new NormalJewel(Colour.Orange);
		Board board = game.getBoard();
		board.setJewel(expected, new Coordinate(0,0));
		board.setSelectedPos(new Coordinate(0,0));
		game.processJewel(new Coordinate(0,1));
		assertEquals(false, board.hasSelectedJewel());
		assertEquals(expected, board.getJewel(new Coordinate(0,0)));
	}
	
	/**
	 * Test processJewel() with adjacent Jewel, creating a match.
	 */
	@Test
	public void processJewelSelectedAdjacentMatch() {
		Board board = game.getBoard();
		Jewel expected = board.getJewel(new Coordinate(2,4));
		board.setSelectedPos(new Coordinate(3,7));
		game.processJewel(new Coordinate(2,7));
		assertEquals(expected, board.getJewel(new Coordinate(2,7)));
	}
	
	/**
	 * Test to test the processMatches() method with a vertical match.
	 */
	@Test
	public void processMatchesVertical() {
		Jewel[][] grid = { { new NormalJewel(Colour.Green) },
				{ new NormalJewel(Colour.Red) },
				{ new NormalJewel(Colour.Red) },
				{ new NormalJewel(Colour.Red) } };
		game.getBoard().setGrid(grid);
		Match match = new Match(game.getBoard());
		match.add(new Coordinate(0,1));
		match.add(new Coordinate(0,2));
		match.add(new Coordinate(0,3));
		List<Match> list = new ArrayList<>();
		list.add(match);
		Board board = game.getBoard();
		Jewel expected = board.getJewel(new Coordinate(0,0));
		game.processMatches(list);
		assertEquals(expected, board.getJewel(new Coordinate(0,3)));
	}
	
	/**
	 * Test to test the processMatch() method with a horizontal match.
	 */
	@Test
	public void processMatchHorizontal() {
		Jewel[][] grid = {
				{ new NormalJewel(Colour.Green), new NormalJewel(Colour.Red),
						new NormalJewel(Colour.Yellow) },
				{ new NormalJewel(Colour.Red), new NormalJewel(Colour.Red),
						new NormalJewel(Colour.Red) } };
		game.getBoard().setGrid(grid);
		Match match = new Match(game.getBoard());
		match.add(new Coordinate(0,1));
		match.add(new Coordinate(1,1));
		match.add(new Coordinate(2,1));
		List<Match> list = new ArrayList<>();
		list.add(match);
		Board board = game.getBoard();
		
		Jewel[] expected = new Jewel[3];
		System.arraycopy(board.getGrid()[0], 0, expected, 0, 3);
		game.processMatches(list);
		Jewel[] actual = board.getGrid()[1];
		assertArrayEquals(expected, actual);
	}
	
	@Test
	public void scoreNextLevel() {
		game.setLevel(10);
		assertEquals(3250, game.goalScore());
	}
	
	@Test
	public void nextLevelCheckFalse() {
		game.setScore(500);
		game.nextLevelCheck();
		assertEquals(1, game.getLevel());
		assertEquals(500, game.getScore());
	}
	
	@Test
	public void nextLevelCheckTrue() {
		game.setScore(2000);
		game.nextLevelCheck();
		assertEquals(2, game.getLevel());
		assertEquals(0, game.getScore());
	}
}
	
	

