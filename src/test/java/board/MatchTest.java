package board;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import jewel.Colour;
import jewel.HorizontalPowerJewel;
import jewel.Jewel;
import jewel.NormalJewel;
import jewel.VerticalPowerJewel;

import org.junit.Before;
import org.junit.Test;

public class MatchTest {
	
	private Board board;
	private Match match;
	private Match vertical3Match;
	private Match horizontal3Match;
	private Match horizontal5lMatch;
	private Match vertical5lMatch;
	
	private Coordinate x0y0 = new Coordinate(0,0);
	private Coordinate x0y1 = new Coordinate(0,1);
	private Coordinate x0y2 = new Coordinate(0,2);
	private Coordinate x1y0 = new Coordinate(1,0);
	private Coordinate x2y0 = new Coordinate(2,0);
	
	private Jewel[][] basicGrid = {
			{ new NormalJewel(Colour.White), new NormalJewel(Colour.White),
					new NormalJewel(Colour.White),
					new NormalJewel(Colour.White),
					new NormalJewel(Colour.White) },
			{ new NormalJewel(Colour.White), new NormalJewel(Colour.White),
					new NormalJewel(Colour.White),
					new NormalJewel(Colour.White),
					new NormalJewel(Colour.White) },
			{ new NormalJewel(Colour.White), new NormalJewel(Colour.White),
					new NormalJewel(Colour.White),
					new NormalJewel(Colour.White),
					new NormalJewel(Colour.White) },
			{ new NormalJewel(Colour.White), new NormalJewel(Colour.White),
					new NormalJewel(Colour.White),
					new NormalJewel(Colour.White),
					new NormalJewel(Colour.White) },
			{ new NormalJewel(Colour.White), new NormalJewel(Colour.White),
					new NormalJewel(Colour.White),
					new NormalJewel(Colour.White),
					new NormalJewel(Colour.White) } };
	
	private Jewel[][] powerJewelGrid = {
			{ new NormalJewel(Colour.White), new NormalJewel(Colour.White),
				new HorizontalPowerJewel(Colour.White),
				new NormalJewel(Colour.White),
				new NormalJewel(Colour.White) },
		{ new NormalJewel(Colour.White), new NormalJewel(Colour.White),
				new NormalJewel(Colour.White),
				new NormalJewel(Colour.White),
				new NormalJewel(Colour.White) },
		{ new NormalJewel(Colour.White), new NormalJewel(Colour.White),
				new NormalJewel(Colour.White),
				new NormalJewel(Colour.White),
				new VerticalPowerJewel(Colour.White) },
		{ new NormalJewel(Colour.White), new NormalJewel(Colour.White),
				new NormalJewel(Colour.White),
				new NormalJewel(Colour.White),
				new NormalJewel(Colour.White) },
		{ new HorizontalPowerJewel(Colour.White), new NormalJewel(Colour.White),
				new NormalJewel(Colour.White),
				new NormalJewel(Colour.White),
				new NormalJewel(Colour.White) } };
	
	@Before
	public void before() {
		board = new Board();
		board.setGrid(basicGrid);
		match = new Match(board);
		vertical3Match = new Match(board);
		horizontal3Match = new Match(board);
		horizontal5lMatch = new Match(board);
		vertical5lMatch = new Match(board);
		
		vertical3Match.add(x0y0);
		vertical3Match.add(x0y1);
		vertical3Match.add(x0y2);
		
		horizontal3Match.add(x0y0);
		horizontal3Match.add(x1y0);
		horizontal3Match.add(x2y0);
		
		Match innerMatch = new Match(board);
		innerMatch.add(x0y0);
		innerMatch.add(x0y1);
		innerMatch.add(x0y2);
		horizontal5lMatch.add(innerMatch);
		horizontal5lMatch.add(x0y0);
		horizontal5lMatch.add(x1y0);
		horizontal5lMatch.add(x2y0);
		
		Match outerMatch = new Match(board);
		outerMatch.add(x0y0);
		outerMatch.add(x1y0);
		outerMatch.add(x2y0);
		
		vertical5lMatch.add(x0y0);
		vertical5lMatch.add(x0y1);
		vertical5lMatch.add(x0y2);
		vertical5lMatch.add(outerMatch);
		
	}
	
	/**
	 * Testing the getCoordinates method with an empty list.
	 */
	@Test
	public void getCoordinatesEmptyList() {
		assertEquals(0, match.getMatchComponents().size());
	}
	
	/**
	 * Testing the addCoordinates method to see if the size of the list increases.
	 */
	@Test
	public void addCoordinates() {
		match.add(new Coordinate(0,0));
		assertEquals(1, match.getMatchComponents().size());
	}
	
	/**
	 * Testing the get() method.
	 */
	@Test
	public void get() {
		Coordinate expected = new Coordinate(0,0);
		match.add(expected);
		assertEquals(expected, match.getChild(0));
	}
	
	/**
	 * Testing the size() method.
	 */
	@Test
	public void size() {
		match.add(new Coordinate(0,0));
		assertEquals(1, match.size());
	}
	
	/**
	 * Testing the getPoints() method with a 3 match.
	 */
	@Test
	public void getPoints3Match() {
		match.add(new Coordinate(0,0));
		match.add(new Coordinate(0,1));
		match.add(new Coordinate(0,2));
		assertEquals(50, match.getPoints());
	}
	
	/**
	 * Testing the getPoints() method with a 4 match.
	 */
	@Test
	public void getPoints4Match() {
		match.add(new Coordinate(0,0));
		match.add(new Coordinate(0,1));
		match.add(new Coordinate(0,2));
		match.add(new Coordinate(0,3));
		assertEquals(100, match.getPoints());
	}
	
	/**
	 * Testing the getPoints() method with a 5 match.
	 */
	@Test
	public void getPoints5Match() {
		match.add(new Coordinate(0,0));
		match.add(new Coordinate(0,1));
		match.add(new Coordinate(0,2));
		match.add(new Coordinate(0,3));
		match.add(new Coordinate(0,4));
		assertEquals(500, match.getPoints());
	}
	
	@Test
	public void getPointsLMatch() {
		assertEquals(150, horizontal5lMatch.getPoints());
	}
	
	/**
	 * Testing the equals(Object other) method with other being a non-Match Object.
	 */
	@Test
	public void equalsDiffertentObject() {
		assertEquals(false, match.equals(new Coordinate(0,0)));
	}
	
	/**
	 * Testing the equals(Object other) method with other being a different sized match.
	 */
	@Test
	public void equalsDifferentSize() {
		match.add(new Coordinate(0,0));
		assertEquals(false, match.equals(new Match(board)));
	}
	
	/**
	 * Testing the equals(Object other) method with other being
	 * a same sized match with different elements.
	 */
	@Test
	public void equalsDifferentElements() {
		match.add(new Coordinate(0,0));
		Match other = new Match(board);
		other.add(new Coordinate(1,1));
		assertEquals(false, match.equals(other));
	}
	
	/**
	 * Testing the equals(Object other) method with other being
	 * a same sized match with the same elements in a different order.
	 */
	@Test
	public void equalsDifferentOrder() {
		Match other = new Match(board);
		Coordinate c1 = new Coordinate(0,0);
		Coordinate c2 = new Coordinate(1,1);
		
		match.add(c1);
		match.add(c2);
		other.add(c2);
		other.add(c1);
		
		assertEquals(true, match.equals(other));
	}
	
	@Test
	public void testOuterVerticalTrue() {
		assertTrue(vertical3Match.outerVertical());
	}
	
	@Test
	public void testOuterVerticalFalse() {
		assertFalse(horizontal3Match.outerVertical());
	}
	
	@Test
	public void testOuterHorizontalTrue() {
		assertTrue(horizontal3Match.outerHorizontal());
	}
	
	@Test
	public void testOuterHorizontalFalse() {
		assertFalse(vertical3Match.outerHorizontal());
	}
	
	@Test
	public void testOuterHorizontalLMatchFalse() {
		assertFalse(vertical5lMatch.outerHorizontal());
	}	
	
	@Test
	public void testOuterHorizontalLMatchTrue() {
		assertTrue(horizontal5lMatch.outerHorizontal());
	}
	
	@Test
	public void testOuterVerticalLMatchFalse() {
		assertFalse(horizontal5lMatch.outerVertical());
	}
	
	@Test
	public void testOuterVerticalLMatchTrue() {
		assertTrue(vertical5lMatch.outerVertical());
	}
	
	@Test
	public void testNumberOfJewelsHorizontal() {
		assertEquals(3, horizontal3Match.numberOfJewels());
	}
	
	@Test
	public void testNumberOfJewelsVertical() {
		assertEquals(3, vertical3Match.numberOfJewels());
	}
	
	@Test
	public void testNumberOfJewelsHorizontalLMatch() {
		assertEquals(5, horizontal5lMatch.numberOfJewels());
	}
	
	@Test
	public void testNumberOfJewelsVerticalLMatch() {
		assertEquals(5, vertical5lMatch.numberOfJewels());
	}
	
	@Test
	public void testVerticalPowerJewelVerticalMatch() {
		board.setGrid(powerJewelGrid);
		Match match = new Match(board);
		match.add(new Coordinate(4,1));
		match.add(new Coordinate(4,2));
		match.add(new Coordinate(4,3));
		assertEquals(550, match.getPoints());
	}
	
	@Test
	public void testVerticalPowerJewelHorizontalMatch() {
		board.setGrid(powerJewelGrid);
		Match match = new Match(board);
		match.add(new Coordinate(2,2));
		match.add(new Coordinate(3,2));
		match.add(new Coordinate(4,2));
		assertEquals(550, match.getPoints());
	}
	
	@Test
	public void testVerticalPowerJewelLMatchNotOverlapped() {
		board.setGrid(powerJewelGrid);
		Match matchHorizontal = new Match(board);
		matchHorizontal.add(new Coordinate(2,2));
		matchHorizontal.add(new Coordinate(3,2));
		matchHorizontal.add(new Coordinate(4,2));
		Match matchVertical = new Match(board);
		matchVertical.add(new Coordinate(2,0));
		matchVertical.add(new Coordinate(2,1));
		matchVertical.add(new Coordinate(2,2));
		matchVertical.add(matchHorizontal);
		assertEquals(600, matchVertical.getPoints());	
	}
	
	@Test
	public void testVerticalPowerJewelLMatchOverlapped() {
		board.setGrid(powerJewelGrid);
		Match matchHorizontal = new Match(board);
		matchHorizontal.add(new Coordinate(2,2));
		matchHorizontal.add(new Coordinate(3,2));
		matchHorizontal.add(new Coordinate(4,2));
		Match matchVertical = new Match(board);
		matchVertical.add(new Coordinate(4,0));
		matchVertical.add(new Coordinate(4,1));
		matchVertical.add(new Coordinate(4,2));
		matchVertical.add(matchHorizontal);
		assertEquals(600, matchVertical.getPoints());
	}
}
