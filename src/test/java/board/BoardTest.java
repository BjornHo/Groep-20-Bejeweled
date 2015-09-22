package board;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import board.Board;
import board.BoardListener;
import board.Coordinate;
import board.Match;
import board.StatsListener;
import gui.ScoreBoard;
import jewel.Colour;
import jewel.Jewel;




public class BoardTest {

	private Board board;
	@Before
	public void before(){
		board = new Board();
	}
	
	/**
	 * Test to test the getGrid() and setGrid() methods.
	 */
	
	@Test
	public void setGetGridTest() {
		Jewel[][] grid = {
			{new Jewel(Colour.Red), new Jewel(Colour.Blue)},
			{new Jewel(Colour.Green), new Jewel(Colour.Yellow)}
		};
		board.setGrid(grid);
		assertArrayEquals(grid, board.getGrid());
	}
	
	/**
	 * Test to test the getJewel() and setJewel() methods.
	 */
	
	@Test
	public void setGetJewel(){
		Jewel[][] grid = {
				{null, null},
				{null, null}
		};
		board.setGrid(grid);
		Jewel expected = new Jewel(Colour.Red);
		Coordinate location = new Coordinate(0,0);
		board.setJewel(expected, location);
		assertEquals(expected, board.getJewel(location));
	}
	
	/**
	 * Test to test the getSelectedJewel() and setSelectedJewel() methods.
	 */
	
	@Test
	public void setGetSelectedJewel(){
		Jewel[][] grid = {
				{null, null},
				{null, null}
		};
		board.setGrid(grid);
		Coordinate expected = new Coordinate(0,0);
		board.setSelectedJewel(expected);
		assertEquals(expected, board.getSelectedJewel());
	}
	
	/**
	 * Test to test the getBoardListeners() method.
	 */
	
	@Test
	public void getBoardListeners(){
		List<BoardListener> result = board.getBoardListeners();
		assertEquals(0, result.size());
	}
	
	/**
	 * Test to test the hasSelectedJewel() when there is no selected Jewel.
	 */
	
	@Test
	public void hasSelectedJewelFalse(){
		assertEquals(false, board.hasSelectedJewel());
	}
	
	/**
	 * Test to test the hasSelectedJewel() when there is a selected Jewel.
	 */
	
	@Test
	public void hasSelectedJewelTrue(){
		board.setSelectedJewel(new Coordinate(0,0));
		assertEquals(true, board.hasSelectedJewel());
	}
	
	/**
	 * Test to check whether the checkHorizontalMatches() method gives an empty list when there are no horizontal matches.
	 */
	
	@Test 
	public void checkHorizontalMatchesNoMatches(){
		List<Match> matched = new ArrayList<Match>();
		board.checkHorizontalMatches(matched);
		assertEquals(0, matched.size());
	}
	
	/**
	 * Test to check whether the checkHorizontalMatches() method gives a list with one match when there is 1 horizontal 4-match.
	 */
	
	@Test
	public void checkHorizontalMatchesOneFourMatch(){
		Jewel[][] grid = {
				{ new Jewel(Colour.Red), new Jewel(Colour.Red), new Jewel(Colour.Red), new Jewel(Colour.Red), new Jewel(Colour.Blue), new Jewel(Colour.Orange), new Jewel(Colour.Green), new Jewel(Colour.Blue)},
				{ new Jewel(Colour.Red), new Jewel(Colour.Blue), new Jewel(Colour.Orange), new Jewel(Colour.Green), new Jewel(Colour.Blue), new Jewel(Colour.Orange), new Jewel(Colour.Green), new Jewel(Colour.Blue)},
				{ new Jewel(Colour.Red), new Jewel(Colour.Orange), new Jewel(Colour.Green), new Jewel(Colour.Blue), new Jewel(Colour.Orange), new Jewel(Colour.Green), new Jewel(Colour.Blue), new Jewel(Colour.Orange)},
				{ new Jewel(Colour.Red), new Jewel(Colour.Green), new Jewel(Colour.Blue), new Jewel(Colour.Orange), new Jewel(Colour.Green), new Jewel(Colour.Blue), new Jewel(Colour.Orange), new Jewel(Colour.Green)},
				{ new Jewel(Colour.White), new Jewel(Colour.Orange), new Jewel(Colour.Red), new Jewel(Colour.White), new Jewel(Colour.Orange), new Jewel(Colour.Orange), new Jewel(Colour.Yellow), new Jewel(Colour.White) },
				{ new Jewel(Colour.White), new Jewel(Colour.Red), new Jewel(Colour.Blue), new Jewel(Colour.Yellow), new Jewel(Colour.Yellow), new Jewel(Colour.Green), new Jewel(Colour.Red), new Jewel(Colour.Yellow) },
				{ new Jewel(Colour.Orange), new Jewel(Colour.Yellow), new Jewel(Colour.Blue), new Jewel(Colour.Purple), new Jewel(Colour.Red), new Jewel(Colour.Red), new Jewel(Colour.Green), new Jewel(Colour.Yellow) },
				{ new Jewel(Colour.White), new Jewel(Colour.Purple), new Jewel(Colour.Green), new Jewel(Colour.Blue), new Jewel(Colour.Yellow), new Jewel(Colour.Orange), new Jewel(Colour.Red), new Jewel(Colour.Green) }
		};
		board.setGrid(grid);
		
		List<Match> matched = new ArrayList<Match>();
		board.checkHorizontalMatches(matched);
		Coordinate expected = new Coordinate(3,0);
		
		assertEquals(1, matched.size());
		assertEquals(4, matched.get(0).size());
		assertEquals(expected, matched.get(0).get(3));
	}
	
	/**
	 * Test to check whether the checkHorizontalMatches() method gives a list with one match when there is 1 horizontal 5-match.
	 */
	
	@Test
	public void checkHorizontalMatchesOneFiveMatch(){
		Jewel[][] grid = {
				{ new Jewel(Colour.Red), new Jewel(Colour.Red), new Jewel(Colour.Red), new Jewel(Colour.Red), new Jewel(Colour.Red), new Jewel(Colour.Orange), new Jewel(Colour.Green), new Jewel(Colour.Blue)},
				{ new Jewel(Colour.Red), new Jewel(Colour.Blue), new Jewel(Colour.Orange), new Jewel(Colour.Green), new Jewel(Colour.Blue), new Jewel(Colour.Orange), new Jewel(Colour.Green), new Jewel(Colour.Blue)},
				{ new Jewel(Colour.Red), new Jewel(Colour.Orange), new Jewel(Colour.Green), new Jewel(Colour.Blue), new Jewel(Colour.Orange), new Jewel(Colour.Green), new Jewel(Colour.Blue), new Jewel(Colour.Orange)},
				{ new Jewel(Colour.Red), new Jewel(Colour.Green), new Jewel(Colour.Blue), new Jewel(Colour.Orange), new Jewel(Colour.Green), new Jewel(Colour.Blue), new Jewel(Colour.Orange), new Jewel(Colour.Green)},
				{ new Jewel(Colour.White), new Jewel(Colour.Orange), new Jewel(Colour.Red), new Jewel(Colour.White), new Jewel(Colour.Orange), new Jewel(Colour.Orange), new Jewel(Colour.Yellow), new Jewel(Colour.White) },
				{ new Jewel(Colour.White), new Jewel(Colour.Red), new Jewel(Colour.Blue), new Jewel(Colour.Yellow), new Jewel(Colour.Yellow), new Jewel(Colour.Green), new Jewel(Colour.Red), new Jewel(Colour.Yellow) },
				{ new Jewel(Colour.Orange), new Jewel(Colour.Yellow), new Jewel(Colour.Blue), new Jewel(Colour.Purple), new Jewel(Colour.Red), new Jewel(Colour.Red), new Jewel(Colour.Green), new Jewel(Colour.Yellow) },
				{ new Jewel(Colour.White), new Jewel(Colour.Purple), new Jewel(Colour.Green), new Jewel(Colour.Blue), new Jewel(Colour.Yellow), new Jewel(Colour.Orange), new Jewel(Colour.Red), new Jewel(Colour.Green) }
		};
		board.setGrid(grid);
		
		List<Match> matched = new ArrayList<Match>();
		board.checkHorizontalMatches(matched);
		Coordinate expected = new Coordinate(3,0);
		
		assertEquals(1, matched.size());
		assertEquals(5, matched.get(0).size());
		assertEquals(expected, matched.get(0).get(3));
	}
	
	/**
	 * Test to check whether the checkVerticalMatches() method gives a list with one match when there is 1 vertical 4-match.
	 */
	
	@Test
	public void checkVerticalMatchesOneFourMatch(){
		Jewel[][] grid = {
				{ new Jewel(Colour.Red), new Jewel(Colour.Red), new Jewel(Colour.Red), new Jewel(Colour.Red), new Jewel(Colour.Blue), new Jewel(Colour.Orange), new Jewel(Colour.Green), new Jewel(Colour.Blue)},
				{ new Jewel(Colour.Red), new Jewel(Colour.Blue), new Jewel(Colour.Orange), new Jewel(Colour.Green), new Jewel(Colour.Blue), new Jewel(Colour.Orange), new Jewel(Colour.Green), new Jewel(Colour.Blue)},
				{ new Jewel(Colour.Red), new Jewel(Colour.Orange), new Jewel(Colour.Green), new Jewel(Colour.Blue), new Jewel(Colour.Orange), new Jewel(Colour.Green), new Jewel(Colour.Blue), new Jewel(Colour.Orange)},
				{ new Jewel(Colour.Red), new Jewel(Colour.Green), new Jewel(Colour.Blue), new Jewel(Colour.Orange), new Jewel(Colour.Green), new Jewel(Colour.Blue), new Jewel(Colour.Orange), new Jewel(Colour.Green)},
				{ new Jewel(Colour.White), new Jewel(Colour.Orange), new Jewel(Colour.Red), new Jewel(Colour.White), new Jewel(Colour.Orange), new Jewel(Colour.Orange), new Jewel(Colour.Yellow), new Jewel(Colour.White) },
				{ new Jewel(Colour.White), new Jewel(Colour.Red), new Jewel(Colour.Blue), new Jewel(Colour.Yellow), new Jewel(Colour.Yellow), new Jewel(Colour.Green), new Jewel(Colour.Red), new Jewel(Colour.Yellow) },
				{ new Jewel(Colour.Orange), new Jewel(Colour.Yellow), new Jewel(Colour.Blue), new Jewel(Colour.Purple), new Jewel(Colour.Red), new Jewel(Colour.Red), new Jewel(Colour.Green), new Jewel(Colour.Yellow) },
				{ new Jewel(Colour.White), new Jewel(Colour.Purple), new Jewel(Colour.Green), new Jewel(Colour.Blue), new Jewel(Colour.Yellow), new Jewel(Colour.Orange), new Jewel(Colour.Red), new Jewel(Colour.Green) }
		};
		board.setGrid(grid);
		
		List<Match> matched = new ArrayList<Match>();
		board.checkVerticalMatches(matched);
		Coordinate expected = new Coordinate(0,3);
		
		assertEquals(1, matched.size());
		assertEquals(4, matched.get(0).size());
		assertEquals(expected, matched.get(0).get(3));
	}
	
	/**
	 * Test to check whether the checkVerticalMatches() method gives a list with one match when there is 1 vertical 5-match.
	 */
	
	@Test
	public void checkVerticalMatchesOneFiveMatch(){
		Jewel[][] grid = {
				{ new Jewel(Colour.Red), new Jewel(Colour.Red), new Jewel(Colour.Red), new Jewel(Colour.Red), new Jewel(Colour.Blue), new Jewel(Colour.Orange), new Jewel(Colour.Green), new Jewel(Colour.Blue)},
				{ new Jewel(Colour.Red), new Jewel(Colour.Blue), new Jewel(Colour.Orange), new Jewel(Colour.Green), new Jewel(Colour.Blue), new Jewel(Colour.Orange), new Jewel(Colour.Green), new Jewel(Colour.Blue)},
				{ new Jewel(Colour.Red), new Jewel(Colour.Orange), new Jewel(Colour.Green), new Jewel(Colour.Blue), new Jewel(Colour.Orange), new Jewel(Colour.Green), new Jewel(Colour.Blue), new Jewel(Colour.Orange)},
				{ new Jewel(Colour.Red), new Jewel(Colour.Green), new Jewel(Colour.Blue), new Jewel(Colour.Orange), new Jewel(Colour.Green), new Jewel(Colour.Blue), new Jewel(Colour.Orange), new Jewel(Colour.Green)},
				{ new Jewel(Colour.Red), new Jewel(Colour.Orange), new Jewel(Colour.Red), new Jewel(Colour.White), new Jewel(Colour.Orange), new Jewel(Colour.Orange), new Jewel(Colour.Yellow), new Jewel(Colour.White) },
				{ new Jewel(Colour.White), new Jewel(Colour.Red), new Jewel(Colour.Blue), new Jewel(Colour.Yellow), new Jewel(Colour.Yellow), new Jewel(Colour.Green), new Jewel(Colour.Red), new Jewel(Colour.Yellow) },
				{ new Jewel(Colour.Orange), new Jewel(Colour.Yellow), new Jewel(Colour.Blue), new Jewel(Colour.Purple), new Jewel(Colour.Red), new Jewel(Colour.Red), new Jewel(Colour.Green), new Jewel(Colour.Yellow) },
				{ new Jewel(Colour.White), new Jewel(Colour.Purple), new Jewel(Colour.Green), new Jewel(Colour.Blue), new Jewel(Colour.Yellow), new Jewel(Colour.Orange), new Jewel(Colour.Red), new Jewel(Colour.Green) }
		};
		board.setGrid(grid);
		
		List<Match> matched = new ArrayList<Match>();
		board.checkVerticalMatches(matched);
		Coordinate expected = new Coordinate(0,4);
		
		assertEquals(1, matched.size());
		assertEquals(5, matched.get(0).size());
		assertEquals(expected, matched.get(0).get(4));
	}
	
	/**
	 * Test to test the checkMatches() method when there are no matches.
	 */
	
	@Test 
	public void checkMatchesNoMatches(){
		List<Match> result = board.checkMatches();
		assertEquals(0, result.size());
	}
	
	/**
	 * Test to test the checkMatches() method when there are multiple matches.
	 */
	
	@Test
	public void checkMatchesMultipleMatches(){
		Jewel[][] grid = {
				{ new Jewel(Colour.Red), new Jewel(Colour.Red), new Jewel(Colour.Red), new Jewel(Colour.Yellow), new Jewel(Colour.Blue), new Jewel(Colour.Orange), new Jewel(Colour.Green), new Jewel(Colour.Blue)},
				{ new Jewel(Colour.Red), new Jewel(Colour.Blue), new Jewel(Colour.Orange), new Jewel(Colour.Green), new Jewel(Colour.Blue), new Jewel(Colour.Orange), new Jewel(Colour.Green), new Jewel(Colour.Blue)},
				{ new Jewel(Colour.Red), new Jewel(Colour.Orange), new Jewel(Colour.Green), new Jewel(Colour.Blue), new Jewel(Colour.Orange), new Jewel(Colour.Green), new Jewel(Colour.Blue), new Jewel(Colour.Orange)},
				{ new Jewel(Colour.Blue), new Jewel(Colour.Green), new Jewel(Colour.Blue), new Jewel(Colour.Orange), new Jewel(Colour.Green), new Jewel(Colour.Blue), new Jewel(Colour.Orange), new Jewel(Colour.Green)},
				{ new Jewel(Colour.White), new Jewel(Colour.Orange), new Jewel(Colour.Red), new Jewel(Colour.White), new Jewel(Colour.Orange), new Jewel(Colour.Orange), new Jewel(Colour.Yellow), new Jewel(Colour.White) },
				{ new Jewel(Colour.White), new Jewel(Colour.Red), new Jewel(Colour.Blue), new Jewel(Colour.Yellow), new Jewel(Colour.Yellow), new Jewel(Colour.Green), new Jewel(Colour.Red), new Jewel(Colour.Yellow) },
				{ new Jewel(Colour.Orange), new Jewel(Colour.Yellow), new Jewel(Colour.Blue), new Jewel(Colour.Purple), new Jewel(Colour.Red), new Jewel(Colour.Red), new Jewel(Colour.Green), new Jewel(Colour.Yellow) },
				{ new Jewel(Colour.White), new Jewel(Colour.Purple), new Jewel(Colour.Green), new Jewel(Colour.Blue), new Jewel(Colour.Yellow), new Jewel(Colour.Orange), new Jewel(Colour.Red), new Jewel(Colour.Green) }
		};
		board.setGrid(grid);
		List<Match> result = board.checkMatches();
		assertEquals(2, result.size());
		assertEquals(3, result.get(0).size());
		assertEquals(3, result.get(1).size());
		assertEquals(result.get(0).get(0), result.get(1).get(0));
	}

}
