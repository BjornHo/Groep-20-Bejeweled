
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.junit.Before;
import org.junit.Test;


public class BoardTest {
	
	private Board b;
	private GUI g;
	
	@Before
	public void before(){
		b = new Board();
	}
	
	@Test
	public void setGetGridTest() {
		Jewel[][] grid = {
				{new Jewel(Colour.Red), new Jewel(Colour.Blue)},
				{new Jewel(Colour.Green), new Jewel(Colour.Yellow)}
		};
		b.setGrid(grid);
		assertArrayEquals(grid, b.getGrid());
	}
	
	@Test
	public void setGetJewel(){
		Jewel[][] grid = {
				{null, null},
				{null, null}
		};
		b.setGrid(grid);
		Jewel expected = new Jewel(Colour.Red);
		Coordinate location = new Coordinate(0,0);
		b.setJewel(expected, location);
		assertEquals(expected, b.getJewel(location));
	}
	
	@Test
	public void setGetSelectedJewel(){
		Jewel[][] grid = {
				{null, null},
				{null, null}
		};
		b.setGrid(grid);
		Coordinate expected = new Coordinate(0,0);
		b.setSelectedJewel(expected);
		assertEquals(expected, b.getSelectedJewel());
	}
	
	@Test
	public void getBoardListeners(){
		List<BoardListener> result = b.getBoardListeners();
		assertEquals(0, result.size());
	}
	
	@Test
	public void addBoardListener() throws IOException, LineUnavailableException, UnsupportedAudioFileException{
		GUI g = new GUI(b);
		assertEquals(1, b.getBoardListeners().size());
		b.addBoardListener(g);
		assertEquals(2, b.getBoardListeners().size());
		assertEquals(g, b.getBoardListeners().get(1));
	}
	
	@Test
	public void getStatsListeners(){
		List<StatsListener> result = b.getStatsListeners();
		assertEquals(0, result.size());
	}
	
	@Test
	public void addStatsListener() throws IOException, LineUnavailableException, UnsupportedAudioFileException{
		ScoreBoard s = new ScoreBoard();
		assertEquals(0, b.getStatsListeners().size());
		b.addStatsListener(s);
		assertEquals(1, b.getStatsListeners().size());
		assertEquals(s, b.getStatsListeners().get(0));
	}
	
	@Test
	public void hasSelectedJewelFalse(){
		assertEquals(false, b.hasSelectedJewel());
	}
	
	@Test
	public void hasSelectedJewelTrue(){
		b.setSelectedJewel(new Coordinate(0,0));
		assertEquals(true, b.hasSelectedJewel());
	}
	
	@Test 
	public void checkHorizontalMatchesNoMatches(){
		List<Match> matched = new ArrayList<Match>();
		b.checkHorizontalMatches(matched);
		assertEquals(0, matched.size());
	}
	
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
		b.setGrid(grid);
		
		List<Match> matched = new ArrayList<Match>();
		b.checkHorizontalMatches(matched);
		Coordinate expected = new Coordinate(3,0);
		
		assertEquals(1, matched.size());
		assertEquals(4, matched.get(0).size());
		assertEquals(expected, matched.get(0).get(3));
	}
	
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
		b.setGrid(grid);
		
		List<Match> matched = new ArrayList<Match>();
		b.checkHorizontalMatches(matched);
		Coordinate expected = new Coordinate(3,0);
		
		assertEquals(1, matched.size());
		assertEquals(5, matched.get(0).size());
		assertEquals(expected, matched.get(0).get(3));
	}
	
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
		b.setGrid(grid);
		
		List<Match> matched = new ArrayList<Match>();
		b.checkVerticalMatches(matched);
		Coordinate expected = new Coordinate(0,3);
		
		assertEquals(1, matched.size());
		assertEquals(4, matched.get(0).size());
		assertEquals(expected, matched.get(0).get(3));
	}
	
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
		b.setGrid(grid);
		
		List<Match> matched = new ArrayList<Match>();
		b.checkVerticalMatches(matched);
		Coordinate expected = new Coordinate(0,4);
		
		assertEquals(1, matched.size());
		assertEquals(5, matched.get(0).size());
		assertEquals(expected, matched.get(0).get(4));
	}
	
	@Test 
	public void checkMatchesNoMatches(){
		List<Match> result = b.checkMatches();
		assertEquals(0, result.size());
	}
	
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
		b.setGrid(grid);
		List<Match> result = b.checkMatches();
		assertEquals(2, result.size());
		assertEquals(3, result.get(0).size());
		assertEquals(3, result.get(1).size());
		assertEquals(result.get(0).get(0), result.get(1).get(0));
	}
	
	@Test
	public void processJewelNoSelectedJewel(){
		Coordinate expected = new Coordinate(0,0);
		b.processJewel(expected);
		assertEquals(expected, b.getSelectedJewel());
	}
	
	@Test
	public void processJewelSelectedNotAdjacent(){
		b.setSelectedJewel(new Coordinate(0,0));
		Coordinate expected = new Coordinate(1,1);
		b.processJewel(expected);
		assertEquals(expected, b.getSelectedJewel());
	}
	
	@Test
	public void processJewelSelectedJewel(){
		Coordinate expected = new Coordinate(0,0);
		b.setSelectedJewel(expected);
		b.processJewel(expected);
		assertEquals(expected, b.getSelectedJewel());
	}
	
	@Test
	public void processJewelSelectedAdjacentNoMatch(){
		Jewel expected = new Jewel(Colour.Orange);
		b.setJewel(expected, new Coordinate(0,0));
		b.setSelectedJewel(new Coordinate(0,0));
		b.processJewel(new Coordinate(0,1));
		assertEquals(false, b.hasSelectedJewel());
		assertEquals(expected, b.getJewel(new Coordinate(0,0)));
	}
	
	@Test
	public void processJewelSelectedAdjacentMatch(){
		Jewel expected = b.getJewel(new Coordinate(2,4));
		b.setSelectedJewel(new Coordinate(3,7));
		b.processJewel(new Coordinate(2,7));
		assertEquals(expected, b.getJewel(new Coordinate(2,7)));
	}
	
	@Test
	public void processMatchVertical(){
		Match m = new Match();
		Jewel expected = b.getJewel(new Coordinate(0,4));
		m.add(new Coordinate(0,5));
		m.add(new Coordinate(0,6));
		m.add(new Coordinate(0,7));
		b.processMatch(m);
		assertEquals(expected, b.getJewel(new Coordinate(0,7)));
	}
	
	@Test
	public void processMatchHorizontal(){
		Match m = new Match();
		Jewel expected = b.getJewel(new Coordinate(0,6));
		m.add(new Coordinate(0,7));
		m.add(new Coordinate(1,7));
		m.add(new Coordinate(2,7));
		b.processMatch(m);
		assertEquals(expected, b.getJewel(new Coordinate(0,7)));
	}

}
